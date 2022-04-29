select * from product p, type t
where p.type_id = t.id
and t.name like 'СЫР';

select * from product
where name like '%мороженое%';

select * from product
where expired_date < current_date;

select * from product
where price = (select max(price) from product);

select t.name, count(p.id)
from product as p, type as t
where p.type_id = t.id
group by t.name;

select * from product p, type t
where p.type_id = t.id
and t.name in ('СЫР', 'МОЛОКО');

select t.name, count(p.id)
from product as p, type as t
where p.type_id = t.id
group by t.name
having count(p.id) < 10;

select p.name as Наименование, t.name as Тип
from product as p, type as t
where p.type_id = t.id;