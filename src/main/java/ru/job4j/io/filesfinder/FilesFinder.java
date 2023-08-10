package ru.job4j.io.filesfinder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FilesFinder {
    private static ArgsName argsName;

    private static List<String> getFindedFiles(List<FileProperty> allFiles) {
        List<FileProperty> listOfFindedFileProperties = null;
        if ("name".equals(argsName.get("t"))) {
            listOfFindedFileProperties = allFiles.stream()
                    .filter(file -> file.getName().contains(argsName.get("n")))
                    .collect(Collectors.toList());
        }
        if ("regex".equals(argsName.get("t"))) {
            Pattern pattern = Pattern.compile(argsName.get("n"));
             listOfFindedFileProperties = allFiles.stream()
                    .filter(file -> pattern.matcher(file.getName()).matches())
                    .collect(Collectors.toList());
        }
        List<String> findedFiles = null;
        if (listOfFindedFileProperties != null && listOfFindedFileProperties.size() > 0) {
            findedFiles = listOfFindedFileProperties.stream()
                    .map(file -> file.toString())
                    .collect(Collectors.toList());
        }
        return findedFiles;
    }

    private static void saveDataToFile(List<String> resultOfFinding) {
        if (resultOfFinding != null) {
            try (PrintWriter printWriter = new PrintWriter(new FileWriter(argsName.get("o"), true))) {
                resultOfFinding.forEach(printWriter::println);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else {
            System.out.println("No files found.");
        }
    }

    private static void filesFind() throws IOException {
        FindVisitor findVisitor = new FindVisitor();
        Files.walkFileTree(Path.of(argsName.get("d")), findVisitor);
        saveDataToFile(getFindedFiles(findVisitor.getAllFiles()));
    }

    private static ArgsName getVerifiedParams(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        if (argsName.get("d") == null
                || argsName.get("n") == null
                || argsName.get("t") == null
                || argsName.get("o") == null) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        return argsName;
    }

    public static void main(String[] args) throws IOException {
        argsName = getVerifiedParams(args);
        filesFind();
    }
}
