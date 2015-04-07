
2015-4-7
  Results from small PVA Schema on postgres (alan-c38-1 ec2 instance)
      > select count(*) from rm_case_master;  --> 3709

conn:           "//10.100.6.130:5432/demo"  -- dbname="demo"
username:       demo
password:       demo123

-----------------------------------------------------------------------------
Oracle -> Postgres conversions
  NVL(x,y)                          -> coalesce(x,y)
  TRUNC(cm.init_rept_date)          -> date_trunc('day', cm.init_rept_date)
  SYSDATE                           -> current_date

-----------------------------------------------------------------------------

 -- 'TYLENOL%'  
 -- 'IBUPROFEN%'  

drop table           alan_tmp ;

drop table if exists alan_tmp ;
create table alan_tmp
  as select
    rm_case_master.case_id,
    rm_case_master.case_num,
    rm_case_master.create_time,
    rm_case_master.dlp_revision_number,

    rm_case_event.art_code,
    rm_case_event.code_status,
    rm_case_event.details,
    rm_case_event.diagnosis,

    rm_case_event_assess.det_listedness_id,

    rm_case_product.product_name,
    rm_case_product.generic_name,

    rm_case_narrative.narrative,

    rm_case_pat_info.gender_id 
  from
    rm_case_master,
    rm_case_event,
    rm_case_event_assess,
    rm_case_product,
    rm_case_narrative, 
    rm_case_pat_info
  where
    rm_case_master.case_id = rm_case_event.case_id and
    rm_case_master.case_id = rm_case_event_assess.case_id and
    rm_case_master.case_id = rm_case_product.case_id and
    rm_case_master.case_id = rm_case_narrative.case_id and
    rm_case_master.case_id = rm_case_pat_info.case_id and
    UPPER(rm_case_product.product_name) LIKE 'IBUPROFEN%'
    and rm_case_event.seq_num = rm_case_event_assess.event_seq_num ;

SELECT 66878
Time: 162.125 ms

----------------------------------------------------------------------------
-- PVR-ST-016-001

drop table           alan_tmp ;

drop table if exists alan_tmp ;
create table alan_tmp as
SELECT cm.case_id,
       cm.case_num,
       ce.desc_reptd,
       lp.prod_name,
       cm.close_date,
       cm.date_locked,
       cm.close_notes,
       cm.last_update_time,
       TO_CHAR(cm.init_rept_date, 'dd-mon-yyyy') init_rept_date
  FROM rm_case_master  cm,
       rm_case_event   ce,
       rm_case_product cp,
       rm_case_study   cs,
       rm_lm_product   lp,
       rm_case_assess  ca
 WHERE cm.case_id = ce.case_id
   AND cm.case_id = cp.case_id
   AND cm.case_id = cs.case_id
   AND cm.case_id = ca.case_id
   AND coalesce(cp.pat_exposure, cp.product_id) = lp.product_id
   AND cp.drug_type = 1
   AND cs.classification_id = 1
   AND lp.prod_name in ('RxLogi-Quick Tablet 15 mg')
   AND (date_trunc('day', (cm.init_rept_date)) >= date_trunc('day', (current_date - 14)) OR EXISTS
         (SELECT 1
            FROM rm_case_followup cf
          WHERE cm.case_id = cf.case_id
            AND cf.significant = 1
            AND date_trunc('day', (cf.receipt_date)) >= date_trunc('day', (current_date - 14))))
   AND (ca.seriousness = 1 OR ce.seriousness = 1)
   AND NOT EXISTS
 (SELECT 1
          FROM rm_case_classifications cc, rm_lm_case_classification lcc
         WHERE cc.classification_id = lcc.classification_id
           AND cc.case_id = cm.case_id
           AND lcc.description IN ('Non-Valid', 'Pre-Clinical Toxicity'));

SELECT 0
Time: 34.489 ms

----------------------------------------------------------------------------
-- PVR-ST-016-002

drop table           alan_tmp ;

drop table if exists alan_tmp ;
create table alan_tmp as
SELECT cmcf.case_id,
       cmcf.case_num,
       cmcf.close_date,
       cmcf.date_locked,
       cmcf.close_notes,
       cmcf.last_update_time,
       ce.desc_reptd,
       lp.prod_name
  FROM
     (SELECT cm.*, cf.receipt_date, cf.significant
       FROM rm_case_master cm
              left outer join
            rm_case_followup cf
       ON cm.case_id = cf.case_id
     ) as cmcf,
     rm_case_event        ce,
     rm_case_product      cp,
     rm_lm_product_family lpf,
     rm_lm_product        lp,
     rm_lm_countries      lc
 WHERE cmcf.case_id = ce.case_id
   AND cmcf.case_id = cp.case_id
   AND cmcf.country_id = lc.country_id
   AND cp.family_id = lpf.family_id
   AND lp.family_id = lpf.family_id
   AND lp.prod_name = 'Motrin'
   AND ce.seriousness = 1
   AND cp.drug_type = 1
   AND LC.COUNTRY <> 'CHINA'

   -- ***** must fix others to look like this *****
   AND ( (cmcf.init_rept_date between       -- a date last month [1..31]
              (date_trunc('month', current_date) - interval '1 month') and
              (date_trunc('month', current_date) - interval '1 day'))
         OR 
         ( (cmcf.receipt_date between         -- a date last month [1..31]
                  (date_trunc('month', current_date) - interval '1 month') and
                  (date_trunc('month', current_date) - interval '1 day'))
           AND cmcf.significant = 1))

   AND NOT EXISTS
     (SELECT 1
          FROM rm_case_classifications      cc, 
               rm_lm_case_classification    lcc
         WHERE cc.classification_id = lcc.classification_id
           AND cc.case_id = cmcf.case_id
           AND lcc.description IN ('Non-Valid', 'Pre-Clinical Toxicity'));

SELECT 108
Time: 35.343 ms

----------------------------------------------------------------------------
-- PVR-ST-016-003

drop table           alan_tmp ;

drop table if exists alan_tmp ;
create table alan_tmp as
SELECT cm.case_id,
       cm.case_num,
       ce.desc_reptd,
       lp.prod_name,
       cm.close_date,
       cm.date_locked,
       cm.close_notes,
       cm.last_update_time,
       lrt.report_type
  FROM rm_case_master    cm,
       rm_case_event     ce,
       rm_case_followup cf,
       rm_case_product   cp,
       rm_lm_product     lp,
       rm_lm_report_type lrt
 WHERE cm.case_id = ce.case_id
   AND cm.case_id = cp.case_id
   and cm.case_id = cf.case_id(+)
   AND cm.rpt_type_id = lrt.rpt_type_id
   AND coalesce(cp.pat_exposure, cp.product_id) = lp.product_id
   AND UPPER(lrt.report_type) IN
       ('SPONTANEOUS', 'LITERATURE MARKETED', 'OTHER', 'UNKNOWN')
   AND cp.drug_type = 1
      --AND UPPER(lp.prod_name) IN ('RXLOGI-QUICK TABLET 15 MG')
   AND (cm.init_rept_date between date_trunc('day', (add_months(current_date, -1), 'MM')) and
       date_trunc('day', (current_date, 'MM')) - 1 or
       (cf.receipt_date between date_trunc('day', (add_months(current_date, -1), 'MM')) and
       date_trunc('day', (current_date, 'MM')) - 1 and cf.significant = 1))
   AND NOT EXISTS
 (SELECT 1
          FROM rm_case_classifications cc, rm_lm_case_classification lcc
         WHERE cc.classification_id = lcc.classification_id
           AND cc.case_id = cm.case_id
           AND lcc.description IN ('Non-Valid', 'Pre-Clinical Toxicity'));

run 1: Time = 48.4 sec
run 2: Time = 48.4 sec

----------------------------------------------------------------------------
-- PVR-ST-016-004

drop table           alan_tmp ;

drop table if exists alan_tmp ;
create table alan_tmp as
SELECT cm.case_id,
       cm.case_num,
       ce.desc_reptd,
       lp.prod_name,
       cm.close_date,
       cm.date_locked,
       cm.close_notes,
       cm.last_update_time
  FROM rm_case_master  cm,
       rm_case_event   ce,
       rm_case_product cp,
       rm_case_study   cs,
       rm_lm_product   lp,
       rm_lm_countries lc
 WHERE cm.case_id = ce.case_id
   AND cm.case_id = cp.case_id
   AND cm.case_id = cs.case_id
   AND cm.country_id = lc.country_id
   AND coalesce(cp.pat_exposure, cp.product_id) = lp.product_id
   AND cp.drug_type = 1
   AND coalesce(cs.classification_id, 0) <> 1
   AND lp.prod_name IN ('RxLogi-Quick Tablet 15 mg')
   AND lc.country IN ('FRANCE',
                      'MONACO',
                      'FRENCH GUIANA',
                      'FRENCH POLYNESIA',
                      'FRENCH SOUTHERN TERRITORIES',
                      'GUADELOUPE',
                      'MARTINIQUE',
                      'MAYOTTE',
                      'NEW CALEDONIA',
                      'RÉUNION',
                      'SAINT PIERRE AND MIQUELON',
                      'WALLIS AND FUTUNA')
   AND (date_trunc('day', (cm.init_rept_date)) >= date_trunc('day', (current_date)) OR EXISTS
        (SELECT 1
           FROM rm_case_followup cf
          WHERE cm.case_id = cf.case_id
            AND cf.significant = 1
            AND date_trunc('day', (cf.receipt_date)) >= date_trunc('day', (current_date))))
   AND NOT EXISTS
 (SELECT 1
          FROM rm_case_classifications cc, rm_lm_case_classification lcc
         WHERE cc.classification_id = lcc.classification_id
           AND cc.case_id = cm.case_id
           AND lcc.description IN ('Non-Valid', 'Pre-Clinical Toxicity'));

run 1: Time = 22.2 sec
run 2: Time = 22.2 sec

----------------------------------------------------------------------------
-- PVR-ST-016-005

drop table           alan_tmp ;

drop table if exists alan_tmp ;
create table alan_tmp as
SELECT cm.case_id,
       cm.case_num,
       ce.desc_reptd,
       lp.prod_name,
       cm.close_date,
       cm.date_locked,
       cm.close_notes,
       cm.last_update_time
  FROM rm_case_master  cm,
       rm_case_event   ce,
       rm_case_product cp,
       rm_lm_product   lp,
       rm_lm_countries lc
 WHERE cm.case_id = ce.case_id
   AND cm.case_id = cp.case_id
   AND cm.country_id = lc.country_id
   AND coalesce(cp.pat_exposure, cp.product_id) = lp.product_id
   AND cp.drug_type = 1
   AND lp.prod_name IN ('RxLogi-Quick Tablet 15 mg')
   AND lc.country IN ('UNITED STATES', 'CANADA')
   AND (date_trunc('day', (cm.init_rept_date)) >= to_date('1-Jan-2015', 'dd-mon-yyyy') OR
       EXISTS (SELECT 1
                 FROM rm_case_followup cf
                WHERE cm.case_id = cf.case_id
                  AND cf.significant = 1
                  AND date_trunc('day', (cf.receipt_date)) >=
                      to_date('1-Jan-2015', 'dd-mon-yyyy')))
   AND NOT EXISTS
 (SELECT 1
          FROM rm_case_classifications cc, rm_lm_case_classification lcc
         WHERE cc.classification_id = lcc.classification_id
           AND cc.case_id = cm.case_id
           AND lcc.description IN ('Non-Valid', 'Pre-Clinical Toxicity'));

run 1: Time = 45.9 sec
run 2: Time = 46.0 sec

----------------------------------------------------------------------------
-- PVR-ST-016-017

drop table           alan_tmp ;

drop table if exists alan_tmp ;
create table alan_tmp as
SELECT cm.case_id,
       cm.case_num,
       ce.desc_reptd,
       cp.product_name,
       cm.close_date,
       cm.date_locked,
       cm.close_notes,
       cm.last_update_time
  FROM rm_case_master    cm,
       rm_case_event     ce,
       rm_case_product   cp,
       rm_lm_report_type lrt,
       rm_case_reporters cr
 WHERE cm.case_id = ce.case_id
   AND cm.case_id = cp.case_id
   AND cm.case_id = cr.case_id
   AND cm.rpt_type_id = lrt.rpt_type_id
   AND cr.primary_contact = 1
   AND cr.hcp_flag = 1
   AND cp.drug_type = 1
   AND (date_trunc('day', (cm.init_rept_date)) between
       to_date('1-Jan-2014', 'dd-mon-yyyy') and
       to_date('31-Jan-2014', 'dd-mon-yyyy') OR EXISTS
        (SELECT 1
           FROM rm_case_followup cf1
          WHERE cm.case_id = cf1.case_id
            AND cf1.significant = 1
            AND date_trunc('day', (cf1.receipt_date)) between
                to_date('1-Jan-2014', 'dd-mon-yyyy') and
                to_date('31-Jan-2014', 'dd-mon-yyyy')))
   AND (lrt.report_type IN
       ('Spontaneous', 'Literature Marketed', 'Other', 'Unknown') OR
       (lrt.report_type IN ('Sponsored Trial') AND EXISTS
        (SELECT 1
            FROM rm_case_study        cs1,
                 rm_case_event        ce1,
                 rm_case_event_assess cea1,
                 rm_lm_causality      lc_rpt,
                 rm_lm_causality      lc_det
           WHERE cs1.case_id = cm.case_id
             AND cs1.case_id = ce1.case_id
             AND ce1.case_id = cea1.case_id
             AND ce1.seq_num = cea1.event_seq_num
             AND cea1.rpt_causality_id = lc_rpt.causality_id
             AND cea1.det_causality_id = lc_det.causality_id
             AND coalesce(cs1.code_broken, -1) <> 0
             AND ce1.seriousness = 1
             AND (lc_rpt.causality IN
                 ('Almost Certain', 'Probable', 'Possible', 'Related') OR
                 lc_det.causality IN
                 ('Almost Certain', 'Probable', 'Possible', 'Related')))))
   AND NOT EXISTS
 (SELECT 1
          FROM rm_lm_case_classification lcc, rm_case_classifications cc1
         WHERE cc1.case_id = cm.case_id
           AND cc1.classification_id = lcc.classification_id
           AND lcc.description IN ('Pre-Clinical Toxicity', 'Non-Valid'))
   AND (ce.sc_death = 1 OR ce.evt_outcome_id = 1);

run 1: Time = 95.6 sec
run 2: Time = 91.8 sec

----------------------------------------------------------------------------
-- PVR-ST-016-018

drop table           alan_tmp ;

drop table if exists alan_tmp ;
create table alan_tmp as
SELECT cm.case_id,
       cm.case_num,
       ce.desc_reptd,
       cp.product_name,
       cm.close_date,
       cm.date_locked,
       cm.close_notes,
       cm.last_update_time
  FROM rm_case_master     cm,
       rm_case_event      ce,
       rm_case_product    cp,
       rm_case_prod_drugs cpd,
       rm_lm_report_type  lrt,
       rm_case_reporters  cr
 WHERE cm.case_id = ce.case_id
   AND cm.case_id = cp.case_id
   AND cm.case_id = cr.case_id
   AND cm.case_id = cpd.case_id
   AND cm.rpt_type_id = lrt.rpt_type_id
   AND cr.primary_contact = 1
   AND cr.hcp_flag = 1
   AND cp.drug_type = 1
   AND date_trunc('day', (cm.init_rept_date)) between
       to_date('1-Jan-2014', 'dd-mon-yyyy') and
       to_date('1-Jan-2014', 'dd-mon-yyyy')
   AND (lrt.report_type IN
       ('Spontaneous', 'Literature Marketed', 'Other', 'Unknown') OR
       (lrt.report_type IN ('Sponsored Trial') AND EXISTS
        (SELECT 1
            FROM rm_case_study        cs1,
                 rm_case_event        ce1,
                 rm_case_event_assess cea1,
                 rm_lm_causality      lc_rpt,
                 rm_lm_causality      lc_det
           WHERE cs1.case_id = cm.case_id
             AND cs1.case_id = ce1.case_id
             AND ce1.case_id = cea1.case_id
             AND ce1.seq_num = cea1.event_seq_num
             AND cea1.rpt_causality_id = lc_rpt.causality_id
             AND cea1.det_causality_id = lc_det.causality_id
             AND coalesce(cs1.code_broken, -1) <> 0
             AND ce1.seriousness = 1
             AND (lc_rpt.causality IN
                 ('Almost Certain', 'Probable', 'Possible', 'Related') OR
                 lc_det.causality IN
                 ('Almost Certain', 'Probable', 'Possible', 'Related')))))
   AND NOT EXISTS
 (SELECT 1
          FROM rm_lm_case_classification lcc, rm_case_classifications cc1
         WHERE cc1.case_id = cm.case_id
           AND cc1.classification_id = lcc.classification_id
           AND lcc.description IN ('Pre-Clinical Toxicity', 'Non-Valid'))
   AND ((cpd.interaction = 1) OR
       (EXISTS (SELECT 1
                   FROM rm_meddra_smq_list msl, rm_meddra_smq_content msc
                  WHERE msl.smq_code = msc.smq_code
                    AND msc.term_scope = 2
                    AND msl.smq_name = 'SCQ-METEOR CRM Drug Interactions'
                    AND (TO_CHAR(ce.art_code) = TO_CHAR(msc.term_code) AND
                        msc.term_level = 4 OR
                        TO_CHAR(ce.inc_code) = TO_CHAR(msc.term_code) AND
                        msc.term_level = 5))));

run 1: Time = 49.6 sec
run 2: Time = 49.5 sec

----------------------------------------------------------------------------
-- PVR-ST-016-019

drop table           alan_tmp ;

drop table if exists alan_tmp ;
create table alan_tmp as
SELECT cm.case_id,
       cm.case_num,
       ce.desc_reptd,
       cp.product_name,
       cm.close_date,
       cm.date_locked,
       cm.close_notes,
       cm.last_update_time
  FROM rm_case_master     cm,
       rm_case_event      ce,
       rm_case_product    cp,
       rm_case_prod_drugs cpd,
       rm_lm_report_type  lrt,
       rm_case_reporters  cr
 WHERE cm.case_id = ce.case_id
   AND cm.case_id = cp.case_id
   AND cm.case_id = cr.case_id
   AND cm.case_id = cpd.case_id
   AND cm.rpt_type_id = lrt.rpt_type_id
   AND cr.primary_contact = 1
   AND cr.hcp_flag = 1
   AND cp.drug_type = 1
   AND date_trunc('day', (cm.init_rept_date)) between
       to_date('1-Jan-2014', 'dd-mon-yyyy') and
       to_date('1-Jan-2014', 'dd-mon-yyyy')
   AND (lrt.report_type IN
       ('Spontaneous', 'Literature Marketed', 'Other', 'Unknown') OR
       (lrt.report_type IN ('Sponsored Trial') AND EXISTS
        (SELECT 1
            FROM rm_case_study        cs1,
                 rm_case_event        ce1,
                 rm_case_event_assess cea1,
                 rm_lm_causality      lc_rpt,
                 rm_lm_causality      lc_det
           WHERE cs1.case_id = cm.case_id
             AND cs1.case_id = ce1.case_id
             AND ce1.case_id = cea1.case_id
             AND ce1.seq_num = cea1.event_seq_num
             AND cea1.rpt_causality_id = lc_rpt.causality_id
             AND cea1.det_causality_id = lc_det.causality_id
             AND coalesce(cs1.code_broken, -1) <> 0
             AND ce1.seriousness = 1
             AND (lc_rpt.causality IN
                 ('Almost Certain', 'Probable', 'Possible', 'Related') OR
                 lc_det.causality IN
                 ('Almost Certain', 'Probable', 'Possible', 'Related')))))
   AND NOT EXISTS
 (SELECT 1
          FROM rm_lm_case_classification lcc, rm_case_classifications cc1
         WHERE cc1.case_id = cm.case_id
           AND cc1.classification_id = lcc.classification_id
           AND lcc.description IN ('Pre-Clinical Toxicity', 'Non-Valid'));

run 1: Time = 49.8 sec
run 2: Time = 49.6 sec

----------------------------------------------------------------------------
-- PVR-ST-016-003

drop table           alan_tmp ;

drop table if exists alan_tmp ;
create table alan_tmp as
SELECT cm.case_id,
       cm.case_num,
       ce.desc_reptd,
       cp.product_name,
       cm.close_date,
       cm.date_locked,
       cm.close_notes,
       cm.last_update_time
  FROM rm_case_master    cm,
       rm_case_event     ce,
       rm_case_product   cp,
       rm_lm_report_type lrt,
       rm_case_reporters cr
 WHERE cm.case_id = ce.case_id
   AND cm.case_id = cp.case_id
   AND cm.case_id = cr.case_id
   AND cm.rpt_type_id = lrt.rpt_type_id
   AND cr.primary_contact = 1
   AND cr.hcp_flag = 1
   AND cp.drug_type = 1
   AND (date_trunc('day', (cm.init_rept_date)) >= '01-Jan-2014' OR EXISTS
        (SELECT 1
           FROM rm_case_followup cf1
          WHERE cm.case_id = cf1.case_id
            AND cf1.significant = 1
            AND date_trunc('day', (cf1.receipt_date)) >= '01-Jan-2014'))
   AND (date_trunc('day', (cm.init_rept_date)) <= '31-Dec-2014' OR EXISTS
        (SELECT 1
           FROM rm_case_followup cf1
          WHERE cm.case_id = cf1.case_id
            AND cf1.significant = 1
            AND date_trunc('day', (cf1.receipt_date)) <= '31-Dec-2014'))
   AND (lrt.report_type IN
       ('Spontaneous', 'Literature Marketed', 'Other', 'Unknown') OR
       (lrt.report_type IN ('Sponsored Trial') AND EXISTS
        (SELECT 1
            FROM rm_case_study        cs1,
                 rm_case_event        ce1,
                 rm_case_event_assess cea1,
                 rm_lm_causality      lc_rpt,
                 rm_lm_causality      lc_det
           WHERE cs1.case_id = cm.case_id
             AND cs1.case_id = ce1.case_id
             AND ce1.case_id = cea1.case_id
             AND ce1.seq_num = cea1.event_seq_num
             AND cea1.rpt_causality_id = lc_rpt.causality_id
             AND cea1.det_causality_id = lc_det.causality_id
             AND coalesce(cs1.code_broken, -1) <> 0
             AND ce1.seriousness = 1
             AND (lc_rpt.causality IN
                 ('Almost Certain', 'Probable', 'Possible', 'Related') OR
                 lc_det.causality IN
                 ('Almost Certain', 'Probable', 'Possible', 'Related')))))
   AND NOT EXISTS
 (SELECT 1
          FROM rm_lm_case_classification lcc, rm_case_classifications cc1
         WHERE cc1.case_id = cm.case_id
           AND cc1.classification_id = lcc.classification_id
           AND lcc.description IN ('Pre-Clinical Toxicity', 'Non-Valid'));

run 1: Time = 9089.8 sec (2.5 hours)
