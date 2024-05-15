package ru.job4j.solid.lsp;

/**
 * Пример
 */

public interface Order {
    default boolean isInStock() {
        return false;
    }

    default boolean isPack() {
        return false;
    }
}
