declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_institutional');
  if num > 0 then
    execute immediate 'drop table account_par_institutional';
  end if;
end;
/
--合伙企业机构信息
CREATE TABLE account_par_institutional(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  institutional_name VARCHAR2(1024) NOT NULL,
  bsiness_description VARCHAR2(3072),
  business_name VARCHAR2(1024),
  website_url VARCHAR2(1024) NOT NULL,
  istitutional_nature VARCHAR2(3072) NOT NULL,
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_institutional_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_institutional.id IS 'id';
COMMENT ON COLUMN account_par_institutional.account_id IS '账户ID';
COMMENT ON COLUMN account_par_institutional.institutional_name IS '机构名称';
COMMENT ON COLUMN account_par_institutional.bsiness_description IS '业务描述';
COMMENT ON COLUMN account_par_institutional.business_name IS '商业名称';
COMMENT ON COLUMN account_par_institutional.website_url IS '网址';
COMMENT ON COLUMN account_par_institutional.istitutional_nature IS '机构性质(见数据字典机构性质)';
COMMENT ON COLUMN account_par_institutional.created_by IS '创建人';
COMMENT ON COLUMN account_par_institutional.created_date IS '创建时间';
COMMENT ON COLUMN account_par_institutional.updated_by IS '更新人';
COMMENT ON COLUMN account_par_institutional.updated_date IS '更新时间';
COMMENT ON TABLE account_par_institutional IS '合伙企业机构信息';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_investment_account');
  if num > 0 then
    execute immediate 'drop table account_par_investment_account';
  end if;
end;
/
--合伙企业一体化投资管理账户
CREATE TABLE account_par_investment_account(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  is_stock_income_growth_plan NUMBER(1),
  account_name VARCHAR2(128) NOT NULL,
  signature_date NUMBER(13) NOT NULL,
  signature VARCHAR2(128) NOT NULL,
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_investment_acc_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_investment_account.id IS 'id';
COMMENT ON COLUMN account_par_investment_account.account_id IS '账户ID';
COMMENT ON COLUMN account_par_investment_account.is_stock_income_growth_plan IS '股票收益增长计划 1是true 0是false';
COMMENT ON COLUMN account_par_investment_account.account_name IS '账户名';
COMMENT ON COLUMN account_par_investment_account.signature_date IS '日期';
COMMENT ON COLUMN account_par_investment_account.signature IS '签名';
COMMENT ON COLUMN account_par_investment_account.created_by IS '创建人';
COMMENT ON COLUMN account_par_investment_account.created_date IS '创建时间';
COMMENT ON COLUMN account_par_investment_account.updated_by IS '更新人';
COMMENT ON COLUMN account_par_investment_account.updated_date IS '更新时间';
COMMENT ON TABLE account_par_investment_account IS '合伙企业一体化投资管理账户';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_account_portfolio');
  if num > 0 then
    execute immediate 'drop table account_par_account_portfolio';
  end if;
end;
/
--合伙企业账户组合信息
CREATE TABLE account_par_account_portfolio(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  account_portfolio_type VARCHAR2(32) NOT NULL,
  base_currency VARCHAR2(32) NOT NULL,
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_account_port_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_account_portfolio.id IS 'id';
COMMENT ON COLUMN account_par_account_portfolio.account_id IS '账户ID';
COMMENT ON COLUMN account_par_account_portfolio.account_portfolio_type IS '账户投资组合类型(见数据字典账户投资组合类型)';
COMMENT ON COLUMN account_par_account_portfolio.base_currency IS '基础货币(见数据字典基础货币)';
COMMENT ON COLUMN account_par_account_portfolio.created_by IS '创建人';
COMMENT ON COLUMN account_par_account_portfolio.created_date IS '创建时间';
COMMENT ON COLUMN account_par_account_portfolio.updated_by IS '更新人';
COMMENT ON COLUMN account_par_account_portfolio.updated_date IS '更新时间';
COMMENT ON TABLE account_par_account_portfolio IS '合伙企业账户组合信息';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_address');
  if num > 0 then
    execute immediate 'drop table account_par_address';
  end if;
end;
/
--合伙企业地址信息
CREATE TABLE account_par_address(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  par_personal_id NUMBER(20) NOT NULL,
  country VARCHAR2(32) NOT NULL,
  address VARCHAR2(128) NOT NULL,
  city VARCHAR2(128) NOT NULL,
  rovince VARCHAR2(128) NOT NULL,
  zip_code VARCHAR2(32) NOT NULL,
  address_type VARCHAR2(32) NOT NULL,
  other_country VARCHAR2(32),
  other_birth_country VARCHAR2(32),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_address_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_address.id IS 'id';
COMMENT ON COLUMN account_par_address.account_id IS '账户ID';
COMMENT ON COLUMN account_par_address.par_personal_id IS '合伙企业个人信息ID';
COMMENT ON COLUMN account_par_address.country IS '国家(见数据字典国家类型)';
COMMENT ON COLUMN account_par_address.address IS '地址';
COMMENT ON COLUMN account_par_address.city IS '城市';
COMMENT ON COLUMN account_par_address.rovince IS '省';
COMMENT ON COLUMN account_par_address.zip_code IS '邮编';
COMMENT ON COLUMN account_par_address.address_type IS '居住，邮寄,公司(见数据字典地址类型)';
COMMENT ON COLUMN account_par_address.other_country IS '其他国家(是否和上方选的国家不同)(见数据字典国家类型)';
COMMENT ON COLUMN account_par_address.other_birth_country IS '其他出生国家(是否和上方选的国家不同)(见数据字典国家类型)';
COMMENT ON COLUMN account_par_address.created_by IS '创建人';
COMMENT ON COLUMN account_par_address.created_date IS '创建时间';
COMMENT ON COLUMN account_par_address.updated_by IS '更新人';
COMMENT ON COLUMN account_par_address.updated_date IS '更新时间';
COMMENT ON TABLE account_par_address IS '合伙企业地址信息';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_agreement');
  if num > 0 then
    execute immediate 'drop table account_par_agreement';
  end if;
end;
/
--合伙企业协议纰漏信息
CREATE TABLE account_par_agreement(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  agreement_number VARCHAR2(256),
  account_name VARCHAR2(32) NOT NULL,
  signature VARCHAR2(128) NOT NULL,
  signature_time NUMBER(13),
  status NUMBER(1) NOT NULL,
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_agreement_PK PRIMARY KEY (id),
  CONSTRAINT account_par_agreement_Unique UNIQUE (id, account_name)
);
COMMENT ON COLUMN account_par_agreement.id IS 'id';
COMMENT ON COLUMN account_par_agreement.account_id IS '账户ID';
COMMENT ON COLUMN account_par_agreement.agreement_number IS '协议文件编号(见共同协议表)';
COMMENT ON COLUMN account_par_agreement.account_name IS '账户名';
COMMENT ON COLUMN account_par_agreement.signature IS '签名';
COMMENT ON COLUMN account_par_agreement.signature_time IS '签名时间';
COMMENT ON COLUMN account_par_agreement.status IS '状态 1是true 0是false';
COMMENT ON COLUMN account_par_agreement.created_by IS '创建人';
COMMENT ON COLUMN account_par_agreement.created_date IS '创建时间';
COMMENT ON COLUMN account_par_agreement.updated_by IS '更新人';
COMMENT ON COLUMN account_par_agreement.updated_date IS '更新时间';
COMMENT ON TABLE account_par_agreement IS '合伙企业协议纰漏信息';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_asset');
  if num > 0 then
    execute immediate 'drop table account_par_asset';
  end if;
end;
/
--合伙企业资产信息
CREATE TABLE account_par_asset(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  anual_net_income VARCHAR2(32) NOT NULL,
  net_asset_value VARCHAR2(32) NOT NULL,
  net_current_assets VARCHAR2(32) NOT NULL,
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_asset_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_asset.id IS 'id';
COMMENT ON COLUMN account_par_asset.account_id IS '账户ID';
COMMENT ON COLUMN account_par_asset.anual_net_income IS '年度净收入(见数据字典年度净收入)';
COMMENT ON COLUMN account_par_asset.net_asset_value IS '资产净值（见数据字典资产净值）';
COMMENT ON COLUMN account_par_asset.net_current_assets IS '流动资产净值（见数据字典资产净值）';
COMMENT ON COLUMN account_par_asset.created_by IS '创建人';
COMMENT ON COLUMN account_par_asset.created_date IS '创建时间';
COMMENT ON COLUMN account_par_asset.updated_by IS '更新人';
COMMENT ON COLUMN account_par_asset.updated_date IS '更新时间';
COMMENT ON TABLE account_par_asset IS '合伙企业资产信息';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_contact');
  if num > 0 then
    execute immediate 'drop table account_par_contact';
  end if;
end;
/
--合伙企业联系信息
CREATE TABLE account_par_contact(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  par_personal_id NUMBER(20) NOT NULL,
  country VARCHAR2(32) NOT NULL,
  contact_number_type VARCHAR2(32) NOT NULL,
  contact_number VARCHAR2(32) NOT NULL,
  founding_country VARCHAR2(32),
  status NUMBER(1),
  exchange VARCHAR2(128),
  code VARCHAR2(128),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT aaccount_par_contact_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_contact.id IS 'id';
COMMENT ON COLUMN account_par_contact.account_id IS '账户ID';
COMMENT ON COLUMN account_par_contact.par_personal_id IS '合伙企业个人信息ID';
COMMENT ON COLUMN account_par_contact.country IS '国家(见数据字典国家类型)';
COMMENT ON COLUMN account_par_contact.contact_number_type IS '号码类别 手机，电话，传真，家庭，商务(见字典电话号码类型)';
COMMENT ON COLUMN account_par_contact.contact_number IS '联系号码';
COMMENT ON COLUMN account_par_contact.founding_country IS '成立国家（见区域表。法人股东需要）';
COMMENT ON COLUMN account_par_contact.status IS '实体是否在监管交易所公开上市交易（法人股东需要）';
COMMENT ON COLUMN account_par_contact.exchange IS '交易所（法人股东需要）';
COMMENT ON COLUMN account_par_contact.code IS '代码（法人股东需要）';
COMMENT ON COLUMN account_par_contact.created_by IS '创建人';
COMMENT ON COLUMN account_par_contact.created_date IS '创建时间';
COMMENT ON COLUMN account_par_contact.updated_by IS '更新人';
COMMENT ON COLUMN account_par_contact.updated_date IS '更新时间';
COMMENT ON TABLE account_par_contact IS '合伙企业联系信息';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_file');
  if num > 0 then
    execute immediate 'drop table account_par_file';
  end if;
end;
/
--合伙企业文件信息
CREATE TABLE account_par_file(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  par_personal_id NUMBER(20),
  file_path VARCHAR2(3072) NOT NULL,
  file_name VARCHAR2(1024) NOT NULL,
  file_identification VARCHAR2(128) NOT NULL,
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_file_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_file.id IS 'id';
COMMENT ON COLUMN account_par_file.account_id IS '账户ID';
COMMENT ON COLUMN account_par_file.par_personal_id IS '个人股东或法人股东的ID';
COMMENT ON COLUMN account_par_file.file_path IS '文件路径';
COMMENT ON COLUMN account_par_file.file_name IS '文件名';
COMMENT ON COLUMN account_par_file.file_identification IS '文件标识(见字典文件身份证类型)';
COMMENT ON COLUMN account_par_file.created_by IS '创建人';
COMMENT ON COLUMN account_par_file.created_date IS '创建时间';
COMMENT ON COLUMN account_par_file.updated_by IS '更新人';
COMMENT ON COLUMN account_par_file.updated_date IS '更新时间';
COMMENT ON TABLE account_par_file IS '合伙企业文件信息';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_identity');
  if num > 0 then
    execute immediate 'drop table account_par_identity';
  end if;
end;
/
--合伙企业身份信息
CREATE TABLE account_par_identity(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  par_personal_id NUMBER(20) NOT NULL,
  issuing_country VARCHAR2(32),
  id_card_type VARCHAR2(32),
  id_number VARCHAR2(63),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_identity_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_identity.id IS 'id';
COMMENT ON COLUMN account_par_identity.account_id IS '账户ID';
COMMENT ON COLUMN account_par_identity.par_personal_id IS '合伙企业个人信息ID';
COMMENT ON COLUMN account_par_identity.issuing_country IS '签发国家';
COMMENT ON COLUMN account_par_identity.id_card_type IS '身份证类型(见数据字典证件类型)';
COMMENT ON COLUMN account_par_identity.id_number IS '证件号码';
COMMENT ON COLUMN account_par_identity.created_by IS '创建人';
COMMENT ON COLUMN account_par_identity.created_date IS '创建时间';
COMMENT ON COLUMN account_par_identity.updated_by IS '更新人';
COMMENT ON COLUMN account_par_identity.updated_date IS '更新时间';
COMMENT ON TABLE account_par_identity IS '合伙企业身份信息';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_investment_target');
  if num > 0 then
    execute immediate 'drop table account_par_investment_target';
  end if;
end;
/
--合伙企业投资目标信息
CREATE TABLE account_par_investment_target(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  investment_target VARCHAR2(3072),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_investment_tar_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_investment_target.id IS 'id';
COMMENT ON COLUMN account_par_investment_target.account_id IS '账户ID';
COMMENT ON COLUMN account_par_investment_target.investment_target IS '投资目标(见数据字典投资目标类型)';
COMMENT ON COLUMN account_par_investment_target.created_by IS '创建人';
COMMENT ON COLUMN account_par_investment_target.created_date IS '创建时间';
COMMENT ON COLUMN account_par_investment_target.updated_by IS '更新人';
COMMENT ON COLUMN account_par_investment_target.updated_date IS '更新时间';
COMMENT ON TABLE account_par_investment_target IS '合伙企业投资目标信息';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_personal');
  if num > 0 then
    execute immediate 'drop table account_par_personal';
  end if;
end;
/
--合伙企业个人信息
CREATE TABLE account_par_personal(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  appellation VARCHAR2(32) NOT NULL,
  last_name VARCHAR2(50) NOT NULL,
  middle_name VARCHAR2(50),
  first_name VARCHAR2(32) NOT NULL,
  suffix VARCHAR2(32),
  position VARCHAR2(32),
  birthday NUMBER(13),
  email VARCHAR2(64),
  prcentage_of_ownership NUMBER(5,2) NOT NULL,
  nationality VARCHAR2(32) NOT NULL,
  birth_country VARCHAR2(32) NOT NULL,
  personal_type VARCHAR2(32) NOT NULL,
  is_business_manager NUMBER(1),
  is_american_taxpayer NUMBER(1) NOT NULL,
  user_name VARCHAR2(32),
  password VARCHAR2(32),
  entity_name VARCHAR2(128),
  company_position VARCHAR2(128),
  personal_type_two VARCHAR2(64),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_personal_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_personal.id IS 'id';
COMMENT ON COLUMN account_par_personal.account_id IS '账户ID';
COMMENT ON COLUMN account_par_personal.appellation IS '先生，女士，博士，夫人(见数据字典称呼类型)';
COMMENT ON COLUMN account_par_personal.last_name IS '姓';
COMMENT ON COLUMN account_par_personal.middle_name IS '中间名';
COMMENT ON COLUMN account_par_personal.first_name IS '名';
COMMENT ON COLUMN account_par_personal.suffix IS '后缀';
COMMENT ON COLUMN account_par_personal.position IS '职位';
COMMENT ON COLUMN account_par_personal.birthday IS '生日';
COMMENT ON COLUMN account_par_personal.email IS '电子邮件';
COMMENT ON COLUMN account_par_personal.prcentage_of_ownership IS '所有权百分比';
COMMENT ON COLUMN account_par_personal.nationality IS '国籍(见数据字典国家类型)';
COMMENT ON COLUMN account_par_personal.birth_country IS '出生国家(见数据字典国家类型)';
COMMENT ON COLUMN account_par_personal.personal_type IS '个人类型(见数据字典个人类型)';
COMMENT ON COLUMN account_par_personal.is_business_manager IS '是否';
COMMENT ON COLUMN account_par_personal.is_american_taxpayer IS '是否美国纳税人';
COMMENT ON COLUMN account_par_personal.user_name IS '用户名(第八页需要)';
COMMENT ON COLUMN account_par_personal.password IS '密码(第八页)';
COMMENT ON COLUMN account_par_personal.entity_name IS '实体名称（法人股东需要）';
COMMENT ON COLUMN account_par_personal.company_position IS '在公司持有哪些头寸（被授权人）';
COMMENT ON COLUMN account_par_personal.personal_type_two IS '个人类型第二种身份(见数据字典个人类型，第九页引用需要)';
COMMENT ON COLUMN account_par_personal.created_by IS '创建人';
COMMENT ON COLUMN account_par_personal.created_date IS '创建时间';
COMMENT ON COLUMN account_par_personal.updated_by IS '更新人';
COMMENT ON COLUMN account_par_personal.updated_date IS '更新时间';
COMMENT ON TABLE account_par_personal IS '合伙企业个人信息';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_regulatory');
  if num > 0 then
    execute immediate 'drop table account_par_regulatory';
  end if;
end;
/
--合伙企业规管信息
CREATE TABLE account_par_regulatory(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  whether NUMBER(1) NOT NULL,
  info_details VARCHAR2(3072),
  info_type VARCHAR2(3072) NOT NULL,
  CONSTRAINT account_par_regulatory_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_regulatory.id IS 'id';
COMMENT ON COLUMN account_par_regulatory.account_id IS '账户ID';
COMMENT ON COLUMN account_par_regulatory.whether IS '是否';
COMMENT ON COLUMN account_par_regulatory.info_details IS '信息详情json';
COMMENT ON COLUMN account_par_regulatory.info_type IS '类型';
COMMENT ON TABLE account_par_regulatory IS '合伙企业规管信息';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_safe_question');
  if num > 0 then
    execute immediate 'drop table account_par_safe_question';
  end if;
end;
/
--合伙企业安全问题
CREATE TABLE account_par_safe_question(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  safe_question VARCHAR2(128) NOT NULL,
  answer VARCHAR2(200) NOT NULL,
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_safe_question_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_safe_question.id IS 'id';
COMMENT ON COLUMN account_par_safe_question.account_id IS '账户ID';
COMMENT ON COLUMN account_par_safe_question.safe_question IS '安全问题(见数据字典安全问题类型)';
COMMENT ON COLUMN account_par_safe_question.answer IS '答案';
COMMENT ON COLUMN account_par_safe_question.created_by IS '创建人';
COMMENT ON COLUMN account_par_safe_question.created_date IS '创建时间';
COMMENT ON COLUMN account_par_safe_question.updated_by IS '更新人';
COMMENT ON COLUMN account_par_safe_question.updated_date IS '更新时间';
COMMENT ON TABLE account_par_safe_question IS '合伙企业安全问题';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_security_officer');
  if num > 0 then
    execute immediate 'drop table account_par_security_officer';
  end if;
end;
/
--合伙企业安全官
CREATE TABLE account_par_security_officer(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  appellation VARCHAR2(32) NOT NULL,
  last_name VARCHAR2(50) NOT NULL,
  middle_name VARCHAR2(50),
  first_name VARCHAR2(32) NOT NULL,
  suffix VARCHAR2(32),
  email VARCHAR2(64) NOT NULL,
  etity_relationship VARCHAR2(32),
  authorized_relationship VARCHAR2(32),
  user_name VARCHAR2(32),
  password VARCHAR2(32),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_security_off_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_security_officer.id IS 'id';
COMMENT ON COLUMN account_par_security_officer.account_id IS '账户ID';
COMMENT ON COLUMN account_par_security_officer.appellation IS '先生，女士，博士，夫人(见数据字典称呼类型)';
COMMENT ON COLUMN account_par_security_officer.last_name IS '姓';
COMMENT ON COLUMN account_par_security_officer.middle_name IS '中间名';
COMMENT ON COLUMN account_par_security_officer.first_name IS '名';
COMMENT ON COLUMN account_par_security_officer.suffix IS '后缀';
COMMENT ON COLUMN account_par_security_officer.email IS '电子邮件';
COMMENT ON COLUMN account_par_security_officer.etity_relationship IS '实体关系';
COMMENT ON COLUMN account_par_security_officer.authorized_relationship IS '授权人关系';
COMMENT ON COLUMN account_par_security_officer.user_name IS '用户名(第八页需要)';
COMMENT ON COLUMN account_par_security_officer.password IS '密码(第八页)';
COMMENT ON COLUMN account_par_security_officer.created_by IS '创建人';
COMMENT ON COLUMN account_par_security_officer.created_date IS '创建时间';
COMMENT ON COLUMN account_par_security_officer.updated_by IS '更新人';
COMMENT ON COLUMN account_par_security_officer.updated_date IS '更新时间';
COMMENT ON TABLE account_par_security_officer IS '合伙企业安全官';



declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_tax_classification');
  if num > 0 then
    execute immediate 'drop table account_par_tax_classification';
  end if;
end;
/
--合伙企业税收分类
CREATE TABLE account_par_tax_classification(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  tax_type VARCHAR2(64) NOT NULL,
  is_preferentia NUMBER(1),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_tax_class_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_tax_classification.id IS 'id';
COMMENT ON COLUMN account_par_tax_classification.account_id IS '账户ID';
COMMENT ON COLUMN account_par_tax_classification.tax_type IS '税收类型(见数据字典税收类型)';
COMMENT ON COLUMN account_par_tax_classification.is_preferentia IS '是否 非企业的情况下';
COMMENT ON COLUMN account_par_tax_classification.created_by IS '创建人';
COMMENT ON COLUMN account_par_tax_classification.created_date IS '创建时间';
COMMENT ON COLUMN account_par_tax_classification.updated_by IS '更新人';
COMMENT ON COLUMN account_par_tax_classification.updated_date IS '更新时间';
COMMENT ON TABLE account_par_tax_classification IS '合伙企业税收分类';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_taxes');
  if num > 0 then
    execute immediate 'drop table account_par_taxes';
  end if;
end;
/
--合伙企业税务居住地
CREATE TABLE account_par_taxes(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  par_personal_id NUMBER(20) NOT NULL,
  tax_residence_country VARCHAR2(32),
  tax_residence_id_card_number VARCHAR2(64),
  no_tax_identification_number VARCHAR2(256),
  different_registration_number VARCHAR2(32),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_taxes_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_taxes.id IS 'id';
COMMENT ON COLUMN account_par_taxes.account_id IS '账户ID';
COMMENT ON COLUMN account_par_taxes.par_personal_id IS '合伙企业个人信息ID';
COMMENT ON COLUMN account_par_taxes.tax_residence_country IS '税务居住国家(见数据字典国家类型)';
COMMENT ON COLUMN account_par_taxes.tax_residence_id_card_number IS '税务居住地证件号码';
COMMENT ON COLUMN account_par_taxes.no_tax_identification_number IS '无纳税识别号的理由选择';
COMMENT ON COLUMN account_par_taxes.different_registration_number IS '不同的注册号码';
COMMENT ON COLUMN account_par_taxes.created_by IS '创建人';
COMMENT ON COLUMN account_par_taxes.created_date IS '创建时间';
COMMENT ON COLUMN account_par_taxes.updated_by IS '更新人';
COMMENT ON COLUMN account_par_taxes.updated_date IS '更新时间';
COMMENT ON TABLE account_par_taxes IS '合伙企业税务居住地';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_trade_experience');
  if num > 0 then
    execute immediate 'drop table account_par_trade_experience';
  end if;
end;
/
--合伙企业交易经验信息
CREATE TABLE account_par_trade_experience(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  trade_experience VARCHAR2(256),
  year_transactions_per_number VARCHAR2(256),
  transactions_total_number VARCHAR2(256),
  knowledge_level VARCHAR2(32),
  trading_location VARCHAR2(3072),
  tansaction_type VARCHAR2(32),
  transaction_authority NUMBER(1),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_trade_exp_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_trade_experience.id IS 'id';
COMMENT ON COLUMN account_par_trade_experience.account_id IS '账户ID';
COMMENT ON COLUMN account_par_trade_experience.trade_experience IS '交易经验';
COMMENT ON COLUMN account_par_trade_experience.year_transactions_per_number IS '年交易数';
COMMENT ON COLUMN account_par_trade_experience.transactions_total_number IS '总交易数';
COMMENT ON COLUMN account_par_trade_experience.knowledge_level IS '知识水平(见数据字典知识水平)';
COMMENT ON COLUMN account_par_trade_experience.trading_location IS '交易地点';
COMMENT ON COLUMN account_par_trade_experience.tansaction_type IS '交易类型(见数据字典交易类型)';
COMMENT ON COLUMN account_par_trade_experience.transaction_authority IS '受交易权限';
COMMENT ON COLUMN account_par_trade_experience.created_by IS '创建人';
COMMENT ON COLUMN account_par_trade_experience.created_date IS '创建时间';
COMMENT ON COLUMN account_par_trade_experience.updated_by IS '更新人';
COMMENT ON COLUMN account_par_trade_experience.updated_date IS '更新时间';
COMMENT ON TABLE account_par_trade_experience IS '合伙企业交易经验信息';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_wben_contract');
  if num > 0 then
    execute immediate 'drop table account_par_wben_contract';
  end if;
end;
/
-- 合伙企业w8ben表
CREATE TABLE account_par_wben_contract(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  organization_name VARCHAR2(128) NOT NULL,
  organization_country VARCHAR2(32) NOT NULL,
  disregarded_entity_name VARCHAR2(128),
  chapter_three_status VARCHAR2(128) NOT NULL,
  whether VARCHAR2(32),
  chapter_four_status VARCHAR2(1024),
  usa_tin VARCHAR2(128),
  ein VARCHAR2(32),
  giin_xxx VARCHAR2(32),
  foreign_tin VARCHAR2(128),
  reference_number VARCHAR2(128),
  ffi VARCHAR2(32),
  branch_address VARCHAR2(32),
  branch_city VARCHAR2(32),
  branch_country VARCHAR2(32),
  ggin_if VARCHAR2(32),
  nonwithholding_a VARCHAR2(1024),
  checked VARCHAR2(3072),
  signature VARCHAR2(128),
  signature_time NUMBER(13),
  status VARCHAR2(1),
  type VARCHAR2(64) NOT NULL,
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_wben_contract_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_wben_contract.id IS 'id';
COMMENT ON COLUMN account_par_wben_contract.account_id IS '账户ID';
COMMENT ON COLUMN account_par_wben_contract.organization_name IS '个人或组织的名称';
COMMENT ON COLUMN account_par_wben_contract.organization_country IS '成立或组织的所在国家';
COMMENT ON COLUMN account_par_wben_contract.disregarded_entity_name IS '被忽略实体的名称';
COMMENT ON COLUMN account_par_wben_contract.chapter_three_status IS '第三章状态';
COMMENT ON COLUMN account_par_wben_contract.whether IS '该实体是否为提出条约索赔的混合体';
COMMENT ON COLUMN account_par_wben_contract.chapter_four_status IS '第四章状态';
COMMENT ON COLUMN account_par_wben_contract.usa_tin IS '美国识别号TIN';
COMMENT ON COLUMN account_par_wben_contract.ein IS 'EIN';
COMMENT ON COLUMN account_par_wben_contract.giin_xxx IS 'GIIN (if applicable-"XXXXXX.XXXXX.XX.XXX")';
COMMENT ON COLUMN account_par_wben_contract.foreign_tin IS '外国TIN';
COMMENT ON COLUMN account_par_wben_contract.reference_number IS '参考编号(见说明)';
COMMENT ON COLUMN account_par_wben_contract.ffi IS '第四章不予受理的实体';
COMMENT ON COLUMN account_par_wben_contract.branch_address IS '分公司地址(街道，公寓或套房号)';
COMMENT ON COLUMN account_par_wben_contract.branch_city IS '分公司城市或城镇，州或省';
COMMENT ON COLUMN account_par_wben_contract.branch_country IS '分公司国家';
COMMENT ON COLUMN account_par_wben_contract.ggin_if IS 'GIIN (if any)';
COMMENT ON COLUMN account_par_wben_contract.nonwithholding_a IS '不扣缴';
COMMENT ON COLUMN account_par_wben_contract.checked IS '勾选';
COMMENT ON COLUMN account_par_wben_contract.signature IS '签名';
COMMENT ON COLUMN account_par_wben_contract.signature_time IS '签名时间';
COMMENT ON COLUMN account_par_wben_contract.status IS '同意';
COMMENT ON COLUMN account_par_wben_contract.type IS '类型(公司或者合伙企业) 见税收分类';
COMMENT ON COLUMN account_par_wben_contract.created_by IS '创建人';
COMMENT ON COLUMN account_par_wben_contract.created_date IS '创建时间';
COMMENT ON COLUMN account_par_wben_contract.updated_by IS '更新人';
COMMENT ON COLUMN account_par_wben_contract.updated_date IS '更新时间';
COMMENT ON TABLE account_par_wben_contract IS '合伙企业w8ben表';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_wealth_source');
  if num > 0 then
    execute immediate 'drop table account_par_wealth_source';
  end if;
end;
/
--合伙企业财富来源
CREATE TABLE account_par_wealth_source(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  income_type VARCHAR2(256),
  is_capital_injection NUMBER(1),
  percentage_of_funds NUMBER(5,2),
  source_desc VARCHAR2(128),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_wealth_source_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_wealth_source.id IS 'id';
COMMENT ON COLUMN account_par_wealth_source.account_id  IS '账户ID';
COMMENT ON COLUMN account_par_wealth_source.income_type IS '资金来源类型(见数据字典财富来源)';
COMMENT ON COLUMN account_par_wealth_source.is_capital_injection IS '是否注资账户';
COMMENT ON COLUMN account_par_wealth_source.percentage_of_funds IS '资金百分比';
COMMENT ON COLUMN account_par_wealth_source.source_desc IS '描述';
COMMENT ON COLUMN account_par_wealth_source.created_by IS '创建人';
COMMENT ON COLUMN account_par_wealth_source.created_date IS '创建时间';
COMMENT ON COLUMN account_par_wealth_source.updated_by IS '更新人';
COMMENT ON COLUMN account_par_wealth_source.updated_date IS '更新时间';
COMMENT ON TABLE account_par_wealth_source IS '合伙企业财富来源';


declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_crs_entity');
  if num > 0 then
    execute immediate 'drop table account_par_crs_entity';
  end if;
end;
/
--合伙企业CRS实体自我证明表格（CRS-CP）
CREATE TABLE account_par_crs_entity(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  entity_name VARCHAR2(256) NOT NULL,
  tax_jurisdiction VARCHAR2(256) NOT NULL,
  registration_number VARCHAR2(64) NOT NULL,
  other_addresses VARCHAR2(256) ,
  other_city VARCHAR2(64) ,
  other_province VARCHAR2(64),
  other_country VARCHAR2(32),
  other_zip_code VARCHAR2(32),
  entity_number VARCHAR2(64),
  entity_category VARCHAR2(1024) NOT NULL,
  entity_input VARCHAR2(1024),
  self_certification VARCHAR2(1024),
  yes_tax_number VARCHAR2(64),
  no_tax_number  VARCHAR2(1024),
  reason VARCHAR2(256),
  authentic_data NUMBER(1, 0) NOT NULL,
  agree_tax_ordinance NUMBER(1, 0) NOT NULL,
  proven_itself NUMBER(1, 0) NOT NULL,
  consent NUMBER(1, 0) NOT NULL,
  signature_date NUMBER(13) NOT NULL,
  signature VARCHAR2(128) NOT NULL,
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_CRS_entity_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_crs_entity.id IS 'id';
COMMENT ON COLUMN account_par_crs_entity.account_id IS '账户ID';
COMMENT ON COLUMN account_par_crs_entity.entity_name IS '实体或分支机构的法定名称';
COMMENT ON COLUMN account_par_crs_entity.tax_jurisdiction IS '实体成立所在的税务管辖区';
COMMENT ON COLUMN account_par_crs_entity.registration_number IS '注册号码';
COMMENT ON COLUMN account_par_crs_entity.other_addresses IS '其他地址';
COMMENT ON COLUMN account_par_crs_entity.other_city IS '其他城市';
COMMENT ON COLUMN account_par_crs_entity.other_province IS '其他省/市';
COMMENT ON COLUMN account_par_crs_entity.other_country IS '其他国家（见区域表）';
COMMENT ON COLUMN account_par_crs_entity.other_zip_code IS '其他邮政编码';
COMMENT ON COLUMN account_par_crs_entity.entity_number IS '实体编号（财务机构,主动非财务实体..）';
COMMENT ON COLUMN account_par_crs_entity.entity_category IS '第二部分,实体类别(见字典数据实体类型子节点)';
COMMENT ON COLUMN account_par_crs_entity.entity_input IS '第二部分,实体类别(见字典数据实体类型子节点) 输入框';
COMMENT ON COLUMN account_par_crs_entity.self_certification IS '第三部分控权人自我证明表格(如实体账户持有人是被动非财务实体,填写此部)';
COMMENT ON COLUMN account_par_crs_entity.yes_tax_number IS '有税务编号';
COMMENT ON COLUMN account_par_crs_entity.no_tax_number IS '没有税务编号（见字典数据税务编号类型）';
COMMENT ON COLUMN account_par_crs_entity.reason IS '不能取税务编号的原因';
COMMENT ON COLUMN account_par_crs_entity.authentic_data IS '声明所有资料均为真实';
COMMENT ON COLUMN account_par_crs_entity.agree_tax_ordinance IS '同意《税务条例》';
COMMENT ON COLUMN account_par_crs_entity.proven_itself IS '会更新的自我证明表格';
COMMENT ON COLUMN account_par_crs_entity.consent IS '同意所有税务表格均用过电子形式发送';
COMMENT ON COLUMN account_par_crs_entity.signature_date IS '签名日期';
COMMENT ON COLUMN account_par_crs_entity.signature IS '签名';
COMMENT ON COLUMN account_par_crs_entity.created_by IS '创建人';
COMMENT ON COLUMN account_par_crs_entity.created_date IS '创建时间';
COMMENT ON COLUMN account_par_crs_entity.updated_by IS '更新人';
COMMENT ON COLUMN account_par_crs_entity.updated_date IS '更新时间';
COMMENT ON TABLE account_par_crs_entity IS '合伙企业CRS实体自我证明表格(CRS-CP)';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_crs_control');
  if num > 0 then
    execute immediate 'drop table account_par_crs_control';
  end if;
end;
/
--合伙企业CRS控权人自我证明表格（CRS-CP）
CREATE TABLE account_par_crs_control(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  appellation VARCHAR2(32) NOT NULL,
  last_name VARCHAR2(50) NOT NULL,
  first_name VARCHAR2(32) NOT NULL,
  id_card_type VARCHAR2(32) NOT NULL,
  id_number VARCHAR2(63) NOT NULL,
  birthday NUMBER(13) NOT NULL,
  other_address_status NUMBER(1,0) NOT NULL,
  other_addresses VARCHAR2(256) ,
  other_city VARCHAR2(64) ,
  other_province VARCHAR2(64),
  other_country VARCHAR2(32),
  other_zip_code VARCHAR2(32),
  entity_account_name VARCHAR2(1024) NOT NULL,
  yes_tax_number VARCHAR2(64),
  no_tax_number  VARCHAR2(1024),
  reason VARCHAR2(256),
  authentic_data NUMBER(1, 0) NOT NULL,
  agree_tax_ordinance NUMBER(1, 0) NOT NULL,
  proven_itself NUMBER(1, 0) NOT NULL,
  consent NUMBER(1, 0) NOT NULL,
  signature_date NUMBER(13) NOT NULL,
  signature VARCHAR2(128) NOT NULL,
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_CRS_control_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_crs_control.id IS 'id';
COMMENT ON COLUMN account_par_crs_control.account_id IS '账户ID';
COMMENT ON COLUMN account_par_crs_control.appellation IS '称呼：先生，女士，博士，夫人(见数据字典称呼类型)';
COMMENT ON COLUMN account_par_crs_control.last_name IS '姓';
COMMENT ON COLUMN account_par_crs_control.first_name IS '名';
COMMENT ON COLUMN account_par_crs_control.id_card_type IS '身份证类型(见数据字典证件类型)';
COMMENT ON COLUMN account_par_crs_control.id_number IS '身份证件号码';
COMMENT ON COLUMN account_par_crs_control.birthday IS '出生日期';
COMMENT ON COLUMN account_par_crs_control.other_address_status IS '是否地址与上方的不一致';
COMMENT ON COLUMN account_par_crs_control.other_addresses IS '其他地址';
COMMENT ON COLUMN account_par_crs_control.other_city IS '其他城市';
COMMENT ON COLUMN account_par_crs_control.other_province IS '其他省/市';
COMMENT ON COLUMN account_par_crs_control.other_country IS '其他国家（见区域表）';
COMMENT ON COLUMN account_par_crs_control.other_zip_code IS '其他邮政编码';
COMMENT ON COLUMN account_par_crs_control.entity_account_name IS '第二部分,控权人的实体账户持有人的名称(公司名)';
COMMENT ON COLUMN account_par_crs_control.yes_tax_number IS '有税务编号';
COMMENT ON COLUMN account_par_crs_control.no_tax_number IS '没有税务编号（见字典数据税务编号类型）';
COMMENT ON COLUMN account_par_crs_control.reason IS '不能取税务编号的原因';
COMMENT ON COLUMN account_par_crs_control.authentic_data IS '声明所有资料均为真实';
COMMENT ON COLUMN account_par_crs_control.agree_tax_ordinance IS '同意《税务条例》';
COMMENT ON COLUMN account_par_crs_control.proven_itself IS '会更新的自我证明表格';
COMMENT ON COLUMN account_par_crs_control.consent IS '同意所有税务表格均用过电子形式发送';
COMMENT ON COLUMN account_par_crs_control.signature_date IS '签名日期';
COMMENT ON COLUMN account_par_crs_control.signature IS '签名';
COMMENT ON COLUMN account_par_crs_control.created_by IS '创建人';
COMMENT ON COLUMN account_par_crs_control.created_date IS '创建时间';
COMMENT ON COLUMN account_par_crs_control.updated_by IS '更新人';
COMMENT ON COLUMN account_par_crs_control.updated_date IS '更新时间';
COMMENT ON TABLE account_par_crs_control IS '合伙企业CRS控权人自我证明表格(CRS-CP)';



declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_broker_permission');
  if num > 0 then
    execute immediate 'drop table account_par_broker_permission';
  end if;
end;
/
 -- 合伙企业经济商监管许可
CREATE TABLE account_par_broker_permission(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  register_adviser VARCHAR2(256) NOT NULL,
  register_country VARCHAR2(128),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_broker_permiss_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_broker_permission.id IS 'id';
COMMENT ON COLUMN account_par_broker_permission.account_id IS '账户ID';
COMMENT ON COLUMN account_par_broker_permission.register_adviser IS '注册顾问';
COMMENT ON COLUMN account_par_broker_permission.register_country IS '主要注册/许可国家';
COMMENT ON COLUMN account_par_broker_permission.created_by IS '创建人';
COMMENT ON COLUMN account_par_broker_permission.created_date IS '创建时间';
COMMENT ON COLUMN account_par_broker_permission.updated_by IS '更新人';
COMMENT ON COLUMN account_par_broker_permission.updated_date IS '更新时间';
COMMENT ON TABLE account_par_broker_permission IS '合伙企业经济商监管许可';




declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_broker_info');
  if num > 0 then
    execute immediate 'drop table account_par_broker_info';
  end if;
end;
/
-- 合伙企业经济商信息
CREATE TABLE account_par_broker_info(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  status NUMBER(1) NOT NULL,
  occ_member_number VARCHAR2(256),
  regulator VARCHAR2(64),
  regulator_info VARCHAR2(128),
  register_number VARCHAR2(64),
  register_position VARCHAR2(128),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_broker_info_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_broker_info.id IS 'id';
COMMENT ON COLUMN account_par_broker_info.account_id IS '账户ID';
COMMENT ON COLUMN account_par_broker_info.status IS '你的组织是否为期权清算公司(OCC)的会员？';
COMMENT ON COLUMN account_par_broker_info.occ_member_number IS 'OCC会员号码';
COMMENT ON COLUMN account_par_broker_info.regulator IS '监管机构';
COMMENT ON COLUMN account_par_broker_info.regulator_info IS '监管机构信息';
COMMENT ON COLUMN account_par_broker_info.register_number IS '注册号码';
COMMENT ON COLUMN account_par_broker_info.register_position IS '注册职位';
COMMENT ON COLUMN account_par_broker_info.created_by IS '创建人';
COMMENT ON COLUMN account_par_broker_info.created_date IS '创建时间';
COMMENT ON COLUMN account_par_broker_info.updated_by IS '更新人';
COMMENT ON COLUMN account_par_broker_info.updated_date IS '更新时间';
COMMENT ON TABLE account_par_broker_info IS '合伙企业经济商监信息';


declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_crs_control_num');
  if num > 0 then
    execute immediate 'drop table account_par_crs_control_num';
  end if;
end;
/
--合伙企业CRS控权人自我证明控权人编号
CREATE TABLE account_par_crs_control_num(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  entity_number VARCHAR2(64),
  entity_category VARCHAR2(64) NOT NULL,
  control_category VARCHAR2(1024) NOT NULL,
  entity_account_name VARCHAR2(1024) NOT NULL,
  CONSTRAINT account_par_crs_control_num_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_crs_control_num.id IS 'id';
COMMENT ON COLUMN account_par_crs_control_num.account_id IS '账户ID';
COMMENT ON COLUMN account_par_crs_control_num.entity_number IS '实体编号';
COMMENT ON COLUMN account_par_crs_control_num.entity_category IS '实体类别（法人,信托,除信托以外的法律安排，见字典数据控权人类型）';
COMMENT ON COLUMN account_par_crs_control_num.control_category IS '控权人类别（见字典数据控权人子节点）';
COMMENT ON COLUMN account_par_crs_control_num.entity_account_name IS '第二部分,控权人的实体账户持有人的名称(公司名)';
COMMENT ON TABLE account_par_crs_control_num IS '合伙企业CRS控权人自我证明控权人编号';


declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_par_aml_ctf');
  if num > 0 then
    execute immediate 'drop table account_par_aml_ctf';
  end if;
end;
/
-- 合伙企业  打击洗钱及恐怖分子资金筹集（ AML/CTF）风险问卷
CREATE TABLE account_par_aml_ctf(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  issue VARCHAR2(256) NOT NULL,
  status NUMBER(1) NOT NULL,
  supplementary_info VARCHAR2(256),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_par_aml_ctf_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_par_aml_ctf.id IS 'id';
COMMENT ON COLUMN account_par_aml_ctf.account_id IS '账户ID';
COMMENT ON COLUMN account_par_aml_ctf.issue IS '问题（获取字典数据aml问卷）';
COMMENT ON COLUMN account_par_aml_ctf.status IS '状态 0否 1是';
COMMENT ON COLUMN account_par_aml_ctf.supplementary_info IS '补充信息（选了 是 需要填写）';
COMMENT ON COLUMN account_par_aml_ctf.created_by IS '创建人';
COMMENT ON COLUMN account_par_aml_ctf.created_date IS '创建时间';
COMMENT ON COLUMN account_par_aml_ctf.updated_by IS '更新人';
COMMENT ON COLUMN account_par_aml_ctf.updated_date IS '更新时间';
COMMENT ON TABLE account_par_aml_ctf IS '合伙企业AML/CTF风险问卷';
