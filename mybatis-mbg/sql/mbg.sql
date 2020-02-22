create database mbg;

use mbg;

create table t_user(
	`id` int primary key auto_increment,
	`username` varchar(30) not null unique,
	`password` varchar(40) not null,
	`email` varchar(50)
);

insert into t_user(`username`,`password`,`email`) values('admin','admin','admin@atguigu.com');
insert into t_user(`username`,`password`,`email`) values('wzg168','123456','admin@atguigu.com');
insert into t_user(`username`,`password`,`email`) values('admin168','123456','admin@atguigu.com');
insert into t_user(`username`,`password`,`email`) values('lisi','123456','admin@atguigu.com');
insert into t_user(`username`,`password`,`email`) values('wangwu','123456','admin@atguigu.com');

create table t_book(
	`id` int primary key auto_increment,
	`name` varchar(50),
	`author` varchar(50),
	`price`	decimal(11,2),
	`sales`	int,
	`stock` int
);


## 插入初始化测试数据
insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock` ) 
values(null , 'java从入门到放弃' , '国哥' , 80 , 9999 , 9);

insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock` ) 
values(null , '数据结构与算法' , '严敏君' , 78.5 , 6 , 13);

insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock` ) 
values(null , '怎样拐跑别人的媳妇' , '龙伍' , 68, 99999 , 52);

insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock` ) 
values(null , '木虚肉盖饭' , '小胖' , 16, 1000 , 50);

insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock` ) 
values(null , 'C++编程思想' , '刚哥' , 45.5 , 14 , 95);

insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock` ) 
values(null , '蛋炒饭' , '周星星' , 9.9, 12 , 53);
 
insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock` ) 
values(null , '赌神' , '龙伍' , 66.5, 125 , 535);

select * from t_user;
select * from t_book;
