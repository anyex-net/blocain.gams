declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Dictionary');
    if num > 0 then
        execute immediate 'drop table Dictionary';
    end if;
end;
/
CREATE TABLE Dictionary
(  ID           NUMBER(20)      NOT NULL,  
   DICT_CODE    VARCHAR2(64)    NOT NULL,
   DICT_NAME    VARCHAR2(128)   NOT NULL,
   DICT_VALUE   VARCHAR2(512)   NOT NULL,
   DICT_DEST    VARCHAR2(512),
   LANG         VARCHAR2(64)    NOT NULL,
   SORT_NUM     NUMBER(10),
   CREATE_BY    NUMBER(20)      NOT NULL,
   CREATE_DATE  NUMBER(13)      NOT NULL,
   UPDATE_BY    NUMBER(20),
   UPDATE_DATE  NUMBER(13),
   CONSTRAINT Dictionary_PK PRIMARY KEY (ID)
 );
COMMENT ON COLUMN Dictionary.ID IS '主键';  
COMMENT ON COLUMN Dictionary.DICT_CODE IS '类型编码';
COMMENT ON COLUMN Dictionary.DICT_NAME IS '字典名称';
COMMENT ON COLUMN Dictionary.DICT_VALUE IS '字典键值';
COMMENT ON COLUMN Dictionary.DICT_DEST IS '键值描述';
COMMENT ON COLUMN Dictionary.LANG IS '语言（en_US,zh_CN,zh_HK）';
COMMENT ON COLUMN Dictionary.SORT_NUM IS '排序号';
COMMENT ON COLUMN Dictionary.CREATE_BY IS '创建人';
COMMENT ON COLUMN Dictionary.CREATE_DATE IS '创建时间';
COMMENT ON COLUMN Dictionary.UPDATE_BY IS '修改人';
COMMENT ON COLUMN Dictionary.UPDATE_DATE IS '修改时间';
COMMENT ON TABLE Dictionary  IS '字典数据';

declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Parameter');
    if num > 0 then
        execute immediate 'drop table Parameter';
    end if;
end;
/
CREATE TABLE Parameter
(
  id                NUMBER(20)      NOT NULL,
  system_name       VARCHAR2(32)    NOT NULL,
  parameter_name    VARCHAR2(32)    NOT NULL,
  describe          VARCHAR2(128)   NOT NULL,
  division          VARCHAR2(32)    NOT NULL,
  type              VARCHAR2(32)    NOT NULL,
  value_bound       VARCHAR2(128),
  value             VARCHAR2(128)   NOT NULL,
  remark            VARCHAR2(128),
  create_by         NUMBER(20)      NOT NULL,
  create_date 		NUMBER(13)      NOT NULL,
  update_by         NUMBER(20),
  update_date       NUMBER(13),
  CONSTRAINT Parameter_pk PRIMARY KEY (id)
);
COMMENT ON COLUMN Parameter.id IS '主键';
COMMENT ON COLUMN Parameter.system_name IS '系统名称';
COMMENT ON COLUMN Parameter.parameter_name IS '参数名称';
COMMENT ON COLUMN Parameter.describe IS '参数描述';
COMMENT ON COLUMN Parameter.division IS '参数大类';
COMMENT ON COLUMN Parameter.type IS '参数类型(文本、单选、多选)';
COMMENT ON COLUMN Parameter.value_bound IS '参数值值域';
COMMENT ON COLUMN Parameter.value IS ' 参数值';
COMMENT ON COLUMN Parameter.remark IS '备注';
COMMENT ON COLUMN Parameter.create_by IS '创建人';
COMMENT ON COLUMN Parameter.create_date IS '创建时间';
COMMENT ON COLUMN Parameter.update_by IS '修改人';
COMMENT ON COLUMN Parameter.update_date IS '修改时间';
COMMENT ON TABLE Parameter IS '参数表';

declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Region');
    if num > 0 then
        execute immediate 'drop table Region';
    end if;
end;
/
CREATE TABLE Region(
   id           NUMBER(20)      NOT NULL,
   s_code       VARCHAR2(64)    NOT NULL,
   l_code       VARCHAR2(64)    NOT NULL,
   en_name      VARCHAR2(128),
   cn_name      VARCHAR2(128),
   area         VARCHAR2(256),
   sort_num     NUMBER(10,0),
   CONSTRAINT Region_PK PRIMARY KEY(id)
);
COMMENT ON COLUMN Region.id IS '主键';
COMMENT ON COLUMN Region.s_code IS '国际简码';
COMMENT ON COLUMN Region.l_code IS '国际代码';
COMMENT ON COLUMN Region.en_name IS '英文名称';
COMMENT ON COLUMN Region.cn_name IS '中文名称';
COMMENT ON COLUMN Region.area IS '区域(亚洲、非洲)';
COMMENT ON COLUMN Region.sort_num IS '排序号';
COMMENT ON TABLE Region IS '区域代码';

declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Template');
    if num > 0 then
        execute immediate 'drop table Template';
    end if;
end;
/
CREATE TABLE Template(
   id           NUMBER(20)      NOT NULL,
   tpl_key      VARCHAR2(64)    NOT NULL,
   lang         VARCHAR2(8),
   type         VARCHAR2(16)    NOT NULL,
   title        VARCHAR2(256)   NOT NULL,
   content      VARCHAR2(4000)  NOT NULL,
   dest         VARCHAR2(256),
   create_by    NUMBER(20)      NOT NULL,
   create_date  NUMBER(13)      NOT NULL,
   CONSTRAINT msg_template_pk PRIMARY KEY(id),
   CONSTRAINT msg_template_casuniques UNIQUE(tpl_key, lang , type)
);
COMMENT ON COLUMN Template.id IS '主键';
COMMENT ON COLUMN Template.tpl_key IS '模版KEY';
COMMENT ON COLUMN Template.lang IS '语言编码（en_US,zh_CN,zh_HK)';
COMMENT ON COLUMN Template.type IS '模版类型(email:邮件、sms:短信)';
COMMENT ON COLUMN Template.title IS '标题（邮件用）';
COMMENT ON COLUMN Template.content IS '模版内容';
COMMENT ON COLUMN Template.dest IS '描述';
COMMENT ON COLUMN Template.create_by IS '创建人';
COMMENT ON COLUMN Template.create_date IS '创建时间';
COMMENT ON TABLE Template IS '消息模版';

declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('USER_LOG');
    if num > 0 then
        execute immediate 'drop table USER_LOG';
    end if;
end;
/
CREATE TABLE USER_LOG
(  ID           NUMBER(20) NOT NULL,
   USER_ID      NUMBER(20) NOT NULL,
   USER_NAME   VARCHAR2(128),
   IP_ADDR      VARCHAR2(64),
   URL          VARCHAR2(128),
   CONTENT      VARCHAR2(256),
   RUN_TIME     NUMBER(8,5),
   CREATE_DATE  NUMBER(13),
   CONSTRAINT OPERATION_LOG_PK PRIMARY KEY (ID)
);
COMMENT ON COLUMN USER_LOG.ID IS '主键';
COMMENT ON COLUMN USER_LOG.USER_ID IS '用户ID';
COMMENT ON COLUMN USER_LOG.USER_NAME IS '用户名称';
COMMENT ON COLUMN USER_LOG.IP_ADDR IS '来访IP';
COMMENT ON COLUMN USER_LOG.URL IS '请求地址';
COMMENT ON COLUMN USER_LOG.CONTENT IS '操作说明';
COMMENT ON COLUMN USER_LOG.RUN_TIME IS '执行时间';
COMMENT ON COLUMN USER_LOG.CREATE_DATE IS '创建时间';
COMMENT ON TABLE USER_LOG  IS '用户日志';

declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('MSG_LOG');
    if num > 0 then
        execute immediate 'drop table MSG_LOG';
    end if;
end;
/
CREATE TABLE MSG_LOG(
   id           NUMBER(20)      NOT NULL,
   type         VARCHAR2(5)     NOT NULL,
   object       VARCHAR2(64)    NOT NULL,
   content      clob            NOT NULL,
   CREATE_DATE  NUMBER(13)      NOT NULL,
   status       NUMBER(1)       NOT NULL,
   CONSTRAINT MSG_LOG_PK PRIMARY KEY(id)
);
COMMENT ON COLUMN MSG_LOG.id IS '主键';
COMMENT ON COLUMN MSG_LOG.type IS '消息类型(email:邮件、sms:短信)';
COMMENT ON COLUMN MSG_LOG.object IS ' 发送对象';
COMMENT ON COLUMN MSG_LOG.content IS '内容';
COMMENT ON COLUMN MSG_LOG.CREATE_DATE IS '创建时间';
COMMENT ON COLUMN MSG_LOG.status IS '发送状态（0：成功，1：失败）';
COMMENT ON TABLE MSG_LOG IS '消息发送日志';

declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('ACCOUNT_LOG');
    if num > 0 then
        execute immediate 'drop table ACCOUNT_LOG';
    end if;
end;
/
CREATE TABLE ACCOUNT_LOG
(  ID           NUMBER(20) NOT NULL,
   ACCOUNT_ID   NUMBER(20) NOT NULL,
   ACCOUNT_NAME VARCHAR2(128),
   IP_ADDR      VARCHAR2(64),
   URL          VARCHAR2(128),
   CONTENT      VARCHAR2(256),
   RUN_TIME     NUMBER(8,5),
   CREATE_DATE  NUMBER(13),
   CONSTRAINT ACCOUNT_LOG_PK PRIMARY KEY (ID)
);
COMMENT ON COLUMN ACCOUNT_LOG.ID IS '主键';
COMMENT ON COLUMN ACCOUNT_LOG.ACCOUNT_ID IS '用户ID';
COMMENT ON COLUMN ACCOUNT_LOG.ACCOUNT_NAME IS '帐户昵称';
COMMENT ON COLUMN ACCOUNT_LOG.IP_ADDR IS '来访IP';
COMMENT ON COLUMN ACCOUNT_LOG.URL IS '请求地址';
COMMENT ON COLUMN ACCOUNT_LOG.CONTENT IS '操作说明';
COMMENT ON COLUMN ACCOUNT_LOG.RUN_TIME IS '执行时间';
COMMENT ON COLUMN ACCOUNT_LOG.CREATE_DATE IS '创建时间';
COMMENT ON TABLE ACCOUNT_LOG  IS '帐户日志';
