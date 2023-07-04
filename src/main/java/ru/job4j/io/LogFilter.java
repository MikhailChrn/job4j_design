package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file)); ) {
            in.lines().forEach(line -> {
                if (line.split(" ")[8].equals("404")) {
                    result.add(line);
                }
            });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        for (String str : log) {
            System.out.println(str);
        }
    }
}
