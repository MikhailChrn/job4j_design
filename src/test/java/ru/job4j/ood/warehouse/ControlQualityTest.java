package ru.job4j.ood.warehouse;

import org.junit.jupiter.api.Test;

import ru.job4j.ood.warehouse.product.Bread;
import ru.job4j.ood.warehouse.product.Fruit;
import ru.job4j.ood.warehouse.product.Vegetable;
import ru.job4j.ood.warehouse.store.Shop;
import ru.job4j.ood.warehouse.store.Trash;
import ru.job4j.ood.warehouse.store.Warehouse;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 4.2. Реализовать Unit тест под ControlQuality.
 */

class ControlQualityTest {
    LocalDate currentDate;
    AbstractProduct bread;
    AbstractProduct vegetable1;
    AbstractProduct vegetable2;
    AbstractProduct fruit;

    {
        this.currentDate = LocalDate.of(2024, Month.MAY, 20);

        this.bread =
                new Bread("Хлеб ржано-пшеничный Бородинский, 300 гр", currentDate);
        this.bread.setPrice(60);
        this.vegetable1 =
                new Vegetable("Морковь Эконом, весовой", currentDate.minusWeeks(2));
        this.vegetable1.setPrice(70);
        this.vegetable2 =
                new Vegetable("Картофель красный, весовой", currentDate.minusWeeks(3));
        this.vegetable2.setPrice(40);
        this.fruit =
                new Fruit("Яблоки Сезонные, весовой", currentDate.minusWeeks(2));
        this.fruit.setPrice(250);
    }

    @Test
    void whenAddProductsWithDifferentFreshnessIntoControlQuality() {
        ControlQuality controlQuality = new ControlQuality(this.currentDate);

        controlQuality.addStore(new Trash());
        controlQuality.addStore(new Shop());
        controlQuality.addStore(new Warehouse());

        List<AbstractProduct> products = Arrays.asList(
                bread, vegetable1, vegetable2, fruit);
        products.forEach(controlQuality::addFood);

        assertThat(fruit).isEqualTo(
                controlQuality.getStoreList()
                        .get(0).findAll().get(0));
        assertThat(vegetable1).isEqualTo(
                controlQuality.getStoreList()
                        .get(1).findAll().get(0));
        assertThat(bread).isEqualTo(
                controlQuality.getStoreList()
                        .get(2).findAll().get(0));
    }
}