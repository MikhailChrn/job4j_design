package ru.job4j.io.files;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public static void packFiles(List<Path> sources, File target) {
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

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkArgs(ArgsName argsName) {
        File dir = new File(argsName.get("d"));
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
        String exclude = argsName.get("e");
        if (exclude.length() < 2 || exclude.charAt(0) != '.') {
            throw new IllegalArgumentException(
                    String.format("Invalid exclude %s", exclude)
            );
        }
        String output = argsName.get("o");
        if (!output.endsWith(".zip")) {
            throw new IllegalArgumentException(
                    String.format("Output source must end with '%s'", output)
            );
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException(
                    "There should be three parameters.");
        }
        ArgsName jvm = ArgsName.of(args);
        checkArgs(jvm);
        Path start = Paths.get(jvm.get("d"));
        List<Path> pathList = Search.search(start, p -> !p.toFile().getName().endsWith(jvm.get("e")));
        packFiles(pathList, new File(jvm.get("o")));
    }
}
