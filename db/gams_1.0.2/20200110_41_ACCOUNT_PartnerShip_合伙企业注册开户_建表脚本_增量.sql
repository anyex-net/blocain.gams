
-- 修改合伙企业个人信息生日类型为字符串
-- 1. 把原字段换个名字，BIRTHDAY改为BIRTH
alter table account_par_personal rename column BIRTHDAY to BIRTH;
-- 2. 在表中添加一个原字段名字 BIRTHDAY 
alter table account_par_personal add BIRTHDAY varchar2(32);
comment on column account_par_personal.BIRTHDAY
is '生日';  
-- 3. 把备份的BIRTH字段内容添加到新建字段BIRTHDAY中来
update account_par_personal set BIRTHDAY = BIRTH;
commit;
-- 4.把备份字段BIRTH删除
alter table account_par_personal drop (BIRTH);
------------------------------------------------------------
-- 修改合伙企业CRS-CP信息生日类型为字符串
-- 1. 把原字段换个名字，BIRTHDAY改为BIRTH
alter table account_par_crs_control rename column BIRTHDAY to BIRTH;
-- 2. 在表中添加一个原字段名字 BIRTHDAY 
alter table account_par_crs_control add BIRTHDAY varchar2(32);
comment on column account_par_crs_control.BIRTHDAY
is '出生日期';  
-- 3. 把备份的BIRTH字段内容添加到新建字段BIRTHDAY中来
update account_par_crs_control set BIRTHDAY = BIRTH;
commit;
-- 4.把备份字段BIRTH删除
alter table account_par_crs_control drop (BIRTH);