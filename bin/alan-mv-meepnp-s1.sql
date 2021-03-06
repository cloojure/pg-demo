\timing on

drop   materialized view if exists
  rmc_meepnp_s1 ;

explain analyze
create materialized view
  rmc_meepnp_s1
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
    rm_case_product.product_name ILIKE 'TYLENOL%'  -- 'IBUPROFEN'
    and rm_case_event.seq_num = rm_case_event_assess.event_seq_num 

