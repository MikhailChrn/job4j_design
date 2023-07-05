package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static final String NOT_FOUND = "404";

    public static List<String> filter(String file) {
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file));) {
            in.lines().forEach(line -> {
                if (NOT_FOUND.equals(line.split(" ")[8])) {
                    result.add(line);
                }
            });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return result;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file, true)))) {
            for (String str : log) {
                out.printf("%s%n", str);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("data/log.txt");
        save(log, "data/404.txt");
    }
}
