package ru.job4j.solid.isp;

/**
 * Здесь нарушен ISP принцип (некорректное выделение абстракций),
 * Интерфейс ShapeIncorrect описывает методы для рисования кругов (drawCircle),
 * квадратов (drawSquare) и прямоугольников (drawRectangle).
 * В результате классы, реализующие этот интерфейс и представляющие отдельные геометрические фигуры,
 * такие как круг (Circle), квадрат (Square) и прямоугольник (Rectangle),
 * должны содержать реализацию всех этих методов.
 */

public interface ShapeIncorrect {
    
    default void drawCircle() {

    }

    default void drawSquare() {

    }

    default void drawRectangle() {

    }
}
