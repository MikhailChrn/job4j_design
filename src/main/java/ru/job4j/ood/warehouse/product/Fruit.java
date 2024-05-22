package ru.job4j.ood.warehouse.product;

import ru.job4j.ood.warehouse.AbstractProduct;

import java.time.LocalDate;

public class Fruit extends AbstractProduct {
    /**
     * Срок годности фруктов принимаем 2 недели
     */
    public Fruit(String title, LocalDate createDate) {
        super(title, createDate);
        this.setExpiryDate(createDate.plusWeeks(2));
    }
}
