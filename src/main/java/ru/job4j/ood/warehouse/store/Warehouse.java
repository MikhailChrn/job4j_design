package ru.job4j.ood.warehouse.store;

import ru.job4j.ood.warehouse.AbstractProduct;
import ru.job4j.ood.warehouse.AbstractStore;

import java.time.LocalDate;

/**
 * 3.1. Если срок годности израсходован меньше чем на 25%,
 * продукт должен оказаться в Warehouse;
 */

public class Warehouse extends AbstractStore {
    public Warehouse(LocalDate localDate) {
        super(SHOP_BELOW, WAREHOUSE_BELOW, localDate);
    }
}
