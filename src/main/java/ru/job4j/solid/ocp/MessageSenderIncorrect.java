package ru.job4j.solid.ocp;

/**
 * Здесь нарушен OCP принцип,
 * т.к. при изменении набора способов отправки уведомлений
 * придётся менять существующий код данного класса.
 */

public interface MessageSenderIncorrect {
    public default void send(String msg, MessageType type) {
        if (type == MessageType.SMS) {
            sendSMS(msg);
        } else if (type == MessageType.EMAIL) {
            sendEMAIL(msg);
        }
    }

    private void sendSMS(String msg) {

    }

    private void sendEMAIL(String msg) {

    }
}
