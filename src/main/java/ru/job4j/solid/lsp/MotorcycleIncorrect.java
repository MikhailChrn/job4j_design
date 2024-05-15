package ru.job4j.solid.lsp;

/**
 * Здесь нарушен LSP принцип,
 * 1. Потомок MotorcycleIncorrect усиливает предусловия
 * (устанавливает ограничение на вес предка Vehicle);
 * 2. Потомок MotorcycleIncorrect генерирует исключение,не описанные предком.
 */

public interface MotorcycleIncorrect extends Vehicle {
    default void setWeight(int value) throws Exception {
        if (value > 200) {
            System.err.print(false);
            throw new Exception();
        } else {
            System.out.print(true);
        }
    }
}
