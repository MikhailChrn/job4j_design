package ru.job4j.solid.dip;

import ru.job4j.solid.lsp.Order;

/**
 * Здесь нарушен DIP принцип,
 * Интерфейс OrderProcessorIncorrect зависит от конкретных
 * реализаций MySQLOrderRepository и ConfirmationEmailSender.
 * С точки зрения принципа DIP было бы правильнее для начала создать некоторые абстракции,
 * например, два интерфейса MailSender и OrderRepository.
 */

public interface OrderProcessorIncorrect {

    MySQLOrderRepository REPOSITORY = new MySQLOrderRepository();

    ConfirmationEmailSender EMAIL_SENDER = new ConfirmationEmailSender();

    default void process(Order order) {
        System.out.println(REPOSITORY);
        System.out.println(EMAIL_SENDER);
    }
}
