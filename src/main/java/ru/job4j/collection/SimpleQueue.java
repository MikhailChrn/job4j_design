package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int sizeIn;
    private int sizeOut;

    public T poll() {
        if (sizeIn == 0 && sizeOut == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (sizeIn != 0 && sizeOut == 0) {
            for (int i = 0; i < sizeIn; i++) {
                out.push(in.pop());
            }
            sizeOut = sizeIn;
            sizeIn = 0;
        }
        sizeOut--;
        return out.pop();
    }

    public void push(T value) {
        sizeIn++;
        in.push(value);
    }
}
