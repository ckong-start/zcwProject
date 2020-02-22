## 一对一数据表
## 创建锁表
create table t_lock(
	`id` int primary key auto_increment,
	`name` varchar(50)
);


## 创建钥匙表
create table t_key(
	`id` int primary key auto_increment,
	`name` varchar(50),
	`lock_id` int ,
	foreign key(`lock_id`) references t_lock(`id`)
);

## 插入初始化数据
insert into t_lock(`name`) values('阿里巴巴');
insert into t_lock(`name`) values('华为');
insert into t_lock(`name`) values('联想');

insert into t_key(`name`,`lock_id`) values('马云',1);
insert into t_key(`name`,`lock_id`) values('任正非',2);
insert into t_key(`name`,`lock_id`) values('柳传志',3);
