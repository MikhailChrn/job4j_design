package ru.job4j.solid.lsp;

/**
 * Предок
 */

public interface OrderStockValidator {
    default boolean isValid(Order order) {
        return order.isInStock();
    }
}
