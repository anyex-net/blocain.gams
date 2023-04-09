declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Stock_Exchange');
    if num > 0 then
        execute immediate 'drop table Stock_Exchange';
    end if;
end;
/
CREATE TABLE Stock_Exchange
(  id                       NUMBER(20)   NOT NULL,  
   exchange_code            VARCHAR2(32) NOT NULL,
   exchange_name            VARCHAR2(64) NOT NULL,
   region                   VARCHAR2(32) NOT NULL,
   continent                VARCHAR2(32) NOT NULL,
   base_currency            VARCHAR2(16) NOT NULL,
   trade_day_type           VARCHAR2(16),
   is_opentrade             NUMBER(1) DEFAULT 1 NOT NULL,
   create_by                NUMBER(20)   NOT NULL,
   create_date 		        NUMBER(13)   NOT NULL,
   update_by                NUMBER(20),
   update_date              NUMBER(13),
   CONSTRAINT Stock_Exchange_PK PRIMARY KEY (id),
   CONSTRAINT Stock_Exchang_Unique UNIQUE (exchange_code)
 );
COMMENT ON COLUMN Stock_Exchange.id IS '主键';
COMMENT ON COLUMN Stock_Exchange.exchange_code IS '交易所国际编码';
COMMENT ON COLUMN Stock_Exchange.exchange_name IS '交易所名称';
COMMENT ON COLUMN Stock_Exchange.region IS '所在国家地区(国家地区)';
COMMENT ON COLUMN Stock_Exchange.continent IS '所在世界大洲(数据字典)';
COMMENT ON COLUMN Stock_Exchange.base_currency IS '基础币种(数据字典)';
COMMENT ON COLUMN Stock_Exchange.trade_day_type IS '交易日类型(数据字典)';
COMMENT ON COLUMN Stock_Exchange.is_opentrade IS '是否开放交易 0否、1是';
COMMENT ON COLUMN Stock_Exchange.create_by IS '创建人';
COMMENT ON COLUMN Stock_Exchange.create_date IS '创建时间';
COMMENT ON COLUMN Stock_Exchange.update_by IS '修改人';
COMMENT ON COLUMN Stock_Exchange.update_date IS '修改时间';
COMMENT ON TABLE Stock_Exchange IS '证券交易所信息';

declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Stock_Info');
    if num > 0 then
        execute immediate 'drop table Stock_Info';
    end if;
end;
/
CREATE TABLE Stock_Info
(  id                       NUMBER(20)    NOT NULL,  
   stock_code               VARCHAR2(32)  NOT NULL,
   stock_symbol             VARCHAR2(32)  NOT NULL,
   stock_isin               VARCHAR2(64)  NOT NULL,
   stock_spell_abbr         VARCHAR2(64),
   stock_name_en            VARCHAR2(64)  NOT NULL,
   stock_name_cn            VARCHAR2(128) NOT NULL,
   stock_type               VARCHAR2(16)  NOT NULL,
   asset_type               VARCHAR2(16)  NOT NULL,
   exchange_id              NUMBER(20)    NOT NULL,  
   base_currency            VARCHAR2(16)  NOT NULL,
   company_code             VARCHAR2(32),
   company_name             VARCHAR2(64),
   offer_date               NUMBER(13),
   list_date                NUMBER(13),
   cancel_date              NUMBER(13),
   last_price               NUMBER(20,8) DEFAULT 0 NOT NULL,
   is_opentrade             NUMBER(1) DEFAULT 1 NOT NULL,
   ib_symbol                VARCHAR2(64),
   create_by                NUMBER(20)    NOT NULL,
   create_date 		        NUMBER(13)    NOT NULL,
   update_by                NUMBER(20),
   update_date              NUMBER(13),
   CONSTRAINT Stock_Info_PK PRIMARY KEY (id),
   CONSTRAINT Stock_Info_Unique UNIQUE (stock_code)
 );
COMMENT ON COLUMN Stock_Info.id IS '主键';
COMMENT ON COLUMN Stock_Info.stock_code IS '证券编码';
COMMENT ON COLUMN Stock_Info.stock_symbol IS '证券代码';
COMMENT ON COLUMN Stock_Info.stock_isin IS '证券ISIN码';
COMMENT ON COLUMN Stock_Info.stock_spell_abbr IS '证券拼音缩写';
COMMENT ON COLUMN Stock_Info.stock_name_en IS '证券英文名称';
COMMENT ON COLUMN Stock_Info.stock_name_cn IS '证券中文名称';
COMMENT ON COLUMN Stock_Info.stock_type IS '证券类型(数据字典)';
COMMENT ON COLUMN Stock_Info.asset_type IS '资产类型(数据字典)';
COMMENT ON COLUMN Stock_Info.exchange_id IS '上市场所(证券交易所)';
COMMENT ON COLUMN Stock_Info.base_currency IS '基础币种(数据字典)';
COMMENT ON COLUMN Stock_Info.company_code IS '发行人Code';
COMMENT ON COLUMN Stock_Info.company_name IS '发行人名称';
COMMENT ON COLUMN Stock_Info.offer_date IS '发行日期';
COMMENT ON COLUMN Stock_Info.list_date IS '上市日期';
COMMENT ON COLUMN Stock_Info.cancel_date IS '退市日期';
COMMENT ON COLUMN Stock_Info.last_price IS '最新价格';
COMMENT ON COLUMN Stock_Info.is_opentrade IS '是否开放交易 0否、1是';
COMMENT ON COLUMN Stock_Info.ib_symbol IS 'IB平台代码';
COMMENT ON COLUMN Stock_Info.create_by IS '创建人';
COMMENT ON COLUMN Stock_Info.create_date IS '创建时间';
COMMENT ON COLUMN Stock_Info.update_by IS '修改人';
COMMENT ON COLUMN Stock_Info.update_date IS '修改时间';
COMMENT ON TABLE Stock_Info IS '证券信息';

declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Stock_Fund');
    if num > 0 then
        execute immediate 'drop table Stock_Fund';
    end if;
end;
/
CREATE TABLE Stock_Fund
(  id                       NUMBER(20)    NOT NULL,  
   stockinfo_id             NUMBER(20)    NOT NULL,
   manager_org_code         VARCHAR2(32),
   manager_org_name         VARCHAR2(32),
   trust_org_code           VARCHAR2(32),
   trust_org_name           VARCHAR2(12),
   fund_size                NUMBER(20,6),
   establishment_date       NUMBER(13),
   list_date                NUMBER(13),
   expire_date              NUMBER(13),
   fund_operate             VARCHAR2(16)  NOT NULL,
   fund_type                VARCHAR2(16)  NOT NULL,
   fund_invest_area         VARCHAR2(16)  NOT NULL, 
   fund_invest_style        VARCHAR2(16)  NOT NULL,
   share_transfer           VARCHAR2(16),
   risk_level               VARCHAR2(16)  NOT NULL,
   dividend_policy          VARCHAR2(16)  NOT NULL,
   create_by                NUMBER(20)    NOT NULL,
   create_date 		        NUMBER(13)    NOT NULL,
   update_by                NUMBER(20),
   update_date              NUMBER(13),
   CONSTRAINT Stock_Fund_PK PRIMARY KEY (id),
   CONSTRAINT Stock_Fund_Unique UNIQUE (stockinfo_id)
 );
COMMENT ON COLUMN Stock_Fund.id IS '主键';
COMMENT ON COLUMN Stock_Fund.stockinfo_id IS '证券信息ID';
COMMENT ON COLUMN Stock_Fund.manager_org_code IS '基金管理机构';
COMMENT ON COLUMN Stock_Fund.manager_org_name IS '基金管理机构名称';
COMMENT ON COLUMN Stock_Fund.trust_org_code IS '基金托管机构';
COMMENT ON COLUMN Stock_Fund.trust_org_name IS '基金托管机构名称';
COMMENT ON COLUMN Stock_Fund.fund_size IS '基金设立规模';
COMMENT ON COLUMN Stock_Fund.establishment_date IS '设立日期';
COMMENT ON COLUMN Stock_Fund.list_date IS '上市日期';
COMMENT ON COLUMN Stock_Fund.expire_date IS '到期日期';
COMMENT ON COLUMN Stock_Fund.fund_operate IS '基金运作方式(数据字典)';
COMMENT ON COLUMN Stock_Fund.fund_type IS '基金类型(数据字典)';
COMMENT ON COLUMN Stock_Fund.fund_invest_area IS '基金投资区域(数据字典)';
COMMENT ON COLUMN Stock_Fund.fund_invest_style IS '基金投资风格(数据字典)';
COMMENT ON COLUMN Stock_Fund.share_transfer IS '份额结转方式(数据字典)';
COMMENT ON COLUMN Stock_Fund.risk_level IS '风险程度(数据字典)';
COMMENT ON COLUMN Stock_Fund.dividend_policy IS '分红策略(数据字典)';
COMMENT ON COLUMN Stock_Fund.create_by IS '创建人';
COMMENT ON COLUMN Stock_Fund.create_date IS '创建时间';
COMMENT ON COLUMN Stock_Fund.update_by IS '修改人';
COMMENT ON COLUMN Stock_Fund.update_date IS '修改时间';
COMMENT ON TABLE Stock_Fund IS '证券基金信息';

declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Stock_Warrant');
    if num > 0 then
        execute immediate 'drop table Stock_Warrant';
    end if;
end;
/
CREATE TABLE Stock_Warrant
(  id                       NUMBER(20)    NOT NULL,  
   stockinfo_id             NUMBER(20)    NOT NULL,
   warrant_class            VARCHAR2(16)  NOT NULL,
   warrant_type             VARCHAR2(16)  NOT NULL,
   exercise_price           NUMBER(12,6),
   create_by                NUMBER(20)    NOT NULL,
   create_date 		        NUMBER(13)    NOT NULL,
   update_by                NUMBER(20),
   update_date              NUMBER(13),
   CONSTRAINT Stock_Warrant_PK PRIMARY KEY (id),
   CONSTRAINT Stock_Warrant_Unique UNIQUE (stockinfo_id)
 );
COMMENT ON COLUMN Stock_Warrant.id IS '主键';
COMMENT ON COLUMN Stock_Warrant.stockinfo_id IS '证券信息ID';
COMMENT ON COLUMN Stock_Warrant.warrant_class IS '权证类别(warrant普通权证、cbbc牛熊证)';
COMMENT ON COLUMN Stock_Warrant.warrant_type IS '权证类型(call认购权证、put认沽权证)';
COMMENT ON COLUMN Stock_Warrant.exercise_price IS '行权价';
COMMENT ON COLUMN Stock_Warrant.create_by IS '创建人';
COMMENT ON COLUMN Stock_Warrant.create_date IS '创建时间';
COMMENT ON COLUMN Stock_Warrant.update_by IS '修改人';
COMMENT ON COLUMN Stock_Warrant.update_date IS '修改时间';
COMMENT ON TABLE Stock_Warrant IS '证券权证信息';

declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Stock_Wmp');
    if num > 0 then
        execute immediate 'drop table Stock_Wmp';
    end if;
end;
/
CREATE TABLE Stock_Wmp
(  id                       NUMBER(20)    NOT NULL,  
   stockinfo_id             NUMBER(20)    NOT NULL,
   fund_type                VARCHAR2(16)  NOT NULL,
   expected_annual_return   NUMBER(20,8),
   product_life             NUMBER(8),
   risk_level               VARCHAR2(32),
   start_sale_amt           NUMBER(20,8),
   listing_date             NUMBER(13),
   expire_date              NUMBER(13),
   is_recommend             NUMBER(1),
   create_by                NUMBER(20)    NOT NULL,
   create_date 		        NUMBER(13)    NOT NULL,
   update_by                NUMBER(20),
   update_date              NUMBER(13),
   CONSTRAINT Stock_Wmp_PK PRIMARY KEY (id),
   CONSTRAINT Stock_Wmp_Unique UNIQUE (stockinfo_id)
 );
COMMENT ON COLUMN Stock_Wmp.id IS '主键';
COMMENT ON COLUMN Stock_Wmp.stockinfo_id IS '证券信息ID';
COMMENT ON COLUMN Stock_Wmp.fund_type IS '基金类型(数据字典)';
COMMENT ON COLUMN Stock_Wmp.expected_annual_return IS '预期年化收益率';
COMMENT ON COLUMN Stock_Wmp.product_life IS '产品期限';
COMMENT ON COLUMN Stock_Wmp.risk_level IS '风险程度等级(数据字典)';
COMMENT ON COLUMN Stock_Wmp.start_sale_amt IS '起售金额';
COMMENT ON COLUMN Stock_Wmp.listing_date IS '上市日期';
COMMENT ON COLUMN Stock_Wmp.expire_date IS '到期日期';
COMMENT ON COLUMN Stock_Wmp.is_recommend IS '是否推荐(0不推荐、1推荐)';
COMMENT ON COLUMN Stock_Wmp.create_by IS '创建人';
COMMENT ON COLUMN Stock_Wmp.create_date IS '创建时间';
COMMENT ON COLUMN Stock_Wmp.update_by IS '修改人';
COMMENT ON COLUMN Stock_Wmp.update_date IS '修改时间';
COMMENT ON TABLE Stock_Wmp IS '证券理财产品信息';

/*
declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Stock_Rate');
    if num > 0 then
        execute immediate 'drop table Stock_Rate';
    end if;
end;
/
CREATE TABLE Stock_Rate
(  id                       NUMBER(20)    NOT NULL,  
   rate_type                VARCHAR2(32)  NOT NULL,
   stock_code               VARCHAR2(32)  NOT NULL,
   create_by                NUMBER(20)    NOT NULL,
   create_date 		        NUMBER(13)    NOT NULL,
   update_by                NUMBER(20),
   update_date              NUMBER(13),
   CONSTRAINT Stock_Rate_PK PRIMARY KEY (id)
 );
COMMENT ON COLUMN Stock_Rate.id IS '主键';  
COMMENT ON COLUMN Stock_Rate.rate_type IS '费用类型(数据字典)';
COMMENT ON COLUMN Stock_Rate.stock_code IS '证券编码';
COMMENT ON COLUMN Stock_Rate.create_by IS '创建人';
COMMENT ON COLUMN Stock_Rate.create_date IS '创建时间';
COMMENT ON COLUMN Stock_Rate.update_by IS '修改人';
COMMENT ON COLUMN Stock_Rate.update_date IS '修改时间';
COMMENT ON TABLE Stock_Rate IS '证券费率信息';
*/