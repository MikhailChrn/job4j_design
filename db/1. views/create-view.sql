create table car_bodies(
    id serial primary key,
    title varchar(255)
);

insert into car_bodies(id, title) values (1, 'body-1');
insert into car_bodies(id, title) values (2, 'body-2');
insert into car_bodies(id, title) values (3, 'body-3');
insert into car_bodies(id, title) values (4, 'body-4');
insert into car_bodies(id, title) values (5, 'body-5');

create table car_engines(
    id serial primary key,
    title varchar(255)
);

insert into car_engines(id, title) values (1, 'engine-1');
insert into car_engines(id, title) values (2, 'engine-2');
insert into car_engines(id, title) values (3, 'engine-3');
insert into car_engines(id, title) values (4, 'engine-4');
insert into car_engines(id, title) values (5, 'engine-5');

create table car_transmissions(
    id serial primary key,
    title varchar(255)
);

insert into car_transmissions(id, title) values (1, 'transmission-1');
insert into car_transmissions(id, title) values (2, 'transmission-2');
insert into car_transmissions(id, title) values (3, 'transmission-3');
insert into car_transmissions(id, title) values (4, 'transmission-4');
insert into car_transmissions(id, title) values (5, 'transmission-5');

create table cars(
    id serial primary key,
    title varchar(255),
    body_id integer references car_bodies(id),
    engine_id integer references car_engines(id),
    transmission_id integer references car_transmissions(id),
    price integer
);

insert into cars(id, title, engine_id, transmission_id, price)
values (1, 'car-1', 1, 1, 500000);
insert into cars(id, title, body_id, transmission_id, price)
values (2, 'car-2', 2, 2, 600000);
insert into cars(id, title, body_id, engine_id, transmission_id, price)
values (3, 'car-3', 3, 3, 3, 700000);
insert into cars(id, title, body_id, engine_id, price)
values (4, 'car-4', 4, 4, 800000);
insert into cars(id, title, body_id, engine_id, transmission_id, price)
values (5, 'car-5', 5, 5, 5, 900000);

create view cars_without_transmission_price_more_700K as
select c.id "id", c.title "car_name",
    b.title "body_name", e.title "engine_name",
    t.title "transmission_name",
    c.price "price [RUR]"
from cars c
left join car_bodies b
on c.body_id = b.id
left join car_engines e
on c.engine_id = e.id
left join car_transmissions t
on c.transmission_id = t.id
where c.transmission_id is null
    and c.price >= 700000;

select * from cars_without_transmission_price_more_700K;
