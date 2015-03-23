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

(def ora-spec    
  { :classname    "oracle.jdbc.OracleDriver"  ; must be in classpath
    :subprotocol  "oracle"
    :subname      "thin:@//10.100.6.231:1521/pvram"
    :user         "mart_user"
    :password     "rxlogix" } )

(def pg-spec
  { :classname    "org.postgresql.Driver"
    :subprotocol  "postgresql"
    :subname      "//localhost:5432/ubuntu" } )

(def insert-chunk-size 1000)


(defn drop-create [db-spec]
  (doseq [ [table-name creation-sql-str] tables/table-name->creation-sql ]
    (println (format "drop/create: %s" table-name))
    (jdbc/with-db-connection [db-conn db-spec]
      (jdbc/execute! db-conn [ (format "drop table if exists %s cascade" table-name) ] )
      (jdbc/execute! db-conn [creation-sql-str] ))))

(defn result-set->pg-insert [tbl-name-str result-set]
  (println "inserting result-set -> " tbl-name-str) 
  (jdbc/with-db-connection [pg-conn pg-spec]
    (let [rows-inserted (atom 0) ]
      (doseq [it (partition-all 1000 result-set)]
        (print "  " (swap! rows-inserted + (count it)) "  ")
        (time
          (apply jdbc/insert! pg-conn tbl-name-str it ))))))

(defn prepare-oracle []
  ; Stupid Oracle DB will often crash without this set
  ; Stupid Oracle DB gives bogus timezone offsets
  (let [timeZone  (TimeZone/getTimeZone "UTC") ]      ; or "America/Los_Angeles"
    (TimeZone/setDefault timeZone))
  (newline) (spyx (jdbc/query ora-spec [ "select sysdate as current_day from dual" ] ))

  ; Show Oracle DB version info
  (newline) (spyx (jdbc/query ora-spec [ "select BANNER from SYS.V_$VERSION" ] ))

  ; Show size of main table
  (newline)
  (jdbc/with-db-connection [ora-conn ora-spec]
    (spy :msg "pkg_rls.set_context()"
      (jdbc/execute! ora-conn [
        "begin
           pkg_rls.set_context('admin', '1','ARGUS_MART', '#$!AgSeRvIcE@SaFeTy');
         end; " ] ))
    (newline)
    (spyx (jdbc/query ora-conn [ "select count(*) as result from rm_case_master" ] ))))

(defn transfer-data []
  (println \newline "Initializing tables:")
  (time (drop-create pg-spec))

  (newline)
  (println "Transferring rows from Oracle to Postgres:")
  (jdbc/with-db-connection [ora-conn ora-spec]
    (jdbc/execute! ora-conn [
      "begin
         pkg_rls.set_context('admin', '1','ARGUS_MART', '#$!AgSeRvIcE@SaFeTy');
       end; " ] )
    (jdbc/query ora-conn
      ["select * from rm_case_master"]
      :result-set-fn  #(result-set->pg-insert "rm_case_master" %) ))

  (newline)
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "query:")
  (doseq [it (take 2 (jdbc/query pg-spec "select * from rm_case_master" )) ]
    (newline)
    (prn (into (sorted-map) it)))

  (newline))

(defn -main []
  (prepare-oracle)
  (transfer-data)
)

