package ru.job4j.solid.ocp;

/**
 * Здесь нарушен OCP принцип,
 * Если нам требуется дать некоей группе клиентов особую скидку,
 * придётся менять существующий код данного класса.
 */

public interface DiscountIncorrect {
    public default double getDiscount(Customer customer, double cost) {
        if (!customer.isVip()) {
            return cost * 0.1;
        } else if (customer.isVip()) {
            return cost * 0.25;
        }
        return cost;
    }
}
