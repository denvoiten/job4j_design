create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values('Pristis microdon fish', 5000, date '1900-02-04');
insert into fauna(name, avg_age, discovery_date) values('Black butterfly', 30, null);
insert into fauna(name, avg_age, discovery_date) values('Ussurian tiger', 38800, date '2000-05-06');
insert into fauna(name, avg_age, discovery_date) values('Panda', 45666, date '1955-01-09');
insert into fauna(name, avg_age, discovery_date) values('White whale', 63124, null);
insert into fauna(name, avg_age, discovery_date) values('Bulldog', 2920, date '1903-12-23');

select * from fauna where name like '%fish%';
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';
select * from fauna where avg_age >= 10000 and avg_age <= 21000;