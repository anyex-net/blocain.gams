-- 共同的协议增量_生产环境

UPDATE  ACCOUNT_COMMON_AGREEMENT SET EN_NAME = 'Certification Regarding Trading Control and Ownership of the Account'
,PATH='https://gams-release.s3-ap-northeast-1.amazonaws.com/File/agreement/Certification+Regarding+Trading+Control+and+Ownership+of+the+Account.pdf'
,AGREEMENT_NUMBER='fg04'
,AGREEMENT_DESCRIPTION=null
,CN_NAME='账户交易控制及所有权证明' WHERE id=20000000004;


UPDATE  ACCOUNT_COMMON_AGREEMENT SET
EN_NAME = 'Notice Regarding NFA''s BASIC System'
,PATH='https://gams-release.s3-ap-northeast-1.amazonaws.com/File/agreement/Notice+Regarding+NFA''s+BASIC+System.pdf'
,AGREEMENT_NUMBER='fg07'
,AGREEMENT_DESCRIPTION=null
,CN_NAME='Notice Regarding NFA''s BASIC System' WHERE id =20000000007;


UPDATE  ACCOUNT_COMMON_AGREEMENT  SET EN_NAME = 'Essential Legal Terms for your MOF Account'
,PATH = 'https://gams-release.s3-ap-northeast-1.amazonaws.com/File/agreement/Essential+Legal+Terms+for+your+MOF+Account.pdf'
,AGREEMENT_NUMBER='fg09'
,AGREEMENT_DESCRIPTION=null
,CN_NAME= '有关您MOF账户的重要法律条款' WHERE id = 20000000009;

commit;

