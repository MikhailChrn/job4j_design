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

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int getBucketNumber(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private boolean compareElement(int bucket, K key) {
        return table[bucket] != null
                && Objects.hashCode(table[bucket].key)
                                    == Objects.hashCode(key)
                && Objects.equals(table[bucket].key, key);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                newTable[getBucketNumber(entry.key)] = entry;
            }
        }
        table = newTable;
    }

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        int bucket = getBucketNumber(key);
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

    @Override
    public V get(K key) {
        V value = null;
        int bucket = getBucketNumber(key);
        if (compareElement(bucket, key)) {
            value = table[bucket].value;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int bucket = getBucketNumber(key);
        if (compareElement(bucket, key)) {
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
}
