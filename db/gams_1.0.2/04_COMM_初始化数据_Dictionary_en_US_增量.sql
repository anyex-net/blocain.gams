-- 增量数据字典数据;

-- 快讯类型
DELETE from dictionary where id =200000070001;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000070001,'expressType','快讯类型','information','Information','en_US',40,200000000000,200000000000,200000000000,200000000000);
DELETE from dictionary where id =200000070002;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000070002,'expressType','快讯类型','recommend','Recommend','en_US',40,200000000000,200000000000,200000000000,200000000000);
DELETE from dictionary where id =200000070003;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000070003,'expressType','快讯类型','journalism','Journalism','en_US',40,200000000000,200000000000,200000000000,200000000000);

-- 帮助中心类型
DELETE from dictionary where id =200000080001;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000080001,'helpCenterType','帮助中心类型','accountOpenClass','Account Opening','en_US',42,200000000000,200000000000,200000000000,200000000000);
DELETE from dictionary where id =200000080002;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000080002,'helpCenterType','帮助中心类型','transactionClass','Transaction Issues','en_US',42,200000000000,200000000000,200000000000,200000000000);
DELETE from dictionary where id =200000080003;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000080003,'helpCenterType','帮助中心类型','capitalCategory','Funds Related Issues','en_US',42,200000000000,200000000000,200000000000,200000000000);
DELETE from dictionary where id =200000080004;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000080004,'helpCenterType','帮助中心类型','costCategory','Fees Related Issues','en_US',42,200000000000,200000000000,200000000000,200000000000);
DELETE from dictionary where id =200000080005;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000080005,'helpCenterType','帮助中心类型','operationClass','Operation Related Issues','en_US',42,200000000000,200000000000,200000000000,200000000000);
DELETE from dictionary where id =200000080006;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000080006,'helpCenterType','帮助中心类型','other','Others','en_US',42,200000000000,200000000000,200000000000,200000000000);


-- 帮助中心问题类型（一级）  帮助中心类型子节点联动  开户类
DELETE from dictionary where id =200000090001;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000090001,'accountOpenClass','帮助中心类型_子节点','Advantages of Guorui securities','Advantages Of MOF Securities','en_US',43,200000000000,200000000000,200000000000,200000000000);
DELETE from dictionary where id =200000090002;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000090002,'accountOpenClass','帮助中心类型_子节点','Account opening process','Process Of Account Opening','en_US',43,200000000000,200000000000,200000000000,200000000000);
DELETE from dictionary where id =200000090003;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000090003,'accountOpenClass','帮助中心类型_子节点','Account opening certificate','Documents','en_US',43,200000000000,200000000000,200000000000,200000000000);
DELETE from dictionary where id =200000090004;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000090004,'accountOpenClass','帮助中心类型_子节点','Opening up stocks','Open US Stocks','en_US',43,200000000000,200000000000,200000000000,200000000000);

-- 帮助中心问题类型（一级）  帮助中心类型子节点联动  交易类
DELETE from dictionary where id =200000091001;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000091001,'transactionClass','帮助中心类型_子节点','US stock trading rules','US Stock Trading Rules','en_US',43,200000000000,200000000000,200000000000,200000000000);
DELETE from dictionary where id =200000091002;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000091002,'transactionClass','帮助中心类型_子节点','US stock margin trading','US Stock Margin Trading','en_US',43,200000000000,200000000000,200000000000,200000000000);
DELETE from dictionary where id =200000091003;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000091003,'transactionClass','帮助中心类型_子节点','Hong Kong stock exchange rules','Hong Kong Stock Trading Rules','en_US',43,200000000000,200000000000,200000000000,200000000000);
DELETE from dictionary where id =200000091004;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000091004,'transactionClass','帮助中心类型_子节点','Hong Kong stock margin trading','Hong Kong Stock Margin Trading','en_US',43,200000000000,200000000000,200000000000,200000000000);
DELETE from dictionary where id =200000091005;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000091005,'transactionClass','帮助中心类型_子节点','Account type','Account Type','en_US',43,200000000000,200000000000,200000000000,200000000000);

-- 帮助中心问题类型（一级）  帮助中心类型子节点联动  资金类
DELETE from dictionary where id =200000092001;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000092001,'capitalCategory','帮助中心类型_子节点','Gold entry required','Guidelines For Fund Deposit','en_US',43,200000000000,200000000000,200000000000,200000000000);
DELETE from dictionary where id =200000092002;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000092002,'capitalCategory','帮助中心类型_子节点','Gold entry process','Process Of Fund Deposit','en_US',43,200000000000,200000000000,200000000000,200000000000);
DELETE from dictionary where id =200000092003;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000092003,'capitalCategory','帮助中心类型_子节点','Extract capital','Withdrawal Of Funds','en_US',43,200000000000,200000000000,200000000000,200000000000);
DELETE from dictionary where id =200000092004;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000092004,'capitalCategory','帮助中心类型_子节点','Currency Exchange','Currency Exchange','en_US',43,200000000000,200000000000,200000000000,200000000000);

-- 帮助中心问题类型（一级）  帮助中心类型子节点联动  费用类
DELETE from dictionary where id =200000093001;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000093001,'costCategory','帮助中心类型_子节点','US stock charge','US Stocks','en_US',43,200000000000,200000000000,200000000000,200000000000);
DELETE from dictionary where id =200000093002;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000093002,'costCategory','帮助中心类型_子节点','Hong Kong stock charge','Hong Kong Stocks','en_US',43,200000000000,200000000000,200000000000,200000000000);
DELETE from dictionary where id =200000093003;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000093003,'costCategory','帮助中心类型_子节点','Financing interest rate','Financing Interest Rate','en_US',43,200000000000,200000000000,200000000000,200000000000);
DELETE from dictionary where id =200000093004;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000093004,'costCategory','帮助中心类型_子节点','Commission package','Commission Package','en_US',43,200000000000,200000000000,200000000000,200000000000);

-- 帮助中心问题类型（一级）  帮助中心类型子节点联动  操作类
DELETE from dictionary where id =200000094001;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000094001,'operationClass','帮助中心类型_子节点','Software usage','How To Use Softwares','en_US',43,200000000000,200000000000,200000000000,200000000000);
DELETE from dictionary where id =200000094002;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000094002,'operationClass','帮助中心类型_子节点','Financing securities loan operation','Margin Trading','en_US',43,200000000000,200000000000,200000000000,200000000000);

-- 帮助中心问题类型（一级）  帮助中心类型子节点联动  其他
DELETE from dictionary where id =200000095001;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000095001,'other','帮助中心类型_子节点','Risk control status','Risk Control Status','en_US',43,200000000000,200000000000,200000000000,200000000000);

-- 消息公告类型
DELETE from dictionary where id =200000096001;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000096001,'newsAnnounceType','消息公告类型','announce','announcement','en_US',44,200000000000,200000000000,200000000000,200000000000);

-- 客户端类型
DELETE from dictionary where id =200000097002;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000097002,'clientType','客户端类型','OfficialWebsite','OfficialWebsite','en_US',45,200000000000,200000000000,200000000000,200000000000);

-- 修改其他官员
DELETE from dictionary where id =200000050004;
insert into Dictionary (ID,DICT_CODE,DICT_NAME,DICT_VALUE,DICT_DEST,LANG,SORT_NUM,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE) 
values (200000050004,'otherOfficer','其他官员','Chief Legal Officer/General Counsel or equivalent','Chief Legal Officer/General Counsel or equivalent','en_US',40,200000000000,200000000000,200000000000,200000000000);



commit;
