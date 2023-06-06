package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index = -1;
    private int downCounter;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
        this.downCounter = getAmountOfEvenNumber();
        if (this.downCounter > 0) {
            this.index = getIndexOfEvenNumber(index);
        }
    }

    private boolean isEven(int number) {
        return number % 2 == 0;
    }

    private int getAmountOfEvenNumber() {
        int count = 0;
        for (int i : data) {
            if (isEven(i)) {
                count++;
            }
        }
        return count;
    }

    private Integer getIndexOfEvenNumber(int index) {
        for (int i = index + 1; i < data.length; i++) {
            if (isEven(data[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public boolean hasNext() {
        return downCounter > 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int output = index;
        downCounter--;
        index = getIndexOfEvenNumber(index);
        return data[output];
    }
}
