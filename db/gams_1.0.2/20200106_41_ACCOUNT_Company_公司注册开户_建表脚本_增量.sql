-- 公司 w8ben 表
alter table account_co_wben_contract modify branch_address VARCHAR2(512);
alter table account_co_wben_contract modify branch_city VARCHAR2(128);

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_co_crs_entity_contr');
  if num > 0 then
    execute immediate 'drop table account_co_crs_entity_contr';
  end if;
end;
/
-- 公司crs实体（CRS-E）控权人信息
CREATE TABLE account_co_crs_entity_contr(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  last_name VARCHAR2(50) NOT NULL,
  first_name VARCHAR2(32) NOT NULL,
  co_crs_entity_id NUMBER(20),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_co_crs_entity_contr_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_co_crs_entity_contr.id IS 'id';
COMMENT ON COLUMN account_co_crs_entity_contr.account_id IS '账户ID';
COMMENT ON COLUMN account_co_crs_entity_contr.last_name IS '姓';
COMMENT ON COLUMN account_co_crs_entity_contr.first_name IS '名';
COMMENT ON COLUMN account_co_crs_entity_contr.co_crs_entity_id IS 'CRS-E的id';
COMMENT ON COLUMN account_co_crs_entity_contr.created_by IS '创建人';
COMMENT ON COLUMN account_co_crs_entity_contr.created_date IS '创建时间';
COMMENT ON COLUMN account_co_crs_entity_contr.updated_by IS '更新人';
COMMENT ON COLUMN account_co_crs_entity_contr.updated_date IS '更新时间';
COMMENT ON TABLE account_co_crs_entity_contr IS '公司crs实体（CRS-E）控权人信息';


--删除字段 公司 CRS-E
alter table account_co_crs_entity drop (SELF_CERTIFICATION);
  
-- 新增字段 公司 CRS-E   
alter table account_co_crs_entity add (crs_cp_status_id NUMBER(20)) ;	-- 正确
comment on column account_co_crs_entity.crs_cp_status_id
is '记录crs_cp的控权人状态';

-- 新增字段 公司CRS-CP 
alter table account_co_crs_control add (co_crs_entity_contr_id NUMBER(20)) ;	-- 正确
comment on column account_co_crs_control.co_crs_entity_contr_id
is 'crs实体（CRS-E）控权人(控权人姓名)信息ID';
  
-- 新增字段  CRS-CP-NUM  
alter table account_co_crs_control_num add (co_crs_entity_contr_id NUMBER(20)) ;	-- 正确
comment on column account_co_crs_control_num.co_crs_entity_contr_id
is 'crs实体（CRS-E）控权人(控权人姓名)信息ID';

