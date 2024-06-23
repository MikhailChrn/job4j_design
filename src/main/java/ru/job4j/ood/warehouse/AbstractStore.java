package ru.job4j.ood.warehouse;

import ru.job4j.ood.warehouse.calculator.FreshnessCalculator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store<AbstractProduct> {
    public static final int WAREHOUSE_BELOW = 100;
    public static final int SHOP_BELOW = 75;
    public static final int DISCOUNT_BELOW = 25;
    public static final int TRASH_BELOW = 0;

    protected LocalDate currentDate;
    protected final List<AbstractProduct> foodList;
    protected Calculator<AbstractProduct> productCalculator;
    protected int minPerc;
    protected int maxPerc;

    public AbstractStore(int minPerc, int maxPerc, LocalDate currentDate) {
        this.minPerc = minPerc;
        this.maxPerc = maxPerc;
        this.currentDate = currentDate;
        this.productCalculator = new FreshnessCalculator(currentDate);
        this.foodList = new ArrayList<>();
    }

    public void setCurrentDate(LocalDate date) {
        this.currentDate = date;
        this.productCalculator.setCurrentDate(this.currentDate);
    }

    @Override
    public void add(AbstractProduct product) {
        if (!check(product)) {
            return;
        }
        this.foodList.add(product);
    }

    @Override
    public List<AbstractProduct> findAll() {
        return List.copyOf(foodList);
    }

    @Override
    public List<AbstractProduct> clear() {
        List<AbstractProduct> list = findAll();
        foodList.clear();
        return list;
    }

    public void removeProduct(AbstractProduct product) {
        foodList.remove(product);
    }

    protected boolean check(AbstractProduct product) {
        int result = productCalculator.calculate(product);
        return result <= this.maxPerc && result > this.minPerc;
    }
}
