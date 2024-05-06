package ru.job4j.solid.srp;

public interface GeometricFigure {
    int SQUARE = 0;

    default int getSquare() {
        return SQUARE;
    }
}