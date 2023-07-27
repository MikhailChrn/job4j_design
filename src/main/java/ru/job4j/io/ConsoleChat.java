package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private List<String> currentBotPhrase;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    private List<String> readPhrases() {
        List<String> botPhrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(botPhrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return botPhrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getAnswer() {
        return currentBotPhrase.get((int) (currentBotPhrase.size() * Math.random()));
    }

    public void run() {
        currentBotPhrase = readPhrases();
        List<String> log = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean isAbleToRespond = true;
        boolean isAbleToListen = true;
        String inputPhrase = scanner.nextLine();
        log.add(inputPhrase);
        while (isAbleToListen) {
            if (OUT.equals(inputPhrase)) {
                isAbleToListen = false;
                continue;
            }
            if (isAbleToRespond) {
                log.add(getAnswer());
                System.out.println(log.get(log.size() - 1));
            }
            inputPhrase = scanner.nextLine();
            log.add(inputPhrase);
            if (STOP.equals(inputPhrase)) {
                isAbleToRespond = false;
            }
            if (CONTINUE.equals(inputPhrase)) {
                isAbleToRespond = true;
            }
        }
        saveLog(log);
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/data/dialog.log", "./src/data/answers.txt");
        cc.run();
    }
}