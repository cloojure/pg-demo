(ns pg-demo.core
  (:require [clojure.java.jdbc      :as jdbc]
            [java-jdbc.ddl          :as ddl]
            [java-jdbc.sql          :as jdbc-sql]
            [honeysql.core          :as honey]
            [honeysql.helpers       :refer :all]
;           [cooljure.misc          :as cool-misc]
  )
  (:use cooljure.core cooljure.misc)
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
  (println "-----------------------------------------------------------------------------")
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
      (jdbc-sql/select * :tags (jdbc-sql/where {:name "Clojure"} ))))
 (newline))

(defn v1 []
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "v1")
  (newline)

  (spy :msg "drop"
    (jdbc/execute! db-spec [(format "drop table if exists %s cascade" 
      (kw->dbstr :compute-langs)) ] ))

  (newline)
  (println "create")
  (jdbc/db-do-commands db-spec 
    (ddl/create-table
      (kw->dbstr :compute-langs)
      [:id        :serial "primary key"]
      [:name      :text   "not null"] ))

  (newline)
  (spy :msg "insert"
    (jdbc/execute! db-spec 
      (spy :msg "sql"
        (honey/format 
          (-> (insert-into :compute-langs)
              (values [
                {:name "Clojure"}
                {:name "Java"} ] ))))))

  (newline)
  (spy :msg "query all"
    (jdbc/query db-spec
      (spy :msg "sql"
        (honey/format 
          { :select [:*]  :from [:compute-langs] } ))))

  (newline)
  (spy :msg "query"
    (jdbc/query db-spec
      (spy :msg "sql"
        (honey/format 
          { :select [:*]  :from [:compute-langs]  :where [:= :name "Clojure"] } ))))

  (newline)
  (spy :msg "query helper"
    (jdbc/query db-spec
      (spy :msg "sql"
        (honey/format 
          (-> (select :*)
              (from :compute-langs)
              (where [:= :name "Clojure"] ))))))

 (newline))

(defn ora []
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "ora")
  (let [
    db-spec { :classname "oracle.jdbc.OracleDriver"  ; must be in classpath
              :subprotocol "oracle"
              :subname "thin:@//argus-mart-db01.eng.rxlogix.com:1521:pvram" 
              :user "mart_user"
              :password "rxlogix" } 
    db-str  "jdbc:oracle:thin:@//argus-mart-db01.eng.rxlogix.com:1521:pvram" 
  ]
    (newline)
    (spy :msg "pkg_rls.set_context"
      (jdbc/execute! db-spec [
         "begin
            pkg_rls.set_context ('admin', '1','ARGUS_MART', '#$!AgSeRvIcE@SaFeTy');
          end; " ] ))

    (newline)
    (spy :msg "count(*)"
      (jdbc/execute! db-spec [" select count(*) from rm_case_master; "] ))

   (newline)))

(defn -main []
; (v1)
  (ora)
)

