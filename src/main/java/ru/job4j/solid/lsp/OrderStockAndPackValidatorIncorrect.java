package ru.job4j.solid.lsp;

/**
 * Здесь нарушен LSP принцип,
 * 1. Потомок OrderStockAndPackValidatorIncorrect усиливает предусловия.
 * Добавляет дополнительную проверку isPack (т.е требуeт больше от своих клиентов);
 */

public interface OrderStockAndPackValidatorIncorrect extends OrderStockValidator {
    default boolean isValid(Order order) {
        return order.isInStock() && order.isPack();
    }
}
