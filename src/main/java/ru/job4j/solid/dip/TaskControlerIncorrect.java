package ru.job4j.solid.dip;

import ru.job4j.solid.lsp.Order;

/**
 * Здесь нарушен DIP принцип,
 * Интерфейс TaskControlerIncorrect зависит от конкретной
 * реализации TaskService.
 * С точки зрения принципа DIP было бы правильнее для начала создать некоторые абстракции,
 * например, интерфейс TaskService.
 */

public interface TaskControlerIncorrect {

    TaskService TASK_SERVICE = new TaskService();

    default void beginInit(Order order) {
        System.out.println(TASK_SERVICE);
    }
}
