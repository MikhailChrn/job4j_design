package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    void whenPairWithoutComment() throws Exception {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"))
                .isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithCommentAndEmptyLines() throws Exception {
        String path = "./data/pair_with_comment_and_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"))
                .isEqualTo("Mikhail Cherneyev");
        assertThat(config.value("department"))
                .isEqualTo("IT");
    }

    @Test
    void whenInputFileIncludingLongLines() throws Exception {
        String path = "./data/including_long_line.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"))
                .isEqualTo("Mikhail Cherneyev=department=IT");
        assertThat(config.value("department"))
                .isEqualTo("HR= 78");
    }

    @Test
    void whenThrowsIllegalArgumentException() {
        String path = "./data/wrong_pair_including_file.properties";
        Config config = new Config(path);
        assertThatThrownBy(() ->config.load())
                .isInstanceOf(IllegalArgumentException.class);
    }
}