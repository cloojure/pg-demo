-- original: alan-mv-meepnp-s1.sql
-- psql --dbname=ubuntu_large --username=ubuntu --host=10.100.6.89 (no password)
-- psql --dbname=postal --username=rxlogix --host=pg-test-1.cksh17mdz5oo.us-west-1.rds.amazonaws.com   (pass=rxlogix123)
-- conn: ora-test-1.cksh17mdz5oo.us-west-1.rds.amazonaws.com:1521/ORCL (user=rxlogix  pass=rxlogix123)

\timing on

drop index if exists idx_rm_case_master__case_id;
drop index if exists idx_rm_case_event__case_id;
drop index if exists idx_rm_case_event__seq_num;
drop index if exists idx_rm_case_event_assess__case_id;
drop index if exists idx_rm_case_event_assess__event_seq_num;
drop index if exists idx_rm_case_narrative__case_id;
drop index if exists idx_rm_case_pat_info__case_id;
drop index if exists idx_rm_case_product__case_id;
drop index if exists idx_rm_case_product__product_name;

create index idx_rm_case_master__case_id on rm_case_master (case_id);
create index idx_rm_case_event__case_id on rm_case_event (case_id);
create index idx_rm_case_event__seq_num on rm_case_event (seq_num);
create index idx_rm_case_event_assess__case_id on rm_case_event_assess (case_id);
create index idx_rm_case_event_assess__event_seq_num on rm_case_event_assess (event_seq_num);
create index idx_rm_case_narrative__case_id on rm_case_narrative (case_id);
create index idx_rm_case_pat_info__case_id on rm_case_pat_info (case_id);
create index idx_rm_case_product__case_id on rm_case_product (case_id);
create index idx_rm_case_product__product_name on rm_case_product (product_name);

