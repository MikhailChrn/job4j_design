create or replace function discount()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where count <= 5 AND id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger discount_trigger
    after insert
    on products
    for each row
    execute procedure discount();

alter table products disable trigger discount_trigger;

drop trigger discount_trigger on products;
