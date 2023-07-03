package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
            multiplicationTable(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void multiplicationTable(FileOutputStream out) throws IOException {
        Integer cursor;
        for (int row = 1; row < 11; row++) {
            for (int coloumn = 1; coloumn < 11; coloumn++) {
                cursor = row * coloumn;
                out.write(cursor.toString().getBytes());
                out.write("  ".getBytes());
            }
            out.write(System.lineSeparator().getBytes());
        }

    }
}
