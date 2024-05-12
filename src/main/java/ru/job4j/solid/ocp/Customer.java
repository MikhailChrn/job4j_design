package ru.job4j.solid.ocp;

public interface Customer {
    public default boolean isVip() {
        return false;
    }
}
