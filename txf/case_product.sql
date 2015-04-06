SELECT
    cp.SDRUG_NOT_ADMIN, cp.PROD_REPTD, cp.PROD_CODED, cp.CASE_ID, cp.FAMILY_ID,
    cp.SEQ_NUM, cp.PRODUCT_ID, cp.DRUG_TYPE, cp.PAT_EXPOSURE, cp.MANUFACTURER_ID,
    cp.FIRST_SUS_PROD, cp.COUNTRY_ID, cp.WHO_DRUG_CODE, cp.CO_DRUG_CODE,
    cp.PRODUCT_NAME, cp.GENERIC_NAME, cvt.VERSION_NUM, cvt.EFFECTIVE_START_DATE,
    cvt.EFFECTIVE_END_DATE, cp.ENTERPRISE_ID, cp.SORT_ID
FROM
    ArgusMart.RM_CASE_PRODUCT AS cp, ArgusMart.CASE_VERSION_TABLE AS cvt
WHERE
    (cp.CASE_ID = cvt.CASE_ID) AND 
    (cp.ENTERPRISE_ID = cvt.ENTERPRISE_ID) AND 
    (convert(cp.EFFECTIVE_START_DATE, timestamp) <= cvt.EFFECTIVE_START_DATE) AND 
    (convert(cp.EFFECTIVE_END_DATE, timestamp) > cvt.EFFECTIVE_START_DATE) AND 
    (cp.DELETED_FLAG = convert(0, bigdecimal))

--------------------

