explain analyze
SELECT case_num, ca.seriousness, ca.agent_suspect, ll.listedness, leo.evt_outcome
FROM rm_case_master       cm
   , rm_case_assess       ca
   , rm_lm_listedness     ll
   , rm_lm_evt_outcome    leo
   , rm_case_reg_reports  crr
WHERE cm.case_id = ca.case_id
  AND cm.case_id = crr.case_id
  AND ca.listedness = ll.listedness_id
  AND ca.outcome = leo.evt_outcome_id
\q


You are now connected to database "ubuntu_large" as user "ubuntu".
Timing is on.
                                                                                    QUERY PLAN
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 Merge Join  (cost=514549.73..6483751.62 rows=349227088 width=196) (actual time=13360.133..147458.203 rows=178954920 loops=1)
   Merge Cond: (ca.case_id = cm.case_id)
   ->  Merge Join  (cost=514536.98..622242.60 rows=6744049 width=197) (actual time=13359.887..16763.726 rows=4038792 loops=1)
         Merge Cond: (crr.case_id = ca.case_id)
         ->  Sort  (cost=164436.52..167847.40 rows=1364352 width=6) (actual time=2344.992..2570.379 rows=1363753 loops=1)
               Sort Key: crr.case_id
               Sort Method: quicksort  Memory: 113107kB
               ->  Seq Scan on rm_case_reg_reports crr  (cost=0.00..25410.52 rows=1364352 width=6) (actual time=0.009..308.500 rows=1364352 loops=1)
         ->  Sort  (cost=350099.78..353465.75 rows=1346388 width=191) (actual time=11014.858..11724.250 rows=4352747 loops=1)
               Sort Key: ca.case_id
               Sort Method: quicksort  Memory: 191777kB
               ->  Hash Join  (cost=2.47..213033.02 rows=1346388 width=191) (actual time=0.122..8732.534 rows=1422492 loops=1)
                     Hash Cond: (ca.listedness = ll.listedness_id)
                     ->  Hash Join  (cost=1.41..194120.15 rows=1452780 width=138) (actual time=0.055..7950.083 rows=1465128 loops=1)
                           Hash Cond: (ca.outcome = leo.evt_outcome_id)
                           ->  Seq Scan on rm_case_assess ca  (cost=0.00..164023.96 rows=4151196 width=25) (actual time=0.008..6542.431 rows=4151196 loops=1)
                           ->  Hash  (cost=1.18..1.18 rows=18 width=150) (actual time=0.014..0.014 rows=18 loops=1)
                                 Buckets: 1024  Batches: 1  Memory Usage: 1kB
                                 ->  Seq Scan on rm_lm_evt_outcome leo  (cost=0.00..1.18 rows=18 width=150) (actual time=0.002..0.009 rows=18 loops=1)
                     ->  Hash  (cost=1.03..1.03 rows=3 width=90) (actual time=0.015..0.015 rows=3 loops=1)
                           Buckets: 1024  Batches: 1  Memory Usage: 1kB
                           ->  Seq Scan on rm_lm_listedness ll  (cost=0.00..1.03 rows=3 width=90) (actual time=0.010..0.011 rows=3 loops=1)
   ->  Materialize  (cost=0.43..650647.73 rows=11096988 width=18) (actual time=0.039..46924.004 rows=183615249 loops=1)
         ->  Index Scan using idx_rm_case_master__case_id on rm_case_master cm  (cost=0.43..622905.26 rows=11096988 width=18) (actual time=0.027..17014.869 rows=11094489 loops=1)
 Planning time: 2.861 ms
 Execution time: 170134.564 ms
(26 rows)
