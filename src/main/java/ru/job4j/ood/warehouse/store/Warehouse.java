package ru.job4j.ood.warehouse.store;

import ru.job4j.ood.warehouse.AbstractProduct;
import ru.job4j.ood.warehouse.AbstractStore;

/**
 * 3.1. Если срок годности израсходован меньше чем на 25%,
 * продукт должен оказаться в Warehouse;
 */

public class Warehouse extends AbstractStore {
    @Override
    public boolean isValidToAdd(AbstractProduct product) {
        return super.getPercentOfFreshness(product) <= WAREHOUSE_BELOW
                && super.getPercentOfFreshness(product) > SHOP_BELOW;
    }
}
