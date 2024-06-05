package ru.job4j.solid.isp;

/**
 * Здесь нарушен ISP принцип (некорректное выделение абстракций),
 * Интерфейс ReportGeneratorIncorrect описывает методы для формирования отчётов (generate).
 * В результате классы, реализующие этот интерфейс и формирующие отдельные виды отчётов,
 * вынуждены содержать реализацию всех этих методов.
 */

public interface ReportGeneratorIncorrect {

    default void generatePDF() {

    }

    default void generateExcel() {

    }

    default void generateXML() {

    }
}
