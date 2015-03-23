(ns pg-demo.rm-case-master-0
  (:require [clojure.java.jdbc      :as jdbc]
            [java-jdbc.ddl          :as ddl]
            [java-jdbc.sql          :as jdbc-sql]
            [honeysql.core          :as honey]
            [honeysql.helpers       :refer :all]
  )
  (:use cooljure.core cooljure.misc)
  (:gen-class))

(def table-name "rm_case_master" )

(def creation-sql-str "
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

(def insert2-str
  "
  Insert into RM_CASE_MASTER
     (ENTERPRISE_ID, CASE_ID, CASE_NUM, REV, WORKFLOW_SEQ_NUM,
      LAST_WORKFLOW_SEQ_NUM, CREATE_TIME, INIT_REPT_DATE, USER_ID, LAST_UPDATE_TIME,
      LAST_UPDATE_USER_ID, REQUIRES_FOLLOWUP, FOLLOWUP_DATE, OWNER_ID, STATE_ID,
      COUNTRY_ID, LANG_ID, PRIORITY, SITE_ID, SERIOUSNESS,
      RPT_TYPE_ID, LAST_STATE_ID, ASSESSMENT_NEEDED, PRIORITY_OVERRIDE, SID,
      SAFETY_DATE, NORMAL_TIME, MAX_TIME, REPORT_SCHEDULING, PRIORITY_ASSESSMENT,
      CLOSE_USER_ID, CLOSE_DATE, CLOSE_NOTES, DATE_LOCKED, UD_TEXT_1,
      UD_TEXT_2, UD_TEXT_3, UD_TEXT_4, UD_TEXT_5, UD_TEXT_6,
      UD_TEXT_7, UD_TEXT_8, UD_TEXT_9, UD_TEXT_10, UD_TEXT_11,
      UD_TEXT_12, UD_DATE_1, UD_DATE_2, UD_DATE_3, UD_DATE_4,
      UD_DATE_5, UD_DATE_6, UD_DATE_7, UD_DATE_8, UD_DATE_9,
      UD_DATE_10, UD_DATE_11, UD_DATE_12, UD_NUMBER_1, UD_NUMBER_2,
      UD_NUMBER_3, UD_NUMBER_4, UD_NUMBER_5, UD_NUMBER_6, UD_NUMBER_7,
      UD_NUMBER_8, UD_NUMBER_9, UD_NUMBER_10, UD_NUMBER_11, UD_NUMBER_12,
      DELETED, DUE_SOON, GLOBAL_NUM, PRIORITY_DATE_ASSESSED, LAM_ASSESS_DONE,
      E2B_WW_NUMBER, WORKLIST_OWNER_ID, SUSAR, LAST_UPDATE_EVENT, INITIAL_JUSTIFICATION,
      FORCE_SOON, DUE_SOON_J, FOLLOWUP_DATE_J, INIT_REPT_DATE_J, JUST_INIT_REPT_DATE_J,
      UD_TEXT_1_J, UD_TEXT_2_J, UD_TEXT_3_J, UD_TEXT_4_J, UD_TEXT_5_J,
      UD_TEXT_6_J, UD_TEXT_7_J, UD_TEXT_8_J, UD_TEXT_9_J, UD_TEXT_10_J,
      UD_TEXT_11_J, UD_TEXT_12_J, INITIAL_JUSTIFICATION_J, DLP_REVISION_NUMBER, REVISION_DELETE_FLAG,
      DELETED_FLAG, EFFECTIVE_START_DATE, EFFECTIVE_END_DATE)
   Values
     (1, 100022, '13US000015', 0, NULL,
      NULL, TO_DATE('10/29/2013 01:34:21', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('10/28/2013 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 100009, TO_DATE('10/29/2013 01:46:53', 'MM/DD/YYYY HH24:MI:SS'),
      100009, 0, NULL, 0, 3,
      223, NULL, NULL, 100000, 1,
      3, 4, 0, NULL, 'RXARGUS7',
      NULL, NULL, NULL, NULL, 1,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, 0,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, 198, 0,
      0, TO_DATE('10/29/2013 01:46:53', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('10/29/2013 20:38:47', 'MM/DD/YYYY HH24:MI:SS'));
  Insert into RM_CASE_MASTER
     (ENTERPRISE_ID, CASE_ID, CASE_NUM, REV, WORKFLOW_SEQ_NUM,
      LAST_WORKFLOW_SEQ_NUM, CREATE_TIME, INIT_REPT_DATE, USER_ID, LAST_UPDATE_TIME,
      LAST_UPDATE_USER_ID, REQUIRES_FOLLOWUP, FOLLOWUP_DATE, OWNER_ID, STATE_ID,
      COUNTRY_ID, LANG_ID, PRIORITY, SITE_ID, SERIOUSNESS,
      RPT_TYPE_ID, LAST_STATE_ID, ASSESSMENT_NEEDED, PRIORITY_OVERRIDE, SID,
      SAFETY_DATE, NORMAL_TIME, MAX_TIME, REPORT_SCHEDULING, PRIORITY_ASSESSMENT,
      CLOSE_USER_ID, CLOSE_DATE, CLOSE_NOTES, DATE_LOCKED, UD_TEXT_1,
      UD_TEXT_2, UD_TEXT_3, UD_TEXT_4, UD_TEXT_5, UD_TEXT_6,
      UD_TEXT_7, UD_TEXT_8, UD_TEXT_9, UD_TEXT_10, UD_TEXT_11,
      UD_TEXT_12, UD_DATE_1, UD_DATE_2, UD_DATE_3, UD_DATE_4,
      UD_DATE_5, UD_DATE_6, UD_DATE_7, UD_DATE_8, UD_DATE_9,
      UD_DATE_10, UD_DATE_11, UD_DATE_12, UD_NUMBER_1, UD_NUMBER_2,
      UD_NUMBER_3, UD_NUMBER_4, UD_NUMBER_5, UD_NUMBER_6, UD_NUMBER_7,
      UD_NUMBER_8, UD_NUMBER_9, UD_NUMBER_10, UD_NUMBER_11, UD_NUMBER_12,
      DELETED, DUE_SOON, GLOBAL_NUM, PRIORITY_DATE_ASSESSED, LAM_ASSESS_DONE,
      E2B_WW_NUMBER, WORKLIST_OWNER_ID, SUSAR, LAST_UPDATE_EVENT, INITIAL_JUSTIFICATION,
      FORCE_SOON, DUE_SOON_J, FOLLOWUP_DATE_J, INIT_REPT_DATE_J, JUST_INIT_REPT_DATE_J,
      UD_TEXT_1_J, UD_TEXT_2_J, UD_TEXT_3_J, UD_TEXT_4_J, UD_TEXT_5_J,
      UD_TEXT_6_J, UD_TEXT_7_J, UD_TEXT_8_J, UD_TEXT_9_J, UD_TEXT_10_J,
      UD_TEXT_11_J, UD_TEXT_12_J, INITIAL_JUSTIFICATION_J, DLP_REVISION_NUMBER, REVISION_DELETE_FLAG,
      DELETED_FLAG, EFFECTIVE_START_DATE, EFFECTIVE_END_DATE)
   Values
     (1, 100022, '13US000015', 0, NULL,
      NULL, TO_DATE('10/29/2013 01:34:21', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('10/28/2013 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 100009, TO_DATE('10/29/2013 20:38:47', 'MM/DD/YYYY HH24:MI:SS'),
      100009, 0, NULL, 0, 3,
      223, NULL, NULL, 100000, 1,
      3, 4, 0, NULL, 'RXARGUS7',
      NULL, NULL, NULL, NULL, 1,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, 0,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, NULL, NULL,
      NULL, NULL, NULL, 207, 0,
      0, TO_DATE('10/29/2013 20:38:47', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('11/13/2013 22:57:12', 'MM/DD/YYYY HH24:MI:SS'));
  ")

(defn drop-create [db-spec]
  (newline)
  (println (format "drop   %s" table-name))
  (jdbc/execute! db-spec [ (format "drop table if exists %s cascade" table-name) ] )
  (println (format "create %s" table-name))
  (jdbc/execute! db-spec [creation-sql-str] )
)

(defn test0 []
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "rm-case-master-spec")
  (let [db-spec   { :classname "org.postgresql.Driver"
                    :subprotocol "postgresql"
                    :subname "//localhost:5432/ubuntu"
                  } 
  ]
    (spy :msg "drop table"
      (jdbc/execute! db-spec [ (format "drop table if exists %s cascade" table-name) ] ))

    (spy :msg "create2"
      (jdbc/execute! db-spec [creation-sql-str] ))

    (spy :msg "insert2"
      (jdbc/execute! db-spec [insert2-str] ))

    (spy :msg "query"
      (jdbc/query db-spec
        (jdbc-sql/select * table-name  )))
    (newline)))

(defn -main []
  (drop-create)
  (test0)
)

