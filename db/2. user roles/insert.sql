insert into users(name_of_user) values ('Михаил');
insert into users(name_of_user) values ('Анастасия');
insert into users(name_of_user) values ('Елена');
insert into users(name_of_user) values ('Марина');

insert into roles (title, description)
    values ('Директор', 'Руководитель организации');
insert into roles (title, description)
    values ('Главный бухгалтер', 'Главный бухгалтер организации');
insert into roles (title, description)
    values ('Руководитель отдела кадров', 'Руководитель отдела кадров организации');
insert into roles (title, description)
    values ('Продавец-кассир', 'Продавец-кассир торгового зала');

insert into users_roles (user_id, role_id) values (1, 1);
insert into users_roles (user_id, role_id) values (2, 2);
insert into users_roles (user_id, role_id) values (3, 3);
insert into users_roles (user_id, role_id) values (4, 4);

insert into rules_of_role (title, description)
    values ('Администратор', 'Администратор ИС');
insert into rules_of_role (title, description)
    values ('Оператор', 'Оператор ИС');

insert into roles_rules (roles_id, rules_id) values (1, 1);
insert into roles_rules (roles_id, rules_id) values (1, 2);
insert into roles_rules (roles_id, rules_id) values (2, 1);
insert into roles_rules (roles_id, rules_id) values (2, 2);
insert into roles_rules (roles_id, rules_id) values (3, 2);
insert into roles_rules (roles_id, rules_id) values (4, 2);

insert into items (title, description)
    values ('HQ77012', 'Подшипник ступицы задний');
insert into items (title, description)
    values ('CF-940C', 'Фильтр салонный угольный');
insert into items (title, description)
    values ('105_107', 'Регулятор холостого хода ОРИГИНАЛ');

insert into states (title) values ('в работе');
insert into states (title) values ('выполнено');

insert into items_state (item_id, state_id) values (1, 1);
insert into items_state (item_id, state_id) values (2, 1);
insert into items_state (item_id, state_id) values (3, 1);

insert into categories (title) values ('услуги');
insert into categories (title) values ('товары');

insert into items_categories (item_id, categorie_id) values (1, 2);
insert into items_categories (item_id, categorie_id) values (2, 2);
insert into items_categories (item_id, categorie_id) values (3, 2);

insert into comments (title, description)
    values ('Передать ООО "Авангард"', 'передать до 31.08.2023');
insert into comments (title, description)
    values ('Для ООО "Вымпел"', 'представитель подъедет в офис 28.08.2023 до 12.00');
insert into comments (title, description)
    values ('Товар отсутсвует на складе', 'Поставщик ожидает оплаты');

insert into items_comments (item_id, comment_id) values (1, 1);
insert into items_comments (item_id, comment_id) values (2, 2);
insert into items_comments (item_id, comment_id) values (3, 3);

insert into attachs (title, path_to_file)
    values ('Счёт-фактура №47457 от 21.08.2023', '/data/invoices/47457.invc');
insert into attachs (title, path_to_file)
    values ('Счёт-фактура №8759 от 11.07.2023', '/data/invoices/8759.invc');
insert into attachs (title, path_to_file)
    values ('Счёт в оплату №54155 от 14.08.2023', '/data/payment/54155.pay');

insert into items_attachs (item_id, attach_id) values (1, 1);
insert into items_attachs (item_id, attach_id) values (2, 2);
insert into items_attachs (item_id, attach_id) values (3, 3);

