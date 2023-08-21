create table fauna(
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date) values
	('Red fox', 15, '1775.01.01'),
	('Silver wolf', 25, '1822.01.01'),
	('Nephritian deer', 35, '1758.01.01'),
	('Emerald tiger', 45, '1882.01.01'),
	('Diamand bear', 75, '1778.01.01'),
	('Gold fish', 999, '1909.01.01');

select * from fauna where name like '%fish%';

select * from fauna where avg_age > 30 and avg_age < 50;

select * from fauna where discovery_date is null;

select * from fauna where EXTRACT(YEAR FROM discovery_date) < 1950;