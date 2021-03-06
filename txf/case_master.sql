SELECT
    CM.CASE_ID, CM.GLOBAL_NUM, CM.INITIAL_JUSTIFICATION, CM.WORKFLOW_SEQ_NUM,
    CM.OWNER_ID, CM.SITE_ID, CM.SUSAR, CM.CLOSE_USER_ID, CM.CLOSE_DATE,
    CM.DATE_LOCKED, CM.CLOSE_NOTES, CM.STATE_ID, CM.LAST_STATE_ID,
    CM.REQUIRES_FOLLOWUP, CM.DUE_SOON, CM.CREATE_TIME, CM.FOLLOWUP_DATE,
    CM.INIT_REPT_DATE, CM.LAST_UPDATE_TIME, CM.SAFETY_DATE, CM.WORKLIST_OWNER_ID,
    CM.CASE_NUM, CM.E2B_WW_NUMBER, CM.COUNTRY_ID, CM.RPT_TYPE_ID, CM.USER_ID,
    CM.LAST_UPDATE_USER_ID, CM.SERIOUSNESS, cvt.VERSION_NUM, cvt.EFFECTIVE_START_DATE,
    cvt.EFFECTIVE_END_DATE, CM.ENTERPRISE_ID
FROM
    ArgusMart.RM_CASE_MASTER AS CM, ArgusMart.CASE_VERSION_TABLE AS cvt
WHERE
    (cvt.CASE_ID = CM.CASE_ID) AND 
    (cvt.EFFECTIVE_START_DATE >= convert(CM.EFFECTIVE_START_DATE, timestamp)) AND 
    (cvt.EFFECTIVE_START_DATE < convert(CM.EFFECTIVE_END_DATE, timestamp)) AND 
    (CM.DELETED_FLAG = convert(0, bigdecimal)) AND 
    (CM.ENTERPRISE_ID = cvt.ENTERPRISE_ID)

--------------------

