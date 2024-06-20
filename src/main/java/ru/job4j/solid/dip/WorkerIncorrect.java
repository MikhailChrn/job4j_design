package ru.job4j.solid.dip;

import ru.job4j.solid.lsp.Order;

/**
 * Здесь нарушен DIP принцип,
 * Интерфейс WorkerIncorrect зависит от конкретных
 * реализаций FileWriter и DataProcessor.
 * С точки зрения принципа DIP было бы правильнее для начала создать некоторые абстракции,
 * например, два интерфейса IoInterface и IProcess.
 */

public interface WorkerIncorrect {

    FileWriter FILE_WRITER = new FileWriter();

    DataProcessor DATA_PROCESSOR = new DataProcessor();

    default void work(Order order) {
        System.out.println(FILE_WRITER);
        System.out.println(DATA_PROCESSOR);
    }
}
