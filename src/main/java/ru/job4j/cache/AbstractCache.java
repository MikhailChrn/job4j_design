package ru.job4j.cache;

/**
 * Создать структуру данных типа кеш. Кеш должен быть абстрактный.
 * То есть необходимо, чтобы можно было задать ключ получения объекта кеша,
 * и, в случае если его нет в памяти, задать поведение загрузки этого объекта в кеш.
 */

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {

    }

    public final V get(K key) {
        return null;
    }

    protected abstract V load(K key);

}
