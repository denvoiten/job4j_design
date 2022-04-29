create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('Nook', 4990), ('PS 5', 32990), ('Ryzen 5950x', 45000);

insert into people(name) values('Victor'), ('Den'), ('Elena'), ('Dmitry');

insert into devices_people(device_id, people_id) values(1, 1), (1, 3);
insert into devices_people(device_id, people_id) values(2, 3), (1, 2);
insert into devices_people(device_id, people_id) values(3, 2);

select avg(price) from devices;

select p.name, avg(d.price)
from devices as d
join devices_people dp on d.id = dp.device_id
join people p on p.id = dp.people_id
group by p.name;

select p.name, avg(d.price)
from devices as d
join devices_people dp on d.id = dp.device_id
join people p on p.id = dp.people_id
group by p.name
having avg(d.price) > 5000;

