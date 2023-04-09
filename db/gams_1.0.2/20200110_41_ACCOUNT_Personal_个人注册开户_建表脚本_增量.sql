
-- 修改个人生日类型为字符串
-- 1. 把原字段换个名字，BIRTHDAY改为BIRTH
alter table account_per_info rename column BIRTHDAY to BIRTH;
-- 2. 在表中添加一个原字段名字 BIRTHDAY 
alter table account_per_info add BIRTHDAY varchar2(32);
comment on column account_per_info.BIRTHDAY
is '生日';  
-- 3. 把备份的BIRTH字段内容添加到新建字段BIRTHDAY中来
update account_per_info set BIRTHDAY = BIRTH;
commit;
-- 4.把备份字段BIRTH删除
alter table account_per_info drop (BIRTH);



-- 修改个人W8其他出生日期为字符串
alter table account_per_wben_contract rename column OTHER_BIRTHDAY to BIRTH;
-- 2. 在表中添加一个原字段名字 OTHER_BIRTHDAY 
alter table account_per_wben_contract add OTHER_BIRTHDAY varchar2(32);
comment on column account_per_wben_contract.OTHER_BIRTHDAY
is '其他出生日期';  
-- 3. 把备份的BIRTH字段内容添加到新建字段OTHER_BIRTHDAY中来
update account_per_wben_contract set OTHER_BIRTHDAY = BIRTH;
commit;
-- 4.把备份字段BIRTH删除
alter table account_per_wben_contract drop (BIRTH);