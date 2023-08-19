create table groups(
    id serial primary key,
    title varchar(255)
);

create table students(
    id serial primary key,
    name_of_student varchar(255),
    group_id integer references Groups(id)
);

insert into groups(title) values ('П-483э');
insert into students(name_of_student, group_id) values ('Mikhail', 1);

select * from students;

select * from groups
where id in
    (select group_id from Students);
