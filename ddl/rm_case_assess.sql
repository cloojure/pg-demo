--------------------------------------------------------
--  File created - Saturday-April-04-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table RM_CASE_ASSESS
--------------------------------------------------------

  CREATE TABLE "MART_USER"."RM_CASE_ASSESS" 
   (	"ENTERPRISE_ID" NUMBER DEFAULT SYS_CONTEXT('ARGUS_MART_CTX','enterprise_id'), 
	"CASE_ID" NUMBER, 
	"TEMPLATE_ID" NUMBER, 
	"LISTEDNESS" NUMBER, 
	"UPDATED" DATE, 
	"OUTCOME" NUMBER, 
	"COMMENT_ID" NUMBER, 
	"COMP_COMMENT_ID" NUMBER, 
	"SERIOUSNESS" NUMBER, 
	"SERIOUS_NOTES" VARCHAR2(1000 CHAR), 
	"AGENT_SUSPECT" NUMBER, 
	"AGENT_NOTES" VARCHAR2(1000 CHAR), 
	"LISTEDNESS_NOTES" VARCHAR2(1000 CHAR), 
	"BFARM_CAUSALITY" NUMBER(10,0), 
	"BFARM_MANUAL_TEXT" CLOB, 
	"COMPANY_DIAGNOSIS" VARCHAR2(250 CHAR), 
	"COMPANY_DIAGNOSIS_NOTES" VARCHAR2(1000 CHAR), 
	"DIAGNOSIS_DICT_ID" NUMBER, 
	"DIAGNOSIS_PREF_CODE" VARCHAR2(30 CHAR), 
	"DIAGNOSIS_INC_CODE" VARCHAR2(30 CHAR), 
	"DIAGNOSIS_INC_TERM" VARCHAR2(250 CHAR), 
	"DIAGNOSIS_HLGT_CODE" VARCHAR2(30 CHAR), 
	"DIAGNOSIS_HLGT" VARCHAR2(250 CHAR), 
	"DIAGNOSIS_HLT_CODE" VARCHAR2(30 CHAR), 
	"DIAGNOSIS_HLT" VARCHAR2(250 CHAR), 
	"DIAGNOSIS_SOC_CODE" VARCHAR2(30 CHAR), 
	"DIAGNOSIS_BODY_SYS" VARCHAR2(250 CHAR), 
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
	"EVALUATION" CLOB, 
	"EVALUATION_J" CLOB, 
	"CO_SUSPECT_COUNT" NUMBER(3,0), 
	"EVENT_SYNOPSIS" VARCHAR2(5 CHAR), 
	"EVENT_PRIMARY" VARCHAR2(400 CHAR), 
	"DIAGNOSIS_REPTD" VARCHAR2(255 CHAR), 
	"DIAGNOSIS_CODED" VARCHAR2(255 CHAR), 
	"DIAGNOSIS_SYN_CODE" NUMBER, 
	"DIAGNOSIS_CODE_STATUS" NUMBER(3,0), 
	"EVENT_PRIMARY_J" VARCHAR2(400 CHAR), 
	"AGENT_NOTES_J" VARCHAR2(1000 CHAR), 
	"COMPANY_DIAGNOSIS_J" VARCHAR2(250 CHAR), 
	"COMPANY_DIAGNOSIS_NOTES_J" VARCHAR2(1000 CHAR), 
	"DIAGNOSIS_BODY_SYS_J" VARCHAR2(250 CHAR), 
	"DIAGNOSIS_CODED_J" VARCHAR2(255 CHAR), 
	"DIAGNOSIS_CODE_STATUS_J" NUMBER, 
	"DIAGNOSIS_HLGT_J" VARCHAR2(250 CHAR), 
	"DIAGNOSIS_HLT_J" VARCHAR2(250 CHAR), 
	"DIAGNOSIS_INC_CODE_J" VARCHAR2(30 CHAR), 
	"DIAGNOSIS_INC_TERM_J" VARCHAR2(250 CHAR), 
	"DIAGNOSIS_REPTD_J" VARCHAR2(255 CHAR), 
	"LISTEDNESS_NOTES_J" VARCHAR2(1000 CHAR), 
	"SERIOUS_NOTES_J" VARCHAR2(1000 CHAR), 
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
	"DIAGNOSIS_SYN_CODE_J" NUMBER, 
	"DELETED" DATE, 
	"DLP_REVISION_NUMBER" NUMBER, 
	"REVISION_DELETE_FLAG" NUMBER DEFAULT 0, 
	"DELETED_FLAG" NUMBER(1,0), 
	"EFFECTIVE_START_DATE" DATE, 
	"EFFECTIVE_END_DATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_DATA_01" 
 LOB ("BFARM_MANUAL_TEXT") STORE AS BASICFILE "RCA_BFARM_MANUAL_TEXT_CLOB"(
  TABLESPACE "AM_MART_LOB_01" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) 
 LOB ("EVALUATION") STORE AS BASICFILE "RCA_EVALUATION_CLOB"(
  TABLESPACE "AM_MART_LOB_01" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) 
 LOB ("EVALUATION_J") STORE AS BASICFILE "RCA_EVALUATION_J_CLOB"(
  TABLESPACE "AM_MART_LOB_01" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;
--------------------------------------------------------
--  DDL for Index PK_RM_CASE_ASSESS
--------------------------------------------------------

  CREATE UNIQUE INDEX "MART_USER"."PK_RM_CASE_ASSESS" ON "MART_USER"."RM_CASE_ASSESS" ("ENTERPRISE_ID", "CASE_ID", "DLP_REVISION_NUMBER") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_INDEX_01" ;
--------------------------------------------------------
--  DDL for Index I_RM_CASE_ASSESS
--------------------------------------------------------

  CREATE INDEX "MART_USER"."I_RM_CASE_ASSESS" ON "MART_USER"."RM_CASE_ASSESS" ("ENTERPRISE_ID", "CASE_ID", "EFFECTIVE_END_DATE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_INDEX_01" ;
--------------------------------------------------------
--  Constraints for Table RM_CASE_ASSESS
--------------------------------------------------------

  ALTER TABLE "MART_USER"."RM_CASE_ASSESS" ADD CONSTRAINT "PK_RM_CASE_ASSESS" PRIMARY KEY ("ENTERPRISE_ID", "CASE_ID", "DLP_REVISION_NUMBER")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_INDEX_01"  ENABLE;
 
  ALTER TABLE "MART_USER"."RM_CASE_ASSESS" MODIFY ("ENTERPRISE_ID" NOT NULL ENABLE);
 
  ALTER TABLE "MART_USER"."RM_CASE_ASSESS" MODIFY ("DELETED_FLAG" NOT NULL ENABLE);
 
  ALTER TABLE "MART_USER"."RM_CASE_ASSESS" MODIFY ("EFFECTIVE_START_DATE" NOT NULL ENABLE);
 
  ALTER TABLE "MART_USER"."RM_CASE_ASSESS" MODIFY ("EFFECTIVE_END_DATE" NOT NULL ENABLE);
