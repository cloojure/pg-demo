(ns pg-demo.core
  (:require [clojure.java.jdbc      :as jdbc]
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

(def pg-spec
  { :classname    "org.postgresql.Driver"
    :subprotocol  "postgresql"
    :subname      "//localhost:5432/ubuntu" } )

(def ora-spec    
  { :classname    "oracle.jdbc.OracleDriver"  ; must be in classpath
    :subprotocol  "oracle"
    :subname      "thin:@//10.100.6.231:1521/pvram"
    :user         "mart_user"
    :password     "rxlogix" } )

(def insert-chunk-size 1000)
(defn result-set->pg-insert [tbl-name-str result-set]
  (println "inserting result-set -> " tbl-name-str) 
  (jdbc/with-db-connection [pg-conn pg-spec]
    (let [rows-inserted (atom 0) ]
      (doseq [it (partition-all 1000 result-set)]
        (print "  " (swap! rows-inserted + (count it)) "  ")
        (time
          (apply jdbc/insert! pg-conn tbl-name-str it ))))))

(defn tx1 []
  (newline)
  (println "Initializing tables:")
  (newline)
  (time (tables/drop-create pg-spec))

  (let [
  ]
    ; Stupid Oracle DB will often crash without this set
    ; Stupid Oracle DB gives bogus timezone offsets
    (let [timeZone  (TimeZone/getTimeZone "UTC") ]      ; or "America/Los_Angeles"
      (TimeZone/setDefault timeZone))
    (newline)
    (spyx (jdbc/query ora-spec [ "select sysdate as current_day from dual" ] ))
    (newline)
    (spyx (jdbc/query ora-spec [ "select BANNER from SYS.V_$VERSION" ] ))

    (newline)
    (jdbc/with-db-connection [ora-conn ora-spec]
      (spy :msg "pkg_rls.set_context()"
        (jdbc/execute! ora-conn [
          "begin
             pkg_rls.set_context('admin', '1','ARGUS_MART', '#$!AgSeRvIcE@SaFeTy');
           end; " ] ))
      (spyx
        (jdbc/query ora-conn [ "select count(*) as result from rm_case_master" ] ))

      (newline)
      (println "Transferring rows from Oracle to Postgres:")
      (jdbc/query ora-conn
        [ "select * from rm_case_master" ]
        :result-set-fn  #(result-set->pg-insert "rm_case_master" %) ))

    (newline)
    (newline)
    (println "-----------------------------------------------------------------------------")
    (println "query:")
    (doseq [it (take 2 (jdbc/query pg-spec "select * from rm_case_master" )) ]
      (newline)
      (prn (into (sorted-map) it)))

    (newline)
  ))

(defn -main []
  (tx1)
)

