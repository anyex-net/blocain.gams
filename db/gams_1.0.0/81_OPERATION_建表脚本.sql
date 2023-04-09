declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Banner');
    if num > 0 then
        execute immediate 'drop table Banner';
    end if;
end;
/
CREATE TABLE Banner
(  id                       NUMBER(20)      NOT NULL,
   title                    VARCHAR2(64)    NOT NULL, 
   summary                  VARCHAR2(256)   NOT NULL,
   pict_url                 VARCHAR2(512)   NOT NULL,
   detail_link              VARCHAR2(256),
   lang                     VARCHAR2(8),
   sort_num                 NUMBER(8)       NOT NULL,
   status                   NUMBER(1)       NOT NULL,
   create_by                NUMBER(20)      NOT NULL,
   create_date 		        NUMBER(13)      NOT NULL,
   update_by                NUMBER(20),
   update_date              NUMBER(13),
   CONSTRAINT Banner_PK PRIMARY KEY (id)
 );
COMMENT ON COLUMN Banner.id IS '主键';  
COMMENT ON COLUMN Banner.title IS '标题';
COMMENT ON COLUMN Banner.summary IS '摘要简介';
COMMENT ON COLUMN Banner.pict_url IS '图片URL中文版';
COMMENT ON COLUMN Banner.lang IS '语言标识';
COMMENT ON COLUMN Banner.detail_link IS '详情链接';
COMMENT ON COLUMN Banner.sort_num IS '排序顺序';
COMMENT ON COLUMN Banner.status IS '状态(0待发布、1已发布)';
COMMENT ON COLUMN Banner.create_by IS '创建人';
COMMENT ON COLUMN Banner.create_by IS '创建时间';
COMMENT ON COLUMN Banner.update_by IS '修改人';
COMMENT ON COLUMN Banner.update_date IS '修改时间';
COMMENT ON TABLE Banner IS 'Banner信息';

declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Express');
    if num > 0 then
        execute immediate 'drop table Express';
    end if;
end;
/
CREATE TABLE Express
(  id                       NUMBER(20)      NOT NULL,
   type                     VARCHAR2(16)    NOT NULL, 
   summary                  VARCHAR2(256)   NOT NULL,
   pict_url                 VARCHAR2(512)   NOT NULL,
   content                  VARCHAR2(4000)  NOT NULL,
   lang                     VARCHAR2(8),
   status                   NUMBER(1)       NOT NULL,
   is_top                   NUMBER(1)       NOT NULL,
   create_by                NUMBER(20)      NOT NULL,
   create_date 		        NUMBER(13)      NOT NULL,
   update_by                NUMBER(20),
   update_date              NUMBER(13),
   CONSTRAINT Express_PK PRIMARY KEY (id)
 );
COMMENT ON COLUMN Express.id IS '主键';  
COMMENT ON COLUMN Express.type IS '类型(默认express)';
COMMENT ON COLUMN Express.summary IS '摘要';
COMMENT ON COLUMN Express.pict_url IS '主图URL';
COMMENT ON COLUMN Express.content IS '内容';
COMMENT ON COLUMN Express.lang IS '语言标识';
COMMENT ON COLUMN Express.status IS '状态(0待发布、1已发布)';
COMMENT ON COLUMN Express.is_top IS '是否置顶(0否、1是)';
COMMENT ON COLUMN Express.create_by IS '创建人';
COMMENT ON COLUMN Express.create_by IS '创建时间';
COMMENT ON COLUMN Express.update_by IS '修改人';
COMMENT ON COLUMN Express.update_date IS '修改时间';
COMMENT ON TABLE Express IS '快讯信息';

declare
    num   number;
begin
    select count(1) into num from user_tables where table_name = upper('Stock_Hot_Config');
    if num > 0 then
        execute immediate 'drop table Stock_Hot_Config';
    end if;
end;
/
CREATE TABLE Stock_Hot_Config
(  id                       NUMBER(20)      NOT NULL,
   stockinfo_id             NUMBER(20)      NOT NULL, 
   remark                   VARCHAR2(256)   NOT NULL,
   create_by                NUMBER(20)      NOT NULL,
   create_date 		        NUMBER(13)      NOT NULL,
   update_by                NUMBER(20),
   update_date              NUMBER(13),
   CONSTRAINT Stock_Hot_Config_PK PRIMARY KEY (id),
   CONSTRAINT Stock_Hot_Config_unique UNIQUE(stockinfo_id)
 );
COMMENT ON COLUMN Stock_Hot_Config.id IS '主键';  
COMMENT ON COLUMN Stock_Hot_Config.stockinfo_id IS '证券信息ID';
COMMENT ON COLUMN Stock_Hot_Config.remark IS '备注';
COMMENT ON COLUMN Stock_Hot_Config.create_by IS '创建人';
COMMENT ON COLUMN Stock_Hot_Config.create_by IS '创建时间';
COMMENT ON COLUMN Stock_Hot_Config.update_by IS '修改人';
COMMENT ON COLUMN Stock_Hot_Config.update_date IS '修改时间';
COMMENT ON TABLE Stock_Hot_Config IS '证券热门推荐配置';
