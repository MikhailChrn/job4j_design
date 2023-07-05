package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

// Обратите внимание, что каждый метод теста будет работать со своим вариантом файла.
// В этих тестах не должно быть полей класса.

// Дополните тест проверками ниже. Под каждый тест будет свой файл.
//1) на чтение файла с комментариями и пустыми строками
//2) на чтение файла с нарушением шаблона ключ=значение
// (напр. =значение, или ключ= , или строка без символа "=" ключзначение,
// или строка без ключа и без значения, но с символом =)
// в этом случае нужно ожидать исключение IllegalArgumentException
//Строка вида "ключ=значение=1" или "ключ=значение="
// должна быть обработана и распознана как ключ "ключ" и значение "значение=1"
// или "значение=" соответственно.

class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithCommentAndEmptyLines() {
        String path = "./data/pair_with_comment_and_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Mikhail Cherneyev");
        assertThat(config.value("department")).isEqualTo("IT");
    }

    @Test
    void whenInputFileIncludingWrongLines() {
        String path = "./data/wrong_pair_including_file.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Mikhail Cherneyev");
        assertThat(config.value("department")).isEqualTo("IT");
    }
}