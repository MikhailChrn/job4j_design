package ru.job4j.ood.warehouse;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractStore implements Store<AbstractProduct> {
    private LocalDate currentDate;
    private final List<AbstractProduct> foodList;
    public static final int WAREHOUSE_BELOW = 100;
    public static final int SHOP_BELOW = 75;
    public static final int DISCOUNT_BELOW = 25;
    public static final int TRASH_BELOW = 0;

    public AbstractStore() {
        this.foodList = new ArrayList<>();
    }

    public int getPercentOfFreshness(AbstractProduct product) {
        double shelfLifeDuration
                = Period.between(product.getCreateDate(), product.getExpiryDate())
                .getDays();
        double currentLifeDuration
                = Period.between(product.getCreateDate(), this.getCurrentDate())
                .getDays();
        currentLifeDuration = currentLifeDuration < 0 ? 0 : currentLifeDuration;
        int percentOfFreshness = (int) (100 - (currentLifeDuration / shelfLifeDuration * 100));
        return percentOfFreshness < 0 ? 0 : percentOfFreshness;
    }

    public abstract boolean isValidToAdd(AbstractProduct product);

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public void add(AbstractProduct product) {
        if (!isValidToAdd(product)) {
            return;
        }
        this.foodList.add(product);
    }

    @Override
    public List<AbstractProduct> findAll() {
        return List.copyOf(foodList);
    }
}
