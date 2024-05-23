package ru.job4j.ood.warehouse;

import java.util.List;

public interface Store<T> {
    void add(T obj);

    List<T> findAll();
}
