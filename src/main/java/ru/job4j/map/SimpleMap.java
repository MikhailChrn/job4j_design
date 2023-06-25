package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        int bucket = indexFor(hash(Objects.hashCode(key)));
        if (table[bucket] == null) {
            modCount++;
            count++;
            table[bucket] = new MapEntry<>(key, value);
            result = true;
        }
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                int bucket = indexFor(hash(Objects.hashCode(entry.key)));
                newTable[bucket] = entry;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int bucket = indexFor(hash(Objects.hashCode(key)));
        V value = null;
        if (table[bucket] != null
                && Objects.hashCode(table[bucket].key) == Objects.hashCode(key)
                && Objects.equals(table[bucket].key, key)) {
            value = table[bucket].value;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        int bucket = indexFor(hash(Objects.hashCode(key)));
        boolean result = false;
        if (table[bucket] != null
                && Objects.hashCode(table[bucket].key) == Objects.hashCode(key)
                && Objects.equals(table[bucket].key, key)) {
            count--;
            modCount++;
            table[bucket] = null;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int cursor = 0;

            private final int iteratorChecker = modCount;

            @Override
            public boolean hasNext() {
                if (iteratorChecker != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (cursor < table.length && table[cursor] == null) {
                    cursor++;
                }
                return cursor < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
