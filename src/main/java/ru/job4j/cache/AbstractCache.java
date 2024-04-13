package ru.job4j.cache;

/**
 * Создать структуру данных типа кеш. Кеш должен быть абстрактный.
 * То есть необходимо, чтобы можно было задать ключ получения объекта кеша,
 * и, в случае если его нет в памяти, задать поведение загрузки этого объекта в кеш.
 */

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        cache.putIfAbsent(key, new SoftReference<V>(value));
    }

    public final V get(K key) {
        return cache.getOrDefault(key, new SoftReference<>(null)).get();
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

    protected abstract V load(K key);
}
