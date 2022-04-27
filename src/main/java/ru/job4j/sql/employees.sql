create table employees(
id serial primary key,
name varchar(255),
yearsOfExp integer,
available boolean
);

insert into employees(name, yearsOfExp, available) values('Bob', '7', 'false');
select * from cars;
update employees set name = 'Boby';
select * from cars;
delete from employees;