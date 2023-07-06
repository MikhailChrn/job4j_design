package ru.job4j.io;

import java.io.*;
import java.util.Arrays;

public class Analysis {
    private final static String[] UNAVAILABLE_STATUS
            = new String[] {"400", "500"};

    private boolean isAvailable(String code) {
        return !Arrays.asList(UNAVAILABLE_STATUS)
                .contains(code);
    }

    private static void save(String str, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file, true)))) {
            out.println(str);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            StringBuilder lineBuilder = new StringBuilder();
            boolean isAvailablePeriod = true;
            while (read.ready()) {
                String[] line = read.readLine().split(" ", 2);
                if (!isAvailable(line[0]) & isAvailablePeriod) {
                    isAvailablePeriod = false;
                    lineBuilder.setLength(0);
                    lineBuilder.append(line[1] + ";");
                } else if (isAvailable(line[0]) & !isAvailablePeriod) {
                    isAvailablePeriod = true;
                    lineBuilder.append(line[1] + ";");
                    save(lineBuilder.toString(), target);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
