package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkNamesArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");

    }

    @Test
    void checkValidateWhenNameDoesNotContainEqualsSymbol() {
        NameLoad nameLoad = new NameLoad();
        String param = "qwe";
        assertThatThrownBy(() -> nameLoad.parse(param))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain the symbol '='")
                .hasMessageContaining(param);
    }

    @Test
    void checkValidateWhenNameStartWithEqualSymbol() {
        NameLoad nameLoad = new NameLoad();
        String param = "=qwe";
        assertThatThrownBy(() -> nameLoad.parse(param))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a key")
                .hasMessageContaining(param);
    }

    @Test
    void checkValidateWhenNameEndWithEqualSymbol() {
        NameLoad nameLoad = new NameLoad();
        String param = "qwe=";
        assertThatThrownBy(() -> nameLoad.parse(param))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a value")
                .hasMessageContaining(param);
    }
}
