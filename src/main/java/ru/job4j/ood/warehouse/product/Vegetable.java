package ru.job4j.ood.warehouse.product;

import ru.job4j.ood.warehouse.AbstractProduct;

import java.time.LocalDate;

public class Vegetable extends AbstractProduct {
    /**
     * Срок годности овощей принимаем 4 недели
     */
    public Vegetable(String name, LocalDate createDate) {
        super(name, createDate);
        this.setExpiryDate(createDate.plusWeeks(4));
    }
}
