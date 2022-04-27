create table roles(
    id serial primary key,
    name varchar(255)
);

create table rules(
    id serial primary key,
    name varchar(255)
);

create table role_rules(
    id serial primary key,
    roles_id references roles(id),
    rules_id references rules(id)
);

create table item(
    id serial primary key,
    name varchar(255),
    users_id references users(id),
    category_id references category(id),
    state_id references state(id)
);

create table users(
    id serial primary key,
    name varchar(255)
    roles_id references roles(id)
);

create table comments(
    id serial primary key,
    name varchar(255),
    item_id references item(id)
);

create table attachs(
    id serial primary key,
    name varchar(255),
    item_id references item(id)
);

create table category(
    id serial primary key,
    name varchar(255)
);

create table state(
    id serial primary key,
    name varchar(255)
);