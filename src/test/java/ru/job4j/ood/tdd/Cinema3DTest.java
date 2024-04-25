package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

/**
 * Поведение пользователя через тесты.
 * 
 * 1. Посмотрите на созданные тесты. Опишите, каких тестов тут не хватает?
 * 2. Допишите недостающие тесты. Классы реализовывать не нужно.
 *      (Достаточно их создать и реализовать в них интерфейсы)
 */

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class Cinema3DTest {
    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(data -> true);
        assertThat(sessions).contains(session);
    }

    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, -1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenAddSeveralSessionsThanFindListOfSessions() {
        Cinema cinema = new Cinema3D();
        List<Session> expected = new ArrayList<>();
        Session session1 = new Session3D();
        Session session2 = new Session3D();
        Session session3 = new Session3D();
        expected.add(session1);
        expected.add(session2);
        expected.add(session3);
        cinema.add(session1);
        cinema.add(session2);
        cinema.add(session3);
        List<Session> result = cinema.find(new Predicate<Session>() {
            @Override
            public boolean test(Session session) {
                return true;
            }
        });
        assertThat(result.size()).isEqualTo(expected.size());
        for (Session session : expected) {
            assertThat(result).contains(session);
        }
    }

    @Test
    public void whenBuyOnValidTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket result = cinema.buy(account, 5, 5, date);
        assertThat(result).isEqualTo(new Ticket3D());
    }
}
