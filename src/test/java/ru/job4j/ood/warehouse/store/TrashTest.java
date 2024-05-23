package ru.job4j.ood.warehouse.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.warehouse.AbstractProduct;
import ru.job4j.ood.warehouse.AbstractStore;

import ru.job4j.ood.warehouse.product.Fruit;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 4.1. Реализовать Unit тесты под каждую реализацию хранилищ;
 */

class TrashTest {
    LocalDate currentDate;
    AbstractProduct fruit;

    {
        this.currentDate = LocalDate.of(2024, Month.MAY, 20);

        this.fruit =
                new Fruit("Яблоки Сезонные, весовой", currentDate.minusWeeks(2));
        this.fruit.setPrice(250);
    }

    @Test
    void whenPutFruitIntoTrash() {
        AbstractStore trash = new Trash(this.currentDate);

        double expPrice = fruit.getPrice();
        String expTitle = fruit.getTitle();
        trash.add(fruit);

        assertThat(expPrice).isEqualTo(
                trash.findAll().get(0).getPrice());
        assertThat(expTitle).isEqualTo(
                trash.findAll().get(0).getTitle());
    }

}