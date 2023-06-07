package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (index >= data.length || data.length == 0) {
            return false;
        }
        if (data[index] % 2 != 0) {
            int i = index;
            while (i < data.length - 1) {
                i++;
                if (data[i] % 2 == 0) {
                    index = i;
                    break;
                }
            }
        }
        return data[index] % 2 == 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
