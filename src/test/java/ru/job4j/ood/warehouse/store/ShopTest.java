package ru.job4j.ood.warehouse.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.warehouse.AbstractProduct;
import ru.job4j.ood.warehouse.AbstractStore;
import ru.job4j.ood.warehouse.product.Vegetable;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 4.1. Реализовать Unit тесты под каждую реализацию хранилищ;
 */

class ShopTest {
    LocalDate currentDate;
    AbstractProduct vegetable0;
    AbstractProduct vegetable1;

    {
        this.currentDate = LocalDate.of(2024, Month.MAY, 20);

        this.vegetable0 =
                new Vegetable("Морковь Эконом, весовой", currentDate.minusWeeks(2));
        this.vegetable0.setPrice(70);

        this.vegetable1 =
                new Vegetable("Картофель красный, весовой", currentDate.minusWeeks(3));
        this.vegetable1.setPrice(40);
    }

    @Test
    void whenGetDiscountOnPotatoes() {
        AbstractStore shop = new Shop();
        shop.setCurrentDate(this.currentDate);
        double expectPrice0 = this.vegetable0.getPrice();
        double expectPrice1 = 0.8 * this.vegetable1.getPrice();
        List<AbstractProduct> products = Arrays.asList(vegetable0, vegetable1);
        products.forEach(shop::add);
        assertThat(expectPrice0).isEqualTo(
                shop.findAll().get(0).getPrice());
        assertThat(expectPrice1).isEqualTo(
                shop.findAll().get(1).getPrice());
    }
}