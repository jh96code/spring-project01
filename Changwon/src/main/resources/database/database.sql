create table board(
   num int not null auto_increment,
   id varchar(10) not null,
    title varchar(50) not null,
    bdate date,
   content varchar(200),
    hit int,
   img varchar(100),
   PRIMARY KEY (num)
);

CREATE TABLE member (
    id VARCHAR(10) NOT NULL,
    password VARCHAR(10) NOT NULL,
    name VARCHAR(10) NOT NULL,
    gender VARCHAR(4),
    birth VARCHAR(20),
    mail VARCHAR(30),
    phone VARCHAR(20),
    address VARCHAR(90),
    regist_day VARCHAR(50),
    admincheck boolean,
    PRIMARY KEY (id)
)  DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS spots(
   aid INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(20) not null,
   location  varcharacter(30) not null,
   description TEXT,
      number VARCHAR(20),
   time VARCHAR(20),
   img VARCHAR(20)
);
alter table spots add unique name(name);

create table score(
   num int primary key auto_increment,
   name varchar(20) not null,
    score int not null, 
   Questions varchar(30),
    foreign key(name) references spots(name)
);


create table if not exists goodslist (
   gId int auto_increment,
   gName varchar(50) not null unique,
   gPrice int,
   gDescription varchar(50),
   gCategory varchar(10) not null,
   gStock int,
   gImg varchar(20),
   primary key(gId)
)default charset=utf8mb4;

create table if not exists orderlist (
   orderNum int not null auto_increment,
   orderAt datetime not null ,
   getNum integer,
   userId varchar(20),
   buygoods varchar(20),
   primary key(orderNum)
)default charset=utf8mb4;

create table board_reply(
   num INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    content varchar(200) not null,
    regist_day date,
    board_num int not null,
    member_id VARCHAR(10) NOT NULL,
    foreign key(board_num) references board(num) on update cascade on delete cascade,
    foreign key(member_id) references member(id) on update cascade on delete cascade
) DEFAULT CHARSET=UTF8;

create table if not exists cslist(
   num int not null auto_increment primary key,
   title varchar(40) not null,
   category varchar(20) not null,
   content varchar(250) not null,
   reply_content varchar(250),
    member_id varchar(10) not null,
    reply_member_id varchar(10),
    send_day date not null,
    reply_day date,
    foreign key(member_id) references member(id) on update cascade on delete cascade, 
    foreign key(reply_member_id) references member(id) on update cascade on delete cascade 
) DEFAULT CHARSET=UTF8;

create table if not exists address (
   addressNum int not null auto_increment primary key,
   addressId varchar(20) not null ,
   addressName varchar(10) not null ,
   addressPhone varchar(20) not null,
   addressAdr varchar(50) not null
)default charset=utf8mb4;