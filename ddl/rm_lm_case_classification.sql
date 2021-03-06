--------------------------------------------------------
--  File created - Saturday-April-04-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table RM_LM_CASE_CLASSIFICATION
--------------------------------------------------------

  CREATE TABLE "MART_USER"."RM_LM_CASE_CLASSIFICATION" 
   (	"ENTERPRISE_ID" NUMBER DEFAULT SYS_CONTEXT('ARGUS_MART_CTX','enterprise_id'), 
	"CLASSIFICATION_ID" NUMBER, 
	"DESCRIPTION" VARCHAR2(30 CHAR), 
	"PROTECTED" NUMBER, 
	"DISPLAY" NUMBER, 
	"DESCRIPTION_J" VARCHAR2(30 CHAR), 
	"DELETED" DATE, 
	"E2B_CODE" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_DATA_01" ;
--------------------------------------------------------
--  DDL for Index PK_RM_LM_CASE_CLASSIFICATION
--------------------------------------------------------

  CREATE UNIQUE INDEX "MART_USER"."PK_RM_LM_CASE_CLASSIFICATION" ON "MART_USER"."RM_LM_CASE_CLASSIFICATION" ("ENTERPRISE_ID", "CLASSIFICATION_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_INDEX_01" ;
--------------------------------------------------------
--  Constraints for Table RM_LM_CASE_CLASSIFICATION
--------------------------------------------------------

  ALTER TABLE "MART_USER"."RM_LM_CASE_CLASSIFICATION" ADD CONSTRAINT "PK_RM_LM_CASE_CLASSIFICATION" PRIMARY KEY ("ENTERPRISE_ID", "CLASSIFICATION_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_INDEX_01"  ENABLE;
 
  ALTER TABLE "MART_USER"."RM_LM_CASE_CLASSIFICATION" MODIFY ("ENTERPRISE_ID" NOT NULL ENABLE);
 
  ALTER TABLE "MART_USER"."RM_LM_CASE_CLASSIFICATION" MODIFY ("DESCRIPTION" NOT NULL ENABLE);
