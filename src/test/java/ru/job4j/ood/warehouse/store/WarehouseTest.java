package ru.job4j.ood.warehouse.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.warehouse.AbstractProduct;
import ru.job4j.ood.warehouse.AbstractStore;
import ru.job4j.ood.warehouse.product.Bread;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 4.1. Реализовать Unit тесты под каждую реализацию хранилищ;
 */

class WarehouseTest {
    LocalDate currentDate;
    AbstractProduct bread;

    {
        this.currentDate = LocalDate.of(2024, Month.MAY, 20);

        this.bread =
                new Bread("Хлеб ржано-пшеничный Бородинский, 300 гр", currentDate);
        this.bread.setPrice(60);
    }

    @Test
    void whenPutBreadIntoWareHouse() {
        AbstractStore warehouse = new Warehouse();
        warehouse.setCurrentDate(this.currentDate);
        double expPrice = bread.getPrice();
        String expTitle = bread.getTitle();
        warehouse.add(bread);

        assertThat(expPrice).isEqualTo(
                warehouse.findAll().get(0).getPrice());
        assertThat(expTitle).isEqualTo(
                warehouse.findAll().get(0).getTitle());
    }
}
