package ru.job4j.serialization.json;

public class Task {
    private final String description;
    private final int priority;

    public Task(String description, int priority) {
        this.description = description;
        this.priority = priority;
    }
}
