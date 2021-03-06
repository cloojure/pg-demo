--------------------------------------------------------
--  File created - Saturday-April-04-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table RM_CASE_PAT_INFO
--------------------------------------------------------

  CREATE TABLE "MART_USER"."RM_CASE_PAT_INFO" 
   (	"ENTERPRISE_ID" NUMBER DEFAULT SYS_CONTEXT('ARGUS_MART_CTX','enterprise_id'), 
	"CASE_ID" NUMBER, 
	"PAT_ID" VARCHAR2(10 CHAR), 
	"PAT_SUBJ_NUM" VARCHAR2(20 CHAR), 
	"PAT_INITIALS" VARCHAR2(10 CHAR), 
	"PAT_FIRSTNAME" VARCHAR2(35 CHAR), 
	"PAT_MI" VARCHAR2(15 CHAR), 
	"PAT_LASTNAME" VARCHAR2(50 CHAR), 
	"PAT_ADDRESS" VARCHAR2(120 CHAR), 
	"PAT_CITY" VARCHAR2(35 CHAR), 
	"PAT_STATE" VARCHAR2(40 CHAR), 
	"PAT_POSTAL_CODE" VARCHAR2(15 CHAR), 
	"PAT_COUNTRY" VARCHAR2(50 CHAR), 
	"PAT_PHONE" VARCHAR2(20 CHAR), 
	"PAT_DOB" DATE, 
	"PAT_DOB_RES" NUMBER, 
	"PAT_AGE" NUMBER(22,7), 
	"AGE_UNIT_ID" NUMBER, 
	"AGE_GROUP_ID" NUMBER, 
	"GENDER_ID" NUMBER, 
	"PAT_WEIGHT_LBS" NUMBER(22,7), 
	"PAT_WEIGHT_KG" NUMBER(22,7), 
	"PAT_HEIGHT_IN" NUMBER(22,7), 
	"PAT_HEIGHT_CM" NUMBER(22,7), 
	"ETHNICITY_ID" NUMBER, 
	"OCCUPATION_ID" NUMBER, 
	"PAT_STAT_PREG" NUMBER, 
	"REL_TEST_ID" NUMBER, 
	"PAT_DOB_PARTIAL" VARCHAR2(20 CHAR), 
	"RAND_NUM" VARCHAR2(15 CHAR), 
	"CONFIDENTIAL" NUMBER(1,0), 
	"NUMBER_OF_PATIENTS" VARCHAR2(8 CHAR), 
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
	"CHILD_ONLY" NUMBER(1,0), 
	"PAT_BMI" NUMBER(22,7), 
	"PAT_BODY_AREA" NUMBER(22,7), 
	"PAT_ADDRESS_J" VARCHAR2(120 CHAR), 
	"PAT_CITY_J" VARCHAR2(35 CHAR), 
	"PAT_FIRSTNAME_J" VARCHAR2(35 CHAR), 
	"PAT_LASTNAME_J" VARCHAR2(50 CHAR), 
	"PAT_MI_J" VARCHAR2(15 CHAR), 
	"PAT_STATE_J" VARCHAR2(40 CHAR), 
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
	"PAT_COUNTRY_ID" NUMBER, 
	"DLP_REVISION_NUMBER" NUMBER, 
	"REVISION_DELETE_FLAG" NUMBER DEFAULT 0, 
	"DELETED_FLAG" NUMBER(1,0), 
	"EFFECTIVE_START_DATE" DATE, 
	"EFFECTIVE_END_DATE" DATE, 
	"NOTES" CLOB, 
	"NOTES_J" CLOB, 
	"AGE_UNIT_ID_AT_VACC" NUMBER, 
	"PAT_AGE_AT_VACC" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_DATA_01" 
 LOB ("NOTES") STORE AS BASICFILE "RCPI_NOTES_CLOB"(
  TABLESPACE "AM_MART_LOB_01" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) 
 LOB ("NOTES_J") STORE AS BASICFILE "RCPI_NOTES_J_CLOB"(
  TABLESPACE "AM_MART_LOB_01" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;
--------------------------------------------------------
--  DDL for Index PK_RM_CASE_PAT_INFO
--------------------------------------------------------

  CREATE UNIQUE INDEX "MART_USER"."PK_RM_CASE_PAT_INFO" ON "MART_USER"."RM_CASE_PAT_INFO" ("ENTERPRISE_ID", "CASE_ID", "DLP_REVISION_NUMBER") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_INDEX_01" ;
--------------------------------------------------------
--  DDL for Index I_RM_CASE_PAT_INFO
--------------------------------------------------------

  CREATE INDEX "MART_USER"."I_RM_CASE_PAT_INFO" ON "MART_USER"."RM_CASE_PAT_INFO" ("ENTERPRISE_ID", "CASE_ID", "EFFECTIVE_END_DATE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_INDEX_01" ;
--------------------------------------------------------
--  Constraints for Table RM_CASE_PAT_INFO
--------------------------------------------------------

  ALTER TABLE "MART_USER"."RM_CASE_PAT_INFO" ADD CONSTRAINT "PK_RM_CASE_PAT_INFO" PRIMARY KEY ("ENTERPRISE_ID", "CASE_ID", "DLP_REVISION_NUMBER")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_INDEX_01"  ENABLE;
 
  ALTER TABLE "MART_USER"."RM_CASE_PAT_INFO" MODIFY ("ENTERPRISE_ID" NOT NULL ENABLE);
 
  ALTER TABLE "MART_USER"."RM_CASE_PAT_INFO" MODIFY ("DELETED_FLAG" NOT NULL ENABLE);
 
  ALTER TABLE "MART_USER"."RM_CASE_PAT_INFO" MODIFY ("EFFECTIVE_START_DATE" NOT NULL ENABLE);
 
  ALTER TABLE "MART_USER"."RM_CASE_PAT_INFO" MODIFY ("EFFECTIVE_END_DATE" NOT NULL ENABLE);
