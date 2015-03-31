-- original: alan-mv-meepnp-s1.sql
-- psql --dbname=ubuntu_large --username=ubuntu --host=10.100.6.89 (no password)
-- psql --dbname=postal --username=rxlogix --host=pg-test-1.cksh17mdz5oo.us-west-1.rds.amazonaws.com   (pass=rxlogix123)
-- conn: ora-test-1.cksh17mdz5oo.us-west-1.rds.amazonaws.com:1521/ORCL (user=rxlogix  pass=rxlogix123)

\timing on

-----------------------------------------------------------------------------
cluster rm_case_master            using idx_rm_case_master__case_id ;
cluster rm_case_event             using idx_rm_case_event__case_id ;
cluster rm_case_event_assess      using idx_rm_case_event_assess__case_id ;
cluster rm_case_narrative         using idx_rm_case_narrative__case_id ;
cluster rm_case_pat_info          using idx_rm_case_pat_info__case_id ;
cluster rm_case_product           using idx_rm_case_product__case_id ;
\q

CLUSTER Time: 109087.480 ms
CLUSTER Time:  85811.275 ms
CLUSTER Time: 104449.853 ms
CLUSTER Time:   3685.617 ms
CLUSTER Time:  17818.582 ms
CLUSTER Time: 115303.043 ms

-----------------------------------------------------------------------------
create index idx_rm_case_master__case_id on rm_case_master (case_id);
create index idx_rm_case_event__case_id on rm_case_event (case_id);
create index idx_rm_case_event__seq_num on rm_case_event (seq_num);
create index idx_rm_case_event_assess__case_id on rm_case_event_assess (case_id);
create index idx_rm_case_event_assess__event_seq_num on rm_case_event_assess (event_seq_num);
create index idx_rm_case_narrative__case_id on rm_case_narrative (case_id);
create index idx_rm_case_pat_info__case_id on rm_case_pat_info (case_id);
create index idx_rm_case_product__case_id on rm_case_product (case_id);
create index idx_rm_case_product__product_name on rm_case_product (product_name);
\q

CREATE INDEX Time: 55013.279 ms
CREATE INDEX Time: 21300.269 ms
CREATE INDEX Time: 20562.475 ms
CREATE INDEX Time: 39034.187 ms
CREATE INDEX Time: 41165.589 ms
CREATE INDEX Time:  1638.475 ms
CREATE INDEX Time:  9626.572 ms
CREATE INDEX Time: 14335.850 ms
CREATE INDEX Time: 77005.786 ms
