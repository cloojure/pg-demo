--------------------------------------------------------
--  File created - Saturday-April-04-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table RM_LM_REPORT_TYPE
--------------------------------------------------------

  CREATE TABLE "MART_USER"."RM_LM_REPORT_TYPE" 
   (	"ENTERPRISE_ID" NUMBER DEFAULT SYS_CONTEXT('ARGUS_MART_CTX','enterprise_id'), 
	"RPT_TYPE_ID" NUMBER, 
	"REPORT_TYPE" VARCHAR2(30 CHAR), 
	"PROTECTED" NUMBER, 
	"DISPLAY" NUMBER, 
	"INCL_LIT" NUMBER, 
	"INCL_TRIAL" NUMBER, 
	"E2B_CODE" NUMBER, 
	"ABRV" VARCHAR2(3 CHAR), 
	"INVESTIGATIONAL" NUMBER, 
	"REPORT_TYPE_J" VARCHAR2(30 CHAR), 
	"DELETED" DATE, 
	"INCL_RESEARCH" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_DATA_01" ;
--------------------------------------------------------
--  DDL for Index PK_RM_LM_REPORT_TYPE
--------------------------------------------------------

  CREATE UNIQUE INDEX "MART_USER"."PK_RM_LM_REPORT_TYPE" ON "MART_USER"."RM_LM_REPORT_TYPE" ("ENTERPRISE_ID", "RPT_TYPE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_INDEX_01" ;
--------------------------------------------------------
--  Constraints for Table RM_LM_REPORT_TYPE
--------------------------------------------------------

  ALTER TABLE "MART_USER"."RM_LM_REPORT_TYPE" ADD CONSTRAINT "PK_RM_LM_REPORT_TYPE" PRIMARY KEY ("ENTERPRISE_ID", "RPT_TYPE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_INDEX_01"  ENABLE;
 
  ALTER TABLE "MART_USER"."RM_LM_REPORT_TYPE" MODIFY ("ENTERPRISE_ID" NOT NULL ENABLE);
 
  ALTER TABLE "MART_USER"."RM_LM_REPORT_TYPE" MODIFY ("REPORT_TYPE" NOT NULL ENABLE);
 
  ALTER TABLE "MART_USER"."RM_LM_REPORT_TYPE" MODIFY ("INCL_RESEARCH" NOT NULL ENABLE);
