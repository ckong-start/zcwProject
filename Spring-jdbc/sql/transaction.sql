##创建tx数据库
drop database if exists `tx`;
CREATE database `tx`;
##切换tx数据库
USE `tx`;

##创建用户表
CREATE TABLE `user` (
  `id` int primary key auto_increment,	
  `username` varchar(50) NOT NULL,
  `money` int(11) DEFAULT NULL
);
##插入数据
insert  into `user`(`username`,`money`) values ('张三',1000),('李四',1000);

##创建图书表
create table `book`(
    `id` int primary key auto_increment,
    `name` varchar(500) not null,
    `stock` int
);
##插入数据
insert into book(`name`,`stock`) values('java编程思想',100),('C++编程思想',100);

##查看数据
select * from book;
select * from user;
