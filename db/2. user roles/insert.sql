insert into users(id, name_of_user) values (1, 'Михаил');
insert into users(id, name_of_user) values (2, 'Анастасия');
insert into users(id, name_of_user) values (3, 'Елена');
insert into users(id, name_of_user) values (4, 'Марина');

insert into roles (title, description, user_id)
    values ('Инженер-прогаммист', 'Адиминистратор ИС', 1);
insert into roles (title, description, user_id)
    values ('Бухгалтер', 'Главный бухгалтер организации', 2);
insert into roles (title, description, user_id)
    values ('Руководитель отдела продаж', 'Руководитель отдела продаж организации', 3);
insert into roles (title, description, user_id)
    values ('Продавец-кассир', 'Продавец-кассир торгового зала', 4);

insert into rules (id, title, description)
    values (1, 'Администратор', 'Администратор ИС');
insert into rules (id, title, description)
    values (2, 'Оператор', 'Оператор ИС');

insert into roles_rules (role_id, rule_id) values (1, 1);
insert into roles_rules (role_id, rule_id) values (1, 2);
insert into roles_rules (role_id, rule_id) values (2, 2);
insert into roles_rules (role_id, rule_id) values (3, 2);
insert into roles_rules (role_id, rule_id) values (4, 2);

insert into categories (id, title) values (1, 'Услуга');
insert into categories (id, title) values (2, 'Товар');

insert into items (id, title, description, user_id, categorie_id)
    values (1, 'HQ77012', 'Подшипник ступицы задний', 4, 2);
insert into items (id, title, description, user_id, categorie_id)
    values (2, 'CF-940C', 'Фильтр салонный угольный', 4, 2);
insert into items (id, title, description, user_id, categorie_id)
    values (3, '105_107', 'Регулятор холостого хода ОРИГИНАЛ', 3, 2);

insert into states (id, title, item_id)
    values (1, 'Заказ №1 - в работе', 1);
insert into states (id, title, item_id)
    values (2, 'Заказ №1 - выполнен', 1);
insert into states (id, title, item_id)
    values (3, 'Заказ №2 - в работе', 2);
insert into states (id, title, item_id)
    values (4, 'Заказ №2 - выполнен', 2);
insert into states (id, title, item_id)
    values (5, 'Заказ №3 - поставщик ожидает оплату', 3);

insert into comments (title, description, item_id)
    values ('Передать ООО "Авангард"', 'передать до 31.08.2023', 1);
insert into comments (title, description, item_id)
    values ('Для ООО "Вымпел"', 'представитель подъедет в офис 28.08.2023 до 12.00', 2);
insert into comments (title, description, item_id)
    values ('Товар отсутсвует на складе', 'Поставщик ожидает оплаты', 3);

insert into attachs (title, path_to_file, item_id)
    values ('Счёт-фактура №47457 от 21.08.2023', '/data/invoices/47457.invc', 1);
insert into attachs (title, path_to_file, item_id)
    values ('Счёт-фактура №8759 от 11.07.2023', '/data/invoices/8759.invc', 2);
insert into attachs (title, path_to_file, item_id)
    values ('Счёт в оплату №54155 от 14.08.2023', '/data/payment/54155.pay', 3);
