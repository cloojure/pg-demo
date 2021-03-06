--------------------------------------------------------
--  File created - Saturday-April-04-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table RM_CASE_FOLLOWUP
--------------------------------------------------------

  CREATE TABLE "MART_USER"."RM_CASE_FOLLOWUP" 
   (	"ENTERPRISE_ID" NUMBER DEFAULT SYS_CONTEXT('ARGUS_MART_CTX','enterprise_id'), 
	"CASE_ID" NUMBER, 
	"SEQ_NUM" NUMBER, 
	"RECEIPT_DATE" DATE, 
	"SAFETY_DATE" DATE, 
	"SIGNIFICANT" NUMBER(1,0), 
	"AUDITLOG_TYPE" NUMBER, 
	"TIME_STAMP" DATE, 
	"DELETED" DATE, 
	"SIGNIFICANT_DEVICE" NUMBER(1,0), 
	"DATA_CLEANUP" NUMBER(1,0), 
	"JUSTIFICATION" VARCHAR2(1000 CHAR), 
	"JUSTIFICATION_ID" NUMBER, 
	"JUSTIFICATION_J" VARCHAR2(1000 CHAR), 
	"JUST_RECEIPT_DATE_J" VARCHAR2(1000 CHAR), 
	"RECEIPT_DATE_J" DATE, 
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
--  DDL for Index PK_RM_CASE_FOLLOWUP
--------------------------------------------------------

  CREATE UNIQUE INDEX "MART_USER"."PK_RM_CASE_FOLLOWUP" ON "MART_USER"."RM_CASE_FOLLOWUP" ("ENTERPRISE_ID", "CASE_ID", "SEQ_NUM", "DLP_REVISION_NUMBER") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_INDEX_01" ;
--------------------------------------------------------
--  DDL for Index I_RM_CASE_FOLLOWUP
--------------------------------------------------------

  CREATE INDEX "MART_USER"."I_RM_CASE_FOLLOWUP" ON "MART_USER"."RM_CASE_FOLLOWUP" ("ENTERPRISE_ID", "CASE_ID", "EFFECTIVE_END_DATE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_INDEX_01" ;
--------------------------------------------------------
--  Constraints for Table RM_CASE_FOLLOWUP
--------------------------------------------------------

  ALTER TABLE "MART_USER"."RM_CASE_FOLLOWUP" ADD CONSTRAINT "PK_RM_CASE_FOLLOWUP" PRIMARY KEY ("ENTERPRISE_ID", "CASE_ID", "SEQ_NUM", "DLP_REVISION_NUMBER")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_INDEX_01"  ENABLE;
 
  ALTER TABLE "MART_USER"."RM_CASE_FOLLOWUP" MODIFY ("ENTERPRISE_ID" NOT NULL ENABLE);
 
  ALTER TABLE "MART_USER"."RM_CASE_FOLLOWUP" MODIFY ("DELETED_FLAG" NOT NULL ENABLE);
 
  ALTER TABLE "MART_USER"."RM_CASE_FOLLOWUP" MODIFY ("EFFECTIVE_START_DATE" NOT NULL ENABLE);
 
  ALTER TABLE "MART_USER"."RM_CASE_FOLLOWUP" MODIFY ("EFFECTIVE_END_DATE" NOT NULL ENABLE);
