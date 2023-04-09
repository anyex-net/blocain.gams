declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_per_address');
  if num > 0 then
    execute immediate 'drop table account_per_address';
  end if;
end;
/
--个人地址
CREATE TABLE account_per_address(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  country VARCHAR2(32) NOT NULL,
  address VARCHAR2(128) NOT NULL,
  city VARCHAR2(128) NOT NULL,
  rovince VARCHAR2(128) NOT NULL,
  zip_code VARCHAR2(32),
  address_type VARCHAR2(32) NOT NULL,
  other_country VARCHAR2(32),
  other_birth_country VARCHAR2(32),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_per_address_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_per_address.id IS 'id';
COMMENT ON COLUMN account_per_address.account_id IS '账户ID';
COMMENT ON COLUMN account_per_address.country IS '国家(见数据字典国家类型)';
COMMENT ON COLUMN account_per_address.address IS '地址';
COMMENT ON COLUMN account_per_address.city IS '城市';
COMMENT ON COLUMN account_per_address.rovince IS '省';
COMMENT ON COLUMN account_per_address.zip_code IS '邮编';
COMMENT ON COLUMN account_per_address.address_type IS '居住，邮寄,公司(见数据字典地址类型)';
COMMENT ON COLUMN account_per_address.other_country IS '其他国家(是否和上方选的国家不同)(见数据字典国家类型)';
COMMENT ON COLUMN account_per_address.other_birth_country IS '其他出生国家(是否和上方选的国家不同)(见数据字典国家类型)';
COMMENT ON COLUMN account_per_address.created_by IS '创建人';
COMMENT ON COLUMN account_per_address.created_date IS '创建时间';
COMMENT ON COLUMN account_per_address.updated_by IS '更新人';
COMMENT ON COLUMN account_per_address.updated_date IS '更新时间';
COMMENT ON TABLE account_per_address IS '个人地址';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_per_asset');
  if num > 0 then
    execute immediate 'drop table account_per_asset';
  end if;
end;
/
--个人资产信息
CREATE TABLE account_per_asset(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  anual_net_income VARCHAR2(32) NOT NULL,
  net_asset_value VARCHAR2(32) NOT NULL,
  net_current_assets VARCHAR2(32) NOT NULL,
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_per_asset_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_per_asset.id IS 'id';
COMMENT ON COLUMN account_per_asset.account_id IS '账户ID';
COMMENT ON COLUMN account_per_asset.anual_net_income IS '年度净收入(见字典数据年度净收入)';
COMMENT ON COLUMN account_per_asset.net_asset_value IS '资产净值（见字典数据资产净值）';
COMMENT ON COLUMN account_per_asset.net_current_assets IS '流动资产净值（见字典数据资产净值）';
COMMENT ON COLUMN account_per_asset.created_by IS '创建人';
COMMENT ON COLUMN account_per_asset.created_date IS '创建时间';
COMMENT ON COLUMN account_per_asset.updated_by IS '更新人';
COMMENT ON COLUMN account_per_asset.updated_date IS '更新时间';
COMMENT ON TABLE account_per_asset IS '个人资产信息';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_per_contact');
  if num > 0 then
    execute immediate 'drop table account_per_contact';
  end if;
end;
/
--个人联系方式
CREATE TABLE account_per_contact(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  contact_number VARCHAR2(32) NOT NULL,
  contact_number_type VARCHAR2(32) NOT NULL,
  country VARCHAR2(32) NOT NULL,
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_per_contact_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_per_contact.id IS 'id';
COMMENT ON COLUMN account_per_contact.account_id IS '账户ID';
COMMENT ON COLUMN account_per_contact.contact_number IS '联系号码';
COMMENT ON COLUMN account_per_contact.contact_number_type IS '号码类别 手机，电话，传真，家庭，商务(见数据字典电话号码类型)';
COMMENT ON COLUMN account_per_contact.country IS '国家(见数据字典国家类型)';
COMMENT ON COLUMN account_per_contact.created_by IS '创建人';
COMMENT ON COLUMN account_per_contact.created_date IS '创建时间';
COMMENT ON COLUMN account_per_contact.updated_by IS '更新人';
COMMENT ON COLUMN account_per_contact.updated_date IS '更新时间';
COMMENT ON TABLE account_per_contact IS '个人联系方式';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_per_file');
  if num > 0 then
    execute immediate 'drop table account_per_file';
  end if;
end;
/
--个人文件
CREATE TABLE account_per_file(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  file_path VARCHAR2(3072) NOT NULL,
  file_name VARCHAR2(1024) NOT NULL,
  file_identification VARCHAR2(128) NOT NULL,
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_per_file_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_per_file.id IS 'id';
COMMENT ON COLUMN account_per_file.account_id IS '账户ID';
COMMENT ON COLUMN account_per_file.file_path IS '文件路径';
COMMENT ON COLUMN account_per_file.file_name IS '文件名';
COMMENT ON COLUMN account_per_file.file_identification IS '文件标识(见数据字典文件身份证类型)';
COMMENT ON COLUMN account_per_file.created_by IS '创建人';
COMMENT ON COLUMN account_per_file.created_date IS '创建时间';
COMMENT ON COLUMN account_per_file.updated_by IS '更新人';
COMMENT ON COLUMN account_per_file.updated_date IS '更新时间';
COMMENT ON TABLE account_per_file IS '个人文件';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_per_identity');
  if num > 0 then
    execute immediate 'drop table account_per_identity';
  end if;
end;
/
--个人身份信息
CREATE TABLE account_per_identity(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  issuing_country VARCHAR2(32),
  id_card_type VARCHAR2(32),
  id_number VARCHAR2(63),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_per_identity_PK PRIMARY KEY (id)
);
COMMENT ON TABLE account_per_identity IS '身份信息';
COMMENT ON COLUMN account_per_identity.id IS 'id';
COMMENT ON COLUMN account_per_identity.account_id IS '账户ID';
COMMENT ON COLUMN account_per_identity.issuing_country IS '签发国家';
COMMENT ON COLUMN account_per_identity.id_card_type IS '身份证类型(见数据字典数据证件类型)';
COMMENT ON COLUMN account_per_identity.id_number IS '证件号码';
COMMENT ON COLUMN account_per_identity.created_by IS '创建人';
COMMENT ON COLUMN account_per_identity.created_date IS '创建时间';
COMMENT ON COLUMN account_per_identity.updated_by IS '更新人';
COMMENT ON COLUMN account_per_identity.updated_date IS '更新时间';
COMMENT ON TABLE account_per_identity IS '个人身份信息';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_per_invest_target');
  if num > 0 then
    execute immediate 'drop table account_per_invest_target';
  end if;
end;
/
--个人投资目标
CREATE TABLE account_per_invest_target(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  investment_target VARCHAR2(3072),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_per_invest_tar_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_per_invest_target.id IS 'id';
COMMENT ON COLUMN account_per_invest_target.account_id IS '账户ID';
COMMENT ON COLUMN account_per_invest_target.investment_target IS '投资目标(见数据字典数据投资目标类型)';
COMMENT ON COLUMN account_per_invest_target.created_by IS '创建人';
COMMENT ON COLUMN account_per_invest_target.created_date IS '创建时间';
COMMENT ON COLUMN account_per_invest_target.updated_by IS '更新人';
COMMENT ON COLUMN account_per_invest_target.updated_date IS '更新时间';
COMMENT ON TABLE account_per_invest_target IS '个人投资目标';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_per_safe_question');
  if num > 0 then
    execute immediate 'drop table account_per_safe_question';
  end if;
end;
/
--个人安全问题
CREATE TABLE account_per_safe_question(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  safe_question VARCHAR2(128) NOT NULL,
  answer VARCHAR2(200) NOT NULL,
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_per_safe_question_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_per_safe_question.id IS 'id';
COMMENT ON COLUMN account_per_safe_question.account_id IS '账户ID';
COMMENT ON COLUMN account_per_safe_question.safe_question IS '安全问题(见数据字典数据安全问题类型)';
COMMENT ON COLUMN account_per_safe_question.answer IS '答案';
COMMENT ON COLUMN account_per_safe_question.created_by IS '创建人';
COMMENT ON COLUMN account_per_safe_question.created_date IS '创建时间';
COMMENT ON COLUMN account_per_safe_question.updated_by IS '更新人';
COMMENT ON COLUMN account_per_safe_question.updated_date IS '更新时间';
COMMENT ON TABLE account_per_safe_question IS '个人安全问题';



declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_per_tax_class');
  if num > 0 then
    execute immediate 'drop table account_per_tax_class';
  end if;
end;
/
--个人税收分类
CREATE TABLE account_per_tax_class(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  tax_type VARCHAR2(32) NOT NULL,
  is_preferentia NUMBER(1),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_per_tax_class_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_per_tax_class.id IS 'id';
COMMENT ON COLUMN account_per_tax_class.account_id IS '账户ID';
COMMENT ON COLUMN account_per_tax_class.tax_type IS '税收类型(见数据字典数据税收类型)';
COMMENT ON COLUMN account_per_tax_class.is_preferentia IS '1是，0否 非企业的情况下';
COMMENT ON COLUMN account_per_tax_class.created_by IS '创建人';
COMMENT ON COLUMN account_per_tax_class.created_date IS '创建时间';
COMMENT ON COLUMN account_per_tax_class.updated_by IS '更新人';
COMMENT ON COLUMN account_per_tax_class.updated_date IS '更新时间';
COMMENT ON TABLE account_per_tax_class IS '个人税收分类';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_per_taxes');
  if num > 0 then
    execute immediate 'drop table account_per_taxes';
  end if;
end;
/
--个人税务居住地
CREATE TABLE account_per_taxes(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  tax_residence_country VARCHAR2(32),
  tax_residence_id_card_number VARCHAR2(64),
  no_tax_identification_number VARCHAR2(256),
  different_registration_number VARCHAR2(32),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_per_taxes_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_per_taxes.id IS 'id';
COMMENT ON COLUMN account_per_taxes.account_id IS '账户ID';
COMMENT ON COLUMN account_per_taxes.tax_residence_country IS '税务居住国家(见数据字典数据国家类型)';
COMMENT ON COLUMN account_per_taxes.tax_residence_id_card_number IS '税务居住地证件号码';
COMMENT ON COLUMN account_per_taxes.no_tax_identification_number IS '无纳税识别号的理由选择（见字典数据纳税识别号类型）';
COMMENT ON COLUMN account_per_taxes.different_registration_number IS '不同的注册号码';
COMMENT ON COLUMN account_per_taxes.created_by IS '创建人';
COMMENT ON COLUMN account_per_taxes.created_date IS '创建时间';
COMMENT ON COLUMN account_per_taxes.updated_by IS '更新人';
COMMENT ON COLUMN account_per_taxes.updated_date IS '更新时间';
COMMENT ON TABLE account_per_taxes IS '个人税务居住地';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_per_trade_exp');
  if num > 0 then
    execute immediate 'drop table account_per_trade_exp';
  end if;
end;
/
--个人交易经验信息
CREATE TABLE account_per_trade_exp(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  trading_experience VARCHAR2(256),
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
  CONSTRAINT account_per_trade_exp_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_per_trade_exp.id IS 'id';
COMMENT ON COLUMN account_per_trade_exp.account_id IS '账户ID';
COMMENT ON COLUMN account_per_trade_exp.trading_experience IS '交易经验(见数据字典数据交易经验)';
COMMENT ON COLUMN account_per_trade_exp.year_transactions_per_number IS '年交易数(见数据字典数据每年交易笔数)';
COMMENT ON COLUMN account_per_trade_exp.transactions_total_number IS '总交易数';
COMMENT ON COLUMN account_per_trade_exp.knowledge_level IS '知识水平(见数据字典数据知识水平)';
COMMENT ON COLUMN account_per_trade_exp.trading_location IS '交易地点';
COMMENT ON COLUMN account_per_trade_exp.tansaction_type IS '交易类型(见数据字典数据交易类型)';
COMMENT ON COLUMN account_per_trade_exp.transaction_authority IS '受交易权限';
COMMENT ON COLUMN account_per_trade_exp.created_by IS '创建人';
COMMENT ON COLUMN account_per_trade_exp.created_date IS '创建时间';
COMMENT ON COLUMN account_per_trade_exp.updated_by IS '更新人';
COMMENT ON COLUMN account_per_trade_exp.updated_date IS '更新时间';
COMMENT ON TABLE account_per_trade_exp IS '个人交易经验信息';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_per_wealth_source');
  if num > 0 then
    execute immediate 'drop table account_per_wealth_source';
  end if;
end;
/
--个人财富来源
CREATE TABLE account_per_wealth_source(
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
  CONSTRAINT account_per_wealth_sc_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_per_wealth_source.id IS 'id';
COMMENT ON COLUMN account_per_wealth_source.account_id IS '账户ID';
COMMENT ON COLUMN account_per_wealth_source.income_type IS '资金来源类型(见数据字典数据财富来源)';
COMMENT ON COLUMN account_per_wealth_source.is_capital_injection IS '是否注资账户（0否，1是）';
COMMENT ON COLUMN account_per_wealth_source.percentage_of_funds IS '资金百分比';
COMMENT ON COLUMN account_per_wealth_source.source_desc IS '描述';
COMMENT ON COLUMN account_per_wealth_source.created_by IS '创建人';
COMMENT ON COLUMN account_per_wealth_source.created_date IS '创建时间';
COMMENT ON COLUMN account_per_wealth_source.updated_by IS '更新人';
COMMENT ON COLUMN account_per_wealth_source.updated_date IS '更新时间';
COMMENT ON TABLE account_per_wealth_source IS '个人财富来源';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_per_info');
  if num > 0 then
    execute immediate 'drop table account_per_info';
  end if;
end;
/
--个人信息
CREATE TABLE account_per_info(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  personal_type VARCHAR2(32),
  appellation VARCHAR2(32) NOT NULL,
  last_name VARCHAR2(50) NOT NULL,
  first_name VARCHAR2(32) NOT NULL,
  birthday NUMBER(13),
  gender VARCHAR2(32) NOT NULL,
  marital_status VARCHAR2(32) NOT NULL,
  number_of_family_members VARCHAR2(32) NOT NULL,
  other_nationality VARCHAR2(32),
  other_countries_of_birth VARCHAR2(32),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_per_info_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_per_info.id IS 'id';
COMMENT ON COLUMN account_per_info.account_id IS '账户ID';
COMMENT ON COLUMN account_per_info.personal_type IS '个人类型（见字典数据个人类型）';
COMMENT ON COLUMN account_per_info.appellation IS '先生，女士，博士，夫人(见数据字典数据称呼类型)';
COMMENT ON COLUMN account_per_info.last_name IS '姓';
COMMENT ON COLUMN account_per_info.first_name IS '名';
COMMENT ON COLUMN account_per_info.birthday IS '生日';
COMMENT ON COLUMN account_per_info.gender IS '性别(见数据字典数据性别类型)';
COMMENT ON COLUMN account_per_info.marital_status IS '婚姻状况(见数据字典数据婚姻状况)';
COMMENT ON COLUMN account_per_info.number_of_family_members IS '家庭成员数目(见数据字典数据家庭成员数)';
COMMENT ON COLUMN account_per_info.other_nationality IS '其它国籍(见字数据国家类型)';
COMMENT ON COLUMN account_per_info.other_countries_of_birth IS '其它出生国家(见字数据国家类型)';
COMMENT ON COLUMN account_per_info.created_by IS '创建人';
COMMENT ON COLUMN account_per_info.created_date IS '创建时间';
COMMENT ON COLUMN account_per_info.updated_by IS '更新人';
COMMENT ON COLUMN account_per_info.updated_date IS '更新时间';
COMMENT ON TABLE account_per_info IS '个人信息';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_per_employment');
  if num > 0 then
    execute immediate 'drop table account_per_employment';
  end if;
end;
/
--个人雇佣(就业)信息
CREATE TABLE account_per_employment(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  employment_status VARCHAR2(128) NOT NULL,
  company_name VARCHAR2(32),
  commercial_nature VARCHAR2(128),
  position VARCHAR2(128),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_per_employment_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_per_employment.id IS 'id';
COMMENT ON COLUMN account_per_employment.account_id IS '账户ID';
COMMENT ON COLUMN account_per_employment.employment_status IS '就业状态(见数据字典数据雇佣类型)';
COMMENT ON COLUMN account_per_employment.company_name IS '公司名';
COMMENT ON COLUMN account_per_employment.commercial_nature IS '商业性质';
COMMENT ON COLUMN account_per_employment.position IS '职位';
COMMENT ON COLUMN account_per_employment.created_by IS '创建人';
COMMENT ON COLUMN account_per_employment.created_date IS '创建时间';
COMMENT ON COLUMN account_per_employment.updated_by IS '更新人';
COMMENT ON COLUMN account_per_employment.updated_date IS '更新时间';
COMMENT ON TABLE account_per_employment IS '个人雇佣(就业)信息';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_per_agreement');
  if num > 0 then
    execute immediate 'drop table account_per_agreement';
  end if;
end;
/
--个人协议纰漏
CREATE TABLE account_per_agreement(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  agreement_number VARCHAR2(128),
  account_name VARCHAR2(32) NOT NULL,
  signature VARCHAR2(128) NOT NULL,
  signature_time NUMBER(13),
  status NUMBER(1),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_per_agreement_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_per_agreement.id IS 'id';
COMMENT ON COLUMN account_per_agreement.account_id IS '账户ID';
COMMENT ON COLUMN account_per_agreement.agreement_number IS '协议文件编号(见共同协议表)';
COMMENT ON COLUMN account_per_agreement.account_name IS '账户名';
COMMENT ON COLUMN account_per_agreement.signature IS '签名';
COMMENT ON COLUMN account_per_agreement.signature_time IS '签名时间';
COMMENT ON COLUMN account_per_agreement.status IS '1是,0否';
COMMENT ON COLUMN account_per_agreement.created_by IS '创建人';
COMMENT ON COLUMN account_per_agreement.created_date IS '创建时间';
COMMENT ON COLUMN account_per_agreement.updated_by IS '更新人';
COMMENT ON COLUMN account_per_agreement.updated_date IS '更新时间';
COMMENT ON TABLE account_per_agreement IS '个人协议纰漏';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_per_invest_account');
  if num > 0 then
    execute immediate 'drop table account_per_invest_account';
  end if;
end;
/
--个人一体化投资管理账户功能
CREATE TABLE account_per_invest_account(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  stock_income_growth_plan NUMBER(1),
  account_name VARCHAR2(32) NOT NULL,
  signature_date NUMBER(13) NOT NULL,
  signature VARCHAR2(128) NOT NULL,
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_per_invest_account_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_per_invest_account.id IS 'id';
COMMENT ON COLUMN account_per_invest_account.account_id IS '账户id';
COMMENT ON COLUMN account_per_invest_account.stock_income_growth_plan IS '股票收益增长计划 1是true,0是false';
COMMENT ON COLUMN account_per_invest_account.account_name IS '账户名';
COMMENT ON COLUMN account_per_invest_account.signature_date IS '日期';
COMMENT ON COLUMN account_per_invest_account.signature IS '签名';
COMMENT ON COLUMN account_per_invest_account.created_by IS '创建人';
COMMENT ON COLUMN account_per_invest_account.created_date IS '创建时间';
COMMENT ON COLUMN account_per_invest_account.updated_by IS '更新人';
COMMENT ON COLUMN account_per_invest_account.updated_date IS '更新时间';
COMMENT ON TABLE account_per_invest_account IS '个人一体化投资管理账户功能';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_per_portfolio');
  if num > 0 then
    execute immediate 'drop table account_per_portfolio';
  end if;
end;
/
--个人账户投资组合
CREATE TABLE account_per_portfolio(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  account_portfolio_type VARCHAR2(32) NOT NULL,
  base_currency VARCHAR2(32) NOT NULL,
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_per_portfolio_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_per_portfolio.id IS 'id';
COMMENT ON COLUMN account_per_portfolio.account_id IS '账户ID';
COMMENT ON COLUMN account_per_portfolio.account_portfolio_type IS '账户投资组合类型（见字典数据账户投资组合类型）';
COMMENT ON COLUMN account_per_portfolio.base_currency IS '基础货币(见数据字典数据基础货币)';
COMMENT ON COLUMN account_per_portfolio.created_by IS '创建人';
COMMENT ON COLUMN account_per_portfolio.created_date IS '创建时间';
COMMENT ON COLUMN account_per_portfolio.updated_by IS '更新人';
COMMENT ON COLUMN account_per_portfolio.updated_date IS '更新时间';
COMMENT ON TABLE account_per_portfolio IS '个人账户投资组合';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_per_regulatory');
  if num > 0 then
    execute immediate 'drop table account_per_regulatory';
  end if;
end;
/
--个人账户规管信息
CREATE TABLE account_per_regulatory(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  employer VARCHAR2(1024),
  relationship_account_holders VARCHAR2(32),
  family_members_company VARCHAR2(32),
  account_holder_company_stock VARCHAR2(300),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_per_regulatory_PK PRIMARY KEY (id)
);

COMMENT ON COLUMN account_per_regulatory.id IS 'id';
COMMENT ON COLUMN account_per_regulatory.account_id IS '账户ID';
COMMENT ON COLUMN account_per_regulatory.employer IS '雇主';
COMMENT ON COLUMN account_per_regulatory.relationship_account_holders IS '账户持有人关系';
COMMENT ON COLUMN account_per_regulatory.family_members_company IS '与公司有关联的家庭成员';
COMMENT ON COLUMN account_per_regulatory.account_holder_company_stock IS '账户持有人公司股票代码';
COMMENT ON COLUMN account_per_regulatory.created_by IS '创建人';
COMMENT ON COLUMN account_per_regulatory.created_date IS '创建时间';
COMMENT ON COLUMN account_per_regulatory.updated_by IS '更新人';
COMMENT ON COLUMN account_per_regulatory.updated_date IS '更新时间';
COMMENT ON TABLE account_per_regulatory IS '个人账户规管信息';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_per_wben_contract');
  if num > 0 then
    execute immediate 'drop table account_per_wben_contract';
  end if;
end;
/
--个人WBen合约
CREATE TABLE account_per_wben_contract(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  account_name VARCHAR2(32) NOT NULL,
  signature_time NUMBER(13) NOT NULL,
  signature VARCHAR2(128) NOT NULL,
  email_address VARCHAR2(128),
  mail_code VARCHAR2(128),
  other_nationality VARCHAR2(32),
  usa_itin VARCHAR2(32),
  overseas_tax_number VARCHAR2(32),
  reference_number VARCHAR2(32),
  other_birthday NUMBER(13),
  usa_country_agreement VARCHAR2(128),
  explain_address VARCHAR2(128),
  consent_status VARCHAR2(1),
  consent_tax VARCHAR2(1),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_per_wben_contract_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_per_wben_contract.id IS 'id';
COMMENT ON COLUMN account_per_wben_contract.account_id IS '账户ID';
COMMENT ON COLUMN account_per_wben_contract.account_name IS '账户名';
COMMENT ON COLUMN account_per_wben_contract.signature_time IS '签名时间';
COMMENT ON COLUMN account_per_wben_contract.signature IS '签名';
COMMENT ON COLUMN account_per_wben_contract.email_address IS '其他邮寄地址';
COMMENT ON COLUMN account_per_wben_contract.mail_code IS '其他邮政编码';
COMMENT ON COLUMN account_per_wben_contract.other_nationality IS '其他国籍(见数据字典数据国家类型)';
COMMENT ON COLUMN account_per_wben_contract.usa_itin IS '美国纳税人';
COMMENT ON COLUMN account_per_wben_contract.overseas_tax_number IS '海外税收识别号码';
COMMENT ON COLUMN account_per_wben_contract.reference_number IS '参考编号';
COMMENT ON COLUMN account_per_wben_contract.other_birthday IS '其他出生日期';
COMMENT ON COLUMN account_per_wben_contract.usa_country_agreement IS '美国与所属国的税收协定下';
COMMENT ON COLUMN account_per_wben_contract.explain_address IS '条约外的地址解释';
COMMENT ON COLUMN account_per_wben_contract.consent_status IS '状态';
COMMENT ON COLUMN account_per_wben_contract.consent_tax IS '同意税收';
COMMENT ON COLUMN account_per_wben_contract.created_by IS '创建人';
COMMENT ON COLUMN account_per_wben_contract.updated_by IS '更新人';
COMMENT ON COLUMN account_per_wben_contract.created_date IS '创建时间';
COMMENT ON COLUMN account_per_wben_contract.updated_date IS '更新时间';
COMMENT ON TABLE account_per_wben_contract IS '个人WBen合约';






declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_per_CRS');
  if num > 0 then
    execute immediate 'drop table account_per_CRS';
  end if;
end;
/
--  个人的CRS 
CREATE TABLE account_per_CRS(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  other_addresses VARCHAR2(256) ,
  other_city VARCHAR2(64) ,
  other_province VARCHAR2(64),
  other_country VARCHAR2(32),
  other_zip_Code VARCHAR2(32),
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
  CONSTRAINT account_per_CRS_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_per_CRS.id IS 'id';
COMMENT ON COLUMN account_per_CRS.account_id IS '账户ID';
COMMENT ON COLUMN account_per_CRS.other_addresses IS '其他地址';
COMMENT ON COLUMN account_per_CRS.other_city IS '其他城市';
COMMENT ON COLUMN account_per_CRS.other_province IS '其他省份';
COMMENT ON COLUMN account_per_CRS.other_country IS '其他国家（见区域表）';
COMMENT ON COLUMN account_per_CRS.other_zip_Code IS '其他邮政编码';
COMMENT ON COLUMN account_per_CRS.yes_tax_number IS '有税务编号';
COMMENT ON COLUMN account_per_CRS.no_tax_number IS '没有税务编号（见字典数据税务编号类型）';
COMMENT ON COLUMN account_per_CRS.reason IS '不能取税务编号的原因';
COMMENT ON COLUMN account_per_CRS.authentic_data IS '声明所有资料均为真实';
COMMENT ON COLUMN account_per_CRS.agree_tax_ordinance IS '同意《税务条例》';
COMMENT ON COLUMN account_per_CRS.proven_itself IS '会更新的自我证明表格';
COMMENT ON COLUMN account_per_CRS.consent IS '同意所有税务表格均用过电子形式发送';
COMMENT ON COLUMN account_per_CRS.signature_date IS '签名日期';
COMMENT ON COLUMN account_per_CRS.signature IS '签名';
COMMENT ON COLUMN account_per_CRS.created_by IS '创建人';
COMMENT ON COLUMN account_per_CRS.created_date IS '创建时间';
COMMENT ON COLUMN account_per_CRS.updated_by IS '更新人';
COMMENT ON COLUMN account_per_CRS.updated_date IS '更新时间';
COMMENT ON TABLE account_per_CRS IS '个人CRS个人自我证明表格';





declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('account_per_aml_ctf');
  if num > 0 then
    execute immediate 'drop table account_per_aml_ctf';
  end if;
end;
/
-- 个人  打击洗钱及恐怖分子资金筹集（ AML/CTF）风险问卷
CREATE TABLE account_per_aml_ctf(
  id NUMBER(20) NOT NULL,
  account_id NUMBER(20) NOT NULL,
  issue VARCHAR2(256) NOT NULL,
  status NUMBER(1) NOT NULL,
  supplementary_info VARCHAR2(256),
  created_by NUMBER(20) NOT NULL,
  created_date NUMBER(13) NOT NULL,
  updated_by NUMBER(20),
  updated_date NUMBER(13),
  CONSTRAINT account_per_aml_ctf_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN account_per_aml_ctf.id IS 'id';
COMMENT ON COLUMN account_per_aml_ctf.account_id IS '账户ID';
COMMENT ON COLUMN account_per_aml_ctf.issue IS '问题（获取字典数据个人aml）';
COMMENT ON COLUMN account_per_aml_ctf.status IS '状态 0否 1是';
COMMENT ON COLUMN account_per_aml_ctf.supplementary_info IS '补充信息（选了 是 需要填写）';
COMMENT ON COLUMN account_per_aml_ctf.created_by IS '创建人';
COMMENT ON COLUMN account_per_aml_ctf.created_date IS '创建时间';
COMMENT ON COLUMN account_per_aml_ctf.updated_by IS '更新人';
COMMENT ON COLUMN account_per_aml_ctf.updated_date IS '更新时间';
COMMENT ON TABLE account_per_aml_ctf IS '个人AML/CTF风险问卷';
