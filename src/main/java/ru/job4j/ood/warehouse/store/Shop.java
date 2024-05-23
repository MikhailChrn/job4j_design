package ru.job4j.ood.warehouse.store;

import ru.job4j.ood.warehouse.AbstractProduct;
import ru.job4j.ood.warehouse.AbstractStore;

import java.time.LocalDate;

/**
 * 3.2. Если срок годности израсходован от 25% до 75%, продукт должен оказаться в Shop;
 * 3.3. Если срок годности израсходован более, чем на 75%,
 * то продукт должен оказаться в Shop и его цена
 * должна быть снижена на размер скидки в 20 % от первоначальной цены;
 */

public class Shop extends AbstractStore {
    public Shop(LocalDate localDate) {
        super(TRASH_BELOW, SHOP_BELOW, localDate);
    }

    @Override
    public void add(AbstractProduct product) {
        if (!check(product)) {
            return;
        }
        if (super.productCalculator.calculate(product) <= DISCOUNT_BELOW) {
            product.setDiscount(0.2 * product.getPrice());
        }
        this.foodList.add(product);
    }
}

