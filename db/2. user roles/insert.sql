insert into roles (id, title, description)
    values (1, 'Инженер-прогаммист', 'Адиминистратор ИС');
insert into roles (id, title, description)
    values (2, 'Бухгалтер', 'Главный бухгалтер организации');
insert into roles (id, title, description)
    values (3, 'Руководитель отдела продаж', 'Руководитель отдела продаж организации');
insert into roles (id, title, description)
    values (4, 'Продавец-кассир', 'Продавец-кассир торгового зала');

insert into rules (id, title, description)
    values (1, 'Администратор', 'Администратор ИС');
insert into rules (id, title, description)
    values (2, 'Оператор', 'Оператор ИС');

insert into roles_rules (role_id, rule_id) values (1, 1);
insert into roles_rules (role_id, rule_id) values (1, 2);
insert into roles_rules (role_id, rule_id) values (2, 2);
insert into roles_rules (role_id, rule_id) values (3, 2);
insert into roles_rules (role_id, rule_id) values (4, 2);

insert into users (id, name_of_user, role_id) values (1, 'Михаил', 1);
insert into users (id, name_of_user, role_id) values (2, 'Анастасия', 2);
insert into users (id, name_of_user, role_id) values (3, 'Елена', 3);
insert into users (id, name_of_user, role_id) values (4, 'Марина', 4);

insert into categories (id, title) values (1, 'Услуга');
insert into categories (id, title) values (2, 'Товар');

insert into states (id, title)
    values (1, 'Черновик');
insert into states (id, title)
    values (2, 'В работе');
insert into states (id, title)
    values (3, 'Готов');

insert into items (id, title, description, user_id, categorie_id, state_id)
    values (1, 'HQ77012', 'Подшипник ступицы задний', 4, 2, 2);
insert into items (id, title, description, user_id, categorie_id, state_id)
    values (2, 'CF-940C', 'Фильтр салонный угольный', 4, 2, 2);
insert into items (id, title, description, user_id, categorie_id, state_id)
    values (3, '105_107', 'Регулятор холостого хода ОРИГИНАЛ', 3, 2, 1);


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
