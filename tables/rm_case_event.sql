rm_case_event.art_code                                -- varchar(30),
rm_case_event.body_sys                                -- varchar(250),
rm_case_event.body_sys_j                              -- varchar(250),
rm_case_event.case_id                                 -- numeric not null,
rm_case_event.code_status                             -- numeric,
rm_case_event.code_status_j                           -- numeric,
rm_case_event.delay_minutes                           -- numeric,
rm_case_event.deleted                                 -- timestamp,
rm_case_event.deleted_flag                            -- numeric(1) not null,
rm_case_event.desc_coded                              -- varchar(255),
rm_case_event.desc_coded_j                            -- varchar(255),
rm_case_event.desc_reptd                              -- varchar(250),
rm_case_event.desc_reptd_j                            -- varchar(250),
rm_case_event.details                                 -- text,
rm_case_event.details_j                               -- text,
rm_case_event.diagnosis                               -- numeric,
rm_case_event.dict_id                                 -- numeric,
rm_case_event.dict_key                                -- varchar(20),
rm_case_event.disease                                 -- numeric,
rm_case_event.dlp_revision_number                     -- numeric not null,
rm_case_event.duration                                -- varchar(40),
rm_case_event.duration_seconds                        -- numeric,
rm_case_event.duration_unit_e2b                       -- numeric,
rm_case_event.effective_end_date                      -- timestamp not null,
rm_case_event.effective_start_date                    -- timestamp not null,
rm_case_event.efficacy                                -- numeric,
rm_case_event.enterprise_id                           -- numeric not null,
rm_case_event.evt_freq_id                             -- numeric,
rm_case_event.evt_intensity_id                        -- numeric,
rm_case_event.evt_outcome_id                          -- numeric,
rm_case_event.hlgt                                    -- varchar(250),
rm_case_event.hlgt_code                               -- varchar(30),
rm_case_event.hlgt_j                                  -- varchar(250),
rm_case_event.hlt                                     -- varchar(250),
rm_case_event.hlt_code                                -- varchar(30),
rm_case_event.hlt_j                                   -- varchar(250),
rm_case_event.inc_code                                -- varchar(30),
rm_case_event.inc_code_j                              -- varchar(30),
rm_case_event.inc_term                                -- varchar(250),
rm_case_event.inc_term_j                              -- varchar(250),
rm_case_event.infection                               -- numeric,
rm_case_event.med_serious                             -- numeric,
rm_case_event.onset                                   -- timestamp,
rm_case_event.onset_date_partial                      -- varchar(20),
rm_case_event.onset_delay                             -- varchar(40),
rm_case_event.onset_delay_seconds                     -- numeric,
rm_case_event.onset_delay_unit_e2b                    -- numeric,
rm_case_event.onset_latency                           -- varchar(40),
rm_case_event.onset_latency_seconds                   -- numeric,
rm_case_event.onset_latency_unit_e2b                  -- numeric,
rm_case_event.onset_minutes                           -- numeric,
rm_case_event.onset_res                               -- numeric,
rm_case_event.past_hist                               -- numeric,
rm_case_event.pref_term                               -- varchar(250),
rm_case_event.pref_term_j                             -- varchar(250),
rm_case_event.prod_seq_num                            -- numeric,
rm_case_event.receipt_date                            -- timestamp,
rm_case_event.rechall_related                         -- numeric,
rm_case_event.report_exclusion                        -- numeric,
rm_case_event.reportable                              -- numeric,
rm_case_event.revision_delete_flag                    -- numeric,
rm_case_event.rpt_exclude_cmt                         -- varchar(1000),
rm_case_event.rpt_exclude_cmt_j                       -- varchar(1000),
rm_case_event.rpt_serious                             -- numeric,
rm_case_event.sc_cong_anom                            -- numeric,
rm_case_event.sc_death                                -- numeric,
rm_case_event.sc_disable                              -- numeric,
rm_case_event.sc_hosp                                 -- numeric,
rm_case_event.sc_int_req                              -- numeric,
rm_case_event.sc_other                                -- numeric,
rm_case_event.sc_other_text                           -- varchar(30),
rm_case_event.sc_other_text_j                         -- varchar(30),
rm_case_event.sc_threat                               -- numeric,
rm_case_event.seq_num                                 -- numeric not null,
rm_case_event.seriousness                             -- numeric,
rm_case_event.soc_code                                -- varchar(30),
rm_case_event.sort_id                                 -- numeric,
rm_case_event.stop_date                               -- timestamp,
rm_case_event.stop_date_partial                       -- varchar(20),
rm_case_event.stop_date_res                           -- numeric,
rm_case_event.study_dropped                           -- numeric(4),
rm_case_event.study_related                           -- numeric,
rm_case_event.study_related_reptd                     -- numeric(1),
rm_case_event.syn_code                                -- numeric,
rm_case_event.syn_code_j                              -- numeric,
rm_case_event.treated                                 -- numeric,
rm_case_event.ud_date_1                               -- timestamp,
rm_case_event.ud_date_10                              -- timestamp,
rm_case_event.ud_date_11                              -- timestamp,
rm_case_event.ud_date_12                              -- timestamp,
rm_case_event.ud_date_2                               -- timestamp,
rm_case_event.ud_date_3                               -- timestamp,
rm_case_event.ud_date_4                               -- timestamp,
rm_case_event.ud_date_5                               -- timestamp,
rm_case_event.ud_date_6                               -- timestamp,
rm_case_event.ud_date_7                               -- timestamp,
rm_case_event.ud_date_8                               -- timestamp,
rm_case_event.ud_date_9                               -- timestamp,
rm_case_event.ud_number_1                             -- numeric(30,10),
rm_case_event.ud_number_10                            -- numeric(30,10),
rm_case_event.ud_number_11                            -- numeric(30,10),
rm_case_event.ud_number_12                            -- numeric(30,10),
rm_case_event.ud_number_2                             -- numeric(30,10),
rm_case_event.ud_number_3                             -- numeric(30,10),
rm_case_event.ud_number_4                             -- numeric(30,10),
rm_case_event.ud_number_5                             -- numeric(30,10),
rm_case_event.ud_number_6                             -- numeric(30,10),
rm_case_event.ud_number_7                             -- numeric(30,10),
rm_case_event.ud_number_8                             -- numeric(30,10),
rm_case_event.ud_number_9                             -- numeric(30,10),
rm_case_event.ud_text_1                               -- varchar(100),
rm_case_event.ud_text_10                              -- varchar(100),
rm_case_event.ud_text_10_j                            -- varchar(100),
rm_case_event.ud_text_11                              -- varchar(100),
rm_case_event.ud_text_11_j                            -- varchar(100),
rm_case_event.ud_text_12                              -- varchar(100),
rm_case_event.ud_text_12_j                            -- varchar(100),
rm_case_event.ud_text_1_j                             -- varchar(100),
rm_case_event.ud_text_2                               -- varchar(100),
rm_case_event.ud_text_2_j                             -- varchar(100),
rm_case_event.ud_text_3                               -- varchar(100),
rm_case_event.ud_text_3_j                             -- varchar(100),
rm_case_event.ud_text_4                               -- varchar(100),
rm_case_event.ud_text_4_j                             -- varchar(100),
rm_case_event.ud_text_5                               -- varchar(100),
rm_case_event.ud_text_5_j                             -- varchar(100),
rm_case_event.ud_text_6                               -- varchar(100),
rm_case_event.ud_text_6_j                             -- varchar(100),
rm_case_event.ud_text_7                               -- varchar(100),
rm_case_event.ud_text_7_j                             -- varchar(100),
rm_case_event.ud_text_8                               -- varchar(100),
rm_case_event.ud_text_8_j                             -- varchar(100),
rm_case_event.ud_text_9                               -- varchar(100),
rm_case_event.ud_text_9_j                             -- varchar(100),
rm_case_event.withdrawal                              -- numeric,