package ru.job4j.question;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalizeTest {
    @Test
    void whenNotChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set previous = Set.of(u1, u2, u3);
        Set current = Set.of(u1, u2, u3);
        assertThat(Analize.diff(previous, current)).isEqualTo(new Info(0, 0, 0));
    }

    @Test
    void whenOneChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set previous = Set.of(u1, u2, u3);
        Set current = Set.of(u1, new User(2, "BB"), u3);
        assertThat(Analize.diff(previous, current)).isEqualTo(new Info(0, 1, 0));
    }

    @Test
    void whenOneDeleted() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set previous = Set.of(u1, u2, u3);
        Set current = Set.of(u1, u3);
        assertThat(Analize.diff(previous, current)).isEqualTo(new Info(0, 0, 1));
    }

    @Test
    void whenOneAdded() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set previous = Set.of(u1, u2, u3);
        Set current = Set.of(u1, u2, u3, new User(4, "D"));
        assertThat(Analize.diff(previous, current)).isEqualTo(new Info(1, 0, 0));
    }

    @Test
    void whenAllChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set previous = Set.of(u1, u2, u3);
        Set current = Set.of(new User(1, "AA"), u2, new User(4, "D"));
        assertThat(Analize.diff(previous, current)).isEqualTo(new Info(1, 1, 1));
    }

    @Test
    void whenChangeTwoFromNine() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        User u4 = new User(4, "D");
        User u5 = new User(5, "F");
        User u6 = new User(6, "G");
        User u7 = new User(7, "H");
        User u8 = new User(8, "J");
        User u9 = new User(9, "K");
        Set previous = Set.of(u1, u2, u3, u4, u5, u6, u7, u8, u9);
        Set current = Set.of(u1, u2,
                new User(3, "CC"), u4,
                new User(5, "FF"), u6, u7,
                new User(10, "J"),
                new User(11, "K"));
        assertThat(Analize.diff(previous, current)).isEqualTo(new Info(2, 2, 2));
    }
}
