create table passport(
    id serial primary key,
    seria int,
    number int
);

create table people(
    id serial primary key,
    name varchar(255),
    passport_id int references passport(id) unique
);

insert into  passport(seria, number) values(8822, 8313499);
insert into  people(name, passport_id) values('Bob', 1);