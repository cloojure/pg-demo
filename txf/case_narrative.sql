SELECT
    A.ENTERPRISE_ID, A.CASE_ID, A.ABBREV_NARRATIVE, A.ABBREV_NARRATIVE_J, A.NARRATIVE,
    A.NARRATIVE_J, B.VERSION_NUM, B.EFFECTIVE_START_DATE, B.EFFECTIVE_END_DATE
FROM
    ArgusMart.RM_CASE_NARRATIVE AS A, ArgusMartView.CASE_VERSION_TABLE AS B
WHERE
    (A.CASE_ID = B.CASE_ID) AND 
    (A.EFFECTIVE_START_DATE <= B.EFFECTIVE_START_DATE) AND 
    (A.EFFECTIVE_END_DATE > B.EFFECTIVE_START_DATE)

--------------------
