declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('feedback');
  if num > 0 then
    execute immediate 'drop table feedback';
  end if;
end;
/
-- 问题反馈
CREATE TABLE feedback(
  id NUMBER(20) NOT NULL,
  type VARCHAR2(16) NOT NULL,
  lang VARCHAR2(8) NOT NULL,
  status NUMBER(2) NOT NULL,
  name VARCHAR2(64),
  email VARCHAR2(64),  
  mobile VARCHAR2(16),  
  answer VARCHAR2(1024), 
  content VARCHAR2(1024) NOT NULL,
  create_by NUMBER(20) NOT NULL,
  create_date NUMBER(13) NOT NULL,
  update_by NUMBER(20),
  update_date NUMBER(13),
  CONSTRAINT feedback_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN feedback.id IS 'id';
COMMENT ON COLUMN feedback.type IS '反馈类型:1.离线反馈,2.在线客服问题';
COMMENT ON COLUMN feedback.lang IS '语言(en_US,zh_CN)';
COMMENT ON COLUMN feedback.status IS '状态(0待解决、1已解决)';
COMMENT ON COLUMN feedback.name IS '姓名';
COMMENT ON COLUMN feedback.email IS '邮箱';
COMMENT ON COLUMN feedback.mobile IS '手机号';
COMMENT ON COLUMN feedback.answer IS '回答';
COMMENT ON COLUMN feedback.content IS '反馈内容';
COMMENT ON COLUMN feedback.create_by IS '创建人';
COMMENT ON COLUMN feedback.create_date IS '创建时间';
COMMENT ON COLUMN feedback.update_by IS '更新人';
COMMENT ON COLUMN feedback.update_date IS '更新时间';
COMMENT ON TABLE feedback IS '问题反馈';

