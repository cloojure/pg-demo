\timing on

CLUSTER rm_case_master             USING idx_rm_case_master__case_id;
CLUSTER rm_case_Event              USING idx_rm_case_Event__case_id;
CLUSTER rm_case_event_assess       USING idx_rm_case_event_assess__case_id;
CLUSTER rm_case_narrative          USING idx_rm_case_narrative__case_id;
CLUSTER rm_case_pat_info           USING idx_rm_case_pat_info__case_id;
CLUSTER rm_case_product            USING idx_rm_case_product__case_id;
