package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {
    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterIntoEndOfList() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addAfter(input, 2, 4);
        assertThat(input).hasSize(4).containsSequence(1, 2, 3, 4);
    }

    @Test
    void whenRemoveIfWithPredicate() {
        ListUtils.addAfter(input, 1, 1);
        ListUtils.addAfter(input, 2, 3);
        ListUtils.addAfter(input, 3, 1);
        ListUtils.addAfter(input, 4, 3);
        ListUtils.removeIf(input, t -> (t == 1));
        assertThat(input).hasSize(3).containsSequence(3, 3, 3);
    }

    @Test
    void whenReplaceIfWithPredicate() {
        ListUtils.addAfter(input, 1, 1);
        ListUtils.addAfter(input, 2, 3);
        ListUtils.addAfter(input, 3, 1);
        ListUtils.addAfter(input, 4, 3);
        ListUtils.replaceIf(input, t -> (t == 1), 5);
        assertThat(input).hasSize(6).containsSequence(5, 3, 5, 3, 5, 3);
    }

    @Test
    void whenRemoveAllElementsThatAreContentedInTheList() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 3, 5);
        ListUtils.addAfter(input, 4, 6);
        ListUtils.removeAll(input, List.of(2, 4, 6));
        assertThat(input).hasSize(3).containsSequence(1, 3, 5);
    }
}
