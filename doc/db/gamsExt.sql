--删除表空间
DROP TABLESPACE "gams" INCLUDING CONTENTS AND DATAFILES;
--删除用户
DROP USER gams CASCADE;

--创建表空间
CREATE TABLESPACE gams DATAFILE 'D:\Oracle\oradata\ORCL\gams.dbf'
SIZE 512M AUTOEXTEND ON NEXT 512M MAXSIZE UNLIMITED
EXTENT MANAGEMENT LOCAL;

--创建用户
CREATE USER gamsDev IDENTIFIED BY gamsDev DEFAULT TABLESPACE gams TEMPORARY TABLESPACE temp;

ALTER USER gamsDev QUOTA UNLIMITED ON gams;

--赋予权限
GRANT CONNECT, RESOURCE TO gamsDev;
GRANT DEBUG CONNECT SESSION TO gamsDev;
GRANT EXECUTE ANY PROCEDURE TO gamsDev;
GRANT create view to gamsDev;
--revoke connect,resource,DEBUG CONNECT SESSION from gamsDev;
--grant connect,resource,dba to gamsDev;


alter user gamsDev identified by gamsDev;

alter user gamsDev account unlock;
