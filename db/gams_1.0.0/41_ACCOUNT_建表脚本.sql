declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Account');
    if num > 0 then
        execute immediate 'drop table Account';
    end if;
end;
/
CREATE TABLE Account
(
  id                            NUMBER(20)             NOT NULL,
  unid                          NUMBER(8)              NOT NULL,
  account_type                  VARCHAR2(32)           NOT NULL,
  account_name                  VARCHAR2(64)           NOT NULL,
  nick_name                     VARCHAR2(128),
  email                         VARCHAR2(64)           NOT NULL,
  mobile                        VARCHAR2(16),
  region                        VARCHAR2(8),
  country                       VARCHAR2(32)           NOT NULL,
  nationality                   VARCHAR2(32),
  location                      VARCHAR2(256),
  lang                          VARCHAR2(8),
  login_pwd                     VARCHAR2(64)           NOT NULL,
  fund_pwd                      VARCHAR2(64),
  status                        NUMBER(1) DEFAULT 0    NOT NULL,
  auth_key                      VARCHAR2(32),
  security_policy               NUMBER(1) DEFAULT 0    NOT NULL,
  trade_policy                  NUMBER(1) DEFAULT 0    NOT NULL,
  activation_code               VARCHAR2(32),
  unforzen_date                 NUMBER(13),
  register_status               NUMBER(2),
  remark                        VARCHAR2(64),
  sign                          VARCHAR2(64)           NOT NULL,
  random_key                    VARCHAR2(8)            NOT NULL,
  create_date                   NUMBER(13)             NOT NULL,
  update_date                   NUMBER(13),
  del_flag                      NUMBER(1) DEFAULT 0    NOT NULL,
  CONSTRAINT Account_PK PRIMARY KEY (id),
  CONSTRAINT Account_Unique_unid UNIQUE (unid),
  CONSTRAINT Account_Unique_account_name UNIQUE (account_name),
  CONSTRAINT ACCOUNT_UNIQUE_MOBILE UNIQUE (region, mobile),
  CONSTRAINT ACCOUNT_UNIQUE_EMAIL UNIQUE (email)
);
COMMENT ON COLUMN Account.id IS '主键';
COMMENT ON COLUMN Account.unid IS '账号编号';
COMMENT ON COLUMN Account.account_type IS '账户类型(数据字典)';
COMMENT ON COLUMN Account.account_name IS '账户名';
COMMENT ON COLUMN Account.nick_name IS '帐户昵称';
COMMENT ON COLUMN Account.email IS '邮箱';
COMMENT ON COLUMN Account.mobile IS '手机号';
COMMENT ON COLUMN Account.location IS '注册所在地或所在IP';
COMMENT ON COLUMN Account.region IS '地区代码';
COMMENT ON COLUMN Account.country IS '国家地区(区域表)';
COMMENT ON COLUMN Account.nationality IS '合法居住国家地区(区域表)';
COMMENT ON COLUMN Account.lang IS '语言标识';
COMMENT ON COLUMN Account.login_pwd IS '登录密码';
COMMENT ON COLUMN Account.fund_pwd IS '资金密码';
COMMENT ON COLUMN Account.status IS '账户状态(0正常、1冻结、2注销)';
COMMENT ON COLUMN Account.auth_key IS 'ga私钥';
COMMENT ON COLUMN Account.security_policy IS '安全验证策略';
COMMENT ON COLUMN Account.trade_policy IS '交易验证策略';
COMMENT ON COLUMN Account.activation_code IS '激活码';
COMMENT ON COLUMN Account.unforzen_date IS '解冻时间';
COMMENT ON COLUMN Account.register_status IS '注册开户状态';
COMMENT ON COLUMN Account.remark IS '备注';
COMMENT ON COLUMN Account.sign IS '签名';
COMMENT ON COLUMN Account.random_key IS 'key';
COMMENT ON COLUMN Account.create_date IS '创建时间';
COMMENT ON COLUMN Account.update_date IS '修改时间';
COMMENT ON COLUMN Account.del_flag IS '删除标识(0未删除、1删除)';
COMMENT ON TABLE Account IS '账户表';
