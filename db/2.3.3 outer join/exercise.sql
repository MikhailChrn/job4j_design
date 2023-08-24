create table departments(
    id serial primary key,
    title varchar(255)
);

insert into departments(id, title) values (1, 'IT');
insert into departments(id, title) values (2, 'HR');
insert into departments(id, title) values (3, 'Accounting');
insert into departments(id, title) values (4, 'Cleaning');

create table employees(
    id serial primary key,
    name varchar(255),
    department_id integer references departments (id)
);

insert into employees(id, name, department_id) values (1, 'Марьям', 1);
insert into employees(id, name, department_id) values (2, 'Анна', 1);
insert into employees(id, name, department_id) values (3, 'Владислав', 1);
insert into employees(id, name, department_id) values (4, 'Евгения', 2);
insert into employees(id, name, department_id) values (5, 'Варвара', 2);
insert into employees(id, name, department_id) values (6, 'Никита', 2);
insert into employees(id, name, department_id) values (7, 'Алексей', 3);
insert into employees(id, name, department_id) values (8, 'Алиса', 3);
insert into employees(id, name, department_id) values (9, 'Екатерина', 3);
insert into employees(id, name, department_id) values (10, 'Денис', null);
insert into employees(id, name, department_id) values (11, 'Владислав', null);

select *
from employees emp
left join departments dep
on dep.id = emp.department_id;

select *
from employees emp
right join departments dep
on dep.id = emp.department_id;

select *
from employees emp
full join departments dep
on dep.id = emp.department_id;

select *
from employees emp
cross join departments dep;

select emp.department_id, dep.title, emp.name
from departments dep
left join employees emp
on dep.id = emp.department_id
where emp.department_id is null;

select emp.department_id, dep.title, emp.name
from employees emp
right join departments dep
on emp.department_id = dep.id
where emp.department_id is null;

create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(1)
);

insert into teens(id, name, gender) values (1, 'Марьям', 'ж');
insert into teens(id, name, gender) values (2, 'Анна', 'ж');
insert into teens(id, name, gender) values (3, 'Владислав', 'м');
insert into teens(id, name, gender) values (4, 'Евгения', 'ж');
insert into teens(id, name, gender) values (5, 'Варвара', 'ж');
insert into teens(id, name, gender) values (6, 'Никита', 'м');
insert into teens(id, name, gender) values (7, 'Алексей', 'м');
insert into teens(id, name, gender) values (8, 'Алиса', 'ж');
insert into teens(id, name, gender) values (9, 'Екатерина', 'ж');
insert into teens(id, name, gender) values (10, 'Денис', 'м');
insert into teens(id, name, gender) values (11, 'Владислав', 'м');

select m.name, f.name
from teens m
cross join teens f
where m.gender = 'м' and f.gender = 'ж';


