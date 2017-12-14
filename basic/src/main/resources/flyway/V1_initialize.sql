-- flyway 迁移脚本
CREATE table Reader (
id int PRIMARY key;
username varchar(20) UNIQUE NOT NULL ;
password VARCHAR(30) not null;
fullname VARCHAR(30) NOT NULL;
)


CREATE TABLE Book (
id int primary KEY ;
author VARCHAR(40) not NULL;
description VARCHAR(1000) not NULL;
isbn VARCHAR(40) not NULL;
title VARCHAR(40) not NULL;
reader_username VARCHAR(40) not NULL;
)