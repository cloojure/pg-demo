(ns pg-demo.core
  (:require [clojure.java.jdbc      :as jdbc]
            [java-jdbc.ddl          :as ddl]
            [java-jdbc.sql          :as jdbc-sql]
            [honeysql.core          :as honey]
            [honeysql.helpers       :refer :all]
;           [cooljure.misc          :as cool-misc]
  )
  (:use cooljure.core cooljure.misc)
  (:import java.util.TimeZone)
  (:gen-class))

(def db-spec
  { :classname "org.postgresql.Driver"
    :subprotocol "postgresql"
    :subname "//localhost:5432/ubuntu"
  ; :subname "//localhost:5432/alan"
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

(defn ora1 []
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "ora1")
  (let [
    db-spec { :classname    "oracle.jdbc.OracleDriver"  ; must be in classpath
              :subprotocol  "oracle"
              :subname      "thin:@//10.100.6.231:1521/pvram" 
              :user         "mart_user"
              :password     "rxlogix" } 
;   db-str  "jdbc:oracle:thin:@//argus-mart-db01.eng.rxlogix.com:1521:pvram" 
  ]
    ; Stupid Oracle DB will often crash without this set
    (let [timeZone  (TimeZone/getTimeZone "America/Los_Angeles") ]
      (TimeZone/setDefault timeZone))
    (newline)
    (spy :msg "pkg_rls.set_context"
      (jdbc/execute! db-spec [
        "begin
           pkg_rls.set_context ('admin', '1','ARGUS_MART', '#$!AgSeRvIcE@SaFeTy');
         end; " ] ))
    (spyx (jdbc/query db-spec [ "select sysdate as current_day from dual" ] ))
    (spyx (jdbc/query db-spec [ "select BANNER from SYS.V_$VERSION" ] ))

    (newline)
    (println "Trying count(*)...")
    (spyx (jdbc/query db-spec [ "select * from rm_case_master" ] ))

    (newline)
    (println "using conn")
    (jdbc/with-db-connection [db-conn db-spec]
      (spy :msg "  1st stmt"
        (jdbc/execute! db-conn [
          "begin
             pkg_rls.set_context ('admin', '1','ARGUS_MART', '#$!AgSeRvIcE@SaFeTy');
           end; " ] ))
      (spy :msg "  2st stmt"
        (let [result  (jdbc/query db-conn 
                        [ "select * from rm_case_master" ]
              ;         :result-set-fn first
                        :result-set-fn #(doall (take 5 %))
                      ) 
              ]
          (doseq [it result]
            (newline)
            (prn it))
          (newline)))

      (spy :msg "  count(*)"
        (jdbc/query db-conn [ "select count(*) as result from rm_case_master" ] ))
      (spy :msg "  count(1)"
        (jdbc/query db-conn [ "select count(1) as result from rm_case_master" ] ))
    )

    (newline)
    (println "using conn #2")
    (jdbc/with-db-connection [db-conn db-spec]
      (spy :msg "  1st stmt"
        (jdbc/execute! db-conn [
          "begin
             pkg_rls.set_context ('admin', '1','ARGUS_MART', '#$!AgSeRvIcE@SaFeTy');
           end; " ] ))
      (time
        (spy :msg "  2st stmt"
          (jdbc/query db-conn 
                          [ "select * from rm_case_master" ]
                          :result-set-fn count )))
    )

   (newline)))

(defn ora2 []
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "ora2")
  (let [
    db-spec { :classname    "oracle.jdbc.OracleDriver"  ; must be in classpath
              :subprotocol  "oracle"
              :subname      "thin:@//10.100.6.231:1521/pvram" 
              :user         "mart_user_bkp"
              :password     "rxlogix" } 
  ]
    ; Stupid Oracle DB will often crash without this set
    (let [timeZone  (TimeZone/getTimeZone "America/Los_Angeles") ]
      (TimeZone/setDefault timeZone))
    (newline)
    #_(spy :msg "pkg_rls.set_context"
      (jdbc/execute! db-spec [
        "begin
           pkg_rls.set_context ('admin', '1','ARGUS_MART', '#$!AgSeRvIcE@SaFeTy');
         end; " ] ))
    (spyx (jdbc/query db-spec [ "select sysdate as current_day from dual" ] ))
    (spyx (jdbc/query db-spec [ "select BANNER from SYS.V_$VERSION" ] ))

    (newline)
    (println "using conn")
    (jdbc/with-db-connection [db-conn db-spec]
      (spy :msg "  1st stmt"
        #_(jdbc/execute! db-conn [
          "begin
             pkg_rls.set_context ('admin', '1','ARGUS_MART', '#$!AgSeRvIcE@SaFeTy');
           end; " ] ))
      (spy :msg "  2st stmt"
        (let [result  (jdbc/query db-conn 
                        [ "select * from rm_case_master" ]
              ;         :result-set-fn first
                        :result-set-fn #(doall (take 5 %))
                      ) 
              ]
          (doseq [it result]
            (newline)
            (prn (into (sorted-map) it)))
          (newline)))

      (spy :msg "  count(*)"
        (jdbc/query db-conn [ "select count(*) as result from rm_case_master" ] ))
      (spy :msg "  count(1)"
        (jdbc/query db-conn [ "select count(1) as result from rm_case_master" ] ))
    )

    (newline)
    (println "using conn #2")
    (jdbc/with-db-connection [db-conn db-spec]
      (spy :msg "  1st stmt"
        #_(jdbc/execute! db-conn [
          "begin
             pkg_rls.set_context ('admin', '1','ARGUS_MART', '#$!AgSeRvIcE@SaFeTy');
           end; " ] ))
      ; perf: about 11K rows/sec tx between servers on AWS
      (time
        (spy :msg "  2st stmt"
          (jdbc/query db-conn 
                          [ "select * from rm_case_master where rownum < 100000" ]
                          :result-set-fn count )))
    )

   (newline)))


(defn -main []
; (v0)
; (v1)
; (ora1)
  (ora2)
)

