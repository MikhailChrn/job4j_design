package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {

    private List<T> data;
    private int index;

    public CyclicIterator(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean res = true;
        if (this.data.size() == 0) {
            res = false;
        }

        return res;
    }

    @Override
    public T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        if (this.index == this.data.size()) {
            this.index = 0;
        }

        return this.data.get(index++);
    }
}
