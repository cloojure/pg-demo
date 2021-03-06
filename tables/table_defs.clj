(ns pg-demo.table-defs
  (:require [clojure.java.jdbc      :as jdbc]
            [java-jdbc.ddl          :as ddl]
            [java-jdbc.sql          :as jdbc-sql]
  )
  (:gen-class))

; A map from table name (string) -> table creation sql (string)
(def table-name->creation-sql 
  (into (sorted-map) [

    { "rm_lm_study_cohorts"
          "CREATE TABLE rm_lm_study_cohorts (
              ENTERPRISE_ID numeric NOT NULL,
              COHORT_ID numeric NOT NULL,
              STUDY_KEY numeric NOT NULL,
              BLIND_NAME varchar(70),
              BLIND_NAME_J varchar(70),
              STUDY_TYPE_ID numeric NOT NULL,
              SORT_ID numeric NOT NULL,
              primary_val numeric NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,COHORT_ID)
          ); " }

    { "rm_case_master"
          "CREATE TABLE rm_case_master (
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
          );" }

    { "rm_case_actions"
          "CREATE TABLE rm_case_actions (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              USER_ID numeric,
              DATE_OPEN timestamp,
              DATE_DUE timestamp,
              DATE_DONE timestamp,
              DESCRIPTION varchar(1000),
              SORT_ID numeric,
              CODE numeric,
              GROUP_ID numeric,
              SYSTEM_CREATED numeric,
              ACTION_STATUS numeric,
              DESCRIPTION_J varchar(1000),
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              SENT_IN_LTR numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          );" }

    { "rm_case_afssaps"
          "CREATE TABLE rm_case_afssaps (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              PROCEED_WO_CHANGE numeric(1),
              PROCEED_W_CHANGE numeric(1),
              TERM_STUDY_PROTOCOL numeric(1),
              TERM_PRODUCT_DEV numeric(1),
              OTHER numeric(1),
              OTHER_DESC varchar(50),
              FUTURE_ACTIONS text,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              DELETED_FLAG numeric(1) NOT NULL,
              REVISION_DELETE_FLAG numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          );" }

    { "rm_case_all_locked_rev"
          "CREATE TABLE rm_case_all_locked_rev (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              DLP_REVISION_NUMBER numeric NOT NULL,
              DATE_LOCKED timestamp,
              USER_LOCKED numeric(1),
              CASE_DELETED numeric(1),
              CLOSE_DATE timestamp,
              VALIDSTART timestamp,
              VALIDEND timestamp,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,DLP_REVISION_NUMBER)
          );" }

    { "rm_case_assess"
          "CREATE TABLE rm_case_assess (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              TEMPLATE_ID numeric,
              LISTEDNESS numeric,
              UPDATED timestamp,
              OUTCOME numeric,
              COMMENT_ID numeric,
              COMP_COMMENT_ID numeric,
              SERIOUSNESS numeric,
              SERIOUS_NOTES varchar(1000),
              AGENT_SUSPECT numeric,
              AGENT_NOTES varchar(1000),
              LISTEDNESS_NOTES varchar(1000),
              BFARM_CAUSALITY numeric(10),
              BFARM_MANUAL_TEXT text,
              COMPANY_DIAGNOSIS varchar(250),
              COMPANY_DIAGNOSIS_NOTES varchar(1000),
              DIAGNOSIS_DICT_ID numeric,
              DIAGNOSIS_PREF_CODE varchar(30),
              DIAGNOSIS_INC_CODE varchar(30),
              DIAGNOSIS_INC_TERM varchar(250),
              DIAGNOSIS_HLGT_CODE varchar(30),
              DIAGNOSIS_HLGT varchar(250),
              DIAGNOSIS_HLT_CODE varchar(30),
              DIAGNOSIS_HLT varchar(250),
              DIAGNOSIS_SOC_CODE varchar(30),
              DIAGNOSIS_BODY_SYS varchar(250),
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
              EVALUATION text,
              EVALUATION_J text,
              CO_SUSPECT_COUNT numeric(3),
              EVENT_SYNOPSIS varchar(5),
              EVENT_PRIMARY varchar(400),
              DIAGNOSIS_REPTD varchar(255),
              DIAGNOSIS_CODED varchar(255),
              DIAGNOSIS_SYN_CODE numeric,
              DIAGNOSIS_CODE_STATUS numeric(3),
              EVENT_PRIMARY_J varchar(400),
              AGENT_NOTES_J varchar(1000),
              COMPANY_DIAGNOSIS_J varchar(250),
              COMPANY_DIAGNOSIS_NOTES_J varchar(1000),
              DIAGNOSIS_BODY_SYS_J varchar(250),
              DIAGNOSIS_CODED_J varchar(255),
              DIAGNOSIS_CODE_STATUS_J numeric,
              DIAGNOSIS_HLGT_J varchar(250),
              DIAGNOSIS_HLT_J varchar(250),
              DIAGNOSIS_INC_CODE_J varchar(30),
              DIAGNOSIS_INC_TERM_J varchar(250),
              DIAGNOSIS_REPTD_J varchar(255),
              LISTEDNESS_NOTES_J varchar(1000),
              SERIOUS_NOTES_J varchar(1000),
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
              DIAGNOSIS_SYN_CODE_J numeric,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_bfarm_data"
          "CREATE TABLE rm_case_bfarm_data (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              MED_STATUS_ID numeric NOT NULL,
              MED_STATUS numeric,
              OTHER_TEXT varchar(20),
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              DELETED_FLAG numeric(1) NOT NULL,
              REVISION_DELETE_FLAG numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,MED_STATUS_ID,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_classifications"
          "CREATE TABLE rm_case_classifications (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              CLASSIFICATION_ID numeric,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_comments"
          "CREATE TABLE rm_case_comments (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              COMMENT_TXT text,
              COMMENT_TXT_J text,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_company_cmts"
          "CREATE TABLE rm_case_company_cmts (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              DELETED timestamp,
              COMMENT_TXT text,
              COMMENT_TXT_J text,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_contact_log"
          "CREATE TABLE rm_case_contact_log (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              CONTACT_DATE timestamp,
              CONTACT_TYPE numeric,
              DESCRIPTION varchar(1000),
              USER_ID numeric,
              DATE_SENT timestamp,
              SORT_ID numeric,
              CODE numeric,
              GROUP_ID numeric,
              LAM_ID numeric(10),
              LAM_CONTACT_LOG_ID numeric(10),
              DESCRIPTION_J varchar(1000),
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              DELETED_FLAG numeric(1) NOT NULL,
              REVISION_DELETE_FLAG numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          );
          " }

    { "rm_case_death"
          "CREATE TABLE rm_case_death (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              DEATH_DATE timestamp,
              AUTOPSY numeric,
              DEATH_DATE_RES numeric,
              DEATH_DATE_PARTIAL varchar(20),
              DETERMINE_AUTOPSY varchar(250),
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_death_details"
          "CREATE TABLE rm_case_death_details (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              SORT_ID numeric,
              TERM_TYPE numeric,
              CAUSE_REPTD varchar(1000),
              CAUSE_CODED varchar(1000),
              CAUSE varchar(1000),
              CAUSE_CODE varchar(50),
              CAUSE_LLT varchar(250),
              CAUSE_LLT_CODE varchar(50),
              CAUSE_HLT varchar(250),
              CAUSE_HLT_CODE varchar(50),
              CAUSE_HLGT varchar(250),
              CAUSE_HLGT_CODE varchar(50),
              CAUSE_SOC varchar(250),
              CAUSE_SOC_CODE varchar(50),
              CAUSE_SYN_CODE numeric,
              CAUSE_DICT numeric,
              CAUSE_CODE_STATUS numeric(3),
              CAUSE_CODED_J varchar(1000),
              CAUSE_SOC_J varchar(250),
              CAUSE_CODE_STATUS_J numeric,
              CAUSE_HLGT_J varchar(250),
              CAUSE_HLT_J varchar(250),
              CAUSE_J varchar(1000),
              CAUSE_LLT_CODE_J varchar(50),
              CAUSE_LLT_J varchar(250),
              CAUSE_REPTD_J varchar(1000),
              CAUSE_SYN_CODE_J numeric,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_device_prod_deliv"
          "CREATE TABLE rm_case_device_prod_deliv (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              PROD_SEQ_NUM numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              PROD_DELIV_SEQ numeric,
              DELETED timestamp,
              TYPE numeric,
              OTHER_MFG_PROD varchar(100),
              OTHER_MFG_PROD_J varchar(100),
              DLP_REVISION_NUMBER numeric NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              DELETED_FLAG numeric(1) NOT NULL,
              REVISION_DELETE_FLAG numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,PROD_SEQ_NUM,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_dose_regimens"
          "CREATE TABLE rm_case_dose_regimens (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              LOG_NO numeric NOT NULL,
              START_DATETIME timestamp,
              START_DATETIME_RES numeric,
              STOP_DATETIME timestamp,
              STOP_DATETIME_RES numeric,
              ONGOING numeric,
              OFF_LABEL numeric,
              DOSE numeric(22,7),
              DOSE_UNIT_ID numeric,
              FREQ_ID numeric,
              ADMIN_ROUTE_ID numeric,
              DAILY_DOSE numeric(22,7),
              DAILY_DOSE_UNIT_ID numeric,
              EXP_DATE timestamp,
              EXP_DATE_RES numeric,
              TOTAL_REG_DOSE numeric(22,7),
              TOT_REG_DOSE_UNIT_ID numeric,
              SORT_ID numeric,
              PARENT_SEQ_NUM numeric,
              LOCATION_ID numeric,
              DOSE_NO numeric,
              PAR_ADMIN_ROUTE numeric,
              EXP_DATE_PARTIAL varchar(20),
              START_DATE_PARTIAL varchar(20),
              STOP_DATE_PARTIAL varchar(20),
              DOSE_DESCRIPTION varchar(100),
              PACKAGE_ID varchar(20),
              LOT_NO varchar(35),
              PACK_UNITS numeric,
              ACCIDENTAL_EXPOSURE numeric,
              DELETED timestamp,
              LOT_ID numeric,
              LOT_JUST varchar(1000),
              LOT_STATUS numeric(1),
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
              DURATION_SECONDS numeric,
              DOSE_DESCRIPTION_J varchar(100),
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
              LOT_JUST_J varchar(1000),
              DLP_REVISION_NUMBER numeric NOT NULL,
              LAST_UPDATE_TIME timestamp,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              VAERS_BLOCK_10 numeric,
              VAERS_BLOCK_14 numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,LOG_NO,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_eu_device"
          "CREATE TABLE rm_case_eu_device (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              ACCESSORIES varchar(90),
              VERSION varchar(40),
              IDENTIFICATION varchar(90),
              PREVIOUSLY_REPORTED numeric,
              INCIDENT numeric,
              COUNTRY varchar(1000),
              EXPECTED_DATE timestamp,
              EXPECTED_DATE_RES numeric,
              EXPECTED_DATE_PARTIAL varchar(20),
              PROJECTED_TIMING text,
              PROJECTED_TIMING_FINAL text,
              CORRECTIVE_ACTION_FINAL text,
              INVESTIGATION_RESULT text,
              FURTHER_INVESTIGATION text,
              CURRENT_DEV_LOCATIONS text,
              COUNTRIES_OF_DISTRIBUTION text,
              DELETED timestamp,
              CORRECTIVE_ACTION text,
              DEVICE_PURCHASED varchar(120),
              ADDRESS varchar(120),
              DEVICE_STERILE numeric(1),
              IMPORTER_NAME varchar(100),
              IMPORTER_ADDRESS varchar(120),
              IMPORTER_POSTCODE varchar(15),
              IMPORTER_PHONE varchar(20),
              IMPORTER_FAX varchar(50),
              SEQ_NUM numeric NOT NULL,
              PROD_SEQ_NUM numeric,
              NCA_REF_NUM varchar(100),
              INDENTIF text,
              PATS_INVOLVED numeric,
              DEVS_INVOLVED numeric,
              USER_FAC_REF varchar(20),
              REMEDIAL_ACTION varchar(1000),
              DEV_USAGE numeric,
              OTHER varchar(15),
              UPDATE_TO_REPORT numeric(1),
              FINAL_REPORT numeric(1),
              PUBLIC_THREAT numeric,
              SIMILAR_INCIDENT_NUM numeric(3),
              MANUFACTURER_COMMENTS text,
              DLP_REVISION_NUMBER numeric NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              DELETED_FLAG numeric(1) NOT NULL,
              REVISION_DELETE_FLAG numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_event"
          "CREATE TABLE rm_case_event (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              DESC_REPTD varchar(250),
              DESC_CODED varchar(255),
              DIAGNOSIS numeric,
              ONSET timestamp,
              ONSET_RES numeric,
              ONSET_DELAY varchar(40),
              STOP_DATE timestamp,
              STOP_DATE_RES numeric,
              DURATION varchar(40),
              ONSET_LATENCY varchar(40),
              EVT_INTENSITY_ID numeric,
              EVT_FREQ_ID numeric,
              TREATED numeric,
              STUDY_RELATED numeric,
              RECHALL_RELATED numeric,
              PROD_SEQ_NUM numeric,
              EVT_OUTCOME_ID numeric,
              RECEIPT_DATE timestamp,
              RPT_SERIOUS numeric,
              SC_DEATH numeric,
              SC_HOSP numeric,
              SC_CONG_ANOM numeric,
              SC_THREAT numeric,
              SC_DISABLE numeric,
              SC_INT_REQ numeric,
              SC_OTHER numeric,
              SC_OTHER_TEXT varchar(30),
              ART_CODE varchar(30),
              PREF_TERM varchar(250),
              INC_TERM varchar(250),
              BODY_SYS varchar(250),
              MED_SERIOUS numeric,
              PAST_HIST numeric,
              DICT_ID numeric,
              DICT_KEY varchar(20),
              CODE_STATUS numeric,
              SERIOUSNESS numeric,
              SORT_ID numeric,
              ONSET_DATE_PARTIAL varchar(20),
              STOP_DATE_PARTIAL varchar(20),
              EFFICACY numeric,
              DISEASE numeric,
              WITHDRAWAL numeric,
              HLGT varchar(250),
              HLT varchar(250),
              HLT_CODE varchar(30),
              HLGT_CODE varchar(30),
              SOC_CODE varchar(30),
              STUDY_DROPPED numeric(4),
              INC_CODE varchar(30),
              ONSET_MINUTES numeric,
              DELAY_MINUTES numeric,
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
              DETAILS text,
              DETAILS_J text,
              SYN_CODE numeric,
              DELETED timestamp,
              STUDY_RELATED_REPTD numeric(1),
              DURATION_UNIT_E2B numeric,
              ONSET_LATENCY_UNIT_E2B numeric,
              ONSET_DELAY_UNIT_E2B numeric,
              DURATION_SECONDS numeric,
              ONSET_LATENCY_SECONDS numeric,
              ONSET_DELAY_SECONDS numeric,
              BODY_SYS_J varchar(250),
              CODE_STATUS_J numeric,
              DESC_CODED_J varchar(255),
              DESC_REPTD_J varchar(250),
              HLGT_J varchar(250),
              HLT_J varchar(250),
              INC_CODE_J varchar(30),
              INC_TERM_J varchar(250),
              PREF_TERM_J varchar(250),
              SC_OTHER_TEXT_J varchar(30),
              REPORT_EXCLUSION numeric,
              REPORTABLE numeric,
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
              SYN_CODE_J numeric,
              INFECTION numeric,
              RPT_EXCLUDE_CMT varchar(1000),
              RPT_EXCLUDE_CMT_J varchar(1000),
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_event_assess"
          "CREATE TABLE rm_case_event_assess (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              EVENT_SEQ_NUM numeric NOT NULL,
              PROD_SEQ_NUM numeric NOT NULL,
              DATASHEET_ID numeric NOT NULL,
              LICENSE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              UPDATED timestamp,
              DET_LISTEDNESS_ID numeric,
              RPT_CAUSALITY_ID numeric,
              DET_CAUSALITY_ID numeric,
              LISTED_JUSTIFY varchar(1000),
              CAUSE_JUSTIFY varchar(1000),
              LAM_ASSESSED numeric(12),
              DELETED timestamp,
              CAUSALITY_ASSESSMENT varchar(50),
              CAUSALITY_SCORE numeric,
              SOURCE_ID numeric,
              PRT_CAUSALITY_ID numeric,
              CAUSE_JUSTIFY_J varchar(1000),
              LISTED_JUSTIFY_J varchar(1000),
              DLP_REVISION_NUMBER numeric NOT NULL,
              LAST_UPDATE_TIME timestamp,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,EVENT_SEQ_NUM,PROD_SEQ_NUM,DATASHEET_ID,LICENSE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_event_consequence"
          "CREATE TABLE rm_case_event_consequence (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              ED_SEQ_NUM numeric NOT NULL,
              EVT_CONSEQUENCE_ID numeric NOT NULL,
              CONSEQUENCE varchar(100),
              TERM varchar(250),
              CONSEQUENCE_J varchar(100),
              TERM_J varchar(250),
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              DELETED_FLAG numeric(1) NOT NULL,
              REVISION_DELETE_FLAG numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_event_detail"
          "CREATE TABLE rm_case_event_detail (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              PROD_SEQ_NUM numeric NOT NULL,
              EVENT_SEQ_NUM numeric NOT NULL,
              ONSET_DELAY varchar(40),
              ONSET_DELAY_SECONDS numeric,
              ONSET_LATENCY varchar(40),
              ONSET_LATENCY_SECONDS numeric,
              TOTAL_DOSE numeric(22,7),
              TOTAL_DOSE_UNIT_ID numeric,
              MOST_IMPORTANT numeric,
              MORE_SPECIFIC numeric,
              ACT_TAKEN_ID numeric,
              OTHER_INFORMATION varchar(250),
              DECHALLENGE numeric,
              RECHALLENGE numeric,
              OTHER_INFORMATION_J varchar(250),
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_event_imputability"
          "CREATE TABLE rm_case_event_imputability (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              EVENT_SEQ_NUM numeric NOT NULL,
              AGENT_SEQ_NUM numeric NOT NULL,
              CHRONOLOGY varchar(4),
              SEMIOLOGY varchar(4),
              BIBLIOGRAPHY varchar(4),
              IMPUTABILITY varchar(4),
              DELETED timestamp,
              TIME_TO_ONSET numeric,
              READMINISTRATION numeric,
              DRUG_STOPPED numeric,
              SEMIOLOGY_OUTCOME numeric,
              COMPLEMENTARY_TEST numeric,
              OTHER_EXPLANATION numeric,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,EVENT_SEQ_NUM,AGENT_SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_event_nature"
          "CREATE TABLE rm_case_event_nature (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              EVENT_SEQ_NUM numeric NOT NULL,
              EVT_NATURE_ID numeric NOT NULL,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              DELETED_FLAG numeric(1) NOT NULL,
              REVISION_DELETE_FLAG numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_event_product_rptblty"
          "CREATE TABLE rm_case_event_product_rptblty (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              PROD_SEQ_NUM numeric NOT NULL,
              EVENT_SEQ_NUM numeric,
              REPORTABILITY numeric,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,PROD_SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_event_rech_prod"
          "CREATE TABLE rm_case_event_rech_prod (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              EVENT_SEQ_NUM numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              PRODUCT_SEQ_NUM numeric,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,EVENT_SEQ_NUM,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_followup"
          "CREATE TABLE rm_case_followup (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              RECEIPT_DATE timestamp,
              SAFETY_DATE timestamp,
              SIGNIFICANT numeric(1),
              AUDITLOG_TYPE numeric,
              TIME_STAMP timestamp,
              DELETED timestamp,
              SIGNIFICANT_DEVICE numeric(1),
              DATA_CLEANUP numeric(1),
              JUSTIFICATION varchar(1000),
              JUSTIFICATION_ID numeric,
              JUSTIFICATION_J varchar(1000),
              JUST_RECEIPT_DATE_J varchar(1000),
              RECEIPT_DATE_J timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_hosp"
          "CREATE TABLE rm_case_hosp (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              START_DATE timestamp,
              END_DATE timestamp,
              DURATION numeric,
              PROLONGED numeric,
              SUMMARY numeric,
              EVENT_CAUSED numeric,
              START_DATE_RES numeric,
              START_DATE_PARTIAL varchar(20),
              END_DATE_RES numeric,
              END_DATE_PARTIAL varchar(20),
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_justifications"
          "CREATE TABLE rm_case_justifications (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              FIELD_ID numeric NOT NULL,
              PRIMARY_SEQ_NUM numeric NOT NULL,
              ALT_SEQ_NUM numeric NOT NULL,
              J_TEXT varchar(1000),
              J_TEXT_J varchar(1000),
              UPDATED_TIME timestamp,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,FIELD_ID,PRIMARY_SEQ_NUM,ALT_SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_lab_data"
          "CREATE TABLE rm_case_lab_data (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              LAB_TEST_ID numeric,
              TEST_DATE timestamp,
              SORT_ID numeric,
              ASSESSMENT numeric,
              TEST_DATE_RES numeric(4),
              RESULTS varchar(50),
              NORM_HIGH varchar(50),
              NORM_LOW varchar(50),
              TEST_DATE_PARTIAL varchar(20),
              RESULT varchar(20),
              UNIT varchar(35),
              LAB_TEST_NAME varchar(250),
              DELETED timestamp,
              NOTES text,
              NOTES_J text,
              PT_CODE varchar(50),
              LLT_CODE varchar(50),
              HLT_CODE varchar(50),
              HLGT_CODE varchar(50),
              SOC_CODE varchar(50),
              SYN_CODE numeric,
              DICT_ID numeric,
              LLT varchar(250),
              HLT varchar(250),
              HLGT varchar(250),
              SOC varchar(250),
              CODE_STATUS numeric,
              TEST_REPTD varchar(250),
              UNIT_ID numeric,
              CODE_STATUS_J numeric,
              HLGT_J varchar(250),
              HLT_J varchar(250),
              LAB_TEST_NAME_J varchar(250),
              LLT_CODE_J varchar(50),
              LLT_J varchar(250),
              RESULTS_J varchar(50),
              RESULT_J varchar(20),
              SOC_J varchar(250),
              TEST_REPTD_J varchar(250),
              SYN_CODE_J numeric,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_language"
          "CREATE TABLE rm_case_language (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              FIELD_ID numeric NOT NULL,
              LANGUAGE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              DELETED timestamp,
              TEXT text,
              DLP_REVISION_NUMBER numeric NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              DELETED_FLAG numeric(1) NOT NULL,
              REVISION_DELETE_FLAG numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,FIELD_ID,LANGUAGE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_letters"
          "CREATE TABLE rm_case_letters (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              BLOB_SEQ numeric(10) NOT NULL,
              TEMPLATE_ID numeric,
              SORT_ID numeric,
              BLOBSIZE numeric,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              DELETED_FLAG numeric(1) NOT NULL,
              REVISION_DELETE_FLAG numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,BLOB_SEQ,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_literature"
          "CREATE TABLE rm_case_literature (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              SORT_ID numeric,
              LITERATURE_ID numeric,
              JOURNAL varchar(80),
              AUTHOR varchar(200),
              TITLE varchar(500),
              VOL varchar(15),
              YEAR varchar(4),
              PGS varchar(30),
              DELETED timestamp,
              AUTHOR_J varchar(200),
              JOURNAL_J varchar(80),
              TITLE_J varchar(500),
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_local_eva_comment"
          "CREATE TABLE rm_case_local_eva_comment (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              EVALUATOR_TYPE_ID numeric NOT NULL,
              LOCAL_COMMENT text,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,EVALUATOR_TYPE_ID,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_medwatch_data"
          "CREATE TABLE rm_case_medwatch_data (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              EVALUATION numeric,
              ADVERSE_EVENT numeric,
              PRODUCT_PROBLEM numeric,
              LOC_HOSP numeric,
              LOC_HOME numeric,
              LOC_NH numeric,
              LOC_OTF numeric,
              LOC_ODF numeric,
              LOC_ASF numeric,
              LOC_OTHER numeric,
              LOC_OTHER_TEXT varchar(15),
              FOLLOWUP_TYPE numeric,
              SINGLE_USE numeric,
              MFG_DATE timestamp,
              RETURN_DATE timestamp,
              METH_CD1 char(4),
              METH_CD2 char(4),
              METH_CD3 char(4),
              METH_CD4 char(4),
              RES_CD1 char(4),
              RES_CD2 char(4),
              RES_CD3 char(4),
              RES_CD4 char(4),
              CONC_CD1 char(4),
              CONC_CD2 char(4),
              CONC_CD3 char(4),
              CONC_CD4 char(4),
              FDA_PAT_CD1 char(4),
              FDA_PAT_CD2 char(4),
              FDA_PAT_CD3 char(4),
              FDA_DEV_CD1 char(4),
              FDA_DEV_CD2 char(4),
              FDA_DEV_CD3 char(4),
              DEV_USAGE numeric,
              DEV_OPER numeric,
              DEV_OPER_OTHER_TEXT varchar(20),
              NDC varchar(50),
              CATALOG varchar(20),
              OTHER varchar(30),
              FACILITY_DISTRIBUTOR numeric(1),
              UF_DIST_REPORT varchar(50),
              NAME_ADDRESS varchar(300),
              CONT_NAME varchar(40),
              CONT_PHONE varchar(20),
              AWARE_DATE timestamp,
              SENT_FDA numeric(1),
              SENT_FDA_DATE timestamp,
              SENT_MANUFACTURER numeric(1),
              SENT_MANU_DATE timestamp,
              REPORT_SOURCE numeric,
              RS_OTHER_TEXT varchar(30),
              PRE1938 numeric(1),
              OTC numeric(1),
              EVENT_TYPE numeric,
              EVENT_OTHER varchar(40),
              MFR_EVALUATION numeric,
              MFR_NO_CODE varchar(20),
              REMEDIAL_ACTION numeric,
              REMEDIAL_OTHER varchar(20),
              USC varchar(20),
              MANU_NARRATIVE numeric(1),
              MANU_CORRECTED numeric(1),
              PLA varchar(10),
              DELETED timestamp,
              NARRATIVE_TEXT text,
              DLP_REVISION_NUMBER numeric NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              DELETED_FLAG numeric(1) NOT NULL,
              REVISION_DELETE_FLAG numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_med_status"
          "CREATE TABLE rm_case_med_status (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              MED_STATUS_ID numeric NOT NULL,
              MED_STATUS numeric,
              OTHER_TEXT varchar(20),
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,MED_STATUS_ID,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_mhlw_notes"
          "CREATE TABLE rm_case_mhlw_notes (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              DELETED timestamp,
              REMARK1 text,
              REMARK2 text,
              REMARK3 text,
              REMARK4 text,
              DLP_REVISION_NUMBER numeric NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              DELETED_FLAG numeric(1) NOT NULL,
              REVISION_DELETE_FLAG numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_narrative"
          "CREATE TABLE rm_case_narrative (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              ABBREV_NARRATIVE text,
              ABBREV_NARRATIVE_J text,
              NARRATIVE text,
              NARRATIVE_J text,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_neonates"
          "CREATE TABLE rm_case_neonates (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              DELIVER_DATE timestamp,
              WEIGHT_GRAMS numeric(22,7),
              WEIGHT_OZS numeric(22,7),
              DELIVERY_TYPE_ID numeric,
              APGAR1 numeric,
              APGAR2 numeric,
              APGAR3 numeric,
              NOTES varchar(1000),
              SORT_ID numeric,
              DELETED timestamp,
              BIRTH_TYPE_ID numeric,
              FETAL_OUTCOME_ID numeric,
              PARENT numeric NOT NULL,
              NOTES_J varchar(1000),
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_notes_attach"
          "CREATE TABLE rm_case_notes_attach (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              BLOB_SEQ numeric(10) NOT NULL,
              CLASSIFICATION numeric,
              ENTRY_DATE timestamp,
              TYPE numeric,
              E2B_ADD_DOC numeric,
              BLOBSIZE numeric,
              SORT_ID numeric,
              FILETYPE varchar(300),
              KEYWORDS varchar(200),
              NOTES varchar(1000),
              LAM_ID numeric(10),
              LAM_NOTES_ATTACHMENT_ID numeric(10),
              DELETED timestamp,
              DOCUMENTUM_ID varchar(200),
              INCL_REG_SUB numeric,
              ESM_REPORT_ID numeric,
              KEYWORDS_J varchar(200),
              NOTES_J varchar(1000),
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,BLOB_SEQ,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_parent_info"
          "CREATE TABLE rm_case_parent_info (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              AGE_UNIT_ID numeric,
              DATE_OF_LMP timestamp,
              GENDER_ID numeric,
              AGE numeric(22,7),
              DOB timestamp,
              DOB_RES numeric(1),
              HEIGHT_CM numeric(22,7),
              INITIALS varchar(10),
              WEIGHT_KG numeric(22,7),
              DOB_PARTIAL varchar(20),
              WEIGHT_LBS numeric(22,7),
              HEIGHT_IN numeric(22,7),
              DELETED timestamp,
              BREASTFEEDING numeric,
              DATE_OF_LMP_RES numeric,
              DATE_OF_LMP_PARTIAL varchar(20),
              MED_HIST_TEXT text,
              MED_HIST_TEXT_J text,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_pat_hist"
          "CREATE TABLE rm_case_pat_hist (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              START_DATE timestamp,
              START_DATE_RES numeric,
              STOP_DATE timestamp,
              STOP_DATE_RES numeric,
              CONDITION_TYPE_ID numeric,
              SORT_ID numeric,
              CONTINUE numeric(1),
              PARENT numeric(1),
              ITEM_DICT numeric,
              ITEM_CODE varchar(45),
              START_DATE_PARTIAL varchar(20),
              STOP_DATE_PARTIAL varchar(20),
              CONDITION varchar(250),
              NOTE text,
              NOTE_J text,
              ITEM_LLT varchar(250),
              ITEM_HLT varchar(250),
              ITEM_HLGT varchar(250),
              ITEM_SOC varchar(250),
              ITEM_LLT_CODE varchar(50),
              ITEM_HLT_CODE varchar(50),
              ITEM_HLGT_CODE varchar(50),
              ITEM_SOC_CODE varchar(50),
              ITEM_REPTD varchar(255),
              ITEM_CODED varchar(255),
              ITEM_SYN_CODE numeric,
              ITEM_CODE_STATUS numeric(3),
              DELETED timestamp,
              INDICATION varchar(250),
              IND_REPTD varchar(255),
              IND_CODED varchar(255),
              IND_PT_CODE varchar(50),
              IND_LLT_CODE varchar(50),
              IND_LLT varchar(250),
              IND_HLT_CODE varchar(50),
              IND_HLT varchar(250),
              IND_HLGT_CODE varchar(50),
              IND_HLGT varchar(250),
              IND_SOC_CODE varchar(50),
              IND_SOC varchar(250),
              IND_SYN_CODE numeric,
              IND_DICT_ID numeric,
              IND_CODE_STATUS numeric(3),
              REACTION varchar(250),
              REACT_REPTD varchar(255),
              REACT_CODED varchar(255),
              REACT_PT_CODE varchar(50),
              REACT_LLT_CODE varchar(50),
              REACT_LLT varchar(250),
              REACT_HLT_CODE varchar(50),
              REACT_HLT varchar(250),
              REACT_HLGT_CODE varchar(50),
              REACT_HLGT varchar(250),
              REACT_SOC_CODE varchar(50),
              REACT_SOC varchar(250),
              REACT_SYN_CODE numeric,
              REACT_DICT_ID numeric,
              REACT_CODE_STATUS numeric(3),
              CONDITION_J varchar(250),
              INDICATION_J varchar(250),
              IND_CODED_J varchar(255),
              IND_CODE_STATUS_J numeric,
              IND_HLGT_J varchar(250),
              IND_HLT_J varchar(250),
              IND_LLT_CODE_J varchar(50),
              IND_LLT_J varchar(250),
              IND_REPTD_J varchar(255),
              IND_SOC_J varchar(250),
              ITEM_CODED_J varchar(255),
              ITEM_CODE_STATUS_J numeric,
              ITEM_HLGT_J varchar(250),
              ITEM_HLT_J varchar(250),
              ITEM_LLT_CODE_J varchar(50),
              ITEM_LLT_J varchar(250),
              ITEM_REPTD_J varchar(255),
              ITEM_SOC_J varchar(250),
              REACTION_J varchar(250),
              REACT_CODED_J varchar(255),
              REACT_CODE_STATUS_J numeric,
              REACT_HLGT_J varchar(250),
              REACT_HLT_J varchar(250),
              REACT_LLT_CODE_J varchar(50),
              REACT_LLT_J varchar(250),
              REACT_REPTD_J varchar(255),
              REACT_SOC_J varchar(250),
              IND_SYN_CODE_J numeric,
              ITEM_SYN_CODE_J numeric,
              REACT_SYN_CODE_J numeric,
              ITEM_DICT_J numeric,
              ITEM_CODE_J varchar(50),
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_pat_info"
          "CREATE TABLE rm_case_pat_info (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              PAT_ID varchar(10),
              PAT_SUBJ_NUM varchar(20),
              PAT_INITIALS varchar(10),
              PAT_FIRSTNAME varchar(35),
              PAT_MI varchar(15),
              PAT_LASTNAME varchar(50),
              PAT_ADDRESS varchar(120),
              PAT_CITY varchar(35),
              PAT_STATE varchar(40),
              PAT_POSTAL_CODE varchar(15),
              PAT_COUNTRY varchar(50),
              PAT_PHONE varchar(20),
              PAT_DOB timestamp,
              PAT_DOB_RES numeric,
              PAT_AGE numeric(22,7),
              AGE_UNIT_ID numeric,
              AGE_GROUP_ID numeric,
              GENDER_ID numeric,
              PAT_WEIGHT_LBS numeric(22,7),
              PAT_WEIGHT_KG numeric(22,7),
              PAT_HEIGHT_IN numeric(22,7),
              PAT_HEIGHT_CM numeric(22,7),
              ETHNICITY_ID numeric,
              OCCUPATION_ID numeric,
              PAT_STAT_PREG numeric,
              REL_TEST_ID numeric,
              PAT_DOB_PARTIAL varchar(20),
              RAND_NUM varchar(15),
              CONFIDENTIAL numeric(1),
              NUMBER_OF_PATIENTS varchar(8),
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
              CHILD_ONLY numeric(1),
              PAT_BMI numeric(22,7),
              PAT_BODY_AREA numeric(22,7),
              PAT_ADDRESS_J varchar(120),
              PAT_CITY_J varchar(35),
              PAT_FIRSTNAME_J varchar(35),
              PAT_LASTNAME_J varchar(50),
              PAT_MI_J varchar(15),
              PAT_STATE_J varchar(40),
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
              PAT_COUNTRY_ID numeric,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              NOTES text,
              NOTES_J text,
              AGE_UNIT_ID_AT_VACC numeric,
              PAT_AGE_AT_VACC numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_pat_tests"
          "CREATE TABLE rm_case_pat_tests (
              ENTERPRISE_ID numeric NOT NULL,
              REL_TEST_ID numeric NOT NULL,
              DELETED timestamp,
              REL_TESTS text,
              REL_TESTS_J text,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,REL_TEST_ID,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_pmda_event_assess"
          "CREATE TABLE rm_case_pmda_event_assess (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              EVENT_SEQ_NUM numeric NOT NULL,
              PRODUCT_ID numeric NOT NULL,
              PROD_SEQ_NUM numeric NOT NULL,
              RPT_CAUSALITY_ID numeric,
              DET_CAUSALITY_ID numeric,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              DELETED_FLAG numeric(1) NOT NULL,
              REVISION_DELETE_FLAG numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,EVENT_SEQ_NUM,PROD_SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_pmda_general"
          "CREATE TABLE rm_case_pmda_general (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              DELETED_FLAG numeric(1) NOT NULL,
              REVISION_DELETE_FLAG numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_pmda_license"
          "CREATE TABLE rm_case_pmda_license (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              LICENSE_ID numeric NOT NULL,
              PROD_SEQ_NUM numeric NOT NULL,
              LIC_CATEGORY_ID numeric,
              REPORT_TYPE numeric,
              RPT_CATEGORY_ID numeric,
              CASE_COMPLETE numeric,
              PMDA_NUMBER varchar(100),
              INCOMPLETE_COMMENT text,
              FUTURE_ACTIONS text,
              FORM_2_MEMO text,
              SENDER_COMMENT text,
              DOWNGRADED numeric,
              SERIOUS_DISEASE numeric,
              SIGNIFICANT_CHANGE numeric,
              EFFECTIVENESS numeric,
              PROBLEM_DESC text,
              SORT_ID numeric,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              DELETED_FLAG numeric(1) NOT NULL,
              REVISION_DELETE_FLAG numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_pmda_lic_studies"
          "CREATE TABLE rm_case_pmda_lic_studies (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              LICENSE_ID numeric NOT NULL,
              STUDIES text,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,LICENSE_ID,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_pmda_mgmt_num"
          "CREATE TABLE rm_case_pmda_mgmt_num (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              PROD_SEQ_NUM numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              RECEIVED_DATE timestamp,
              ACK_BRANCH_NUMBER varchar(8),
              REG_BRANCH_NUMBER varchar(8),
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,PROD_SEQ_NUM,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_pmda_notes"
          "CREATE TABLE rm_case_pmda_notes (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              REMARK1 text,
              REMARK2 text,
              REMARK3 text,
              REMARK4 text,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              DELETED_FLAG numeric(1) NOT NULL,
              REVISION_DELETE_FLAG numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_pmda_reportability"
          "CREATE TABLE rm_case_pmda_reportability (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              PROD_SEQ_NUM numeric NOT NULL,
              EVENT_SEQ_NUM numeric NOT NULL,
              LICENSE_ID numeric NOT NULL,
              LISTEDNESS numeric,
              REPORTABILITY numeric,
              TIMEFRAME numeric,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,PROD_SEQ_NUM,EVENT_SEQ_NUM,LICENSE_ID,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_pmda_rpt_version"
          "CREATE TABLE rm_case_pmda_rpt_version (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              REVISION numeric NOT NULL,
              LAST_UPDATE_TIME timestamp,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,REVISION,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_pregnancy"
          "CREATE TABLE rm_case_pregnancy (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              DATE_OF_LMP timestamp,
              WEEKS numeric,
              DUE_DATE timestamp,
              EXP_TRIMESTER numeric,
              GESTATION_EXP_PERIOD numeric,
              PARENT numeric(1) NOT NULL,
              DELETED timestamp,
              BREASTFEEDING numeric(1),
              PROSPECTIVE numeric(1),
              NUMBER_OF_FETUS numeric(2),
              DATE_OF_LMP_RES numeric,
              DATE_OF_LMP_PARTIAL varchar(20),
              GRAVIDA numeric,
              PARA numeric,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,PARENT,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_product"
          "CREATE TABLE rm_case_product (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              PRODUCT_ID numeric,
              DRUG_TYPE numeric,
              PAT_EXPOSURE numeric,
              MANUFACTURER_ID numeric,
              PROTOCOL_FOLLOWED numeric,
              FIRST_SUS_PROD numeric,
              SELECTED_VIEW numeric,
              SORT_ID numeric,
              VIEWS_AVAILABLE numeric,
              COUNTRY_ID numeric,
              QC_SAFETY_DATE timestamp,
              QC_SENT_DATE timestamp,
              QC_RESULT_DATE timestamp,
              QC_CROSS_REFERENCE varchar(16),
              WHO_DRUG_CODE varchar(45),
              CO_DRUG_CODE varchar(20),
              PRODUCT_NAME varchar(70),
              GENERIC_NAME text,
              GENERIC_NAME_J text,
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
              INCLUDE_FREQUENCY numeric,
              SDRUG_NOT_ADMIN numeric,
              PROD_REPTD varchar(255),
              PROD_CODED varchar(255),
              PROD_SYN_ID numeric,
              PROD_CODE_STATUS numeric(3),
              DELETED timestamp,
              STUDY_PRODUCT_NUM numeric(2),
              QC_QUANTITY varchar(10),
              QC_RETURN_DATE timestamp,
              QC_COMPLAINT_CAT_DATE timestamp,
              QC_ANALYSIS_CAT_DATE timestamp,
              QC_ANALYSIS_SUMMARY_DATE timestamp,
              PRIMARY_EVENT numeric,
              MEDICINAL_PROD_ID varchar(10),
              FAMILY_ID numeric,
              EXISTING_RPT_SEQ_MOD_FLG numeric,
              DRUG_CODE_J varchar(20),
              DRUG_CODE_TYPE_J numeric,
              PRODUCT_NAME_J varchar(70),
              PROD_CODED_J varchar(255),
              PROD_REPTD_J varchar(255),
              QC_CROSS_REFERENCE_J varchar(16),
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
              DLP_REVISION_NUMBER numeric NOT NULL,
              LAST_UPDATE_TIME timestamp,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              NOTES text,
              NOTES_J text,
              QC_ANALYSIS_CAT_TEXT text,
              QC_ANALYSIS_CAT_TEXT_J text,
              QC_ANAL_SUMMARY_TEXT text,
              QC_ANAL_SUMMARY_TEXT_J text,
              QC_RESULT text,
              QC_RESULT_J text,
              QC_COMMENT text,
              QC_COMMENT_J text,
              QC_COMPLAINT_CAT_TEXT text,
              QC_COMPLAINT_CAT_TEXT_J text,
              VAERS_BLOCK_ID numeric,
              PROD_LIC_ID numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_prod_devices"
          "CREATE TABLE rm_case_prod_devices (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              MODEL_NO varchar(20),
              LOT_NO varchar(35),
              SERIAL_NO varchar(20),
              DATE_IMPLANT timestamp,
              DATE_IMPANT_RES numeric,
              DATE_EXPLANT timestamp,
              DATE_EXPLANT_RES numeric,
              DEVICE_AGE varchar(10),
              EXP_DATE timestamp,
              EXP_DATE_RES numeric,
              SORT_ID numeric,
              EXPLANT_DATE_PARTIAL varchar(20),
              IMPLANT_DATE_PARTIAL varchar(20),
              EXP_DATE_PARTIAL varchar(20),
              DELETED timestamp,
              MALFUNCTION_DATE timestamp,
              LOT_ID numeric,
              LOT_JUST varchar(1000),
              LOT_STATUS numeric(1),
              MFR_EVALUATION numeric,
              MFR_EVAL_REASON_ID numeric,
              MFG_DATE timestamp,
              REMEDIAL_ACTION numeric,
              REMEDIAL_OTHER varchar(20),
              DEV_USAGE numeric,
              DEV_OPER numeric,
              DEV_OPER_OTHER_TEXT varchar(20),
              CATALOG varchar(20),
              CATALOG_OTHER varchar(30),
              EVALUATION numeric,
              RETURN_DATE timestamp,
              SUBCOMPONENT_ID numeric,
              SUBCOMPONENT_LOT varchar(35),
              DEVICE_TYPE numeric,
              TRAINED_USER numeric,
              IMPROPER_USE numeric,
              MALFUNCTION numeric,
              MALFUNCTION_TYPE numeric,
              REPROCESSED_REUSE numeric,
              REPROCESSOR_NAME_ADDRESS varchar(200),
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
              FOLLOWUP_TYPE numeric,
              METH_CD1 varchar(4),
              METH_CD2 varchar(4),
              METH_CD3 varchar(4),
              METH_CD4 varchar(4),
              RES_CD1 varchar(4),
              RES_CD2 varchar(4),
              RES_CD3 varchar(4),
              RES_CD4 varchar(4),
              CONC_CD1 varchar(4),
              CONC_CD2 varchar(4),
              CONC_CD3 varchar(4),
              CONC_CD4 varchar(4),
              USC varchar(20),
              MANU_NARRATIVE numeric(1),
              MANU_CORRECTED numeric(1),
              ADVERSE_EVENT numeric,
              PRODUCT_PROBLEM numeric,
              REPORT_TYPE numeric,
              MFG_DATE_PARTIAL varchar(20),
              MFG_DATE_RES numeric,
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
              LOT_JUST_J varchar(1000),
              DLP_REVISION_NUMBER numeric NOT NULL,
              IMPLANT_DURATION varchar(20),
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRELIMINARY_COMMENTS text,
              NARRATIVE_TEXT text,
              PRELIMINARY_COMMENTS_J text,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_prod_devices_pmda"
          "CREATE TABLE rm_case_prod_devices_pmda (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              PROD_SEQ_NUM numeric NOT NULL,
              REPORTING_CATEGORY numeric,
              MALFUNCTION_DATE timestamp,
              NEXT_REPORT_DATE timestamp,
              MALFUNCTION_STATUS numeric,
              MALFUNCTION_NAME varchar(255),
              RESPONSIBLE_OFFICER varchar(50),
              PATIENT_STATUS text,
              PATIENT_TREATMENT text,
              FIRST_TIME_USE numeric,
              TIMES_OF_USE numeric,
              SINCE_FIRST_USE numeric,
              SINCE_FIRST_USE_DURATION numeric,
              CURRENT_STATUS numeric,
              RECALL_DISPOSED numeric,
              RECALL_VESTIGIAL numeric,
              RECALL_PLANNED numeric,
              RECALL_IMPOSSIBLE numeric,
              REMARKS text,
              INVESTIGATION_RESULTS text,
              ACTION_TAKEN_UNTIL_NOW text,
              FUTURE_ACTIONS numeric,
              DEVICE_SUMMARY text,
              MEASURE_CLASSIFICATION varchar(100),
              RESEARCH_AND_MEASURES text,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,PROD_SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_prod_drugs"
          "CREATE TABLE rm_case_prod_drugs (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              FORMULATION_ID numeric,
              CONCENTRATION varchar(10),
              CONC_UNITS_ID numeric,
              ACT_TAKEN_ID numeric,
              SEVERITY_ID numeric,
              INTERACTION numeric,
              CONTRAIND numeric,
              FIRST_DOSE timestamp,
              FIRST_DOSE_RES numeric,
              LAST_DOSE timestamp,
              LAST_DOSE_RES numeric,
              TOTAL_DOSE numeric(22,7),
              TOT_DOSE_UNIT_ID numeric,
              PREV_USE numeric,
              ABUSE numeric,
              OVERDOSE numeric,
              PROTOCOL numeric,
              DECHALLENGE numeric,
              DECHALL_DATE timestamp,
              RECHALLENGE numeric,
              RECHALL_START timestamp,
              RECHALL_STOP timestamp,
              RECHALL_OUTCOME numeric,
              SORT_ID numeric,
              B_WEIGHT_GRAMS numeric,
              N_SIBLINGS numeric,
              B_WEIGHT_OZS numeric,
              FIRST_DOSE_PARTIAL varchar(20),
              LAST_DOSE_PARTIAL varchar(20),
              TAMPERING numeric(4),
              CUMULATIVE_DOSE numeric(22,7),
              CUMULATIVE_DOSE_UNIT varchar(10),
              DELETED timestamp,
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
              CUMULATIVE_DOSE_UNIT_ID numeric,
              OBTAIN_DRUG_COUNTRY_ID numeric,
              DRUG_AUTH_COUNTRY_ID numeric,
              DURATION_SECONDS numeric,
              LATENCY_SECONDS numeric,
              DELAY_SECONDS numeric,
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
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_prod_indications"
          "CREATE TABLE rm_case_prod_indications (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              PROD_SEQ_NUM numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              SORT_ID numeric,
              IND_REPTD varchar(255),
              IND_CODED varchar(255),
              IND_CODE varchar(50),
              IND_PREF_TERM varchar(250),
              IND_LLT_CODE varchar(50),
              IND_LLT varchar(250),
              IND_HLT_CODE varchar(50),
              IND_HLT varchar(250),
              IND_HLGT_CODE varchar(50),
              IND_HLGT varchar(250),
              IND_SOC_CODE varchar(50),
              IND_SOC varchar(250),
              IND_SYN_CODE numeric,
              IND_CODE_DICT numeric,
              IND_CODE_STATUS numeric(3),
              IND_CODED_J varchar(255),
              IND_CODE_STATUS_J numeric,
              IND_HLGT_J varchar(250),
              IND_HLT_J varchar(250),
              IND_LLT_CODE_J varchar(50),
              IND_LLT_J varchar(250),
              IND_PREF_TERM_J varchar(250),
              IND_REPTD_J varchar(255),
              IND_SOC_J varchar(250),
              IND_SYN_CODE_J numeric,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,PROD_SEQ_NUM,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_prod_ingredient"
          "CREATE TABLE rm_case_prod_ingredient (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              ITEM numeric NOT NULL,
              INGREDIENT_ID numeric,
              CONC_UNIT_ID numeric,
              CONCENTRATION varchar(10),
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,ITEM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_prod_qc_cid"
          "CREATE TABLE rm_case_prod_qc_cid (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              PROD_SEQ_NUM numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              SORT_ID numeric,
              CID_NUMBER varchar(20),
              PCID_NUMBER varchar(20),
              LOT_NUMBER varchar(35),
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              DELETED_FLAG numeric(1) NOT NULL,
              REVISION_DELETE_FLAG numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,PROD_SEQ_NUM,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_reference"
          "CREATE TABLE rm_case_reference (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              REF_NO varchar(100),
              REF_TYPE_ID numeric,
              NOTES varchar(200),
              REF_CASE_ID numeric,
              SORT_ID numeric,
              NOTES_J varchar(200),
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_reg_reports"
          "CREATE TABLE rm_case_reg_reports (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              REG_REPORT_ID numeric,
              DATE_ASSESSED timestamp,
              SORT_ID numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM)
          ); " }

    { "rm_case_relationships"
          "CREATE TABLE rm_case_relationships (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              DIAGNOSIS numeric,
              SYMPTOM numeric,
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_reporters"
          "CREATE TABLE rm_case_reporters (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              MEDIA_ID numeric,
              OCCUPATION_ID numeric,
              HCP_FLAG numeric,
              PRIMARY_CONTACT numeric,
              CORR_CONTACT numeric,
              REPORTER_TYPE numeric,
              INTERMEDIARY_ID numeric,
              RPT_SENT numeric,
              SORT_ID numeric,
              CONFIDENTIAL numeric(1),
              COUNTRY_ID numeric,
              SUFFIX varchar(15),
              MIDDLE_NAME varchar(15),
              PREFIX varchar(50),
              FIRST_NAME varchar(35),
              LAST_NAME varchar(50),
              REPORTER_ID varchar(20),
              INSTITUTION varchar(60),
              DEPARTMENT varchar(60),
              CITY varchar(35),
              STATE varchar(40),
              POSTCODE varchar(15),
              COUNTRY varchar(50),
              PHONE varchar(20),
              ALTPHONE varchar(20),
              FAX varchar(50),
              REPORTER_REF varchar(20),
              EMAIL varchar(100),
              ADDRESS varchar(120),
              SUFFIX_J varchar(15),
              ADDRESS_J varchar(120),
              CITY_J varchar(35),
              DEPARTMENT_J varchar(60),
              FIRST_NAME_J varchar(35),
              INSTITUTION_J varchar(60),
              LAST_NAME_J varchar(50),
              MIDDLE_NAME_J varchar(15),
              PREFIX_J varchar(50),
              STATE_J varchar(40),
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              NOTES text,
              NOTES_J text,
              INSTITUTION_ID varchar(15),
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_rev_master"
          "CREATE TABLE rm_case_rev_master (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              DLP_REVISION_NUMBER numeric NOT NULL,
              CASE_LOCKED numeric,
              DATA_CLEANUP numeric,
              CASE_REVISION_DATE timestamp,
              DATE_LOCKED timestamp,
              SITE_ID numeric,
              STATE_ID numeric,
              COUNTRY_ID numeric,
              EFFECTIVE_RECEIPT_DATE timestamp,
              EFFECTIVE_RECEIPT_DATE_J timestamp,
              EFFECTIVE_SAFETY_DATE timestamp,
              EFFECTIVE_TIME_STAMP timestamp,
              DCM_XREF_NUM numeric,
              REVISION_DELETE_FLAG numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_routing"
          "CREATE TABLE rm_case_routing (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              ROUTE_DATE timestamp,
              USER_ID numeric,
              SORT_ID numeric,
              COMMENT_TXT varchar(1000),
              TO_STATE_ID numeric,
              FR_STATE_ID numeric,
              WORKFLOW_SEQ_NUM numeric,
              DELETED timestamp,
              JUSTIFICATION_ID numeric,
              FOLLOWUP_NUM numeric,
              COMMENT_TXT_J varchar(1000),
              DLP_REVISION_NUMBER numeric NOT NULL,
              S_FOLLOWUP_NUM numeric,
              REPORT_SCHEDULING numeric,
              CASE_STATUS numeric,
              LAST_UPDATE_TIME timestamp,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_study"
          "CREATE TABLE rm_case_study (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              STUDY_KEY numeric,
              STUDY_NUM varchar(35),
              STUDY_TYPE numeric,
              CENTER_ID numeric,
              CENTER_NAME varchar(40),
              PROTOCOL_ID numeric,
              PROTOCOL_NUM varchar(40),
              OTHER_ID varchar(20),
              CODE_BROKEN numeric,
              BROKEN_BY numeric,
              BROKEN_DATE timestamp,
              REASON varchar(100),
              WEEK varchar(5),
              VISIT varchar(5),
              DELETED timestamp,
              PRODUCT_COUNT numeric(2),
              CLASSIFICATION_ID numeric,
              BLIND_NAME varchar(70),
              STUDY_DESC text,
              STUDY_DESC_J text,
              BLIND_NAME_J varchar(70),
              CENTER_NAME_J varchar(40),
              CLIN_COMPOUND_NUM_J varchar(70),
              PROTOCOL_NUM_J varchar(40),
              STUDY_NUM_J varchar(35),
              DEV_PHASE_ID numeric,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              COHORT_ID numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_user_defined"
          "CREATE TABLE rm_case_user_defined (
              ENTERPRISE_ID numeric NOT NULL,
              TABLE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              UD_SEQ_NUM numeric NOT NULL,
              UD_NUMBER_1 numeric,
              UD_NUMBER_2 numeric,
              UD_NUMBER_3 numeric,
              UD_NUMBER_4 numeric,
              UD_NUMBER_5 numeric,
              UD_NUMBER_6 numeric,
              UD_NUMBER_7 numeric,
              UD_NUMBER_8 numeric,
              UD_NUMBER_9 numeric,
              UD_NUMBER_10 numeric,
              UD_NUMBER_11 numeric,
              UD_NUMBER_12 numeric,
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
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              DELETED_FLAG numeric(1) NOT NULL,
              REVISION_DELETE_FLAG numeric,
              PRIMARY KEY (ENTERPRISE_ID,TABLE_ID,CASE_ID,UD_SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_vacc_hist"
          "CREATE TABLE rm_case_vacc_hist (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              LOG_NO numeric NOT NULL,
              LOCATION_ID numeric,
              ADMIN_ROUTE_ID numeric,
              VACC_DATE timestamp,
              VACC_DATE_RES numeric,
              VACCINE varchar(40),
              DOSE_NO numeric,
              DOSE numeric(22,7),
              DOSE_UNIT_ID numeric,
              LOT_NO varchar(20),
              MANUFACTURER_ID numeric,
              SORT_ID numeric,
              PARENT_SEQ_NUM numeric,
              VACC_DATE_PARTIAL varchar(20),
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              VAERS_BLOCK_14 numeric,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,LOG_NO,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_vacc_prior_ae"
          "CREATE TABLE rm_case_vacc_prior_ae (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              PATIENT numeric,
              VACCINE varchar(40),
              DOSE_NO numeric,
              AGE numeric(22,7),
              AGE_UNIT_ID numeric,
              ADVERSE_EVENT varchar(50),
              SORT_ID numeric,
              ADVERSE_EVENT_J varchar(50),
              VACCINE_J varchar(40),
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,SEQ_NUM,DLP_REVISION_NUMBER)
          ); " }

    { "rm_case_vacc_vaers"
          "CREATE TABLE rm_case_vacc_vaers (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              VACC_AT_ID numeric,
              VACC_STATE varchar(10),
              VACC_COUNTY varchar(20),
              PURCHASE_ID numeric,
              VACC_ER_VISIT numeric,
              ADMIN_BY varchar(40),
              PHYSICIAN varchar(40),
              FACILITY varchar(40),
              ADDRESS varchar(120),
              CITY varchar(30),
              STATE varchar(10),
              POSTAL_CODE varchar(10),
              PHONE varchar(20),
              RELATION_TO_PATIENT numeric,
              REPORTED_PREVIOUSLY numeric,
              ILLNESS varchar(50),
              VAERS_NUMBER varchar(20),
              PROLONGED_HOSP numeric(1),
              ADDRESS_J varchar(120),
              ADMIN_BY_J varchar(40),
              CITY_J varchar(30),
              FACILITY_J varchar(40),
              ILLNESS_J varchar(50),
              PHYSICIAN_J varchar(40),
              STATE_J varchar(10),
              VACC_COUNTY_J varchar(20),
              VACC_STATE_J varchar(10),
              DELETED timestamp,
              DLP_REVISION_NUMBER numeric NOT NULL,
              REVISION_DELETE_FLAG numeric,
              DELETED_FLAG numeric(1) NOT NULL,
              EFFECTIVE_START_DATE timestamp NOT NULL,
              EFFECTIVE_END_DATE timestamp NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,CASE_ID,DLP_REVISION_NUMBER)
          ); " }

    { "rm_cfg_auth_countries"
          "CREATE TABLE rm_cfg_auth_countries (
              ENTERPRISE_ID numeric NOT NULL,
              GROUP_ID numeric NOT NULL,
              COUNTRY_ID numeric NOT NULL,
              TYPE numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,GROUP_ID,COUNTRY_ID,TYPE,SEQ_NUM)
          ); " }

    { "rm_cfg_auth_products"
          "CREATE TABLE rm_cfg_auth_products (
              ENTERPRISE_ID numeric NOT NULL,
              GROUP_ID numeric NOT NULL,
              PRODUCT_ID numeric NOT NULL,
              TYPE numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,GROUP_ID,PRODUCT_ID,TYPE,SEQ_NUM)
          ); " }

    { "rm_cfg_auth_sites"
          "CREATE TABLE rm_cfg_auth_sites (
              ENTERPRISE_ID numeric NOT NULL,
              GROUP_ID numeric NOT NULL,
              SITE_ID numeric NOT NULL,
              TYPE numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,GROUP_ID,SITE_ID,TYPE,SEQ_NUM)
          ); " }

    { "rm_cfg_auth_studies"
          "CREATE TABLE rm_cfg_auth_studies (
              ENTERPRISE_ID numeric NOT NULL,
              GROUP_ID numeric NOT NULL,
              STUDY_KEY numeric NOT NULL,
              TYPE numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,GROUP_ID,STUDY_KEY,TYPE,SEQ_NUM)
          ); " }

    { "rm_cfg_dictionaries_enterprise"
          "CREATE TABLE rm_cfg_dictionaries_enterprise (
              ENTERPRISE_ID numeric NOT NULL,
              GLOBAL_DICT_ID numeric NOT NULL,
              DICT_ID numeric NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,GLOBAL_DICT_ID,DICT_ID)
          ); " }

    { "rm_cfg_dictionaries_global"
          "CREATE TABLE rm_cfg_dictionaries_global (
              NAME varchar(30),
              DIALOG_NUM numeric,
              SQL_DRUG varchar(4000),
              SQL_INDICATION varchar(4000),
              SQL_EVENT_SIMPLE varchar(4000),
              SQL_EVENT_PART1 varchar(4000),
              SQL_EVENT_PART2 varchar(4000),
              VERSION_NUMBER varchar(20),
              DICTIONARY_SOURCE varchar(14),
              DELETED timestamp,
              SCHEMA_OWNER varchar(30),
              GLOBAL_DICT_ID numeric NOT NULL,
              PRIMARY KEY (GLOBAL_DICT_ID)
          ); " }

    { "rm_cfg_dsr_owner"
          "CREATE TABLE rm_cfg_dsr_owner (
              ENTERPRISE_ID numeric NOT NULL,
              ID numeric NOT NULL,
              REPORT_FORM_ID numeric,
              OWNER_ID numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,ID)
          ); " }

    { "rm_cfg_dsr_report"
          "CREATE TABLE rm_cfg_dsr_report (
              ENTERPRISE_ID numeric NOT NULL,
              REPORT_FORM_ID numeric NOT NULL,
              SHOW_HEADERFOOTER numeric NOT NULL,
              LANDSCAPE_TEMPLATE varchar(100),
              PORTRAIT_TEMPLATE varchar(100),
              LANDSCAPE_BLOB_SIZE numeric,
              PORTRAIT_BLOB_SIZE numeric,
              SHOW_PAGE_NUMBERING numeric,
              PAGE_NUMBERING numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,REPORT_FORM_ID)
          ); " }

    { "rm_cfg_dsr_template_variables"
          "CREATE TABLE rm_cfg_dsr_template_variables (
              ENTERPRISE_ID numeric NOT NULL,
              ID numeric NOT NULL,
              VARIABLE_NAME varchar(200),
              SQL_QUERY varchar(4000),
              REPORT_RELATED numeric(1),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,ID)
          ); " }

    { "rm_cfg_groups"
          "CREATE TABLE rm_cfg_groups (
              ENTERPRISE_ID numeric NOT NULL,
              GROUP_ID numeric NOT NULL,
              GROUP_NAME varchar(40),
              EMAIL_ADDR varchar(100),
              CASE_ACCESS varchar(1000),
              MENU_ACCESS varchar(1000),
              DISABLED numeric,
              LISTEDNESS_ACCESS varchar(1000),
              EMAIL_SUPERVISOR varchar(100),
              LAM numeric(2),
              DEFAULT_REPORT_FORM_ID numeric,
              DELETED timestamp,
              PRODUCT_SECURITY numeric(1) NOT NULL,
              STUDY_SECURITY numeric(1) NOT NULL,
              NO_ADV_ACCESS numeric(1) NOT NULL,
              NO_ADV_SHARE numeric(1) NOT NULL,
              NO_ADV_SQL numeric(1) NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,GROUP_ID)
          ); " }

    { "rm_cfg_groups_product"
          "CREATE TABLE rm_cfg_groups_product (
              ENTERPRISE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              GROUP_ID numeric NOT NULL,
              FAMILY_ID numeric,
              PRODUCT_ID numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,SEQ_NUM)
          ); " }

    { "rm_cfg_groups_study"
          "CREATE TABLE rm_cfg_groups_study (
              ENTERPRISE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              GROUP_ID numeric NOT NULL,
              PROTOCOL_NUM varchar(40),
              STUDY_KEY numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,SEQ_NUM)
          ); " }

    { "rm_cfg_lam_central_site"
          "CREATE TABLE rm_cfg_lam_central_site (
              ENTERPRISE_ID numeric NOT NULL,
              SITE_ID numeric NOT NULL,
              LAM_SITE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,SITE_ID,LAM_SITE_ID,SEQ_NUM)
          ); " }

    { "rm_cfg_lam_rpt_sched"
          "CREATE TABLE rm_cfg_lam_rpt_sched (
              ENTERPRISE_ID numeric NOT NULL,
              ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              USER_ID numeric NOT NULL,
              REASON varchar(100),
              REQUEST_DATE timestamp,
              PROCESSED numeric(1) NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,ID)
          ); " }

    { "rm_cfg_letter_site"
          "CREATE TABLE rm_cfg_letter_site (
              ENTERPRISE_ID numeric NOT NULL,
              ID numeric(10) NOT NULL,
              TEMPLATE_ID numeric(10),
              SITE_ID numeric(10),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,ID)
          ); " }

    { "rm_cfg_list_acc_user_country"
          "CREATE TABLE rm_cfg_list_acc_user_country (
              ENTERPRISE_ID numeric NOT NULL,
              USER_ID numeric NOT NULL,
              COUNTRY_ID numeric NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,USER_ID,COUNTRY_ID)
          ); " }

    { "rm_cfg_medwatch_codes"
          "CREATE TABLE rm_cfg_medwatch_codes (
              DIALOG numeric(10) NOT NULL,
              VALUE varchar(4) NOT NULL,
              DESCRIPTION varchar(400) NOT NULL,
              DEFINITION varchar(500),
              SORTBY numeric(10,2) NOT NULL,
              PRIMARY KEY (DIALOG,VALUE,SORTBY)
          ); " }

    { "rm_cfg_mem_report"
          "CREATE TABLE rm_cfg_mem_report (
              ENTERPRISE_ID numeric NOT NULL,
              ID numeric(10) NOT NULL,
              NAME varchar(50) NOT NULL,
              USER_ID numeric NOT NULL,
              REPORT_TYPE numeric,
              XT_ROW numeric,
              XT_ROW2 numeric(10),
              XT_COLUMN numeric,
              XT_COLUMN2 numeric(10),
              XT_PRODUCT_TYPE numeric,
              XT_PRODUCT_ID numeric,
              XT_SCOPE_ID numeric,
              XT_TOP numeric,
              AC_ID numeric,
              LOGO numeric,
              SHADING numeric,
              TITLE varchar(100),
              START_DATE timestamp,
              END_DATE timestamp,
              RELATIVITY numeric,
              SHARED numeric,
              LS_GROUP numeric,
              LS_SORT numeric,
              FOR_PERIODIC numeric(1),
              DELETED timestamp,
              SUB_TITLE varchar(80),
              FOOTER varchar(80),
              CREATION_DATE numeric,
              LS_GROUPINGS varchar(300),
              DESCRIPTION varchar(200),
              LAST_MODIFIED_TIME timestamp,
              XT_ROW3 numeric(10),
              XT_COLUMN3 numeric(10),
              NO_REPORT_HEADER numeric(1) NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,ID)
          ); " }

    { "rm_cfg_narrative_template"
          "CREATE TABLE rm_cfg_narrative_template (
              ENTERPRISE_ID numeric NOT NULL,
              TEMPLATE_ID numeric NOT NULL,
              TEMPLATE_NAME varchar(40),
              LANGUAGE numeric,
              RETIRED numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,TEMPLATE_ID)
          ); " }

    { "rm_cfg_narr_template_phrase"
          "CREATE TABLE rm_cfg_narr_template_phrase (
              ENTERPRISE_ID numeric NOT NULL,
              TEMPLATE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              AC_ID numeric,
              SORT_ID numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,TEMPLATE_ID,SEQ_NUM)
          ); " }

    { "rm_cfg_per_report"
          "CREATE TABLE rm_cfg_per_report (
              ENTERPRISE_ID numeric NOT NULL,
              REPORT_FORM_ID numeric NOT NULL,
              AGENCY_ID numeric,
              INGREDIENT_ID numeric,
              INDICATION varchar(50),
              FORMULATION_ID numeric,
              REPORT_NUM varchar(50),
              REPORT_TITLE varchar(100),
              INGREDIENT varchar(120),
              TRADE_NAME varchar(400),
              APPROVAL varchar(100),
              INGD_TRD_APRV numeric(10),
              AC_ID numeric,
              INCL_OPTIONS numeric(10),
              INCL_LINE_LISTING numeric(10),
              GROUP_BY numeric,
              SORT_BY numeric,
              LINE_OPTIONS numeric(8),
              LIST_ONCEALL_NARR numeric(10),
              START_DATE timestamp,
              DUE_DAYS numeric(10),
              AUTO_GEN_PREG numeric(10),
              AUTO_DAYS numeric(10),
              AUTO_TIME varchar(5),
              GROUP_ID numeric,
              LANGUAGE numeric(10),
              PRINT_CONFIG numeric,
              CUM_START_DATE timestamp,
              CUM_END_DATE timestamp,
              DELETED timestamp,
              SUMMARY numeric,
              GROUP_BY2 numeric,
              GROUP_BY3 numeric,
              CATEGORY varchar(50),
              SHARED numeric,
              USER_ID numeric,
              HIT_LIST numeric(1),
              SORT_BY2 numeric,
              SORT_BY3 numeric,
              GROUP_BY4 numeric,
              TITLE_PER_DRUG varchar(50),
              TITLE_PER_DRUG_OPTION varchar(50),
              REASSESSMENT numeric(3),
              FOOTER varchar(120),
              FDA_SHEETNAME varchar(40),
              FDA_AGENCY_ID numeric,
              SUB_CATEGORY varchar(50),
              SOLICITED_AC_ID numeric,
              SOLICITED_RPT_TITLE varchar(60),
              SOLICITED_HIT_LIST numeric(1),
              PREV_REPORT_START_DT timestamp,
              MESSAGE_TYPE_ID numeric,
              ICSR_AGENCY_ID numeric,
              FROM_DATE timestamp,
              TO_DATE timestamp,
              INCL_DATE_FILTER_TYPE numeric,
              LAST_MODIFIED_USER_ID numeric,
              CREATE_TIME timestamp,
              UPDATE_TIME timestamp,
              JUSTIFICATION_TEXT varchar(1000),
              PRINT_PAGE_NUM numeric,
              JPN_APPROVAL varchar(100),
              CSPS_DATE varchar(100),
              CTPCS_DATE varchar(100),
              ENABLE_DELIV_QTY numeric(1),
              PRINT_ALL_AGENCY numeric,
              PRINT_ALL_ANOTHER_NDA numeric(1) NOT NULL,
              PRINT_ALL_CASES numeric(1) NOT NULL,
              PRINT_ALL_EXPEDITED numeric(1) NOT NULL,
              PRINT_ALL_FORM numeric(1) NOT NULL,
              PRINT_ALL_PERIODIC numeric(1) NOT NULL,
              PRINT_NO_WATERMARK numeric(1) NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,REPORT_FORM_ID)
          ); " }

    { "rm_cfg_per_rpt_child"
          "CREATE TABLE rm_cfg_per_rpt_child (
              ENTERPRISE_ID numeric NOT NULL,
              ID numeric NOT NULL,
              REPORT_FORM_ID numeric NOT NULL,
              REC_TYPE numeric(3),
              FIELD numeric,
              DATE_START timestamp,
              DATE_END timestamp,
              OPTIONS numeric(4),
              DELETED timestamp,
              SORT_ID numeric(2),
              FIELD_TEXT varchar(250),
              PRIMARY KEY (ENTERPRISE_ID,ID)
          ); " }

    { "rm_cfg_per_rpt_cri"
          "CREATE TABLE rm_cfg_per_rpt_cri (
              ENTERPRISE_ID numeric NOT NULL,
              REPORT_TYPE numeric(10) NOT NULL,
              REPORT_FORM_ID numeric NOT NULL,
              REPORT_TYPE_ID numeric NOT NULL,
              SERIOUS numeric(1),
              FATAL numeric(1),
              LISTEDNESS numeric(1),
              RELATED numeric(1),
              HCP numeric(1),
              DATASHEET numeric,
              SHEET_NAME varchar(40),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,REPORT_TYPE)
          ); " }

    { "rm_cfg_per_rpt_freq"
          "CREATE TABLE rm_cfg_per_rpt_freq (
              ENTERPRISE_ID numeric NOT NULL,
              ID numeric NOT NULL,
              REPORT_FORM_ID numeric NOT NULL,
              FREQ numeric(2),
              START_MONTH numeric(8),
              END_MONTH numeric(8),
              SUBMITTED numeric(1),
              TOTAL_DELIV_QTY varchar(15),
              TOTAL_DELIV_QTY_UNIT_ID numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,ID,REPORT_FORM_ID)
          ); " }

    { "rm_cfg_per_rpt_security"
          "CREATE TABLE rm_cfg_per_rpt_security (
              ENTERPRISE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              REPORT_FORM_ID numeric NOT NULL,
              GROUP_ID numeric NOT NULL,
              RIGHTS numeric NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,SEQ_NUM)
          ); " }

    { "rm_cfg_placeholders"
          "CREATE TABLE rm_cfg_placeholders (
              ENTERPRISE_ID numeric NOT NULL,
              PLACE_ID numeric NOT NULL,
              PLACE_HOLDER_TYPE numeric,
              PLACE_HOLDER varchar(40),
              SQL_QUERY varchar(4000),
              DELETED timestamp,
              ORDERBY_CLAUSE1 varchar(200),
              ORDERBY_CLAUSE2 varchar(200),
              SQL_QUERY_J varchar(4000),
              PRIMARY KEY (ENTERPRISE_ID,PLACE_ID)
          ); " }

    { "rm_cfg_priorities"
          "CREATE TABLE rm_cfg_priorities (
              ENTERPRISE_ID numeric NOT NULL,
              PRIORITY_ID numeric NOT NULL,
              ASSESS_SERIOUS numeric,
              ASSESS_LISTED numeric,
              ASSESS_CAUS numeric,
              DUE_DATE_NOTIFY numeric,
              DUE_SOON_DAYS numeric,
              AC_ID numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,PRIORITY_ID)
          ); " }

    { "rm_cfg_psur_multi_ingredients"
          "CREATE TABLE rm_cfg_psur_multi_ingredients (
              ENTERPRISE_ID numeric NOT NULL,
              ID numeric NOT NULL,
              REPORT_FORM_ID numeric NOT NULL,
              INGREDIENT_ID numeric NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,ID)
          ); " }

    { "rm_cfg_reg_report_rules"
          "CREATE TABLE rm_cfg_reg_report_rules (
              ENTERPRISE_ID numeric NOT NULL,
              REG_RPT_RULES_ID numeric NOT NULL,
              COUNTRY_ID numeric,
              LICENSE_TYPE_ID numeric,
              TITLE varchar(40),
              PERIODIC numeric,
              IND_STUDIES numeric,
              REPORT_FORM_ID numeric,
              LANGUAGE_ID numeric,
              TIMEFRAME numeric,
              EVENT numeric,
              FATAL numeric,
              SERIOUS numeric,
              LISTED numeric,
              CAUSAL numeric,
              AC_ID numeric,
              AGENCY_ID numeric,
              GROUP_ID numeric,
              CAUSAL_RPTD numeric(1),
              CASE_CAUSAL numeric(1),
              CASE_CAUSAL_RPTD numeric(1),
              MOST_CONSERV numeric(1),
              TEMPLATE_ID numeric,
              ACTIVE_MOIETY numeric(3),
              PROTECT numeric(1),
              SDRUG_NON_ADMIN numeric(2),
              DELETED timestamp,
              COMMENT_TYPE_ID numeric(10),
              REF_TYPE_ID numeric,
              ACTIVE numeric(1),
              FAMILY_ID numeric,
              PRODUCT_GROUP_ID numeric,
              EVT_INTENSITY_ID numeric,
              EVT_SERIOUSNESS numeric,
              MESSAGE_TYPE_ID numeric,
              NON_CLINICAL_TRIAL_CASES numeric(1),
              AUTO_DISTRIBUTE_REPORTS numeric NOT NULL,
              BLIND_STUDY_PRODUCT numeric(1) NOT NULL,
              FORCE_DISTRIBUTE_DAYS numeric,
              HCP_CASE numeric(1),
              NO_FOLLOWUP_DOWNGRADE numeric(1),
              RPT_CATEGORY_ID numeric,
              LIC_CATEGORY_ID numeric,
              SUPER_RULE numeric(1),
              GROUP_2_COUNTRY_ADJUST_DAYS numeric,
              DEVICE_CATEGORY_ID numeric,
              PRIMARY KEY (ENTERPRISE_ID,REG_RPT_RULES_ID)
          ); " }

    { "rm_cfg_rpt_wf_rules"
          "CREATE TABLE rm_cfg_rpt_wf_rules (
              ENTERPRISE_ID numeric NOT NULL,
              TO_STATE_ID numeric NOT NULL,
              FR_STATE_ID numeric NOT NULL,
              AC_ID numeric,
              EXCLUSIVE_FLAG numeric,
              EMAIL_FLAG numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,TO_STATE_ID,FR_STATE_ID)
          ); " }

    { "rm_cfg_rpt_wf_states"
          "CREATE TABLE rm_cfg_rpt_wf_states (
              ENTERPRISE_ID numeric NOT NULL,
              REPORT_STATE_ID numeric NOT NULL,
              STATE_DESC varchar(30),
              STATE_NAME varchar(30),
              DELETED timestamp,
              STATE_DESC_J varchar(30),
              STATE_NAME_J varchar(30),
              PRIMARY KEY (ENTERPRISE_ID,REPORT_STATE_ID)
          ); " }

    { "rm_cfg_templates"
          "CREATE TABLE rm_cfg_templates (
              ENTERPRISE_ID numeric NOT NULL,
              TEMPLATE_ID numeric NOT NULL,
              BLOB_SEQ numeric(10) NOT NULL,
              NAME varchar(40),
              SHOW numeric,
              LETTER_SCHED varchar(30),
              ACTION_SCHED numeric,
              LETTER_PATH varchar(200),
              BLOBSIZE numeric,
              REG_RPT_COVER numeric,
              DELETED timestamp,
              ACTION_TYPE_ID numeric,
              RETURN_EMAIL_ADDRESS varchar(255),
              AC_ID numeric,
              NAME_J varchar(40),
              PRIMARY KEY (ENTERPRISE_ID,TEMPLATE_ID,BLOB_SEQ)
          ); " }

    { "rm_cfg_users"
          "CREATE TABLE rm_cfg_users (
              ENTERPRISE_ID numeric NOT NULL,
              USER_ID numeric NOT NULL,
              USER_FULLNAME varchar(64),
              USER_NAME varchar(64),
              USER_PASSWORD varchar(1000),
              USER_EMAIL varchar(100),
              WORKFLOW_MGR numeric,
              ACCOUNT_DISABLED numeric,
              FORCE_EXPIRE numeric,
              NUM_DAYS numeric,
              PROTECT_FROM_UNBLIND numeric,
              DISPLAY_WL_LOGIN numeric,
              LANG_PREF numeric,
              SITE_ID numeric,
              PWD_SET_DATE timestamp,
              CREATION_DATE timestamp,
              ALLOW_UNBLINDING numeric(1),
              ALLOW_LOCKING numeric(1),
              ALLOW_CLOSING numeric(1),
              SITE_SECURITY numeric(4),
              AGSERVICE numeric(1),
              FORCE_CHANGE numeric(2),
              J_CONFIGURATION numeric(2),
              SEE_NON_CURRENT numeric(2),
              DELETED timestamp,
              LOGIN_COUNT numeric NOT NULL,
              SECURITY_DISABLED numeric NOT NULL,
              SECURITY_DISABLED_TEXT varchar(400),
              ROUTE_ON_CLOSE numeric,
              LDAP_USER numeric,
              ESM_USER numeric,
              PERCEPTIVE_WORKFLOW_MGR numeric(1),
              ENABLE_CHECKLIST_ON_ROUTE numeric NOT NULL,
              AC_ADMIN numeric NOT NULL,
              LDAP_SERVER_ID numeric,
              COPY_CONFIG numeric,
              GL_ENT_MGMT_ACCESS numeric NOT NULL,
              GL_USR_MGMT_ACCESS numeric NOT NULL,
              MENU_ACCESS varchar(2000),
              SITE_NO_ACCESS varchar(2000),
              WL_ACT_ITEM_ACCESS numeric NOT NULL,
              WL_CONTACTS_ACCESS numeric NOT NULL,
              WL_NEW_ACCESS numeric NOT NULL,
              WL_OPEN_ACCESS numeric NOT NULL,
              DEOPTIMIZER_COUNTER numeric,
              GLOBAL_ADMIN numeric NOT NULL,
              HASH_ALGO varchar(30),
              SALT varchar(30),
              PRIMARY KEY (ENTERPRISE_ID,USER_ID)
          ); " }

    { "rm_cfg_users_sites"
          "CREATE TABLE rm_cfg_users_sites (
              ENTERPRISE_ID numeric NOT NULL,
              USER_ID numeric NOT NULL,
              SITE_ID numeric NOT NULL,
              ACCESS_LEVEL numeric(4) NOT NULL,
              GROUP_ID numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,USER_ID,SITE_ID)
          ); " }

    { "rm_cfg_user_access"
          "CREATE TABLE rm_cfg_user_access (
              ENTERPRISE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              USER_ID numeric NOT NULL,
              APPLICATION_ID numeric NOT NULL,
              DEFAULT_APPLICATIONID numeric NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,SEQ_NUM)
          ); " }

    { "rm_cfg_user_enterprise_apps"
          "CREATE TABLE rm_cfg_user_enterprise_apps (
              USER_NAME varchar(64) NOT NULL,
              APP_NAME varchar(100) NOT NULL,
              SECURITY_LEVEL numeric,
              ENTERPRISE_ID numeric NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,USER_NAME,APP_NAME)
          ); " }

    { "rm_cfg_user_groups"
          "CREATE TABLE rm_cfg_user_groups (
              ENTERPRISE_ID numeric NOT NULL,
              USER_ID numeric NOT NULL,
              GROUP_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,USER_ID,GROUP_ID,SEQ_NUM)
          ); " }

    { "rm_cfg_workflow_rules"
          "CREATE TABLE rm_cfg_workflow_rules (
              ENTERPRISE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              TO_STATE_ID numeric NOT NULL,
              FR_STATE_ID numeric NOT NULL,
              GROUP_ID numeric NOT NULL,
              AC_ID numeric,
              EXCLUSIVE_FLAG numeric,
              EMAIL_FLAG numeric,
              DESCRIPTION varchar(80),
              SORT_ID numeric,
              NORMAL_TIME numeric(8,2),
              MAX_TIME numeric(8,2),
              DELETED timestamp,
              LOCK_CASE numeric,
              REQUIRE_PASSWORD numeric,
              PRODUCT_GROUP_ID numeric,
              UD_TEXT_1 varchar(100),
              UD_TEXT_2 varchar(100),
              RESTRICT_WORKFLOW_GROUP numeric,
              UNIT numeric,
              PRIMARY KEY (ENTERPRISE_ID,SEQ_NUM)
          ); " }

    { "rm_cfg_workflow_states"
          "CREATE TABLE rm_cfg_workflow_states (
              ENTERPRISE_ID numeric NOT NULL,
              STATE_ID numeric NOT NULL,
              STATE_NAME varchar(30),
              STATE_DESC varchar(100),
              SORT_ID numeric,
              DELETED timestamp,
              SITE_ID numeric,
              ARCHIVING numeric(1),
              PREF_STATE_ID numeric,
              PRIMARY KEY (ENTERPRISE_ID,STATE_ID)
          ); " }

    { "rm_cl_autopsy_results"
          "CREATE TABLE rm_cl_autopsy_results (
              ID numeric NOT NULL,
              RESULTS varchar(35),
              DELETED timestamp,
              PRIMARY KEY (ID)
          ); " }

    { "rm_cl_bfarm_causality"
          "CREATE TABLE rm_cl_bfarm_causality (
              ID numeric NOT NULL,
              CAUSALITY varchar(35) NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ID)
          ); " }

    { "rm_cl_device_deliv_type"
          "CREATE TABLE rm_cl_device_deliv_type (
              ID numeric NOT NULL,
              TYPE varchar(24),
              DELETED timestamp,
              PRIMARY KEY (ID)
          ); " }

    { "rm_cl_device_evaluation"
          "CREATE TABLE rm_cl_device_evaluation (
              ID numeric NOT NULL,
              DEV_EVAL varchar(24),
              DELETED timestamp,
              PRIMARY KEY (ID)
          ); " }

    { "rm_cl_device_operator"
          "CREATE TABLE rm_cl_device_operator (
              ID numeric NOT NULL,
              DEV_OPER varchar(19),
              DELETED timestamp,
              PRIMARY KEY (ID)
          ); " }

    { "rm_cl_device_usage"
          "CREATE TABLE rm_cl_device_usage (
              ID numeric NOT NULL,
              DEV_USAGE varchar(7),
              DELETED timestamp,
              PRIMARY KEY (ID)
          ); " }

    { "rm_cl_followup_type"
          "CREATE TABLE rm_cl_followup_type (
              ID numeric NOT NULL,
              TYPE varchar(75),
              DELETED timestamp,
              PRIMARY KEY (ID)
          ); " }

    { "rm_cl_jpn_drug_code_type"
          "CREATE TABLE rm_cl_jpn_drug_code_type (
              DRUG_CODE_ID numeric NOT NULL,
              DRUG_CODE varchar(200),
              DRUG_CODE_J varchar(200),
              DELETED timestamp,
              PRIMARY KEY (DRUG_CODE_ID)
          ); " }

    { "rm_cl_mfr_evaluation"
          "CREATE TABLE rm_cl_mfr_evaluation (
              ID numeric NOT NULL,
              EVALUATION varchar(51),
              DELETED timestamp,
              PRIMARY KEY (ID)
          ); " }

    { "rm_cl_pmda_device_use"
          "CREATE TABLE rm_cl_pmda_device_use (
              USE_ID numeric NOT NULL,
              USE varchar(20),
              USE_J varchar(20),
              DELETED timestamp,
              PRIMARY KEY (USE_ID)
          ); " }

    { "rm_cl_prev_use"
          "CREATE TABLE rm_cl_prev_use (
              ID numeric NOT NULL,
              DELETED timestamp,
              PREV_USE varchar(25),
              PRIMARY KEY (ID)
          ); " }

    { "rm_cl_rechall_outcome"
          "CREATE TABLE rm_cl_rechall_outcome (
              ID numeric NOT NULL,
              OUTCOME varchar(16),
              DELETED timestamp,
              PRIMARY KEY (ID)
          ); " }

    { "rm_cl_remedial_action"
          "CREATE TABLE rm_cl_remedial_action (
              ID numeric NOT NULL,
              ACTION varchar(106),
              DELETED timestamp,
              PRIMARY KEY (ID)
          ); " }

    { "rm_cl_report_scheduling"
          "CREATE TABLE rm_cl_report_scheduling (
              ID numeric NOT NULL,
              RESULTS varchar(35),
              DELETED timestamp,
              PRIMARY KEY (ID)
          ); " }

    { "rm_cl_study_product_type"
          "CREATE TABLE rm_cl_study_product_type (
              PROD_TYPE_NAME varchar(50),
              PROD_TYPE_ID numeric NOT NULL,
              PRIMARY KEY (PROD_TYPE_ID)
          ); " }

    { "rm_cl_subject"
          "CREATE TABLE rm_cl_subject (
              ID numeric NOT NULL,
              SUBJECT varchar(30) NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ID)
          ); " }

    { "rm_cl_term_type"
          "CREATE TABLE rm_cl_term_type (
              ID numeric NOT NULL,
              TYPE varchar(100),
              DELETED timestamp,
              PRIMARY KEY (ID)
          ); " }

    { "rm_cl_trimester_status"
          "CREATE TABLE rm_cl_trimester_status (
              ID numeric NOT NULL,
              STATUS varchar(100),
              PRIMARY KEY (ID)
          ); " }

    { "rm_cl_usage_of_device"
          "CREATE TABLE rm_cl_usage_of_device (
              ID numeric NOT NULL,
              USAGE_OF_DEVICE varchar(200),
              DELETED timestamp,
              PRIMARY KEY (ID)
          ); " }

    { "rm_cmn_lookup"
          "CREATE TABLE rm_cmn_lookup (
              ID numeric(1) NOT NULL,
              STATE2 varchar(10),
              STATE3 varchar(10),
              STATE4 varchar(10),
              DRUG_TYPE varchar(20),
              DELETED timestamp,
              PRIMARY KEY (ID)
          ); " }

    { "rm_cmn_profile_enterprise"
          "CREATE TABLE rm_cmn_profile_enterprise (
              ENTERPRISE_ID numeric NOT NULL,
              SECTION varchar(300) NOT NULL,
              KEY varchar(300) NOT NULL,
              VALUE varchar(1000),
              TREE_NAME varchar(100),
              KEY_TYPE numeric(2),
              KEY_LABEL varchar(500),
              KEY_OPTIONS varchar(1000),
              LINK_TABLE varchar(100),
              LINK_COLUMN_SEQ varchar(100),
              LINK_COLUMN_DISPLAY varchar(100),
              HELP_TEXT_TREE_NAME varchar(300),
              NEXT_KEY varchar(300),
              SORT_ORDER numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,SECTION,KEY)
          ); " }

    { "rm_cmn_profile_global"
          "CREATE TABLE rm_cmn_profile_global (
              SECTION varchar(300) NOT NULL,
              KEY varchar(300) NOT NULL,
              VALUE varchar(1000),
              TREE_NAME varchar(100),
              KEY_TYPE numeric(2),
              KEY_LABEL varchar(500),
              KEY_OPTIONS varchar(1000),
              LINK_TABLE varchar(100),
              LINK_COLUMN_SEQ varchar(100),
              LINK_COLUMN_DISPLAY varchar(100),
              HELP_TEXT_TREE_NAME varchar(300),
              NEXT_KEY varchar(300),
              SORT_ORDER numeric,
              DELETED timestamp,
              PRIMARY KEY (SECTION,KEY)
          ); " }

    { "rm_cmn_reg_reports"
          "CREATE TABLE rm_cmn_reg_reports (
              ENTERPRISE_ID numeric NOT NULL,
              REG_REPORT_ID numeric NOT NULL,
              DUE_DATE timestamp,
              DATE_APPROVED timestamp,
              DATE_GENERATED timestamp,
              DATE_SUBMITTED timestamp,
              DATE_XMIT timestamp,
              DATE_SCHEDULED timestamp,
              DATE_SUBMISSION_DETERMINED timestamp,
              AWARE_DATE timestamp,
              PRODUCT_ID numeric,
              GROUP_ID numeric,
              DETERMINED_USER_ID numeric,
              LICENSE_ID numeric,
              COUNTRY_ID numeric,
              REPORT_FORM_ID numeric,
              PDF_ID numeric,
              LANGUAGE_ID numeric,
              FOLLOWUP_NUM numeric,
              XMIT_TYPE numeric,
              CASE_REV numeric,
              PRIORITY numeric,
              STATE_ID numeric,
              OWNER_ID numeric,
              LAST_STATE_ID numeric,
              TIMEFRAME numeric,
              AUTO_SCHED numeric(1),
              AGENCY_ID numeric,
              SUBMIT_REQUIRED numeric(1),
              USER_ID numeric,
              TEMPLATE_ID numeric,
              PARENT_REPORT_ID numeric,
              PER_REPORT_FLAG numeric,
              METHOD numeric(4),
              OUTPUT_STATUS numeric(4),
              PRINT_ID numeric,
              TRANSMIT_ID numeric,
              STATUS numeric(4),
              AWARE_METHOD numeric,
              ESM_REPORT_ID numeric,
              TRACKING_NUM varchar(30),
              EVENT varchar(250),
              BODY_SYS varchar(250),
              STATUS_TEXT varchar(200),
              GENERATION_ERROR varchar(1000),
              SUBMIT_NOTES varchar(1000),
              NON_SUBMIT_REASON varchar(1000),
              DELETED timestamp,
              PROTECT numeric(1),
              COMMENT_TYPE_ID numeric(10),
              REF_TYPE_ID numeric,
              SOURCE_REPORT_ID numeric,
              DOCUMENTUM_ID varchar(200),
              MARK_AS_SUBMITTED numeric(1),
              NOTIFICATION numeric,
              DOCUMENTUM_ID_PUBLISH varchar(200),
              PROD_SEQ_NUM numeric,
              SCHEDULED_BY_ID numeric,
              REPORT_FORM_TYPE numeric,
              MESSAGE_TYPE_ID numeric,
              SUSAR numeric,
              LAST_UPDATE_TIME timestamp,
              SAFETY_DATE timestamp,
              AUTO_DISTRIBUTE_REPORTS numeric NOT NULL,
              BLIND_STUDY_PRODUCT numeric(1),
              NULLIFICATION numeric(1),
              PREV_ESM_RPT_ID numeric,
              NULLIFICATION_REASON varchar(1000),
              NO_FOLLOWUP_DOWNGRADE numeric,
              EVENT_J varchar(250),
              BODY_SYS_J varchar(250),
              RPT_COMMENT text,
              GROUP_2_COUNTRY_ADJUST_DAYS numeric,
              PRIMARY KEY (ENTERPRISE_ID,REG_REPORT_ID)
          ); " }

    { "rm_code_list_code_attributes"
          "CREATE TABLE rm_code_list_code_attributes (
              ENTERPRISE_ID numeric NOT NULL,
              CODE_LIST_ID varchar(100) NOT NULL,
              CODE numeric NOT NULL,
              PROTECTED numeric(1),
              LAST_UPDATE_TIME timestamp,
              DELETED timestamp,
              DISPLAY numeric(1),
              DATA_SOURCE numeric(1),
              PRIMARY KEY (ENTERPRISE_ID,CODE_LIST_ID,CODE)
          ); " }

    { "rm_code_list_detail_discrete"
          "CREATE TABLE rm_code_list_detail_discrete (
              ENTERPRISE_ID numeric NOT NULL,
              CODE_LIST_ID varchar(100) NOT NULL,
              DECODE_CONTEXT varchar(20) NOT NULL,
              CODE numeric NOT NULL,
              DISPLAY_VALUE varchar(1000),
              PREFERRED numeric(1),
              SORT numeric,
              LAST_UPDATE_TIME timestamp,
              PRIMARY KEY (ENTERPRISE_ID,CODE_LIST_ID,DECODE_CONTEXT,CODE)
          ); " }

    { "rm_code_list_master"
          "CREATE TABLE rm_code_list_master (
              ENTERPRISE_ID numeric NOT NULL,
              CODE_LIST_ID varchar(100) NOT NULL,
              CODE_LIST_DESC varchar(1000),
              SRC_TABLE_NAME varchar(30),
              SRC_TABLE_FILTER varchar(100),
              LAST_UPDATE_TIME timestamp,
              DATA_SOURCE numeric(1),
              PRIMARY KEY (ENTERPRISE_ID,CODE_LIST_ID)
          ); " }

    { "rm_lm_accidental_exposure"
          "CREATE TABLE rm_lm_accidental_exposure (
              ENTERPRISE_ID numeric NOT NULL,
              ID numeric NOT NULL,
              DESCRIPTION varchar(20) NOT NULL,
              DELETED timestamp,
              DISPLAY numeric,
              PROTECTED numeric,
              DESCRIPTION_J varchar(20),
              PRIMARY KEY (ENTERPRISE_ID,ID)
          ); " }

    { "rm_lm_action_item_type"
          "CREATE TABLE rm_lm_action_item_type (
              ENTERPRISE_ID numeric NOT NULL,
              ACTION_TYPE_ID numeric NOT NULL,
              ACTION_TYPE varchar(40) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              DESCRIPTION varchar(1000),
              QUERY_ACTION numeric,
              AC_ID numeric,
              USER_GROUP numeric,
              DUE_DAYS numeric,
              LETTER_PLACEHOLDR_CONTENT varchar(1000),
              ACTION_TYPE_J varchar(40),
              DESCRIPTION_J varchar(1000),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,ACTION_TYPE_ID)
          ); " }

    { "rm_lm_action_taken"
          "CREATE TABLE rm_lm_action_taken (
              ENTERPRISE_ID numeric NOT NULL,
              ACT_TAKEN_ID numeric NOT NULL,
              ACTION_TAKEN varchar(30) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              E2B_CODE numeric,
              ACTION_TAKEN_J varchar(30),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,ACT_TAKEN_ID)
          ); " }

    { "rm_lm_admin_route"
          "CREATE TABLE rm_lm_admin_route (
              ENTERPRISE_ID numeric NOT NULL,
              ADMIN_ROUTE_ID numeric NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              SHORT_NAME varchar(5),
              E2B_CODE varchar(6),
              ROUTE varchar(15) NOT NULL,
              ROUTE_DESC varchar(40),
              ROUTE_DESC_J varchar(40),
              ROUTE_J varchar(15),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,ADMIN_ROUTE_ID)
          ); " }

    { "rm_lm_age_groups"
          "CREATE TABLE rm_lm_age_groups (
              ENTERPRISE_ID numeric NOT NULL,
              AGE_GROUP_ID numeric NOT NULL,
              GROUP_NAME varchar(20) NOT NULL,
              GROUP_LOW numeric(22,7),
              GROUP_HIGH numeric(22,7),
              PROTECTED numeric,
              DISPLAY numeric,
              E2B_CODE numeric,
              GROUP_NAME_J varchar(20),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,AGE_GROUP_ID)
          ); " }

    { "rm_lm_age_units"
          "CREATE TABLE rm_lm_age_units (
              ENTERPRISE_ID numeric NOT NULL,
              AGE_UNIT_ID numeric NOT NULL,
              AGE_UNIT varchar(10) NOT NULL,
              RELATION numeric,
              PROTECTED numeric,
              DISPLAY numeric,
              E2B_CODE numeric,
              AGE_UNIT_J varchar(10),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,AGE_UNIT_ID)
          ); " }

    { "rm_lm_always_serious_term"
          "CREATE TABLE rm_lm_always_serious_term (
              ENTERPRISE_ID numeric NOT NULL,
              AST_ID numeric(22) NOT NULL,
              AST_PT_CODE varchar(50),
              AST_PT_TERM varchar(250),
              AST_LLT_CODE varchar(50),
              AST_LLT varchar(250),
              AST_HLT_CODE varchar(50),
              AST_HLT varchar(250),
              AST_HLGT_CODE varchar(50),
              AST_HLGT varchar(250),
              AST_SOC_CODE varchar(50),
              AST_SOC varchar(250),
              CODE_STATUS numeric,
              CODE_DICT numeric,
              DELETED timestamp,
              AST_CODED varchar(250),
              AST_REPTD varchar(250),
              AST_REPTD_J varchar(250),
              AST_CODED_J varchar(250),
              AST_PT_TERM_J varchar(250),
              AST_LLT_CODE_J varchar(50),
              AST_LLT_J varchar(250),
              AST_HLT_J varchar(250),
              AST_HLGT_J varchar(250),
              AST_SOC_J varchar(250),
              CODE_STATUS_J numeric,
              SYN_CODE numeric,
              SYN_CODE_J numeric,
              PRIMARY KEY (ENTERPRISE_ID,AST_ID)
          ); " }

    { "rm_lm_applications"
          "CREATE TABLE rm_lm_applications (
              APPLICATION_ID numeric NOT NULL,
              APPLICATION_DESC varchar(50) NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (APPLICATION_ID)
          ); " }

    { "rm_lm_attachment_type"
          "CREATE TABLE rm_lm_attachment_type (
              ENTERPRISE_ID numeric NOT NULL,
              ATTACHMENT_ID numeric NOT NULL,
              ATTACHMENT_DESC varchar(200),
              DISPLAY numeric(1),
              PROTECTED numeric(1),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,ATTACHMENT_ID)
          ); " }

    { "rm_lm_birth_type"
          "CREATE TABLE rm_lm_birth_type (
              ENTERPRISE_ID numeric NOT NULL,
              BIRTH_TYPE_ID numeric NOT NULL,
              BIRTH_TYPE varchar(35) NOT NULL,
              DISPLAY numeric,
              PROTECTED numeric,
              BIRTH_TYPE_J varchar(35),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,BIRTH_TYPE_ID)
          ); " }

    { "rm_lm_case_classification"
          "CREATE TABLE rm_lm_case_classification (
              ENTERPRISE_ID numeric NOT NULL,
              CLASSIFICATION_ID numeric NOT NULL,
              DESCRIPTION varchar(30) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              DESCRIPTION_J varchar(30),
              DELETED timestamp,
              E2B_CODE numeric,
              PRIMARY KEY (ENTERPRISE_ID,CLASSIFICATION_ID)
          ); " }

    { "rm_lm_causality"
          "CREATE TABLE rm_lm_causality (
              ENTERPRISE_ID numeric NOT NULL,
              CAUSALITY_ID numeric NOT NULL,
              CAUSALITY varchar(35) NOT NULL,
              REPORTABILITY numeric,
              PROTECTED numeric,
              DISPLAY numeric,
              CAUSALITY_J varchar(35),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,CAUSALITY_ID)
          ); " }

    { "rm_lm_causality_category"
          "CREATE TABLE rm_lm_causality_category (
              ENTERPRISE_ID numeric NOT NULL,
              CAUSALITY_ID numeric NOT NULL,
              CAUSALITY_MIN_SCORE numeric,
              CAUSALITY_MAX_SCORE numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,CAUSALITY_ID)
          ); " }

    { "rm_lm_causality_source"
          "CREATE TABLE rm_lm_causality_source (
              ENTERPRISE_ID numeric NOT NULL,
              SOURCE_ID numeric NOT NULL,
              SOURCE varchar(35),
              PROTECTED numeric,
              DISPLAY numeric,
              SOURCE_J varchar(35),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,SOURCE_ID)
          ); " }

    { "rm_lm_centers"
          "CREATE TABLE rm_lm_centers (
              ENTERPRISE_ID numeric NOT NULL,
              CENTER_ID numeric NOT NULL,
              CENTER_NO varchar(20),
              CENTER_NAME varchar(40),
              ADDRESS varchar(120),
              CENTER_NAME_J varchar(40),
              CENTER_NO_J varchar(20),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,CENTER_ID)
          ); " }

    { "rm_lm_classification"
          "CREATE TABLE rm_lm_classification (
              ENTERPRISE_ID numeric NOT NULL,
              CLASSIFICATION_ID numeric NOT NULL,
              CLASSIFICATION varchar(30) NOT NULL,
              DISPLAY numeric(1),
              PROTECTED numeric(1),
              E2B_ADDITIONAL_DOC numeric,
              CLASSIFICATION_J varchar(30),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,CLASSIFICATION_ID)
          ); " }

    { "rm_lm_classification_sites"
          "CREATE TABLE rm_lm_classification_sites (
              ENTERPRISE_ID numeric NOT NULL,
              SEQ_NUM numeric(22) NOT NULL,
              SITE_ID numeric(22) NOT NULL,
              CLASSIFICATION_ID numeric(22) NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,SEQ_NUM)
          ); " }

    { "rm_lm_clinical_ref_types"
          "CREATE TABLE rm_lm_clinical_ref_types (
              ENTERPRISE_ID numeric NOT NULL,
              REF_TYPE_ID numeric NOT NULL,
              REF_TYPE_DESC varchar(20),
              REG_NUM_SIZE numeric,
              PROTECTED numeric,
              REF_TYPE_DESC_J varchar(20),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,REF_TYPE_ID)
          ); " }

    { "rm_lm_condition_type"
          "CREATE TABLE rm_lm_condition_type (
              ENTERPRISE_ID numeric NOT NULL,
              CONDITION_TYPE_ID numeric NOT NULL,
              COND_TYPE varchar(20) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              DELETED timestamp,
              COND_CATEGORY numeric(1),
              COND_TYPE_J varchar(20),
              PRIMARY KEY (ENTERPRISE_ID,CONDITION_TYPE_ID)
          ); " }

    { "rm_lm_contact_type"
          "CREATE TABLE rm_lm_contact_type (
              ENTERPRISE_ID numeric NOT NULL,
              CONTACT_ID numeric NOT NULL,
              CONTACT_TYPE varchar(10) NOT NULL,
              DESCRIPTION varchar(120),
              DISPLAY numeric(4) NOT NULL,
              PROTECTED numeric(4) NOT NULL,
              CONTACT_TYPE_J varchar(10),
              DESCRIPTION_J varchar(120),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,CONTACT_ID)
          ); " }

    { "rm_lm_costart"
          "CREATE TABLE rm_lm_costart (
              ENTERPRISE_ID numeric NOT NULL,
              CODE varchar(50) NOT NULL,
              DESCRIPTION varchar(1000),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,CODE)
          ); " }

    { "rm_lm_countries"
          "CREATE TABLE rm_lm_countries (
              ENTERPRISE_ID numeric NOT NULL,
              COUNTRY_ID numeric NOT NULL,
              COUNTRY varchar(50) NOT NULL,
              A2 varchar(2),
              A3 varchar(3),
              NUM numeric,
              PROTECTED numeric,
              DELETED timestamp,
              COUNTRY_GROUP_ID numeric,
              COUNTRY_GROUP varchar(50),
              COUNTRY_J varchar(50),
              GROUP_2_COUNTRY numeric,
              PRIMARY KEY (ENTERPRISE_ID,COUNTRY_ID)
          ); " }

    { "rm_lm_datasheet"
          "CREATE TABLE rm_lm_datasheet (
              ENTERPRISE_ID numeric NOT NULL,
              DATASHEET_ID numeric NOT NULL,
              SHEET_NAME varchar(40),
              CORE_SHEET numeric,
              FAMILY_ID numeric,
              DELETED timestamp,
              REVISION numeric NOT NULL,
              ACTIVATION_DATE timestamp,
              MAX_REVISION numeric,
              NO_LOCAL_ASSESSMENT numeric,
              INCLUDE_DATASHEET_ID numeric,
              SHEET_NAME_J varchar(40),
              NOTES text,
              PRIMARY KEY (ENTERPRISE_ID,DATASHEET_ID,REVISION)
          ); " }

    { "rm_lm_datasheet_event_groups"
          "CREATE TABLE rm_lm_datasheet_event_groups (
              ENTERPRISE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              DATASHEET_ID numeric NOT NULL,
              REVISION numeric NOT NULL,
              EG_ID numeric NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,SEQ_NUM)
          ); " }

    { "rm_lm_date_ranges"
          "CREATE TABLE rm_lm_date_ranges (
              ENTERPRISE_ID numeric NOT NULL,
              RANGE_ID numeric NOT NULL,
              RANGE_NAME varchar(20) NOT NULL,
              DURATION numeric,
              FROM_DATE timestamp,
              TO_DATE timestamp,
              UNITS varchar(10),
              RANGE_NAME_J varchar(20),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,RANGE_ID)
          ); " }

    { "rm_lm_delivery_types"
          "CREATE TABLE rm_lm_delivery_types (
              ENTERPRISE_ID numeric NOT NULL,
              DELIVERY_TYPE_ID numeric NOT NULL,
              DELIVERY_TYPE varchar(30) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              DELIVERY_TYPE_J varchar(30),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,DELIVERY_TYPE_ID)
          ); " }

    { "rm_lm_device_cur_status"
          "CREATE TABLE rm_lm_device_cur_status (
              ENTERPRISE_ID numeric NOT NULL,
              STATUS_ID numeric NOT NULL,
              STATUS varchar(30),
              STATUS_J varchar(30),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,STATUS_ID)
          ); " }

    { "rm_lm_device_future_act"
          "CREATE TABLE rm_lm_device_future_act (
              ENTERPRISE_ID numeric NOT NULL,
              ACTION_ID numeric NOT NULL,
              ACTION varchar(30),
              ACTION_J varchar(30),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,ACTION_ID)
          ); " }

    { "rm_lm_device_mal_status"
          "CREATE TABLE rm_lm_device_mal_status (
              ENTERPRISE_ID numeric NOT NULL,
              STATUS_ID numeric NOT NULL,
              STATUS varchar(10),
              STATUS_J varchar(10),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,STATUS_ID)
          ); " }

    { "rm_lm_device_pre_comments"
          "CREATE TABLE rm_lm_device_pre_comments (
              ENTERPRISE_ID numeric NOT NULL,
              PRE_COMMENTS_ID numeric NOT NULL,
              DISPLAY numeric,
              PROTECTED numeric,
              DELETED timestamp,
              PRE_COMMENTS text,
              PRE_COMMENTS_J text,
              PRIMARY KEY (ENTERPRISE_ID,PRE_COMMENTS_ID)
          ); " }

    { "rm_lm_device_rpt_category"
          "CREATE TABLE rm_lm_device_rpt_category (
              ENTERPRISE_ID numeric NOT NULL,
              CATEGORY_ID numeric NOT NULL,
              CATEGORY varchar(40),
              CATEGORY_J varchar(40),
              DISPLAY numeric,
              PROTECTED numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,CATEGORY_ID)
          ); " }

    { "rm_lm_device_subcomponents"
          "CREATE TABLE rm_lm_device_subcomponents (
              ENTERPRISE_ID numeric NOT NULL,
              SUBCOMPONENT_ID numeric NOT NULL,
              SUBCOMPONENT_NAME varchar(50),
              DISPLAY numeric,
              PROTECTED numeric,
              SUBCOMPONENT_NAME_J varchar(50),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,SUBCOMPONENT_ID)
          ); " }

    { "rm_lm_device_type"
          "CREATE TABLE rm_lm_device_type (
              ENTERPRISE_ID numeric NOT NULL,
              DEVICE_TYPE_ID numeric NOT NULL,
              DEVICE_TYPE_DESC varchar(50) NOT NULL,
              DISPLAY numeric,
              PROTECTED numeric,
              DEVICE_TYPE_DESC_J varchar(50),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,DEVICE_TYPE_ID)
          ); " }

    { "rm_lm_device_use_duration"
          "CREATE TABLE rm_lm_device_use_duration (
              ENTERPRISE_ID numeric NOT NULL,
              DURATION_ID numeric NOT NULL,
              DURATION varchar(10),
              DURATION_J varchar(10),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,DURATION_ID)
          ); " }

    { "rm_lm_dev_phase"
          "CREATE TABLE rm_lm_dev_phase (
              ENTERPRISE_ID numeric NOT NULL,
              DEV_PHASE_ID numeric NOT NULL,
              DEV_PHASE varchar(50),
              DEV_PHASE_J varchar(50),
              E2B_CODE numeric(2),
              DISPLAY numeric(1),
              PROTECTED numeric(1),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,DEV_PHASE_ID)
          ); " }

    { "rm_lm_dose_frequency"
          "CREATE TABLE rm_lm_dose_frequency (
              ENTERPRISE_ID numeric NOT NULL,
              FREQ_ID numeric NOT NULL,
              FREQ varchar(15) NOT NULL,
              PROTECTED numeric,
              FREQ_VALUE numeric,
              DISPLAY numeric,
              DELETED timestamp,
              SEPARATE_DOSAGE_NUMB_E2B numeric,
              INTERVAL_DOSAGE_UNIT_E2B numeric,
              INTERVAL_DOSAGE_DEF_E2B numeric,
              FREQ_J varchar(15),
              PRIMARY KEY (ENTERPRISE_ID,FREQ_ID)
          ); " }

    { "rm_lm_dose_units"
          "CREATE TABLE rm_lm_dose_units (
              ENTERPRISE_ID numeric NOT NULL,
              UNIT_ID numeric NOT NULL,
              UNIT varchar(25) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              DOSE_UNIT_VALUE numeric,
              E2B_CODE varchar(6),
              LAB_TEST_UNIT numeric,
              DOSAGE_UNIT numeric NOT NULL,
              UNIT_J varchar(25),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,UNIT_ID)
          ); " }

    { "rm_lm_dsr_per_subrpt"
          "CREATE TABLE rm_lm_dsr_per_subrpt (
              ENTERPRISE_ID numeric NOT NULL,
              PER_RPT_TYPE numeric NOT NULL,
              SUB_RPT_ID numeric NOT NULL,
              PER_BOOKMARK_NAME varchar(255),
              TOC_NAME varchar(1000),
              BOOKMARK_NAME varchar(255),
              OVERLAY_REQD numeric,
              DISPLAY_SORT_ID numeric,
              INCL_FLAG numeric,
              PAGE_START_PIXEL numeric,
              PAGE_END_PIXEL numeric,
              SPLIT_BOOKMARK_CHILD numeric,
              PRIMARY KEY (ENTERPRISE_ID,PER_RPT_TYPE,SUB_RPT_ID)
          ); " }

    { "rm_lm_emperor"
          "CREATE TABLE rm_lm_emperor (
              ENTERPRISE_ID numeric NOT NULL,
              EMPEROR_ID numeric NOT NULL,
              EMPEROR_NAME varchar(10),
              EMPEROR_NAME_J varchar(10),
              OFFICE_DATE_J timestamp,
              DISPLAY numeric(1),
              PROTECTED numeric(1),
              DATE_ENTRY_ABBREV varchar(1),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,EMPEROR_ID)
          ); " }

    { "rm_lm_ethnicity"
          "CREATE TABLE rm_lm_ethnicity (
              ENTERPRISE_ID numeric NOT NULL,
              ETHNICITY_ID numeric NOT NULL,
              ETHNICITY varchar(40) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              ETHNICITY_J varchar(40),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,ETHNICITY_ID)
          ); " }

    { "rm_lm_evaluator_type"
          "CREATE TABLE rm_lm_evaluator_type (
              ENTERPRISE_ID numeric NOT NULL,
              EVALUATOR_TYPE_ID numeric NOT NULL,
              EVALUATOR_TYPE varchar(25) NOT NULL,
              SITE_ID numeric,
              EVALUATOR_TYPE_J varchar(25),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,EVALUATOR_TYPE_ID)
          ); " }

    { "rm_lm_event_group"
          "CREATE TABLE rm_lm_event_group (
              ENTERPRISE_ID numeric NOT NULL,
              EG_ID numeric NOT NULL,
              EVENT_GROUP_NAME varchar(250) NOT NULL,
              EVENT_GROUP_NAME_J varchar(250),
              DISPLAY numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,EG_ID)
          ); " }

    { "rm_lm_event_group_child"
          "CREATE TABLE rm_lm_event_group_child (
              ENTERPRISE_ID numeric NOT NULL,
              ID numeric NOT NULL,
              EG_ID numeric NOT NULL,
              CHILD_ID numeric NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,ID)
          ); " }

    { "rm_lm_event_group_smq"
          "CREATE TABLE rm_lm_event_group_smq (
              ENTERPRISE_ID numeric NOT NULL,
              SMQ_ID numeric NOT NULL,
              EG_ID numeric NOT NULL,
              SMQ_CODE numeric,
              DELETED timestamp,
              CODE_DICT numeric,
              PRIMARY KEY (ENTERPRISE_ID,SMQ_ID)
          ); " }

    { "rm_lm_event_group_terms"
          "CREATE TABLE rm_lm_event_group_terms (
              ENTERPRISE_ID numeric NOT NULL,
              TERM_ID numeric NOT NULL,
              EG_ID numeric NOT NULL,
              PT varchar(250),
              PT_J varchar(250),
              PT_CODE varchar(50),
              SOC varchar(250),
              SOC_J varchar(250),
              SOC_CODE varchar(50),
              HLT varchar(250),
              HLT_J varchar(250),
              HLT_CODE varchar(50),
              HLGT varchar(250),
              HLGT_J varchar(250),
              HLGT_CODE varchar(50),
              CODE_DICT numeric,
              TERM_TYPE numeric,
              LLT varchar(250),
              LLT_J varchar(250),
              LLT_CODE varchar(50),
              LLT_CODE_J varchar(50),
              CODE_STATUS numeric NOT NULL,
              CODE_STATUS_J numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,TERM_ID)
          ); " }

    { "rm_lm_evt_consequence"
          "CREATE TABLE rm_lm_evt_consequence (
              ENTERPRISE_ID numeric NOT NULL,
              EVT_CONSEQUENCE_ID numeric NOT NULL,
              PROTECTED numeric NOT NULL,
              CONSEQUENCE varchar(100),
              TERM varchar(250),
              CONSEQUENCE_J varchar(100),
              TERM_J varchar(250),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,EVT_CONSEQUENCE_ID)
          ); " }

    { "rm_lm_evt_frequency"
          "CREATE TABLE rm_lm_evt_frequency (
              ENTERPRISE_ID numeric NOT NULL,
              EVT_FREQ_ID numeric NOT NULL,
              EVT_FREQ varchar(15) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              EVT_FREQ_J varchar(15),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,EVT_FREQ_ID)
          ); " }

    { "rm_lm_evt_intensity"
          "CREATE TABLE rm_lm_evt_intensity (
              ENTERPRISE_ID numeric NOT NULL,
              EVT_INTENSITY_ID numeric NOT NULL,
              EVT_INTENSITY varchar(30) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              EVT_INTENSITY_J varchar(30),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,EVT_INTENSITY_ID)
          ); " }

    { "rm_lm_evt_nature"
          "CREATE TABLE rm_lm_evt_nature (
              ENTERPRISE_ID numeric NOT NULL,
              EVT_NATURE_ID numeric NOT NULL,
              EVT_NATURE varchar(40) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              EVT_NATURE_J varchar(40),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,EVT_NATURE_ID)
          ); " }

    { "rm_lm_evt_outcome"
          "CREATE TABLE rm_lm_evt_outcome (
              ENTERPRISE_ID numeric NOT NULL,
              EVT_OUTCOME_ID numeric NOT NULL,
              EVT_OUTCOME varchar(50) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              E2B_CODE numeric,
              EVT_OUTCOME_J varchar(50),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,EVT_OUTCOME_ID)
          ); " }

    { "rm_lm_failure_code"
          "CREATE TABLE rm_lm_failure_code (
              ENTERPRISE_ID numeric NOT NULL,
              FAILURE_CD varchar(6) NOT NULL,
              FAILURE_DESC varchar(100),
              FAILURE_CD_TYPE varchar(1),
              FAILURE_CD_GROUP varchar(16),
              DELETED timestamp,
              FAILURE_DEF text,
              PRIMARY KEY (ENTERPRISE_ID,FAILURE_CD)
          ); " }

    { "rm_lm_fetal_outcome"
          "CREATE TABLE rm_lm_fetal_outcome (
              ENTERPRISE_ID numeric NOT NULL,
              FETAL_OUTCOME_ID numeric NOT NULL,
              FETAL_OUTCOME varchar(35) NOT NULL,
              DISPLAY numeric,
              PROTECTED numeric,
              FETAL_OUTCOME_J varchar(35),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,FETAL_OUTCOME_ID)
          ); " }

    { "rm_lm_file_template_actions"
          "CREATE TABLE rm_lm_file_template_actions (
              ACTION_ID numeric NOT NULL,
              ACTION varchar(250) NOT NULL,
              MODULE_TYPE numeric NOT NULL,
              ACTION_TYPE numeric NOT NULL,
              PRIMARY KEY (ACTION_ID)
          ); " }

    { "rm_lm_formulation"
          "CREATE TABLE rm_lm_formulation (
              ENTERPRISE_ID numeric NOT NULL,
              FORMULATION_ID numeric NOT NULL,
              FORMULATION varchar(100) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              FORMULATION_J varchar(100),
              FORMULATION_SYMBOL_J varchar(5),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,FORMULATION_ID)
          ); " }

    { "rm_lm_gender"
          "CREATE TABLE rm_lm_gender (
              ENTERPRISE_ID numeric NOT NULL,
              GENDER_ID numeric NOT NULL,
              GENDER varchar(10) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              E2B_CODE numeric,
              GENDER_J varchar(10),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,GENDER_ID)
          ); " }

    { "rm_lm_hcp"
          "CREATE TABLE rm_lm_hcp (
              ENTERPRISE_ID numeric NOT NULL,
              HCP_ID numeric NOT NULL,
              HCP varchar(30) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              HCP_J varchar(30),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,HCP_ID)
          ); " }

    { "rm_lm_holiday_calendar"
          "CREATE TABLE rm_lm_holiday_calendar (
              ENTERPRISE_ID numeric NOT NULL,
              HOLIDAY_ID numeric NOT NULL,
              HOLIDAY_NAME varchar(100),
              HOLIDAY_DATE timestamp,
              DESCRIPTION varchar(200),
              PROTECTED numeric(1),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,HOLIDAY_ID)
          ); " }

    { "rm_lm_holiday_cal_countries"
          "CREATE TABLE rm_lm_holiday_cal_countries (
              ENTERPRISE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              HOLIDAY_ID numeric NOT NULL,
              COUNTRY_ID numeric NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,SEQ_NUM)
          ); " }

    { "rm_lm_improper_use"
          "CREATE TABLE rm_lm_improper_use (
              ENTERPRISE_ID numeric NOT NULL,
              IMPROPER_USE_ID numeric NOT NULL,
              IMPROPER_USE varchar(30) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              IMPROPER_USE_J varchar(30),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,IMPROPER_USE_ID)
          ); " }

    { "rm_lm_ingredients"
          "CREATE TABLE rm_lm_ingredients (
              ENTERPRISE_ID numeric NOT NULL,
              INGREDIENT_ID numeric NOT NULL,
              INGREDIENT varchar(120) NOT NULL,
              INGREDIENT_J varchar(120),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,INGREDIENT_ID)
          ); " }

    { "rm_lm_institution"
          "CREATE TABLE rm_lm_institution (
              ENTERPRISE_ID numeric NOT NULL,
              INST_ID numeric NOT NULL,
              INSTITUTION varchar(60),
              INSTITUTION_ID varchar(15),
              INSTITUTION_J varchar(60),
              PROTECTED numeric(1),
              DISPLAY numeric(1),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,INST_ID)
          ); " }

    { "rm_lm_intermediary"
          "CREATE TABLE rm_lm_intermediary (
              ENTERPRISE_ID numeric NOT NULL,
              INTERMEDIARY_ID numeric NOT NULL,
              INTERMEDIARY varchar(30) NOT NULL,
              PROTECTED numeric,
              INTERMEDIARY_J varchar(30),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,INTERMEDIARY_ID)
          ); " }

    { "rm_lm_investigators"
          "CREATE TABLE rm_lm_investigators (
              ENTERPRISE_ID numeric NOT NULL,
              CENTER_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              NAME varchar(140),
              PHONE varchar(20),
              FAX varchar(50),
              NOTES varchar(100),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,CENTER_ID,SEQ_NUM)
          ); " }

    { "rm_lm_justifications"
          "CREATE TABLE rm_lm_justifications (
              ENTERPRISE_ID numeric NOT NULL,
              JUSTIFICATION_ID numeric NOT NULL,
              FIELD_ID numeric NOT NULL,
              JUSTIFICATION varchar(1000),
              PROTECTED numeric(1),
              JUSTIFICATION_J varchar(1000),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,JUSTIFICATION_ID,FIELD_ID)
          ); " }

    { "rm_lm_keywords"
          "CREATE TABLE rm_lm_keywords (
              ENTERPRISE_ID numeric NOT NULL,
              KEYWORD_ID numeric NOT NULL,
              KEYWORD varchar(40),
              DISPLAY numeric(1),
              PROTECTED numeric(1),
              KEYWORD_J varchar(40),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,KEYWORD_ID)
          ); " }

    { "rm_lm_labeled_terms"
          "CREATE TABLE rm_lm_labeled_terms (
              ENTERPRISE_ID numeric NOT NULL,
              DATASHEET_ID numeric NOT NULL,
              LABEL_TERM_ID numeric,
              TERM_CODE varchar(40),
              CODE_DICT numeric,
              TERM_LEVEL numeric,
              LABELED_TERM varchar(250),
              DELETED timestamp,
              SEQ_NUM numeric NOT NULL,
              REVISION numeric,
              ADDED_DATE timestamp,
              CODE_STATUS numeric,
              CODE_STATUS_J numeric,
              LABELED_TERM_J varchar(250),
              TERM_LLT varchar(250),
              TERM_HLT varchar(250),
              TERM_HLGT varchar(250),
              TERM_SOC varchar(250),
              TERM_LLT_CODE varchar(50),
              TERM_HLT_CODE varchar(50),
              TERM_HLGT_CODE varchar(50),
              TERM_SOC_CODE varchar(50),
              TERM_AS_CODED varchar(250),
              TERM_AS_REPTD varchar(250),
              TERM_LLT_J varchar(250),
              TERM_HLT_J varchar(250),
              TERM_HLGT_J varchar(250),
              TERM_SOC_J varchar(250),
              TERM_LLT_CODE_J varchar(50),
              TERM_AS_CODED_J varchar(250),
              TERM_AS_REPTD_J varchar(250),
              SYN_CODE numeric,
              SYN_CODE_J numeric,
              NOTES text,
              NOTES_J text,
              EG_ID numeric,
              PRIMARY KEY (ENTERPRISE_ID,SEQ_NUM)
          ); " }

    { "rm_lm_lab_assessment"
          "CREATE TABLE rm_lm_lab_assessment (
              ENTERPRISE_ID numeric NOT NULL,
              ASSESSMENT_ID numeric NOT NULL,
              ASSESSMENT varchar(20) NOT NULL,
              DISPLAY numeric(1),
              PROTECTED numeric(1),
              ASSESSMENT_J varchar(20),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,ASSESSMENT_ID)
          ); " }

    { "rm_lm_lab_test_group"
          "CREATE TABLE rm_lm_lab_test_group (
              ENTERPRISE_ID numeric NOT NULL,
              LAB_TEST_GROUP_ID numeric NOT NULL,
              LAB_TEST_GROUP varchar(100),
              DISPLAY numeric(1),
              PROTECTED numeric(1),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,LAB_TEST_GROUP_ID)
          ); " }

    { "rm_lm_lab_test_types"
          "CREATE TABLE rm_lm_lab_test_types (
              ENTERPRISE_ID numeric NOT NULL,
              LAB_TEST_ID numeric NOT NULL,
              LAB_TEST varchar(250) NOT NULL,
              NORM_LOW varchar(10),
              NORM_HIGH varchar(10),
              TEST_LLT varchar(250),
              TEST_HLT varchar(250),
              TEST_HLGT varchar(250),
              TEST_SOC varchar(250),
              TEST_LLT_CODE varchar(50),
              TEST_PT_CODE varchar(50),
              TEST_HLT_CODE varchar(50),
              TEST_HLGT_CODE varchar(50),
              TEST_SOC_CODE varchar(50),
              DELETED timestamp,
              CODE_STATUS numeric,
              CODE_DICT numeric,
              TEST_CODED varchar(250),
              TEST_REPTD varchar(250),
              CODE_STATUS_J numeric,
              LAB_TEST_J varchar(250),
              TEST_HLGT_J varchar(250),
              TEST_HLT_J varchar(250),
              TEST_LLT_CODE_J varchar(50),
              TEST_LLT_J varchar(250),
              TEST_SOC_J varchar(250),
              TEST_REPTD_J varchar(250),
              TEST_CODED_J varchar(250),
              SYN_CODE numeric,
              SYN_CODE_J numeric,
              PRIMARY KEY (ENTERPRISE_ID,LAB_TEST_ID)
          ); " }

    { "rm_lm_lab_test_type_groups"
          "CREATE TABLE rm_lm_lab_test_type_groups (
              ENTERPRISE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              LAB_TEST_ID numeric NOT NULL,
              LAB_TEST_GROUP_ID numeric NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,SEQ_NUM)
          ); " }

    { "rm_lm_languages"
          "CREATE TABLE rm_lm_languages (
              ENTERPRISE_ID numeric NOT NULL,
              LANGUAGE_ID numeric NOT NULL,
              LANGUAGE varchar(40),
              ENABLED numeric,
              LANGUAGE_J varchar(40),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,LANGUAGE_ID)
          ); " }

    { "rm_lm_language_data"
          "CREATE TABLE rm_lm_language_data (
              ENTERPRISE_ID numeric NOT NULL,
              ID numeric NOT NULL,
              FIELD_ID numeric NOT NULL,
              LANGUAGE_ID numeric NOT NULL,
              DELETED timestamp,
              TEXT text,
              PRIMARY KEY (ENTERPRISE_ID,ID,FIELD_ID,LANGUAGE_ID)
          ); " }

    { "rm_lm_license"
          "CREATE TABLE rm_lm_license (
              ENTERPRISE_ID numeric NOT NULL,
              LICENSE_ID numeric NOT NULL,
              TRADE_NAME varchar(70),
              MANUFACTURER_ID numeric,
              COUNTRY_ID numeric,
              LICENSE_TYPE_ID numeric,
              LIC_NUMBER varchar(40),
              AWARD_DATE timestamp,
              WITHDRAW_DATE timestamp,
              DRL_ID varchar(45),
              BIOLOGIC numeric,
              REG_RPT_RULES_ID numeric,
              USE_AWARD_IBD_DATE numeric,
              ACTIVE_MOIETY numeric,
              DATASHEET_ID numeric,
              GROUP_ID numeric,
              COMPANY_ITEM_NO varchar(40),
              PRODUCT_INFO_LOCATION varchar(400),
              DELETED timestamp,
              SINGLE_USE numeric,
              COMBINATION_PRODUCT numeric,
              OTC_PRODUCT numeric NOT NULL,
              PMA varchar(20),
              NOMENCLATURE numeric(5),
              MEDICAL_DEVICE_INFO_ID numeric,
              CTPR_GROUP_NAME varchar(50),
              COMMENTS varchar(1000),
              MHLW_RE_EXAMINATION_DATE timestamp,
              CLINICAL_COMPOUND_NUMBER varchar(70),
              TRADE_NAME_J varchar(70),
              COMMENTS_J varchar(1000),
              EXCLUDE_RPT_CANDIDATE numeric(1),
              PMDA_CLASS_ID_1 numeric,
              PMDA_CLASS_ID_2 numeric,
              PMDA_CLASS_ID_3 numeric,
              PRIMARY KEY (ENTERPRISE_ID,LICENSE_ID)
          ); " }

    { "rm_lm_license_types"
          "CREATE TABLE rm_lm_license_types (
              LICENSE_TYPE_ID numeric NOT NULL,
              LICENSE_TYPE varchar(30),
              LICENSE_TYPE_J varchar(30),
              DELETED timestamp,
              PRIMARY KEY (LICENSE_TYPE_ID)
          ); " }

    { "rm_lm_lic_category"
          "CREATE TABLE rm_lm_lic_category (
              ENTERPRISE_ID numeric NOT NULL,
              CATEGORY_ID numeric NOT NULL,
              CATEGORY_J varchar(200),
              E2B_CODE numeric,
              DISPLAY numeric,
              CATEGORY varchar(200),
              PROTECTED numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,CATEGORY_ID)
          ); " }

    { "rm_lm_lic_countries"
          "CREATE TABLE rm_lm_lic_countries (
              ENTERPRISE_ID numeric NOT NULL,
              COUNTRY_ID numeric NOT NULL,
              LICENSE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,COUNTRY_ID,LICENSE_ID,SEQ_NUM)
          ); " }

    { "rm_lm_lic_products"
          "CREATE TABLE rm_lm_lic_products (
              ENTERPRISE_ID numeric NOT NULL,
              LICENSE_ID numeric NOT NULL,
              PRODUCT_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              DISPLAY numeric(4),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,LICENSE_ID,PRODUCT_ID,SEQ_NUM)
          ); " }

    { "rm_lm_lic_report_rules"
          "CREATE TABLE rm_lm_lic_report_rules (
              ENTERPRISE_ID numeric NOT NULL,
              LICENSE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              FREQ numeric,
              START_MONTH numeric,
              END_MONTH numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,LICENSE_ID,SEQ_NUM)
          ); " }

    { "rm_lm_listedness"
          "CREATE TABLE rm_lm_listedness (
              ENTERPRISE_ID numeric NOT NULL,
              LISTEDNESS_ID numeric NOT NULL,
              LISTEDNESS varchar(20),
              PROTECTED numeric,
              DISPLAY numeric,
              DELETED timestamp,
              LABELEDNESS varchar(20),
              LABELEDNESS_J varchar(20),
              LISTEDNESS_J varchar(20),
              PRIMARY KEY (ENTERPRISE_ID,LISTEDNESS_ID)
          ); " }

    { "rm_lm_literature_type"
          "CREATE TABLE rm_lm_literature_type (
              ENTERPRISE_ID numeric NOT NULL,
              ID numeric NOT NULL,
              TYPE varchar(40),
              TYPE_J varchar(40),
              DISPLAY numeric(1),
              PROTECTED numeric(1),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,ID)
          ); " }

    { "rm_lm_lit_citations"
          "CREATE TABLE rm_lm_lit_citations (
              ENTERPRISE_ID numeric NOT NULL,
              LITERATURE_ID numeric NOT NULL,
              JOURNAL varchar(80),
              AUTHOR varchar(200),
              TITLE varchar(250),
              VOL varchar(15),
              YEAR varchar(4),
              PGS varchar(30),
              AUTHOR_J varchar(200),
              JOURNAL_J varchar(80),
              TITLE_J varchar(250),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,LITERATURE_ID)
          ); " }

    { "rm_lm_location"
          "CREATE TABLE rm_lm_location (
              ENTERPRISE_ID numeric NOT NULL,
              LOCATION_ID numeric NOT NULL,
              LOCATION_DESC varchar(20) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              LOCATION_DESC_J varchar(20),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,LOCATION_ID)
          ); " }

    { "rm_lm_malfunction_type"
          "CREATE TABLE rm_lm_malfunction_type (
              ENTERPRISE_ID numeric NOT NULL,
              MALFUNCTION_TYPE_ID numeric NOT NULL,
              MALFUNCTION_TYPE varchar(100) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              ENABLE_DATE numeric,
              MALFUNCTION_TYPE_J varchar(100),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,MALFUNCTION_TYPE_ID)
          ); " }

    { "rm_lm_manufacturer"
          "CREATE TABLE rm_lm_manufacturer (
              ENTERPRISE_ID numeric NOT NULL,
              MANUFACTURER_ID numeric NOT NULL,
              MANU_NAME varchar(40) NOT NULL,
              ADDRESS varchar(120),
              CITY varchar(30),
              STATE varchar(10),
              POSTAL_CODE varchar(10),
              COUNTRY varchar(50),
              CONTACT varchar(40),
              PHONE varchar(20),
              REG_NO varchar(10),
              DELETED timestamp,
              FAX varchar(50),
              COUNTRY_ID numeric,
              ADDRESS_J varchar(120),
              CITY_J varchar(30),
              CONTACT_J varchar(40),
              MANU_NAME_J varchar(40),
              STATE_J varchar(10),
              PRIMARY KEY (ENTERPRISE_ID,MANUFACTURER_ID)
          ); " }

    { "rm_lm_medical_device_info"
          "CREATE TABLE rm_lm_medical_device_info (
              ENTERPRISE_ID numeric NOT NULL,
              MEDICAL_DEVICE_INFO_ID numeric NOT NULL,
              MEDICAL_DEVICE_INFO varchar(200),
              PROTECTED numeric(1) NOT NULL,
              DISPLAY numeric(1) NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,MEDICAL_DEVICE_INFO_ID)
          ); " }

    { "rm_lm_medical_status"
          "CREATE TABLE rm_lm_medical_status (
              ENTERPRISE_ID numeric NOT NULL,
              MED_STATUS_ID numeric NOT NULL,
              SEQ_NUM numeric,
              LABEL varchar(40) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              LABEL_J varchar(40),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,MED_STATUS_ID)
          ); " }

    { "rm_lm_mfr_eval_reason"
          "CREATE TABLE rm_lm_mfr_eval_reason (
              ENTERPRISE_ID numeric NOT NULL,
              MFR_EVAL_ID numeric NOT NULL,
              MFR_EVAL_REASON varchar(100),
              DISPLAY numeric,
              PROTECTED numeric,
              DELETED timestamp,
              MFR_EVAL_CODE numeric NOT NULL,
              MFR_EVAL_REASON_J varchar(100),
              PRIMARY KEY (ENTERPRISE_ID,MFR_EVAL_ID)
          ); " }

    { "rm_lm_occupations"
          "CREATE TABLE rm_lm_occupations (
              ENTERPRISE_ID numeric NOT NULL,
              OCCUPATION_ID numeric NOT NULL,
              OCCUPATION varchar(40) NOT NULL,
              ICH numeric,
              OCCUPATION_J varchar(40),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,OCCUPATION_ID)
          ); " }

    { "rm_lm_pack_units"
          "CREATE TABLE rm_lm_pack_units (
              ENTERPRISE_ID numeric NOT NULL,
              ID numeric NOT NULL,
              DESCRIPTION varchar(20) NOT NULL,
              DISPLAY numeric(4),
              PROTECTED numeric(4),
              DESCRIPTION_J varchar(20),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,ID)
          ); " }

    { "rm_lm_pf_ingredients"
          "CREATE TABLE rm_lm_pf_ingredients (
              ENTERPRISE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              FAMILY_ID numeric NOT NULL,
              INGREDIENT_ID numeric NOT NULL,
              ACTIVE numeric(4),
              DELETED timestamp,
              SORT_ID numeric,
              PRIMARY KEY (ENTERPRISE_ID,SEQ_NUM)
          ); " }

    { "rm_lm_pmda_device_clasfication"
          "CREATE TABLE rm_lm_pmda_device_clasfication (
              ENTERPRISE_ID numeric NOT NULL,
              CLASSIFICATION_ID numeric NOT NULL,
              CLASSIFICATION_TYPE numeric,
              CLASSIFICATION varchar(50),
              CLASSIFICATION_J varchar(50),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,CLASSIFICATION_ID)
          ); " }

    { "rm_lm_product"
          "CREATE TABLE rm_lm_product (
              ENTERPRISE_ID numeric NOT NULL,
              PRODUCT_ID numeric NOT NULL,
              PROD_NAME varchar(70),
              FAMILY_ID numeric,
              FORMULATION_ID numeric,
              MANUFACTURER_ID numeric,
              CONCENTRATION varchar(10),
              CONC_UNIT_ID numeric,
              INDICATION_ID varchar(50),
              CODE_DICT numeric,
              INDICATION_TEXT varchar(250),
              INTL_BIRTH_DATE timestamp,
              MODEL_NO varchar(20),
              DRL_ID varchar(45),
              DRUG_CODE varchar(20),
              DELETED timestamp,
              PROD_GENERIC_NAME text,
              PROD_GENERIC_NAME_J text,
              IND_LLT_CODE varchar(50),
              IND_LLT varchar(250),
              IND_HLT_CODE varchar(50),
              IND_HLT varchar(250),
              IND_HLGT_CODE varchar(50),
              IND_HLGT varchar(250),
              IND_SOC_CODE varchar(50),
              IND_SOC varchar(250),
              IND_SYN_CODE numeric,
              IND_CODE_STATUS numeric,
              MEDICINAL_PROD_ID varchar(10),
              PROD_NAME_ABBRV varchar(5),
              DEVICE_CODE varchar(50),
              PSUR_GROUP_NAME varchar(50),
              COMMENTS varchar(1000),
              IND_CODED varchar(250),
              IND_REPTD varchar(250),
              DRL_ID_J varchar(45),
              DRUG_CODE_J varchar(20),
              DRUG_CODE_TYPE_J numeric,
              INDICATION_TEXT_J varchar(250),
              IND_CODE_STATUS_J numeric,
              IND_HLGT_J varchar(250),
              IND_HLT_J varchar(250),
              IND_LLT_CODE_J varchar(50),
              IND_LLT_J varchar(250),
              IND_SOC_J varchar(250),
              PROD_NAME_J varchar(70),
              IND_SYN_CODE_J numeric,
              IND_CODED_J varchar(250),
              IND_REPTD_J varchar(250),
              DEV_INTL_BIRTH_DATE timestamp,
              COMMENTS_J varchar(1000),
              PRIMARY KEY (ENTERPRISE_ID,PRODUCT_ID)
          ); " }

    { "rm_lm_product_concentrations"
          "CREATE TABLE rm_lm_product_concentrations (
              ENTERPRISE_ID numeric NOT NULL,
              PRODUCT_ID numeric NOT NULL,
              INGREDIENT_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              CONCENTRATION varchar(10),
              CONC_UNIT_ID numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,PRODUCT_ID,INGREDIENT_ID,SEQ_NUM)
          ); " }

    { "rm_lm_product_family"
          "CREATE TABLE rm_lm_product_family (
              ENTERPRISE_ID numeric NOT NULL,
              FAMILY_ID numeric NOT NULL,
              NAME varchar(40),
              PRIMARY_VIEW numeric,
              DELETED timestamp,
              PRODUCT_GROUP_ID numeric,
              COMMENTS varchar(1000),
              SEARCH_EQUATION_NUMBER varchar(10),
              NAME_J varchar(40),
              COMMENTS_J varchar(1000),
              PRIMARY KEY (ENTERPRISE_ID,FAMILY_ID)
          ); " }

    { "rm_lm_product_group"
          "CREATE TABLE rm_lm_product_group (
              ENTERPRISE_ID numeric NOT NULL,
              PRODUCT_GROUP_ID numeric NOT NULL,
              NAME varchar(70),
              DISPLAY numeric,
              NAME_J varchar(70),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,PRODUCT_GROUP_ID)
          ); " }

    { "rm_lm_product_lots"
          "CREATE TABLE rm_lm_product_lots (
              ENTERPRISE_ID numeric NOT NULL,
              LOT_ID numeric NOT NULL,
              PRODUCT_ID numeric NOT NULL,
              LOT_NO varchar(35),
              EXT_LOT_ID varchar(50),
              EXPIRATION_DATE timestamp,
              EXPIRATION_DATE_RES numeric,
              EXPIRATION_DATE_PARTIAL varchar(20),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,LOT_ID)
          ); " }

    { "rm_lm_protocols"
          "CREATE TABLE rm_lm_protocols (
              ENTERPRISE_ID numeric NOT NULL,
              PROTOCOL_ID numeric NOT NULL,
              PROTOCOL_DESC varchar(40),
              DELETED timestamp,
              DISPLAY numeric,
              PROTOCOL_DESC_J varchar(40),
              PRIMARY KEY (ENTERPRISE_ID,PROTOCOL_ID)
          ); " }

    { "rm_lm_purchased_with"
          "CREATE TABLE rm_lm_purchased_with (
              ENTERPRISE_ID numeric NOT NULL,
              PURCHASE_ID numeric NOT NULL,
              PURCHASE varchar(40),
              PURCHASE_J varchar(40),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,PURCHASE_ID)
          ); " }

    { "rm_lm_recipient"
          "CREATE TABLE rm_lm_recipient (
              ENTERPRISE_ID numeric NOT NULL,
              RECIPIENT_ID numeric NOT NULL,
              NAME varchar(30),
              TITLE varchar(50),
              ADDRESS varchar(50),
              CITY varchar(30),
              PHONE varchar(15),
              EMAIL varchar(100),
              STATE varchar(10),
              POSTAL_CODE varchar(10),
              FAX varchar(50),
              PREFERRED numeric(1),
              COUNTRY varchar(50),
              COUNTRY_ID numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,RECIPIENT_ID)
          ); " }

    { "rm_lm_ref_types"
          "CREATE TABLE rm_lm_ref_types (
              ENTERPRISE_ID numeric NOT NULL,
              REF_TYPE_ID numeric NOT NULL,
              TYPE_DESC varchar(25) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              TYPE_DESC_J varchar(25),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,REF_TYPE_ID)
          ); " }

    { "rm_lm_regulatory_contact"
          "CREATE TABLE rm_lm_regulatory_contact (
              ENTERPRISE_ID numeric NOT NULL,
              AGENCY_ID numeric NOT NULL,
              AGENCY_NAME varchar(40),
              REG_NUM varchar(10),
              CONTACT_NAME varchar(40),
              TITLE varchar(40),
              ADDRESS varchar(120),
              CITY varchar(35),
              STATE varchar(40),
              POST_CODE varchar(15),
              COUNTRY numeric,
              PHONE varchar(20),
              FAX varchar(50),
              EMAIL varchar(100),
              OFF_LINE numeric,
              PREF_METHOD numeric,
              DEPARTMENT varchar(60),
              FIRST_NAME varchar(35),
              MIDDLE_NAME varchar(15),
              TEL_EXT varchar(10),
              TEL_CONT_CODE varchar(3),
              FAX_EXT varchar(10),
              FAX_CONT_CODE varchar(3),
              MAX_E2B_RPT numeric,
              VIEW_PRINT_METHOD numeric,
              RECEIVER_TYPE numeric,
              SENDER_TYPE numeric,
              CONT_COUNTRY_ID numeric,
              CONT_COMPANY_NAME varchar(40),
              CONT_NAME varchar(40),
              CONT_TITLE varchar(40),
              CONT_ADDRESS varchar(120),
              CONT_CITY varchar(35),
              CONT_STATE varchar(40),
              CONT_POSTCODE varchar(15),
              CONT_PHONE varchar(20),
              CONT_FAX varchar(50),
              CONT_DEPARTMENT varchar(60),
              CONT_FIRST_NAME varchar(35),
              CONT_MIDDLE_NAME varchar(15),
              CONT_TEL_EXT varchar(10),
              CONT_TEL_CONT_CODE varchar(3),
              CONT_FAX_EXT varchar(10),
              CONT_FAX_CONT_CODE varchar(3),
              SENDER_EMAIL varchar(100),
              FAX_MAIN varchar(50),
              TERM_SELECTION numeric,
              FAX_COVER varchar(255),
              RULE_MARKETED numeric(2) NOT NULL,
              RULE_INVESTIGATIONAL numeric(2) NOT NULL,
              REPORTS_PER_EMAIL numeric(10),
              SINGLE_ATTACHMENT numeric(1),
              EMAIL_BODY_TYPE numeric(1),
              DELETED timestamp,
              CONT_LAB_CODE varchar(30),
              CONTACT_TYPE numeric(1),
              AWARE_SELECTION_ID numeric(1),
              SUSAR_REPORTING numeric,
              SMTP_FROM_EMAIL varchar(100),
              SMTP_CC_EMAIL varchar(1000),
              SMTP_BCC_EMAIL varchar(1000),
              SMTP_READ_RECEIPT_FLAG numeric,
              SMTP_DELIVERY_RECEIPT_FLAG numeric,
              AUTO_ACCEPT_ICSR numeric NOT NULL,
              TRANSMIT_E2B_ATTACHMENT numeric NOT NULL,
              SUPPRESS_DUPLICATE_REPORTS numeric,
              SUPPRESS_LICENSE_TYPE numeric,
              AUTO_ACCEPT_ICSR_TYPE numeric(1),
              USE_JPN_AWARE_DATE varchar(1),
              MULT_RPT_MKT_DRUGS varchar(1),
              MULT_RPT_INVEST_DRUGS varchar(1),
              ADJUST_DUE_DT_FOR_HOLIDAYS numeric(1),
              AGENCY_NAME_J varchar(40),
              CONT_COMPANY_NAME_J varchar(40),
              EMAIL_BODY text,
              FAX_HEADER_J numeric,
              PRIMARY KEY (ENTERPRISE_ID,AGENCY_ID)
          ); " }

    { "rm_lm_relation"
          "CREATE TABLE rm_lm_relation (
              ENTERPRISE_ID numeric NOT NULL,
              ID numeric NOT NULL,
              DESCRIPTION varchar(50),
              DESCRIPTION_J varchar(50),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,ID)
          ); " }

    { "rm_lm_reportable_prod_keyword"
          "CREATE TABLE rm_lm_reportable_prod_keyword (
              ENTERPRISE_ID numeric NOT NULL,
              KEYWORD_ID numeric NOT NULL,
              KEYWORD varchar(100),
              FAMILY_ID numeric,
              ACTIVATE numeric(1),
              PROTECTED numeric(1),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,KEYWORD_ID)
          ); " }

    { "rm_lm_reported"
          "CREATE TABLE rm_lm_reported (
              ENTERPRISE_ID numeric NOT NULL,
              ID numeric NOT NULL,
              DESCRIPTION varchar(50),
              DESCRIPTION_J varchar(50),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,ID)
          ); " }

    { "rm_lm_reporter"
          "CREATE TABLE rm_lm_reporter (
              ENTERPRISE_ID numeric NOT NULL,
              REPORTER_ID numeric NOT NULL,
              CENTER_ID numeric,
              PREFIX varchar(50),
              FIRST_NAME varchar(35),
              LAST_NAME varchar(50),
              OCCUPATION_ID numeric,
              CUST_RPT_ID varchar(20),
              ADDRESS varchar(120),
              CITY varchar(35),
              STATE varchar(40),
              ZIP varchar(15),
              COUNTRY varchar(50),
              PHONE varchar(20),
              ALT_PHONE varchar(20),
              FAX varchar(50),
              EMAIL varchar(100),
              HEALTHCARE_PROF numeric,
              RPTR_TYPE_ID numeric,
              SUFFIX varchar(15),
              RECIPIENT numeric(1),
              PREFERRED numeric(1),
              ADDRESS_J varchar(120),
              SUFFIX_J varchar(15),
              DELETED timestamp,
              DEPARTMENT varchar(60),
              DEPARTMENT_J varchar(60),
              COUNTRY_ID numeric,
              CITY_J varchar(35),
              FIRST_NAME_J varchar(35),
              LAST_NAME_J varchar(50),
              PREFIX_J varchar(50),
              STATE_J varchar(40),
              PRIMARY KEY (ENTERPRISE_ID,REPORTER_ID)
          ); " }

    { "rm_lm_reporter_institution"
          "CREATE TABLE rm_lm_reporter_institution (
              ENTERPRISE_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              INST_ID numeric NOT NULL,
              REPORTER_ID numeric NOT NULL,
              SORT_ID numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,SEQ_NUM)
          ); " }

    { "rm_lm_reporter_type"
          "CREATE TABLE rm_lm_reporter_type (
              ENTERPRISE_ID numeric NOT NULL,
              RPTR_TYPE_ID numeric NOT NULL,
              REPORTER_TYPE varchar(30) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              E2B_CODE numeric,
              REPORTER_TYPE_J varchar(30),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,RPTR_TYPE_ID)
          ); " }

    { "rm_lm_report_build_status"
          "CREATE TABLE rm_lm_report_build_status (
              STATUS_ID numeric NOT NULL,
              STATUS_NAME varchar(100) NOT NULL,
              REPORT_TYPE numeric NOT NULL,
              STATUS_NAME_J varchar(100),
              PRIMARY KEY (STATUS_ID,REPORT_TYPE)
          ); " }

    { "rm_lm_report_forms"
          "CREATE TABLE rm_lm_report_forms (
              ENTERPRISE_ID numeric NOT NULL,
              REPORT_FORM_ID numeric NOT NULL,
              FORM_DESC varchar(200) NOT NULL,
              AGENCY_ID numeric,
              RPT_TYPE numeric,
              HIDDEN numeric,
              FORM_CATEGORY numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,REPORT_FORM_ID)
          ); " }

    { "rm_lm_report_media"
          "CREATE TABLE rm_lm_report_media (
              ENTERPRISE_ID numeric NOT NULL,
              MEDIA_ID numeric NOT NULL,
              MEDIA varchar(30) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              MEDIA_J varchar(30),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,MEDIA_ID)
          ); " }

    { "rm_lm_report_type"
          "CREATE TABLE rm_lm_report_type (
              ENTERPRISE_ID numeric NOT NULL,
              RPT_TYPE_ID numeric NOT NULL,
              REPORT_TYPE varchar(30) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              INCL_LIT numeric,
              INCL_TRIAL numeric,
              E2B_CODE numeric,
              ABRV varchar(3),
              INVESTIGATIONAL numeric,
              REPORT_TYPE_J varchar(30),
              DELETED timestamp,
              INCL_RESEARCH numeric NOT NULL,
              PRIMARY KEY (ENTERPRISE_ID,RPT_TYPE_ID)
          ); " }

    { "rm_lm_rpt_category"
          "CREATE TABLE rm_lm_rpt_category (
              ENTERPRISE_ID numeric NOT NULL,
              RPT_CATEGORY_ID numeric NOT NULL,
              RPT_CATEGORY varchar(3),
              REPORT_FORM_ID numeric,
              DESCRIPTION_J varchar(200),
              E2B_CODE numeric,
              DISPLAY numeric,
              DESCRIPTION varchar(200),
              PROTECTED numeric,
              CATEGORY_FILTER numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,RPT_CATEGORY_ID)
          ); " }

    { "rm_lm_severity"
          "CREATE TABLE rm_lm_severity (
              SEVERITY_ID numeric NOT NULL,
              SEVERITY varchar(30) NOT NULL,
              PROTECTED numeric,
              DISPLAY numeric,
              DELETED timestamp,
              PRIMARY KEY (SEVERITY_ID)
          ); " }

    { "rm_lm_sites"
          "CREATE TABLE rm_lm_sites (
              ENTERPRISE_ID numeric NOT NULL,
              SITE_ID numeric NOT NULL,
              SITE_DESC varchar(40),
              SITE_ABRV varchar(4),
              DELETED timestamp,
              LAM_SITE numeric,
              PROTECT_PATIENT numeric,
              PROTECT_REPORTER numeric,
              APPROVED_REPORTS numeric(1),
              PROCESS_LAM_ASSESS numeric NOT NULL,
              INTAKE_FOLDER_ID numeric,
              PRIMARY KEY (ENTERPRISE_ID,SITE_ID)
          ); " }

    { "rm_lm_studies"
          "CREATE TABLE rm_lm_studies (
              ENTERPRISE_ID numeric NOT NULL,
              STUDY_KEY numeric NOT NULL,
              ALERT_AC_ID numeric,
              STUDY_NUM varchar(35),
              PROTOCOL_NUM varchar(40),
              OTHER_NUM varchar(20),
              STUDY_TYPE numeric,
              UNBLIND_OK numeric(1),
              ENCODING numeric(4),
              DRUG_AUTO numeric(4),
              DRUG_DICT varchar(20),
              EVENT_AUTO numeric(4),
              EVENT_DICT varchar(20),
              EVENT_NO_MANUAL numeric(4),
              EVENT_CLOSURE numeric(4),
              CLASSIFICATION_ID numeric,
              TEMPLATE numeric(1),
              RULES_TEMPLATE_ID numeric,
              ID_PROTOCOL numeric,
              STUDY_ABBREV varchar(5),
              DELETED timestamp,
              COMMENTS varchar(1000),
              DEV_PHASE_ID numeric,
              CASE_UNDER_ADMIN_J numeric,
              STUDY_NUM_J varchar(35),
              TARGET_DISEASE_J varchar(500),
              CLIN_COMP_NUM_J varchar(70),
              STUDY_DESC text,
              STUDY_DESC_J text,
              CENTRAL_EVENT numeric,
              COMMENTS_J varchar(1000),
              PRIMARY KEY (ENTERPRISE_ID,STUDY_KEY)
          ); " }

    { "rm_lm_study_centers"
          "CREATE TABLE rm_lm_study_centers (
              ENTERPRISE_ID numeric NOT NULL,
              CENTER_ID numeric NOT NULL,
              STUDY_KEY numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,CENTER_ID,STUDY_KEY,SEQ_NUM)
          ); " }

    { "rm_lm_study_countries"
          "CREATE TABLE rm_lm_study_countries (
              ENTERPRISE_ID numeric NOT NULL,
              STUDY_KEY numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              COUNTRY_ID numeric NOT NULL,
              INACTIVE_DATE timestamp,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,STUDY_KEY,SEQ_NUM)
          ); " }

    { "rm_lm_study_products"
          "CREATE TABLE rm_lm_study_products (
              ENTERPRISE_ID numeric NOT NULL,
              PRODUCT_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              LICENSE_ID numeric,
              MEDICINAL_PROD_ID varchar(10),
              DELETED timestamp,
              BLINDED numeric NOT NULL,
              COHORT_ID numeric NOT NULL,
              PROD_TYPE_ID numeric,
              PRIMARY KEY (ENTERPRISE_ID,COHORT_ID,PRODUCT_ID,SEQ_NUM)
          ); " }

    { "rm_lm_study_references"
          "CREATE TABLE rm_lm_study_references (
              ENTERPRISE_ID numeric NOT NULL,
              STUDY_KEY numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              SORT_ID numeric,
              REF_TYPE_ID numeric,
              REFERENCE varchar(40),
              COUNTRY_ID numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,STUDY_KEY,SEQ_NUM)
          ); " }

    { "rm_lm_study_susar"
          "CREATE TABLE rm_lm_study_susar (
              ENTERPRISE_ID numeric NOT NULL,
              STUDY_KEY numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              ALWAYS_REPORT numeric NOT NULL,
              COUNTRY_ID numeric NOT NULL,
              LICENSE_TYPE_ID numeric NOT NULL,
              AGENCY_ID numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,STUDY_KEY,SEQ_NUM)
          ); " }

    { "rm_lm_study_types"
          "CREATE TABLE rm_lm_study_types (
              STUDY_TYPE_ID numeric NOT NULL,
              STUDY_TYPE varchar(15),
              PROTECTED numeric,
              DISPLAY numeric,
              STUDY_TYPE_J varchar(15),
              DELETED timestamp,
              PRIMARY KEY (STUDY_TYPE_ID)
          ); " }

    { "rm_lm_udf_ddl_values"
          "CREATE TABLE rm_lm_udf_ddl_values (
              ENTERPRISE_ID numeric NOT NULL,
              ID numeric NOT NULL,
              FIELD_ID numeric NOT NULL,
              DESCRIPTION varchar(100),
              DESCRIPTION_J varchar(100),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,ID)
          ); " }

    { "rm_lm_unblinding_status"
          "CREATE TABLE rm_lm_unblinding_status (
              ENTERPRISE_ID numeric NOT NULL,
              STATUS_ID numeric NOT NULL,
              UNBLINDING_STATUS varchar(30),
              DISPLAY numeric(1),
              PROTECTED numeric(1),
              UNBLINDING_STATUS_J varchar(30),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,STATUS_ID)
          ); " }

    { "rm_lm_vaccinated_at"
          "CREATE TABLE rm_lm_vaccinated_at (
              ENTERPRISE_ID numeric NOT NULL,
              VACC_AT_ID numeric NOT NULL,
              LOCATION varchar(40),
              LOCATION_J varchar(40),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,VACC_AT_ID)
          ); " }

    { "rm_lm_vaccines"
          "CREATE TABLE rm_lm_vaccines (
              VACC_ID numeric NOT NULL,
              VACCINE_CODE varchar(6),
              VACCINE_DESC varchar(100),
              DELETED timestamp,
              PRIMARY KEY (VACC_ID)
          ); " }

    { "rm_lm_vaers_rpt_block"
          "CREATE TABLE rm_lm_vaers_rpt_block (
              ENTERPRISE_ID numeric NOT NULL,
              BLOCK_ID numeric NOT NULL,
              BLOCK_NAME varchar(20),
              BLOCK_NAME_J varchar(20),
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,BLOCK_ID)
          ); " }

    { "rm_meddra_hlgt_hlt_comp"
          "CREATE TABLE rm_meddra_hlgt_hlt_comp (
              GLOBAL_DICT_ID numeric NOT NULL,
              HLGT_CODE numeric NOT NULL,
              HLT_CODE numeric NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (GLOBAL_DICT_ID,HLGT_CODE,HLT_CODE)
          ); " }

    { "rm_meddra_hlg_pref_term"
          "CREATE TABLE rm_meddra_hlg_pref_term (
              GLOBAL_DICT_ID numeric NOT NULL,
              HLGT_CODE numeric NOT NULL,
              HLGT_NAME varchar(120),
              HLGT_WHO_ART_CODE varchar(7),
              HLGT_HARTS_CODE numeric,
              HLGT_COSTART_FG varchar(21),
              HLGT_ICD9_CODE varchar(8),
              HLGT_ICD9CM_CODE varchar(8),
              HLGT_ICD10_CODE varchar(8),
              HLGT_J_ART_CODE varchar(6),
              DELETED timestamp,
              PRIMARY KEY (GLOBAL_DICT_ID,HLGT_CODE)
          ); " }

    { "rm_meddra_hlg_pref_term_j"
          "CREATE TABLE rm_meddra_hlg_pref_term_j (
              GLOBAL_DICT_ID numeric NOT NULL,
              HLGT_CODE numeric NOT NULL,
              HLGT_KANJI varchar(400),
              HLGT_KANA varchar(400),
              HLGT_KANA1 varchar(400),
              HLGT_KANA2 varchar(400),
              PRIMARY KEY (GLOBAL_DICT_ID,HLGT_CODE)
          ); " }

    { "rm_meddra_hlt_pref_comp"
          "CREATE TABLE rm_meddra_hlt_pref_comp (
              GLOBAL_DICT_ID numeric NOT NULL,
              HLT_CODE numeric NOT NULL,
              PT_CODE numeric NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (GLOBAL_DICT_ID,HLT_CODE,PT_CODE)
          ); " }

    { "rm_meddra_hl_pref_term"
          "CREATE TABLE rm_meddra_hl_pref_term (
              GLOBAL_DICT_ID numeric NOT NULL,
              HLT_CODE numeric NOT NULL,
              HLT_NAME varchar(120),
              HLT_WHO_ART_CODE varchar(7),
              HLT_HARTS_CODE numeric,
              HLT_COSTART_FG varchar(21),
              HLT_ICD9_CODE varchar(8),
              HLT_ICD9CM_CODE varchar(8),
              HLT_ICD10_CODE varchar(8),
              HLT_J_ART_CODE varchar(6),
              DELETED timestamp,
              PRIMARY KEY (GLOBAL_DICT_ID,HLT_CODE)
          ); " }

    { "rm_meddra_hl_pref_term_j"
          "CREATE TABLE rm_meddra_hl_pref_term_j (
              GLOBAL_DICT_ID numeric NOT NULL,
              HLT_CODE numeric NOT NULL,
              HLT_KANJI varchar(400),
              HLT_KANA varchar(400),
              HLT_KANA1 varchar(400),
              HLT_KANA2 varchar(400),
              PRIMARY KEY (GLOBAL_DICT_ID,HLT_CODE)
          ); " }

    { "rm_meddra_md_hierarchy"
          "CREATE TABLE rm_meddra_md_hierarchy (
              GLOBAL_DICT_ID numeric NOT NULL,
              PT_CODE numeric NOT NULL,
              HLT_CODE numeric NOT NULL,
              HLGT_CODE numeric NOT NULL,
              SOC_CODE numeric NOT NULL,
              PT_NAME varchar(120) NOT NULL,
              HLT_NAME varchar(120) NOT NULL,
              HLGT_NAME varchar(120) NOT NULL,
              SOC_NAME varchar(120) NOT NULL,
              SOC_ABBREV varchar(5) NOT NULL,
              NULL_FIELD varchar(1),
              PT_SOC_CODE numeric,
              PRIMARY_SOC_FG varchar(1),
              DELETED timestamp,
              PRIMARY KEY (GLOBAL_DICT_ID,PT_CODE,HLT_CODE,HLGT_CODE,SOC_CODE)
          ); " }

    { "rm_meddra_pref_term"
          "CREATE TABLE rm_meddra_pref_term (
              GLOBAL_DICT_ID numeric NOT NULL,
              PT_CODE numeric NOT NULL,
              PT_NAME_ENGLISH varchar(120) NOT NULL,
              NULL_FIELD varchar(1),
              PT_SOC_CODE numeric,
              PT_WHO_ART_CODE varchar(7),
              PT_HARTS_CODE numeric,
              PT_COSTART_FG varchar(21),
              PT_ICD9_CODE varchar(8),
              PT_ICD9CM_CODE varchar(8),
              PT_ICD10_CODE varchar(8),
              PT_J_ART_CODE varchar(6),
              DELETED timestamp,
              PRIMARY KEY (GLOBAL_DICT_ID,PT_CODE)
          ); " }

    { "rm_meddra_pref_term_j"
          "CREATE TABLE rm_meddra_pref_term_j (
              GLOBAL_DICT_ID numeric NOT NULL,
              PT_CODE numeric NOT NULL,
              PT_KANJI varchar(400),
              PT_KANA varchar(400),
              PT_KANA1 varchar(400),
              PT_KANA2 varchar(400),
              PRIMARY KEY (GLOBAL_DICT_ID,PT_CODE)
          ); " }

    { "rm_meddra_pref_term_llt"
          "CREATE TABLE rm_meddra_pref_term_llt (
              GLOBAL_DICT_ID numeric NOT NULL,
              LLT_CODE numeric NOT NULL,
              LLT_NAME_ENGLISH varchar(100) NOT NULL,
              PT_CODE numeric,
              LLT_WHO_ART_CODE varchar(7),
              LLT_HARTS_CODE numeric,
              LLT_COSTART_FG varchar(21),
              LLT_ICD9_CODE varchar(8),
              LLT_ICD9CM_CODE varchar(8),
              LLT_ICD10_CODE varchar(8),
              LLT_CURRENCY varchar(1),
              LLT_J_ART_CODE varchar(6),
              DELETED timestamp,
              PRIMARY KEY (GLOBAL_DICT_ID,LLT_CODE)
          ); " }

    { "rm_meddra_pref_term_llt_j"
          "CREATE TABLE rm_meddra_pref_term_llt_j (
              GLOBAL_DICT_ID numeric NOT NULL,
              LLT_CODE numeric NOT NULL,
              LLT_KANJI varchar(400),
              LLT_JCURR varchar(10),
              LLT_KANA varchar(600),
              LLT_KANA1 varchar(600),
              LLT_KANA2 varchar(600),
              PRIMARY KEY (GLOBAL_DICT_ID,LLT_CODE)
          ); " }

    { "rm_meddra_smq_content"
          "CREATE TABLE rm_meddra_smq_content (
              GLOBAL_DICT_ID numeric NOT NULL,
              SMQ_CODE numeric NOT NULL,
              TERM_CODE numeric NOT NULL,
              TERM_LEVEL numeric NOT NULL,
              TERM_SCOPE numeric NOT NULL,
              TERM_CATEGORY varchar(1) NOT NULL,
              TERM_WEIGHT numeric NOT NULL,
              TERM_STATUS varchar(1) NOT NULL,
              TERM_ADDITION_VERSION varchar(5) NOT NULL,
              TERM_LAST_MODIFIED_VERSION varchar(5) NOT NULL,
              PRIMARY KEY (GLOBAL_DICT_ID,SMQ_CODE,TERM_CODE)
          ); " }

    { "rm_meddra_smq_list"
          "CREATE TABLE rm_meddra_smq_list (
              GLOBAL_DICT_ID numeric NOT NULL,
              SMQ_CODE numeric NOT NULL,
              SMQ_NAME varchar(100) NOT NULL,
              SMQ_LEVEL numeric NOT NULL,
              SMQ_DESCRIPTION varchar(3000),
              SMQ_SOURCE varchar(2000),
              SMQ_NOTE varchar(2000),
              MEDDRA_VERSION varchar(5) NOT NULL,
              STATUS varchar(1) NOT NULL,
              SMQ_ALGORITHM varchar(100) NOT NULL,
              PRIMARY KEY (GLOBAL_DICT_ID,SMQ_CODE)
          ); " }

    { "rm_meddra_smq_list_j"
          "CREATE TABLE rm_meddra_smq_list_j (
              GLOBAL_DICT_ID numeric NOT NULL,
              SMQ_CODE numeric,
              SMQ_KANJI varchar(120),
              SMQ_DESC_KANJI varchar(4000)
          ); " }

    { "rm_meddra_smq_term"
          "CREATE TABLE rm_meddra_smq_term (
              GLOBAL_DICT_ID numeric NOT NULL,
              SMQ_ID numeric NOT NULL,
              SMQ_CODE numeric,
              SMQ_NAME varchar(100),
              SMQ_NB_NAME varchar(120),
              SMQ_LEVEL numeric,
              SMQ_DESCRIPTION varchar(3000),
              SMQ_SOURCE varchar(2000),
              SMQ_NOTE varchar(2000),
              MEDDRA_VERSION varchar(5),
              STATUS varchar(1),
              SMQ_ALGORITHM varchar(100),
              SMQ_TYPE numeric,
              CREATE_DATE timestamp,
              MODIFIED_DATE timestamp,
              PRIMARY KEY (GLOBAL_DICT_ID,SMQ_ID)
          ); " }

    { "rm_meddra_smq_term_details"
          "CREATE TABLE rm_meddra_smq_term_details (
              GLOBAL_DICT_ID numeric NOT NULL,
              SMQ_TERM_ID numeric NOT NULL,
              SMQ_ID numeric,
              PARENT_SMQ_CODE numeric,
              TERM_CODE numeric,
              TERM_LEVEL numeric,
              TERM_SCOPE numeric,
              TERM_CATEGORY varchar(1),
              TERM_WEIGHT numeric,
              TERM_STATUS varchar(1),
              TERM_NAME_ENGLISH varchar(120),
              LLT_CURRENCY varchar(1),
              TERM_ADDITION_VERSION varchar(5),
              TERM_LAST_MODIFIED_VERSION varchar(5),
              CREATE_DATE timestamp,
              PRIMARY KEY (GLOBAL_DICT_ID,SMQ_TERM_ID)
          ); " }

    { "rm_meddra_soc"
          "CREATE TABLE rm_meddra_soc (
              GLOBAL_DICT_ID numeric NOT NULL,
              SOC_CODE numeric NOT NULL,
              SOC_NAME_ENGLISH varchar(120) NOT NULL,
              SOC_ABBREV varchar(5) NOT NULL,
              SOC_WHO_ART_CODE varchar(7),
              SOC_HARTS_CODE numeric,
              SOC_COSTART_FG varchar(21),
              SOC_ICD9_CODE varchar(8),
              SOC_ICD9CM_CODE varchar(8),
              SOC_ICD10_CODE varchar(8),
              SOC_J_ART_CODE varchar(6),
              DELETED timestamp,
              PRIMARY KEY (GLOBAL_DICT_ID,SOC_CODE)
          ); " }

    { "rm_meddra_soc_hlgt_comp"
          "CREATE TABLE rm_meddra_soc_hlgt_comp (
              GLOBAL_DICT_ID numeric NOT NULL,
              SOC_CODE numeric NOT NULL,
              HLGT_CODE numeric NOT NULL,
              DELETED timestamp,
              PRIMARY KEY (GLOBAL_DICT_ID,SOC_CODE,HLGT_CODE)
          ); " }

    { "rm_meddra_soc_intl_order"
          "CREATE TABLE rm_meddra_soc_intl_order (
              GLOBAL_DICT_ID numeric NOT NULL,
              INTL_ORD_CODE numeric NOT NULL,
              SOC_CODE numeric NOT NULL,
              PRIMARY KEY (GLOBAL_DICT_ID,INTL_ORD_CODE)
          ); " }

    { "rm_meddra_soc_j"
          "CREATE TABLE rm_meddra_soc_j (
              GLOBAL_DICT_ID numeric NOT NULL,
              SOC_CODE numeric NOT NULL,
              SOC_KANJI varchar(400),
              SOC_ORDER numeric,
              SOC_KANA varchar(400),
              SOC_KANA1 varchar(400),
              SOC_KANA2 varchar(400),
              PRIMARY KEY (GLOBAL_DICT_ID,SOC_CODE)
          ); " }

    { "rm_meddra_spec_cat"
          "CREATE TABLE rm_meddra_spec_cat (
              GLOBAL_DICT_ID numeric NOT NULL,
              SPEC_CODE numeric NOT NULL,
              SPEC_NAME varchar(120) NOT NULL,
              SPEC_ABBREV varchar(10) NOT NULL,
              DELETED timestamp
          ); " }

    { "rm_meddra_spec_pref_comp"
          "CREATE TABLE rm_meddra_spec_pref_comp (
              GLOBAL_DICT_ID numeric NOT NULL,
              SPEC_CODE numeric NOT NULL,
              PT_CODE numeric NOT NULL,
              DELETED timestamp
          ); " }

    { "rm_meddra_synonyms"
          "CREATE TABLE rm_meddra_synonyms (
              GLOBAL_DICT_ID numeric NOT NULL,
              SYN_ID numeric NOT NULL,
              LLT_CODE numeric NOT NULL,
              SYN varchar(255) NOT NULL,
              SYN_J varchar(255),
              PRIMARY KEY (GLOBAL_DICT_ID,SYN_ID)
          ); " }

    { "rm_rpt_routing"
          "CREATE TABLE rm_rpt_routing (
              ENTERPRISE_ID numeric NOT NULL,
              REG_REPORT_ID numeric NOT NULL,
              SEQ_NUM numeric NOT NULL,
              ROUTE_DATE timestamp,
              USER_ID numeric,
              COMMENT_TXT varchar(1000),
              TO_STATE_ID numeric,
              FROM_REPORT_STATE_ID numeric,
              JUSTIFICATION_ID numeric,
              DELETED timestamp,
              PRIMARY KEY (ENTERPRISE_ID,REG_REPORT_ID,SEQ_NUM)
          ); " }

    { "rm_su_case_study_drug"
          "CREATE TABLE rm_su_case_study_drug (
              ENTERPRISE_ID numeric NOT NULL,
              CASE_ID numeric NOT NULL,
              SEQ_NUM numeric,
              UNBLIND_OK numeric(1),
              BLIND_NAME varchar(70),
              BLIND_NAME_J varchar(70)
          ); " }

    { "rm_who_ard"
          "CREATE TABLE rm_who_ard (
              GLOBAL_DICT_ID numeric NOT NULL,
              ADV_REACTION_NUMBER char(4) NOT NULL,
              SEQ_NUM char(3) NOT NULL,
              CHECK_DIGIT char(1),
              HIGH_LEVEL_TERM_LINK char(4),
              SYSTEM_ORGAN_CLASS1 char(4),
              SYSTEM_ORGAN_CLASS2 char(4),
              SYSTEM_ORGAN_CLASS3 char(4),
              TEXT_ENGLISH varchar(100),
              TEXT_FRENCH varchar(100),
              TEXT_PORTUGUESE varchar(100),
              TEXT_GERMAN varchar(100),
              TEXT_SPANISH varchar(100),
              YEAR_QUARTER char(3),
              DELETED timestamp,
              PRIMARY KEY (GLOBAL_DICT_ID,ADV_REACTION_NUMBER,SEQ_NUM)
          ); " }

    { "rm_who_atc_classification"
          "CREATE TABLE rm_who_atc_classification (
              GLOBAL_DICT_ID numeric NOT NULL,
              DRUG_RECORD_NUMBER varchar(6) NOT NULL,
              SEQ_NUM1 varchar(2) NOT NULL,
              SEQ_NUM2 varchar(3) NOT NULL,
              CHECK_DIGIT varchar(1) NOT NULL,
              ATC_CODE varchar(7) NOT NULL,
              YEAR_QUARTER varchar(3),
              OFFICIAL_ATC_CODE varchar(1),
              DELETED timestamp,
              PRIMARY KEY (GLOBAL_DICT_ID,DRUG_RECORD_NUMBER,SEQ_NUM1,SEQ_NUM2,CHECK_DIGIT,ATC_CODE)
          ); " }

    { "rm_who_atc_code"
          "CREATE TABLE rm_who_atc_code (
              GLOBAL_DICT_ID numeric NOT NULL,
              ATC_CODE varchar(7) NOT NULL,
              ATC_LEVEL varchar(1),
              ATC_TEXT varchar(50),
              DELETED timestamp,
              PRIMARY KEY (GLOBAL_DICT_ID,ATC_CODE)
          ); " }

    { "rm_who_countries"
          "CREATE TABLE rm_who_countries (
              GLOBAL_DICT_ID numeric NOT NULL,
              COUNTRY_CODE char(3) NOT NULL,
              COUNTRY_NAME varchar(30),
              DELETED timestamp,
              PRIMARY KEY (GLOBAL_DICT_ID,COUNTRY_CODE)
          ); " }

    { "rm_who_drug_c_atc_code"
          "CREATE TABLE rm_who_drug_c_atc_code (
              GLOBAL_DICT_ID numeric NOT NULL,
              ATC_CODE varchar(10) NOT NULL,
              LEVEL numeric(1),
              TEXT varchar(110),
              PRIMARY KEY (GLOBAL_DICT_ID,ATC_CODE)
          ); " }

    { "rm_who_drug_c_country"
          "CREATE TABLE rm_who_drug_c_country (
              GLOBAL_DICT_ID numeric NOT NULL,
              COUNTRY_CODE varchar(10) NOT NULL,
              COUNTRY_NAME varchar(80),
              PRIMARY KEY (GLOBAL_DICT_ID,COUNTRY_CODE)
          ); " }

    { "rm_who_drug_c_ingredients"
          "CREATE TABLE rm_who_drug_c_ingredients (
              GLOBAL_DICT_ID numeric NOT NULL,
              INGREDIENT_ID varchar(10) NOT NULL,
              CREATE_DATE varchar(8),
              SUBSTANCE_ID varchar(10),
              QUANTITY varchar(15),
              QUANTITY_2 varchar(15),
              UNIT varchar(10),
              PHARM_PRODUCT_ID varchar(10),
              MEDICINAL_PROD_ID varchar(10),
              PRIMARY KEY (GLOBAL_DICT_ID,INGREDIENT_ID)
          ); " }

    { "rm_who_drug_c_master"
          "CREATE TABLE rm_who_drug_c_master (
              GLOBAL_DICT_ID numeric NOT NULL,
              PK_ID numeric NOT NULL,
              MEDICINAL_PROD_ID varchar(10) NOT NULL,
              DRUG_RECORD_NUMBER varchar(6),
              SEQ_NUM1 varchar(2),
              SEQ_NUM2 varchar(3),
              CODE varchar(13),
              PROD_TYPE_ID varchar(10),
              DRUG_NAME varchar(80),
              SUBSTANCE_NAME varchar(110),
              MANU_NAME varchar(80),
              COUNTRY varchar(10),
              PHARM_FORM_ID varchar(10),
              FORMULATION varchar(80),
              STRENGTH_ID varchar(10),
              STRENGTH varchar(500),
              GENERIC varchar(1),
              ATC_CODE varchar(10),
              ATC_TEXT varchar(110),
              NAME_SPECIFIER varchar(30),
              PRIMARY KEY (GLOBAL_DICT_ID,PK_ID)
          ); " }

    { "rm_who_drug_c_medicinal_prod"
          "CREATE TABLE rm_who_drug_c_medicinal_prod (
              GLOBAL_DICT_ID numeric NOT NULL,
              MEDICINAL_PROD_ID varchar(10) NOT NULL,
              ICH_MEDICINAL_PROD_ID varchar(35),
              DRUG_RECORD_NUMBER varchar(6),
              SEQ_NUM1 varchar(2),
              SEQ_NUM2 varchar(3),
              SEQ_NUM3 varchar(10),
              SEQ_NUM4 varchar(10),
              GENERIC varchar(1),
              DRUG_NAME varchar(80),
              NAME_SPECIFIER varchar(30),
              MA_NUMBER varchar(30),
              MA_DATE varchar(8),
              MA_WITHDRAW_DATE varchar(8),
              COUNTRY varchar(10),
              COMPANY varchar(10),
              MA_HOLDER varchar(10),
              SOURCE_CODE varchar(10),
              SOURCE_COUNTRY varchar(10),
              SOURCE_YEAR varchar(3),
              PRODUCT_TYPE varchar(10),
              PRODUCT_GROUP varchar(10),
              CREATE_DATE varchar(8),
              DATE_CHANGED varchar(8),
              PRIMARY KEY (GLOBAL_DICT_ID,MEDICINAL_PROD_ID)
          ); " }

    { "rm_who_drug_c_organization"
          "CREATE TABLE rm_who_drug_c_organization (
              GLOBAL_DICT_ID numeric NOT NULL,
              ORGANIZATION_ID varchar(10) NOT NULL,
              NAME varchar(80),
              COUNTRY_CODE varchar(10),
              PRIMARY KEY (GLOBAL_DICT_ID,ORGANIZATION_ID)
          ); " }

    { "rm_who_drug_c_pharma_form"
          "CREATE TABLE rm_who_drug_c_pharma_form (
              GLOBAL_DICT_ID numeric NOT NULL,
              PHARM_FORM_ID varchar(10) NOT NULL,
              TEXT varchar(80),
              PRIMARY KEY (GLOBAL_DICT_ID,PHARM_FORM_ID)
          ); " }

    { "rm_who_drug_c_pharma_product"
          "CREATE TABLE rm_who_drug_c_pharma_product (
              GLOBAL_DICT_ID numeric NOT NULL,
              PHARM_PRODUCT_ID varchar(10) NOT NULL,
              PHARMACEUTICAL_FORM varchar(10),
              ROUTE_OF_ADMIN varchar(10),
              MEDICINAL_PROD_ID varchar(10),
              NUMBER_OF_INGREDIENTS varchar(2),
              CREATE_DATE varchar(8),
              PRIMARY KEY (GLOBAL_DICT_ID,PHARM_PRODUCT_ID)
          ); " }

    { "rm_who_drug_c_product_group"
          "CREATE TABLE rm_who_drug_c_product_group (
              GLOBAL_DICT_ID numeric NOT NULL,
              PRODUCT_GROUP_ID varchar(10) NOT NULL,
              PRODUCT_GROUP_NAME varchar(60),
              DATE_RECORDED varchar(8),
              PRIMARY KEY (GLOBAL_DICT_ID,PRODUCT_GROUP_ID)
          ); " }

    { "rm_who_drug_c_product_type"
          "CREATE TABLE rm_who_drug_c_product_type (
              GLOBAL_DICT_ID numeric NOT NULL,
              PROD_TYPE_ID varchar(10) NOT NULL,
              TEXT varchar(80),
              PRIMARY KEY (GLOBAL_DICT_ID,PROD_TYPE_ID)
          ); " }

    { "rm_who_drug_c_source"
          "CREATE TABLE rm_who_drug_c_source (
              GLOBAL_DICT_ID numeric NOT NULL,
              SOURCE_CODE varchar(10) NOT NULL,
              SOURCE varchar(80),
              COUNTRY_CODE varchar(10),
              PRIMARY KEY (GLOBAL_DICT_ID,SOURCE_CODE)
          ); " }

    { "rm_who_drug_c_strength"
          "CREATE TABLE rm_who_drug_c_strength (
              GLOBAL_DICT_ID numeric NOT NULL,
              STRENGTH_ID varchar(10) NOT NULL,
              TEXT varchar(500),
              PRIMARY KEY (GLOBAL_DICT_ID,STRENGTH_ID)
          ); " }

    { "rm_who_drug_c_substance"
          "CREATE TABLE rm_who_drug_c_substance (
              GLOBAL_DICT_ID numeric NOT NULL,
              SUBSTANCE_ID varchar(10) NOT NULL,
              CAS_NUMBER varchar(10),
              LANGUAGE_CODE varchar(10),
              SUBSTANCE_NAME varchar(110),
              SOURCE_YEAR varchar(3),
              SOURCE_CODE varchar(10),
              PRIMARY KEY (GLOBAL_DICT_ID,SUBSTANCE_ID)
          ); " }

    { "rm_who_drug_c_therapeutic_grp"
          "CREATE TABLE rm_who_drug_c_therapeutic_grp (
              GLOBAL_DICT_ID numeric NOT NULL,
              THERAP_GROUP_ID varchar(10) NOT NULL,
              ATC_CODE varchar(10),
              CREATE_DATE varchar(8),
              OFFICIAL_ATC_CODE varchar(1),
              MEDICINAL_PROD_ID varchar(10),
              PRIMARY KEY (GLOBAL_DICT_ID,THERAP_GROUP_ID)
          ); " }

    { "rm_who_drug_c_unit"
          "CREATE TABLE rm_who_drug_c_unit (
              GLOBAL_DICT_ID numeric NOT NULL,
              UNIT_ID varchar(10) NOT NULL,
              TEXT varchar(40),
              PRIMARY KEY (GLOBAL_DICT_ID,UNIT_ID)
          ); " }

    { "rm_who_drug_c_unit_l"
          "CREATE TABLE rm_who_drug_c_unit_l (
              GLOBAL_DICT_ID numeric NOT NULL,
              UNIT_ID varchar(10) NOT NULL,
              TEXT varchar(100),
              PRIMARY KEY (GLOBAL_DICT_ID,UNIT_ID)
          ); " }

    { "rm_who_drug_c_unit_x"
          "CREATE TABLE rm_who_drug_c_unit_x (
              GLOBAL_DICT_ID numeric NOT NULL,
              UNIT_ID varchar(10) NOT NULL,
              TEXT varchar(40),
              PRIMARY KEY (GLOBAL_DICT_ID,UNIT_ID)
          ); " }

    { "rm_who_drug_dict"
          "CREATE TABLE rm_who_drug_dict (
              GLOBAL_DICT_ID numeric NOT NULL,
              DRUG_RECORD_NUMBER char(6) NOT NULL,
              SEQ_NUM1 char(2) NOT NULL,
              SEQ_NUM2 char(3) NOT NULL,
              CHECK_DIGIT char(1) NOT NULL,
              SOURCE_CODE char(4),
              COMPANY_CODE char(5),
              DESIGNATION char(1),
              SOURCE_YEAR char(4),
              NUM_INGREDIENTS char(2),
              SALT_CODE char(1),
              YEAR_QUARTER char(3),
              DRUG_NAME varchar(70),
              DELETED timestamp,
              PRIMARY KEY (GLOBAL_DICT_ID,DRUG_RECORD_NUMBER,SEQ_NUM1,SEQ_NUM2,CHECK_DIGIT)
          ); " }

    { "rm_who_drug_source"
          "CREATE TABLE rm_who_drug_source (
              GLOBAL_DICT_ID numeric NOT NULL,
              SOURCE_CODE char(4) NOT NULL,
              COUNTRY_CODE char(3),
              SOURCE varchar(70),
              DELETED timestamp,
              PRIMARY KEY (GLOBAL_DICT_ID,SOURCE_CODE)
          ); " }

    { "rm_who_ingredients"
          "CREATE TABLE rm_who_ingredients (
              GLOBAL_DICT_ID numeric NOT NULL,
              DRUG_RECORD_NUMBER char(6) NOT NULL,
              SEQ_NUM1 char(2) NOT NULL,
              SEQ_NUM2 char(3) NOT NULL,
              CHECK_DIGIT char(1) NOT NULL,
              CAS_NUMBER char(10),
              DELETED timestamp
          ); " }

    { "rm_who_manufacturers"
          "CREATE TABLE rm_who_manufacturers (
              GLOBAL_DICT_ID numeric NOT NULL,
              COMPANY_CODE char(5) NOT NULL,
              COUNTRY_CODE char(3),
              NAME varchar(100),
              DELETED timestamp,
              PRIMARY KEY (GLOBAL_DICT_ID,COMPANY_CODE)
          ); " }

    { "rm_who_soc"
          "CREATE TABLE rm_who_soc (
              GLOBAL_DICT_ID numeric NOT NULL,
              SYSTEM_ORGAN_CLASS_CODE char(4) NOT NULL,
              TEXT_ENGLISH varchar(100),
              TEXT_FRENCH varchar(100),
              TEXT_GERMAN varchar(100),
              TEXT_SPANISH varchar(100),
              TEXT_PORTUGUESE varchar(100),
              DELETED timestamp,
              PRIMARY KEY (GLOBAL_DICT_ID,SYSTEM_ORGAN_CLASS_CODE)
          ); " }

    { "rm_who_substances"
          "CREATE TABLE rm_who_substances (
              GLOBAL_DICT_ID numeric NOT NULL,
              CAS_NUMBER char(10) NOT NULL,
              LANGUAGE_CODE char(2),
              SUBSTANCE_NAME varchar(45),
              SOURCE_YEAR char(2),
              SOURCE_CODE char(4),
              DELETED timestamp,
              PRIMARY KEY (GLOBAL_DICT_ID,CAS_NUMBER)
          ); " }

  ] ))

(def dummy 

    { ""
          "
          " }

)
