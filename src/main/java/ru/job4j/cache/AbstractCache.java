package ru.job4j.cache;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        cache.put(key, new SoftReference<V>(value));
    }

    public final V get(K key) throws IOException {
        if (cache.get(key) == null) {
            load(key);
        }
        return (V) cache.get(key);
    }

    public final Set<K> getAllKey() {
        return cache.keySet();
    }

    public final boolean isEmpty() {
        boolean result = false;
        if (cache.isEmpty()) {
            result = true;
        }
        return result;
    }

    protected abstract V load(K key) throws IOException;
}
