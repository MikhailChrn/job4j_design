package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Ссылка для работы с папкой:
 * src/main/java/ru/job4j/gc/leak/files
 */

public class Emulator {

    public static final Integer SET_CACHED_DIRECTORY = 1;
    public static final Integer VIEW_ALL_FILES_IN_DIRECTORY = 2;
    public static final Integer VIEW_ALL_FILES_IN_CACHE = 3;
    public static final Integer UPLOAD_FILE_INTO_CACHE = 4;
    public static final Integer GET_FILE_FROM_CACHE = 5;

    public static final String SELECT = "Выберите меню";
    public static final String SELECT_DIRECTORY = "Указажите кэшируемую директорию:";
    public static final String DIRECTORY_NOT_EXISTS = "Директория отсутствует.";
    public static final String FILE_IS_ALREADY_IN_CACHE = "Содержимое файла уже в кеше.";
    public static final String CACHE_ISNOT_DEFINED = "Кеш не создан. Укажите директорию.";
    public static final String SELECT_FILE = "Укажите имя файла для получения содержмого из кеша";
    public static final String FILE_NOT_EXIST = "Файл отсутствует в папке.";
    public static final String EXIT = "До свидания !";

    public static final String MENU = """
                Введите 1, выбор кешируемой папки.
                Введите 2, чтобы отобразить все имеющиеся файлы в папке.
                Введите 3, чтобы отобразить все файлы в кеше.
                Введите 4, чтобы загрузить содержимое файл в кеш.
                Введите 5, чтобы получить содержимое файла из кеша.
                Введите любое другое число для выхода.
            """;

    private static boolean checkDirCache(DirFileCache dirFileCache) {
        boolean result = true;
        if (dirFileCache == null) {
            System.out.println(CACHE_ISNOT_DEFINED);
            result = false;
        }
        return result;
    }

    private static boolean checkFileTitle(String title, DirFileCache dirFileCache) throws IOException {
        return dirFileCache.getAllValidTitles().stream()
                .anyMatch(str -> title.equals(str));
    }

    private static void start(Scanner scanner) throws IOException {
        boolean run = true;
        DirFileCache dirFileCache = null;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());

            if (SET_CACHED_DIRECTORY == userChoice) {
                System.out.println(SELECT_DIRECTORY);
                Path directory = Path.of(scanner.nextLine());
                if (!Files.exists(directory) || !Files.isDirectory(directory)) {
                    System.out.println(DIRECTORY_NOT_EXISTS);
                    continue;
                }
                dirFileCache = new DirFileCache(directory.toString());

            } else if (VIEW_ALL_FILES_IN_DIRECTORY == userChoice) {
                if (!checkDirCache(dirFileCache)) {
                    continue;
                }
                dirFileCache.printAllFilesInCurrentDirectory();

            } else if (VIEW_ALL_FILES_IN_CACHE == userChoice) {
                if (!checkDirCache(dirFileCache)) {
                    continue;
                }
                if (dirFileCache.isEmpty()) {
                    continue;
                }
                dirFileCache.printAllFilesInCache();

            } else if (UPLOAD_FILE_INTO_CACHE == userChoice) {
                if (!checkDirCache(dirFileCache)) {
                    continue;
                }
                System.out.println(SELECT_FILE);
                String fileTitle = scanner.nextLine();
                if (dirFileCache.get(fileTitle) != null) {
                    System.out.println(FILE_IS_ALREADY_IN_CACHE);
                    continue;
                }
                if (!Files.exists(Path.of(dirFileCache.getCachingDir() + "/" + fileTitle))) {
                    System.out.println(FILE_NOT_EXIST);
                    continue;
                }
                dirFileCache.load(fileTitle);

            } else if (GET_FILE_FROM_CACHE == userChoice) {
                if (!checkDirCache(dirFileCache)) {
                    continue;
                }
                System.out.println(SELECT_FILE);
                String title = scanner.nextLine();
                if (!checkFileTitle(title, dirFileCache)) {
                    System.out.println(FILE_NOT_EXIST);
                    continue;
                }
                System.out.println(dirFileCache.get(title));

            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        start(scanner);
    }
}
