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

(def insert-chunk-size 1000)

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
  (println "-----------------------------------------------------------------------------")
)

(def enable-testing true)   ; #todo  FOR TESTING ONLY *******************************************
(def test-rs-limit-fn       ; #todo  FOR TESTING ONLY *******************************************
  (if enable-testing 
    #(take 1234 %)
    identity ))

(defn result-set->pg-insert [table-name table-rows result-set]
  (jdbc/with-db-connection [pg-conn pg-spec]
    (println (format "  drop/create: %s" table-name))
    (newline)
    (let [creation-sql-str (tables/table-name->creation-sql table-name) ]
      (jdbc/execute! pg-conn [ (format "drop table if exists %s cascade" table-name) ] )
      (jdbc/execute! pg-conn [creation-sql-str] ))

    (let [rows-inserted     (atom 0) 
          result-set        (test-rs-limit-fn result-set) ]
      (doseq [rows-chunk (partition-all 1000 result-set) ]
        (let [rows-chunk-new    (map #(set/rename-keys % column-name-corrections) rows-chunk) ]
          (print (format "%s: %7d/%7d  "    table-name 
                                            (swap! rows-inserted + (count rows-chunk-new))
                                            table-rows ))
          (time (apply jdbc/insert! pg-conn table-name rows-chunk-new  )))))

    (newline)
    (println table-name "query:")
    (doseq [it (take 2 (jdbc/query pg-spec (format "select * from %s" table-name ))) ]
      (newline)
      (prn (into (sorted-map) it)))
  ))

(defn proc-table [table-name]
  (jdbc/with-db-connection [ora-conn ora-spec]
    (oracle-set-context ora-conn)
    (let [table-rows    (-> (jdbc/query ora-conn 
                            [ (format "select count(*) as result from %s" table-name) ] )
                        first
                        :result
                        long )
    ]
      (newline)
      (println "-----------------------------------------------------------------------------")
      (println "Transferring rows from Oracle to Postgres:" table-name "  rows:" table-rows)
      (jdbc/query ora-conn [ (format "select * from %s" table-name) ]
        :result-set-fn  #(result-set->pg-insert table-name table-rows %) )
    )))

(defn transfer-data []
  (newline)
  (doseq [table-name (keys tables/table-name->creation-sql) ]
    (proc-table table-name))
  (newline))

(defn -main []
  (oracle-init)
  (transfer-data)
)

