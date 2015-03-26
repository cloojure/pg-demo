
DROP INDEX IF EXISTS  rm_case_master__case_id                     CASCADE;
DROP INDEX IF EXISTS  rm_case_Event__case_id                      CASCADE;
DROP INDEX IF EXISTS  rm_case_Event__seq_num                      CASCADE;
DROP INDEX IF EXISTS  rm_case_event_assess__case_id               CASCADE;
DROP INDEX IF EXISTS  rm_case_event_assess__event_seq_num         CASCADE;
DROP INDEX IF EXISTS  rm_case_event_assess__prod_seq_num          CASCADE;
DROP INDEX IF EXISTS  rm_case_narrative__case_id                  CASCADE;
DROP INDEX IF EXISTS  rm_case_pat_info__case_id                   CASCADE;
DROP INDEX IF EXISTS  rm_case_product__case_id                    CASCADE;
DROP INDEX IF EXISTS  rm_case_product__seq_num                    CASCADE;
DROP INDEX IF EXISTS  rm_case_product__generic_name               CASCADE;

