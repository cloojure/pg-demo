explain analyze
SELECT case_num, ca.seriousness, ca.agent_suspect, ll.listedness, leo.evt_outcome
FROM rm_case_master_6     cm
   , rm_case_assess_6     ca
   , rm_lm_listedness     ll
   , rm_lm_evt_outcome    leo
   , rm_case_reg_reports  crr
WHERE cm.case_id = ca.case_id
  AND cm.case_id = crr.case_id
  AND ca.listedness = ll.listedness_id
  AND ca.outcome = leo.evt_outcome_id
                                                                                    QUERY PLAN
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 Merge Join  (cost=246813.59..442709.90 rows=10309324 width=196) (actual time=3285.259..18256.047 rows=17451800 loops=1)
   Merge Cond: (cm.case_id = crr.case_id)
   ->  Merge Join  (cost=82070.88..165148.06 rows=2058404 width=208) (actual time=919.077..4555.598 rows=4648740 loops=1)
         Merge Cond: (cm.case_id = ca.case_id)
         ->  Index Scan using idx_rm_case_master_6__case_id on rm_case_master_6 cm  (cost=0.42..56313.43 rows=1000000 width=18) (actual time=0.007..391.085 rows=1000000 loops=1)
         ->  Sort  (cost=81838.61..82657.66 rows=327622 width=190) (actual time=912.543..1634.433 rows=4659683 loops=1)
               Sort Key: ca.case_id
               Sort Method: quicksort  Memory: 46712kB
               ->  Hash Join  (cost=2.47..51825.69 rows=327622 width=190) (actual time=0.075..771.341 rows=342530 loops=1)
                     Hash Cond: (ca.listedness = ll.listedness_id)
                     ->  Hash Join  (cost=1.41..47225.40 rows=352800 width=137) (actual time=0.050..582.294 rows=353216 loops=1)
                           Hash Cond: (ca.outcome = leo.evt_outcome_id)
                           ->  Seq Scan on rm_case_assess_6 ca  (cost=0.00..39946.00 rows=1000000 width=24) (actual time=0.003..257.158 rows=1000000 loops=1)
                           ->  Hash  (cost=1.18..1.18 rows=18 width=150) (actual time=0.017..0.017 rows=18 loops=1)
                                 Buckets: 1024  Batches: 1  Memory Usage: 1kB
                                 ->  Seq Scan on rm_lm_evt_outcome leo  (cost=0.00..1.18 rows=18 width=150) (actual time=0.004..0.010 rows=18 loops=1)
                     ->  Hash  (cost=1.03..1.03 rows=3 width=90) (actual time=0.012..0.012 rows=3 loops=1)
                           Buckets: 1024  Batches: 1  Memory Usage: 1kB
                           ->  Seq Scan on rm_lm_listedness ll  (cost=0.00..1.03 rows=3 width=90) (actual time=0.006..0.007 rows=3 loops=1)
   ->  Sort  (cost=164436.52..167847.40 rows=1364352 width=6) (actual time=2361.550..5034.209 rows=17504953 loops=1)
         Sort Key: crr.case_id
         Sort Method: quicksort  Memory: 113107kB
         ->  Seq Scan on rm_case_reg_reports crr  (cost=0.00..25410.52 rows=1364352 width=6) (actual time=0.007..307.600 rows=1364352 loops=1)
 Planning time: 2.881 ms
 Execution time: 20466.029 ms

