package ru.job4j.ood.warehouse.calculator;

import ru.job4j.ood.warehouse.AbstractProduct;
import ru.job4j.ood.warehouse.Calculator;

import java.time.LocalDate;
import java.time.Period;

public class FreshnessCalculator implements Calculator<AbstractProduct> {
    private LocalDate currentDate;

    public FreshnessCalculator(LocalDate date) {
        this.currentDate = date;
    }

    @Override
    public int calculate(AbstractProduct product) {
        double shelfLifeDuration
                = Period.between(product.getCreateDate(), product.getExpiryDate())
                .getDays();
        double currentLifeDuration
                = Period.between(product.getCreateDate(), this.currentDate)
                .getDays();
        currentLifeDuration = currentLifeDuration < 0 ? 0 : currentLifeDuration;
        return Math.max((int) (100 - (currentLifeDuration / shelfLifeDuration * 100)), 0);
    }

    @Override
    public void setCurrentDate(LocalDate date) {
        this.currentDate = date;
    }
}
