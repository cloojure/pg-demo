--------------------------------------------------------
--  File created - Saturday-April-04-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table RM_LM_PRODUCT
--------------------------------------------------------

  CREATE TABLE "MART_USER"."RM_LM_PRODUCT" 
   (	"ENTERPRISE_ID" NUMBER DEFAULT SYS_CONTEXT('ARGUS_MART_CTX','enterprise_id'), 
	"PRODUCT_ID" NUMBER, 
	"PROD_NAME" VARCHAR2(70 CHAR), 
	"FAMILY_ID" NUMBER, 
	"FORMULATION_ID" NUMBER, 
	"MANUFACTURER_ID" NUMBER, 
	"CONCENTRATION" VARCHAR2(10 CHAR), 
	"CONC_UNIT_ID" NUMBER, 
	"INDICATION_ID" VARCHAR2(50 CHAR), 
	"CODE_DICT" NUMBER, 
	"INDICATION_TEXT" VARCHAR2(250 CHAR), 
	"INTL_BIRTH_DATE" DATE, 
	"MODEL_NO" VARCHAR2(20 CHAR), 
	"DRL_ID" VARCHAR2(45 CHAR), 
	"DRUG_CODE" VARCHAR2(20 CHAR), 
	"DELETED" DATE, 
	"PROD_GENERIC_NAME" CLOB, 
	"PROD_GENERIC_NAME_J" CLOB, 
	"IND_LLT_CODE" VARCHAR2(50 CHAR), 
	"IND_LLT" VARCHAR2(250 CHAR), 
	"IND_HLT_CODE" VARCHAR2(50 CHAR), 
	"IND_HLT" VARCHAR2(250 CHAR), 
	"IND_HLGT_CODE" VARCHAR2(50 CHAR), 
	"IND_HLGT" VARCHAR2(250 CHAR), 
	"IND_SOC_CODE" VARCHAR2(50 CHAR), 
	"IND_SOC" VARCHAR2(250 CHAR), 
	"IND_SYN_CODE" NUMBER, 
	"IND_CODE_STATUS" NUMBER, 
	"MEDICINAL_PROD_ID" VARCHAR2(10 CHAR), 
	"PROD_NAME_ABBRV" VARCHAR2(5 CHAR), 
	"DEVICE_CODE" VARCHAR2(50 CHAR), 
	"PSUR_GROUP_NAME" VARCHAR2(50 CHAR), 
	"COMMENTS" VARCHAR2(1000 CHAR), 
	"IND_CODED" VARCHAR2(250 CHAR), 
	"IND_REPTD" VARCHAR2(250 CHAR), 
	"DRL_ID_J" VARCHAR2(45 CHAR), 
	"DRUG_CODE_J" VARCHAR2(20 CHAR), 
	"DRUG_CODE_TYPE_J" NUMBER, 
	"INDICATION_TEXT_J" VARCHAR2(250 CHAR), 
	"IND_CODE_STATUS_J" NUMBER, 
	"IND_HLGT_J" VARCHAR2(250 CHAR), 
	"IND_HLT_J" VARCHAR2(250 CHAR), 
	"IND_LLT_CODE_J" VARCHAR2(50 CHAR), 
	"IND_LLT_J" VARCHAR2(250 CHAR), 
	"IND_SOC_J" VARCHAR2(250 CHAR), 
	"PROD_NAME_J" VARCHAR2(70 CHAR), 
	"IND_SYN_CODE_J" NUMBER, 
	"IND_CODED_J" VARCHAR2(250 CHAR), 
	"IND_REPTD_J" VARCHAR2(250 CHAR), 
	"DEV_INTL_BIRTH_DATE" DATE, 
	"COMMENTS_J" VARCHAR2(1000 CHAR)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_DATA_01" 
 LOB ("PROD_GENERIC_NAME") STORE AS BASICFILE "RLP_PROD_GENERIC_NAME_CLOB"(
  TABLESPACE "AM_MART_LOB_01" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) 
 LOB ("PROD_GENERIC_NAME_J") STORE AS BASICFILE "RLP_PROD_GEN_NAME_J_CLOB"(
  TABLESPACE "AM_MART_LOB_01" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;
--------------------------------------------------------
--  DDL for Index PK_RM_LM_PRODUCT
--------------------------------------------------------

  CREATE UNIQUE INDEX "MART_USER"."PK_RM_LM_PRODUCT" ON "MART_USER"."RM_LM_PRODUCT" ("ENTERPRISE_ID", "PRODUCT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_INDEX_01" ;
--------------------------------------------------------
--  Constraints for Table RM_LM_PRODUCT
--------------------------------------------------------

  ALTER TABLE "MART_USER"."RM_LM_PRODUCT" ADD CONSTRAINT "PK_RM_LM_PRODUCT" PRIMARY KEY ("ENTERPRISE_ID", "PRODUCT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_INDEX_01"  ENABLE;
 
  ALTER TABLE "MART_USER"."RM_LM_PRODUCT" MODIFY ("ENTERPRISE_ID" NOT NULL ENABLE);