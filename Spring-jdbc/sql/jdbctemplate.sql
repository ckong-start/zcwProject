drop database if exists jdbctemplate;

create database jdbctemplate;

use jdbctemplate;

create table `employee` (
  `id` int(11) primary key auto_increment,
  `name` varchar(100) default null,
  `salary` decimal(11,2) default null
);

insert  into `employee`(`id`,`name`,`salary`) 
values (1,'李三',5000.23),(2,'李四',4234.77),(3,'王五',9034.51),
(4,'赵六',8054.33),(5,'孔七',6039.11),(6,'曹八',7714.11);

select * from employee;
