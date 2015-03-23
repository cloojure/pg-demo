(ns pg-demo.core
  (:require [clojure.java.jdbc      :as jdbc]
            [java-jdbc.ddl          :as ddl]
            [java-jdbc.sql          :as jdbc-sql]
            [honeysql.core          :as honey]
            [honeysql.helpers       :refer :all]
;           [cooljure.misc          :as cool-misc]
            [pg-demo.rm-case-master]
  )
  (:use cooljure.core cooljure.misc)
  (:import java.util.TimeZone)
  (:gen-class))

(def pg-spec
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
    (jdbc/execute! pg-spec ["drop table if exists tags cascade"] ))

  (spy :msg "create"
    (jdbc/db-do-commands pg-spec
      (ddl/create-table
        :tags
        [:id        :serial "primary key"]
        [:name      :text   "not null"] )))

  (spy :msg "insert"
    (jdbc/insert! pg-spec :tags
      {:name "Clojure"}
      {:name "Java"} ))

  (spy :msg "query"
    (jdbc/query pg-spec
      (jdbc-sql/select * :tags (jdbc-sql/where {:name "Clojure"} ))))
 (newline))

(defn v1 []
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "v1")
  (newline)

  (spy :msg "drop"
    (jdbc/execute! pg-spec [(format "drop table if exists %s cascade"
      (kw->dbstr :compute-langs)) ] ))

  (newline)
  (println "create")
  (jdbc/db-do-commands pg-spec
    (ddl/create-table
      (kw->dbstr :compute-langs)
      [:id        :serial "primary key"]
      [:name      :text   "not null"] ))

  (newline)
  (spy :msg "insert"
    (jdbc/execute! pg-spec
      (spy :msg "sql"
        (honey/format
          (-> (insert-into :compute-langs)
              (values [
                {:name "Clojure"}
                {:name "Java"} ] ))))))

  (newline)
  (spy :msg "query all"
    (jdbc/query pg-spec
      (spy :msg "sql"
        (honey/format
          { :select [:*]  :from [:compute-langs] } ))))

  (newline)
  (spy :msg "query"
    (jdbc/query pg-spec
      (spy :msg "sql"
        (honey/format
          { :select [:*]  :from [:compute-langs]  :where [:= :name "Clojure"] } ))))

  (newline)
  (spy :msg "query helper"
    (jdbc/query pg-spec
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
    odb-spec { :classname    "oracle.jdbc.OracleDriver"  ; must be in classpath
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
      (jdbc/execute! odb-spec [
        "begin
           pkg_rls.set_context ('admin', '1','ARGUS_MART', '#$!AgSeRvIcE@SaFeTy');
         end; " ] ))
    (spyx (jdbc/query odb-spec [ "select sysdate as current_day from dual" ] ))
    (spyx (jdbc/query odb-spec [ "select BANNER from SYS.V_$VERSION" ] ))

    (newline)
    (println "Trying count(*)...")
    (spyx (jdbc/query odb-spec [ "select * from rm_case_master" ] ))

    (newline)
    (println "using conn")
    (jdbc/with-db-connection [db-conn odb-spec]
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
    (jdbc/with-db-connection [db-conn odb-spec]
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
    odb-spec { :classname    "oracle.jdbc.OracleDriver"  ; must be in classpath
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
      (jdbc/execute! odb-spec [
        "begin
           pkg_rls.set_context ('admin', '1','ARGUS_MART', '#$!AgSeRvIcE@SaFeTy');
         end; " ] ))
    (spyx (jdbc/query odb-spec [ "select sysdate as current_day from dual" ] ))
    (spyx (jdbc/query odb-spec [ "select BANNER from SYS.V_$VERSION" ] ))

    (newline)
    (println "using conn")
    (jdbc/with-db-connection [db-conn odb-spec]
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
    (jdbc/with-db-connection [db-conn odb-spec]
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

(defn result-set->pg-insert-0 [result-set]
  (doseq [it (take 3 result-set)]
    (newline)
    (println "-----------------------------------------------------------------------------")
    (println "result-set->pg-insert: insert:")
    (newline)
    (println "received:")
    (prn (into (sorted-map) it))

    (newline)
    (spy :msg "insert result" (jdbc/insert! pg-spec "rm_case_master" it ))
  ))

(defn result-set->pg-insert [result-set]
  (println "insert result-set 1000") 
  (doseq [it (partition-all result-set)]
    (time
      (apply jdbc/insert! pg-spec "rm_case_master" it )))
)

(def row-count (atom 0))
(defn result-row->pg-insert [pg-conn result-row]
  (let [new-val (swap! row-count inc) ]
    (when (zero? (rem new-val 10))
      (print (format "%7d" new-val))
      (flush))
    (when (zero? (rem new-val 100))
      (newline)))
  (jdbc/insert! pg-conn "rm_case_master" result-row ))

(defn tx1 []
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "tx1")
  (pg-demo.rm-case-master/drop-create)

  (let [
    pg-spec     { :classname    "org.postgresql.Driver"
                  :subprotocol  "postgresql"
                  :subname      "//localhost:5432/ubuntu" }
    odb-spec    { :classname    "oracle.jdbc.OracleDriver"  ; must be in classpath
                  :subprotocol  "oracle"
                  :subname      "thin:@//10.100.6.231:1521/pvram"
                  :user         "mart_user"
                  :password     "rxlogix" }
  ]
    ; Stupid Oracle DB will often crash without this set
    ; Stupid Oracle DB gives bogus timezone offsets values
    (let [timeZone  (TimeZone/getTimeZone "UTC") ]      ; or "America/Los_Angeles"
      (TimeZone/setDefault timeZone))
    (newline)
    (spyx (jdbc/query odb-spec [ "select BANNER from SYS.V_$VERSION" ] ))
    (spyx (jdbc/query odb-spec [ "select sysdate as current_day from dual" ] ))

    (newline)
    (jdbc/with-db-connection [pg-conn pg-spec]
      (jdbc/with-db-connection [db-conn odb-spec]
        (spy :msg "  1st stmt"
          (jdbc/execute! db-conn [
            "begin
               pkg_rls.set_context ('admin', '1','ARGUS_MART', '#$!AgSeRvIcE@SaFeTy');
             end; " ] ))
        (spyx
          (jdbc/query db-conn [ "select count(*) as result from rm_case_master" ] ))

        (jdbc/query db-conn
          [ "select * from rm_case_master" ]
          :result-set-fn  result-set->pg-insert
;         :row-fn         #(result-row->pg-insert pg-conn %)
        )))

    (newline)
    (newline)
    (newline)
    (println "-----------------------------------------------------------------------------")
    (println "query")
    (newline)
    (doseq [it (take 2  (jdbc/query pg-spec
                          (jdbc-sql/select * "rm_case_master" )))
    ]
      (newline)
      (prn (into (sorted-map) it)))
    (newline)
  ))

(defn -main []
; (v0)
; (v1)
; (ora1)
; (ora2)
  (tx1)
)

