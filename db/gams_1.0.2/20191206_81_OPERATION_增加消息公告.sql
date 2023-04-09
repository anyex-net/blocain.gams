declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('news_bulletin');
  if num > 0 then
    execute immediate 'drop table news_bulletin';
  end if;
end;
/ 
-- 消息公告
CREATE TABLE news_bulletin
 (
   id               NUMBER(20)  NOT NULL ,
   title            VARCHAR2(120)  NOT NULL ,
   type             VARCHAR2(16)  NOT NULL ,
   content          VARCHAR2(4000) NOT NULL ,
   lang             VARCHAR2(8)  NOT NULL ,
   status           NUMBER(2)  NOT NULL ,
   is_timed_job     NUMBER(1)  NOT NULL ,
   create_by        NUMBER(20)  NOT NULL ,
   create_date      NUMBER(13)  NOT NULL ,
   update_by        NUMBER(20)  ,
   update_date      NUMBER(13)  ,
   cron             NUMBER(13)  ,
   client_type      VARCHAR2(16)  NOT NULL ,
   release_date     NUMBER(13),  
   CONSTRAINT news_bulletin_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN news_bulletin.id IS 'id';
COMMENT ON COLUMN news_bulletin.title IS '标题';
COMMENT ON COLUMN news_bulletin.type IS '类型(公告）';
COMMENT ON COLUMN news_bulletin.content IS '内容';
COMMENT ON COLUMN news_bulletin.lang IS '语言(en_US,zh_CN,zh_HK)';
COMMENT ON COLUMN news_bulletin.status IS '状态(0待发布、1已发布、2已下架)';
COMMENT ON COLUMN news_bulletin.is_timed_job IS '是否定时任务';
COMMENT ON COLUMN news_bulletin.create_by IS '创建人';
COMMENT ON COLUMN news_bulletin.create_date IS '创建时间';
COMMENT ON COLUMN news_bulletin.update_by IS '修改人';
COMMENT ON COLUMN news_bulletin.update_date IS '修改时间';
COMMENT ON COLUMN news_bulletin.cron IS '定时时间';
COMMENT ON COLUMN news_bulletin.client_type IS '客户端类型（发布位置）';
COMMENT ON COLUMN news_bulletin.release_date IS '发布时间';
COMMENT ON TABLE  news_bulletin IS '消息公告';