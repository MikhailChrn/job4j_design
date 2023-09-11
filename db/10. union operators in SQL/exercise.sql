create table movie (
    id serial primary key,
    name text,
    director text
);

create table book (
    id serial primary key,
    title text,
    author text
);

insert into movie (name, director)
values ('Марсианин', 'Ридли Скотт'),
       ('Матрица', 'Братья Вачовски'),
       ('Властелин колец', 'Питер Джексон'),
       ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
       ('Железный человек', 'Джон Фавро');

insert into book (title, author)
values ('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
       ('Властелин колец', 'Джон Толкин'),
       ('1984', 'Джордж Оруэлл'),
       ('Марсианин', 'Энди Уир'),
       ('Божественная комедия', 'Данте Алигьери');

select b.title as "фильмы, которые сняты по книге" from book b
intersect
select m.name  from movie m;

select b.title as "книги, у которых нет экранизации" from book b
except
select m.name  from movie m;

select b.title as "уникальные названия книг" from book b
except
select m.name  from movie m;

select m.name as "уникальные названия фильмов" from movie m
except
select b.title from book b;
