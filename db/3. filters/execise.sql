create table type(
    id serial primary key,
    name varchar(255)
);

insert into type(id, name) values (1, 'СЫР');
insert into type(id, name) values (2, 'МОЛОКО');
insert into type(id, name) values (3, 'ОВОЩИ');
insert into type(id, name) values (4, 'ФРУКТЫ');
insert into type(id, name) values (5, 'ХЛЕБ');
insert into type(id, name) values (6, 'МОРОЖЕНОЕ');

create table abstractProduct(
    id serial primary key,
    name varchar(255),
    type_id integer references type(id),
    expired_date date,
    price float
);

insert into abstractProduct(name, type_id, expired_date, price)
    values ('Сыр полутвёрдый Ламбер 50% 230 г', 1, '30.07.2023', 259.99);
insert into abstractProduct(name, type_id, expired_date, price)
    values ('Сыр твёрдый Брест-Литовск финский нарезка, 45% 150 г', 1, '30.09.2023', 148.99);
insert into abstractProduct(name, type_id, expired_date, price)
    values ('Сыр твёрдый Cheese Gallery маасдам, 45% 180 г', 1, '31.07.2023', 241.99);
insert into abstractProduct(name, type_id, expired_date, price)
    values ('Молоко питьевое пастеризованное 2.5% ПЭТ Зелёная Линия, 900мл', 2, '30.09.2023', 65.99);
insert into abstractProduct(name, type_id, expired_date, price)
    values ('Молоко Простоквашино пастеризованное 2.5%, 930мл', 2, '30.09.2023', 69.99);
insert into abstractProduct(name, type_id, expired_date, price)
    values ('Молоко цельное отборное детское пастеризованное 3.4-4% Сарафаново, 930мл', 2, '31.07.2023', 83.99);
insert into abstractProduct(name, type_id, expired_date, price)
    values ('Перец Ласточка 1кг', 3, '30.09.2023', 79.99);
insert into abstractProduct(name, type_id, expired_date, price)
    values ('Томаты розовые фасованные, 500г', 3, '30.09.2023', 79.99);
insert into abstractProduct(name, type_id, expired_date, price)
    values ('Морковь мытая 1кг', 3, '31.07.2023', 69.99);
insert into abstractProduct(name, type_id, expired_date, price)
    values ('Бананы 1кг', 4, '30.09.2023', 109.99);
insert into abstractProduct(name, type_id, expired_date, price)
    values ('Дыня Торпеда 1кг', 4, '30.09.2023', 79.99);
insert into abstractProduct(name, type_id, expired_date, price)
    values ('Яблоки Гала Маркет 1кг', 4, '31.07.2023', 129.99);
insert into abstractProduct(name, type_id, expired_date, price)
    values ('Батон Коломенское Нарезной, 400г', 5, '30.09.2023', 51.99);
insert into abstractProduct(name, type_id, expired_date, price)
    values ('Хлеб Черемушки Бородинский половинка нарезка, 390г', 5, '30.09.2023', 46.99);
insert into abstractProduct(name, type_id, expired_date, price)
    values ('Хлеб Коломенское Даниловский зерновой, 300г', 5, '31.07.2023', 69.99);
insert into abstractProduct(name, type_id, expired_date, price)
    values ('Мороженое пломбир солёная карамель 15% Зелёная Линия, 75г', 6, '30.09.2023', 89.99);
insert into abstractProduct(name, type_id, expired_date, price)
    values ('Мороженое сливочное Sunreme малина-банан рожок в глазури 8%, 78г', 6, '30.09.2023', 99.99);
insert into abstractProduct(name, type_id, expired_date, price)
    values ('Эскимо Чистая Линия пломбир ванильное в апельсиновом соке в глазури 12%, 70г', 6, '31.07.2023', 79.99);

select p.name, t.name, p.price
from abstractProduct as p
join type as t
on p.type_id = t.id
where t.name = 'СЫР';

select p.name, p.price
from abstractProduct as p
where p.name like '%Морожен%' or p.name like '%Эскимо%';

select p.name, p.price, p.expired_date
from abstractProduct as p
where p.expired_date < now();

select p.name "Самая дорогая позиция", p.price
from abstractProduct as p
where p.price = (select max(price) from abstractProduct);

select t.name, count(*) "Количество продуктов в категории"
from abstractProduct as p
join type as t
on p.type_id = t.id
group by t.name;

select t.name, p.name, p.price
from abstractProduct as p
join type as t
on p.type_id = t.id
where t.name = 'СЫР' or t.name = 'МОЛОКО';

select t.name, count(*) "Количество продуктов в категории"
from abstractProduct as p
join type as t
on p.type_id = t.id
group by t.name
having count(*) < 10;

select p.name, t.name
from abstractProduct as p
join type as t
on p.type_id = t.id;
