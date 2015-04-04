
create table rm_case_assess
(
  "enterprise_id" numeric(38),
  "case_id" numeric(38),
  "template_id" numeric(38),
  "listedness" numeric(38),
  "updated" date,
  "outcome" numeric(38),
  "comment_id" numeric(38),
  "comp_comment_id" numeric(38),
  "seriousness" numeric(38),
  "serious_notes" varchar(1000),
  "agent_suspect" numeric(38),
  "agent_notes" varchar(1000),
  "listedness_notes" varchar(1000),
  "bfarm_causality" numeric(10,0),
  "bfarm_manual_text" varchar(3900),
  "company_diagnosis" varchar(250),
  "company_diagnosis_notes" varchar(1000),
  "diagnosis_dict_id" numeric(38),
  "diagnosis_pref_code" varchar(30),
  "diagnosis_inc_code" varchar(30),
  "diagnosis_inc_term" varchar(250),
  "diagnosis_hlgt_code" varchar(30),
  "diagnosis_hlgt" varchar(250),
  "diagnosis_hlt_code" varchar(30),
  "diagnosis_hlt" varchar(250),
  "diagnosis_soc_code" varchar(30),
  "diagnosis_body_sys" varchar(250),
  "ud_text_1" varchar(100),
  "ud_text_2" varchar(100),
  "ud_text_3" varchar(100),
  "ud_text_4" varchar(100),
  "ud_text_5" varchar(100),
  "ud_text_6" varchar(100),
  "ud_text_7" varchar(100),
  "ud_text_8" varchar(100),
  "ud_text_9" varchar(100),
  "ud_text_10" varchar(100),
  "ud_text_11" varchar(100),
  "ud_text_12" varchar(100),
  "ud_date_1" date,
  "ud_date_2" date,
  "ud_date_3" date,
  "ud_date_4" date,
  "ud_date_5" date,
  "ud_date_6" date,
  "ud_date_7" date,
  "ud_date_8" date,
  "ud_date_9" date,
  "ud_date_10" date,
  "ud_date_11" date,
  "ud_date_12" date,
  "ud_number_1" numeric(30,10),
  "ud_number_2" numeric(30,10),
  "ud_number_3" numeric(30,10),
  "ud_number_4" numeric(30,10),
  "ud_number_5" numeric(30,10),
  "ud_number_6" numeric(30,10),
  "ud_number_7" numeric(30,10),
  "ud_number_8" numeric(30,10),
  "ud_number_9" numeric(30,10),
  "ud_number_10" numeric(30,10),
  "ud_number_11" numeric(30,10),
  "ud_number_12" numeric(30,10),
  "evaluation" varchar(3900),
  "evaluation_j" varchar(3900),
  "co_suspect_count" numeric(3,0),
  "event_synopsis" varchar(5),
  "event_primary" varchar(400),
  "diagnosis_reptd" varchar(255),
  "diagnosis_coded" varchar(255),
  "diagnosis_syn_code" numeric(38),
  "diagnosis_code_status" numeric(3,0),
  "event_primary_j" varchar(400),
  "agent_notes_j" varchar(1000),
  "company_diagnosis_j" varchar(250),
  "company_diagnosis_notes_j" varchar(1000),
  "diagnosis_body_sys_j" varchar(250),
  "diagnosis_coded_j" varchar(255),
  "diagnosis_code_status_j" numeric(38),
  "diagnosis_hlgt_j" varchar(250),
  "diagnosis_hlt_j" varchar(250),
  "diagnosis_inc_code_j" varchar(30),
  "diagnosis_inc_term_j" varchar(250),
  "diagnosis_reptd_j" varchar(255),
  "listedness_notes_j" varchar(1000),
  "serious_notes_j" varchar(1000),
  "ud_text_1_j" varchar(100),
  "ud_text_2_j" varchar(100),
  "ud_text_3_j" varchar(100),
  "ud_text_4_j" varchar(100),
  "ud_text_5_j" varchar(100),
  "ud_text_6_j" varchar(100),
  "ud_text_7_j" varchar(100),
  "ud_text_8_j" varchar(100),
  "ud_text_9_j" varchar(100),
  "ud_text_10_j" varchar(100),
  "ud_text_11_j" varchar(100),
  "ud_text_12_j" varchar(100),
  "diagnosis_syn_code_j" numeric(38),
  "deleted" date,
  "dlp_revision_number" numeric(38),
  "revision_delete_flag" number default 0,
  "deleted_flag" numeric(1,0),
  "effective_start_date" date,
  "effective_end_date" date
)

create table rm_case_classifications
(
  "enterprise_id" numeric(38),
  "case_id" numeric(38),
  "seq_num" numeric(38),
  "classification_id" numeric(38),
  "deleted" date,
  "dlp_revision_number" numeric(38),
  "revision_delete_flag" number default 0,
  "deleted_flag" numeric(1,0),
  "effective_start_date" date,
  "effective_end_date" date
)

create table rm_case_event
(
  "enterprise_id" numeric(38),
  "case_id" numeric(38),
  "seq_num" numeric(38),
  "desc_reptd" varchar(250),
  "desc_coded" varchar(255),
  "diagnosis" numeric(38),
  "onset" date,
  "onset_res" numeric(38),
  "onset_delay" varchar(40),
  "stop_date" date,
  "stop_date_res" numeric(38),
  "duration" varchar(40),
  "onset_latency" varchar(40),
  "evt_intensity_id" numeric(38),
  "evt_freq_id" numeric(38),
  "treated" numeric(38),
  "study_related" numeric(38),
  "rechall_related" numeric(38),
  "prod_seq_num" numeric(38),
  "evt_outcome_id" numeric(38),
  "receipt_date" date,
  "rpt_serious" numeric(38),
  "sc_death" numeric(38),
  "sc_hosp" numeric(38),
  "sc_cong_anom" numeric(38),
  "sc_threat" numeric(38),
  "sc_disable" numeric(38),
  "sc_int_req" numeric(38),
  "sc_other" numeric(38),
  "sc_other_text" varchar(30),
  "art_code" varchar(30),
  "pref_term" varchar(250),
  "inc_term" varchar(250),
  "body_sys" varchar(250),
  "med_serious" numeric(38),
  "past_hist" numeric(38),
  "dict_id" numeric(38),
  "dict_key" varchar(20),
  "code_status" numeric(38),
  "seriousness" numeric(38),
  "sort_id" numeric(38),
  "onset_date_partial" varchar(20),
  "stop_date_partial" varchar(20),
  "efficacy" numeric(38),
  "disease" numeric(38),
  "withdrawal" numeric(38),
  "hlgt" varchar(250),
  "hlt" varchar(250),
  "hlt_code" varchar(30),
  "hlgt_code" varchar(30),
  "soc_code" varchar(30),
  "study_dropped" numeric(4,0),
  "inc_code" varchar(30),
  "onset_minutes" numeric(38),
  "delay_minutes" numeric(38),
  "ud_text_1" varchar(100),
  "ud_text_2" varchar(100),
  "ud_text_3" varchar(100),
  "ud_text_4" varchar(100),
  "ud_text_5" varchar(100),
  "ud_text_6" varchar(100),
  "ud_text_7" varchar(100),
  "ud_text_8" varchar(100),
  "ud_text_9" varchar(100),
  "ud_text_10" varchar(100),
  "ud_text_11" varchar(100),
  "ud_text_12" varchar(100),
  "ud_date_1" date,
  "ud_date_2" date,
  "ud_date_3" date,
  "ud_date_4" date,
  "ud_date_5" date,
  "ud_date_6" date,
  "ud_date_7" date,
  "ud_date_8" date,
  "ud_date_9" date,
  "ud_date_10" date,
  "ud_date_11" date,
  "ud_date_12" date,
  "ud_number_1" numeric(30,10),
  "ud_number_2" numeric(30,10),
  "ud_number_3" numeric(30,10),
  "ud_number_4" numeric(30,10),
  "ud_number_5" numeric(30,10),
  "ud_number_6" numeric(30,10),
  "ud_number_7" numeric(30,10),
  "ud_number_8" numeric(30,10),
  "ud_number_9" numeric(30,10),
  "ud_number_10" numeric(30,10),
  "ud_number_11" numeric(30,10),
  "ud_number_12" numeric(30,10),
  "details" varchar(3900),
  "details_j" varchar(3900),
  "syn_code" numeric(38),
  "deleted" date,
  "study_related_reptd" numeric(1,0),
  "duration_unit_e2b" numeric(38),
  "onset_latency_unit_e2b" numeric(38),
  "onset_delay_unit_e2b" numeric(38),
  "duration_seconds" numeric(38),
  "onset_latency_seconds" numeric(38),
  "onset_delay_seconds" numeric(38),
  "body_sys_j" varchar(250),
  "code_status_j" numeric(38),
  "desc_coded_j" varchar(255),
  "desc_reptd_j" varchar(250),
  "hlgt_j" varchar(250),
  "hlt_j" varchar(250),
  "inc_code_j" varchar(30),
  "inc_term_j" varchar(250),
  "pref_term_j" varchar(250),
  "sc_other_text_j" varchar(30),
  "report_exclusion" numeric(38),
  "reportable" numeric(38),
  "ud_text_1_j" varchar(100),
  "ud_text_2_j" varchar(100),
  "ud_text_3_j" varchar(100),
  "ud_text_4_j" varchar(100),
  "ud_text_5_j" varchar(100),
  "ud_text_6_j" varchar(100),
  "ud_text_7_j" varchar(100),
  "ud_text_8_j" varchar(100),
  "ud_text_9_j" varchar(100),
  "ud_text_10_j" varchar(100),
  "ud_text_11_j" varchar(100),
  "ud_text_12_j" varchar(100),
  "syn_code_j" numeric(38),
  "infection" numeric(38),
  "rpt_exclude_cmt" varchar(1000),
  "rpt_exclude_cmt_j" varchar(1000),
  "dlp_revision_number" numeric(38),
  "revision_delete_flag" number default 0,
  "deleted_flag" numeric(1,0),
  "effective_start_date" date,
  "effective_end_date" date
)

create table rm_case_event_assess
(
  "enterprise_id" numeric(38),
  "case_id" numeric(38),
  "event_seq_num" numeric(38),
  "prod_seq_num" numeric(38),
  "datasheet_id" numeric(38),
  "license_id" numeric(38),
  "seq_num" numeric(38),
  "updated" date,
  "det_listedness_id" numeric(38),
  "rpt_causality_id" numeric(38),
  "det_causality_id" numeric(38),
  "listed_justify" varchar(1000),
  "cause_justify" varchar(1000),
  "lam_assessed" numeric(12,0),
  "deleted" date,
  "causality_assessment" varchar(50),
  "causality_score" numeric(38),
  "source_id" numeric(38),
  "prt_causality_id" numeric(38),
  "cause_justify_j" varchar(1000),
  "listed_justify_j" varchar(1000),
  "dlp_revision_number" numeric(38),
  "last_update_time" date,
  "revision_delete_flag" number default 0,
  "deleted_flag" numeric(1,0),
  "effective_start_date" date,
  "effective_end_date" date
)

create table rm_case_followup
(
  "enterprise_id" numeric(38),
  "case_id" numeric(38),
  "seq_num" numeric(38),
  "receipt_date" date,
  "safety_date" date,
  "significant" numeric(1,0),
  "auditlog_type" numeric(38),
  "time_stamp" date,
  "deleted" date,
  "significant_device" numeric(1,0),
  "data_cleanup" numeric(1,0),
  "justification" varchar(1000),
  "justification_id" numeric(38),
  "justification_j" varchar(1000),
  "just_receipt_date_j" varchar(1000),
  "receipt_date_j" date,
  "dlp_revision_number" numeric(38),
  "revision_delete_flag" number default 0,
  "deleted_flag" numeric(1,0),
  "effective_start_date" date,
  "effective_end_date" date
)

create table rm_case_master
(
  "enterprise_id" numeric(38),
  "case_id" numeric(38),
  "case_num" varchar(20),
  "rev" numeric(38),
  "workflow_seq_num" numeric(38),
  "last_workflow_seq_num" numeric(38),
  "create_time" date,
  "init_rept_date" date,
  "user_id" numeric(38),
  "last_update_time" date,
  "last_update_user_id" numeric(38),
  "requires_followup" numeric(38),
  "followup_date" date,
  "owner_id" numeric(38),
  "state_id" numeric(38),
  "country_id" numeric(38),
  "lang_id" numeric(38),
  "priority" numeric(38),
  "site_id" numeric(38),
  "seriousness" numeric(38),
  "rpt_type_id" numeric(38),
  "last_state_id" numeric(38),
  "assessment_needed" numeric(38),
  "priority_override" numeric(1,0),
  "sid" varchar(128),
  "safety_date" date,
  "normal_time" date,
  "max_time" date,
  "report_scheduling" numeric(38),
  "priority_assessment" numeric(38),
  "close_user_id" numeric(38),
  "close_date" date,
  "close_notes" varchar(200),
  "date_locked" date,
  "ud_text_1" varchar(100),
  "ud_text_2" varchar(100),
  "ud_text_3" varchar(100),
  "ud_text_4" varchar(100),
  "ud_text_5" varchar(100),
  "ud_text_6" varchar(100),
  "ud_text_7" varchar(100),
  "ud_text_8" varchar(100),
  "ud_text_9" varchar(100),
  "ud_text_10" varchar(100),
  "ud_text_11" varchar(100),
  "ud_text_12" varchar(100),
  "ud_date_1" date,
  "ud_date_2" date,
  "ud_date_3" date,
  "ud_date_4" date,
  "ud_date_5" date,
  "ud_date_6" date,
  "ud_date_7" date,
  "ud_date_8" date,
  "ud_date_9" date,
  "ud_date_10" date,
  "ud_date_11" date,
  "ud_date_12" date,
  "ud_number_1" numeric(30,10),
  "ud_number_2" numeric(30,10),
  "ud_number_3" numeric(30,10),
  "ud_number_4" numeric(30,10),
  "ud_number_5" numeric(30,10),
  "ud_number_6" numeric(30,10),
  "ud_number_7" numeric(30,10),
  "ud_number_8" numeric(30,10),
  "ud_number_9" numeric(30,10),
  "ud_number_10" numeric(30,10),
  "ud_number_11" numeric(30,10),
  "ud_number_12" numeric(30,10),
  "deleted" date,
  "due_soon" date,
  "global_num" varchar(20),
  "priority_date_assessed" date,
  "lam_assess_done" numeric(1,0),
  "e2b_ww_number" varchar(100),
  "worklist_owner_id" numeric(38),
  "susar" numeric(38),
  "last_update_event" date,
  "initial_justification" varchar(1000),
  "force_soon" date,
  "due_soon_j" date,
  "followup_date_j" date,
  "init_rept_date_j" date,
  "just_init_rept_date_j" varchar(1000),
  "ud_text_1_j" varchar(100),
  "ud_text_2_j" varchar(100),
  "ud_text_3_j" varchar(100),
  "ud_text_4_j" varchar(100),
  "ud_text_5_j" varchar(100),
  "ud_text_6_j" varchar(100),
  "ud_text_7_j" varchar(100),
  "ud_text_8_j" varchar(100),
  "ud_text_9_j" varchar(100),
  "ud_text_10_j" varchar(100),
  "ud_text_11_j" varchar(100),
  "ud_text_12_j" varchar(100),
  "initial_justification_j" varchar(1000),
  "dlp_revision_number" numeric(38),
  "revision_delete_flag" number default 0,
  "deleted_flag" numeric(1,0),
  "effective_start_date" date,
  "effective_end_date" date
)

create table rm_case_narrative
(
  "enterprise_id" numeric(38),
  "case_id" numeric(38),
  "abbrev_narrative" varchar(3900),
  "abbrev_narrative_j" varchar(3900),
  "narrative" varchar(3900),
  "narrative_j" varchar(3900),
  "deleted" date,
  "dlp_revision_number" numeric(38),
  "revision_delete_flag" number default 0,
  "deleted_flag" numeric(1,0),
  "effective_start_date" date,
  "effective_end_date" date
)

create table rm_case_pat_info
(
  "enterprise_id" numeric(38),
  "case_id" numeric(38),
  "pat_id" varchar(10),
  "pat_subj_num" varchar(20),
  "pat_initials" varchar(10),
  "pat_firstname" varchar(35),
  "pat_mi" varchar(15),
  "pat_lastname" varchar(50),
  "pat_address" varchar(120),
  "pat_city" varchar(35),
  "pat_state" varchar(40),
  "pat_postal_code" varchar(15),
  "pat_country" varchar(50),
  "pat_phone" varchar(20),
  "pat_dob" date,
  "pat_dob_res" numeric(38),
  "pat_age" numeric(22,7),
  "age_unit_id" numeric(38),
  "age_group_id" numeric(38),
  "gender_id" numeric(38),
  "pat_weight_lbs" numeric(22,7),
  "pat_weight_kg" numeric(22,7),
  "pat_height_in" numeric(22,7),
  "pat_height_cm" numeric(22,7),
  "ethnicity_id" numeric(38),
  "occupation_id" numeric(38),
  "pat_stat_preg" numeric(38),
  "rel_test_id" numeric(38),
  "pat_dob_partial" varchar(20),
  "rand_num" varchar(15),
  "confidential" numeric(1,0),
  "number_of_patients" varchar(8),
  "ud_text_1" varchar(100),
  "ud_text_2" varchar(100),
  "ud_text_3" varchar(100),
  "ud_text_4" varchar(100),
  "ud_text_5" varchar(100),
  "ud_text_6" varchar(100),
  "ud_text_7" varchar(100),
  "ud_text_8" varchar(100),
  "ud_text_9" varchar(100),
  "ud_text_10" varchar(100),
  "ud_text_11" varchar(100),
  "ud_text_12" varchar(100),
  "ud_date_1" date,
  "ud_date_2" date,
  "ud_date_3" date,
  "ud_date_4" date,
  "ud_date_5" date,
  "ud_date_6" date,
  "ud_date_7" date,
  "ud_date_8" date,
  "ud_date_9" date,
  "ud_date_10" date,
  "ud_date_11" date,
  "ud_date_12" date,
  "ud_number_1" numeric(30,10),
  "ud_number_2" numeric(30,10),
  "ud_number_3" numeric(30,10),
  "ud_number_4" numeric(30,10),
  "ud_number_5" numeric(30,10),
  "ud_number_6" numeric(30,10),
  "ud_number_7" numeric(30,10),
  "ud_number_8" numeric(30,10),
  "ud_number_9" numeric(30,10),
  "ud_number_10" numeric(30,10),
  "ud_number_11" numeric(30,10),
  "ud_number_12" numeric(30,10),
  "deleted" date,
  "child_only" numeric(1,0),
  "pat_bmi" numeric(22,7),
  "pat_body_area" numeric(22,7),
  "pat_address_j" varchar(120),
  "pat_city_j" varchar(35),
  "pat_firstname_j" varchar(35),
  "pat_lastname_j" varchar(50),
  "pat_mi_j" varchar(15),
  "pat_state_j" varchar(40),
  "ud_text_1_j" varchar(100),
  "ud_text_2_j" varchar(100),
  "ud_text_3_j" varchar(100),
  "ud_text_4_j" varchar(100),
  "ud_text_5_j" varchar(100),
  "ud_text_6_j" varchar(100),
  "ud_text_7_j" varchar(100),
  "ud_text_8_j" varchar(100),
  "ud_text_9_j" varchar(100),
  "ud_text_10_j" varchar(100),
  "ud_text_11_j" varchar(100),
  "ud_text_12_j" varchar(100),
  "pat_country_id" numeric(38),
  "dlp_revision_number" numeric(38),
  "revision_delete_flag" number default 0,
  "deleted_flag" numeric(1,0),
  "effective_start_date" date,
  "effective_end_date" date,
  "notes" varchar(3900),
  "notes_j" varchar(3900),
  "age_unit_id_at_vacc" numeric(38),
  "pat_age_at_vacc" number
)

create table rm_case_prod_drugs
(
  "enterprise_id" numeric(38),
  "case_id" numeric(38),
  "seq_num" numeric(38),
  "formulation_id" numeric(38),
  "concentration" varchar(10),
  "conc_units_id" numeric(38),
  "act_taken_id" numeric(38),
  "severity_id" numeric(38),
  "interaction" numeric(38),
  "contraind" numeric(38),
  "first_dose" date,
  "first_dose_res" numeric(38),
  "last_dose" date,
  "last_dose_res" numeric(38),
  "total_dose" numeric(22,7),
  "tot_dose_unit_id" numeric(38),
  "prev_use" numeric(38),
  "abuse" numeric(38),
  "overdose" numeric(38),
  "protocol" numeric(38),
  "dechallenge" numeric(38),
  "dechall_date" date,
  "rechallenge" numeric(38),
  "rechall_start" date,
  "rechall_stop" date,
  "rechall_outcome" numeric(38),
  "sort_id" numeric(38),
  "b_weight_grams" numeric(38),
  "n_siblings" numeric(38),
  "b_weight_ozs" numeric(38),
  "first_dose_partial" varchar(20),
  "last_dose_partial" varchar(20),
  "tampering" numeric(4,0),
  "cumulative_dose" numeric(22,7),
  "cumulative_dose_unit" varchar(10),
  "deleted" date,
  "ud_text_1" varchar(100),
  "ud_text_2" varchar(100),
  "ud_text_3" varchar(100),
  "ud_text_4" varchar(100),
  "ud_text_5" varchar(100),
  "ud_text_6" varchar(100),
  "ud_text_7" varchar(100),
  "ud_text_8" varchar(100),
  "ud_text_9" varchar(100),
  "ud_text_10" varchar(100),
  "ud_text_11" varchar(100),
  "ud_text_12" varchar(100),
  "ud_date_1" date,
  "ud_date_2" date,
  "ud_date_3" date,
  "ud_date_4" date,
  "ud_date_5" date,
  "ud_date_6" date,
  "ud_date_7" date,
  "ud_date_8" date,
  "ud_date_9" date,
  "ud_date_10" date,
  "ud_date_11" date,
  "ud_date_12" date,
  "ud_number_1" numeric(30,10),
  "ud_number_2" numeric(30,10),
  "ud_number_3" numeric(30,10),
  "ud_number_4" numeric(30,10),
  "ud_number_5" numeric(30,10),
  "ud_number_6" numeric(30,10),
  "ud_number_7" numeric(30,10),
  "ud_number_8" numeric(30,10),
  "ud_number_9" numeric(30,10),
  "ud_number_10" numeric(30,10),
  "ud_number_11" numeric(30,10),
  "ud_number_12" numeric(30,10),
  "cumulative_dose_unit_id" numeric(38),
  "obtain_drug_country_id" numeric(38),
  "drug_auth_country_id" numeric(38),
  "duration_seconds" numeric(38),
  "latency_seconds" numeric(38),
  "delay_seconds" numeric(38),
  "ud_text_1_j" varchar(100),
  "ud_text_2_j" varchar(100),
  "ud_text_3_j" varchar(100),
  "ud_text_4_j" varchar(100),
  "ud_text_5_j" varchar(100),
  "ud_text_6_j" varchar(100),
  "ud_text_7_j" varchar(100),
  "ud_text_8_j" varchar(100),
  "ud_text_9_j" varchar(100),
  "ud_text_10_j" varchar(100),
  "ud_text_11_j" varchar(100),
  "ud_text_12_j" varchar(100),
  "dlp_revision_number" numeric(38),
  "revision_delete_flag" number default 0,
  "deleted_flag" numeric(1,0),
  "effective_start_date" date,
  "effective_end_date" date
)

create table rm_case_product
(
  "enterprise_id" numeric(38),
  "case_id" numeric(38),
  "seq_num" numeric(38),
  "product_id" numeric(38),
  "drug_type" numeric(38),
  "pat_exposure" numeric(38),
  "manufacturer_id" numeric(38),
  "protocol_followed" numeric(38),
  "first_sus_prod" numeric(38),
  "selected_view" numeric(38),
  "sort_id" numeric(38),
  "views_available" numeric(38),
  "country_id" numeric(38),
  "qc_safety_date" date,
  "qc_sent_date" date,
  "qc_result_date" date,
  "qc_cross_reference" varchar(16),
  "who_drug_code" varchar(45),
  "co_drug_code" varchar(20),
  "product_name" varchar(70),
  "generic_name" varchar(3900),
  "generic_name_j" varchar(3900),
  "ud_text_1" varchar(100),
  "ud_text_2" varchar(100),
  "ud_text_3" varchar(100),
  "ud_text_4" varchar(100),
  "ud_text_5" varchar(100),
  "ud_text_6" varchar(100),
  "ud_text_7" varchar(100),
  "ud_text_8" varchar(100),
  "ud_text_9" varchar(100),
  "ud_text_10" varchar(100),
  "ud_text_11" varchar(100),
  "ud_text_12" varchar(100),
  "ud_date_1" date,
  "ud_date_2" date,
  "ud_date_3" date,
  "ud_date_4" date,
  "ud_date_5" date,
  "ud_date_6" date,
  "ud_date_7" date,
  "ud_date_8" date,
  "ud_date_9" date,
  "ud_date_10" date,
  "ud_date_11" date,
  "ud_date_12" date,
  "ud_number_1" numeric(30,10),
  "ud_number_2" numeric(30,10),
  "ud_number_3" numeric(30,10),
  "ud_number_4" numeric(30,10),
  "ud_number_5" numeric(30,10),
  "ud_number_6" numeric(30,10),
  "ud_number_7" numeric(30,10),
  "ud_number_8" numeric(30,10),
  "ud_number_9" numeric(30,10),
  "ud_number_10" numeric(30,10),
  "ud_number_11" numeric(30,10),
  "ud_number_12" numeric(30,10),
  "include_frequency" numeric(38),
  "sdrug_not_admin" numeric(38),
  "prod_reptd" varchar(255),
  "prod_coded" varchar(255),
  "prod_syn_id" numeric(38),
  "prod_code_status" numeric(3,0),
  "deleted" date,
  "study_product_num" numeric(2,0),
  "qc_quantity" varchar(10),
  "qc_return_date" date,
  "qc_complaint_cat_date" date,
  "qc_analysis_cat_date" date,
  "qc_analysis_summary_date" date,
  "primary_event" numeric(38),
  "medicinal_prod_id" varchar(10),
  "family_id" numeric(38),
  "existing_rpt_seq_mod_flg" numeric(38),
  "drug_code_j" varchar(20),
  "drug_code_type_j" numeric(38),
  "product_name_j" varchar(70),
  "prod_coded_j" varchar(255),
  "prod_reptd_j" varchar(255),
  "qc_cross_reference_j" varchar(16),
  "ud_text_1_j" varchar(100),
  "ud_text_2_j" varchar(100),
  "ud_text_3_j" varchar(100),
  "ud_text_4_j" varchar(100),
  "ud_text_5_j" varchar(100),
  "ud_text_6_j" varchar(100),
  "ud_text_7_j" varchar(100),
  "ud_text_8_j" varchar(100),
  "ud_text_9_j" varchar(100),
  "ud_text_10_j" varchar(100),
  "ud_text_11_j" varchar(100),
  "ud_text_12_j" varchar(100),
  "dlp_revision_number" numeric(38),
  "last_update_time" date,
  "revision_delete_flag" number default 0,
  "deleted_flag" numeric(1,0),
  "effective_start_date" date,
  "effective_end_date" date,
  "notes" varchar(3900),
  "notes_j" varchar(3900),
  "qc_analysis_cat_text" varchar(3900),
  "qc_analysis_cat_text_j" varchar(3900),
  "qc_anal_summary_text" varchar(3900),
  "qc_anal_summary_text_j" varchar(3900),
  "qc_result" varchar(3900),
  "qc_result_j" varchar(3900),
  "qc_comment" varchar(3900),
  "qc_comment_j" varchar(3900),
  "qc_complaint_cat_text" varchar(3900),
  "qc_complaint_cat_text_j" varchar(3900),
  "vaers_block_id" numeric(38),
  "prod_lic_id" number
)

create table rm_case_reporters
(
  "enterprise_id" numeric(38),
  "case_id" numeric(38),
  "seq_num" numeric(38),
  "media_id" numeric(38),
  "occupation_id" numeric(38),
  "hcp_flag" numeric(38),
  "primary_contact" numeric(38),
  "corr_contact" numeric(38),
  "reporter_type" numeric(38),
  "intermediary_id" numeric(38),
  "rpt_sent" numeric(38),
  "sort_id" numeric(38),
  "confidential" numeric(1,0),
  "country_id" numeric(38),
  "suffix" varchar(15),
  "middle_name" varchar(15),
  "prefix" varchar(50),
  "first_name" varchar(35),
  "last_name" varchar(50),
  "reporter_id" varchar(20),
  "institution" varchar(60),
  "department" varchar(60),
  "city" varchar(35),
  "state" varchar(40),
  "postcode" varchar(15),
  "country" varchar(50),
  "phone" varchar(20),
  "altphone" varchar(20),
  "fax" varchar(50),
  "reporter_ref" varchar(20),
  "email" varchar(100),
  "address" varchar(120),
  "suffix_j" varchar(15),
  "address_j" varchar(120),
  "city_j" varchar(35),
  "department_j" varchar(60),
  "first_name_j" varchar(35),
  "institution_j" varchar(60),
  "last_name_j" varchar(50),
  "middle_name_j" varchar(15),
  "prefix_j" varchar(50),
  "state_j" varchar(40),
  "deleted" date,
  "dlp_revision_number" numeric(38),
  "revision_delete_flag" number default 0,
  "deleted_flag" numeric(1,0),
  "effective_start_date" date,
  "effective_end_date" date,
  "notes" varchar(3900),
  "notes_j" varchar(3900),
  "institution_id" varchar(15)
)

create table rm_case_study
(
  "enterprise_id" numeric(38),
  "case_id" numeric(38),
  "study_key" numeric(38),
  "study_num" varchar(35),
  "study_type" numeric(38),
  "center_id" numeric(38),
  "center_name" varchar(40),
  "protocol_id" numeric(38),
  "protocol_num" varchar(40),
  "other_id" varchar(20),
  "code_broken" numeric(38),
  "broken_by" numeric(38),
  "broken_date" date,
  "reason" varchar(100),
  "week" varchar(5),
  "visit" varchar(5),
  "deleted" date,
  "product_count" numeric(2,0),
  "classification_id" numeric(38),
  "blind_name" varchar(70),
  "study_desc" varchar(3900),
  "study_desc_j" varchar(3900),
  "blind_name_j" varchar(70),
  "center_name_j" varchar(40),
  "clin_compound_num_j" varchar(70),
  "protocol_num_j" varchar(40),
  "study_num_j" varchar(35),
  "dev_phase_id" numeric(38),
  "dlp_revision_number" numeric(38),
  "revision_delete_flag" number default 0,
  "deleted_flag" numeric(1,0),
  "effective_start_date" date,
  "effective_end_date" date,
  "cohort_id" number
)

create table rm_lm_case_classification
(
  "enterprise_id" numeric(38),
  "classification_id" numeric(38),
  "description" varchar(30),
  "protected" numeric(38),
  "display" numeric(38),
  "description_j" varchar(30),
  "deleted" date,
  "e2b_code" number
)

create table rm_lm_causality
(
  "enterprise_id" numeric(38),
  "causality_id" numeric(38),
  "causality" varchar(35),
  "reportability" numeric(38),
  "protected" numeric(38),
  "display" numeric(38),
  "causality_j" varchar(35),
  "deleted" date
)

create table rm_lm_countries
(
  "enterprise_id" numeric(38),
  "country_id" numeric(38),
  "country" varchar(50),
  "a2" varchar(2),
  "a3" varchar(3),
  "num" numeric(38),
  "protected" numeric(38),
  "deleted" date,
  "country_group_id" numeric(38),
  "country_group" varchar(50),
  "country_j" varchar(50),
  "group_2_country" number
)

create table rm_lm_product
(
  "enterprise_id" numeric(38),
  "product_id" numeric(38),
  "prod_name" varchar(70),
  "family_id" numeric(38),
  "formulation_id" numeric(38),
  "manufacturer_id" numeric(38),
  "concentration" varchar(10),
  "conc_unit_id" numeric(38),
  "indication_id" varchar(50),
  "code_dict" numeric(38),
  "indication_text" varchar(250),
  "intl_birth_date" date,
  "model_no" varchar(20),
  "drl_id" varchar(45),
  "drug_code" varchar(20),
  "deleted" date,
  "prod_generic_name" varchar(3900),
  "prod_generic_name_j" varchar(3900),
  "ind_llt_code" varchar(50),
  "ind_llt" varchar(250),
  "ind_hlt_code" varchar(50),
  "ind_hlt" varchar(250),
  "ind_hlgt_code" varchar(50),
  "ind_hlgt" varchar(250),
  "ind_soc_code" varchar(50),
  "ind_soc" varchar(250),
  "ind_syn_code" numeric(38),
  "ind_code_status" numeric(38),
  "medicinal_prod_id" varchar(10),
  "prod_name_abbrv" varchar(5),
  "device_code" varchar(50),
  "psur_group_name" varchar(50),
  "comments" varchar(1000),
  "ind_coded" varchar(250),
  "ind_reptd" varchar(250),
  "drl_id_j" varchar(45),
  "drug_code_j" varchar(20),
  "drug_code_type_j" numeric(38),
  "indication_text_j" varchar(250),
  "ind_code_status_j" numeric(38),
  "ind_hlgt_j" varchar(250),
  "ind_hlt_j" varchar(250),
  "ind_llt_code_j" varchar(50),
  "ind_llt_j" varchar(250),
  "ind_soc_j" varchar(250),
  "prod_name_j" varchar(70),
  "ind_syn_code_j" numeric(38),
  "ind_coded_j" varchar(250),
  "ind_reptd_j" varchar(250),
  "dev_intl_birth_date" date,
  "comments_j" varchar(1000)
)

create table rm_lm_product_family
(
  "enterprise_id" numeric(38),
  "family_id" numeric(38),
  "name" varchar(40),
  "primary_view" numeric(38),
  "deleted" date,
  "product_group_id" numeric(38),
  "comments" varchar(1000),
  "search_equation_number" varchar(10),
  "name_j" varchar(40),
  "comments_j" varchar(1000)
)

create table rm_lm_report_type
(
  "enterprise_id" numeric(38),
  "rpt_type_id" numeric(38),
  "report_type" varchar(30),
  "protected" numeric(38),
  "display" numeric(38),
  "incl_lit" numeric(38),
  "incl_trial" numeric(38),
  "e2b_code" numeric(38),
  "abrv" varchar(3),
  "investigational" numeric(38),
  "report_type_j" varchar(30),
  "deleted" date,
  "incl_research" number
)

create table rm_meddra_smq_content
(
  "global_dict_id" numeric(38),
  "smq_code" numeric(38),
  "term_code" numeric(38),
  "term_level" numeric(38),
  "term_scope" numeric(38),
  "term_category" varchar(1),
  "term_weight" numeric(38),
  "term_status" varchar(1),
  "term_addition_version" varchar(5),
  "term_last_modified_version" varchar(5)
)

create table rm_meddra_smq_list
(
  "global_dict_id" numeric(38),
  "smq_code" numeric(38),
  "smq_name" varchar(100),
  "smq_level" numeric(38),
  "smq_description" varchar(3000),
  "smq_source" varchar(2000),
  "smq_note" varchar(2000),
  "meddra_version" varchar(5),
  "status" varchar(1),
  "smq_algorithm" varchar(100)
)
