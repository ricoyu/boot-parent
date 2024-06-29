create database tx_test default character set utf8 default collate utf8_general_ci;
grant all on tx_test.* to 'rico'@'%' identified by '123456';
flush privileges;
    
use tx_test;

create table `user` (
    id int not null primary key auto_increment,
    name varchar(20) not null,
    gendle tinyint not null default 0,
    createTime datetime not null default now(),
    updateTime datetime not null default now());
