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


(def tx-chunk-size      1000)
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

(defn -main []
  (oracle-init-src)
  (count-tables)
  (drop-create-pg)

  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "complete")
)

