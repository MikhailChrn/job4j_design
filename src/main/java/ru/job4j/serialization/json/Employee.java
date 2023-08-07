package ru.job4j.serialization.json;

import java.util.Arrays;

public class Employee {
    private final boolean isManager;
    private final String name;
    private final String position;
    private final int age;
    private final Contact contact;
    private Task[] tasks;

    public Employee(boolean isManager, String name, String position,
                    int age, Contact contact, Task[] tasks) {
        this.isManager = isManager;
        this.name = name;
        this.position = position;
        this.age = age;
        this.contact = contact;
        this.tasks = tasks;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public void setTasks(Task[] tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Employee{"
                + "name='" + name + '\''
                + ", contact=" + contact
                + ", tasks=" + Arrays.toString(tasks)
                + '}';
    }
}
