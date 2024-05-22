package ru.job4j.ood.warehouse.product;

import ru.job4j.ood.warehouse.AbstractProduct;

import java.time.LocalDate;

public class Bread extends AbstractProduct {
    /**
     * Срок годности хлебов принимаем 1 неделю
     */
    public Bread(String name, LocalDate createDate) {
        super(name, createDate);
        this.setExpiryDate(createDate.plusWeeks(1));
    }
}
