create table roles (
    id serial primary key,
    title varchar(255),
    description text
);

create table rules (
    id serial primary key,
    title varchar(255),
    description text
);

create table roles_rules (
    id serial primary key,
    role_id integer references roles(id),
    rule_id integer references rules(id)
);

create table users (
    id serial primary key,
    name_of_user varchar(255),
    role_id integer references roles(id)
);

create table categories (
    id serial primary key,
    title varchar(255)
);

create table states (
    id serial primary key,
    title varchar(255)
);

create table items (
    id serial primary key,
    title varchar(255),
    description text,
    user_id integer references users(id),
    categorie_id integer references categories(id),
    state_id integer references states(id)
);

create table comments (
    id serial primary key,
    title varchar(255),
    description text,
    item_id integer references items(id)
);

create table attachs (
    id serial primary key,
    title varchar(255),
    path_to_file text,
    item_id integer references items(id)
);
