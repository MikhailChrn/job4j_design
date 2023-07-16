package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<Path>> allFiles = new HashMap<>();

    public Map<FileProperty, List<Path>> getAllFiles() {
        return allFiles;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty currentFile = new FileProperty(attrs.size(), file.getFileName().toString());
        if (!allFiles.containsKey(currentFile)) {
            List<Path> filePathList = new ArrayList<>();
            filePathList.add(file.toAbsolutePath());
            allFiles.put(currentFile, filePathList);
        } else {
            allFiles.get(currentFile).add(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}
