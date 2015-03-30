\connect ubuntu_large
\timing
explain analyze
SELECT case_num, ca.seriousness, ca.agent_suspect, ll.listedness, leo.evt_outcome
FROM rm_case_master_5     cm
   , rm_case_assess_5     ca
   , rm_lm_listedness     ll
   , rm_lm_evt_outcome    leo
   , rm_case_reg_reports  crr
   , (SELECT DISTINCT case_id, prod_seq_num, 'Missing' causality
        FROM rm_case_event_assess cea1
        WHERE cea1.license_id = 0
          AND cea1.datasheet_id = 0
          AND cea1.deleted IS NULL
          AND (CASE
                 WHEN (   COALESCE (cea1.det_causality_id, -1) < 0
                       OR COALESCE (cea1.rpt_causality_id, -1) < 0
                      ) 
                   THEN 1
                   ELSE 0
               END) = 1
     ) cause
   , (SELECT case_id, prod_seq_num, event_seq_num, det_listedness_id  
        from rm_case_event_assess 
        where license_id > 0 
        group by case_id, prod_seq_num, event_seq_num
     ) listed
WHERE cm.case_id = ca.case_id
  AND cm.case_id = crr.case_id
  AND cm.case_id = cause.case_id    -- (+)
  AND ca.listedness = ll.listedness_id
  AND ca.outcome = leo.evt_outcome_id
  AND (    COALESCE(ca.seriousness,-1)    < 0
        OR COALESCE(ca.agent_suspect,-1)  < 0
        OR COALESCE(ca.listedness,-1)     < 0
        OR COALESCE(ca.outcome,-1)        < 0 
      )

