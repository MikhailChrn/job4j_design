package ru.job4j.io.filesfinder;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FindVisitor extends SimpleFileVisitor<Path> {
    private final List<FileProperty> allFiles = new ArrayList<>();

    public List<FileProperty> getAllFiles() {
        return allFiles;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty currentFile = new FileProperty(attrs.size(),
                file.getFileName().toString(), file.toAbsolutePath().toString());
        allFiles.add(currentFile);
        return super.visitFile(file, attrs);
    }
}
