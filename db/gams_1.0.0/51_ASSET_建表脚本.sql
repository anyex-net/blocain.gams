declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Asset_Position');
    if num > 0 then
        execute immediate 'drop table Asset_Position';
    end if;
end;
/
CREATE TABLE Asset_Position
(  id                       NUMBER(20)   NOT NULL,  
   account_id               NUMBER(20)   NOT NULL,
   stockinfo_id 			NUMBER(20)   NOT NULL,
   position_direction   	VARCHAR2(32) DEFAULT 'Long'    NOT NULL,
   open_avg_price       	NUMBER(20, 8) DEFAULT 0        NOT NULL,
   amount      			    NUMBER(24, 12) DEFAULT 0       NOT NULL,
   frozen_amount   	        NUMBER(24, 12) DEFAULT 0       NOT NULL,
   period_init_amount       NUMBER(24, 12) DEFAULT 0       NOT NULL,
   period_inflow_amount     NUMBER(24, 12) DEFAULT 0       NOT NULL,
   period_outflow_amount    NUMBER(24, 12) DEFAULT 0       NOT NULL,
   real_profit_loss   		NUMBER(24, 12) DEFAULT 0       NOT NULL,
   unreal_profit_loss   	NUMBER(24, 12) DEFAULT 0       NOT NULL,
   leverage_multiples   	NUMBER(8, 4) DEFAULT 0         NOT NULL,
   remark      			    VARCHAR2(64),
   update_date              NUMBER(13)   NOT NULL,
   CONSTRAINT Asset_Position_PK PRIMARY KEY (id),
   CONSTRAINT Asset_Position_Unique UNIQUE (account_id, stockinfo_id, position_direction)
 );
COMMENT ON COLUMN Asset_Position.id IS '主键';  
COMMENT ON COLUMN Asset_Position.account_id IS '账户ID';
COMMENT ON COLUMN Asset_Position.stockinfo_id IS '证券信息ID';
COMMENT ON COLUMN Asset_Position.position_direction IS '持仓方向';
COMMENT ON COLUMN Asset_Position.open_avg_price IS '开仓均价';
COMMENT ON COLUMN Asset_Position.amount IS '数量';
COMMENT ON COLUMN Asset_Position.frozen_amount IS '冻结数量';
COMMENT ON COLUMN Asset_Position.period_init_amount IS '期间期初数量';
COMMENT ON COLUMN Asset_Position.period_inflow_amount IS '期间流入数量';
COMMENT ON COLUMN Asset_Position.period_outflow_amount IS '期间流出数量';
COMMENT ON COLUMN Asset_Position.real_profit_loss IS '已实现盈亏';
COMMENT ON COLUMN Asset_Position.unreal_profit_loss IS '未实现盈亏';
COMMENT ON COLUMN Asset_Position.leverage_multiples IS '杠杆倍数';
COMMENT ON COLUMN Asset_Position.remark IS '备注';
COMMENT ON COLUMN Asset_Position.update_date IS '修改时间';
COMMENT ON TABLE Asset_Position IS '资产持仓表';

declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Asset_Current');
    if num > 0 then
        execute immediate 'drop table Asset_Current';
    end if;
end;
/
CREATE TABLE Asset_Current
(  id                       NUMBER(20)   NOT NULL,  
   account_id               NUMBER(20)   NOT NULL,
   current_timestamp        TIMESTAMP DEFAULT systimestamp    NOT NULL,
   business_flag            VARCHAR2(64)                      NOT NULL,
   stockinfo_id 			NUMBER(20)   NOT NULL,
   occur_direct             VARCHAR2(16)                      NOT NULL,
   original_amount          NUMBER(24, 12) DEFAULT 0          NOT NULL,
   occur_amount             NUMBER(24, 12) DEFAULT 0          NOT NULL,
   last_amount              NUMBER(24, 12) DEFAULT 0          NOT NULL,
   forzen_original_amount   NUMBER(24, 12) DEFAULT 0          NOT NULL,
   occur_forzen_amount      NUMBER(24, 12) DEFAULT 0          NOT NULL,
   forzen_last_amount       NUMBER(24, 12) DEFAULT 0          NOT NULL,
   transaction_id           VARCHAR2(128),
   deposit_account          VARCHAR2(128),
   withdraw_account         VARCHAR2(128),
   fee                      NUMBER(24, 12) DEFAULT 0          NOT NULL,
   netFee                   NUMBER(24, 12) DEFAULT 0,
   status                   NUMBER(1, 0) DEFAULT 0            NOT NULL,
   original_business_id     NUMBER(20)                        NOT NULL,
   remark                   VARCHAR2(256),
   CONSTRAINT Asset_Current_PK PRIMARY KEY (id)
 );
COMMENT ON COLUMN Asset_Current.id IS '主键';  
COMMENT ON COLUMN Asset_Current.account_id IS '账户ID';
COMMENT ON COLUMN Asset_Current.current_timestamp IS '流水时间戳';
COMMENT ON COLUMN Asset_Current.business_flag IS '业务标志';
COMMENT ON COLUMN Asset_Current.stockinfo_id IS '证券信息ID';
COMMENT ON COLUMN Asset_Current.occur_direct IS '发生方向(增加increase、减少decrease、冻结frozen、解冻unfrozen、解冻并减少frozenDecrease)';
COMMENT ON COLUMN Asset_Current.original_amount IS '原始数量';
COMMENT ON COLUMN Asset_Current.occur_amount IS '发生数量';
COMMENT ON COLUMN Asset_Current.last_amount IS '最新数量';
COMMENT ON COLUMN Asset_Current.forzen_original_amount IS '冻结原始数量';
COMMENT ON COLUMN Asset_Current.occur_forzen_amount IS '冻结发生数量';
COMMENT ON COLUMN Asset_Current.forzen_last_amount IS '冻结最新数量';
COMMENT ON COLUMN Asset_Current.transaction_id IS '唯一交易ID';
COMMENT ON COLUMN Asset_Current.deposit_account IS '充值入金账号';
COMMENT ON COLUMN Asset_Current.withdraw_account IS '提现出金账号';
COMMENT ON COLUMN Asset_Current.fee IS '手续费';
COMMENT ON COLUMN Asset_Current.netFee IS '网络手续费';
COMMENT ON COLUMN Asset_Current.fee IS '手续费';
COMMENT ON COLUMN Asset_Current.status IS '状态(0正常、1非正常)';
COMMENT ON COLUMN Asset_Current.original_business_id IS '原业务ID';
COMMENT ON COLUMN Asset_Current.remark IS '备注';
COMMENT ON TABLE Asset_Current IS '资产流水表';

declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Asset_Overview');
    if num > 0 then
        execute immediate 'drop table Asset_Overview';
    end if;
end;
/
CREATE TABLE Asset_Overview
(  id                       NUMBER(20)   NOT NULL,  
   account_id               NUMBER(20)   NOT NULL,
   stock_long_value   	    NUMBER(24, 12) DEFAULT 0       NOT NULL,
   stock_short_value        NUMBER(24, 12) DEFAULT 0       NOT NULL,
   bond_long_value      	NUMBER(24, 12) DEFAULT 0       NOT NULL,
   futures_long_value   	NUMBER(24, 12) DEFAULT 0       NOT NULL,
   futures_short_value   	NUMBER(24, 12) DEFAULT 0       NOT NULL,
   option_long_value        NUMBER(24, 12) DEFAULT 0       NOT NULL,
   option_short_value       NUMBER(24, 12) DEFAULT 0       NOT NULL,
   foreignex_long_value     NUMBER(24, 12) DEFAULT 0       NOT NULL,
   foreignex_short_value    NUMBER(24, 12) DEFAULT 0       NOT NULL,
   fund_long_value          NUMBER(24, 12) DEFAULT 0       NOT NULL,
   fund_short_value         NUMBER(24, 12) DEFAULT 0       NOT NULL,
   remark      			    VARCHAR2(64),
   update_date              NUMBER(13)   NOT NULL,
   CONSTRAINT Asset_Overview_PK PRIMARY KEY (id),
   CONSTRAINT Asset_Overview_Unique UNIQUE (account_id)
 );
COMMENT ON COLUMN Asset_Overview.id IS '主键';  
COMMENT ON COLUMN Asset_Overview.account_id IS '账户ID';
COMMENT ON COLUMN Asset_Overview.stock_long_value IS '股票多头市值';
COMMENT ON COLUMN Asset_Overview.stock_short_value IS '股票空头市值';
COMMENT ON COLUMN Asset_Overview.bond_long_value IS '债券多头市值';
COMMENT ON COLUMN Asset_Overview.futures_long_value IS '期货多头市值';
COMMENT ON COLUMN Asset_Overview.futures_short_value IS '期货空头市值';
COMMENT ON COLUMN Asset_Overview.option_long_value IS '期权多头市值';
COMMENT ON COLUMN Asset_Overview.option_short_value IS '期权空头市值';
COMMENT ON COLUMN Asset_Overview.foreignex_long_value IS '外汇多头市值';
COMMENT ON COLUMN Asset_Overview.foreignex_short_value IS '外汇空头市值';
COMMENT ON COLUMN Asset_Overview.fund_long_value IS '基金多头市值';
COMMENT ON COLUMN Asset_Overview.fund_short_value IS '基金空头市值';
COMMENT ON COLUMN Asset_Overview.remark IS '备注';
COMMENT ON COLUMN Asset_Overview.update_date IS '修改时间';
COMMENT ON TABLE Asset_Overview IS '资产概览表';

declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Asset_Deposit');
    if num > 0 then
        execute immediate 'drop table Asset_Deposit';
    end if;
end;
/
CREATE TABLE Asset_Deposit
(  id                       NUMBER(20)   NOT NULL,  
   account_id               NUMBER(20)   NOT NULL,
   currency                 VARCHAR2(16) NOT NULL,
   remitting_bank           VARCHAR2(64) NOT NULL,
   account_number           VARCHAR2(64) NOT NULL,
   amount                   NUMBER(24,8) NOT NULL,
   fee                      NUMBER(12,8) DEFAULT 0,
   transfer_type            VARCHAR2(32) NOT NULL,
   swift_code               VARCHAR2(32) NOT NULL,
   transaction_id           VARCHAR2(128) NOT NULL,
   deposit_date             NUMBER(13) NOT NULL,
   status                   VARCHAR2(16) NOT NULL,
   deposit_confirm_date 	NUMBER(13),
   remark                   VARCHAR2(256),
   update_by                NUMBER(20),
   update_date              NUMBER(13),
   CONSTRAINT Asset_Deposit_PK PRIMARY KEY (id),
   CONSTRAINT Asset_Deposit_Unique UNIQUE (currency, remitting_bank, account_number, transaction_id)
 );
COMMENT ON COLUMN Asset_Deposit.id IS '主键';  
COMMENT ON COLUMN Asset_Deposit.account_id IS '账户ID';
COMMENT ON COLUMN Asset_Deposit.currency IS '币种(数据字典)';
COMMENT ON COLUMN Asset_Deposit.remitting_bank IS '汇出银行(钱包ID)';
COMMENT ON COLUMN Asset_Deposit.account_number IS '汇出银行账号(钱包地址)';
COMMENT ON COLUMN Asset_Deposit.amount IS '充值入金数量';
COMMENT ON COLUMN Asset_Deposit.fee IS '手续费';
COMMENT ON COLUMN Asset_Deposit.transfer_type IS '转账方式(支票、电汇，具体见数据字典)';
COMMENT ON COLUMN Asset_Deposit.swift_code IS 'SWIFT码';
COMMENT ON COLUMN Asset_Deposit.transaction_id IS '唯一交易ID';
COMMENT ON COLUMN Asset_Deposit.status IS '状态(unconfirmed未确认、confirmed已确认、canceled已取消)';
COMMENT ON COLUMN Asset_Deposit.deposit_date IS '充值入金时间';
COMMENT ON COLUMN Asset_Deposit.deposit_confirm_date IS '充值入账确认时间';
COMMENT ON COLUMN Asset_Deposit.remark IS '备注';
COMMENT ON COLUMN Asset_Deposit.update_by IS '修改人';
COMMENT ON COLUMN Asset_Deposit.update_date IS '修改时间';
COMMENT ON TABLE Asset_Deposit IS '充值入金记录表';

declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Asset_Withdraw');
    if num > 0 then
        execute immediate 'drop table Asset_Withdraw';
    end if;
end;
/
CREATE TABLE Asset_Withdraw
(  id                       NUMBER(20)   NOT NULL,  
   account_id               NUMBER(20)   NOT NULL,
   currency                 VARCHAR2(16) NOT NULL,
   bene_bank                VARCHAR2(64) NOT NULL,
   account_number           VARCHAR2(64) NOT NULL,
   amount                   NUMBER(20,8) NOT NULL,
   fee                      NUMBER(12,8) DEFAULT 0,
   transfer_type            VARCHAR2(32) NOT NULL,
   swift_code               VARCHAR2(32) NOT NULL,
   transaction_id           VARCHAR2(128) NOT NULL,
   withdraw_date            NUMBER(13) NOT NULL,
   status                   VARCHAR2(16) NOT NULL,
   withdraw_confirm_date 	NUMBER(13),
   remark                   VARCHAR2(256),
   update_by                NUMBER(20),
   update_date              NUMBER(13),
   CONSTRAINT Asset_Withdraw_PK PRIMARY KEY (id)
 );
COMMENT ON COLUMN Asset_Withdraw.id IS '主键';  
COMMENT ON COLUMN Asset_Withdraw.account_id IS '账户ID';
COMMENT ON COLUMN Asset_Withdraw.currency IS '币种(数据字典)';
COMMENT ON COLUMN Asset_Withdraw.bene_bank IS '收款银行(钱包ID)';
COMMENT ON COLUMN Asset_Withdraw.account_number IS '收款银行账号(钱包地址)';
COMMENT ON COLUMN Asset_Withdraw.amount IS '提现出金数量';
COMMENT ON COLUMN Asset_Withdraw.fee IS '提现手续费';
COMMENT ON COLUMN Asset_Withdraw.transfer_type IS '转账方式(支票、电汇，具体见数据字典)';
COMMENT ON COLUMN Asset_Withdraw.swift_code IS 'SWIFT码';
COMMENT ON COLUMN Asset_Withdraw.transaction_id IS '唯一交易ID';
COMMENT ON COLUMN Asset_Withdraw.status IS '状态(unconfirmed未确认、confirmed已确认、canceled已取消)';
COMMENT ON COLUMN Asset_Withdraw.withdraw_date IS '提现出金申请时间';
COMMENT ON COLUMN Asset_Withdraw.withdraw_confirm_date IS '提现入账确认时间';
COMMENT ON COLUMN Asset_Withdraw.remark IS '备注';
COMMENT ON COLUMN Asset_Withdraw.update_by IS '修改人';
COMMENT ON COLUMN Asset_Withdraw.update_date IS '修改时间';
COMMENT ON TABLE Asset_Withdraw IS '提现出金记录表';

/*
declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Asset_Adjust');
    if num > 0 then
        execute immediate 'drop table Asset_Adjust';
    end if;
end;
/
CREATE TABLE Asset_Adjust
(  id                       NUMBER(20)   NOT NULL,  
   account_id               NUMBER(20)   NOT NULL,
   currency                 VARCHAR2(16) NOT NULL,
   bene_bank                VARCHAR2(32) NOT NULL,
   account_number           VARCHAR2(32) NOT NULL,
   amount                   NUMBER(20,8) NOT NULL,
   fee                      NUMBER(12,8) DEFAULT 0,
   swift_code               VARCHAR2(32) NOT NULL,
   transaction_id           VARCHAR2(64) NOT NULL,
   status                   VARCHAR2(16) NOT NULL,
   withdraw_date 		    NUMBER(13)   NOT NULL,
   withdraw_confirm_date 	NUMBER(13)   NOT NULL,
   remark                   VARCHAR2(256),
   update_by                NUMBER(20),
   update_date              NUMBER(13)   NOT NULL,
   CONSTRAINT Asset_Adjust_PK PRIMARY KEY (id)
 );
COMMENT ON COLUMN Asset_Adjust.id IS '主键';  
COMMENT ON COLUMN Asset_Adjust.account_id IS '账户ID';
COMMENT ON COLUMN Asset_Adjust.currency IS '币种(数据字典)';
COMMENT ON COLUMN Asset_Adjust.bene_bank IS '收款银行(钱包ID)';
COMMENT ON COLUMN Asset_Adjust.account_number IS '收款银行账号(钱包地址)';
COMMENT ON COLUMN Asset_Adjust.amount IS '提现出金数量';
COMMENT ON COLUMN Asset_Adjust.fee IS '提现手续费';
COMMENT ON COLUMN Asset_Adjust.swift_code IS 'SWIFT码';
COMMENT ON COLUMN Asset_Adjust.transaction_id IS '唯一交易ID';
COMMENT ON COLUMN Asset_Adjust.status IS '状态(unconfirmed未确认、confirmed已确认)';
COMMENT ON COLUMN Asset_Adjust.withdraw_date IS '提现出金申请时间';
COMMENT ON COLUMN Asset_Adjust.withdraw_confirm_date IS '提现入账确认时间';
COMMENT ON COLUMN Asset_Adjust.remark IS '备注';
COMMENT ON COLUMN Asset_Adjust.update_by IS '修改人';
COMMENT ON COLUMN Asset_Adjust.update_date IS '修改时间';
COMMENT ON TABLE Asset_Adjust IS '资金调整记录表';
*/
