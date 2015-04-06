--------------------------------------------------------
--  File created - Monday-April-06-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for View CASE_VERSION_TABLE
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "MART_USER"."CASE_VERSION_TABLE" 
    ("ENTERPRISE_ID", "CASE_ID", "EFFECTIVE_START_DATE", "DATE_LOCKED", "EFFECTIVE_END_DATE", "VERSION_NUM") 
  AS 
  WITH drcm AS
        (SELECT   a.enterprise_id, a.case_id,
                  MAX (b.CASE_REVISION_DATE ) effective_start_date,
                  NVL (a.close_date, a.date_locked) date_locked
             FROM rm_case_rev_master b , rm_CASE_master a
            WHERE a.case_id = b.case_id and a.DLP_REVISION_NUMBER = b.DCM_XREF_NUM and
            ( NVL (a.close_date, a.date_locked) IS NOT NULL
               OR a.effective_end_date = TO_DATE ('99991231', 'yyyymmdd'))
         GROUP BY a.enterprise_id, a.case_id, NVL (a.close_date, a.date_locked))
   SELECT dcl.enterprise_id, dcl.case_id, dcl.effective_start_date,
          dcl.date_locked,
          NVL
             (LEAD (effective_start_date, 1) OVER (PARTITION BY dcl.enterprise_id, dcl.case_id ORDER BY effective_start_date),
              TO_DATE ('31129999', 'ddmmyyyy')
             ) effective_end_date,
          ROW_NUMBER () OVER (PARTITION BY dcl.enterprise_id, dcl.case_id ORDER BY dcl.effective_start_date)
                                                                  version_num
     FROM drcm dcl ;
