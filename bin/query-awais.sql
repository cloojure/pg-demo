
-- Awais query Friday 2015-3-13

\timing on

-- explain
select distinct
      ce.pref_term
--  , cea.det_listedness_id
    , cm.case_num
    , cn.narrative
    , cp.generic_name
--  , cpi.gender_id

  from
      rm_case_Event           ce
--  , rm_case_event_assess    cea
    , rm_case_master          cm
    , rm_case_narrative       cn
    , rm_case_product         cp
--  , rm_case_pat_info        cpi

  where
          cm.case_id    = ce.case_id
      and cm.case_id    = cp.case_id
--    and cm.case_id    = cea.case_id
      and cm.case_id    = cn.case_id
--    and cm.case_id    = cpi.case_id
--    and ce.seq_num    = cea.event_seq_num
--    and cp.seq_num    = cea.prod_seq_num
--    and generic_name  = 'IBUPROFEN'
;

