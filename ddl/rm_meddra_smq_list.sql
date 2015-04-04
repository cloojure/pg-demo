--------------------------------------------------------
--  File created - Saturday-April-04-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table RM_MEDDRA_SMQ_LIST
--------------------------------------------------------

  CREATE TABLE "MART_USER"."RM_MEDDRA_SMQ_LIST" 
   (	"GLOBAL_DICT_ID" NUMBER DEFAULT SYS_CONTEXT('ARGUS_MART_CTX','meddra_global_dict_id'), 
	"SMQ_CODE" NUMBER, 
	"SMQ_NAME" VARCHAR2(100 CHAR), 
	"SMQ_LEVEL" NUMBER, 
	"SMQ_DESCRIPTION" VARCHAR2(3000 CHAR), 
	"SMQ_SOURCE" VARCHAR2(2000 CHAR), 
	"SMQ_NOTE" VARCHAR2(2000 CHAR), 
	"MEDDRA_VERSION" VARCHAR2(5 CHAR), 
	"STATUS" VARCHAR2(1 CHAR), 
	"SMQ_ALGORITHM" VARCHAR2(100 CHAR)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_DATA_01" ;
--------------------------------------------------------
--  DDL for Index PK_RM_MEDDRA_SMQ_LIST
--------------------------------------------------------

  CREATE UNIQUE INDEX "MART_USER"."PK_RM_MEDDRA_SMQ_LIST" ON "MART_USER"."RM_MEDDRA_SMQ_LIST" ("GLOBAL_DICT_ID", "SMQ_CODE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_INDEX_01" ;
--------------------------------------------------------
--  Constraints for Table RM_MEDDRA_SMQ_LIST
--------------------------------------------------------

  ALTER TABLE "MART_USER"."RM_MEDDRA_SMQ_LIST" ADD CONSTRAINT "PK_RM_MEDDRA_SMQ_LIST" PRIMARY KEY ("GLOBAL_DICT_ID", "SMQ_CODE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "AM_MART_INDEX_01"  ENABLE;
 
  ALTER TABLE "MART_USER"."RM_MEDDRA_SMQ_LIST" MODIFY ("GLOBAL_DICT_ID" NOT NULL ENABLE);
 
  ALTER TABLE "MART_USER"."RM_MEDDRA_SMQ_LIST" MODIFY ("SMQ_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "MART_USER"."RM_MEDDRA_SMQ_LIST" MODIFY ("SMQ_LEVEL" NOT NULL ENABLE);
 
  ALTER TABLE "MART_USER"."RM_MEDDRA_SMQ_LIST" MODIFY ("MEDDRA_VERSION" NOT NULL ENABLE);
 
  ALTER TABLE "MART_USER"."RM_MEDDRA_SMQ_LIST" MODIFY ("STATUS" NOT NULL ENABLE);
 
  ALTER TABLE "MART_USER"."RM_MEDDRA_SMQ_LIST" MODIFY ("SMQ_ALGORITHM" NOT NULL ENABLE);
