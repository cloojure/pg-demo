(ns pg-demo.rm-case-master-spec
  (:require [clojure.java.jdbc      :as jdbc]
            [java-jdbc.ddl          :as ddl]
            [java-jdbc.sql          :as jdbc-sql]
            [honeysql.core          :as honey]
            [honeysql.helpers       :refer :all]
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

(def table-name "rm_case_master" )

(def creation-sql "
  CREATE TABLE rm_case_master (
          ENTERPRISE_ID numeric NOT NULL,
          CASE_ID numeric NOT NULL,
          CASE_NUM varchar(20),
          REV numeric,
          WORKFLOW_SEQ_NUM numeric,
          LAST_WORKFLOW_SEQ_NUM numeric,
          CREATE_TIME timestamp,
          INIT_REPT_DATE timestamp,
          USER_ID numeric,
          LAST_UPDATE_TIME timestamp,
          LAST_UPDATE_USER_ID numeric,
          REQUIRES_FOLLOWUP numeric,
          FOLLOWUP_DATE timestamp,
          OWNER_ID numeric,
          STATE_ID numeric,
          COUNTRY_ID numeric,
          LANG_ID numeric,
          PRIORITY numeric,
          SITE_ID numeric,
          SERIOUSNESS numeric,
          RPT_TYPE_ID numeric,
          LAST_STATE_ID numeric,
          ASSESSMENT_NEEDED numeric,
          PRIORITY_OVERRIDE numeric(1),
          SID varchar(128),
          SAFETY_DATE timestamp,
          NORMAL_TIME timestamp,
          MAX_TIME timestamp,
          REPORT_SCHEDULING numeric,
          PRIORITY_ASSESSMENT numeric,
          CLOSE_USER_ID numeric,
          CLOSE_DATE timestamp,
          CLOSE_NOTES varchar(200),
          DATE_LOCKED timestamp,
          UD_TEXT_1 varchar(100),
          UD_TEXT_2 varchar(100),
          UD_TEXT_3 varchar(100),
          UD_TEXT_4 varchar(100),
          UD_TEXT_5 varchar(100),
          UD_TEXT_6 varchar(100),
          UD_TEXT_7 varchar(100),
          UD_TEXT_8 varchar(100),
          UD_TEXT_9 varchar(100),
          UD_TEXT_10 varchar(100),
          UD_TEXT_11 varchar(100),
          UD_TEXT_12 varchar(100),
          UD_DATE_1 timestamp,
          UD_DATE_2 timestamp,
          UD_DATE_3 timestamp,
          UD_DATE_4 timestamp,
          UD_DATE_5 timestamp,
          UD_DATE_6 timestamp,
          UD_DATE_7 timestamp,
          UD_DATE_8 timestamp,
          UD_DATE_9 timestamp,
          UD_DATE_10 timestamp,
          UD_DATE_11 timestamp,
          UD_DATE_12 timestamp,
          UD_NUMBER_1 numeric(30,10),
          UD_NUMBER_2 numeric(30,10),
          UD_NUMBER_3 numeric(30,10),
          UD_NUMBER_4 numeric(30,10),
          UD_NUMBER_5 numeric(30,10),
          UD_NUMBER_6 numeric(30,10),
          UD_NUMBER_7 numeric(30,10),
          UD_NUMBER_8 numeric(30,10),
          UD_NUMBER_9 numeric(30,10),
          UD_NUMBER_10 numeric(30,10),
          UD_NUMBER_11 numeric(30,10),
          UD_NUMBER_12 numeric(30,10),
          DELETED timestamp,
          DUE_SOON timestamp,
          GLOBAL_NUM varchar(20),
          PRIORITY_DATE_ASSESSED timestamp,
          LAM_ASSESS_DONE numeric(1),
          E2B_WW_NUMBER varchar(100),
          WORKLIST_OWNER_ID numeric,
          SUSAR numeric,
          LAST_UPDATE_EVENT timestamp,
          INITIAL_JUSTIFICATION varchar(1000),
          FORCE_SOON timestamp,
          DUE_SOON_J timestamp,
          FOLLOWUP_DATE_J timestamp,
          INIT_REPT_DATE_J timestamp,
          JUST_INIT_REPT_DATE_J varchar(1000),
          UD_TEXT_1_J varchar(100),
          UD_TEXT_2_J varchar(100),
          UD_TEXT_3_J varchar(100),
          UD_TEXT_4_J varchar(100),
          UD_TEXT_5_J varchar(100),
          UD_TEXT_6_J varchar(100),
          UD_TEXT_7_J varchar(100),
          UD_TEXT_8_J varchar(100),
          UD_TEXT_9_J varchar(100),
          UD_TEXT_10_J varchar(100),
          UD_TEXT_11_J varchar(100),
          UD_TEXT_12_J varchar(100),
          INITIAL_JUSTIFICATION_J varchar(1000),
          DLP_REVISION_NUMBER numeric NOT NULL,
          REVISION_DELETE_FLAG numeric,
          DELETED_FLAG numeric(1) NOT NULL,
          EFFECTIVE_START_DATE timestamp NOT NULL,
          EFFECTIVE_END_DATE timestamp NOT NULL,
          PRIMARY KEY (ENTERPRISE_ID,CASE_ID,DLP_REVISION_NUMBER)
  ); " )

(defn v0 []
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "rm-case-master-spec")

  (spy :msg "drop table"
    (jdbc/execute! db-spec [ (format "drop table if exists %s cascade" table-name) ] ))

  (spy :msg "create table"
    (jdbc/db-do-commands db-spec 
      (ddl/create-table
        table-name
        [:id        :serial "primary key"]
        [:name      :text   "not null"] )))

  (spy :msg "insert"
    (jdbc/insert! db-spec table-name
      {:name "Clojure"}
      {:name "Java"} ))

  (spy :msg "query"
    (jdbc/query db-spec
      (jdbc-sql/select * table-name (jdbc-sql/where {:name "Clojure"} ))))
 (newline))


(defn -main []
  (v0)
)

