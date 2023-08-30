/*
Добавьте процедуру и функцию, которая будет удалять записи.
Условия выбирайте сами – удаление по id,
 удалить если количество товара равно 0 и т.п.
*/

create or replace procedure delete_data(d_name varchar)
language 'plpgsql'
as $$
    BEGIN
    delete from products pr
    where pr.name = d_name;
    END
$$;

call delete_data('product_3');

create or replace function f_delete_data(d_name varchar)
returns void
language 'plpgsql'
as
$$
    BEGIN
        delete from products pr
        where pr.name = d_name;
    END;
$$;

select f_delete_data('product_3');

