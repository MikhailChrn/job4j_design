package ru.job4j.ood.warehouse.store;

import ru.job4j.ood.warehouse.AbstractProduct;
import ru.job4j.ood.warehouse.AbstractStore;

import java.time.Period;

/**
 * 3.4. Если срок годности вышел (израсходован полностью),
 * продукт должен оказаться в Trash.
 */

public class Trash extends AbstractStore {
    @Override
    public boolean isValidToAdd(AbstractProduct product) {
        return super.getPercentOfFreshness(product) <= TRASH_BELOW;
    }
}
