package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    private static List<Integer> getNumbersOfColumns(String path, String delimiter, String filter) throws FileNotFoundException {
        Scanner paramScanner = new Scanner(filter).useDelimiter(",");
        List<String> nameOfColumns = new ArrayList<>();
        paramScanner.forEachRemaining(nameOfColumns::add);
        List<Integer> numbersOfColumns = new ArrayList<>();
        try (Scanner lineScanner = new Scanner(new File(path)).useDelimiter(System.lineSeparator())) {
            String firstLine = lineScanner.next();
            String[] column = firstLine.split(delimiter);
            for (String name : nameOfColumns) {
                for (int n = 0; n < column.length; n++) {
                    if (name.equals(column[n])) {
                        numbersOfColumns.add(n);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException();
        }
        return numbersOfColumns;
    }

    private static List<String> getListOfProcessedRows(ArgsName argsName) throws FileNotFoundException {
        String delimiter = argsName.get("delimiter");
        List<Integer> numberOfColumns = getNumbersOfColumns(argsName.get("path"), delimiter, argsName.get("filter"));
        List<String> listOfProcessedRows = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(new File(argsName.get("path")))
                .useDelimiter(System.lineSeparator())) {
            while (rowScanner.hasNext()) {
                String line = rowScanner.next();
                StringJoiner stringJoiner = new StringJoiner(delimiter);
                String[] column = line.split(delimiter);
                numberOfColumns.forEach(n -> stringJoiner.add(column[n]));
                listOfProcessedRows.add(stringJoiner.toString());
            }
        }
        return listOfProcessedRows;
    }

    private static void saveDataToFile(ArgsName argsName) throws FileNotFoundException {
        List<String> listOfProcessedRows = getListOfProcessedRows(argsName);
        String out = argsName.get("out");
        if ("stdout".equals(out)) {
            listOfProcessedRows.forEach(System.out::println);
        }
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(out, true))) {
            listOfProcessedRows.forEach(printWriter::println);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static ArgsName getVerifiedParams(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        if (argsName.get("path") == null
                || argsName.get("delimiter") == null
                || argsName.get("out") == null
                || argsName.get("filter") == null) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        return argsName;
    }

    public static void handle(ArgsName argsName) throws Exception {
        saveDataToFile(argsName);
    }

    public static void main(String[] args) throws Exception {
        handle(getVerifiedParams(args));
    }
}
