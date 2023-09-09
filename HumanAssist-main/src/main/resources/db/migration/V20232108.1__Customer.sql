CREATE table Customer(
    id varchar(30) primary key,
    name varchar(25) not null,
    phoneNo varchar(15) not null,
    memberSince DATE not null,
    address varchar(100),
    planId int not null default 0
);