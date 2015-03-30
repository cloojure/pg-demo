SELECT   cm.case_id,
         cm.case_num,
         ce.desc_reptd,
         lp.prod_name,
         cm.close_date,
         cm.date_locked,
         cm.close_notes,
         cm.last_update_time,
         cm.init_rept_date
  FROM   case_master cm,
         case_event ce,
         case_product cp,
         case_study cs,
         lm_product lp,
         case_assess ca
 WHERE       cm.case_id = ce.case_id
         AND cm.case_id = cp.case_id
         AND cm.case_id = cs.case_id
         AND cm.case_id = ca.case_id
         AND coalesce(cp.pat_exposure, cp.product_id) = lp.product_id
         AND cp.drug_type = 1
         AND cs.classification_id = 1
         and lp.prod_name in ('RxLogi-Quick Tablet 15 mg')
         AND (date_trunc('day', cm.init_rept_date) >= date_trunc('day', current_date - 14)
              OR EXISTS
                 (SELECT   1
                    FROM   case_followup cf
                   WHERE   cm.case_id = cf.case_id AND cf.significant = 1
                           AND date_trunc('day', cf.receipt_date) >=
                                 date_trunc('day', current_date - 14))
             )
         AND (ca.seriousness = 1 OR ce.seriousness = 1)
         AND NOT EXISTS
               (SELECT   1
                  FROM   case_classifications cc, lm_case_classification lcc
                 WHERE   cc.classification_id = lcc.classification_id
                         AND cc.case_id = cm.case_id
                         AND lcc.description IN
                                  ('Non-Valid', 'Pre-Clinical Toxicity'));

