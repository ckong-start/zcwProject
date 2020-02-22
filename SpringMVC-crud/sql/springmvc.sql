drop database if exists springmvc;

create database springmvc;

use springmvc; 

##创建图书表
create table t_book(
	`id` int(11) primary key auto_increment, 	## 主键
	`name` varchar(50) not null,				## 书名 
	`author` varchar(50) not null,				## 作者
	`price` decimal(11,2) not null,				## 价格
	`sales` int(11) not null,					## 销量
	`stock` int(11) not null					## 库存
);


## 插入初始化测试数据
insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock`) 
values(null , 'java从入门到放弃' , '国哥' , 80 , 9999 , 9);

insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock`) 
values(null , '数据结构与算法' , '严敏君' , 78.5 , 6 , 13);

insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock`) 
values(null , '怎样拐跑别人的媳妇' , '龙伍' , 68, 99999 , 52);

insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock`) 
values(null , '木虚肉盖饭' , '小胖' , 16, 1000 , 50);

insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock`) 
values(null , 'C++编程思想' , '刚哥' , 45.5 , 14 , 95);

insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock`) 
values(null , '蛋炒饭' , '周星星' , 9.9, 12 , 53);
 
insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock`) 
values(null , '赌神' , '龙伍' , 66.5, 125 , 535);

insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock`) 
values(null , 'Java编程思想' , '阳哥' , 99.5 , 47 , 36);

insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock`) 
values(null , 'JavaScript从入门到精通' , '婷姐' , 9.9 , 85 , 95);

insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock`) 
values(null , 'cocos2d-x游戏编程入门' , '国哥' , 49, 52 , 62);

insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock`) 
values(null , 'C语言程序设计' , '谭浩强' , 28 , 52 , 74);

insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock`) 
values(null , 'Lua语言程序设计' , '雷丰阳' , 51.5 , 48 , 82);

insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock`) 
values(null , '西游记' , '罗贯中' , 12, 19 , 9999);

insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock`) 
values(null , '水浒传' , '华仔' , 33.05 , 22 , 88);
 
insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock`) 
values(null , '操作系统原理' , '刘优' , 133.05 , 122 , 188);
 
insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock`) 
values(null , '数据结构 java版' , '封大神' , 173.15 , 21 , 81);
 
insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock`) 
values(null , 'UNIX高级环境编程' , '乐天' , 99.15 , 210 , 810);
 
insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock`) 
values(null , 'javaScript高级编程' , '国哥' , 69.15 , 210 , 810);
 
insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock`) 
values(null , '大话设计模式' , '国哥' , 89.15 , 20 , 10);
 
insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock`) 
values(null , '人月神话' , '刚哥' , 88.15 , 20 , 80); 


## 查看表内容
select id,name,author,price,sales,stock from t_book;
