SELECT   case_num, DECODE (ca.seriousness, 0, 'No', 1, 'Yes') seriousness,
                     DECODE (ca.agent_suspect,
                             0, 'No',
                             1, 'Yes',
                             2, 'Unknown'
                            ) agent_suspect,
                     ll.listedness lm_listedness, leo.evt_outcome,
                     NVL (causality, 'OK') causality, listed.listedness,
                     (SELECT report_type FROM rm_lm_report_type b WHERE cm.rpt_type_id = b.rpt_type_id) report_type
                FROM rm_case_master cm,
                     rm_case_assess ca,
                     rm_lm_listedness ll,
                     rm_lm_evt_outcome leo,
                     rm_case_reg_reports crr,
                     (SELECT DISTINCT cea1.case_id, prod_seq_num, 'Missing' causality
                                 FROM rm_case_event_assess cea1
                                WHERE cea1.license_id = 0
                                  AND cea1.datasheet_id = 0
                                  AND cea1.deleted IS NULL
                      AND (CASE
                              WHEN (   NVL (cea1.det_causality_id, -1) < 0
                                    OR NVL (cea1.rpt_causality_id, -1) < 0
                                      )
                                 THEN 1
                              ELSE 0
                           END
                          ) = 1) cause,
                     (SELECT case_id,  prod_seq_num , event_seq_num , decode(max(decode(det_listedness_id,1,0,1)),1,'OK','NO') listedness  from rm_case_event_assess where license_id >0 group by
                     case_id,  prod_seq_num , event_seq_num ) listed
               WHERE cm.case_id = ca.case_id
                 AND ca.listedness = ll.listedness_id(+)
                 AND ca.outcome = leo.evt_outcome_id(+)
     AND ( nvl(ca.seriousness,-1) < 0
                      OR causality IS NOT NULL
          OR nvl(ca.agent_suspect,-1) < 0
                      OR listed.listedness <> 'OK'
          OR nvl(ca.listedness,-1) < 0
          OR nvl(ca.outcome,-1) < 0
                     )
                 AND cm.case_id = crr.case_id
                 AND cm.case_id = cause.case_id(+)
                 AND cm.case_id = listed.case_id
            ORDER BY case_num
