package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/**
 * * Создать программу, эмулирующую поведение данного кэша.
 * * Программа должна считывать текстовые файлы из системы
 * * и выдавать текст при запросе имени файла.
 * * Если в кэше файла нет, кэш должен загрузить себе данные.
 * * По умолчанию в кеше нет ни одного файла.
 * * Текстовые файлы должны лежать в одной директории.
 * * Пример: Names.txt, Address.txt - файлы в системе.
 * * При запросе по ключу Names.txt - кеш должен вернуть содержимое файла Names.txt.menu
 * * Важно! key это относительный путь к файлу в директории.
 * <p>
 * <p>
 * 2. Реализовать класс AbstractCache
 * 3. Реализовать класс DirFileCache
 * 4. Создать в папке cache/menu класс Emulator для работы с пользователем.
 * Предоставить пользователю возможности:
 * - указать кэшируемую директорию
 * - загрузить содержимое файла в кэш
 * - получить содержимое файла из кэша
 * <p>
 * src/main/java/ru/job4j/gc/leak/files
 */

public class Menu {

    public static final Integer SET_CACHED_DIRECTORY = 1;
    public static final Integer VIEW_ALL_FILES_IN_DIRECTORY = 2;
    public static final Integer VIEW_ALL_FILES_IN_CACHE = 3;
    public static final Integer UPLOAD_FILE_IN_CACHE = 4;
    public static final Integer GET_FILE_FROM_CACHE = 5;

    public static final String SELECT = "Выберите меню";
    public static final String SELECT_DIRECTORY = "Указажите кэшируемую директорию";
    public static final String DIRECTORY_NOT_EXISTS = "Директория отсутствует.";
    public static final String FILE_IS_ALREADY_IN_CACHE = "Содержимое файла уже в кеше";
    public static final String CACHE_ISNOT_DEFINED = "Кеш не создан. Укажите директорию.";
    public static final String SELECT_FILE = "Укажите адрес файла для получения содержмого из кеша";
    public static final String FILE_NOT_EXIST_IN_CACHE = "Файл отсутствует в кеше.";
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

            } else if (UPLOAD_FILE_IN_CACHE == userChoice) {
                if (!checkDirCache(dirFileCache)) {
                    continue;
                }
                String targetPath = scanner.nextLine();
                if (dirFileCache.get(targetPath) != null) {
                    System.out.println(FILE_IS_ALREADY_IN_CACHE);
                    continue;
                }
                if (!Files.exists(Path.of(targetPath))) {
                    System.out.println(FILE_NOT_EXIST);
                    continue;
                }
                dirFileCache.load(targetPath);

            } else if (GET_FILE_FROM_CACHE == userChoice) {
                if (!checkDirCache(dirFileCache)) {
                    continue;
                }
                System.out.println(SELECT_FILE);
                String result = dirFileCache.get(scanner.nextLine());
                if (result == null) {
                    System.out.println(FILE_NOT_EXIST_IN_CACHE);
                    continue;
                }
                System.out.println(result);

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
