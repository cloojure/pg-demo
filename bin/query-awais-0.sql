
-- Awais query Friday 2015-3-13

select distinct cm.case_num, cn.narrative, cpi.gender_id, ce.pref_term, cp.generic_name, cea.det_listedness_id 
  from  rm_case_event_assess cea, rm_case_pat_info cpi,
        rm_case_master cm, rm_case_Event ce, 
        rm_case_product cp, rm_case_narrative cn 
  where 
      cm.case_id=ce.case_id and cm.case_id=cp.case_id
      and cea.case_id=cm.case_id and ce.seq_num=cea.event_seq_num
      and cp.seq_num=cea.prod_seq_num and cm.case_id=cn.case_id
      and cm.case_id=cpi.case_id and generic_name='IBUPROFEN' ;


