--------------------------------------------------------
--  File created - Saturday-April-04-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table RM_CASE_MASTER
--------------------------------------------------------

  CREATE TABLE "MART_USER"."RM_CASE_MASTER" 
   (	"ENTERPRISE_ID" NUMBER DEFAULT SYS_CONTEXT('ARGUS_MART_CTX','enterprise_id'), 
	"CASE_ID" NUMBER, 
	"CASE_NUM" VARCHAR2(20 CHAR), 
	"REV" NUMBER, 
	"WORKFLOW_SEQ_NUM" NUMBER, 
	"LAST_WORKFLOW_SEQ_NUM" NUMBER, 
	"CREATE_TIME" DATE, 
	"INIT_REPT_DATE" DATE, 
	"USER_ID" NUMBER, 
	"LAST_UPDATE_TIME" DATE, 
	"LAST_UPDATE_USER_ID" NUMBER, 
	"REQUIRES_FOLLOWUP" NUMBER, 
	"FOLLOWUP_DATE" DATE, 
	"OWNER_ID" NUMBER, 
	"STATE_ID" NUMBER, 
	"COUNTRY_ID" NUMBER, 
	"LANG_ID" NUMBER, 
	"PRIORITY" NUMBER, 
	"SITE_ID" NUMBER, 
	"SERIOUSNESS" NUMBER, 
	"RPT_TYPE_ID" NUMBER, 
	"LAST_STATE_ID" NUMBER, 
	"ASSESSMENT_NEEDED" NUMBER, 
	"PRIORITY_OVERRIDE" NUMBER(1,0), 
	"SID" VARCHAR2(128 CHAR), 
	"SAFETY_DATE" DATE, 
	"NORMAL_TIME" DATE, 
	"MAX_TIME" DATE, 
	"REPORT_SCHEDULING" NUMBER, 
	"PRIORITY_ASSESSMENT" NUMBER, 
	"CLOSE_USER_ID" NUMBER, 
	"CLOSE_DATE" DATE, 
	"CLOSE_NOTES" VARCHAR2(200 CHAR), 
	"DATE_LOCKED" DATE, 
	"UD_TEXT_1" VARCHAR2(100 CHAR), 
	"UD_TEXT_2" VARCHAR2(100 CHAR), 
	"UD_TEXT_3" VARCHAR2(100 CHAR), 
	"UD_TEXT_4" VARCHAR2(100 CHAR), 
	"UD_TEXT_5" VARCHAR2(100 CHAR), 
	"UD_TEXT_6" VARCHAR2(100 CHAR), 
	"UD_TEXT_7" VARCHAR2(100 CHAR), 
	"UD_TEXT_8" VARCHAR2(100 CHAR), 
	"UD_TEXT_9" VARCHAR2(100 CHAR), 
	"UD_TEXT_10" VARCHAR2(100 CHAR), 
	"UD_TEXT_11" VARCHAR2(100 CHAR), 
	"UD_TEXT_12" VARCHAR2(100 CHAR), 
	"UD_DATE_1" DATE, 
	"UD_DATE_2" DATE, 
	"UD_DATE_3" DATE, 
	"UD_DATE_4" DATE, 
	"UD_DATE_5" DATE, 
	"UD_DATE_6" DATE, 
	"UD_DATE_7" DATE, 
	"UD_DATE_8" DATE, 
	"UD_DATE_9" DATE, 
	"UD_DATE_10" DATE, 
	"UD_DATE_11" DATE, 
	"UD_DATE_12" DATE, 
	"UD_NUMBER_1" NUMBER(30,10), 
	"UD_NUMBER_2" NUMBER(30,10), 
	"UD_NUMBER_3" NUMBER(30,10), 
	"UD_NUMBER_4" NUMBER(30,10), 
	"UD_NUMBER_5" NUMBER(30,10), 
	"UD_NUMBER_6" NUMBER(30,10), 
	"UD_NUMBER_7" NUMBER(30,10), 
	"UD_NUMBER_8" NUMBER(30,10), 
	"UD_NUMBER_9" NUMBER(30,10), 
	"UD_NUMBER_10" NUMBER(30,10), 
	"UD_NUMBER_11" NUMBER(30,10), 
	"UD_NUMBER_12" NUMBER(30,10), 
	"DELETED" DATE, 
	"DUE_SOON" DATE, 
	"GLOBAL_NUM" VARCHAR2(20 CHAR), 
	"PRIORITY_DATE_ASSESSED" DATE, 
	"LAM_ASSESS_DONE" NUMBER(1,0), 
	"E2B_WW_NUMBER" VARCHAR2(100 CHAR), 
	"WORKLIST_OWNER_ID" NUMBER, 
	"SUSAR" NUMBER, 
	"LAST_UPDATE_EVENT" DATE, 
	"INITIAL_JUSTIFICATION" VARCHAR2(1000 CHAR), 
	"FORCE_SOON" DATE, 
	"DUE_SOON_J" DATE, 
	"FOLLOWUP_DATE_J" DATE, 
	"INIT_REPT_DATE_J" DATE, 
	"JUST_INIT_REPT_DATE_J" VARCHAR2(1000 CHAR), 
	"UD_TEXT_1_J" VARCHAR2(100 CHAR), 
	"UD_TEXT_2_J" VARCHAR2(100 CHAR), 
	"UD_TEXT_3_J" VARCHAR2(100 CHAR), 
	"UD_TEXT_4_J" VARCHAR2(100 CHAR), 
	"UD_TEXT_5_J" VARCHAR2(100 CHAR), 
	"UD_TEXT_6_J" VARCHAR2(100 CHAR), 
	"UD_TEXT_7_J" VARCHAR2(100 CHAR), 
	"UD_TEXT_8_J" VARCHAR2(100 CHAR), 
	"UD_TEXT_9_J" VARCHAR2(100 CHAR), 
	"UD_TEXT_10_J" VARCHAR2(100 CHAR), 
	"UD_TEXT_11_J" VARCHAR2(100 CHAR), 
	"UD_TEXT_12_J" VARCHAR2(100 CHAR), 
	"INITIAL_JUSTIFICATION_J" VARCHAR2(1000 CHAR), 
	"DLP_REVISION_NUMBER" NUMBER, 
	"REVISION_DELETE_FLAG" NUMBER DEFAULT 0, 
	"DELETED_FLAG" NUMBER(1,0), 
	"EFFECTIVE_START_DATE" DATE, 
	"EFFECTIVE_END_DATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_DATA_01" ;
--------------------------------------------------------
--  DDL for Index PK_RM_CASE_MASTER
--------------------------------------------------------

  CREATE UNIQUE INDEX "MART_USER"."PK_RM_CASE_MASTER" ON "MART_USER"."RM_CASE_MASTER" ("ENTERPRISE_ID", "CASE_ID", "DLP_REVISION_NUMBER") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_INDEX_01" ;
--------------------------------------------------------
--  DDL for Index I_RM_CASE_MASTER
--------------------------------------------------------

  CREATE INDEX "MART_USER"."I_RM_CASE_MASTER" ON "MART_USER"."RM_CASE_MASTER" ("ENTERPRISE_ID", "CASE_ID", "EFFECTIVE_END_DATE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_INDEX_01" ;
--------------------------------------------------------
--  Constraints for Table RM_CASE_MASTER
--------------------------------------------------------

  ALTER TABLE "MART_USER"."RM_CASE_MASTER" ADD CONSTRAINT "PK_RM_CASE_MASTER" PRIMARY KEY ("ENTERPRISE_ID", "CASE_ID", "DLP_REVISION_NUMBER")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_INDEX_01"  ENABLE;
 
  ALTER TABLE "MART_USER"."RM_CASE_MASTER" MODIFY ("ENTERPRISE_ID" NOT NULL ENABLE);
 
  ALTER TABLE "MART_USER"."RM_CASE_MASTER" MODIFY ("DELETED_FLAG" NOT NULL ENABLE);
 
  ALTER TABLE "MART_USER"."RM_CASE_MASTER" MODIFY ("EFFECTIVE_START_DATE" NOT NULL ENABLE);
 
  ALTER TABLE "MART_USER"."RM_CASE_MASTER" MODIFY ("EFFECTIVE_END_DATE" NOT NULL ENABLE);
