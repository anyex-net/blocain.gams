-- 初始化参数数据;
TRUNCATE table Parameter;

insert into Parameter (id, system_name, parameter_name, describe, division, type, value_bound, value, remark, create_by, create_date, update_by, update_date)
values (200000000001, 'GAMS', 'gamsUserName', 'gams平台客户名', '系统类', '文本', null, 'MOF Securities Co Ltd', 'gams平台客户名', 200000000000, 1501467844534, 200000000000, 1501467844534);

commit;
