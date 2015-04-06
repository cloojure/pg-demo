\connect ubuntu_large
\timing on

explain analyze
SELECT case_num, ca.seriousness, ca.agent_suspect, ll.listedness, leo.evt_outcome
FROM rm_case_master_5     cm
   , rm_case_assess_5     ca
   , rm_lm_listedness     ll
   , rm_lm_evt_outcome    leo
   , rm_case_reg_reports  crr
WHERE cm.case_id = ca.case_id
  AND cm.case_id = crr.case_id
  AND ca.listedness = ll.listedness_id
  AND ca.outcome = leo.evt_outcome_id

                            QUERY PLAN
-----------------------------------------------------------------------------------------------------------------------------------------------------------
 Hash Join  (cost=62830.67..88822.89 rows=1233682 width=196) (actual time=1024.988..1484.684 rows=1205020 loops=1)
   Hash Cond: (cm.case_id = ca.case_id)
   ->  Seq Scan on rm_case_master_5 cm  (cost=0.00..3959.00 rows=100000 width=18) (actual time=0.005..25.708 rows=100000 loops=1)
   ->  Hash  (cost=60199.34..60199.34 rows=210506 width=196) (actual time=1024.734..1024.734 rows=117492 loops=1)
         Buckets: 32768  Batches: 1  Memory Usage: 9663kB
         ->  Hash Join  (cost=42467.39..60199.34 rows=210506 width=196) (actual time=832.018..976.331 rows=117492 loops=1)
               Hash Cond: (ca.case_id = crr.case_id)
               ->  Hash Join  (cost=2.47..5645.08 rows=41819 width=190) (actual time=0.933..87.910 rows=42946 loops=1)
                     Hash Cond: (ca.listedness = ll.listedness_id)
                     ->  Hash Join  (cost=1.41..5061.80 rows=43740 width=137) (actual time=0.354..64.575 rows=43433 loops=1)
                           Hash Cond: (ca.outcome = leo.evt_outcome_id)
                           ->  Seq Scan on rm_case_assess_5 ca  (cost=0.00..4248.00 rows=100000 width=24) (actual time=0.005..27.209 rows=100000 loops=1)
                           ->  Hash  (cost=1.18..1.18 rows=18 width=150) (actual time=0.311..0.311 rows=18 loops=1)
                                 Buckets: 1024  Batches: 1  Memory Usage: 1kB
                                 ->  Seq Scan on rm_lm_evt_outcome leo  (cost=0.00..1.18 rows=18 width=150) (actual time=0.297..0.298 rows=18 loops=1)
                     ->  Hash  (cost=1.03..1.03 rows=3 width=90) (actual time=0.571..0.571 rows=3 loops=1)
                           Buckets: 1024  Batches: 1  Memory Usage: 1kB
                           ->  Seq Scan on rm_lm_listedness ll  (cost=0.00..1.03 rows=3 width=90) (actual time=0.560..0.562 rows=3 loops=1)
               ->  Hash  (cost=25410.52..25410.52 rows=1364352 width=6) (actual time=830.206..830.206 rows=1364352 loops=1)
                     Buckets: 262144  Batches: 1  Memory Usage: 51963kB
                     ->  Seq Scan on rm_case_reg_reports crr  (cost=0.00..25410.52 rows=1364352 width=6) (actual time=0.331..405.509 rows=1364352 loops=1)
 Planning time: 2.817 ms
 Execution time: 1644.800 ms
(23 rows)
