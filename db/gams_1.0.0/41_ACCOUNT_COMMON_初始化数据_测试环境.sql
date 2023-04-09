declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_common_agreement');
  if num > 0 then
    execute immediate 'drop table account_common_agreement';
  end if;
end;
/
--共同的协议
CREATE TABLE account_common_agreement(
  id NUMBER(20) NOT NULL,
  en_name VARCHAR2(100) NOT NULL,
  path VARCHAR2(256) NOT NULL,
  agreement_number VARCHAR2(32) ,
  agreement_description VARCHAR2(256),
  cn_name VARCHAR2(100),
  CONSTRAINT account_common_agreement_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_common_agreement.id IS 'id';
COMMENT ON COLUMN account_common_agreement.en_name IS '协议英文名';
COMMENT ON COLUMN account_common_agreement.path IS '协议存储路径';
COMMENT ON COLUMN account_common_agreement.agreement_number IS '协议编号';
COMMENT ON COLUMN account_common_agreement.agreement_description IS '协议描述';
COMMENT ON COLUMN account_common_agreement.cn_name IS '协议中文名';
COMMENT ON TABLE account_common_agreement IS '共同的协议';

Insert into ACCOUNT_COMMON_AGREEMENT (ID,EN_NAME,PATH,AGREEMENT_NUMBER,AGREEMENT_DESCRIPTION,CN_NAME) values (20000000001,'Day Trading Risk Disclosure Statement','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/agreement/%E6%97%A5%E5%86%85%E4%BA%A4%E6%98%93%E9%A3%8E%E9%99%A9%E6%8A%AB%E9%9C%B2%E5%A3%B0%E6%98%8E.pdf','fg01',null,'日内交易风险披露声明');
Insert into ACCOUNT_COMMON_AGREEMENT (ID,EN_NAME,PATH,AGREEMENT_NUMBER,AGREEMENT_DESCRIPTION,CN_NAME) values (20000000002,'Hong Kong Risk Disclosure','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/agreement/%E9%A6%99%E6%B8%AF%E9%A3%8E%E9%99%A9%E6%8A%AB%E9%9C%B2+HONG+KONG+RISK+DISCLOSURE+STATEMENT.pdf','fg02',null,'香港风险披露');
Insert into ACCOUNT_COMMON_AGREEMENT (ID,EN_NAME,PATH,AGREEMENT_NUMBER,AGREEMENT_DESCRIPTION,CN_NAME) values (20000000013,'Customer Agreement Institutional Services','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/agreement/%E6%9C%BA%E6%9E%84%E5%8D%8F%E8%AE%AE.pdf','fg13',null,'机构协议');
Insert into ACCOUNT_COMMON_AGREEMENT (ID,EN_NAME,PATH,AGREEMENT_NUMBER,AGREEMENT_DESCRIPTION,CN_NAME) values (20000000003,'Customer Agreement US','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/agreement/%E5%AE%A2%E6%88%B7%E5%8D%8F%E8%AE%AE%E7%BE%8E%E5%9B%BD.pdf','fg03',null,'客户协议美国');
Insert into ACCOUNT_COMMON_AGREEMENT (ID,EN_NAME,PATH,AGREEMENT_NUMBER,AGREEMENT_DESCRIPTION,CN_NAME) values (20000000004,'Certification Regarding Trading Control and Ownership of the Account','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/agreement/%E8%B4%A6%E6%88%B7%E4%BA%A4%E6%98%93%E6%8E%A7%E5%88%B6%E5%8F%8A%E6%89%80%E6%9C%89%E6%9D%83%E8%AF%81%E6%98%8E.pdf','fg04',null,'账户交易控制及所有权证明');
Insert into ACCOUNT_COMMON_AGREEMENT (ID,EN_NAME,PATH,AGREEMENT_NUMBER,AGREEMENT_DESCRIPTION,CN_NAME) values (20000000005,'Forex and Multi-Currency Risk Disclosure','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/agreement/Forex+and+Multi-Currency+Risk+Disclosure.pdf','fg05',null,'Forex and Multi-Currency Risk Disclosure');
Insert into ACCOUNT_COMMON_AGREEMENT (ID,EN_NAME,PATH,AGREEMENT_NUMBER,AGREEMENT_DESCRIPTION,CN_NAME) values (20000000006,'Risks of After-Hours Trading','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/agreement/%E5%B8%82%E5%90%8E%E4%BA%A4%E6%98%93%E9%A3%8E%E9%99%A9.pdf','fg06',null,'市后交易风险');
Insert into ACCOUNT_COMMON_AGREEMENT (ID,EN_NAME,PATH,AGREEMENT_NUMBER,AGREEMENT_DESCRIPTION,CN_NAME) values (20000000007,'Notice Regarding NFA''s BASIC System','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/agreement/Notice+Regarding+NFA''s+BASIC+System.pdf','fg07',null,'Notice Regarding NFA''s BASIC System');
Insert into ACCOUNT_COMMON_AGREEMENT (ID,EN_NAME,PATH,AGREEMENT_NUMBER,AGREEMENT_DESCRIPTION,CN_NAME) values (20000000008,'US Stock Stop Order Disclosure','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/agreement/%E7%BE%8E%E5%9B%BD%E8%82%A1%E7%A5%A8%E6%AD%A2%E6%8D%9F%E5%AE%9A%E5%8D%95%E6%8A%AB%E9%9C%B2.pdf','fg08',null,'美国股票止损定单披露');
Insert into ACCOUNT_COMMON_AGREEMENT (ID,EN_NAME,PATH,AGREEMENT_NUMBER,AGREEMENT_DESCRIPTION,CN_NAME) values (20000000009,'Essential Legal Terms for your IB Account','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/agreement/%E6%9C%89%E5%85%B3%E6%82%A8IB%E8%B4%A6%E6%88%B7%E7%9A%84%E9%87%8D%E8%A6%81%E6%B3%95%E5%BE%8B%E6%9D%A1%E6%AC%BE.pdf','fg09',null,'有关您IB账户的重要法律条款');
Insert into ACCOUNT_COMMON_AGREEMENT (ID,EN_NAME,PATH,AGREEMENT_NUMBER,AGREEMENT_DESCRIPTION,CN_NAME) values (20000000010,'MOF Securities Privacy Policy','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/agreement/MOF+Securities+Privacy+Policy.pdf','fg10',null,'MOF Securities Privacy Policy');
Insert into ACCOUNT_COMMON_AGREEMENT (ID,EN_NAME,PATH,AGREEMENT_NUMBER,AGREEMENT_DESCRIPTION,CN_NAME) values (20000000011,'US Margin Trading Risk Disclosure','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/agreement/US+Margin+Trading+Risk+Disclosure.pdf','fg11',null,'US Margin Trading Risk Disclosure');
Insert into ACCOUNT_COMMON_AGREEMENT (ID,EN_NAME,PATH,AGREEMENT_NUMBER,AGREEMENT_DESCRIPTION,CN_NAME) values (20000000012,'Portfolio Margin Risk Disclosure','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/agreement/%E6%8A%95%E8%B5%84%E7%BB%84%E5%90%88%E4%BF%9D%E8%AF%81%E9%87%91%E9%A3%8E%E9%99%A9%E6%8A%AB%E9%9C%B2.pdf','fg12',null,'投资组合保证金风险披露');
Insert into ACCOUNT_COMMON_AGREEMENT (ID,EN_NAME,PATH,AGREEMENT_NUMBER,AGREEMENT_DESCRIPTION,CN_NAME) values (20000000014,'Org Auth Traders','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/agreement/Org+Auth+Traders.pdf','fg14',null,'Org Auth Traders');

commit;

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_common_trade_location');
  if num > 0 then
    execute immediate 'drop table account_common_trade_location';
  end if;
end;
/
-- 共同的交易地点表
CREATE TABLE account_common_trade_location(
  id NUMBER(20) NOT NULL,
  tansaction_location VARCHAR2(64) NOT NULL,
  tansaction_type VARCHAR2(32) NOT NULL,
  tansaction_region VARCHAR2(32) NOT NULL,
  cn_name VARCHAR2(128) NOT NULL,
  en_name VARCHAR2(128) NOT NULL,
  CONSTRAINT account_common_tradelocationPK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_common_trade_location.id IS 'id';
COMMENT ON COLUMN account_common_trade_location.tansaction_location IS '交易地点';
COMMENT ON COLUMN account_common_trade_location.tansaction_type IS '交易类型';
COMMENT ON COLUMN account_common_trade_location.tansaction_region IS '交易区域';
COMMENT ON COLUMN account_common_trade_location.cn_name IS '中文名';
COMMENT ON COLUMN account_common_trade_location.en_name IS '英文名';
COMMENT ON TABLE account_common_trade_location IS '共同的交易地点表';

Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('HK','Stock','AsiaPacific',2,'香港','Hong Kong');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('AU','Stock','AsiaPacific',1,'澳大利亚','Australia');

Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('JP','Stock','AsiaPacific',4,'日本','Japan');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('ES','Options','Europe',43,'西班牙','Spain');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('BALTICSEA','Stock','Europe',6,'波罗的海（爱沙尼亚、拉脱维亚、立陶宛）','BalticSea (Estonia, Latvia, Lithuania)');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('BE','Stock','Europe',7,'比利时','Belgium');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('FR','Stock','Europe',8,'法国','France');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('DE','Stock','Europe',9,'德国','Germany');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('IT','Stock','Europe',10,'意大利','Italy');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('NL','Stock','Europe',11,'荷兰','Netherlands');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('NO','Stock','Europe',12,'挪威','Norway');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('ES','Stock','Europe',13,'西班牙','Spain');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('SE','Stock','Europe',14,'瑞典','Sweden');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('CH','Stock','Europe',15,'瑞士','Switzerland');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('UK','Stock','Europe',16,'英国','United Kingdom');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('CA','Stock','NorthAmerica',17,'加拿大','Canada');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('MX','Stock','NorthAmerica',18,'墨西哥','Mexico');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('US','Stock','NorthAmerica',19,'美国','United States');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('USA','Stock','NorthAmerica',20,'美国（算法执行场所）','USA (Place for Algorithmic Implementation)');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('UST','Stock','NorthAmerica',21,'美国（T+1结算）','U.S. (T+1 Settlement)');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('HK','Bond','AsiaPacific',22,'香港','Hong Kong');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('US','Bond','NorthAmerica',23,'美国','United States');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('FR','Warrant','Europe',24,'法国','France');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('DE','Warrant','Europe',25,'德国','Germany');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('IT','Warrant','Europe',26,'意大利','Italy');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('NL','Warrant','Europe',27,'荷兰','Netherlands');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('CH','Warrant','Europe',28,'瑞士','Switzerland');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('AU','Options','AsiaPacific',29,'澳大利亚','Australia');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('HK','Options','AsiaPacific',30,'香港','Hong Kong');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('JP','Options','AsiaPacific',31,'日本','Japan');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('BE','Options','Europe',32,'比利时','Belgium');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('FR','Options','Europe',33,'法国','France');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('DE','Options','Europe',34,'德国','Germany');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('IT','Options','Europe',35,'意大利','Italy');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('NL','Options','Europe',36,'荷兰','Netherlands');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('SE','Options','Europe',37,'瑞典','Sweden');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('CH','Options','Europe',38,'瑞士','Switzerland');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('UK','Options','Europe',39,'英国','United Kingdom');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('CA','Options','NorthAmerica',40,'加拿大','Canada');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('MX','Options','NorthAmerica',41,'墨西哥','Mexico');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('US','Options','NorthAmerica',42,'美国','United States');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('AU','Futures','AsiaPacific',44,'澳大利亚','Australia');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('HK','Futures','AsiaPacific',45,'香港','Hong Kong');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('JP','Futures','AsiaPacific',46,'日本','Japan');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('KR','Futures','AsiaPacific',47,'韩国','South Korea');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('SG','Futures','AsiaPacific',48,'新加坡','Singapore');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('FR','Futures','Europe',49,'法国','France');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('DE','Futures','Europe',50,'德国','Germany');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('IT','Futures','Europe',51,'意大利','Italy');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('ES','Futures','Europe',52,'西班牙','Spain');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('NO','Futures','Europe',53,'挪威','Norway');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('SE','Futures','Europe',54,'瑞典','Sweden');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('CH','Futures','Europe',55,'瑞士','Switzerland');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('UK','Futures','Europe',56,'英国','United Kingdom');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('CA','Futures','NorthAmerica',57,'加拿大','Canada');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('MX','Futures','NorthAmerica',58,'墨西哥','Mexico');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('US','Futures','NorthAmerica',59,'美国','United States');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('USF','Futures','NorthAmerica',60,'美国（大厅式叫价交易所）','United States（Floor Based Exchanges）');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('USI','Futures','NorthAmerica',61,'美国（国际石油交易所）','United States（International Petroleum Exchange）');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('USC','Futures','NorthAmerica',62,'美国（加密货币）','United States（Cropto）');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('AU','ForwardOption','AsiaPacific',63,'澳大利亚','Australia');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('JP','ForwardOption','AsiaPacific',64,'日本','Japan');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('SG','ForwardOption','AsiaPacific',65,'新加坡','Singapore');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('FR','ForwardOption','Europe',66,'法国','France');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('DE','ForwardOption','Europe',67,'德国','Germany');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('ES','ForwardOption','Europe',68,'西班牙','Spain');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('UK','ForwardOption','Europe',69,'英国','United Kingdom');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('MX','ForwardOption','NorthAmerica',70,'墨西哥','Mexico');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('US','ForwardOption','NorthAmerica',71,'美国','United States');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('USF','ForwardOption','NorthAmerica',72,'美国（大厅式叫价交易所）','United States（Floor Based Exchanges）');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('USI','ForwardOption','NorthAmerica',73,'美国（国际石油交易所）','United States（International Petroleum Exchange）');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('IT','SingleStockFutures','Europe',74,'意大利','Italy');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('NL','SingleStockFutures','Europe',75,'荷兰','Netherlands');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('ES','SingleStockFutures','Europe',76,'西班牙','Spain');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('MX','SingleStockFutures','NorthAmerica',77,'墨西哥','Mexico');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('US','SingleStockFutures','NorthAmerica',78,'美国','United States');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('US','NextShares','NorthAmerica',79,'美国','United States');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('BE','MutualFund','Europe',80,'比利时','Belgium');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('GLOBAL','CurrencyConverter','Global',81,'全球','Global');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('FR','Bond','Europe',90,'法国','France');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('NL','Bond','Europe',91,'荷兰','Netherlands');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('SE','Bond','Europe',92,'瑞典','Sweden');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('DE','Bond','Europe',93,'德国','Germany');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('NO','Bond','Europe',94,'挪威','Norway');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('CH','Bond','Europe',95,'瑞士','Switzerland');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('BE','Bond','Europe',96,'比利时','Belgium');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('IT','Bond','Europe',97,'意大利','Italy');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('ES','Bond','Europe',98,'西班牙','Spain');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('UK','Bond','Europe',99,'英国','United Kingdom');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('BALTICSEA','Bond','Europe',100,'波罗的海（爱沙尼亚、拉脱维亚、立陶宛）','BalticSea (Estonia, Latvia, Lithuania)');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('CENTRALEUROPE','Bond','Europe',101,'中欧','Central Europe');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('IT','MutualFund','Europe',102,'意大利','Italy');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('SE','MutualFund','Europe',103,'瑞典','Sweden');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('FR','MutualFund','Europe',104,'法国','France');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('NL','MutualFund','Europe',105,'荷兰','Netherlands');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('CH','MutualFund','Europe',106,'瑞士','Switzerland');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('DE','MutualFund','Europe',107,'德国','Germany');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('ES','MutualFund','Europe',108,'西班牙','Spain');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('UK','MutualFund','Europe',109,'英国','United Kingdom');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('SG','Stock','AsiaPacific',5,'新加坡','Singapore');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('AT','Stock','Europe',82,'奥地利','Austria');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('AT','Bond','Europe',83,'奥地利','Austria');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('BE','Futures','Europe',84,'比利时','Belgium');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('NL','Futures','Europe',85,'荷兰','Netherlands');
Insert into  account_common_trade_location (TANSACTION_LOCATION,TANSACTION_TYPE,TANSACTION_REGION,ID,CN_NAME,EN_NAME) values ('CENTRALEUROPE','Stock','Europe',86,'中欧','Central Europe');

commit;



declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_common_file');
  if num > 0 then
    execute immediate 'drop table account_common_file';
  end if;
end;
/
--公共的文件  
CREATE TABLE account_common_file(
  id NUMBER(20) NOT NULL,
  file_name VARCHAR2(256) NOT NULL,
  account_type VARCHAR2(64) NOT NULL,
  file_path VARCHAR2(256) NOT NULL,
  dict_value VARCHAR2(256) NOT NULL,
  lang VARCHAR2(32) NOT NULL,
  kind NUMBER(20) NOT NULL,
  CONSTRAINT account_common_file_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_common_file.id IS 'id';
COMMENT ON COLUMN account_common_file.file_name IS '文件名';
COMMENT ON COLUMN account_common_file.account_type IS '账户类型';
COMMENT ON COLUMN account_common_file.file_path IS '文件路径';
COMMENT ON COLUMN account_common_file.dict_value IS '对应字典数据的dictValue';
COMMENT ON COLUMN account_common_file.lang IS '语言';
COMMENT ON COLUMN account_common_file.kind IS '协议种类';
COMMENT ON TABLE account_common_file IS '公共的文件';
Insert into account_common_file (ID,file_name,account_type,file_path,dict_value,lang,kind) values (20000000001,'身份和生日证明','personal','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/personal/%E4%B8%AA%E4%BA%BA%E8%BA%AB%E4%BB%BD%E5%92%8C%E7%94%9F%E6%97%A5%E8%AF%81%E6%98%8E.pdf','identityAndBirthdayCertificate','zh_CN','1');
Insert into account_common_file (ID,file_name,account_type,file_path,dict_value,lang,kind) values (20000000002,'住址证明','personal','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/personal/%E4%BD%8F%E5%9D%80%E8%AF%81%E6%98%8E.pdf','proofOfAddress','zh_CN','1');
Insert into account_common_file (ID,file_name,account_type,file_path,dict_value,lang,kind) values (20000000003,'条约国家的地址证明','personal','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/personal/%E6%9D%A1%E7%BA%A6%E5%9B%BD%E5%AE%B6%E7%9A%84%E5%9C%B0%E5%9D%80%E8%AF%81%E6%98%8E.pdf','proofOfAddressOfATreatyState','zh_CN','2');
Insert into account_common_file (ID,file_name,account_type,file_path,dict_value,lang,kind) values (20000000004,'条约国家的身份证明','personal','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/personal/%E6%9D%A1%E7%BA%A6%E5%9B%BD%E5%AE%B6%E7%9A%84%E8%BA%AB%E4%BB%BD%E8%AF%81%E6%98%8E.pdf','identificationOfTreatyStates','zh_CN','2');
Insert into account_common_file (ID,file_name,account_type,file_path,dict_value,lang,kind) values (920000000001,'Proof of Identity and Date of Birth','personal','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/personal/Proof+of+Identity+and+Date+of+Birth.pdf','identityAndBirthdayCertificate','en_US','1');
Insert into account_common_file (ID,file_name,account_type,file_path,dict_value,lang,kind) values (920000000002,'Proof of address','personal','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/personal/Personal+address+proof.pdf','proofOfAddress','en_US','1');
Insert into account_common_file (ID,file_name,account_type,file_path,dict_value,lang,kind) values (920000000003,'Proof of address of the treaty country','personal','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/personal/Proof+of+Address+in+Treaty+Country.pdf','proofOfAddressOfATreatyState','en_US','2');
Insert into account_common_file (ID,file_name,account_type,file_path,dict_value,lang,kind) values (920000000004,'Proof of identity of the treaty country','personal','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/personal/Proof+of+ID+in+Treaty+Country.pdf','identificationOfTreatyStates','en_US','2');



commit;


declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('upload_file_type');
  if num > 0 then
    execute immediate 'drop table upload_file_type';
  end if;
end;
/
--上传文件类型
CREATE TABLE upload_file_type(
  id NUMBER(20) NOT NULL,
  co_personal_id NUMBER(20),
  type_key VARCHAR2(256) NOT NULL,
  type VARCHAR2(64) NOT NULL,
  lang VARCHAR2(8) NOT NULL,
  content VARCHAR2(256) NOT NULL,
  dest VARCHAR2(256) NOT NULL,
  more_info_pdf VARCHAR2(256) NOT NULL,
  account_type VARCHAR2(64) NOT NULL,
  kind NUMBER(20) NOT NULL,
  CONSTRAINT upload_file_type_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN upload_file_type.id IS 'id';
COMMENT ON COLUMN upload_file_type.co_personal_id IS '个人股东法人股东的id';
COMMENT ON COLUMN upload_file_type.type_key IS '类型key';
COMMENT ON COLUMN upload_file_type.type IS '类型';
COMMENT ON COLUMN upload_file_type.lang IS '语言编码（en_US,zh_CN,zh_HK）';
COMMENT ON COLUMN upload_file_type.content IS '类型内容';
COMMENT ON COLUMN upload_file_type.dest IS '描述';
COMMENT ON COLUMN upload_file_type.more_info_pdf IS '更多信息的PDF文件';
COMMENT ON COLUMN upload_file_type.account_type IS '帐户类型';
COMMENT ON COLUMN upload_file_type.kind IS '文件种类';
COMMENT ON TABLE upload_file_type IS '上传文件类型';
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (20000000001,null,'proof_of_ability_to_trade_on_margin','marginCertificate','zh_CN','Proof of Ability to Trade on Margin - company','保证金交易能力证明-公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/limitedLiablity/Proof+of+Ability+to+Trade+on+Margin+-+%E6%9C%89%E9%99%90%E8%B4%A3%E4%BB%BB%E5%85%AC%E5%8F%B8.pdf','limitedLiablity','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (20000000002,null,'proof_of_existence_company','existenceProof','zh_CN','Proof of Existence - company','存在证明-公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/limitedLiablity/Proof+of+Existence+%E6%9C%89%E9%99%90%E8%B4%A3%E4%BB%BB%E5%85%AC%E5%8F%B8.pdf','limitedLiablity','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (20000000003,null,'proof_of_principal_place_of_business_address_company','locationCertificate','zh_CN','Proof of Principal Place of Business Address - company','主要营业地点证明-公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/limitedLiablity/Proof+of+Principal+Place+of+Business+Address%C2%A0-%E6%9C%89%E9%99%90%E8%B4%A3%E4%BB%BB%E5%85%AC%E5%8F%B8.pdf','limitedLiablity','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (20000000004,null,'authorizedPerson_identity','identityCertificate','zh_CN','请提供authorizedPerson的身份和生日证明','请提供授权人的身份和生日证明','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/limitedLiablity/%E8%A2%AB%E6%8E%88%E6%9D%83%E4%BA%BA%E7%9A%84%E8%BA%AB%E4%BB%BD%E5%92%8C%E7%94%9F%E6%97%A5%E8%AF%81%E6%98%8E.pdf','limitedLiablity','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (20000000005,null,'individualShareholder_identity','identityCertificate','zh_CN','请提供%s的身份和生日证明','请提供个人股东的身份和生日证明','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/limitedLiablity/%E4%B8%AA%E4%BA%BA%E8%82%A1%E4%B8%9C%E7%9A%84%E8%BA%AB%E4%BB%BD%E5%92%8C%E7%94%9F%E6%97%A5%E8%AF%81%E6%98%8E.pdf','limitedLiablity','2');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (20000000006,null,'authorizedPerson_address','addressCertificate','zh_CN','authorizedPerson的住址证明','授权人的住址证明','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/limitedLiablity/%E8%A2%AB%E6%8E%88%E6%9D%83%E4%BA%BA%E7%9A%84%E4%BD%8F%E5%9D%80%E8%AF%81%E6%98%8E.pdf','limitedLiablity','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (20000000007,null,'individualShareholder_address','addressCertificate','zh_CN','%s的住址证明','个人股东的住址证明','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/limitedLiablity/%E4%B8%AA%E4%BA%BA%E8%82%A1%E4%B8%9C%E7%9A%84%E4%BD%8F%E5%9D%80%E8%AF%81%E6%98%8E.pdf','limitedLiablity','2');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (20000000008,null,'proof_of_existence_legalPersonShareholderCompany','existenceProof','zh_CN','Proof of Existence - %s','存在证明-法人股东公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/limitedLiablity/Proof+of+Existence+-+%E6%B3%95%E4%BA%BA%E8%82%A1%E4%B8%9C%E5%85%AC%E5%8F%B8+(OWNER).pdf','limitedLiablity','3');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (20000000009,null,'proof_of_principal_place_of_business_address_legalPersonShareholderCompany','locationCertificate','zh_CN','Proof of Principal Place of Business Address - %s','主要营业地点证明-法人股东公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/limitedLiablity/Proof+of+Principal+Place+of+Business+Address%C2%A0-%E6%B3%95%E4%BA%BA%E8%82%A1%E4%B8%9C%E5%85%AC%E5%8F%B8.pdf','limitedLiablity','3');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (20000000010,null,'proof_of_authorization_to_open_account_company','authorizerCertificate','zh_CN','Proof of Authorization to Open Account - company','开户授权证明-公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/limitedLiablity/Proof+of+Authorization+to+Open+Account+-+%E6%9C%89%E9%99%90%E8%B4%A3%E4%BB%BB%E5%85%AC%E5%8F%B8.pdf','limitedLiablity','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (20000001001,null,'proof_of_ability_to_trade_on_margin','marginCertificate','zh_CN','Proof of Ability to Trade on Margin - company','保证金交易能力证明-公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/partnerShip/Proof+of+Ability+to+Trade+on+Margin+-+%E5%90%88%E4%BC%99%E4%BC%81%E4%B8%9A.pdf','partnerShip','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (20000001002,null,'proof_of_existence_company','existenceProof','zh_CN','Proof of Existence - company','存在证明-公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/partnerShip/Proof+of+Existence+-+%E5%90%88%E4%BC%99%E4%BC%81%E4%B8%9A.pdf','partnerShip','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (20000001003,null,'proof_of_principal_place_of_business_address_company','locationCertificate','zh_CN','Proof of Principal Place of Business Address - company','主要营业地点证明-公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/partnerShip/Proof+of+Principal+Place+of+Business+Address+-+%E5%90%88%E4%BC%99%E4%BC%81%E4%B8%9A.pdf','partnerShip','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (20000001004,null,'authorizedPerson_identity','identityCertificate','zh_CN','请提供authorizedPerson的身份和生日证明','请提供授权人的身份和生日证明','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/partnerShip/%E8%AF%B7%E6%8F%90%E4%BE%9B%E8%A2%AB%E6%8E%88%E6%9D%83%E4%BA%BA%E7%9A%84%E8%BA%AB%E4%BB%BD%E5%92%8C%E7%94%9F%E6%97%A5%E8%AF%81%E6%98%8E.pdf','partnerShip','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (20000001005,null,'individualShareholder_identity','identityCertificate','zh_CN','请提供%s的身份和生日证明','请提供个人股东的身份和生日证明','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/partnerShip/%E8%AF%B7%E6%8F%90%E4%BE%9B%E4%B8%AA%E4%BA%BA%E8%82%A1%E4%B8%9C%E7%9A%84%E8%BA%AB%E4%BB%BD%E5%92%8C%E7%94%9F%E6%97%A5%E8%AF%81%E6%98%8E.pdf','partnerShip','2');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (20000001006,null,'authorizedPerson_address','addressCertificate','zh_CN','authorizedPerson的住址证明','授权人的住址证明','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/partnerShip/%E8%A2%AB%E6%8E%88%E6%9D%83%E4%BA%BA%E4%BD%8F%E5%9D%80%E8%AF%81%E6%98%8E.pdf','partnerShip','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (20000001007,null,'individualShareholder_address','addressCertificate','zh_CN','%s的住址证明','个人股东的住址证明','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/partnerShip/%E4%B8%AA%E4%BA%BA%E8%82%A1%E4%B8%9C%E5%9C%B0%E5%9D%80.pdf','partnerShip','2');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (20000001008,null,'proof_of_existence_legalPersonShareholderCompany','existenceProof','zh_CN','Proof of Existence - %s','存在证明-法人股东公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/partnerShip/Proof+of+Existence+-+%E6%B3%95%E4%BA%BA%E8%82%A1%E4%B8%9C%E5%85%AC%E5%8F%B8.pdf','partnerShip','3');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (20000001009,null,'proof_of_principal_place_of_business_address_legalPersonShareholderCompany','locationCertificate','zh_CN','Proof of Principal Place of Business Address - %s','主要营业地点证明-法人股东公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/partnerShip/Proof+of+Principal+Place+of+Business+Address+-+%E6%B3%95%E4%BA%BA%E8%82%A1%E4%B8%9C%E5%85%AC%E5%8F%B8.pdf','partnerShip','3');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (20000001010,null,'proof_of_authorization_to_open_account_company','authorizerCertificate','zh_CN','Proof of Authorization to Open Account - company','开户授权证明-公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/partnerShip/Proof+of+Authorization+to+Open+Account+-+%E5%90%88%E4%BC%99%E4%BC%81%E4%B8%9A.pdf','partnerShip','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (920000000001,null,'proof_of_ability_to_trade_on_margin','marginCertificate','en_US','Proof of Ability to Trade on Margin - company','保证金交易能力证明-公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/limitedLiablity/Proof+of+Ability+to+Trade+on+Margin+-+Limited+Liability+Company.pdf','limitedLiablity','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (920000000002,null,'proof_of_existence_company','existenceProof','en_US','Proof of Existence - company','存在证明-公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/limitedLiablity/Proof+of+Existence+-+Limited+Liability+Company.pdf','limitedLiablity','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (920000000003,null,'proof_of_principal_place_of_business_address_company','locationCertificate','en_US','Proof of Principal Place of Business Address - company','主要营业地点证明-公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/limitedLiablity/Proof+of+Principal+Place+of+Business+Address+-+Limited+Liability+Company.pdf','limitedLiablity','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (920000000004,null,'authorizedPerson_identity','identityCertificate','en_US','Please provide authorizedPerson identity and birth certificate','请提供授权人的身份和生日证明','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/limitedLiablity/Proof+of+Identity+and+Date+of+Birth+for+Authorized+Person.pdf','limitedLiablity','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (920000000005,null,'individualShareholder_identity','identityCertificate','en_US','Please provide %s identity and birth certificate','请提供个人股东的身份和生日证明','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/limitedLiablity/Proof+of+Identity+and+Date+of+Birth+for+Individual+Shareholder.pdf','limitedLiablity','2');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (920000000006,null,'authorizedPerson_address','addressCertificate','en_US','Address proof of authorizedPerson','授权人的住址证明','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/limitedLiablity/Proof+of+Address+for+Authorized+Person.pdf','limitedLiablity','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (920000000007,null,'individualShareholder_address','addressCertificate','en_US','Address proof of %s','个人股东的住址证明','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/limitedLiablity/Proof+of+Address+for+Individual+Shareholder.pdf','limitedLiablity','2');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (920000000008,null,'proof_of_existence_legalPersonShareholderCompany','existenceProof','en_US','Proof of Existence - %s','存在证明-法人股东公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/limitedLiablity/Proof+of+Existence+-+Corporate+Shareholder+Company.pdf','limitedLiablity','3');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (920000000009,null,'proof_of_principal_place_of_business_address_legalPersonShareholderCompany','locationCertificate','en_US','Proof of Principal Place of Business Address - %s','主要营业地点证明-法人股东公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/limitedLiablity/Proof+of+Principal+Place+of+Business+Address+-+Corporate+Shareholder+Company.pdf','limitedLiablity','3');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (920000000010,null,'proof_of_authorization_to_open_account_company','authorizerCertificate','en_US','Proof of Authorization to Open Account - company','开户授权证明-公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/limitedLiablity/Proof+of+Authorization+to+Open+Account+-+Limited+Liability+Company.pdf','limitedLiablity','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (920000001001,null,'proof_of_ability_to_trade_on_margin','marginCertificate','en_US','Proof of Ability to Trade on Margin - company','保证金交易能力证明-公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/partnerShip/Proof+of+Ability+to+Trade+on+Margin+-+Partnership.pdf','partnerShip','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (920000001002,null,'proof_of_existence_company','existenceProof','en_US','Proof of Existence - company','存在证明-公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/partnerShip/Proof+of+Existence+-+Partnership.pdf','partnerShip','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (920000001003,null,'proof_of_principal_place_of_business_address_company','locationCertificate','en_US','Proof of Principal Place of Business Address - company','主要营业地点证明-公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/partnerShip/Proof+of+Principal+Place+of+Business+Address+-+Partnership.pdf','partnerShip','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (920000001004,null,'authorizedPerson_identity','identityCertificate','en_US','Please provide authorizedPerson identity and birth certificate','请提供授权人的身份和生日证明','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/partnerShip/Proof+of+Identity+and+Date+of+Birth+for+Authorized+Person.pdf','partnerShip','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (920000001005,null,'individualShareholder_identity','identityCertificate','en_US','Please provide %s identity and birth certificate','请提供个人股东的身份和生日证明','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/partnerShip/Proof+of+Identity+and+Date+of+Birth+for+Individual+Shareholder.pdf','partnerShip','2');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (920000001006,null,'authorizedPerson_address','addressCertificate','en_US','Address proof of authorizedPerson','授权人的住址证明','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/partnerShip/Proof+of+Address+for+Authorized+Person.pdf','partnerShip','1');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (920000001007,null,'individualShareholder_address','addressCertificate','en_US','Address proof of %s','个人股东的住址证明','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/partnerShip/Proof+of+Address+for+Individual+Shareholder.pdf','partnerShip','2');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (920000001008,null,'proof_of_existence_legalPersonShareholderCompany','existenceProof','en_US','Proof of Existence - %s','存在证明-法人股东公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/partnerShip/Proof+of+Existence+-+Corporate+Shareholder+Company.pdf','partnerShip','3');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (920000001009,null,'proof_of_principal_place_of_business_address_legalPersonShareholderCompany','locationCertificate','en_US','Proof of Principal Place of Business Address - %s','主要营业地点证明-法人股东公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/partnerShip/Proof+of+Principal+Place+of+Business+Address+-+Corporate+Shareholder+Company.pdf','partnerShip','3');
Insert into upload_file_type (ID,co_personal_id,type_key,type,lang,content,dest,more_info_pdf,account_type,kind) values (920000001010,null,'proof_of_authorization_to_open_account_company','authorizerCertificate','en_US','Proof of Authorization to Open Account - company','开户授权证明-公司','https://gams-test.s3-ap-northeast-1.amazonaws.com/File/partnerShip/Proof+of+Authorization+to+Open+Account+-+Partnership.pdf','partnerShip','1');

	
commit;
