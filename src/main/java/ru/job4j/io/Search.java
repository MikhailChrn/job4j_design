package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

import static java.lang.String.format;

public class Search {
    private static Boolean validateArgs(String arg0) {
        File file = new File(arg0);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        return true;
    }

    private static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid arguments.");
        }
        try {
            validateArgs(args[0]);
        } catch (IllegalArgumentException ex) {
            System.out.println("Caught IOException: " + ex.getMessage());
        }
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(format(".%s", args[1]))).forEach(System.out::println);
    }
}