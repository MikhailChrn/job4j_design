package ru.job4j.solid.srp;

import java.util.List;

/**
 * Интерфейс AreaCalculatorUncorrect, представленный здесь, суммирует площади геометрических фигур.
 * Этот класс нарушает принцип единственной ответственности (SRP).
 * В соответствии с принципом единственной ответственности класс должен решать лишь какую-то одну задачу.
 * Он же решает две: суммирует площади фигур и выводит площади фигур на экран.
 */

public interface AreaCalculatorIncorrect {
    public int sum(List<GeometricFigure> figures);

    public void printSum(List<GeometricFigure> figures);
}
