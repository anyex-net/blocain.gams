/*
declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('News_StockConfig');
    if num > 0 then
        execute immediate 'drop table News_StockConfig';
    end if;
end;
/
CREATE TABLE News_StockConfig
(  id                               NUMBER(20)    NOT NULL,  
   stockinfo_id 			        NUMBER(20)    NOT NULL,
   company_profile_api   	        VARCHAR2(256) NOT NULL,
   financial_analysis_api           VARCHAR2(256) NOT NULL,
   stock_holders_api                VARCHAR2(256) NOT NULL,
   dividend_distribution_api        VARCHAR2(256) NOT NULL,
   main_business_composition_api    VARCHAR2(256) NOT NULL,
   executive_research_api           VARCHAR2(256) NOT NULL,
   balance_sheet_api                VARCHAR2(256) NOT NULL,
   consolidated_profit_loss_api     VARCHAR2(256) NOT NULL,
   cash_flows_statement_api         VARCHAR2(256) NOT NULL,
   director_stockholder_equity_api  VARCHAR2(256) NOT NULL,
   remark      			            VARCHAR2(128),
   create_by                        NUMBER(20)    NOT NULL,
   create_date 		                NUMBER(13)    NOT NULL,
   update_by                        NUMBER(20),
   update_date                      NUMBER(13),
   CONSTRAINT News_StockConfig_PK PRIMARY KEY (id),
   CONSTRAINT News_StockConfig_Unique UNIQUE (stockinfo_id)
 );
COMMENT ON COLUMN News_StockConfig.id IS '主键';  
COMMENT ON COLUMN News_StockConfig.stockinfo_id IS '证券信息ID';
COMMENT ON COLUMN News_StockConfig.company_profile_api IS '公司概况';
COMMENT ON COLUMN News_StockConfig.financial_analysis_api IS '财务分析';
COMMENT ON COLUMN News_StockConfig.stock_holders_api IS '股本股东';
COMMENT ON COLUMN News_StockConfig.dividend_distribution_api IS '分红派息';
COMMENT ON COLUMN News_StockConfig.main_business_composition_api IS '主营构成';
COMMENT ON COLUMN News_StockConfig.executive_research_api IS '高管研究';
COMMENT ON COLUMN News_StockConfig.balance_sheet_api IS '资产负债表';
COMMENT ON COLUMN News_StockConfig.consolidated_profit_loss_api IS '综合损益表';
COMMENT ON COLUMN News_StockConfig.cash_flows_statement_api IS '现金流量表';
COMMENT ON COLUMN News_StockConfig.director_stockholder_equity_api IS '董事及股东权益';
COMMENT ON COLUMN News_StockConfig.remark IS '备注';
COMMENT ON COLUMN News_StockConfig.create_by IS '创建人';
COMMENT ON COLUMN News_StockConfig.create_date IS '创建时间';
COMMENT ON COLUMN News_StockConfig.update_by IS '修改人';
COMMENT ON COLUMN News_StockConfig.update_date IS '修改时间';
COMMENT ON TABLE News_StockConfig IS '股票资讯配置表';
*/

--外汇资讯配置表
--News_ForeignExConfig

--期货资讯配置表
--News_FuturesConfig

--基金资讯配置表
--News_FundConfig

--债券资讯配置表
--News_BondConfig

--期权资讯配置表
--News_OptionConfig

--黄金资讯配置表
--News_GoldConfig
