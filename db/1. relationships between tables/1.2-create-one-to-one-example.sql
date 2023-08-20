create table students(
    id serial primary key,
    name_of_student varchar(255)
);

create table contracts(
    id serial primary key,
    title varchar(255),
    date_of_contract date
);

create table students_contracts(
    id serial primary key,
    student_id integer references Students(id) unique ,
    contract_id integer references Contracts(id) unique
);

insert into students(name_of_student) values ('Михаил');
insert into students(name_of_student) values ('Анастасия');
insert into students(name_of_student) values ('Елена');

insert into contracts(title, date_of_contract) values ('К-98765', '01.08.2023');
insert into contracts(title, date_of_contract) values ('К-31252', '02.08.2023');
insert into contracts(title, date_of_contract) values ('К-56753', '03.08.2023');

insert into students_contracts(student_id, contract_id) values (1, 1);
insert into students_contracts(student_id, contract_id) values (2, 2);
insert into students_contracts(student_id, contract_id) values (3, 3);

select * from students_contracts;
