SELECT   cm.case_id,
         cm.case_num,
         ce.desc_reptd,
         cp.product_name,
         cm.close_date,
         cm.date_locked,
         cm.close_notes,
         cm.last_update_time
  FROM   case_master cm,
         case_event ce,
         case_product cp,
         lm_report_type lrt,
         case_reporters cr
 WHERE       cm.case_id = ce.case_id
         AND cm.case_id = cp.case_id
         AND cm.case_id = cr.case_id
         AND cm.rpt_type_id = lrt.rpt_type_id
         AND cr.primary_contact = 1
         AND cr.hcp_flag = 1
         AND cp.drug_type = 1
        AND cm.state_id <> 1
         AND (date_trunc ('day', cm.init_rept_date) >= '01-Jan-2014'
              OR EXISTS
                   (SELECT   1
                      FROM   case_followup cf1
                     WHERE       cm.case_id = cf1.case_id
                             AND cf1.significant = 1
                             AND date_trunc ('day', cf1.receipt_date) >= '01-Jan-2014'))
         AND (date_trunc ('day', cm.init_rept_date) <= '31-Dec-2014'
              OR EXISTS
                   (SELECT   1
                      FROM   case_followup cf1
                     WHERE       cm.case_id = cf1.case_id
                             AND cf1.significant = 1
                             AND date_trunc ('day', cf1.receipt_date) <= '31-Dec-2014'))
         AND (lrt.report_type IN
                    ('Spontaneous', 'Literature Marketed', 'Other', 'Unknown')
              OR (lrt.report_type IN ('Sponsored Trial')
                  AND EXISTS
                        (SELECT   1
                           FROM   case_study cs1,
                                  case_event ce1,
                                  case_event_assess cea1,
                                  lm_causality lc_rpt,
                                  lm_causality lc_det
                          WHERE       cs1.case_id = cm.case_id
                                  AND cs1.case_id = ce1.case_id
                                  AND ce1.case_id = cea1.case_id
                                  AND ce1.seq_num = cea1.event_seq_num
                                  AND cea1.rpt_causality_id =
                                        lc_rpt.causality_id
                                  AND cea1.det_causality_id =
                                        lc_det.causality_id
                                  AND NVL (cs1.code_broken, -1) <> 0
                                  AND ce1.seriousness = 1
                                  AND (lc_rpt.causality IN
                                             ('Almost Certain',
                                              'Probable',
                                              'Possible',
                                              'Related')
                                       OR lc_det.causality IN
                                               ('Almost Certain',
                                                'Probable',
                                                'Possible',
                                                'Related')))))
         AND NOT EXISTS
               (SELECT   1
                  FROM   lm_case_classification lcc, case_classifications cc1
                 WHERE   cc1.case_id = cm.case_id
                         AND cc1.classification_id = lcc.classification_id
                         AND lcc.description IN
                                  ('Pre-Clinical Toxicity', 'Non-Valid'))
         AND EXISTS
               (SELECT   1
                  FROM   meddra_160_j.meddra_smq_list msl,
                         meddra_160_j.meddra_smq_content msc
                 WHERE   msl.smq_code = msc.smq_code AND msc.term_scope = 2
                         AND msl.smq_name IN
                                  ('SCQ-METEOR CRM Hepatic medical conditions')
                         AND TO_CHAR (ce.art_code) = TO_CHAR (msc.term_code)
                         AND msc.term_level = 4)

