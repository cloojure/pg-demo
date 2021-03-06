SELECT
    cf.CASE_ID, cf.SEQ_NUM, cf.AUDITLOG_TYPE, cf.TIME_STAMP, cf.RECEIPT_DATE,
    cf.SAFETY_DATE, cf.SIGNIFICANT, cvt.VERSION_NUM, cvt.EFFECTIVE_START_DATE,
    cvt.EFFECTIVE_END_DATE, cf.ENTERPRISE_ID
FROM
    ArgusMart.RM_CASE_FOLLOWUP AS cf, ArgusMart.CASE_VERSION_TABLE AS cvt
WHERE
    (cf.CASE_ID = cvt.CASE_ID) AND 
    (cf.ENTERPRISE_ID = cvt.ENTERPRISE_ID) AND 
    (convert(cf.EFFECTIVE_START_DATE, timestamp) <= cvt.EFFECTIVE_START_DATE) AND 
    (convert(cf.EFFECTIVE_END_DATE, timestamp) > cvt.EFFECTIVE_START_DATE) AND 
    (cf.DELETED_FLAG = convert(0, bigdecimal))

--------------------

