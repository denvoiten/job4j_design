create table team(
    id serial primary key,
    name varchar(255)
);

create table players(
    id serial primary key,
    name varchar(255),
    team_id int references team(id)
);

insert into team(name) values('Liverpool');
insert into players(name, team_id) values('Fabinho', 1);
insert into players(name, team_id) values('Mane', 1);


