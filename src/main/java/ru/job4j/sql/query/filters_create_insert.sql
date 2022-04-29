create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date date,
    price float
);

insert into type(name) values('СЫР'), ('КОЛБАСА'), ('МОЛОКО'), ('ХЛЕБ');
insert into product(name, type_id, expired_date, price) values('Сыр плавленный', 1, '2022-12-01', 256);
insert into product(name, type_id, expired_date, price) values('Сыр пармезан', 1, '2022-11-03', 789);
insert into product(name, type_id, expired_date, price) values('Сыр гауда', 1, '2022-10-05', 378);
insert into product(name, type_id, expired_date, price) values('Сыр чеддер', 1, '2022-12-30', 459);
insert into product(name, type_id, expired_date, price) values('Сыр родчестер', 1, '2022-09-11', 425);
insert into product(name, type_id, expired_date, price) values('Колбаса краковская', 2, '2022-05-11', 259);
insert into product(name, type_id, expired_date, price) values('Колбаса сервелат', 2, '2022-06-18', 380);
insert into product(name, type_id, expired_date, price) values('Колбаса салями', 2, '2022-08-13', 596);
insert into product(name, type_id, expired_date, price) values('Колбаса докторская', 2, '2022-07-03', 189);
insert into product(name, type_id, expired_date, price) values('Молоко веселый молочник', 3, '2022-05-03', 79);
insert into product(name, type_id, expired_date, price) values('Молоко простоквашино', 3, '2022-05-08', 69);
insert into product(name, type_id, expired_date, price) values('Молоко вкуснотеево', 3, '2022-05-11', 82);
insert into product(name, type_id, expired_date, price) values('Кельн', 4, '2022-05-01', 69);
insert into product(name, type_id, expired_date, price) values('Бородинский', 4, '2022-05-04', 55);
insert into product(name, type_id, expired_date, price) values('Батон московский', 4, '2022-05-03', 35);