(ns pg-demo.core
  (:require [clojure.java.jdbc      :as jdbc]
            [clojure.set            :as set]
            [java-jdbc.ddl          :as ddl]
            [java-jdbc.sql          :as jdbc-sql]
            [honeysql.core          :as honey]
            [honeysql.helpers       :refer :all]
;           [cooljure.misc          :as cool-misc]
            [pg-demo.table-defs     :as tables]
  )
  (:use cooljure.core 
        cooljure.misc)
  (:import java.util.TimeZone)
  (:gen-class))

(def ora-spec    
  { :classname    "oracle.jdbc.OracleDriver"  ; must be in classpath
    :subprotocol  "oracle"
    :subname      "thin:@//10.100.6.231:1521/pvram"
    :user         "mart_user_bkp"
    :password     "rxlogix" } )

(def pg-spec
  { :classname    "org.postgresql.Driver"
    :subprotocol  "postgresql"
    :subname      "//localhost:5432/ubuntu_large" } )

(def insert-chunk-size 1000)

(def column-name-corrections
  "A map used to rename problematic columns with more acceptable names.  In the future,
  it would be best if SQL reserved words (or their derivatives) were not used for column
  names in the first place."
  { :primary :primary_val } )


(defn tables-drop-create []
  (jdbc/with-db-connection [pg-conn pg-spec]
    (doseq [ [table-name creation-sql-str] tables/table-name->creation-sql ]
      (println (format "  drop/create: %s" table-name))
      (jdbc/execute! pg-conn [ (format "drop table if exists %s cascade" table-name) ] )
      (jdbc/execute! pg-conn [creation-sql-str] ))))

(defn result-set->pg-insert [tbl-name-str result-set]
  (jdbc/with-db-connection [pg-conn pg-spec]
    (let [rows-inserted     (atom 0) ]
      (doseq [rows-chunk (partition-all 1000 result-set) ]
        (let [rows-chunk-new    (map #(set/rename-keys % column-name-corrections) rows-chunk) ]
          (print (format "%7d  " (swap! rows-inserted + (count rows-chunk-new))))
          (time
            (apply jdbc/insert! pg-conn tbl-name-str rows-chunk-new  )))))))

(defn oracle-set-context [ora-conn]
  (jdbc/execute! ora-conn 
    [ "begin
         pkg_rls.set_context('admin', '1','ARGUS_MART', '#$!AgSeRvIcE@SaFeTy');
       end; " ] ))

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
  (println "-----------------------------------------------------------------------------")
)

(defn transfer-data []
  (newline)
  (println "Initializing tables:")
  (tables-drop-create)

  (jdbc/with-db-connection [ora-conn ora-spec]
    (oracle-set-context ora-conn)
    (doseq [ table-name (keys tables/table-name->creation-sql) ]
      (newline)
      (newline)
      (println "-----------------------------------------------------------------------------")
      (println "Transferring rows from Oracle to Postgres:" table-name "  rows:"
        (-> (jdbc/query ora-conn [ (format "select count(*) as result from %s" table-name) ] )
            first
            :result
            long ))
      (jdbc/query ora-conn [ (format "select * from %s" table-name) ]
        :result-set-fn  #(result-set->pg-insert table-name %) )
      (newline)
      (println "query:")
      (doseq [it (take 2 (jdbc/query pg-spec (format "select * from %s" table-name ))) ]
        (newline)
        (prn (into (sorted-map) it)))
    ))
  (newline))

(defn -main []
  (oracle-init)
  (transfer-data)
)

