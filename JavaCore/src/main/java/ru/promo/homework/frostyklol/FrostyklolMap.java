package ru.promo.homework.frostyklol;

import java.util.*;

public class FrostyklolMap<K, V> implements Map<K, V> {
    private static final int START_CAPACITY = 50;
    private static final double LOAD_FACTOR = 0.75;
    private Entry<K, V>[] table;
    private int size;

    public void SimpleMap() {
        table = new Entry[START_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int index = getIndex(key);
        Entry<K, V> entry = table[index];
        return entry != null && Objects.equals(entry.getKey(), key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (Entry<K, V> entry : table) {
            if (entry != null && Objects.equals(entry.getValue(), value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int index = getIndex(key);
        Entry<K, V> entry = table[index];
        if (entry != null && Objects.equals(entry.getKey(), key)) {
            return entry.getValue();
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if ((double) size / table.length > LOAD_FACTOR) { //берем текущий размер таблицы и делим на общий размер таблицы, если больше -> ресайз
            resize();
        }

        int index = getIndex(key);
        Entry<K, V> entry = table[index];

        if (entry == null) {
            table[index] = new Entry<>(key, value);
            size++;
            return null;
        } else {
            V oldValue = entry.getValue();
            entry.setValue(value);
            return oldValue;
        }
    }

    @Override
    public V remove(Object key) {
        int index = getIndex(key);
        Entry<K, V> entry = table[index];

        if (entry != null && Objects.equals(entry.getKey(), key)) {
            V value = entry.getValue();
            table[index] = null;
            size--;
            return value;
        }

        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        table = new Entry[START_CAPACITY];
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Entry<K, V> entry : table) {
            if (entry != null) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (Entry<K, V> entry : table) {
            if (entry != null) {
                values.add(entry.getValue());
            }
        }
        return values;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entries = new HashSet<>();
        for (Entry<K, V> entry : table) {
            if (entry != null) {
                entries.add(entry);
            }
        }
        return entries;
    }

    private int getIndex(Object key) {
        return key == null ? 0 : Math.abs(key.hashCode()) % table.length;
    }

    private void resize() {
        int newCapacity = table.length * 2;
        Entry<K, V>[] newTable = new Entry[newCapacity];

        for (Entry<K, V> entry : table) {
            if (entry != null) {
                int index = getIndex(entry.getKey());
                newTable[index] = entry;
            }
        }

        table = newTable;
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public String toString() {
            return "Entry{" + "key=" + key + ", value=" + value + '}';
        }
    }
}
