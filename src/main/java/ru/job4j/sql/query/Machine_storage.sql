create table body(
    id serial primary key,
    name varchar(255)
);

create table engine(
    id serial primary key,
    name varchar(255)
);

create table transmission(
    id serial primary key,
    name varchar(255)
);

create table car(
    id serial primary key,
    name varchar(255),
    body_id int references body(id),
    engine_id int references engine(id),
    transmission_id int references transmission(id)
);

insert into body(name) values('Sedan'), ('Supercar'), ('Pickup'), ('Minivan'), ('SUV');
insert into engine(name) values('Diesel'), ('Petrol'), ('Hybrid'), ('Electro');
insert into transmission(name) values('Manual'), ('Automatic'), ('Semi-automatic'), ('CVT');

insert into car(name, body_id, engine_id, transmission_id) values('Honda', 1, 3, 2);
insert into car(name, body_id, engine_id, transmission_id) values('Porshe', 2, 2, 1);
insert into car(name, body_id, engine_id, transmission_id) values('Dodge', 3, 1, 1);
insert into car(name, body_id, engine_id, transmission_id) values('Peugeot', 4, 2, 3);
insert into car(name, engine_id, transmission_id) values('Mercedes', 1, 1);

select c.name as Car, b.name as Body, e.name as Engine, t.name as Transmission from car c
left join body b on c.body_id = b.id
left join engine e on c.engine_id = e.id
left join transmission t on c.transmission_id = t.id;

select b.id, b.name as Body from body b
left join car c on c.body_id = b.id
where c.id is null;

select e.id, e.name as Engine from engine e
left join car c on c.engine_id = e.id
where c.id is null;

select t.id, t.name as Transmission from transmission t
left join car c on c.transmission_id = t.id
where c.id is null;