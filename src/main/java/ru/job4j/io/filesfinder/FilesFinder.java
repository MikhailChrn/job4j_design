package ru.job4j.io.filesfinder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FilesFinder {
    private static ArgsName argsName;

    private static String getRegexFromMask(String mask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('^');
        mask.chars()
                .forEach(ch -> {
                    if (ch == '*') {
                        stringBuilder.append(".*");
                    } else if (ch == '?') {
                        stringBuilder.append(".{1}");
                    } else {
                        stringBuilder.append(Character.toString(ch));
                    }
                });
        stringBuilder.append('$');
        return stringBuilder.toString();
    }

    private static List<String> getFindedFiles(List<FileProperty> allFiles) {
        List<FileProperty> listOfFindedFileProperties = null;
        if ("name".equals(argsName.get("t"))) {
            listOfFindedFileProperties = allFiles.stream()
                    .filter(file -> file.getName().contains(argsName.get("n")))
                    .collect(Collectors.toList());
        }
        if ("mask".equals(argsName.get("t"))) {
            Pattern pattern = Pattern.compile(getRegexFromMask(argsName.get("n")));
            listOfFindedFileProperties = allFiles.stream()
                    .filter(file -> pattern.matcher(file.getName()).matches())
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
        if (Files.notExists(Path.of(argsName.get("d")))) {
            throw new IllegalArgumentException("Path not exists");
        }
        File result = new File(argsName.get("o"));
        if (!result.exists()) {
            try {
                result.createNewFile();
            } catch (IOException ioException) {
                throw new IllegalArgumentException("Path not exists");
            }
        }
        String mode = argsName.get("t");
        if (!"name".equals(mode)
                && !"mask".equals(mode)
                && !"regex".equals(mode)) {
            throw new IllegalArgumentException("Invalid search type");
        }
        return argsName;
    }

    private static void printHint() {
        System.out.println("Программа должна запускаться с параметрами: ");
        System.out.println("Ключи \n"
                + "-d - директория, в которой начинать поиск.\n"
                + "-n - имя файла, маска, либо регулярное выражение.\n"
                + "-t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.\n"
                + "-o - результат записать в файл.\n");
        System.out.println("Например:  -d=c:  -n=*.?xt -t=mask -o=log.txt");
    }

    public static void main(String[] args) throws IOException {
        printHint();
        argsName = getVerifiedParams(args);
        filesFind();
    }
}
