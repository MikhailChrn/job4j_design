package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;
    private int downCounter = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
        this.downCounter = getNumberOfAllValidElements();
        if (this.downCounter > 0) {
            this.row = getNumberOfFirstValidRow();
        }
    }

    private int getNumberOfAllValidElements() {
        int count = 0;
        for (int[] lcRow : data) {
            count += lcRow.length;
        }
        return count;
    }

    private int getNumberOfFirstValidRow() {
        int number = 0;
        for (int lcRow = 0; lcRow < data.length; lcRow++) {
            if (data[lcRow].length > 0) {
                number = lcRow;
                break;
            }
        }
        return number;
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
        int result = data[row][column];
        downCounter--;
        column++;
        while (row < data.length && column >= data[row].length) {
            column = 0;
            row++;
        }
        return result;
    }
}
