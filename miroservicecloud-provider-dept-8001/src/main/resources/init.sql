create database cloudDB01 character set UTF8;
grant all privileges on cloudDB01.* to root@'%' identified by '123456';
flush privileges;


create table dept(
 deptno BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 dname varchar(60),
 db_source varchar(60)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;  

insert into dept(dname,db_source)values('开发部',database());
insert into dept(dname,db_source)values('人事部',database());
insert into dept(dname,db_source)values('财务部',database());
insert into dept(dname,db_source)values('市场部',database());
insert into dept(dname,db_source)values('运维部',database());