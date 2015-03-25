(ns pg-demo.core
  (:require [clojure.java.jdbc      :as jdbc]
            [clojure.set            :as set]
            [java-jdbc.ddl          :as ddl]
            [java-jdbc.sql          :as jdbc-sql]
            [honeysql.core          :as honey]
            [honeysql.helpers       :refer :all]
;           [cooljure.misc          :as cool-misc]
            [pg-demo.table-defs     :as tables]
            [com.climate.claypoole  :as cp]
  )
  (:use cooljure.core 
        cooljure.misc)
  (:import java.util.TimeZone)
  (:gen-class))

(def large-db true)

(def ora-spec    
  { :classname    "oracle.jdbc.OracleDriver"  ; must be in classpath
    :subprotocol  "oracle"
    :subname      "thin:@//10.100.6.231:1521/pvram"
    :user         (if large-db  "mart_user_bkp" 
                                "mart_user" )
    :password     "rxlogix" 
  } )

(def pg-spec
  { :classname    "org.postgresql.Driver"
    :subprotocol  "postgresql"
    :subname      (if large-db  "//localhost:5432/ubuntu_large"
                                "//localhost:5432/ubuntu" ) 
  } )

(def insert-chunk-size 10000)

(def column-name-corrections
  "A map used to rename problematic columns with more acceptable names.  In the future,
  it would be best if SQL reserved words (or their derivatives) were not used for column
  names in the first place."
  { :primary :primary_val } )


(defn oracle-set-context [ora-conn]
  (when-not large-db
    (jdbc/execute! ora-conn 
      [ "begin
           pkg_rls.set_context('admin', '1','ARGUS_MART', '#$!AgSeRvIcE@SaFeTy');
         end; " ] )))

(defn oracle-init []
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "oracle-init")
  ; Stupid Oracle DB will often crash without this set
  ; Stupid Oracle DB gives bogus timezone offsets
  (let [timeZone  (TimeZone/getTimeZone "UTC") ]      ; or "America/Los_Angeles"
    (TimeZone/setDefault timeZone))
  (newline) (spyx (jdbc/query ora-spec "select sysdate as current_day from dual" ))

  ; Show Oracle DB version info
  (newline) (spyx (jdbc/query ora-spec "select BANNER from SYS.V_$VERSION" ))

  ; Show size of main table
  (newline)
  (jdbc/with-db-connection [ora-conn ora-spec]
    (spy :msg "pkg_rls.set_context()" 
      (oracle-set-context ora-conn))
    (newline)
    (spyx (jdbc/query ora-conn "select count(*) as result from rm_case_master" )))
  (newline)
)

(def oracle-table-rows 
  "A map from table-name to table-rows."
  (promise))

(defn count-tables []
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "Counting rows for all Oracle tables...")
  (time
    (jdbc/with-db-connection [ora-conn ora-spec]
      (oracle-set-context ora-conn)
      (deliver oracle-table-rows 
        (into (sorted-map) 
          (for [table-name (sort (keys tables/table-name->creation-sql)) ]
            (let [table-rows   (-> (jdbc/query ora-conn 
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
  (println "Running drop/create on all Postgres tables...")
  (time
    (jdbc/with-db-connection [pg-conn pg-spec]
      (doseq [ [table-name creation-sql-str] tables/table-name->creation-sql ]
        (print ".")
        (flush)
        (jdbc/execute! pg-conn [ (format "drop table if exists %s cascade" table-name) ] )
        (jdbc/execute! pg-conn [creation-sql-str] ))
      (newline)
    )))


(def enable-testing true)   ; #todo  FOR TESTING ONLY *******************************************
(def test-rs-limit-fn       ; #todo  FOR TESTING ONLY *******************************************
  (if enable-testing 
    #(take 12345 %)
    identity ))

(def pg-threadpool (cp/threadpool 4))

; This works, but too hard to control the number of simultaneous processes
(defn result-set->pg-insert [table-name result-set]
  (jdbc/with-db-connection [pg-conn pg-spec]
    (let [rows-inserted     (atom 0) 
          table-rows        (@oracle-table-rows table-name)
          result-set        (test-rs-limit-fn result-set) ]
      (doseq [rows-chunk (partition-all insert-chunk-size result-set) ]
        (let [rows-chunk-new (map #(set/rename-keys % column-name-corrections) rows-chunk) ]
          (time (apply jdbc/insert! pg-conn table-name rows-chunk-new  ))
          (println (format "%9d/%9d  %s"    (swap! rows-inserted + (count rows-chunk-new))
                                            table-rows
                                            table-name ))
          (newline) (flush) )))

    (when false
      (newline)
      (println table-name "query:")
      (doseq [it (take 2 (jdbc/query pg-spec (format "select * from %s" table-name ))) ]
        (newline)
        (prn (into (sorted-map) it))))
  ))

(defn proc-table [table-name]
  (let [completed (atom false)]
    (while (not @completed)
      (println "Processing:" table-name)
      (try
        (jdbc/with-db-connection [ora-conn ora-spec]
          (oracle-set-context ora-conn)
          (jdbc/query ora-conn [ (format "select * from %s" table-name) ]
            :result-set-fn  #(result-set->pg-insert table-name %) ))
        (reset! completed true)
        (catch Exception ex 
          (println (format "    %s failed... will retry" table-name))
          (Thread/sleep (-> (rand 5) (+ 5) (* 1000) (long)))  ; [5..10) seconds (in millis)
        )))))

(defn transfer-data []
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "Beginning data transfer...")
  (newline)
  (let [table-futures-map   (into (sorted-map)
                              (for [table-name (keys tables/table-name->creation-sql) ]
                                { table-name (cp/future pg-threadpool (proc-table table-name)) } ))
  ]
    (doseq [it table-futures-map]
      (deref (val it))  ; block until complete
      (println (key it) "  done"))
    (shutdown-agents)
  ))

(defn -main []
  (oracle-init)
  (count-tables)
  (drop-create-tables)
  (transfer-data)
)

