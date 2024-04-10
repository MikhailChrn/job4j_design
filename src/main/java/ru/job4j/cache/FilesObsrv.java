package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FilesObsrv extends SimpleFileVisitor<Path> {

    private List<Path> filePaths = new ArrayList<>();

    public List<Path> getFilesPaths() {
        return filePaths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!Files.isDirectory(file)) {
            filePaths.add(file);
        }
        return super.visitFile(file, attrs);
    }
}

