CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age integer,
    country text
);

insert into customers (id, first_name, last_name, age, country)
    values (1, 'Кирилл', 'Киселев', 21, 'Словения');
insert into customers (id, first_name, last_name, age, country)
    values (2, 'Ульяна', 'Титова', 22, 'Монголия');
insert into customers (id, first_name, last_name, age, country)
    values (3, 'Лев', 'Рожков', 23, 'Азербаджан');
insert into customers (id, first_name, last_name, age, country)
    values (4, 'Алиса', 'Костина', 24, 'Люксенбург');
insert into customers (id, first_name, last_name, age, country)
    values (5, 'Максим', 'Павлов', 25, 'Ватикан');
insert into customers (id, first_name, last_name, age, country)
    values (6, 'Полина', 'Игнатова', 26, 'Вьетнам');

select * from customers c
where c.age = (select min(age) from customers);

CREATE TABLE orders(
    id serial primary key,
    amount integer,
    customer_id integer references customers(id)
);

insert into orders (id, amount, customer_id) values (1, 1, 1);
insert into orders (id, amount, customer_id) values (2, 2, 2);
insert into orders (id, amount, customer_id) values (3, 3, 3);
insert into orders (id, amount, customer_id) values (4, 4, 4);
insert into orders (id, amount, customer_id) values (5, 5, 5);

select * from  customers cust
where cust.id
    not in (select orders.customer_id from orders);