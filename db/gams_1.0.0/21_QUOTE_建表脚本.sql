--股票行情
--Quote_Stock
declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Quote_Stock');
    if num > 0 then
        execute immediate 'drop table Quote_Stock';
    end if;
end;
/
CREATE TABLE Quote_Stock
(  id                       NUMBER(20)      NOT NULL,
   stockinfo_id             NUMBER(20)      NOT NULL,
   stock_code               VARCHAR2(32),
   exchange_id              NUMBER(20)      NOT NULL, 
   quote_date               NUMBER(13)      NOT NULL,
   prev_close               NUMBER(12,6)    NOT NULL,
   last                     NUMBER(12,6)    NOT NULL,
   open                     NUMBER(12,6),
   high                     NUMBER(12,6),
   low                      NUMBER(12,6),
   chg                      NUMBER(12,6),
   change                   NUMBER(12,4),
   volume                   NUMBER(14,4),
   turnover                 NUMBER(14,4),
   market_cap               NUMBER(12,4),
   pe_ratio                 NUMBER(10,4),
   pb_ratio                 NUMBER(10,4),
   committee_than           NUMBER(10,4),
   quantity_relative        NUMBER(10,4),
   turnover_rate            NUMBER(10,4),
   bid                      NUMBER(12,6),
   ask                      NUMBER(12,6),
   bid_size                 NUMBER(12),
   ask_size                 NUMBER(12),
   create_by                NUMBER(20),
   create_date 		        NUMBER(13),
   update_by                NUMBER(20),
   update_date              NUMBER(13),
   CONSTRAINT Quote_Stock_PK PRIMARY KEY (id),
   CONSTRAINT Quote_Stock_Unique UNIQUE (stockinfo_id, quote_date)
 );
COMMENT ON COLUMN Quote_Stock.id IS '主键';  
COMMENT ON COLUMN Quote_Stock.stockinfo_id IS '证券信息ID';
COMMENT ON COLUMN Quote_Stock.stock_code IS '证券编码';
COMMENT ON COLUMN Quote_Stock.exchange_id IS '上市场所ID(证券交易所)';
COMMENT ON COLUMN Quote_Stock.quote_date IS '行情日期';
COMMENT ON COLUMN Quote_Stock.prev_close IS '昨收盘价';
COMMENT ON COLUMN Quote_Stock.last IS '最新价';
COMMENT ON COLUMN Quote_Stock.open IS '开盘价';
COMMENT ON COLUMN Quote_Stock.high IS '最高价';
COMMENT ON COLUMN Quote_Stock.low IS '最低价';
COMMENT ON COLUMN Quote_Stock.chg IS '涨跌幅';
COMMENT ON COLUMN Quote_Stock.change IS '涨跌额';
COMMENT ON COLUMN Quote_Stock.volume IS '成交量';
COMMENT ON COLUMN Quote_Stock.turnover IS '成交额';
COMMENT ON COLUMN Quote_Stock.market_cap IS '总市值';
COMMENT ON COLUMN Quote_Stock.pe_ratio IS '市盈率';
COMMENT ON COLUMN Quote_Stock.pb_ratio IS '市净率';
COMMENT ON COLUMN Quote_Stock.committee_than IS '委比';
COMMENT ON COLUMN Quote_Stock.quantity_relative IS '量比';
COMMENT ON COLUMN Quote_Stock.turnover_rate IS '换手率';
COMMENT ON COLUMN Quote_Stock.bid IS '买一价';
COMMENT ON COLUMN Quote_Stock.ask IS '卖一价';
COMMENT ON COLUMN Quote_Stock.bid_size IS '买入量';
COMMENT ON COLUMN Quote_Stock.ask_size IS '卖出量';
COMMENT ON COLUMN Quote_Stock.create_by IS '创建人';
COMMENT ON COLUMN Quote_Stock.create_date IS '创建时间';
COMMENT ON COLUMN Quote_Stock.update_by IS '修改人';
COMMENT ON COLUMN Quote_Stock.update_date IS '修改时间';
COMMENT ON TABLE Quote_Stock IS '股票行情';

--债券行情
--Quote_Bond
declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Quote_Bond');
    if num > 0 then
        execute immediate 'drop table Quote_Bond';
    end if;
end;
/
CREATE TABLE Quote_Bond
(  id                       NUMBER(20)      NOT NULL,
   stockinfo_id             NUMBER(20)      NOT NULL,
   stock_code               VARCHAR2(32),
   exchange_id              NUMBER(20)      NOT NULL, 
   quote_date               NUMBER(13)      NOT NULL,
   prev_close               NUMBER(12,6)    NOT NULL,
   last                     NUMBER(12,6)    NOT NULL,
   open                     NUMBER(12,6),
   high                     NUMBER(12,6),
   low                      NUMBER(12,6),
   chg                      NUMBER(12,6),
   change                   NUMBER(12,4),
   volume                   NUMBER(14,4),
   turnover                 NUMBER(14,4),
   committee_than           NUMBER(10,4),
   quantity_relative        NUMBER(10,4),
   turnover_rate            NUMBER(10,4),
   bid                      NUMBER(12,6),
   ask                      NUMBER(12,6),
   bid_size                 NUMBER(12),
   ask_size                 NUMBER(12),
   create_by                NUMBER(20),
   create_date 		        NUMBER(13),
   update_by                NUMBER(20),
   update_date              NUMBER(13),
   CONSTRAINT Quote_Bond_PK PRIMARY KEY (id),
   CONSTRAINT Quote_Bond_Unique UNIQUE (stockinfo_id, quote_date)
 );
COMMENT ON COLUMN Quote_Bond.id IS '主键';  
COMMENT ON COLUMN Quote_Bond.stockinfo_id IS '证券信息ID';
COMMENT ON COLUMN Quote_Bond.stock_code IS '证券编码';
COMMENT ON COLUMN Quote_Bond.exchange_id IS '上市场所ID(证券交易所)';
COMMENT ON COLUMN Quote_Bond.quote_date IS '行情日期';
COMMENT ON COLUMN Quote_Bond.prev_close IS '昨收盘价';
COMMENT ON COLUMN Quote_Bond.last IS '最新价';
COMMENT ON COLUMN Quote_Bond.open IS '开盘价';
COMMENT ON COLUMN Quote_Bond.high IS '最高价';
COMMENT ON COLUMN Quote_Bond.low IS '最低价';
COMMENT ON COLUMN Quote_Bond.chg IS '涨跌幅';
COMMENT ON COLUMN Quote_Bond.change IS '涨跌额';
COMMENT ON COLUMN Quote_Bond.volume IS '成交量';
COMMENT ON COLUMN Quote_Bond.turnover IS '成交额';
COMMENT ON COLUMN Quote_Bond.committee_than IS '委比';
COMMENT ON COLUMN Quote_Bond.quantity_relative IS '量比';
COMMENT ON COLUMN Quote_Bond.turnover_rate IS '换手率';
COMMENT ON COLUMN Quote_Bond.bid IS '买一价';
COMMENT ON COLUMN Quote_Bond.ask IS '卖一价';
COMMENT ON COLUMN Quote_Bond.bid_size IS '买入量';
COMMENT ON COLUMN Quote_Bond.ask_size IS '卖出量';
COMMENT ON COLUMN Quote_Bond.create_by IS '创建人';
COMMENT ON COLUMN Quote_Bond.create_by IS '创建时间';
COMMENT ON COLUMN Quote_Bond.update_by IS '修改人';
COMMENT ON COLUMN Quote_Bond.update_date IS '修改时间';
COMMENT ON TABLE Quote_Bond IS '债券行情';

--期货行情
--Quote_Futures
declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Quote_Futures');
    if num > 0 then
        execute immediate 'drop table Quote_Futures';
    end if;
end;
/
CREATE TABLE Quote_Futures
(  id                       NUMBER(20)      NOT NULL,
   stockinfo_id             NUMBER(20)      NOT NULL,
   stock_code               VARCHAR2(32),
   exchange_id              NUMBER(20)      NOT NULL, 
   quote_date               NUMBER(13)      NOT NULL,
   prev_close               NUMBER(12,6)    NOT NULL,
   last                     NUMBER(12,6)    NOT NULL,
   open                     NUMBER(12,6),
   high                     NUMBER(12,6),
   low                      NUMBER(12,6),
   chg                      NUMBER(12,6),
   change                   NUMBER(12,4),
   volume                   NUMBER(14,4),
   turnover                 NUMBER(14,4),
   committee_than           NUMBER(10,4),
   quantity_relative        NUMBER(10,4),
   turnover_rate            NUMBER(10,4),
   buy_volume_outside       NUMBER(12,4),
   sell_volume_outside      NUMBER(12,4),
   position_volume          NUMBER(12,4),
   bid                      NUMBER(12,6),
   ask                      NUMBER(12,6),
   bid_size                 NUMBER(12),
   ask_size                 NUMBER(12),
   create_by                NUMBER(20),
   create_date 		        NUMBER(13),
   update_by                NUMBER(20),
   update_date              NUMBER(13),
   CONSTRAINT Quote_Futures_PK PRIMARY KEY (id),
   CONSTRAINT Quote_Futures_Unique UNIQUE (stockinfo_id, quote_date)
 );
COMMENT ON COLUMN Quote_Futures.id IS '主键';  
COMMENT ON COLUMN Quote_Futures.stockinfo_id IS '证券信息ID';
COMMENT ON COLUMN Quote_Futures.stock_code IS '证券编码';
COMMENT ON COLUMN Quote_Futures.exchange_id IS '上市场所ID(证券交易所)';
COMMENT ON COLUMN Quote_Futures.quote_date IS '行情日期';
COMMENT ON COLUMN Quote_Futures.prev_close IS '昨收盘价';
COMMENT ON COLUMN Quote_Futures.last IS '最新价';
COMMENT ON COLUMN Quote_Futures.open IS '开盘价';
COMMENT ON COLUMN Quote_Futures.high IS '最高价';
COMMENT ON COLUMN Quote_Futures.low IS '最低价';
COMMENT ON COLUMN Quote_Futures.chg IS '涨跌幅';
COMMENT ON COLUMN Quote_Futures.change IS '涨跌额';
COMMENT ON COLUMN Quote_Futures.volume IS '成交量';
COMMENT ON COLUMN Quote_Futures.turnover IS '成交额';
COMMENT ON COLUMN Quote_Futures.committee_than IS '委比';
COMMENT ON COLUMN Quote_Futures.quantity_relative IS '量比';
COMMENT ON COLUMN Quote_Futures.turnover_rate IS '换手率';
COMMENT ON COLUMN Quote_Futures.buy_volume_outside IS '买盘(外盘)';
COMMENT ON COLUMN Quote_Futures.sell_volume_outside IS '卖盘(内盘)';
COMMENT ON COLUMN Quote_Futures.position_volume IS '持仓量';
COMMENT ON COLUMN Quote_Futures.bid IS '买一价';
COMMENT ON COLUMN Quote_Futures.ask IS '卖一价';
COMMENT ON COLUMN Quote_Futures.bid_size IS '买入量';
COMMENT ON COLUMN Quote_Futures.ask_size IS '卖出量';
COMMENT ON COLUMN Quote_Futures.create_by IS '创建人';
COMMENT ON COLUMN Quote_Futures.create_by IS '创建时间';
COMMENT ON COLUMN Quote_Futures.update_by IS '修改人';
COMMENT ON COLUMN Quote_Futures.update_date IS '修改时间';
COMMENT ON TABLE Quote_Futures IS '期货行情';

--期权行情
--Quote_Option
declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Quote_Option');
    if num > 0 then
        execute immediate 'drop table Quote_Option';
    end if;
end;
/
CREATE TABLE Quote_Option
(  id                       NUMBER(20)      NOT NULL,
   stockinfo_id             NUMBER(20)      NOT NULL,
   stock_code               VARCHAR2(32),
   exchange_id              NUMBER(20)      NOT NULL, 
   quote_date               NUMBER(13)      NOT NULL,
   prev_close               NUMBER(12,6)    NOT NULL,
   last                     NUMBER(12,6)    NOT NULL,
   open                     NUMBER(12,6),
   high                     NUMBER(12,6),
   low                      NUMBER(12,6),
   chg                      NUMBER(12,6),
   change                   NUMBER(12,4),
   volume                   NUMBER(14,4),
   turnover                 NUMBER(14,4),
   committee_than           NUMBER(10,4),
   quantity_relative        NUMBER(10,4),
   turnover_rate            NUMBER(10,4),
   buy_volume_outside       NUMBER(12,4),
   sell_volume_outside      NUMBER(12,4),
   position_volume          NUMBER(12,4),
   position_daily_increase  NUMBER(12,4),
   exercise_price           NUMBER(12,6),
   remaining_days           NUMBER(6),
   bid                      NUMBER(12,6),
   ask                      NUMBER(12,6),
   bid_size                 NUMBER(12),
   ask_size                 NUMBER(12),
   create_by                NUMBER(20),
   create_date 		        NUMBER(13),
   update_by                NUMBER(20),
   update_date              NUMBER(13),
   CONSTRAINT Quote_Option_PK PRIMARY KEY (id),
   CONSTRAINT Quote_Option_Unique UNIQUE (stockinfo_id, quote_date)
 );
COMMENT ON COLUMN Quote_Option.id IS '主键';  
COMMENT ON COLUMN Quote_Option.stockinfo_id IS '证券信息ID';
COMMENT ON COLUMN Quote_Option.stock_code IS '证券编码';
COMMENT ON COLUMN Quote_Option.exchange_id IS '上市场所ID(证券交易所)';
COMMENT ON COLUMN Quote_Option.quote_date IS '行情日期';
COMMENT ON COLUMN Quote_Option.prev_close IS '昨收盘价';
COMMENT ON COLUMN Quote_Option.last IS '最新价';
COMMENT ON COLUMN Quote_Option.open IS '开盘价';
COMMENT ON COLUMN Quote_Option.high IS '最高价';
COMMENT ON COLUMN Quote_Option.low IS '最低价';
COMMENT ON COLUMN Quote_Option.chg IS '涨跌幅';
COMMENT ON COLUMN Quote_Option.change IS '涨跌额';
COMMENT ON COLUMN Quote_Option.volume IS '成交量';
COMMENT ON COLUMN Quote_Option.turnover IS '成交额';
COMMENT ON COLUMN Quote_Option.committee_than IS '委比';
COMMENT ON COLUMN Quote_Option.quantity_relative IS '量比';
COMMENT ON COLUMN Quote_Option.turnover_rate IS '换手率';
COMMENT ON COLUMN Quote_Option.buy_volume_outside IS '买盘(外盘)';
COMMENT ON COLUMN Quote_Option.sell_volume_outside IS '卖盘(内盘)';
COMMENT ON COLUMN Quote_Option.position_volume IS '持仓量';
COMMENT ON COLUMN Quote_Option.position_daily_increase IS '日增仓';
COMMENT ON COLUMN Quote_Option.exercise_price IS '行权价';
COMMENT ON COLUMN Quote_Option.remaining_days IS '剩余日';
COMMENT ON COLUMN Quote_Option.bid IS '买一价';
COMMENT ON COLUMN Quote_Option.ask IS '卖一价';
COMMENT ON COLUMN Quote_Option.bid_size IS '买入量';
COMMENT ON COLUMN Quote_Option.ask_size IS '卖出量';
COMMENT ON COLUMN Quote_Option.create_by IS '创建人';
COMMENT ON COLUMN Quote_Option.create_by IS '创建时间';
COMMENT ON COLUMN Quote_Option.update_by IS '修改人';
COMMENT ON COLUMN Quote_Option.update_date IS '修改时间';
COMMENT ON TABLE Quote_Option IS '期权行情';

--外汇行情
--Quote_ForeignEx
declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Quote_ForeignEx');
    if num > 0 then
        execute immediate 'drop table Quote_ForeignEx';
    end if;
end;
/
CREATE TABLE Quote_ForeignEx
(  id                       NUMBER(20)      NOT NULL,
   stockinfo_id             NUMBER(20)      NOT NULL,
   stock_code               VARCHAR2(32),
   exchange_id              NUMBER(20)      NOT NULL, 
   quote_date               NUMBER(13)      NOT NULL,
   prev_close               NUMBER(12,6)    NOT NULL,
   last                     NUMBER(12,6)    NOT NULL,
   open                     NUMBER(12,6),
   high                     NUMBER(12,6),
   low                      NUMBER(12,6),
   chg                      NUMBER(12,6),
   change                   NUMBER(12,4),
   volume                   NUMBER(14,4),
   turnover                 NUMBER(14,4),
   committee_than           NUMBER(10,4),
   quantity_relative        NUMBER(10,4),
   turnover_rate            NUMBER(10,4),
   bid                      NUMBER(12,6),
   ask                      NUMBER(12,6),
   bid_size                 NUMBER(12),
   ask_size                 NUMBER(12),
   high_52week              NUMBER(12,6),
   low_52week               NUMBER(12,6),
   create_by                NUMBER(20),
   create_date 		        NUMBER(13),
   update_by                NUMBER(20),
   update_date              NUMBER(13),
   CONSTRAINT Quote_ForeignEx_PK PRIMARY KEY (id),
   CONSTRAINT Quote_ForeignEx_Unique UNIQUE (stockinfo_id, quote_date)
 );
COMMENT ON COLUMN Quote_ForeignEx.id IS '主键';  
COMMENT ON COLUMN Quote_ForeignEx.stockinfo_id IS '证券信息ID';
COMMENT ON COLUMN Quote_ForeignEx.stock_code IS '证券编码';
COMMENT ON COLUMN Quote_ForeignEx.exchange_id IS '上市场所ID(证券交易所)';
COMMENT ON COLUMN Quote_ForeignEx.quote_date IS '行情日期';
COMMENT ON COLUMN Quote_ForeignEx.prev_close IS '昨收盘价';
COMMENT ON COLUMN Quote_ForeignEx.last IS '最新价';
COMMENT ON COLUMN Quote_ForeignEx.open IS '开盘价';
COMMENT ON COLUMN Quote_ForeignEx.high IS '最高价';
COMMENT ON COLUMN Quote_ForeignEx.low IS '最低价';
COMMENT ON COLUMN Quote_ForeignEx.chg IS '涨跌幅';
COMMENT ON COLUMN Quote_ForeignEx.change IS '涨跌额';
COMMENT ON COLUMN Quote_ForeignEx.volume IS '成交量';
COMMENT ON COLUMN Quote_ForeignEx.turnover IS '成交额';
COMMENT ON COLUMN Quote_ForeignEx.committee_than IS '委比';
COMMENT ON COLUMN Quote_ForeignEx.quantity_relative IS '量比';
COMMENT ON COLUMN Quote_ForeignEx.turnover_rate IS '换手率';
COMMENT ON COLUMN Quote_ForeignEx.bid IS '买一价';
COMMENT ON COLUMN Quote_ForeignEx.ask IS '卖一价';
COMMENT ON COLUMN Quote_ForeignEx.bid_size IS '买入量';
COMMENT ON COLUMN Quote_ForeignEx.ask_size IS '卖出量';
COMMENT ON COLUMN Quote_ForeignEx.high_52week IS '52周最高价';
COMMENT ON COLUMN Quote_ForeignEx.low_52week IS '52周最低价';
COMMENT ON COLUMN Quote_ForeignEx.create_by IS '创建人';
COMMENT ON COLUMN Quote_ForeignEx.create_by IS '创建时间';
COMMENT ON COLUMN Quote_ForeignEx.update_by IS '修改人';
COMMENT ON COLUMN Quote_ForeignEx.update_date IS '修改时间';
COMMENT ON TABLE Quote_ForeignEx IS '外汇行情';

--基金行情
--Quote_Fund
declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Quote_Fund');
    if num > 0 then
        execute immediate 'drop table Quote_Fund';
    end if;
end;
/
CREATE TABLE Quote_Fund
(  id                       NUMBER(20)      NOT NULL,
   stockinfo_id             NUMBER(20)      NOT NULL,
   stock_code               VARCHAR2(32),
   exchange_id              NUMBER(20)      NOT NULL, 
   quote_date               NUMBER(13)      NOT NULL,
   prev_close               NUMBER(12,6)    NOT NULL,
   last                     NUMBER(12,6)    NOT NULL,
   open                     NUMBER(12,6),
   high                     NUMBER(12,6),
   low                      NUMBER(12,6),
   chg                      NUMBER(12,6),
   change                   NUMBER(12,4),
   volume                   NUMBER(14,4),
   turnover                 NUMBER(14,4),
   committee_than           NUMBER(10,4),
   quantity_relative        NUMBER(10,4),
   turnover_rate            NUMBER(10,4),
   bid                      NUMBER(12,6),
   ask                      NUMBER(12,6),
   bid_size                 NUMBER(12),
   ask_size                 NUMBER(12),
   cumulative_net_value     NUMBER(12,6),
   return_daily_rate        NUMBER(12,4),
   return_1week             NUMBER(12,4),
   return_1month            NUMBER(12,4),
   return_3month            NUMBER(12,4),
   return_6month            NUMBER(12,4),
   return_1year             NUMBER(12,4),
   return_2year             NUMBER(12,4),
   return_3year             NUMBER(12,4),
   return_yield_this_year   NUMBER(12,4),
   return_since_foundation  NUMBER(12,4),
   create_by                NUMBER(20),
   create_date 		        NUMBER(13),
   update_by                NUMBER(20),
   update_date              NUMBER(13),
   CONSTRAINT Quote_Fund_PK PRIMARY KEY (id),
   CONSTRAINT Quote_Fund_Unique UNIQUE (stockinfo_id, quote_date)
 );
COMMENT ON COLUMN Quote_Fund.id IS '主键';  
COMMENT ON COLUMN Quote_Fund.stockinfo_id IS '证券信息ID';
COMMENT ON COLUMN Quote_Fund.stock_code IS '证券编码';
COMMENT ON COLUMN Quote_Fund.exchange_id IS '上市场所ID(证券交易所)';
COMMENT ON COLUMN Quote_Fund.quote_date IS '行情日期';
COMMENT ON COLUMN Quote_Fund.prev_close IS '昨收盘价';
COMMENT ON COLUMN Quote_Fund.last IS '最新价(单位净值)';
COMMENT ON COLUMN Quote_Fund.open IS '开盘价';
COMMENT ON COLUMN Quote_Fund.high IS '最高价';
COMMENT ON COLUMN Quote_Fund.low IS '最低价';
COMMENT ON COLUMN Quote_Fund.chg IS '涨跌幅';
COMMENT ON COLUMN Quote_Fund.change IS '涨跌额';
COMMENT ON COLUMN Quote_Fund.volume IS '成交量';
COMMENT ON COLUMN Quote_Fund.turnover IS '成交额';
COMMENT ON COLUMN Quote_Fund.committee_than IS '委比';
COMMENT ON COLUMN Quote_Fund.quantity_relative IS '量比';
COMMENT ON COLUMN Quote_Fund.turnover_rate IS '换手率';
COMMENT ON COLUMN Quote_Fund.bid IS '买一价';
COMMENT ON COLUMN Quote_Fund.ask IS '卖一价';
COMMENT ON COLUMN Quote_Fund.bid_size IS '买入量';
COMMENT ON COLUMN Quote_Fund.ask_size IS '卖出量';
COMMENT ON COLUMN Quote_Fund.cumulative_net_value IS '累计净值';
COMMENT ON COLUMN Quote_Fund.return_daily_rate IS '日增长率';
COMMENT ON COLUMN Quote_Fund.return_1week IS '近一周收益率';
COMMENT ON COLUMN Quote_Fund.return_1month IS '近一月收益率';
COMMENT ON COLUMN Quote_Fund.return_3month IS '近三月收益率';
COMMENT ON COLUMN Quote_Fund.return_6month IS '近六月收益率';
COMMENT ON COLUMN Quote_Fund.return_1year IS '近一年收益率';
COMMENT ON COLUMN Quote_Fund.return_2year IS '近两年收益率';
COMMENT ON COLUMN Quote_Fund.return_3year IS '近三年收益率';
COMMENT ON COLUMN Quote_Fund.return_yield_this_year IS '今年以来收益率';
COMMENT ON COLUMN Quote_Fund.return_since_foundation IS '成立以来收益率';
COMMENT ON COLUMN Quote_Fund.create_by IS '创建人';
COMMENT ON COLUMN Quote_Fund.create_by IS '创建时间';
COMMENT ON COLUMN Quote_Fund.update_by IS '修改人';
COMMENT ON COLUMN Quote_Fund.update_date IS '修改时间';
COMMENT ON TABLE Quote_Fund IS '基金行情';

--权证行情
--Quote_Warrant
declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Quote_Warrant');
    if num > 0 then
        execute immediate 'drop table Quote_Warrant';
    end if;
end;
/
CREATE TABLE Quote_Warrant
(  id                       NUMBER(20)      NOT NULL,
   stockinfo_id             NUMBER(20)      NOT NULL,
   stock_code               VARCHAR2(32),
   exchange_id              NUMBER(20)      NOT NULL, 
   quote_date               NUMBER(13)      NOT NULL,
   prev_close               NUMBER(12,6),
   last                     NUMBER(12,6)    NOT NULL,
   open                     NUMBER(12,6),
   high                     NUMBER(12,6),
   low                      NUMBER(12,6),
   chg                      NUMBER(12,6),
   change                   NUMBER(12,4),
   volume                   NUMBER(14,4),
   turnover                 NUMBER(14,4),
   committee_than           NUMBER(10,4),
   quantity_relative        NUMBER(10,4),
   turnover_rate            NUMBER(10,4),
   exercise_price           NUMBER(12,6),
   bid                      NUMBER(12,6),
   ask                      NUMBER(12,6),
   bid_size                 NUMBER(12),
   ask_size                 NUMBER(12),
   create_by                NUMBER(20),
   create_date 		        NUMBER(13),
   update_by                NUMBER(20),
   update_date              NUMBER(13),
   CONSTRAINT Quote_Warrant_PK PRIMARY KEY (id),
   CONSTRAINT Quote_Warrant_Unique UNIQUE (stockinfo_id, quote_date)
 );
COMMENT ON COLUMN Quote_Warrant.id IS '主键';  
COMMENT ON COLUMN Quote_Warrant.stockinfo_id IS '证券信息ID';
COMMENT ON COLUMN Quote_Warrant.stock_code IS '证券编码';
COMMENT ON COLUMN Quote_Warrant.exchange_id IS '上市场所ID(证券交易所)';
COMMENT ON COLUMN Quote_Warrant.quote_date IS '行情日期';
COMMENT ON COLUMN Quote_Warrant.prev_close IS '昨收盘价';
COMMENT ON COLUMN Quote_Warrant.last IS '最新价';
COMMENT ON COLUMN Quote_Warrant.open IS '开盘价';
COMMENT ON COLUMN Quote_Warrant.high IS '最高价';
COMMENT ON COLUMN Quote_Warrant.low IS '最低价';
COMMENT ON COLUMN Quote_Warrant.chg IS '涨跌幅';
COMMENT ON COLUMN Quote_Warrant.change IS '涨跌额';
COMMENT ON COLUMN Quote_Warrant.volume IS '成交量';
COMMENT ON COLUMN Quote_Warrant.turnover IS '成交额';
COMMENT ON COLUMN Quote_Warrant.committee_than IS '委比';
COMMENT ON COLUMN Quote_Warrant.quantity_relative IS '量比';
COMMENT ON COLUMN Quote_Warrant.turnover_rate IS '换手率';
COMMENT ON COLUMN Quote_Warrant.exercise_price IS '行权价';
COMMENT ON COLUMN Quote_Warrant.bid IS '买一价';
COMMENT ON COLUMN Quote_Warrant.ask IS '卖一价';
COMMENT ON COLUMN Quote_Warrant.bid_size IS '买入量';
COMMENT ON COLUMN Quote_Warrant.ask_size IS '卖出量';
COMMENT ON COLUMN Quote_Warrant.create_by IS '创建人';
COMMENT ON COLUMN Quote_Warrant.create_date IS '创建时间';
COMMENT ON COLUMN Quote_Warrant.update_by IS '修改人';
COMMENT ON COLUMN Quote_Warrant.update_date IS '修改时间';
COMMENT ON TABLE Quote_Warrant IS '权证行情';

--行情报价
--ticker


--行情深度
--depth


--分时成交
--trade


--走势K线
--kline
