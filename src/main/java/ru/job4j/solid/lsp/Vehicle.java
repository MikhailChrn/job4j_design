package ru.job4j.solid.lsp;

/**
 * Предок
 */

public interface Vehicle {
    default void setWeight(int value) throws Exception {
        System.out.print(true);
    }
}
