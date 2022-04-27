create table people(
    id serial primary key,
    name varchar(255),
);

create table hobbies(
    id serial primary key,
    name varchar(255)
);

create table people_hobbies(
    id serial primary key,
    people_id int references people(id)
    hobbies_id int references hobbies(id)
);

insert into people(name) values('Den');
insert into people(name) values('Bob');

insert into  hobbies(name) values('Football');
insert into  hobbies(name) values('Hokey');
insert into  hobbies(name) values('Basketball');

insert into people_hobbies(people_id, hobbies_id) values(1, 1);
insert into people_hobbies(people_id, hobbies_id) values(1, 3);
insert into people_hobbies(people_id, hobbies_id) values(2, 2);
insert into people_hobbies(people_id, hobbies_id) values(2, 3);

select * from players;

