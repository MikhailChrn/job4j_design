package ru.job4j.collection;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        int initialCapacity = 16;
        Map<User, Object> map = new HashMap<>(initialCapacity);
        User user1 = new User("Jhon", 1, birthday);
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> initialCapacity);
        int bucket1 = hashCode1 & (initialCapacity - 1);
        User user2 = new User("Jhon", 1, birthday);
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> initialCapacity);
        int bucket2 = hashCode2 & (initialCapacity - 1);
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.printf("user1 хешкод: %s, хеш: %s, бакет: %s",
                hashCode1, hash1, bucket1);
        System.out.println();
        System.out.printf("user2 хешкод: %s, хеш: %s, бакет: %s",
                hashCode2, hash2, bucket2);
    }
}
