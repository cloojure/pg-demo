DROP   MATERIALIZED   VIEW IF EXISTS case_version_table CASCADE;
CREATE MATERIALIZED   VIEW           case_version_table 
  (enterprise_id, case_id, effective_start_date, date_locked, effective_end_date, version_num)
AS
   WITH drcm AS
        (SELECT   enterprise_id, case_id,
                  MAX (effective_start_date) effective_start_date,
                 COALESCE (close_date, date_locked) date_locked
             FROM rm_case_master
            WHERE COALESCE (close_date, date_locked) IS NOT NULL
               OR effective_end_date = TO_DATE ('99991231', 'yyyymmdd')
         GROUP BY enterprise_id, case_id, COALESCE (close_date, date_locked))
   SELECT dcl.enterprise_id, dcl.case_id, dcl.effective_start_date,
          dcl.date_locked,
          COALESCE
             (LEAD (effective_start_date, 1) OVER (PARTITION BY dcl.enterprise_id, dcl.case_id ORDER BY effective_start_date),
              TO_DATE ('31129999', 'ddmmyyyy')
             ) effective_end_date,
          ROW_NUMBER () OVER (PARTITION BY dcl.enterprise_id, dcl.case_id ORDER BY dcl.effective_start_date)
                                                                  version_num
     FROM drcm dcl
