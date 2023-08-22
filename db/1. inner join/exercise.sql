create table student_groups(
    id serial primary key,
    title varchar(255)
);

create table students(
    id serial primary key,
    name varchar(255),
    sex varchar(1),
    group_id int references student_groups(id)
);

insert into student_groups(id, title) values (1, 'Б1');
insert into student_groups(id, title) values (2, 'Б2');
insert into student_groups(id, title) values (3, 'Б3');

insert into students(name, sex, group_id) values('Баранов Марк Вячеславович', 'м', 1);
insert into students(name, sex, group_id) values('Козлов Владимир Владимирович', 'м', 2);
insert into students(name, sex, group_id) values('Соболев Даниил Тимофеевич', 'м', 3);
insert into students(name, sex, group_id) values ('Яковлев Роман Данилович', 'м', 1);
insert into students(name, sex, group_id) values ('Ильина Анастасия Марковна', 'ж', 2);
insert into students(name, sex, group_id) values ('Зайцев Михаил Григорьевич', 'м', 3);
insert into students(name, sex, group_id) values ('Беляев Дамир Павлович', 'м', 1);
insert into students(name, sex, group_id) values ('Степанова Кира Степановна', 'ж', 2);
insert into students(name, sex, group_id) values ('Федорова Ульяна Артёмовна', 'ж', 3);
insert into students(name, sex, group_id) values ('Кузьмин Тимур Максимович', 'м', 1);
insert into students(name, sex, group_id) values ('Тарасов Илья Иванович', 'м', 2);
insert into students(name, sex, group_id) values ('Давыдова Марьям Максимовна', 'ж', 3);
insert into students(name, sex, group_id) values ('Соколова София Михайловна', 'ж', 1);
insert into students(name, sex, group_id) values ('Агафонова Софья Фёдоровна', 'ж', 2);
insert into students(name, sex, group_id) values ('Федотов Фёдор Демидович', 'м', 3);
insert into students(name, sex, group_id) values ('Николаева Анна Ильинична', 'ж', 1);
insert into students(name, sex, group_id) values ('Покровский Михаил Артёмович', 'м', 2);
insert into students(name, sex, group_id) values ('Сергеев Владислав Александрович', 'м', 3);
insert into students(name, sex, group_id) values ('Захарова Вера Сергеевна', 'ж', 1);
insert into students(name, sex, group_id) values ('Кузнецов Денис Миронович', 'м', 2);
insert into students(name, sex, group_id) values ('Плотникова Ксения Никитична', 'ж', 3);

select st.name as "Студент(ка)", st.sex "пол", gr.title "Номер группы"
from students st join student_groups gr on st.group_id = gr.id;

select st.name as "Студент(ка)", st.sex "пол", gr.title "Номер группы"
from students st join student_groups gr on st.group_id = gr.id
where st.sex = 'ж';

select st.name as "Студент(ка)", st.sex "пол", gr.title "Номер группы"
from students st join student_groups gr on st.group_id = gr.id
where gr.title = 'Б2';