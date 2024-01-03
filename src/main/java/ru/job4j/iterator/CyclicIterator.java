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
        if (this.data.size() == 0) {
            throw new NoSuchElementException();
        }
        return this.data.get(getIndex());
    }

    int getIndex() {
        int res = this.index;
        if (this.index == this.data.size() - 1) {
            this.index = 0;
        } else {
            this.index++;
        }

        return res;
    }
}
