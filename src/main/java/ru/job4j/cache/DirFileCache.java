package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public String getCachingDir() {
        return cachingDir;
    }

    public DirFileCache(String cachingDir) throws IOException {
        this.cachingDir = cachingDir;
    }

    public void printAllFilesInCurrentDirectory() throws IOException {
        getAllFilesPaths()
                .forEach(path -> System.out.println(path.toString()));
    }

    public void printAllFilesInCache() throws IOException {
        super.getAllKey()
                .forEach(key -> System.out.println(key.toString()));
    }

    private List<Path> getAllFilesPaths() throws IOException {
        FilesObsrv obsrv = new FilesObsrv();
        Files.walkFileTree(Path.of(cachingDir), obsrv);
        return obsrv.getFilesPaths();
    }

    public List<String> getAllValidTitles() throws IOException {
        return getAllFilesPaths().stream()
                .map(path -> {
                    String[] strs = path.toString().split("/");
                    return strs[strs.length - 1];
                    })
                .collect(Collectors.toList());

    }

    @Override
    public String load(String fileTitle) throws IOException {
        super.put(fileTitle, Files.readString(Path.of(cachingDir + "/" + fileTitle)));
        return super.get(fileTitle);
    }
}
