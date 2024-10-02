-- db 생성
create database calendar default character set utf8 collate utf8_general_ci;

-- db 사용
use calendar;

-- 테이블 생성
create table calendar (
                          id int auto_increment primary key,
                          author varchar(100) not null,
                          todolist varchar(255) not null,
                          password varchar(100) not null,
                          createDate timestamp default current_timestamp,
                          updateDate timestamp default current_timestamp on update current_timestamp
);
