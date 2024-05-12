package ru.job4j.solid.ocp;

/**
 * Здесь нарушен OCP принцип,
 * т.к. при изменении алгоритма выполнения процесса
 * придётся менять существующий код данного класса.
 */

public interface OrderProcessorIncorrect {

    public default void process(Order order) {
        beforeProcessing();
        order.makeSomething();
        afterProcessing();
    }

    private void beforeProcessing() {

    }

    private void afterProcessing() {

    }
}
