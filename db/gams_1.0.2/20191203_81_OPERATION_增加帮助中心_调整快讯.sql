declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('help_center');
  if num > 0 then
    execute immediate 'drop table help_center';
  end if;
end;
/ 
-- 帮助中心
CREATE TABLE help_center
(
  id 						        NUMBER(20) 				NOT NULL,
  problem_level_one 		VARCHAR2(128) 		NOT NULL,
  problem_level_two 		VARCHAR2(512) 		NOT NULL,
  problem_type 				  VARCHAR2(64)			NOT NULL,
  problem_solving 			VARCHAR2(4000)		NOT NULL,
  search_content 			VARCHAR2(4000)		NOT NULL,
  pict_url 					    VARCHAR2(512),
  status 					      NUMBER(2) 				NOT NULL,
  lang 						      VARCHAR2(8),
  is_hot 					      NUMBER(1)				  NOT NULL,
  create_by 				    NUMBER(20) 				NOT NULL,
  create_date 				  NUMBER(13) 				NOT NULL,
  update_by 				    NUMBER(20),
  update_date 				  NUMBER(13),
  CONSTRAINT help_center_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN help_center.id IS 'id';
COMMENT ON COLUMN help_center.problem_level_one IS '问题(一级)';
COMMENT ON COLUMN help_center.problem_level_two IS '问题(二级)';
COMMENT ON COLUMN help_center.problem_type IS '问题类型(开户类，交易类..)';
COMMENT ON COLUMN help_center.problem_solving IS '问题解答';
COMMENT ON COLUMN help_center.search_content IS '搜索问题解答内容(纯文本)';
COMMENT ON COLUMN help_center.pict_url IS '图片url';
COMMENT ON COLUMN help_center.status IS '状态(0待发布、1已发布、2已下架)';
COMMENT ON COLUMN help_center.lang IS '语言(en_US,zh_CN,zh_HK)';
COMMENT ON COLUMN help_center.is_hot IS '是否热门(0否、1是)';
COMMENT ON COLUMN help_center.create_by IS '创建人';
COMMENT ON COLUMN help_center.create_date IS '创建时间';
COMMENT ON COLUMN help_center.update_by IS '修改人';
COMMENT ON COLUMN help_center.update_date IS '更新时间';
COMMENT ON TABLE help_center IS '帮助中心';

declare
  num   number;
begin
  select count(1) into num from user_tables where table_name = upper('express');
  if num > 0 then
    execute immediate 'drop table express';
  end if;
end;
/ 
-- 快讯信息
CREATE TABLE express
(
  id 						    NUMBER(20) 				NOT NULL,
  title						  VARCHAR2(512)			NOT NULL,
  type						  VARCHAR2(16)			NOT NULL,
  source					  VARCHAR2(512),
  summary					  VARCHAR2(512)			NOT NULL,
  pict_url				  VARCHAR2(512),
  content					  VARCHAR2(4000)	  NOT NULL,
  lang						  VARCHAR2(8),
  status					  NUMBER(2) 				NOT NULL,
  is_top					  NUMBER(1) 				NOT NULL,
  reading_quantity	NUMBER(20) 	DEFAULT 0   NOT NULL,
  thumbs_up  				NUMBER(20) 	DEFAULT 0   NOT NULL,	
  create_by 				NUMBER(20) 				NOT NULL,
  create_date 			NUMBER(13) 				NOT NULL,
  update_by 				NUMBER(20),
  update_date 			NUMBER(13),
  CONSTRAINT express_PK PRIMARY KEY (id)
);
COMMENT ON COLUMN express.id IS 'id';
COMMENT ON COLUMN express.title IS '标题';
COMMENT ON COLUMN express.type IS '类型(资讯、热门、新闻)';
COMMENT ON COLUMN express.source IS '来源';
COMMENT ON COLUMN express.summary IS '摘要';
COMMENT ON COLUMN express.pict_url IS '图片url';
COMMENT ON COLUMN express.content IS '内容';
COMMENT ON COLUMN express.lang IS '语言(en_US,zh_CN,zh_HK)';
COMMENT ON COLUMN express.status IS '状态(0待发布、1已发布、2已下架)';
COMMENT ON COLUMN express.is_top IS '是否置顶(0否、1是)';
COMMENT ON COLUMN express.reading_quantity IS '阅读量';
COMMENT ON COLUMN express.thumbs_up IS '点赞数';
COMMENT ON COLUMN express.create_by IS '创建人';
COMMENT ON COLUMN express.create_date IS '创建时间';
COMMENT ON COLUMN express.update_by IS '修改人';
COMMENT ON COLUMN express.update_date IS '修改时间';
COMMENT ON TABLE express IS '快讯信息';
