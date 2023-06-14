package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        for (int s = 0; s < size; s++) {
            out.push(in.pop());
        }
        T result = out.pop();
        size--;
        for (int s = 0; s < size; s++) {
            in.push(out.pop());
        }
        return result;
    }

    public void push(T value) {
        size++;
        in.push(value);
    }
}
