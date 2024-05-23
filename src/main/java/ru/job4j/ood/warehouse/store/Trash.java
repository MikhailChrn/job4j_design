package ru.job4j.ood.warehouse.store;

import ru.job4j.ood.warehouse.AbstractStore;

import java.time.LocalDate;

/**
 * 3.4. Если срок годности вышел (израсходован полностью),
 * продукт должен оказаться в Trash.
 */

public class Trash extends AbstractStore {
    public Trash(LocalDate localDate) {
        super(-1, TRASH_BELOW, localDate);
    }
}
