package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("C:\\projects\\"), duplicatesVisitor);
        duplicatesVisitor.getAllFiles().entrySet().stream()
                .forEach(entry -> {
                    if (entry.getValue().size() > 1) {
                        System.out.printf("%s - %d bytes\n",
                                entry.getKey().getName(),
                                entry.getKey().getSize());
                        entry.getValue().stream().forEach(System.out::println);
                        System.out.print("\n");
                    }
                });
    }
}
