package ru.job4j.solid.isp;

/**
 * Здесь нарушен ISP принцип (некорректное выделение абстракций),
 * Интерфейс VehicleIncorrect описывает методы перемещения транспортного средства (fly, drive, sail).
 * В результате классы, реализующие интерфейс Vehicle,
 * вынуждены содержать реализацию всех возможных способов перемещения транспортного средства.
 *
 */

public interface VehicleIncorrect {

    default void fly() {

    }

    default void drive() {

    }

    default void sail() {

    }
}