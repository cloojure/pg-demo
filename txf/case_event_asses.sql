SELECT
    a.CASE_ID, a.PROD_SEQ_NUM, a.EVENT_SEQ_NUM, a.LICENSE_ID, a.DATASHEET_ID,
    a.SOURCE_ID, a.DET_CAUSALITY_ID, a.SEQ_NUM, a.CAUSALITY_SCORE, a.CAUSE_JUSTIFY,
    a.LISTED_JUSTIFY, a.CAUSALITY_ASSESSMENT, a.DET_LISTEDNESS_ID, a.RPT_CAUSALITY_ID,
    a.PRT_CAUSALITY_ID, b.VERSION_NUM, b.EFFECTIVE_START_DATE, b.EFFECTIVE_END_DATE,
    a.ENTERPRISE_ID
FROM
    ArgusMart.RM_CASE_EVENT_ASSESS AS a, ArgusMart.CASE_VERSION_TABLE AS b
WHERE
    (a.CASE_ID = b.CASE_ID) AND 
    (a.ENTERPRISE_ID = b.ENTERPRISE_ID) AND 
    (a.DELETED_FLAG = convert(0, bigdecimal)) AND 
    (convert(a.EFFECTIVE_START_DATE, timestamp) <= b.EFFECTIVE_START_DATE) AND 
    (convert(a.EFFECTIVE_END_DATE, timestamp) > b.EFFECTIVE_START_DATE)

--------------------

