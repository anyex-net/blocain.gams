declare
  num number;
begin
  select count(1) into num from user_tables where table_name = upper('OAUTH_CLIENT_DETAILS');
  if num > 0 then
    execute immediate 'drop table OAUTH_CLIENT_DETAILS';
  end if;
end;
/
CREATE TABLE OAUTH_CLIENT_DETAILS
(
  id                     NUMBER(20)    NOT NULL,
  client_id              VARCHAR2(256) NOT NULL,
  client_secret          VARCHAR2(256) NOT NULL,
  client_name            VARCHAR2(256) NOT NULL,
  client_uri             VARCHAR2(256) NOT NULL,
  client_icon_uri        VARCHAR2(256) NOT NULL,
  resource_ids           VARCHAR2(256) NOT NULL,
  scope                  VARCHAR2(20)  NOT NULL,
  grant_types            VARCHAR2(128) NOT NULL,
  redirect_uri           VARCHAR2(256) NOT NULL,
  logout_uri             VARCHAR2(256) NOT NULL,
  access_token_validity  NUMBER(6)     NOT NULL,
  refresh_token_validity NUMBER(6)     NOT NULL,
  description            VARCHAR2(512) NOT NULL,
  create_time            NUMBER(13)    NOT NULL,
  trusted                NUMBER(1)     NOT NULL,
  CONSTRAINT OAUTH_CLIENT_DETAILS_PK PRIMARY KEY (id)
);

declare
  num number;
begin
  select count(1) into num from user_tables where table_name = upper('OAUTH_ACCESS_TOKEN');
  if num > 0 then
    execute immediate 'drop table OAUTH_ACCESS_TOKEN';
  end if;
end;
/
CREATE TABLE OAUTH_ACCESS_TOKEN
(
  id                            NUMBER(20)    NOT NULL,
  create_time                   NUMBER(13)    NOT NULL,
  token_id                      VARCHAR2(256) NOT NULL,
  token_expired_seconds         NUMBER(13)    NOT NULL,
  authentication_id             VARCHAR2(256) NOT NULL,
  user_name                     VARCHAR2(64)  NOT NULL,
  client_id                     VARCHAR2(256) NOT NULL,
  token_type                    VARCHAR2(64)  NOT NULL,
  refresh_token                 VARCHAR(256)  NOT NULL,
  refresh_token_expired_seconds NUMBER(13)    NOT NULL,
  CONSTRAINT OAUTH_ACCESS_TOKEN_PK PRIMARY KEY (id)
);

declare
  num number;
begin
  select count(1) into num from user_tables where table_name = upper('OAUTH_CODE');
  if num > 0 then
    execute immediate 'drop table OAUTH_CODE';
  end if;
end;
/
CREATE TABLE OAUTH_CODE
(
  id          NUMBER(20)    NOT NULL,
  code        VARCHAR2(256) NOT NULL,
  user_name   VARCHAR2(64)  NOT NULL,
  client_id   VARCHAR2(256) NOT NULL,
  create_time NUMBER(13)    NOT NULL,
  CONSTRAINT OAUTH_CODE_PK PRIMARY KEY (id)
); 

INSERT INTO OAUTH_CLIENT_DETAILS("ID", "CLIENT_ID", "CLIENT_SECRET", "CLIENT_NAME", "CLIENT_URI", "CLIENT_ICON_URI", "RESOURCE_IDS", "SCOPE", "GRANT_TYPES", "REDIRECT_URI", "LOGOUT_URI", "ACCESS_TOKEN_VALIDITY", "REFRESH_TOKEN_VALIDITY", "DESCRIPTION", "CREATE_TIME", "TRUSTED") 
VALUES ('200000000000', 'trade', 'trade123456', 'TradeServer', 'http://www.devgams.com/account/manage', 'http://www.devgams.com/favicon.ico', 'trade', 'read,write', 'authorization_code,password,refresh_token,client_credentials', 'http://www.devgams.com/api/oauth/check', 'http://www.devgams.com/api/logout', '5000', '-1', '交易平台', '1560218787998', '1');

COMMIT;
 