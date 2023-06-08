package ru.job4j.generics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    private RoleStore store;

    @BeforeEach
    public void setUp() {
        store = new RoleStore();
    }

    @Test
    void whenAddAndFindThenRoleNameIsOwner() {
        store.add(new Role("1", "Owner"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Owner");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        store.add(new Role("1", "Administrator"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsOperator() {
        store.add(new Role("1", "Operator"));
        store.add(new Role("1", "Owner"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Operator");
    }

    @Test
    void whenReplaceThenRoleNameIsOperator() {
        store.add(new Role("1", "Owner"));
        store.replace("1", new Role("1", "Operator"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Operator");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        store.add(new Role("1", "Operator"));
        store.replace("10", new Role("10", "Administrator"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Operator");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        store.add(new Role("1", "Owner"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenRoleNameIsOperator() {
        store.add(new Role("1", "Operator"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Operator");
    }

    @Test
    void whenReplaceOkThenTrue() {
        store.add(new Role("1", "Administrator"));
        boolean result = store.replace("1", new Role("1", "Operator"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        store.add(new Role("1", "Operator"));
        boolean result = store.replace("10", new Role("10", "Administrator"));
        assertThat(result).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        store.add(new Role("1", "Owner"));
        boolean result = store.delete("1");
        assertThat(result).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        store.add(new Role("1", "Administrator"));
        boolean result = store.delete("2");
        assertThat(result).isFalse();
    }

}