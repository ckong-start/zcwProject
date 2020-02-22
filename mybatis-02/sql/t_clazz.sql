## 一对多数据表
## 创建班级表
create table t_clazz(
	`id` int primary key auto_increment,
	`name` varchar(50)
);

## 插入班级信息
insert into t_clazz(`name`) values('0228');
insert into t_clazz(`name`) values('0325');
insert into t_clazz(`name`) values('0420');
insert into t_clazz(`name`) values('0515');

## 创建学生表
create table t_student(
	`id` int primary key auto_increment,
	`name` varchar(50),
	`clazz_id` int,
	foreign key(`clazz_id`) references t_clazz(`id`)
);

## 插入班级信息
insert into t_student(`name`,`clazz_id`) values('stu0228_1',1);
insert into t_student(`name`,`clazz_id`) values('stu0228_2',1);
insert into t_student(`name`,`clazz_id`) values('stu0228_3',1);
insert into t_student(`name`,`clazz_id`) values('stu0325_1',2);
insert into t_student(`name`,`clazz_id`) values('stu0325_2',2);
insert into t_student(`name`,`clazz_id`) values('stu0420_1',3);
