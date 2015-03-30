(ns pg-demo.core
  (:require [clojure.java.jdbc      :as jdbc]
            [clojure.set            :as set]
            [java-jdbc.ddl          :as ddl]
            [java-jdbc.sql          :as jdbc-sql]
            [honeysql.core          :as honey]
            [honeysql.helpers       :refer :all]
            [pg-demo.table-defs     :as tables]
            [com.climate.claypoole  :as cp]
  )
  (:use cooljure.core 
        cooljure.misc)
  (:import java.util.TimeZone)
  (:gen-class))

(def src-is-oracle false)
(def insert-chunk-size 10000)
(def pg-threadpool (cp/threadpool 8))
(def large-db false)

; (def ora-spec    
;   { :classname      "oracle.jdbc.OracleDriver"  ; must be in classpath
;     :subprotocol    "oracle"
;     :subname        "thin:@//10.100.6.231:1521/pvram"
;     :user           (if large-db  "mart_user_bkp" 
;                                   "mart_user" )
;     :password       "rxlogix" 
;   } )

; (def pg-spec
;   { :classname      "org.postgresql.Driver"
;     :subprotocol    "postgresql"
;     :subname        "//pg-test-1.cksh17mdz5oo.us-west-1.rds.amazonaws.com:5432/postal"
;     :user           "rxlogix"
;     :password       "rxlogix123"

; ;   :subname      (if large-db  "//localhost:5432/ubuntu_large"
; ;                               "//localhost:5432/ubuntu" ) 
;   } )

(def src-spec
  { :classname      "org.postgresql.Driver"
    :subprotocol    "postgresql"
    :subname        "//localhost:5432/ubuntu"
    :user           "ubuntu"
  } )

(def dest-spec
  { :classname    "oracle.jdbc.OracleDriver"  ; must be in classpath
    :subprotocol  "oracle"
    :subname      "thin:@//ora-test-1.cksh17mdz5oo.us-west-1.rds.amazonaws.com:1521/ORCL"
    :user         "rxlogix" 
    :password     "rxlogix123" 
  } )

(def column-name-corrections
  "A map used to rename problematic columns with more acceptable names.  In the future,
  it would be best if SQL reserved words (or their derivatives) were not used for column
  names in the first place."
  { :primary :primary_val 
    :level   :level_val } )


(defn src-set-context [src-conn]
  (when src-is-oracle
    (when-not large-db
      (jdbc/execute! src-conn 
        [ "begin
             pkg_rls.set_context('admin', '1','ARGUS_MART', '#$!AgSeRvIcE@SaFeTy');
           end; " ] ))))

(defn oracle-init-src []
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "oracle-init-src")
  ; Stupid Oracle DB will often crash without this set
  ; Stupid Oracle DB gives bogus timezone offsets
  (let [timeZone  (TimeZone/getTimeZone "UTC") ]      ; or "America/Los_Angeles"
    (TimeZone/setDefault timeZone))
  (newline) (spyx (jdbc/query src-spec "select sysdate as current_day from dual" ))

  ; Show Oracle DB version info
  (newline) (spyx (jdbc/query src-spec "select BANNER from SYS.V_$VERSION" ))

  ; Show size of main table
  (newline)
  (jdbc/with-db-connection [src-conn src-spec]
    (spy :msg "pkg_rls.set_context()" 
      (src-set-context src-conn))
    (newline)
    (spyx (jdbc/query src-conn "select count(*) as result from rm_case_master" )))
  (newline)
)

(defn oracle-init-dest []
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "oracle-init-dest")
  ; Stupid Oracle DB will often crash without this set
  ; Stupid Oracle DB gives bogus timezone offsets
  (let [timeZone  (TimeZone/getTimeZone "UTC") ]      ; or "America/Los_Angeles"
    (TimeZone/setDefault timeZone))
  (newline) (spyx (jdbc/query dest-spec "select sysdate as current_day from dual" ))

  ; Show Oracle DB version info
  (newline) (spyx (jdbc/query dest-spec "select BANNER from SYS.V_$VERSION" )))

(def src-table-rows 
  "A map from table-name to table-rows."
  (promise))

(defn test-src []
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "Testing src-spec...")
  (spyx (jdbc/query src-spec "select count(*) as result from rm_case_master" )))

(defn count-tables []
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "Counting rows for all source tables...")
  (time
    (jdbc/with-db-connection [src-conn src-spec]
     ;(src-set-context src-conn)
      (deliver src-table-rows 
        (into (sorted-map) 
          (for [table-name (sort (keys tables/table-name->creation-sql)) ]
            (let [table-rows   (-> (jdbc/query src-conn 
                                        [ (format "select count(*) as result from %s" table-name) ] )
                                first
                                :result
                                long ) 
            ]
              (println (format "%15d  %s" table-rows table-name))
              {table-name table-rows} )))))))

(defn drop-create-tables []
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "Running drop/create on all destination tables...")
  (time
    (jdbc/with-db-connection [pg-conn dest-spec]
      (doseq [ [table-name creation-sql-str] tables/table-name->creation-sql ]
        (print ".")
;       (println " " table-name)
        (flush)

        ; Stupid Oracle does not allow "drop table if exists <tablename>".  We need to use
        ; try/catch to find the exception and ignore the harmless ones
        (let [drop-cmd (format "drop table %s" table-name)]
          (try
;           (println "running:" drop-cmd)
            (jdbc/execute! pg-conn [drop-cmd] )
            (catch Exception ex 
              (let [ex-str (.toString ex) ]
                (when-not (re-find #"table or view does not exist" ex-str)
                  (throw (Exception. (str "Command failed: " drop-cmd "  Exception: " ex))))))))
;       (println "running:" creation-sql-str)
        (jdbc/execute! pg-conn [creation-sql-str] ))
      (newline)
    )))

(defn result-set->pg-insert [table-name result-set]
  (jdbc/with-db-connection [pg-conn dest-spec]
    (let [rows-inserted     (atom 0) 
          table-rows        (@src-table-rows table-name) ]
      (doseq [rows-chunk (partition-all insert-chunk-size result-set) ]
        (let [
          rows-chunk-new    (map #(set/rename-keys % column-name-corrections) rows-chunk) 
          start-time        (System/nanoTime)
        ]
          (apply jdbc/insert! pg-conn table-name rows-chunk-new  ) 
          (println (format "%9d/%9d  %35s  %10.3f"    
                    (swap! rows-inserted + (count rows-chunk-new))
                    table-rows table-name 
                    (/ (double (- (System/nanoTime) start-time)) 1e9)))
          (flush) )))))

(defn proc-table [table-name]
  (let [completed (atom false)]
    (while (not @completed)
      (Thread/sleep (-> (rand 5) (* 1000) (long)))  ; [0..5) seconds (in millis)
      (println "Processing:" table-name)
      (try
        (jdbc/with-db-connection [src-conn src-spec]
          (src-set-context src-conn)
          (jdbc/query src-conn [ (format "select * from %s" table-name) ]
            :result-set-fn  #(result-set->pg-insert table-name %) ))
        (reset! completed true)
        (catch Exception ex 
          (println (format "    %s failed... will retry. Error: %s " table-name (.toString ex)))
        )))))

(defn transfer-data []
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "Beginning data transfer...")
  (newline)
  (let [
    table-names-sorted    (as-> (keys @src-table-rows) it      ; table names
                                (sort-by @src-table-rows it)   ; sort by # rows
                                (reverse it))                     ; descending order
    table-futures-map     (into (sorted-map)
                            (for [table-name table-names-sorted]
                              { table-name (cp/future pg-threadpool (proc-table table-name)) } ))
  ]
    (doseq [it table-futures-map]
      (deref (val it))  ; block until complete
      (println (key it) "  done"))
    (shutdown-agents)
  ))

(defn -main []
  (test-src)
; (oracle-init-src)
  (oracle-init-dest)
  (drop-create-tables)
  (count-tables)

  (println "-----------------------------------------------------------------------------")
  (transfer-data)
)

