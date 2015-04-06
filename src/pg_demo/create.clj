(ns pg-demo.create
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

(def dest-spec
  { :classname      "org.postgresql.Driver"
    :subprotocol    "postgresql"
    :subname        "//pg-test-1.cksh17mdz5oo.us-west-1.rds.amazonaws.com:5432/demo"
    :user           "rxlogix"
    :password       "rxlogix123"
  } )

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

(defn -main []
  (drop-create-pg)
)

