create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(id, name, price)
    values (1, 'Телефон iPhone 14 Pro Max 256gb', 104999.0);
insert into devices(id, name, price)
    values (2, 'Студийные наушники Behringer BH 470', 2290.0);
insert into devices(id, name, price)
    values (3, 'Игровая приставка 2.4G Wireless 128gb', 1500.0);
insert into devices(id, name, price)
    values (4, 'Телевизор samsung smart tv 40 ', 2999.0);
insert into devices(id, name, price)
    values (5, 'Роутер Tp-link Archer C20 AC750 Wireles', 1500.0);
insert into devices(id, name, price)
    values (6, 'Напольная акустика System Audio SA Saxo 60 Satin W', 102890.0);
insert into devices(id, name, price)
    values (7, 'Видеокарта KFA2 RTX 2060 12 GB', 23000.0);
insert into devices(id, name, price)
    values (8, 'Напольная акустика Scansonic HD M40 White', 206990.0);
insert into devices(id, name, price)
    values (9, 'ThinkPad T470 14" i5 8GB DDR4 256SSD nvme Type-C', 20990.0);
insert into devices(id, name, price)
    values (10, 'Redmi Note 12 6/128 NFC', 15984.0);
insert into devices(id, name, price)
    values (11, 'Внешний жесткий диск Toshiba Canvio Basics 1 тб ', 1500.0);
insert into devices(id, name, price)
    values (12, 'Системный блок игровой Ryzen 5 5600X + RTX3060 12GB', 75800.0);
insert into devices(id, name, price)
    values (13, 'Телевизор Hi 43/109 см smart Led Tv', 12000.0);
insert into devices(id, name, price)
    values (14, 'Студийные наушники AKG K361BT', 15590.0);
insert into devices(id, name, price)
    values (15, 'Геймпад беспроводной Хbox One', 4000.0);
insert into devices(id, name, price)
    values (16, 'Геймпад PS4/PC Pro Controller', 2990.0);
insert into devices(id, name, price)
    values (17, 'Блок питания 12v 1-2A', 350.0);
insert into devices(id, name, price)
    values (18, 'Камера Sony PlayStation Camera (CUH-ZEY2) V2', 2490.0);
insert into devices(id, name, price)
    values (19, 'Игровая приставка PS Vita Black 1008 + 32G', 12500.0);
insert into devices(id, name, price)
    values (20, 'Игровой монитор asus VP249QGR 23.8" 144 Гц', 11000.0);

insert into people(id, name) values (1, 'Марьям');
insert into people(id, name) values (2, 'Анна');
insert into people(id, name) values (3, 'Владислав');
insert into people(id, name) values (4, 'Евгения');
insert into people(id, name) values (5, 'Варвара');
insert into people(id, name) values (6, 'Никита');
insert into people(id, name) values (7, 'Алексей');
insert into people(id, name) values (8, 'Алиса');
insert into people(id, name) values (9, 'Екатерина');
insert into people(id, name) values (10, 'Денис');

insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (2, 1);
insert into devices_people(device_id, people_id) values (3, 2);
insert into devices_people(device_id, people_id) values (4, 2);
insert into devices_people(device_id, people_id) values (5, 3);
insert into devices_people(device_id, people_id) values (6, 3);
insert into devices_people(device_id, people_id) values (7, 4);
insert into devices_people(device_id, people_id) values (8, 4);
insert into devices_people(device_id, people_id) values (9, 5);
insert into devices_people(device_id, people_id) values (10, 5);
insert into devices_people(device_id, people_id) values (11, 6);
insert into devices_people(device_id, people_id) values (12, 6);
insert into devices_people(device_id, people_id) values (13, 7);
insert into devices_people(device_id, people_id) values (14, 7);
insert into devices_people(device_id, people_id) values (15, 8);
insert into devices_people(device_id, people_id) values (16, 8);
insert into devices_people(device_id, people_id) values (17, 9);
insert into devices_people(device_id, people_id) values (18, 9);
insert into devices_people(device_id, people_id) values (19, 10);
insert into devices_people(device_id, people_id) values (20, 10);

select avg(dev.price) "Средняя цена устройства"
from devices as dev;

select p.name, avg(dev.price)
from devices_people as dp
join devices as dev on dp.device_id = dev.id
join people as p on dp.people_id = p.id
group by p.name
having avg(dev.price) > 5000;
