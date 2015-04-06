(ns pg-demo.etl
  (:require [clojure.java.jdbc      :as jdbc]
            [clojure.set            :as set]
            [pg-demo.ddl            :as ddl]
            [com.climate.claypoole  :as cp]
  )
  (:use cooljure.core
        cooljure.misc)
  (:import java.util.TimeZone)
  (:gen-class))


(def tx-chunk-size      10000)
(def pg-threadpool
  (cp/threadpool        4))
(def src-is-oracle      true)
(def dest-is-oracle     false)


(def src-spec
  { :classname    "oracle.jdbc.OracleDriver"  ; must be in classpath
    :subprotocol  "oracle"
    :subname        "thin:@//10.100.6.231:1521/pvram"
    :user           "mart_user"
    :password       "rxlogix"
  } )

(def dest-spec
  { :classname      "org.postgresql.Driver"
    :subprotocol    "postgresql"
    :subname        "//10.100.6.130:5432/demo"
    :user           "demo"
    :password       "demo123"
  } )


(def column-name-corrections
  "A map used to rename problematic columns with more acceptable names.  In the future,
  it would be best if SQL reserved words (or their derivatives) were not used for column
  names in the first place."
  { :primary :primary_val
    :level   :level_val } )

(defn src-set-context [src-conn]
  (when src-is-oracle
    (jdbc/execute! src-conn
      [ "begin
           pkg_rls.set_context('admin', '1','ARGUS_MART', '#$!AgSeRvIcE@SaFeTy');
         end; " ] )))

(defn oracle-init-src []
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "oracle-init-src")
  ; Stupid Oracle DB will often crash without this set
  ; Stupid Oracle DB gives bogus timezone offsets
  (let [timeZone  (TimeZone/getTimeZone "UTC") ]      ; or "America/Los_Angeles"
    (TimeZone/setDefault timeZone))
  (newline)
  (spyx (jdbc/query src-spec "select sysdate as current_day from dual" ))

  ; Show Oracle DB version info
  (newline)
  (spyx (jdbc/query src-spec "select BANNER from SYS.V_$VERSION" ))

  ; Show size of main table
  (newline)
  (jdbc/with-db-connection [src-conn src-spec]
    (spy :msg "pkg_rls.set_context()"
      (src-set-context src-conn))
    (newline)
    (spyx (jdbc/query src-conn "select count(*) as result from rm_case_master" ))
    (newline)
  ))

(defn drop-create-pg []
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println (format "Running drop/create on all destination tables (%d)" (count ddl/table-ddl)))
  (jdbc/with-db-connection [pg-conn dest-spec]
    (doseq [ [table-kw table-ddl] ddl/table-ddl ]
      (let [table-name  (name table-kw)
            drop-cmd    (format "drop table if exists %s" table-name)
      ]
        (print ".")     ; (println " " table-name)
        (flush)
        (jdbc/execute! pg-conn [drop-cmd] )
        (jdbc/db-do-commands pg-conn
          (apply jdbc/create-table-ddl table-kw (seq table-ddl))))))
  (println))

(def src-table-rows
  "A map from table-name to table-rows."
  (promise))

(defn count-tables []
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "Counting rows for all source tables...")
  (time
    (jdbc/with-db-connection [src-conn src-spec]
      (src-set-context src-conn)
      (deliver src-table-rows
        (into (sorted-map)
          (for [table-name (sort (map name (keys ddl/table-ddl))) ]
            (let [table-rows   (-> (jdbc/query src-conn
                                        [ (format "select count(*) as result from %s" table-name) ] )
                                first
                                :result
                                long )
            ]
              (println (format "%15d  %s" table-rows table-name))
              {table-name table-rows} )))))))

;-----------------------------------------------------------------------------

(defn result-set->pg-insert [table-name result-set]
  (jdbc/with-db-connection [pg-conn dest-spec]
    (let [rows-inserted     (atom 0) 
          table-rows        (@src-table-rows table-name) ]
      (doseq [rows-chunk (partition-all tx-chunk-size result-set) ]
        (let [
          rows-chunk-new    (map #(set/rename-keys % column-name-corrections) rows-chunk) 
          start-time        (System/nanoTime)
        ]
          (try
            (apply jdbc/insert! pg-conn table-name rows-chunk-new  ) 
            (println (format "%9d/%9d  %35s  %10.3f"    
                      (swap! rows-inserted + (count rows-chunk-new))
                      table-rows table-name 
                      (/ (double (- (System/nanoTime) start-time)) 1e9)))
            (flush) 
            (catch Exception ex 
              (let [msg (format "    %s insert failed, error: %s  \n data: %s " 
                            table-name (.toString ex) rows-chunk-new) ]
                (spit "error.txt" msg)
                (flush) (println msg) (flush)
                (System/exit 1))))))))
  nil ; do not return the entire result-set!
)

(defn proc-table [table-name]
  (Thread/sleep (-> (rand 5) (* 1000) (long)))  ; 0..5 seconds (in millis)
  (println "Beginning:" table-name) (flush)
  (try
    (jdbc/with-db-connection [src-conn src-spec]
      (src-set-context src-conn)
      (jdbc/query src-conn [ (format "select * from %s" table-name) ]
        :result-set-fn  #(result-set->pg-insert table-name %) )
      (println "  finished:" table-name) (flush)
      nil ; do not return the result-set of the query!
    )
    (catch Exception ex 
      (println (format "    %s failed... Error: %s " table-name (.toString ex)))
      (println ex)
      (System/exit 1))))

;-----------------------------------------------------------------------------
(defn transfer-data []
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "Beginning data transfer...")
  (newline)
  (let [
    table-names-sorted    (as-> (keys @src-table-rows) it       ; table names
                                (sort-by @src-table-rows it)    ; sort by # rows
                                (reverse it))                   ; descending order
    table-futures-map     (forv [table-name table-names-sorted]
                            [ table-name (cp/future pg-threadpool 
                                              (proc-table table-name)) ] )
  ]
    (doseq [ [table-name table-future] table-futures-map]
      (deref table-future)  ; block until complete
      (println table-name "  done"))
    (shutdown-agents)
  ))

(defn -main []
  (oracle-init-src)
  (count-tables)
  (drop-create-pg)
  (transfer-data)

  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "complete")
)

