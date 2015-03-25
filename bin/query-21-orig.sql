-- source: akash email 2015-3-21  (awt)

select
  cm.enterprise_id,
  cm.case_id,
  cm.version_num dlp_revision_number
   from CASE_MASTER cm join
   (
     select enterprise_id, case_id, max(version_num) dlp_revision_number from
     (
       select enterprise_id, case_id, version_num from case_master
      union
       select enterprise_id, case_id, version_num from case_followup
     ) subquery01
     group by  enterprise_id, case_id
   ) ver on (
          cm.case_id = ver.case_id
          and cm.version_num = ver.dlp_revision_number
          and cm.state_id <> 1
      )
    join CASE_EVENT ce
            on (
                cm.CASE_ID = ce.CASE_ID
                and cm.version_num = ce.version_num
            )
    join CASE_PRODUCT cp
            on (
                cm.CASE_ID = cp.CASE_ID
                and cm.version_num = cp.version_num
            )
    join
        CASE_EVENT_ASSESS cea
            on (
                cm.CASE_ID = cea.CASE_ID
                and cm.version_num = cea.version_num
                AND ce.SEQ_NUM = cea.EVENT_SEQ_NUM
                and ce.version_num = cea.version_num
                AND cp.SEQ_NUM = cea.PROD_SEQ_NUM
                and cp.version_num = cea.version_num
            )
    Left  Join
        LM_LISTEDNESS llist
            on (
                cea.DET_LISTEDNESS_ID = llist.LISTEDNESS_ID
                and cea.enterprise_id = llist.enterprise_id
            )
    Join
        LM_COUNTRIES lco
            on (
                cm.COUNTRY_ID = lco.COUNTRY_ID
                and cm.enterprise_id = lco.enterprise_id
            )
--  Left  Join                                                   -- #awt #todo missing table
--      LM_DRUG_TYPE cldt                                        -- #awt #todo missing table
--          on (                                                 -- #awt #todo missing table
--              cp.DRUG_TYPE = cldt.ID                           -- #awt #todo missing table
--          )                                                    -- #awt #todo missing table
    Left  Join
        LM_PRODUCT lp
            on (
                cp.PRODUCT_ID = lp.PRODUCT_ID
                and cp.enterprise_id = lp.enterprise_id
            )
--  Left  Join                                                   -- #awt #todo missing table
--      CMN_LOOKUP_STATE2 cls2                                   -- #awt #todo missing table
--          on (                                                 -- #awt #todo missing table
--              ce.SERIOUSNESS = cls2.ID                         -- #awt #todo missing table
--          )                                                    -- #awt #todo missing table
    Left  Join
        LM_CAUSALITY lcau
            on (
                cea.DET_CAUSALITY_ID = lcau.CAUSALITY_ID
                and cea.enterprise_id = lcau.enterprise_id
            )
    Left  Join
        LM_CAUSALITY lcaub
            on (
                cea.RPT_CAUSALITY_ID = lcaub.CAUSALITY_ID
                and cea.enterprise_id = lcaub.enterprise_id
            )
--  where
--      (
--          (
--              UPPER(llist.LISTEDNESS) IN (
--                  UPPER('Listed'), UPPER('Unknown'), UPPER('Unlisted')
--              )
--          )
--          and (
--              (
--                  UPPER(lco.COUNTRY) IN (
--                      UPPER('FRANCE'), UPPER('JAPAN'), UPPER('UNITED KINGDOM'), UPPER('UNITED STATES')
--                  )
--              )
--              and (                                            -- #awt #todo missing table
--                  UPPER(cldt.DRUG_TYPE) = UPPER('Suspect')     -- #awt #todo missing table
--              )                                                -- #awt #todo missing table
--              and (
--                  UPPER(lp.PROD_NAME) IN (
--                      UPPER('ASPIRIN (E.C.)'), UPPER('RxLogi-Quick Tablet 30 mg'), UPPER('RxLogi-Quick Tablet 45 mg'), UPPER('RxLogix Product A'), UPPER('Test Group Product'), UPPER('cinacalcet HCI'), UPPER('cinacalcet HCI')
--                  )
--              )
--              and (                                            -- #awt #todo missing table
--                  UPPER(cls2.STATE2) IN (                      -- #awt #todo missing table
--                      UPPER('No'), UPPER('Yes')                -- #awt #todo missing table
--                  )                                            -- #awt #todo missing table
--              )                                                -- #awt #todo missing table
--          )
--          and (
--              (
--                  UPPER(lcau.CAUSALITY) IN (
--                      UPPER('Almost Certain'), UPPER('Not Related'), UPPER('Probable'), UPPER('Related'), UPPER('Unknown')
--                  )
--              )
--              or (
--                  UPPER(lcaub.CAUSALITY) IN (
--                      UPPER('Not Related'), UPPER('Possible'), UPPER('Related'), UPPER('Unknown')
--                  )
--              )
--          )
--      )
