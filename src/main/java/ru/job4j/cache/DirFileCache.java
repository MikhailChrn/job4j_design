package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public String getCachingDir() {
        return cachingDir;
    }

    public DirFileCache(String cachingDir) throws IOException {
        this.cachingDir = cachingDir;
    }

    public void printAllFilesInCurrentDirectory() throws IOException {
        getAllFilesPaths(Path.of(cachingDir))
                .forEach(path -> System.out.println(path.toString()));
    }

    public void printAllFilesInCache() throws IOException {
        super.getAllKey()
                .forEach(key -> System.out.println(key.toString()));
    }

    private List<Path> getAllFilesPaths(Path directory) throws IOException {
        FilesObsrv obsrv = new FilesObsrv();
        Files.walkFileTree(directory, obsrv);
        return obsrv.getFilesPaths();
    }

    @Override
    public String load(String fileTitle) throws IOException {
        super.put(fileTitle, Files.readString(Path.of(cachingDir + "/" + fileTitle)));
        return super.get(fileTitle);
    }
}
