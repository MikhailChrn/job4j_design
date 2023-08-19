create table students(
    id serial primary key,
    name_of_student varchar(255)
);

create table courses(
    id serial primary key,
    title varchar(255),
    duration interval
);

create table students_courses(
    id serial primary key,
    student_id integer references students(id),
    course_id integer references courses(id)
);

insert into students(name_of_student) values ('Михаил');
insert into students(name_of_student) values ('Анастасия');
insert into students(name_of_student) values ('Елена');

insert into courses(title, duration) values ('Информационные технологии', '14mons');
insert into courses(title, duration) values ('Бухучёт', '9mons');
insert into courses(title, duration) values ('Подбор персонала', '8mons');
insert into courses(title, duration) values ('Математический анализ', '24mons');
insert into courses(title, duration) values ('Русский язык', '24mons');

insert into students_courses(student_id, course_id) values (1, 1);
insert into students_courses(student_id, course_id) values (1, 4);
insert into students_courses(student_id, course_id) values (1, 5);
insert into students_courses(student_id, course_id) values (2, 2);
insert into students_courses(student_id, course_id) values (2, 5);
insert into students_courses(student_id, course_id) values (3, 3);
insert into students_courses(student_id, course_id) values (3, 5);

select * from students
    where id in (select student_id from students_courses
        where course_id = 5);
