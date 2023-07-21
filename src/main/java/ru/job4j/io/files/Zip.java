package ru.job4j.io.files;

import ru.job4j.io.ArgsName;
import ru.job4j.io.SearchFiles;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static ArgsName getArgsName(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException(
                    String.format("There should be three parameters.")
            );
        }
        ArgsName jvm = ArgsName.of(args);
        File dir = new File(jvm.get("d"));
        if (!dir.exists()) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", dir.getAbsoluteFile())
            );
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Not directory %s", dir.getAbsoluteFile())
            );
        }
        String exclude = jvm.get("e");
        if (exclude.length() < 2 || exclude.charAt(0) != '.') {
            throw new IllegalArgumentException(
                    String.format("Invalid exclude %s", exclude)
            );
        }
        String output = jvm.get("o");
        if (!output.endsWith(".zip")) {
            throw new IllegalArgumentException(
                    String.format("Output source must end with '%s'", output)
            );
        }
        return jvm;
    }

    public static void main(String[] args) throws IOException {
        ArgsName jvm = getArgsName(args);
        Zip zip = new Zip();
        Path start = Paths.get(jvm.get("d"));
        List<Path> pathList = search(start, p -> !p.toFile().getName().endsWith(jvm.get("e")));
        zip.packFiles(pathList, new File(jvm.get("o")));
    }
}
