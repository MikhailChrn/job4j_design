package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * 1. Генератор получает шаблон вида.*
 * "I am a ${name}, Who are ${subject}? "
 * 2. Программа должна учитывать, что в шаблоне есть ключи,
 *      которых нет в карте (Map) и кидать исключение. *
 * 3. Программа должна учитывать, что в карте есть лишние ключи и тоже кидать исключение. *
 */

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class GeneratorTest {

    @Test
    void whenSuccessfulUseOfTheTemplate() {
        Generator generator = new ModernGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Mikhail Cherneyev");
        args.put("subject", "you");
        String expected = "I am a Petr Arsentev, Who are you?";
        String result = generator.produce(template, args);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenThereAreMissingKeysInTheMap() {
        Generator generator = new ModernGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("subject", "you");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenThereAreMoreThanNeedKeysInTheMap() {
        Generator generator = new ModernGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Mikhail Cherneyev");
        args.put("subject", "you");
        args.put("MoreThanNeedKey", "something");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
