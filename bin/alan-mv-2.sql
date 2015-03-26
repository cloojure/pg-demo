\timing on

drop   materialized view if exists
  rmc_m_e_n ;

explain -- analyze
create materialized view
  rmc_m_e_n
  as select
    rm_case_master.assessment_needed,
    rm_case_master.case_id,
    rm_case_master.case_num,
    rm_case_master.country_id,
    rm_case_master.create_time,
    rm_case_master.dlp_revision_number,

    rm_case_event.art_code,
    rm_case_event.code_status,
    rm_case_event.deleted,
    rm_case_event.desc_coded,
    rm_case_event.details,
    rm_case_event.diagnosis,

    rm_case_narrative.abbrev_narrative,
    rm_case_narrative.narrative
  from
    rm_case_master,
    rm_case_event,
    rm_case_narrative
  where
    rm_case_master.case_id = rm_case_event.case_id          and
    rm_case_master.case_id = rm_case_narrative.case_id
