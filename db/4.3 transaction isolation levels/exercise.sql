create table simple_tab (
    class integer,
    value integer
);

insert into simple_tab(class, value) values (1, 10);
insert into simple_tab(class, value) values (1, 10);
insert into simple_tab(class, value) values (2, 30);
insert into simple_tab(class, value) values (2, 30);
insert into simple_tab(class, value) values (3, 50);
insert into simple_tab(class, value) values (4, 50);

begin transaction isolation level serializable;

select sum(value)
from simple_tab
where class = 1;

update simple_tab set value = 20 where class = 2;

commit;


