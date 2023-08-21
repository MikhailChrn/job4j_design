create table users (
    id serial primary key,
    name_of_user varchar(255)
);

create table roles (
    id serial primary key,
    title varchar(255),
    description text
);

create table users_roles (
    id serial primary key,
    user_id integer references users(id),
    role_id integer references roles(id)
);

create table rules_of_role (
    id serial primary key,
    title varchar(255),
    description text
);

create table roles_rules (
    id serial primary key,
    roles_id integer references roles(id),
    rules_id integer references rules_of_role(id)
);

create table items (
    id serial primary key,
    title varchar(255),
    description varchar(255)
);

create table states (
    id serial primary key,
    title varchar(255)
);

create table items_state (
    id serial primary key,
    item_id integer references items(id) unique,
    state_id integer references states(id)
);

create table categories (
    id serial primary key,
    title varchar(255)
);

create table items_categories (
    id serial primary key,
    item_id integer references items(id) unique,
    categorie_id integer references categories(id)
);

create table comments (
    id serial primary key,
    title varchar(255),
    description text
);

create table items_comments (
    id serial primary key,
    item_id integer references items(id),
    comment_id integer references comments(id) unique
);

create table attachs (
    id serial primary key,
    title varchar(255),
    path_to_file text
);

create table items_attachs (
    id serial primary key,
    item_id integer references items(id),
    attach_id integer references attachs(id) unique
);