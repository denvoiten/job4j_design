create table roles(
    id serial primary key,
    name varchar(255)
);

create table rules(
    id serial primary key,
    name varchar(255)
);

create table roles_rules(
    id serial primary key,
    roles_id int references roles(id),
    rules_id int references rules(id)
);

create table users(
    id serial primary key,
    name varchar(255),
    roles_id int references roles(id)
);

create table category(
    id serial primary key,
    name varchar(255)
);

create table state(
    id serial primary key,
    name varchar(255)
);

create table item(
    id serial primary key,
    name varchar(255),
    users_id int references users(id),
    category_id int references category(id),
    state_id int references state(id)
);

create table comments(
    id serial primary key,
    name varchar(255),
    item_id int references item(id)
);

create table attachs(
    id serial primary key,
    name varchar(255),
    item_id int references item(id)
);