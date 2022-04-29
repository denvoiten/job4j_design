create table departments(
    id serial primary key,
    name varchar(255)
);

create table epmloyees(
    id serial primary key,
    name varchar(255),
    departments_id int references departments(id)
);

insert into departments(name)
values('HR'), ('Research and development'), ('Marketing'), ('Security'), ('Quality Department');

insert into employees(name, departments_id) values('Denis', 2);
insert into employees(name, departments_id) values('Sergey', 2);
insert into employees(name, departments_id) values('Bob', 4);
insert into employees(name, departments_id) values('John', 3);
insert into employees(name, departments_id) values('Bobuk', 4);
insert into employees(name, departments_id) values('Sveta', 1);
insert into employees(name, departments_id) values('Inna', 1);
insert into employees(name, departments_id) values('Kirill', 2);

select * from employees e
left join departments d on e.departments_id = d.id;

select * from departments d
left join  employees e on e.departments_id = d.id;

select * from departments d
right join employees e on e.departments_id = d.id;

select * from employees e
full join departments d on e.departments_id = d.id;

select * from employees e
cross join departments d;

/*3. Используя left join найти департаменты, у которых нет работников*/
select d.name as "Департамент без сотрудников" from departments d
left join employees e on d.id=e.departments_id
where e.departments_id is null
group by d.id;

/*4. Используя left и right join написать запросы, которые давали бы одинаковый результат*/
select * from departments d
left join  employees e on e.departments_id = d.id
where e.departments_id is not null;

select * from departments d
right join employees e on e.departments_id = d.id
where e.departments_id is not null;

create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(255)
);

insert into teens(name, gender) values('Boris', 'male');
insert into teens(name, gender) values('Sergey', 'male');
insert into teens(name, gender) values('Anna', 'female');
insert into teens(name, gender) values('Sveta', 'female');
insert into teens(name, gender) values('Alex', 'male');
insert into teens(name, gender) values('Alena', 'female');

select t.name, t.gender, t1.name, t1.gender
from teens t cross join teens t1
where t.gender = 'male' and t1.gender = 'female';