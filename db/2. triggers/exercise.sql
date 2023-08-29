/*
2. За основу возьмите таблицу, с которой мы работали в описании.
Добавьте 3 триггера:

1)  Триггер должен срабатывать после вставки данных,
    для любого товара и просто насчитывать налог на товар
    (нужно прибавить налог к цене товара).
    Действовать он должен не на каждый ряд, а на запрос (statement уровень)
2) Триггер должен срабатывать до вставки данных
    и насчитывать налог на товар (нужно прибавить налог к цене товара).
    Здесь используем row уровень.
3) Нужно написать триггер на row уровне,
    который сразу после вставки продукта в таблицу products,
    будет заносить имя, цену и текущую дату в таблицу history_of_price.
*/

create or replace function adding_tax_to_cost()
    returns trigger as
$$
    BEGIN
        update products
            set price = price + price * 0.2
            where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger exercise1_trigger
    after insert on products referencing new table as inserted
        for each statement
    execute procedure adding_tax_to_cost();

drop trigger exercise1_trigger on products;

create or replace function exclusion_tax_from_cost()
    returns trigger as
$$
    BEGIN
        NEW.price = NEW.price - (NEW.price * 0.5);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger exercise2_trigger
    before insert on products for each row
    execute procedure exclusion_tax_from_cost();

drop trigger exercise2_trigger on products;

create or replace function storaging_the_product_price()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price, date)
            values(NEW.name, NEW.price, now());
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';


create trigger exercise3_trigger
    after insert on products for each row
    execute procedure storaging_the_product_price();

drop trigger exercise3_trigger on products;


