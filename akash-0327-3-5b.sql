\connect ubuntu_large
\timing
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
  AND (    COALESCE(ca.seriousness,-1)    < 0
        OR COALESCE(ca.agent_suspect,-1)  < 0
        OR COALESCE(ca.listedness,-1)     < 0
        OR COALESCE(ca.outcome,-1)        < 0 
      )

 Hash Join  (cost=60647.24..89938.31 rows=989991 width=196) (actual time=802.750..869.215 rows=50570 loops=1)
   Hash Cond: (cm.case_id = ca.case_id)
   ->  Seq Scan on rm_case_master_5 cm  (cost=0.00..3959.00 rows=100000 width=18) (actual time=0.005..23.955 rows=100000 loops=1)
   ->  Hash  (cost=58535.72..58535.72 rows=168922 width=196) (actual time=802.289..802.289 rows=5648 loops=1)
         Buckets: 32768  Batches: 1  Memory Usage: 478kB
         ->  Hash Join  (cost=42467.39..58535.72 rows=168922 width=196) (actual time=734.587..799.828 rows=5648 loops=1)
               Hash Cond: (ca.case_id = crr.case_id)
               ->  Hash Join  (cost=2.47..6369.60 rows=33558 width=190) (actual time=0.155..63.030 rows=816 loops=1)
                     Hash Cond: (ca.listedness = ll.listedness_id)
                     ->  Hash Join  (cost=1.41..5901.33 rows=35100 width=137) (actual time=0.135..62.468 rows=1303 loops=1)
                           Hash Cond: (ca.outcome = leo.evt_outcome_id)
                           ->  Seq Scan on rm_case_assess_5 ca  (cost=0.00..5248.00 rows=80247 width=24) (actual time=0.007..53.529 rows=57870 loops=1)
                                 Filter: ((COALESCE(seriousness, (-1)::numeric) < 0::numeric) OR (COALESCE(agent_suspect, (-1)::numeric) < 0::numeric) OR (COALESCE(listedness, (-1)::numeric) < 0::numeric) OR (COALESCE(outcome, (-1)::numeric) < 0::numeric))
                                 Rows Removed by Filter: 42130
                           ->  Hash  (cost=1.18..1.18 rows=18 width=150) (actual time=0.017..0.017 rows=18 loops=1)
                                 Buckets: 1024  Batches: 1  Memory Usage: 1kB
                                 ->  Seq Scan on rm_lm_evt_outcome leo  (cost=0.00..1.18 rows=18 width=150) (actual time=0.004..0.010 rows=18 loops=1)
                     ->  Hash  (cost=1.03..1.03 rows=3 width=90) (actual time=0.014..0.014 rows=3 loops=1)
                           Buckets: 1024  Batches: 1  Memory Usage: 1kB
                           ->  Seq Scan on rm_lm_listedness ll  (cost=0.00..1.03 rows=3 width=90) (actual time=0.006..0.006 rows=3 loops=1)
               ->  Hash  (cost=25410.52..25410.52 rows=1364352 width=6) (actual time=733.657..733.657 rows=1364352 loops=1)
                     Buckets: 262144  Batches: 1  Memory Usage: 51963kB
                     ->  Seq Scan on rm_case_reg_reports crr  (cost=0.00..25410.52 rows=1364352 width=6) (actual time=0.006..321.644 rows=1364352 loops=1)
 Planning time: 2.805 ms
 Execution time: 878.223 ms
(25 rows)

Time: 883.144 ms
