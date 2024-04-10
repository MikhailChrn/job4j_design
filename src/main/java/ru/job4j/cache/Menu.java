package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/**
 *
 *  * Создать программу, эмулирующую поведение данного кэша.
 *  * Программа должна считывать текстовые файлы из системы
 *  * и выдавать текст при запросе имени файла.
 *  * Если в кэше файла нет, кэш должен загрузить себе данные.
 *  * По умолчанию в кеше нет ни одного файла.
 *  * Текстовые файлы должны лежать в одной директории.
 *  * Пример: Names.txt, Address.txt - файлы в системе.
 *  * При запросе по ключу Names.txt - кеш должен вернуть содержимое файла Names.txt.menu
 *  * Важно! key это относительный путь к файлу в директории.
 *
 *
 * 2. Реализовать класс AbstractCache
 * 3. Реализовать класс DirFileCache
 * 4. Создать в папке cache/menu класс Emulator для работы с пользователем.
 *      Предоставить пользователю возможности:
 *          - указать кэшируемую директорию
 *          - загрузить содержимое файла в кэш
 *          - получить содержимое файла из кэша
 *
 *          src/main/java/ru/job4j/gc/leak/files
 */

public class Menu {

    public static final Integer SET_CACHED_DIRECTORY = 1;
    public static final Integer UPLOAD_FILES_IN_CACHE = 2;
    public static final Integer GET_FILE_FROM_CACHE = 3;

    public static final String SELECT = "Выберите меню";
    public static final String SELECT_DIRECTORY = "Указажите кэшируемую директорию";
    public static final String LOAD = "Загрузите содержимое файлов в кэш";
    public static final String GET = "Получить содержимое файла из кэша";
    public static final String SELECT_FILE = "Укажите имя файла для получения содержмого";
    public static final String EXIT = "До свидания !";

    public static final String MENU = """
                Введите 1, для выбора кешируемой папки.
                Введите 2, чтобы инициировать кеширование файлов.
                Введите 3, чтобы получить содержимое файла.
                Введите любое другое число для выхода.
            """;

    private static List<Path> getFilesPaths(Path directory) throws IOException {
        FilesObsrv obsrv = new FilesObsrv();
        Files.walkFileTree(directory, obsrv);
        return obsrv.getFilesPaths();
    }

    private static void start(Scanner scanner) throws IOException {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (SET_CACHED_DIRECTORY == userChoice) {
                System.out.println(SELECT_DIRECTORY);
                String directory = scanner.nextLine();
                getFilesPaths(Path.of(directory))
                        .forEach(path -> System.out.println(path));

            /*} else if (UPLOAD_FILES_IN_CACHE == userChoice) {

            } else if (GET_FILE_FROM_CACHE == userChoice) {*/

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
