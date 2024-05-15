package ru.job4j.solid.lsp;

/**
 * Здесь нарушен LSP принцип,
 * Отношению Square ISA Shape и Circle ISA Shape не соблюдается по отношению
 * к внешнему поведению потомков Shape, от которого зависит ShapeDrawerIncorrect.
 * Метод drawShapeIncorrect вынужден подстраиваться ко всем потомкам Shape.
 */

public interface ShapeDrawerIncorrect {
    default void drawShapeIncorrect(Shape shape) {
        if (shape instanceof Square) {
            ((Square) shape).drawSquare();
        } else if (shape instanceof Circle) {
            ((Circle) shape).drawCircle();
        }
    }
}
