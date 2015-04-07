(ns pg-demo.pva-trans
  (:require
    [clojure.java.jdbc      :as jdbc]
    [clojure.string         :as str]
    [clojure.set            :as set]
    [pg-demo.ddl            :as ddl]
    [com.climate.claypoole  :as cp]
  )
  (:use cooljure.core
        cooljure.misc)
  (:gen-class))

(def db-spec
  { :classname      "org.postgresql.Driver"
    :subprotocol    "postgresql"
    :subname        "//10.100.6.130:5432/demo"
    :user           "demo"
    :password       "demo123"
  } )

(def pva-trans
  "The tranformation from ArgusMart schema to PVA schema"
  (into (sorted-map)
    {
      :case_assess
         "select
            a.case_id,
            a.listedness,
            a.outcome,
            a.seriousness,
            a.agent_suspect,
            a.company_diagnosis,
            b.version_num,
            b.effective_start_date,
            b.effective_end_date,
            a.enterprise_id
          from
            rm_case_assess as a,
            case_version_table as b
          where
            (a.case_id = b.case_id) and
            (a.enterprise_id = b.enterprise_id) and
            (a.deleted_flag = 0) and
            (a.effective_start_date <= b.effective_start_date) and
            (a.effective_end_date > b.effective_start_date) "

      :case_classifications
         "select
            a.enterprise_id,
            a.case_id,
            a.seq_num,
            a.classification_id,
            a.deleted,
            b.version_num,
            b.effective_start_date,
            b.effective_end_date
          from
            rm_case_classifications as a,
            case_version_table as b
          where
            (a.case_id = b.case_id) and
            (a.effective_start_date <= b.effective_start_date) and
            (a.effective_end_date > b.effective_start_date) "

      :case_event
         "select
            a.enterprise_id,
            a.case_id,
            a.seq_num,
            a.desc_reptd,
            a.desc_coded,
            a.diagnosis,
            a.onset,
            a.onset_res,
            a.onset_delay,
            a.stop_date,
            a.stop_date_res,
            a.duration,
            a.onset_latency,
            a.evt_intensity_id,
            a.evt_freq_id,
            a.treated,
            a.study_related,
            a.rechall_related,
            a.prod_seq_num,
            a.evt_outcome_id,
            a.receipt_date,
            a.rpt_serious,
            a.sc_death,
            a.sc_hosp,
            a.sc_cong_anom,
            a.sc_threat,
            a.sc_disable,
            a.sc_int_req,
            a.sc_other,
            a.sc_other_text,
            a.art_code,
            a.pref_term,
            a.inc_term,
            a.body_sys,
            a.med_serious,
            a.past_hist,
            a.dict_id,
            a.dict_key,
            a.code_status,
            a.seriousness,
            a.sort_id,
            a.onset_date_partial,
            a.stop_date_partial,
            a.efficacy,
            a.disease,
            a.withdrawal,
            a.hlgt,
            a.hlt,
            a.hlt_code,
            a.hlgt_code,
            a.soc_code,
            a.study_dropped,
            a.inc_code,
            a.onset_minutes,
            a.delay_minutes,
            b.version_num,
            b.effective_start_date,
            b.effective_end_date
          from
            rm_case_event as a,
            case_version_table as b
          where
            (a.case_id = b.case_id) and
            (a.enterprise_id = b.enterprise_id) and
            (a.deleted_flag = 0) and
            (a.effective_start_date <= b.effective_start_date) and
            (a.effective_end_date > b.effective_start_date) "

      :case_event_assess
         "select
            a.case_id,
            a.prod_seq_num,
            a.event_seq_num,
            a.license_id,
            a.datasheet_id,
            a.source_id,
            a.det_causality_id,
            a.seq_num,
            a.causality_score,
            a.cause_justify,
            a.listed_justify,
            a.causality_assessment,
            a.det_listedness_id,
            a.rpt_causality_id,
            a.prt_causality_id,
            b.version_num,
            b.effective_start_date,
            b.effective_end_date,
            a.enterprise_id
          from
            rm_case_event_assess as a,
            case_version_table as b
          where
            (a.case_id = b.case_id) and
            (a.enterprise_id = b.enterprise_id) and
            (a.deleted_flag = 0) and
            (a.effective_start_date <= b.effective_start_date) and
            (a.effective_end_date > b.effective_start_date) "

      :case_followup
         "select
            cf.case_id,
            cf.seq_num,
            cf.auditlog_type,
            cf.time_stamp,
            cf.receipt_date,
            cf.safety_date,
            cf.significant,
            cvt.version_num,
            cvt.effective_start_date,
            cvt.effective_end_date,
            cf.enterprise_id
          from
            rm_case_followup as cf,
            case_version_table as cvt
          where
            (cf.case_id = cvt.case_id) and
            (cf.enterprise_id = cvt.enterprise_id) and
            (cf.effective_start_date <= cvt.effective_start_date) and
            (cf.effective_end_date > cvt.effective_start_date) and
            (cf.deleted_flag = 0) "

      :case_master
         "select
            cm.case_id,
            cm.global_num,
            cm.initial_justification,
            cm.workflow_seq_num,
            cm.owner_id,
            cm.site_id,
            cm.susar,
            cm.close_user_id,
            cm.close_date,
            cm.date_locked,
            cm.close_notes,
            cm.state_id,
            cm.last_state_id,
            cm.requires_followup,
            cm.due_soon,
            cm.create_time,
            cm.followup_date,
            cm.init_rept_date,
            cm.last_update_time,
            cm.safety_date,
            cm.worklist_owner_id,
            cm.case_num,
            cm.e2b_ww_number,
            cm.country_id,
            cm.rpt_type_id,
            cm.user_id,
            cm.last_update_user_id,
            cm.seriousness,
            cvt.version_num,
            cvt.effective_start_date,
            cvt.effective_end_date,
            cm.enterprise_id
          from
            rm_case_master as cm,
            case_version_table as cvt
          where
            (cvt.case_id = cm.case_id) and
            (cvt.effective_start_date >= cm.effective_start_date) and
            (cvt.effective_start_date < cm.effective_end_date) and
            (cm.deleted_flag = 0) and
            (cm.enterprise_id = cvt.enterprise_id) "

      :case_narrative
         "select
            a.enterprise_id,
            a.case_id,
            a.abbrev_narrative,
            a.abbrev_narrative_j,
            a.narrative,
            a.narrative_j,
            b.version_num,
            b.effective_start_date,
            b.effective_end_date
          from
            rm_case_narrative as a,
            case_version_table as b
          where
            (a.case_id = b.case_id) and
            (a.effective_start_date <= b.effective_start_date) and
            (a.effective_end_date > b.effective_start_date) "

      :case_pat_info
         "select
            a.case_id,
            a.age_group_id,
            a.ethnicity_id,
            a.gender_id,
            a.pat_age,
            a.pat_subj_num,
            a.pat_stat_preg,
            a.age_unit_id,
            b.version_num,
            b.effective_start_date,
            b.effective_end_date,
            a.enterprise_id
          from
            rm_case_pat_info as a,
            case_version_table as b
          where
            (a.case_id = b.case_id) and
            (a.enterprise_id = b.enterprise_id) and
            (a.deleted_flag = 0) and
            (a.effective_start_date <= b.effective_start_date) and
            (a.effective_end_date > b.effective_start_date) "

      :case_prod_drugs
         "select
            a.case_id,
            a.seq_num,
            a.latency_seconds,
            a.rechall_stop,
            a.rechall_start,
            a.total_dose,
            a.tampering,
            a.overdose,
            a.protocol,
            a.rechall_outcome,
            a.rechallenge,
            a.n_siblings,
            a.cumulative_dose_unit_id,
            a.obtain_drug_country_id,
            a.drug_auth_country_id,
            a.cumulative_dose,
            a.abuse,
            a.contraind,
            a.dechallenge,
            a.act_taken_id,
            a.conc_units_id,
            a.formulation_id,
            a.concentration,
            a.severity_id,
            a.interaction,
            a.prev_use,
            a.dechall_date,
            a.first_dose,
            a.last_dose,
            a.duration_seconds,
            b.version_num,
            b.effective_start_date,
            b.effective_end_date,
            a.enterprise_id,
            a.first_dose_partial,
            a.last_dose_partial
          from
            rm_case_prod_drugs as a,
            case_version_table as b
          where
            (a.case_id = b.case_id) and
            (a.enterprise_id = b.enterprise_id) and
            (a.deleted_flag = 0) and
            (a.effective_start_date <= b.effective_start_date) and
            (a.effective_end_date > b.effective_start_date) "

      :case_product
         "select
            cp.sdrug_not_admin,
            cp.prod_reptd,
            cp.prod_coded,
            cp.case_id,
            cp.family_id,
            cp.seq_num,
            cp.product_id,
            cp.drug_type,
            cp.pat_exposure,
            cp.manufacturer_id,
            cp.first_sus_prod,
            cp.country_id,
            cp.who_drug_code,
            cp.co_drug_code,
            cp.product_name,
            cp.generic_name,
            cvt.version_num,
            cvt.effective_start_date,
            cvt.effective_end_date,
            cp.enterprise_id,
            cp.sort_id
          from
            rm_case_product as cp,
            case_version_table as cvt
          where
            (cp.case_id = cvt.case_id) and
            (cp.enterprise_id = cvt.enterprise_id) and
            (cp.effective_start_date <= cvt.effective_start_date) and
            (cp.effective_end_date > cvt.effective_start_date) and
            (cp.deleted_flag = 0) "

      :case_reporters
         "select
            a.enterprise_id,
            a.case_id,
            a.seq_num,
            a.media_id,
            a.occupation_id,
            a.hcp_flag,
            a.primary_contact,
            a.corr_contact,
            a.reporter_type,
            a.intermediary_id,
            a.rpt_sent,
            a.sort_id,
            a.confidential,
            a.country_id,
            a.reporter_id,
            a.institution,
            a.institution_id,
            b.version_num,
            b.effective_start_date,
            b.effective_end_date
          from
            rm_case_reporters as a,
            case_version_table as b
          where
            (a.case_id = b.case_id) and
            (a.enterprise_id = b.enterprise_id) and
            (a.deleted_flag = 0) and
            (a.effective_start_date <= b.effective_start_date) and
            (a.effective_end_date > b.effective_start_date) "

      :case_study
         "select
            a.case_id,
            a.blind_name,
            a.center_id,
            a.classification_id,
            a.code_broken,
            a.dev_phase_id,
            a.other_id,
            a.study_desc,
            a.study_key,
            a.study_num,
            b.version_num,
            b.effective_start_date,
            b.effective_end_date,
            a.enterprise_id
          from
            rm_case_study as a,
            case_version_table as b
          where
            (a.case_id = b.case_id) and
            (a.enterprise_id = b.enterprise_id) and
            (a.deleted_flag = 0) and
            (a.effective_start_date <= b.effective_start_date) and
            (a.effective_end_date > b.effective_start_date) "

      :lm_case_classification
         "select * from rm_lm_case_classification "

      :lm_causality
         "select * from rm_lm_causality "

      :lm_countries
         "select * from rm_lm_countries "

      :lm_product
         "select
            enterprise_id,
            product_id,
            prod_name,
            family_id,
            formulation_id,
            manufacturer_id,
            concentration,
            conc_unit_id,
            indication_id,
            code_dict,
            indication_text,
            intl_birth_date,
            model_no,
            drug_code,
            prod_generic_name,
            ind_llt_code,
            ind_llt,
            ind_hlt_code,
            ind_hlt,
            ind_hlgt_code,
            ind_hlgt,
            ind_soc_code,
            ind_soc,
            ind_syn_code,
            ind_code_status,
            prod_name_abbrv,
            ind_coded,
            ind_reptd
          from
            rm_lm_product
          where
            deleted is null "

      :lm_product_family
         "select
            t.enterprise_id,
            t.family_id,
            t.name,
            t.primary_view,
            t.product_group_id,
            t.deleted,
            t.name_j
          from
            rm_lm_product_family t"

      :lm_report_type
         "select * from rm_lm_report_type "
    } ))

(defn -main []
  (let [pva-tables-kw (keys pva-trans) ]
    (jdbc/with-db-connection [db-conn db-spec]
      (print "Dropping pva.* tables ")
      (doseq [table-name (mapv name pva-tables-kw)]
        (let [drop-cmd (format "drop table if exists pva.%s;" table-name) ]

          ; example drop-cmd = " drop table if exists pva.lm_report_type; "
          (jdbc/execute! db-conn [drop-cmd] )

          (print ".") (flush)))
      (newline) (newline)

      (doseq [table-kw pva-tables-kw]
        (let [table-name        (name table-kw)
              pva-fullname      (format "pva.%s" table-name)
              creation-select   (pva-trans table-kw)
              create-cmd        (format "create table %s as ( %s )"
                                  pva-fullname creation-select)
                ; ex: "create table pva.lm_report_type as 
                ;         (select * from rm_lm_report_type); "
        ]
          (println "Creating table:" pva-fullname)
          (jdbc/execute! db-conn [create-cmd] )))
    ))
  (newline)
  (println "-----------------------------------------------------------------------------")
  (println "complete")
)

