delete from RESOURCES  a where a.id >= 210000000001;

-- ----------------------------
-- 基础管理-资源菜单初始化数据
-- ----------------------------
INSERT INTO RESOURCES VALUES ('210000000001', '0', 'common:index', '基础管理', NULL, '0', 'el-icon-menu', '#', '1', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('210000000002', '210000000001', 'common:dictionary:index', '数据字典', NULL, '0', 'el-icon-menu',  '/common/dictionary', '6','200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('210000000003', '210000000002', 'common:dictionary:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('210000000004', '210000000002', 'common:dictionary:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('210000000005', '210000000001', 'common:parameter:index', '系统参数', NULL, '0', 'el-icon-menu', '/common/parameter', '7', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('210000000006', '210000000005', 'common:parameter:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('210000000007', '210000000005', 'common:parameter:data', '数据权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('210000000008', '210000000001', 'common:region:index', '区域代码', NULL, '0', 'el-icon-menu', '/common/region', '8', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('210000000009', '210000000008', 'common:region:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('210000000010', '210000000008', 'common:region:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('210000000011', '210000000001', 'common:template:index', '消息模板', NULL, '0', 'el-icon-menu', '/common/template', '9', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('210000000012', '210000000011', 'common:template:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('210000000013', '210000000011', 'common:template:data', '数据权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');

-- ----------------------------
-- 证券管理-资源菜单初始化数据
-- ----------------------------
INSERT INTO RESOURCES VALUES ('310000000001', '0', 'stock:index', '证券管理', NULL, '0', 'el-icon-menu', '#', '1', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('310000000002', '310000000001', 'stock:stockExchange:index', '交易市场', NULL, '0', 'el-icon-menu', '/stock/stockExchange', '2', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('310000000003', '310000000002', 'stock:stockExchange:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('310000000004', '310000000002', 'stock:stockExchange:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('310000000005', '310000000001', 'stock:stockInfo:index', '证券信息', NULL, '0', 'el-icon-menu', '/stock/stockInfo', '3', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('310000000006', '310000000005', 'stock:stockInfo:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('310000000007', '310000000005', 'stock:stockInfo:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('310000000008', '310000000001', 'stock:stockWarrant:index', '权证信息', NULL, '0', 'el-icon-menu', '/stock/stockWarrant', '4', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('310000000009', '310000000008', 'stock:stockWarrant:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('310000000010', '310000000008', 'stock:stockWarrant:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('310000000011', '310000000001', 'stock:stockWmp:index', '理财产品', NULL, '0', 'el-icon-menu', '/stock/stockWmp', '5', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('310000000012', '310000000011', 'stock:stockWmp:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('310000000013', '310000000011', 'stock:stockWmp:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');

-- ----------------------------
-- 证券行情-资源菜单初始化数据
-- ----------------------------
INSERT INTO RESOURCES VALUES ('320000000001', '0', 'quote:index', '证券行情', NULL, '0', 'el-icon-menu', '#', '1', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('320000000002', '320000000001', 'quote:quoteStock:index', '股票行情', NULL, '0', 'el-icon-menu', '/quote/quoteStock', '2', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('320000000003', '320000000002', 'quote:quoteStock:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('320000000004', '320000000002', 'quote:quoteStock:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('320000000005', '320000000001', 'quote:quoteBond:index', '债券行情', NULL, '0', 'el-icon-menu', '/quote/quoteBond', '3', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('320000000006', '320000000005', 'quote:quoteBond:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('320000000007', '320000000005', 'quote:quoteBond:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('320000000008', '320000000001', 'quote:quoteFutures:index', '期货行情', NULL, '0', 'el-icon-menu', '/quote/quoteFutures', '4', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('320000000009', '320000000008', 'quote:quoteFutures:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('320000000010', '320000000008', 'quote:quoteFutures:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('320000000011', '320000000001', 'quote:quoteOption:index', '期权行情', NULL, '0', 'el-icon-menu', '/quote/quoteOption', '5', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('320000000012', '320000000011', 'quote:quoteOption:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('320000000013', '320000000011', 'quote:quoteOption:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('320000000014', '320000000001', 'quote:quoteForeignEx:index', '外汇行情', NULL, '0', 'el-icon-menu', '/quote/quoteForeignEx', '6', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('320000000015', '320000000014', 'quote:quoteForeignEx:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('320000000016', '320000000014', 'quote:quoteForeignEx:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('320000000017', '320000000001', 'quote:quoteFund:index', '基金行情', NULL, '0', 'el-icon-menu', '/quote/quoteFund', '7', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('320000000018', '320000000017', 'quote:quoteFund:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('320000000019', '320000000017', 'quote:quoteFund:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('320000000020', '320000000001', 'quote:quoteWarrant:index', '权证行情', NULL, '0', 'el-icon-menu', '/quote/quoteWarrant', '8', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('320000000021', '320000000020', 'quote:quoteWarrant:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('320000000022', '320000000020', 'quote:quoteWarrant:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
-- ----------------------------
-- 证券资讯-资源菜单初始化数据
-- ----------------------------
/*
INSERT INTO RESOURCES VALUES ('330000000001', '0', 'news:index', '证券资讯', NULL, '0', 'el-icon-menu', '#', '1', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('330000000002', '330000000001', 'news:newsStockConfig:index', '资讯股票配置', NULL, '0', 'el-icon-menu', '/news/newsStockConfig', '1', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('330000000003', '330000000002', 'news:newsStockConfig:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('330000000004', '330000000002', 'news:newsStockConfig:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('330000000005', '330000000001', 'news:newsForeignExConfig:index', '资讯外汇配置', NULL, '0', 'el-icon-menu', '/news/newsForeignExConfig', '2', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('330000000006', '330000000005', 'news:newsForeignExConfig:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('330000000007', '330000000005', 'news:newsForeignExConfig:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('330000000008', '330000000001', 'news:newsFuturesConfig:index', '资讯期货配置', NULL, '0', 'el-icon-menu', '/news/newsFuturesConfig', '3', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('330000000009', '330000000008', 'news:newsFuturesConfig:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('330000000010', '330000000008', 'news:newsFuturesConfig:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
*/

-- ----------------------------
-- 账户管理-资源菜单初始化数据
-- ----------------------------
INSERT INTO RESOURCES VALUES ('410000000001', '0', 'account:index', '账户管理', NULL, '0', 'el-icon-menu', '#', '1', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('410000000002', '410000000001', 'account:registerOpen:index', '开户管理', NULL, '0', 'el-icon-menu', '/account/registerOpen', '2', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('410000000003', '410000000002', 'account:registerOpen:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('410000000004', '410000000002', 'account:registerOpen:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('410000000005', '410000000001', 'account:account:index', '账户管理', NULL, '0', 'el-icon-menu', '/account/account', '3', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('410000000006', '410000000005', 'account:account:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('410000000007', '410000000005', 'account:account:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');

-- ----------------------------
-- IB数据管理-资源菜单初始化数据
-- ----------------------------
INSERT INTO RESOURCES VALUES ('420000000001', '0', 'ib:index', 'IB数据管理', NULL, '0', 'el-icon-menu', '#', '1', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('420000000002', '420000000001', 'ib:ibAccountFamily:index', 'IB账户家族', NULL, '0', 'el-icon-menu', '/ib/ibAccountFamily', '2', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('420000000003', '420000000002', 'ib:ibAccountFamily:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('420000000004', '420000000002', 'ib:ibAccountFamily:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('420000000005', '420000000001', 'ib:ibAccountBalanceMargin:index', 'IB账户资金', NULL, '0', 'el-icon-menu', '/ib/ibAccountBalanceMargin', '3', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('420000000006', '420000000005', 'ib:ibAccountBalanceMargin:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('420000000007', '420000000005', 'ib:ibAccountBalanceMargin:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('420000000008', '420000000001', 'ib:ibAccountMarketValue:index', 'IB账户市值', NULL, '0', 'el-icon-menu', '/ib/ibAccountMarketValue', '4', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('420000000009', '420000000008', 'ib:ibAccountMarketValue:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('420000000010', '420000000008', 'ib:ibAccountMarketValue:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('420000000011', '420000000001', 'ib:ibAccountDailyPnL:index', 'IB当日盈亏', NULL, '0', 'el-icon-menu', '/ib/ibAccountDailyPnL', '5', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('420000000012', '420000000011', 'ib:ibAccountDailyPnL:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('420000000013', '420000000011', 'ib:ibAccountDailyPnL:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('420000000014', '420000000001', 'ib:ibAccountPosition:index', 'IB账户持仓', NULL, '0', 'el-icon-menu', '/ib/ibAccountPosition', '6', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('420000000015', '420000000014', 'ib:ibAccountPosition:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('420000000016', '420000000014', 'ib:ibAccountPosition:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('420000000017', '420000000001', 'ib:ibAccountTrade:index', 'IB交易流水', NULL, '0', 'el-icon-menu', '/ib/ibAccountTrade', '7', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('420000000018', '420000000017', 'ib:ibAccountTrade:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('420000000019', '420000000017', 'ib:ibAccountTrade:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('420000000020', '420000000001', 'ib:ibAccountMarketValueHis:index', 'IB账户市值历史', NULL, '0', 'el-icon-menu', '/ib/ibAccountMarketValueHis', '8', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('420000000021', '420000000020', 'ib:ibAccountMarketValueHis:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('420000000022', '420000000020', 'ib:ibAccountMarketValueHis:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');

-- ----------------------------
-- 资金管理-资源菜单初始化数据
-- ----------------------------
INSERT INTO RESOURCES VALUES ('510000000001', '0', 'cashManager:index', '资金管理', NULL, '0', 'el-icon-menu', '#', '1', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('510000000002', '510000000001', 'asset:assetDeposit:index', '充值入金', NULL, '0', 'el-icon-menu', '/asset/assetDeposit', '2', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('510000000003', '510000000002', 'asset:assetDeposit:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('510000000004', '510000000002', 'asset:assetDeposit:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('510000000005', '510000000001', 'asset:assetWithdraw:index', '提现出金', NULL, '0', 'el-icon-menu', '/asset/assetWithdraw', '3', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('510000000006', '510000000005', 'asset:assetWithdraw:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('510000000007', '510000000005', 'asset:assetWithdraw:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
-- ----------------------------
-- 资产管理-资源菜单初始化数据
-- ----------------------------
/*
INSERT INTO RESOURCES VALUES ('520000000001', '0', 'assetManager:index', '资产管理', NULL, '0', 'el-icon-menu', '#', '1', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('520000000002', '520000000001', 'asset:assetPosition:index', '资产持仓', NULL, '0', 'el-icon-menu', '/asset/assetPosition', '2', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('520000000003', '520000000002', 'asset:assetPosition:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('520000000004', '520000000002', 'asset:assetPosition:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('520000000005', '520000000001', 'asset:assetCurrent:index', '资产流水', NULL, '0', 'el-icon-menu', '/asset/assetCurrent', '3', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('520000000006', '520000000005', 'asset:assetCurrent:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL,'200000000000',  '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('520000000007', '520000000005', 'asset:assetCurrent:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
*/

-- ----------------------------
-- 交易管理-资源菜单初始化数据
-- ----------------------------
/*
INSERT INTO RESOURCES VALUES ('610000000001', '0', 'trade:index', '交易管理', NULL, '0', 'el-icon-menu', '#', '1', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('610000000002', '610000000001', 'trade:order:index', '订单管理', NULL, '0', 'el-icon-menu', '/trade/order', '2', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('610000000003', '610000000002', 'trade:order:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('610000000004', '610000000002', 'trade:order:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('610000000005', '610000000001', 'trade:trade:index', '成交管理', NULL, '0', 'el-icon-menu', '/trade/trade', '3', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('610000000006', '610000000005', 'trade:trade:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('610000000007', '610000000005', 'trade:trade:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
*/

-- ----------------------------
-- 交易通道-资源菜单初始化数据
-- ----------------------------
/*
INSERT INTO RESOURCES VALUES ('620000000001', '0', 'tradeChannel:index', '交易通道', NULL, '0', 'el-icon-menu', '#', '1', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('620000000002', '620000000001', 'tradeChannel:tradeChannelConfig:index', '通道配置管理', NULL, '0', 'el-icon-menu', '/tradeChannel/tradeChannelConfig', '2', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('620000000003', '620000000002', 'tradeChannel:tradeChannelConfig:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('620000000004', '620000000002', 'tradeChannel:tradeChannelConfig:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
*/

-- ----------------------------
-- 风控管理-资源菜单初始化数据
-- ----------------------------
/*
INSERT INTO RESOURCES VALUES ('710000000001', '0', 'risk:index', '风控管理', NULL, '0', 'el-icon-menu', '#', '1', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
*/

-- ----------------------------
-- 运营管理-资源菜单初始化数据
-- ----------------------------
INSERT INTO RESOURCES VALUES ('810000000001', '0', 'operation:index', '运营管理', NULL, '0', 'el-icon-menu', '#', '1', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('810000000002', '810000000001', 'common:banner:index', 'Banner管理', NULL, '0', 'el-icon-menu', '/common/banner', '2', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('810000000003', '810000000002', 'common:banner:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('810000000004', '810000000002', 'common:banner:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('810000000005', '810000000001', 'common:express:index', '快讯管理', NULL, '0', 'el-icon-menu', '/common/express', '3', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('810000000006', '810000000005', 'common:express:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('810000000007', '810000000005', 'common:express:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('810000000008', '810000000001', 'stock:stockHotConfig:index', '热门证券', NULL, '0', 'el-icon-menu', '/stock/stockHotConfig', '4', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('810000000009', '810000000008', 'stock:stockHotConfig:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('810000000010', '810000000008', 'stock:stockHotConfig:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');

-- ----------------------------
-- 综合查询-资源菜单初始化数据
-- ----------------------------
INSERT INTO RESOURCES VALUES ('910000000001', '0', 'query:index', '综合查询', NULL, '0', 'el-icon-menu', '#', '1', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('910000000002', '910000000001', 'query:userLog:index', '用户日志', NULL, '0', 'el-icon-menu', '/query/userLog', '2', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('910000000003', '910000000002', 'query:userLog:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('910000000004', '910000000002', 'query:userLog:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('910000000005', '910000000001', 'query:msgLog:index', '消息日志', NULL, '0', 'el-icon-menu', '/query/msgLog', '3', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('910000000006', '910000000005', 'query:msgLog:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('910000000007', '910000000005', 'query:msgLog:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('910000000008', '910000000001', 'query:accountLog:index', '账户日志', NULL, '0', 'el-icon-menu', '/query/accountLog', '4', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('910000000009', '910000000008', 'query:accountLog:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('910000000010', '910000000008', 'query:accountLog:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');

-- ----------------------------
-- 平台总账-资源菜单初始化数据
-- ----------------------------
/*
INSERT INTO RESOURCES VALUES ('990000000001', '0', 'platform:index', '平台总账', NULL, '0', 'el-icon-menu', '#', '1', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('990000000002', '990000000001', 'platform:assetPosition:index', '平台持仓管理', NULL, '0', 'el-icon-menu', '/platform/assetPosition', '2', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('990000000003', '990000000002', 'platform:assetPosition:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('990000000004', '990000000002', 'platform:assetPosition:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('990000000005', '990000000001', 'platform:assetCurrent:index', '平台流水管理', NULL, '0', 'el-icon-menu', '/platform/assetCurrent', '3', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('990000000006', '990000000005', 'platform:assetCurrent:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('990000000007', '990000000005', 'platform:assetCurrent:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('990000000008', '990000000001', 'platform:order:index', '平台订单管理', NULL, '0', 'el-icon-menu', '/platform/order', '4', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('990000000009', '990000000008', 'platform:order:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('990000000010', '990000000008', 'platform:order:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('990000000011', '990000000001', 'platform:trade:index', '平台成交管理', NULL, '0', 'el-icon-menu', '/platform/trade', '5', '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('990000000012', '990000000011', 'platform:trade:operator', '操作权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
INSERT INTO RESOURCES VALUES ('990000000013', '990000000011', 'platform:trade:data', '查询权限', NULL, '1', 'el-icon-menu', '#', NULL, '200000000000', '1501467844534', '200000000000', '1501467844534', '0');
*/

COMMIT;
