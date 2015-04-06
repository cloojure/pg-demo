SELECT
  case_num, ca.seriousness, ca.agent_suspect, ll.listedness, leo.evt_outcome, 
  FROM rm_case_master       cm,
       rm_case_assess       ca,
       rm_lm_listedness     ll,
       rm_lm_evt_outcome    leo,
       rm_case_reg_reports  crr,
  WHERE cm.case_id = ca.case_id
    AND ca.listedness = ll.listedness_id
    AND ca.outcome = leo.evt_outcome_id
    AND (    COALESCE(ca.seriousness,-1)    < 0
          OR COALESCE(ca.agent_suspect,-1)  < 0
          OR COALESCE(ca.listedness,-1)     < 0
          OR COALESCE(ca.outcome,-1)        < 0 
        )
    AND cm.case_id = crr.case_id
