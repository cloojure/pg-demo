(ns pg-demo.core
  (:require [clojure.java.jdbc      :as jdbc]
            [java-jdbc.ddl          :as ddl]
            [java-jdbc.sql          :as sql])
  (:use cooljure.core)
  (:gen-class))

(def db-spec
  { :classname "org.postgresql.Driver"
    :subprotocol "postgresql"
    :subname "//localhost:5432/alan"
  ; :user "alan"
  ; :pass "secret" 
  })

(defn v0 []
  (newline)
  (println "v0")

  (spy :msg "drop"
    (jdbc/execute! db-spec ["drop table if exists tags cascade"] ))

  (spy :msg "create"
    (jdbc/db-do-commands db-spec 
      (ddl/create-table
        :tags
        [:id        :serial "primary key"]
        [:name      :text   "not null"] )))

  (spy :msg "insert"
    (jdbc/insert! db-spec :tags
      {:name "Clojure"}
      {:name "Java"} ))

  (spy :msg "query"
    (jdbc/query db-spec
      (sql/select * :tags (sql/where {:name "Clojure"} ))))
 (newline)
)

(defn -main []
 (v0)
)

