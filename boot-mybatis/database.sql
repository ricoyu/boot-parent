drop database if exists tuling;

create database tuling default character set utf8mb4 default collate utf8mb4_general_ci;

use tuling;
grant all on tuling.* to 'rico'@'%';
flush privileges;

drop table if exists t_user;
create table t_user (
    id int primary key auto_increment,
    name varchar(20) not null,
    gendle tinyint not null default 0,
    create_time datetime not null default now(),
    update_time datetime not null default now()
);

