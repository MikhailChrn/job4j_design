package ru.job4j.kiss.fool;

import java.util.Scanner;

/**
 * Ваша задача произвести рефакторинг этого класса. Напишите тесты на получившийся код.
 */

public class Fool {
    public static int counter;

    public static Scanner input = new Scanner(System.in);

    public static final String GREETING = "Игра FizzBuzz.";
    public static final String FIZZ = "Fizz";
    public static final String BUZZ = "Buzz";
    public static final String FIZZBUZZ = "FizzBuzz";
    public static final String ERROR = "Ошибка. Начинай снова.";

    public static String checkCounter() {
        return counter % 3 == 0 && counter % 5 == 0 ? FIZZBUZZ
                : counter % 3 == 0 ? FIZZ
                : counter % 5 == 0 ? BUZZ
                : String.valueOf(counter);
    }

    public static void checkPlayer(String answer) {
        if (!checkCounter().equals(answer)) {
            System.out.println(ERROR);
            counter = 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(GREETING);
        counter = 1;
        while (counter < 100) {
            System.out.println(checkCounter());
            counter++;
            checkPlayer(input.nextLine());
            counter++;
        }
    }
}
