
-- indexes for Awais query Friday 2015-3-13

DROP INDEX IF EXISTS  idx_rm_case_master__case_id                     CASCADE;
DROP INDEX IF EXISTS  idx_rm_case_Event__case_id                      CASCADE;
DROP INDEX IF EXISTS  idx_rm_case_Event__seq_num                      CASCADE;
DROP INDEX IF EXISTS  idx_rm_case_event_assess__case_id               CASCADE;
DROP INDEX IF EXISTS  idx_rm_case_event_assess__event_seq_num         CASCADE;
DROP INDEX IF EXISTS  idx_rm_case_event_assess__prod_seq_num          CASCADE;
DROP INDEX IF EXISTS  idx_rm_case_narrative__case_id                  CASCADE;
DROP INDEX IF EXISTS  idx_rm_case_pat_info__case_id                   CASCADE;
DROP INDEX IF EXISTS  idx_rm_case_product__case_id                    CASCADE;
DROP INDEX IF EXISTS  idx_rm_case_product__seq_num                    CASCADE;
DROP INDEX IF EXISTS  idx_rm_case_product__generic_name               CASCADE;

\timing on
CREATE INDEX  idx_rm_case_master__case_id                     ON rm_case_master (case_id); 
CREATE INDEX  idx_rm_case_Event__case_id                      ON rm_case_Event (case_id); 
CREATE INDEX  idx_rm_case_Event__seq_num                      ON rm_case_Event (seq_num);
CREATE INDEX  idx_rm_case_event_assess__case_id               ON rm_case_event_assess (case_id);
CREATE INDEX  idx_rm_case_event_assess__event_seq_num         ON rm_case_event_assess (event_seq_num);
CREATE INDEX  idx_rm_case_event_assess__prod_seq_num          ON rm_case_event_assess (prod_seq_num); 
CREATE INDEX  idx_rm_case_narrative__case_id                  ON rm_case_narrative (case_id); 
CREATE INDEX  idx_rm_case_pat_info__case_id                   ON rm_case_pat_info (case_id); 
CREATE INDEX  idx_rm_case_product__case_id                    ON rm_case_product (case_id); 
CREATE INDEX  idx_rm_case_product__seq_num                    ON rm_case_product (seq_num); 
CREATE INDEX  idx_rm_case_product__generic_name               ON rm_case_product (generic_name);


