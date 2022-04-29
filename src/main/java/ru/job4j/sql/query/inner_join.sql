create table vehicle(
    id serial primary key,
    seria varchar(255),
    number int,
    color varchar(255)
);

create table owner(
    id serial primary key,
    name varchar(255),
    vehicle_id int references vehicle(id)
);

insert into  vehicle(seria, number, color) values('HE', 265134, 'Red');
insert into  vehicle(seria, number, color) values('EE',  346791, 'Black');
insert into  vehicle(seria, number, color) values('AH', 461218, 'White');
insert into  vehicle(seria, number, color) values('AE', 561319, 'Red');

insert into owner(name, vehicle_id) values('Viktor', 1);
insert into owner(name, vehicle_id) values('Sveta', 3);
insert into owner(name, vehicle_id) values('Vova', 2);
insert into owner(name, vehicle_id) values('Andrey', 4);

select * from owner inner
join vehicle v
on owner.vehicle_id = v.id;

select vo.name, v.seria, v.number
from owner as vo
join vehicle as v
on vo.vehicle_id = v.id;

select vo.name as "VehicleOwner Name", v.seria as Серия,
v.number as "Vehicle Number", v.color as Цвет from owner as vo
join vehicle as v
on vo.vehicle_id = v.id;