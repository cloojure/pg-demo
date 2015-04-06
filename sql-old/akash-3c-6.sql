\connect ubuntu_large
\timing
explain analyze
SELECT case_num, ca.seriousness, ca.agent_suspect, ll.listedness, leo.evt_outcome
FROM rm_case_master_6     cm
   , rm_case_assess_6     ca
   , rm_lm_listedness     ll
   , rm_lm_evt_outcome    leo
   , rm_case_reg_reports  crr
   , (SELECT DISTINCT case_id, prod_seq_num, 'Missing' causality
        FROM rm_case_event_assess cea1
        WHERE cea1.license_id = 0
          AND cea1.datasheet_id = 0
          AND cea1.deleted IS NULL
     ) cause
   , (SELECT case_id, prod_seq_num, event_seq_num 
        from rm_case_event_assess 
        where license_id > 0 
        group by case_id, prod_seq_num, event_seq_num
     ) listed
WHERE cm.case_id = ca.case_id
  AND cm.case_id = crr.case_id
  AND cm.case_id = cause.case_id
  AND cm.case_id = listed.case_id
  AND ca.listedness = ll.listedness_id
  AND ca.outcome = leo.evt_outcome_id
  AND (    COALESCE(ca.seriousness,-1)    < 0
        OR COALESCE(ca.agent_suspect,-1)  < 0
        OR COALESCE(ca.listedness,-1)     < 0
        OR COALESCE(ca.outcome,-1)        < 0 
      )

