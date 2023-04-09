declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('IB_Account_Family');
    if num > 0 then
        execute immediate 'drop table IB_Account_Family';
    end if;
end;
/
CREATE TABLE IB_Account_Family
(
  id                            NUMBER(20)             NOT NULL,
  account_id                    NUMBER(20),
  ib_account_code               VARCHAR2(64)           NOT NULL,
  ib_family_code                VARCHAR2(64),
  base_currency                 VARCHAR2(32),
  remark                        VARCHAR2(64),
  create_date                   NUMBER(13)             NOT NULL,
  update_date                   NUMBER(13),
  CONSTRAINT IB_Account_Family_PK PRIMARY KEY (id),
  CONSTRAINT IB_Account_Family_Unique1 UNIQUE (ib_account_code),
  CONSTRAINT IB_Account_Family_Unique2 UNIQUE (account_id)
);
COMMENT ON COLUMN IB_Account_Family.id IS '主键';
COMMENT ON COLUMN IB_Account_Family.account_id IS '账户ID';
COMMENT ON COLUMN IB_Account_Family.ib_account_code IS 'IB对应AccountCode';
COMMENT ON COLUMN IB_Account_Family.ib_family_code IS 'IB对应FamilyCode';
COMMENT ON COLUMN IB_Account_Family.base_currency IS '本位币';
COMMENT ON COLUMN IB_Account_Family.remark IS '备注';
COMMENT ON COLUMN IB_Account_Family.create_date IS '创建时间';
COMMENT ON COLUMN IB_Account_Family.update_date IS '修改时间';
COMMENT ON TABLE IB_Account_Family IS 'IB账户家族表';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('IB_Account_Balance_Margin');
  if num > 0 then
    execute immediate 'drop table IB_Account_Balance_Margin';
  end if;
end;
/
CREATE TABLE IB_Account_Balance_Margin
(
  id                                NUMBER(20)             NOT NULL,
  account_id                        NUMBER(20)             NOT NULL,
  Account_Code                      VARCHAR2(32)           NOT NULL,
  Account_Type                      VARCHAR2(32),
  Account_Ready                     VARCHAR2(32),
  Cushion                           NUMBER(20,8),
  Leverage_S                        NUMBER(20,8),
  Leverage_C                        NUMBER(20,8),
  Look_Ahead_Next_Change            NUMBER(20,8),
  NLV_And_Margin_In_Review          VARCHAR2(32),
  Segment_Title_S                   VARCHAR2(32),
  Trading_Type_S                    VARCHAR2(32),
  Segment_Title_C                   VARCHAR2(32),
  Trading_Type_C                    VARCHAR2(32),

  Day_Trades_Remaining              NUMBER(20,8),
  Day_Trades_Remaining_T1           NUMBER(20,8),
  Day_Trades_Remaining_T2           NUMBER(20,8),
  Day_Trades_Remaining_T3           NUMBER(20,8),
  Day_Trades_Remaining_T4           NUMBER(20,8),

  Currency                          VARCHAR2(32),
  Accrued_Cash                      NUMBER(20,8),
  Accrued_Cash_S                    NUMBER(20,8),
  Accrued_Cash_C                    NUMBER(20,8),
  Accrued_Dividend                  NUMBER(20,8),
  Accrued_Dividend_S                NUMBER(20,8),
  Accrued_Dividend_C                NUMBER(20,8),
  Available_Funds                   NUMBER(20,8),
  Available_Funds_S                 NUMBER(20,8),
  Available_Funds_C                 NUMBER(20,8),
  Billable                          NUMBER(20,8),
  Billable_S                        NUMBER(20,8),
  Billable_C                        NUMBER(20,8),
  Buying_Power                      NUMBER(20,8),
  Equity_With_Loan_Value            NUMBER(20,8),
  Equity_With_Loan_Value_S          NUMBER(20,8),
  Equity_With_Loan_Value_C          NUMBER(20,8),
  Excess_Liquidity                  NUMBER(20,8),
  Excess_Liquidity_S                NUMBER(20,8),
  Excess_Liquidity_C                NUMBER(20,8),
  Full_Available_Funds              NUMBER(20,8),
  Full_Available_Funds_S            NUMBER(20,8),
  Full_Available_Funds_C            NUMBER(20,8),
  Full_Excess_Liquidity             NUMBER(20,8),
  Full_Excess_Liquidity_S           NUMBER(20,8),
  Full_Excess_Liquidity_C           NUMBER(20,8),
  Full_Init_Margin_Req              NUMBER(20,8),
  Full_Init_Margin_Req_S            NUMBER(20,8),
  Full_Init_Margin_Req_C            NUMBER(20,8),
  Full_Maint_Margin_Req             NUMBER(20,8),
  Full_Maint_Margin_Req_S           NUMBER(20,8),
  Full_Maint_Margin_Req_C           NUMBER(20,8),
  Gross_Position_Value              NUMBER(20,8),
  Gross_Position_Value_S            NUMBER(20,8),
  Gross_Position_Value_C            NUMBER(20,8),
  Guarantee                         NUMBER(20,8),
  Guarantee_S                       NUMBER(20,8),
  Guarantee_C                       NUMBER(20,8),
  Indian_Stock_Haircut              NUMBER(20,8),
  Indian_Stock_Haircut_S            NUMBER(20,8),
  Indian_Stock_Haircut_C            NUMBER(20,8),
  Init_Margin_Req                   NUMBER(20,8),
  Init_Margin_Req_S                 NUMBER(20,8),
  Init_Margin_Req_C                 NUMBER(20,8),
  Look_Ahead_Available_Funds        NUMBER(20,8),
  Look_Ahead_Available_Funds_S      NUMBER(20,8),
  Look_Ahead_Available_Funds_C      NUMBER(20,8),
  Look_Ahead_Excess_Liquidity       NUMBER(20,8),
  Look_Ahead_Excess_Liquidity_S     NUMBER(20,8),
  Look_Ahead_Excess_Liquidity_C     NUMBER(20,8),
  Look_Ahead_Init_Margin_Req		    NUMBER(20,8),
  Look_Ahead_Init_Margin_Req_S      NUMBER(20,8),
  Look_Ahead_Init_Margin_Req_C      NUMBER(20,8),
  Look_Ahead_Maint_Margin_Req       NUMBER(20,8),
  Look_Ahead_Maint_Margin_Req_S     NUMBER(20,8),
  Look_Ahead_Maint_Margin_Req_C     NUMBER(20,8),
  Maint_Margin_Req                  NUMBER(20,8),
  Maint_Margin_Req_S                NUMBER(20,8),
  Maint_Margin_Req_C                NUMBER(20,8),
  Net_Liquidation                   NUMBER(20,8),
  Net_Liquidation_S                 NUMBER(20,8),
  Net_Liquidation_C                 NUMBER(20,8),
  Net_Liquidation_Uncertainty       NUMBER(20,8),
  PAShares_Value                    NUMBER(20,8),
  PAShares_Value_S                  NUMBER(20,8),
  PAShares_Value_C                  NUMBER(20,8),
  Post_Expiration_Excess            NUMBER(20,8),
  Post_Expiration_Excess_S          NUMBER(20,8),
  Post_Expiration_Excess_C          NUMBER(20,8),
  Post_Expiration_Margin            NUMBER(20,8),
  Post_Expiration_Margin_S          NUMBER(20,8),
  Post_Expiration_Margin_C          NUMBER(20,8),

  Reg_T_Equity                      NUMBER(20,8),
  Reg_T_Equity_S                    NUMBER(20,8),
  Reg_T_Equity_C                    NUMBER(20,8),
  Reg_T_Margin                      NUMBER(20,8),
  Reg_T_Margin_S                    NUMBER(20,8),
  Reg_T_Margin_C                    NUMBER(20,8),
  SMA                               NUMBER(20,8),
  SMA_S                             NUMBER(20,8),
  SMA_C                             NUMBER(20,8),
  
  Total_CashValue                   NUMBER(20,8),
  Total_CashValue_S                 NUMBER(20,8),
  Total_CashValue_C                 NUMBER(20,8),
  Debit_Card_Pending_Charges        NUMBER(20,8),
  Debit_Card_Pending_Charges_S      NUMBER(20,8),
  Debit_Card_Pending_Charges_C      NUMBER(20,8),
  remark                            VARCHAR2(64),
  create_date                       NUMBER(13)             NOT NULL,
  update_date                       NUMBER(13),
  CONSTRAINT IB_Account_Balance_Margin_PK PRIMARY KEY (id),
  CONSTRAINT IB_Account_Balance_Margin_Un1 UNIQUE (account_id),
  CONSTRAINT IB_Account_Balance_Margin_Un2 UNIQUE (Account_Code)
);
COMMENT ON COLUMN IB_Account_Balance_Margin.id IS '主键';
COMMENT ON COLUMN IB_Account_Balance_Margin.account_id IS '账户ID';
COMMENT ON COLUMN IB_Account_Balance_Margin.Account_Code IS 'IB对应AccountCode';
COMMENT ON COLUMN IB_Account_Balance_Margin.Account_Type IS '账户类型';
COMMENT ON COLUMN IB_Account_Balance_Margin.Account_Ready IS '账户就绪';
COMMENT ON COLUMN IB_Account_Balance_Margin.Cushion IS 'Cushion';
COMMENT ON COLUMN IB_Account_Balance_Margin.Leverage_S IS '杠杆';
COMMENT ON COLUMN IB_Account_Balance_Margin.Leverage_C IS '杠杆';
COMMENT ON COLUMN IB_Account_Balance_Margin.Look_Ahead_Next_Change IS '前瞻下一变化';
COMMENT ON COLUMN IB_Account_Balance_Margin.NLV_And_Margin_In_Review IS '净清算值和保证金审查';
COMMENT ON COLUMN IB_Account_Balance_Margin.Segment_Title_S IS '分部标题';
COMMENT ON COLUMN IB_Account_Balance_Margin.Segment_Title_C IS '分部标题';
COMMENT ON COLUMN IB_Account_Balance_Margin.Trading_Type_S IS '交易类型';
COMMENT ON COLUMN IB_Account_Balance_Margin.Trading_Type_C IS '交易类型';

COMMENT ON COLUMN IB_Account_Balance_Margin.Day_Trades_Remaining IS '剩余日内交易数T';
COMMENT ON COLUMN IB_Account_Balance_Margin.Day_Trades_Remaining_T1 IS '剩余日内交易数T+1';
COMMENT ON COLUMN IB_Account_Balance_Margin.Day_Trades_Remaining_T2 IS '剩余日内交易数T+2';
COMMENT ON COLUMN IB_Account_Balance_Margin.Day_Trades_Remaining_T3 IS '剩余日内交易数T+3';
COMMENT ON COLUMN IB_Account_Balance_Margin.Day_Trades_Remaining_T4 IS '剩余日内交易数T+4';

COMMENT ON COLUMN IB_Account_Balance_Margin.Currency IS '币种';
COMMENT ON COLUMN IB_Account_Balance_Margin.Accrued_Cash IS '应计现金';
COMMENT ON COLUMN IB_Account_Balance_Margin.Accrued_Cash_S IS '应计现金';
COMMENT ON COLUMN IB_Account_Balance_Margin.Accrued_Cash_C IS '应计现金';
COMMENT ON COLUMN IB_Account_Balance_Margin.Accrued_Dividend IS '应计股息';
COMMENT ON COLUMN IB_Account_Balance_Margin.Accrued_Dividend_S IS '应计股息';
COMMENT ON COLUMN IB_Account_Balance_Margin.Accrued_Dividend_C IS '应计股息';
COMMENT ON COLUMN IB_Account_Balance_Margin.Available_Funds IS '可用资金';
COMMENT ON COLUMN IB_Account_Balance_Margin.Available_Funds_S IS '可用资金';
COMMENT ON COLUMN IB_Account_Balance_Margin.Available_Funds_C IS '可用资金';
COMMENT ON COLUMN IB_Account_Balance_Margin.Billable IS 'Billable';
COMMENT ON COLUMN IB_Account_Balance_Margin.Billable_S IS 'Billable_S';
COMMENT ON COLUMN IB_Account_Balance_Margin.Billable_C IS 'Billable_C';
COMMENT ON COLUMN IB_Account_Balance_Margin.Buying_Power IS '购买力';
COMMENT ON COLUMN IB_Account_Balance_Margin.Equity_With_Loan_Value IS '含贷款价值的资产';
COMMENT ON COLUMN IB_Account_Balance_Margin.Equity_With_Loan_Value_S IS '含贷款价值的资产';
COMMENT ON COLUMN IB_Account_Balance_Margin.Equity_With_Loan_Value_C IS '含贷款价值的资产';
COMMENT ON COLUMN IB_Account_Balance_Margin.Excess_Liquidity IS '剩余流动性';
COMMENT ON COLUMN IB_Account_Balance_Margin.Excess_Liquidity_S IS '剩余流动性';
COMMENT ON COLUMN IB_Account_Balance_Margin.Excess_Liquidity_C IS '剩余流动性';
COMMENT ON COLUMN IB_Account_Balance_Margin.Full_Available_Funds IS '全部可用资金';
COMMENT ON COLUMN IB_Account_Balance_Margin.Full_Available_Funds_S IS '全部可用资金';
COMMENT ON COLUMN IB_Account_Balance_Margin.Full_Available_Funds_C IS '全部可用资金';
COMMENT ON COLUMN IB_Account_Balance_Margin.Full_Excess_Liquidity IS '全部过剩流动性';
COMMENT ON COLUMN IB_Account_Balance_Margin.Full_Excess_Liquidity_S IS '全部过剩流动性';
COMMENT ON COLUMN IB_Account_Balance_Margin.Full_Excess_Liquidity_C IS '全部过剩流动性';
COMMENT ON COLUMN IB_Account_Balance_Margin.Full_Init_Margin_Req IS '全部初始保证金要求';
COMMENT ON COLUMN IB_Account_Balance_Margin.Full_Init_Margin_Req_S IS '全部初始保证金要求';
COMMENT ON COLUMN IB_Account_Balance_Margin.Full_Init_Margin_Req_C IS '全部初始保证金要求';
COMMENT ON COLUMN IB_Account_Balance_Margin.Full_Maint_Margin_Req IS '全部维持保证金要求';
COMMENT ON COLUMN IB_Account_Balance_Margin.Full_Maint_Margin_Req_S IS '全部维持保证金要求';
COMMENT ON COLUMN IB_Account_Balance_Margin.Full_Maint_Margin_Req_C IS '全部维持保证金要求';
COMMENT ON COLUMN IB_Account_Balance_Margin.Gross_Position_Value IS '总头寸价值';
COMMENT ON COLUMN IB_Account_Balance_Margin.Gross_Position_Value_S IS '总头寸价值';
COMMENT ON COLUMN IB_Account_Balance_Margin.Gross_Position_Value_C IS '总头寸价值';
COMMENT ON COLUMN IB_Account_Balance_Margin.Guarantee IS '担保';
COMMENT ON COLUMN IB_Account_Balance_Margin.Guarantee_S IS '担保';
COMMENT ON COLUMN IB_Account_Balance_Margin.Guarantee_C IS '担保';
COMMENT ON COLUMN IB_Account_Balance_Margin.Indian_Stock_Haircut IS '印度股票';
COMMENT ON COLUMN IB_Account_Balance_Margin.Indian_Stock_Haircut_S IS '印度股票';
COMMENT ON COLUMN IB_Account_Balance_Margin.Indian_Stock_Haircut_C IS '印度股票';
COMMENT ON COLUMN IB_Account_Balance_Margin.Init_Margin_Req IS '初始保证金要求';
COMMENT ON COLUMN IB_Account_Balance_Margin.Init_Margin_Req_S IS '初始保证金要求';
COMMENT ON COLUMN IB_Account_Balance_Margin.Init_Margin_Req_C IS '初始保证金要求';
COMMENT ON COLUMN IB_Account_Balance_Margin.Look_Ahead_Available_Funds IS '前瞻可用资金';
COMMENT ON COLUMN IB_Account_Balance_Margin.Look_Ahead_Available_Funds_S IS '前瞻可用资金';
COMMENT ON COLUMN IB_Account_Balance_Margin.Look_Ahead_Available_Funds_C IS '前瞻可用资金';
COMMENT ON COLUMN IB_Account_Balance_Margin.Look_Ahead_Excess_Liquidity IS '前瞻剩余流动性';
COMMENT ON COLUMN IB_Account_Balance_Margin.Look_Ahead_Excess_Liquidity_S IS '前瞻剩余流动性';
COMMENT ON COLUMN IB_Account_Balance_Margin.Look_Ahead_Excess_Liquidity_C IS '前瞻剩余流动性';
COMMENT ON COLUMN IB_Account_Balance_Margin.Look_Ahead_Init_Margin_Req IS '前瞻初始保证金要求';
COMMENT ON COLUMN IB_Account_Balance_Margin.Look_Ahead_Init_Margin_Req_S IS '前瞻初始保证金要求';
COMMENT ON COLUMN IB_Account_Balance_Margin.Look_Ahead_Init_Margin_Req_C IS '前瞻初始保证金要求';
COMMENT ON COLUMN IB_Account_Balance_Margin.Look_Ahead_Maint_Margin_Req IS '前瞻维持保证金要求';
COMMENT ON COLUMN IB_Account_Balance_Margin.Look_Ahead_Maint_Margin_Req_S IS '前瞻维持保证金要求';
COMMENT ON COLUMN IB_Account_Balance_Margin.Look_Ahead_Maint_Margin_Req_C IS '前瞻维持保证金要求';
COMMENT ON COLUMN IB_Account_Balance_Margin.Maint_Margin_Req IS '维持保证金要求';
COMMENT ON COLUMN IB_Account_Balance_Margin.Maint_Margin_Req_S IS '维持保证金要求';
COMMENT ON COLUMN IB_Account_Balance_Margin.Maint_Margin_Req_C IS '维持保证金要求';
COMMENT ON COLUMN IB_Account_Balance_Margin.Net_Liquidation IS '净清算';
COMMENT ON COLUMN IB_Account_Balance_Margin.Net_Liquidation_S IS '净清算';
COMMENT ON COLUMN IB_Account_Balance_Margin.Net_Liquidation_C IS '净清算';
COMMENT ON COLUMN IB_Account_Balance_Margin.Net_Liquidation_Uncertainty IS '净清算不确定';
COMMENT ON COLUMN IB_Account_Balance_Margin.PAShares_Value IS 'PAShares_Value';
COMMENT ON COLUMN IB_Account_Balance_Margin.PAShares_Value_S IS 'PAShares_Value_S';
COMMENT ON COLUMN IB_Account_Balance_Margin.PAShares_Value_C IS 'PAShares_Value_C';
COMMENT ON COLUMN IB_Account_Balance_Margin.Post_Expiration_Excess IS '到期后剩余';
COMMENT ON COLUMN IB_Account_Balance_Margin.Post_Expiration_Excess_S IS '到期后剩余';
COMMENT ON COLUMN IB_Account_Balance_Margin.Post_Expiration_Excess_C IS '到期后剩余';
COMMENT ON COLUMN IB_Account_Balance_Margin.Post_Expiration_Margin IS '到期后保证金';
COMMENT ON COLUMN IB_Account_Balance_Margin.Post_Expiration_Margin_S IS '到期后保证金';
COMMENT ON COLUMN IB_Account_Balance_Margin.Post_Expiration_Margin_C IS '到期后保证金';

COMMENT ON COLUMN IB_Account_Balance_Margin.Reg_T_Equity IS 'RegT权益';
COMMENT ON COLUMN IB_Account_Balance_Margin.Reg_T_Equity_S IS 'RegT权益S';
COMMENT ON COLUMN IB_Account_Balance_Margin.Reg_T_Equity_C IS 'RegT权益C';
COMMENT ON COLUMN IB_Account_Balance_Margin.Reg_T_Margin IS 'RegT保证金';
COMMENT ON COLUMN IB_Account_Balance_Margin.Reg_T_Margin_S IS 'RegT保证金S';
COMMENT ON COLUMN IB_Account_Balance_Margin.Reg_T_Margin_C IS 'RegT保证金C';
COMMENT ON COLUMN IB_Account_Balance_Margin.SMA IS '特别备忘录账户';
COMMENT ON COLUMN IB_Account_Balance_Margin.SMA_S IS '特别备忘录账户S';
COMMENT ON COLUMN IB_Account_Balance_Margin.SMA_C IS '特别备忘录账户C';

COMMENT ON COLUMN IB_Account_Balance_Margin.Total_CashValue IS '总现金价值';
COMMENT ON COLUMN IB_Account_Balance_Margin.Total_CashValue_S IS '总现金价值';
COMMENT ON COLUMN IB_Account_Balance_Margin.Total_CashValue_C IS '总现金价值';
COMMENT ON COLUMN IB_Account_Balance_Margin.Debit_Card_Pending_Charges IS '待支付的借记卡费用';
COMMENT ON COLUMN IB_Account_Balance_Margin.Debit_Card_Pending_Charges_S IS '待支付的借记卡费用';
COMMENT ON COLUMN IB_Account_Balance_Margin.Debit_Card_Pending_Charges_C IS '待支付的借记卡费用';
--COMMENT ON COLUMN IB_Account_Balance_Margin.Total_Debit_Card_Pending_Charges IS '待支付的借记卡费用';
--COMMENT ON COLUMN IB_Account_Balance_Margin.Total_Debit_Card_Pending_Charges_S IS '待支付的借记卡费用';
--COMMENT ON COLUMN IB_Account_Balance_Margin.Total_Debit_Card_Pending_Charges_C IS '待支付的借记卡费用';
COMMENT ON COLUMN IB_Account_Balance_Margin.remark IS '备注';
COMMENT ON COLUMN IB_Account_Balance_Margin.create_date IS '创建时间';
COMMENT ON COLUMN IB_Account_Balance_Margin.update_date IS '修改时间';
COMMENT ON TABLE IB_Account_Balance_Margin IS 'IB账户资金保证金表 -S证券 -C商品';

declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('IB_Account_Market_Value');
    if num > 0 then
        execute immediate 'drop table IB_Account_Market_Value';
    end if;
end;
/
CREATE TABLE IB_Account_Market_Value
(
  id                                NUMBER(20)             NOT NULL,
  account_id                        NUMBER(20)             NOT NULL,
  Account_Code                      VARCHAR2(32)           NOT NULL,
  Currency                          VARCHAR2(32),
  Total_Cash_Balance                NUMBER(20,8),
  Cash_Balance                      NUMBER(20,8),
  Accrued_Cash                      NUMBER(20,8),
  Stock_Market_Value                NUMBER(20,8),
  Option_Market_Value               NUMBER(20,8),
  Future_Option_Value               NUMBER(20,8),
  Futures_PNL                       NUMBER(20,8),
  Net_Liquidation_By_Currency       NUMBER(20,8),
  Fund_Value                        NUMBER(20,8),
  Net_Dividend                      NUMBER(20,8),
  Mutual_Fund_Value                 NUMBER(20,8),
  Money_Market_Fund_Value           NUMBER(20,8),
  Corporate_Bond_Value              NUMBER(20,8),
  T_Bond_Value                      NUMBER(20,8),
  T_Bill_Value                      NUMBER(20,8),
  Warrant_Value                     NUMBER(20,8),
  Fx_Cash_Balance                   NUMBER(20,8),
  Account_Or_Group                  VARCHAR2(32),
  Real_Currency                     VARCHAR2(32),
  Issuer_Option_Value               NUMBER(20,8),
  Unrealized_PnL                    NUMBER(20,8),
  Realized_PnL                      NUMBER(20,8),
  Exchange_Rate                     NUMBER(20,8),
  remark                            VARCHAR2(64),
  create_date                       NUMBER(13)             NOT NULL,
  update_date                       NUMBER(13),
  CONSTRAINT IB_Account_Market_Value_PK PRIMARY KEY (id),
  CONSTRAINT IB_Account_Market_Value_Un1 UNIQUE (account_id, Account_Code, Currency)
);
COMMENT ON COLUMN IB_Account_Market_Value.id IS '主键';
COMMENT ON COLUMN IB_Account_Market_Value.account_id IS '账户ID';
COMMENT ON COLUMN IB_Account_Market_Value.Account_Code IS 'IB对应AccountCode';
COMMENT ON COLUMN IB_Account_Market_Value.Currency IS '币种';
COMMENT ON COLUMN IB_Account_Market_Value.Total_Cash_Balance IS '总计现金';
COMMENT ON COLUMN IB_Account_Market_Value.Cash_Balance IS '已结算现金';
COMMENT ON COLUMN IB_Account_Market_Value.Accrued_Cash IS '应计现金';
COMMENT ON COLUMN IB_Account_Market_Value.Stock_Market_Value IS '股票';
COMMENT ON COLUMN IB_Account_Market_Value.Option_Market_Value IS '期权';
COMMENT ON COLUMN IB_Account_Market_Value.Future_Option_Value IS '期货期权';
COMMENT ON COLUMN IB_Account_Market_Value.Futures_PNL IS '期货';
COMMENT ON COLUMN IB_Account_Market_Value.Net_Liquidation_By_Currency IS '净清算折算货币';
COMMENT ON COLUMN IB_Account_Market_Value.Fund_Value IS '基金';
COMMENT ON COLUMN IB_Account_Market_Value.Net_Dividend IS '应计股息';
COMMENT ON COLUMN IB_Account_Market_Value.Mutual_Fund_Value IS '共同基金';
COMMENT ON COLUMN IB_Account_Market_Value.Money_Market_Fund_Value IS '货币市场基金';
COMMENT ON COLUMN IB_Account_Market_Value.Corporate_Bond_Value IS '公司债';
COMMENT ON COLUMN IB_Account_Market_Value.T_Bond_Value IS '长期国债';
COMMENT ON COLUMN IB_Account_Market_Value.T_Bill_Value IS '短期国债';
COMMENT ON COLUMN IB_Account_Market_Value.Warrant_Value IS '权证';
COMMENT ON COLUMN IB_Account_Market_Value.Fx_Cash_Balance IS '外汇现金';
COMMENT ON COLUMN IB_Account_Market_Value.Account_Or_Group IS '账户或分组';
COMMENT ON COLUMN IB_Account_Market_Value.Real_Currency IS '实际币种';
COMMENT ON COLUMN IB_Account_Market_Value.Issuer_Option_Value IS '发行方期权';
COMMENT ON COLUMN IB_Account_Market_Value.Unrealized_PnL IS '未实现盈亏';
COMMENT ON COLUMN IB_Account_Market_Value.Realized_PnL IS '已实现盈亏';
COMMENT ON COLUMN IB_Account_Market_Value.Exchange_Rate IS '汇率';
COMMENT ON COLUMN IB_Account_Market_Value.remark IS '备注';
COMMENT ON COLUMN IB_Account_Market_Value.create_date IS '创建时间';
COMMENT ON COLUMN IB_Account_Market_Value.update_date IS '修改时间';
COMMENT ON TABLE IB_Account_Market_Value IS 'IB账户市值表';

declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('IB_Account_Market_Value_His');
    if num > 0 then
        execute immediate 'drop table IB_Account_Market_Value_His';
    end if;
end;
/
CREATE TABLE IB_Account_Market_Value_His
(
  id                                NUMBER(20)             NOT NULL,
  account_id                        NUMBER(20)             NOT NULL,
  Account_Code                      VARCHAR2(32)           NOT NULL,
  Currency                          VARCHAR2(32),
  Total_Cash_Balance                NUMBER(20,8),
  Cash_Balance                      NUMBER(20,8),
  Accrued_Cash                      NUMBER(20,8),
  Stock_Market_Value                NUMBER(20,8),
  Option_Market_Value               NUMBER(20,8),
  Future_Option_Value               NUMBER(20,8),
  Futures_PNL                       NUMBER(20,8),
  Net_Liquidation_By_Currency       NUMBER(20,8),
  Fund_Value                        NUMBER(20,8),
  Net_Dividend                      NUMBER(20,8),
  Mutual_Fund_Value                 NUMBER(20,8),
  Money_Market_Fund_Value           NUMBER(20,8),
  Corporate_Bond_Value              NUMBER(20,8),
  T_Bond_Value                      NUMBER(20,8),
  T_Bill_Value                      NUMBER(20,8),
  Warrant_Value                     NUMBER(20,8),
  Fx_Cash_Balance                   NUMBER(20,8),
  Account_Or_Group                  VARCHAR2(32),
  Real_Currency                     VARCHAR2(32),
  Issuer_Option_Value               NUMBER(20,8),
  Unrealized_PnL                    NUMBER(20,8),
  Realized_PnL                      NUMBER(20,8),
  Exchange_Rate                     NUMBER(20,8),
  remark                            VARCHAR2(64),
  create_date                       NUMBER(13)             NOT NULL,
  update_date                       NUMBER(13),
  CONSTRAINT IB_Account_Market_Value_H_PK PRIMARY KEY (id),
  CONSTRAINT IB_Account_Market_Value_H_Un1 UNIQUE (account_id, Account_Code, Currency, create_date)
);
COMMENT ON COLUMN IB_Account_Market_Value_His.id IS '主键';
COMMENT ON COLUMN IB_Account_Market_Value_His.account_id IS '账户ID';
COMMENT ON COLUMN IB_Account_Market_Value_His.Account_Code IS 'IB对应AccountCode';
COMMENT ON COLUMN IB_Account_Market_Value_His.Currency IS '币种';
COMMENT ON COLUMN IB_Account_Market_Value_His.Total_Cash_Balance IS '总计现金';
COMMENT ON COLUMN IB_Account_Market_Value_His.Cash_Balance IS '已结算现金';
COMMENT ON COLUMN IB_Account_Market_Value_His.Accrued_Cash IS '应计现金';
COMMENT ON COLUMN IB_Account_Market_Value_His.Stock_Market_Value IS '股票';
COMMENT ON COLUMN IB_Account_Market_Value_His.Option_Market_Value IS '期权';
COMMENT ON COLUMN IB_Account_Market_Value_His.Future_Option_Value IS '期货期权';
COMMENT ON COLUMN IB_Account_Market_Value_His.Futures_PNL IS '期货';
COMMENT ON COLUMN IB_Account_Market_Value_His.Net_Liquidation_By_Currency IS '净清算折算货币';
COMMENT ON COLUMN IB_Account_Market_Value_His.Fund_Value IS '基金';
COMMENT ON COLUMN IB_Account_Market_Value_His.Net_Dividend IS '应计股息';
COMMENT ON COLUMN IB_Account_Market_Value_His.Mutual_Fund_Value IS '共同基金';
COMMENT ON COLUMN IB_Account_Market_Value_His.Money_Market_Fund_Value IS '货币市场基金';
COMMENT ON COLUMN IB_Account_Market_Value_His.Corporate_Bond_Value IS '公司债';
COMMENT ON COLUMN IB_Account_Market_Value_His.T_Bond_Value IS '长期国债';
COMMENT ON COLUMN IB_Account_Market_Value_His.T_Bill_Value IS '短期国债';
COMMENT ON COLUMN IB_Account_Market_Value_His.Warrant_Value IS '权证';
COMMENT ON COLUMN IB_Account_Market_Value_His.Fx_Cash_Balance IS '外汇现金';
COMMENT ON COLUMN IB_Account_Market_Value_His.Account_Or_Group IS '账户或分组';
COMMENT ON COLUMN IB_Account_Market_Value_His.Real_Currency IS '实际币种';
COMMENT ON COLUMN IB_Account_Market_Value_His.Issuer_Option_Value IS '发行方期权';
COMMENT ON COLUMN IB_Account_Market_Value_His.Unrealized_PnL IS '未实现盈亏';
COMMENT ON COLUMN IB_Account_Market_Value_His.Realized_PnL IS '已实现盈亏';
COMMENT ON COLUMN IB_Account_Market_Value_His.Exchange_Rate IS '汇率';
COMMENT ON COLUMN IB_Account_Market_Value_His.remark IS '备注';
COMMENT ON COLUMN IB_Account_Market_Value_His.create_date IS '创建时间';
COMMENT ON COLUMN IB_Account_Market_Value_His.update_date IS '修改时间';
COMMENT ON TABLE IB_Account_Market_Value_His IS 'IB账户市值历史表';

declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('IB_Account_Daily_PnL');
    if num > 0 then
        execute immediate 'drop table IB_Account_Daily_PnL';
    end if;
end;
/
CREATE TABLE IB_Account_Daily_PnL
(
  id                                NUMBER(20)             NOT NULL,
  account_id                        NUMBER(20)             NOT NULL,
  Account_Code                      VARCHAR2(32)           NOT NULL,
  daily_PnL                         NUMBER(20,8),
  unrealized_PnL                    NUMBER(20,8),
  realized_PnL                      NUMBER(20,8),
  remark                            VARCHAR2(64),
  create_date                       NUMBER(13)             NOT NULL,
  update_date                       NUMBER(13),
  CONSTRAINT IB_Account_Daily_PnL_PK PRIMARY KEY (id),
  CONSTRAINT IB_Account_Daily_PnL_Unique1 UNIQUE (account_id),
  CONSTRAINT IB_Account_Daily_PnL_Unique2 UNIQUE (Account_Code),
  CONSTRAINT IB_Account_Daily_PnL_Unique3 UNIQUE (account_id, Account_Code)
);
COMMENT ON COLUMN IB_Account_Daily_PnL.id IS '主键';
COMMENT ON COLUMN IB_Account_Daily_PnL.account_id IS '账户ID';
COMMENT ON COLUMN IB_Account_Daily_PnL.Account_Code IS 'IB对应AccountCode';
COMMENT ON COLUMN IB_Account_Daily_PnL.daily_PnL IS '当日盈亏(期末总资产-期初总资产-期间流入+期间流出)';
COMMENT ON COLUMN IB_Account_Daily_PnL.unrealized_PnL IS '未实现盈亏';
COMMENT ON COLUMN IB_Account_Daily_PnL.realized_PnL IS '已实现盈亏';
COMMENT ON COLUMN IB_Account_Daily_PnL.remark IS '备注';
COMMENT ON COLUMN IB_Account_Daily_PnL.create_date IS '创建时间';
COMMENT ON COLUMN IB_Account_Daily_PnL.update_date IS '修改时间';
COMMENT ON TABLE IB_Account_Daily_PnL IS 'IB账户当日盈亏';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('IB_Account_Position');
  if num > 0 then
    execute immediate 'drop table IB_Account_Position';
  end if;
end;
/
CREATE TABLE IB_Account_Position
(
  id                                NUMBER(20)             NOT NULL,
  account_id                        NUMBER(20)             NOT NULL,
  Account_Code                      VARCHAR2(32)           NOT NULL,
  Symbol                            VARCHAR2(32)           NOT NULL,
  Sec_Type                          VARCHAR2(32)           NOT NULL,
  Currency                          VARCHAR2(32)           NOT NULL,
  Financial_Instrument              VARCHAR2(128),
  Company_Name                      VARCHAR2(64),
  Exchange                          VARCHAR2(32),
  Position                          NUMBER(20,8),
  Unsettled_Position                NUMBER(20,8),
  Loanable_Qty                      NUMBER(20,8),
  Private_Locate                    NUMBER(20,8),
  Delta_Dollars                     NUMBER(20,8),
  Market_Price                      NUMBER(20,8),
  Market_Value                      NUMBER(20,8),
  Average_Cost                      NUMBER(20,8),
  Unrealized_PNL                    NUMBER(20,8),
  Realized_PNL                      NUMBER(20,8),
  Liquidate_Last                    VARCHAR2(32),
  remark                            VARCHAR2(64),
  create_date                       NUMBER(13)             NOT NULL,
  update_date                       NUMBER(13),
  CONSTRAINT IB_Account_Position_PK PRIMARY KEY (id),
  CONSTRAINT IB_Account_Position_Un1 UNIQUE (account_id, Account_Code, Symbol, Sec_Type, Currency)
);
COMMENT ON COLUMN IB_Account_Position.id IS '主键';
COMMENT ON COLUMN IB_Account_Position.account_id IS '账户ID';
COMMENT ON COLUMN IB_Account_Position.Account_Code IS 'IB对应AccountCode';
COMMENT ON COLUMN IB_Account_Position.Symbol IS '金融产品代码';
COMMENT ON COLUMN IB_Account_Position.Sec_Type IS '金融产品类型';
COMMENT ON COLUMN IB_Account_Position.Financial_Instrument IS '金融产品描述';
COMMENT ON COLUMN IB_Account_Position.Company_Name IS '公司名称';
COMMENT ON COLUMN IB_Account_Position.Exchange IS '交易所';
COMMENT ON COLUMN IB_Account_Position.Position IS '持仓头寸';
COMMENT ON COLUMN IB_Account_Position.Unsettled_Position IS '未结算持仓头寸';
COMMENT ON COLUMN IB_Account_Position.Loanable_Qty IS '可借数量';
COMMENT ON COLUMN IB_Account_Position.Private_Locate IS 'PrivateLocate';
COMMENT ON COLUMN IB_Account_Position.Delta_Dollars IS 'Delta美元';
COMMENT ON COLUMN IB_Account_Position.Currency IS '币种';
COMMENT ON COLUMN IB_Account_Position.Market_Price IS '市价';
COMMENT ON COLUMN IB_Account_Position.Market_Value IS '市场价值';
COMMENT ON COLUMN IB_Account_Position.Average_Cost IS '平均价格';
COMMENT ON COLUMN IB_Account_Position.Unrealized_PNL IS '未实现盈亏';
COMMENT ON COLUMN IB_Account_Position.Realized_PNL IS '已实现盈亏';
COMMENT ON COLUMN IB_Account_Position.Liquidate_Last IS '最后清算';
COMMENT ON COLUMN IB_Account_Position.remark IS '备注';
COMMENT ON COLUMN IB_Account_Position.create_date IS '创建时间';
COMMENT ON COLUMN IB_Account_Position.update_date IS '修改时间';
COMMENT ON TABLE IB_Account_Position IS 'IB账户持仓表';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('IB_Account_Trade');
  if num > 0 then
    execute immediate 'drop table IB_Account_Trade';
  end if;
end;
/
CREATE TABLE IB_Account_Trade
(
  id                                NUMBER(20)             NOT NULL,
  account_id                        NUMBER(20)             NOT NULL,
  Account_Code                      VARCHAR2(32)           NOT NULL,
  Symbol                            VARCHAR2(32)           NOT NULL,
  Sec_Type                          VARCHAR2(32)           NOT NULL,
  Currency                          VARCHAR2(32)           NOT NULL,
  Exchange                          VARCHAR2(32),
  exec_Id                           VARCHAR2(64),
  order_Id                          VARCHAR2(64),
  side                              VARCHAR2(32),
  price                             NUMBER(20,8),
  shares                            NUMBER(20,8),
  avg_price                         NUMBER(20,8),
  exec_time                         VARCHAR2(32),
  remark                            VARCHAR2(64),
  create_date                       NUMBER(13)             NOT NULL,
  update_date                       NUMBER(13),
  CONSTRAINT IB_Account_Trade_PK PRIMARY KEY (id),
  CONSTRAINT IB_Account_Trade_Un1 UNIQUE (account_id, Account_Code, exec_Id)
);
COMMENT ON COLUMN IB_Account_Trade.id IS '主键';
COMMENT ON COLUMN IB_Account_Trade.account_id IS '账户ID';
COMMENT ON COLUMN IB_Account_Trade.Account_Code IS 'IB对应AccountCode';
COMMENT ON COLUMN IB_Account_Trade.Symbol IS '金融产品代码';
COMMENT ON COLUMN IB_Account_Trade.Sec_Type IS '金融产品类型';
COMMENT ON COLUMN IB_Account_Trade.Currency IS '币种';
COMMENT ON COLUMN IB_Account_Trade.Exchange IS '交易所';
COMMENT ON COLUMN IB_Account_Trade.exec_Id IS '成交执行ID';
COMMENT ON COLUMN IB_Account_Trade.order_Id IS '订单ID';
COMMENT ON COLUMN IB_Account_Trade.side IS '方向';
COMMENT ON COLUMN IB_Account_Trade.price IS '价格';
COMMENT ON COLUMN IB_Account_Trade.shares IS '数量';
COMMENT ON COLUMN IB_Account_Trade.avg_price IS '成交均价';
COMMENT ON COLUMN IB_Account_Trade.exec_time IS '执行时间';
COMMENT ON COLUMN IB_Account_Trade.remark IS '备注';
COMMENT ON COLUMN IB_Account_Trade.create_date IS '创建时间';
COMMENT ON COLUMN IB_Account_Trade.update_date IS '修改时间';
COMMENT ON TABLE IB_Account_Trade IS 'IB账户交易流水表';

/*
declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('IB_Account_Summary');
    if num > 0 then
        execute immediate 'drop table IB_Account_Summary';
    end if;
end;
/
CREATE TABLE IB_Account_Summary
(
  id                            NUMBER(20)             NOT NULL,
  account_id                    NUMBER(20)             NOT NULL,
  accountType                   VARCHAR2(32)           NOT NULL,
  cushion                       NUMBER(20,8),
  lookAheadNextChange           NUMBER(20,8),
  currency                      VARCHAR2(32)           NOT NULL,
  accruedCash                   NUMBER(20,8),
  availableFunds                NUMBER(20,8),
  buyingPower                   NUMBER(20,8),
  equityWithLoanValue           NUMBER(20,8),
  excessLiquidity               NUMBER(20,8),
  fullAvailableFunds            NUMBER(20,8),
  fullExcessLiquidity           NUMBER(20,8),
  fullInitMarginReq             NUMBER(20,8),
  fullMaintMarginReq            NUMBER(20,8),
  grossPositionValue            NUMBER(20,8),
  initMarginReq                 NUMBER(20,8),
  lookAheadAvailableFunds       NUMBER(20,8),
  lookAheadExcessLiquidity      NUMBER(20,8),
  lookAheadInitMarginReq		NUMBER(20,8),
  lookAheadMaintMarginReq       NUMBER(20,8),
  maintMarginReq                NUMBER(20,8),
  netLiquidation                NUMBER(20,8),
  totalCashValue                NUMBER(20,8),
  create_date                   NUMBER(13)             NOT NULL,
  update_date                   NUMBER(13),
  CONSTRAINT IB_Account_Summary_PK PRIMARY KEY (id),
  CONSTRAINT IB_Account_Summary_Unique UNIQUE (account_id)
);
COMMENT ON COLUMN IB_Account_Summary.id IS '主键';
COMMENT ON COLUMN IB_Account_Summary.account_id IS '账户ID';
COMMENT ON COLUMN IB_Account_Summary.accountType IS '账户类型';
COMMENT ON COLUMN IB_Account_Summary.cushion IS '账户名';
COMMENT ON COLUMN IB_Account_Summary.lookAheadNextChange IS '帐户昵称';
COMMENT ON COLUMN IB_Account_Summary.currency IS '币种';
COMMENT ON COLUMN IB_Account_Summary.accruedCash IS '应计现金';
COMMENT ON COLUMN IB_Account_Summary.availableFunds IS '注册所在地或所在IP';
COMMENT ON COLUMN IB_Account_Summary.buyingPower IS '国家地区(区域表)';
COMMENT ON COLUMN IB_Account_Summary.equityWithLoanValue IS '合法居住国家地区(区域表)';
COMMENT ON COLUMN IB_Account_Summary.excessLiquidity IS '语言标识';
COMMENT ON COLUMN IB_Account_Summary.fullAvailableFunds IS '完全可用资金';
COMMENT ON COLUMN IB_Account_Summary.fullExcessLiquidity IS '全额流动资金';
COMMENT ON COLUMN IB_Account_Summary.fullInitMarginReq IS '账户状态(0正常、1冻结、2注销)';
COMMENT ON COLUMN IB_Account_Summary.fullMaintMarginReq IS 'ga私钥';
COMMENT ON COLUMN IB_Account_Summary.grossPositionValue IS '安全验证策略';
COMMENT ON COLUMN IB_Account_Summary.initMarginReq IS '初始保证金';
COMMENT ON COLUMN IB_Account_Summary.lookAheadAvailableFunds IS '前瞻可用资金';
COMMENT ON COLUMN IB_Account_Summary.lookAheadExcessLiquidity IS '前瞻剩余流动性';
COMMENT ON COLUMN IB_Account_Summary.lookAheadInitMarginReq IS '查看维护余量请求';
COMMENT ON COLUMN IB_Account_Summary.lookAheadMaintMarginReq IS '备注';
COMMENT ON COLUMN IB_Account_Summary.maintMarginReq IS '签名';
COMMENT ON COLUMN IB_Account_Summary.netLiquidation IS '净清算价值';
COMMENT ON COLUMN IB_Account_Summary.totalCashValue IS '删除标识(0未删除、1删除)';
COMMENT ON COLUMN IB_Account_Summary.create_date IS '创建时间';
COMMENT ON COLUMN IB_Account_Summary.update_date IS '修改时间';
COMMENT ON TABLE IB_Account_Summary IS 'IB账户概览表';
*/
