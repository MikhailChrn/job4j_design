package ru.job4j.ood.warehouse;

import java.time.LocalDate;

public interface Calculator<T> {
    int calculate(T t);

    void setCurrentDate(LocalDate date);
}
