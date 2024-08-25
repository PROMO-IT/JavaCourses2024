package ru.promo.homework.Dok3R73;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Dok3R73Map<K, V> implements Map<K, V> {
    private static final int INITIAL_SIZE = 16;
    private static final double LOAD_FACTOR = 0.75d;

    private List<Entry<K, V>>[] buckets;
    private int size;
    private final ReentrantReadWriteLock lock;

    public Dok3R73Map() {
        this.buckets = new List[INITIAL_SIZE];
        for (int i = 0; i < INITIAL_SIZE; i++) {
            buckets[i] = new ArrayList<>();
        }
        this.size = 0;
        this.lock = new ReentrantReadWriteLock();
    }

    @Override
    public int size() {
        lock.readLock().lock();
        try {
            return size;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        lock.readLock().lock();
        try {
            return size == 0;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public boolean containsKey(Object key) {
        lock.readLock().lock();
        try {
            int index = getIndex(key);
            List<Entry<K, V>> bucket = buckets[index];
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    return true;
                }
            }
            return false;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public boolean containsValue(Object value) {
        lock.readLock().lock();
        try {
            for (List<Entry<K, V>> bucket : buckets) {
                for (Entry<K, V> entry : bucket) {
                    if (entry.getValue().equals(value)) {
                        return true;
                    }
                }
            }
            return false;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public V get(Object key) {
        lock.readLock().lock();
        try {
            int index = getIndex(key);
            List<Entry<K, V>> bucket = buckets[index];
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
            return null;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public V put(K key, V value) {
        lock.writeLock().lock();
        try {
            int index = getIndex(key);
            List<Entry<K, V>> bucket = buckets[index];
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    V oldValue = entry.getValue();
                    entry.setValue(value);
                    return oldValue;
                }
            }
            bucket.add(new Entry<>(key, value));
            size++;

            if ((double) size / buckets.length > LOAD_FACTOR) {
                expansionBuckets();
            }

            return null;
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public V remove(Object key) {
        lock.writeLock().lock();
        try {
            int index = getIndex(key);
            List<Entry<K, V>> bucket = buckets[index];
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    V value = entry.getValue();
                    bucket.remove(entry);
                    size--;
                    return value;
                }
            }
            return null;
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        lock.writeLock().lock();
        try {
            for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void clear() {
        lock.writeLock().lock();
        try {
            for (List<Entry<K, V>> bucket : buckets) {
                bucket.clear();
            }
            size = 0;
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public Set<K> keySet() {
        lock.readLock().lock();
        try {
            Set<K> keySet = new HashSet<>();
            for (List<Entry<K, V>> bucket : buckets) {
                for (Entry<K, V> entry : bucket) {
                    keySet.add(entry.getKey());
                }
            }
            return keySet;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public Collection<V> values() {
        lock.readLock().lock();
        try {
            List<V> values = new ArrayList<>();
            for (List<Entry<K, V>> bucket : buckets) {
                for (Entry<K, V> entry : bucket) {
                    values.add(entry.getValue());
                }
            }
            return values;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        lock.readLock().lock();
        try {
            Set<Map.Entry<K, V>> entrySet = new HashSet<>();
            for (List<Entry<K, V>> bucket : buckets) {
                entrySet.addAll(bucket);
            }
            return entrySet;
        } finally {
            lock.readLock().unlock();
        }
    }


    private int getIndex(Object key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    private void expansionBuckets() {
        int newCapacity = buckets.length * 2;
        List<Entry<K, V>>[] newBuckets = new List[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newBuckets[i] = new ArrayList<>();
        }

        for (List<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                int index = Math.abs(entry.getKey().hashCode() % newCapacity);
                newBuckets[index].add(entry);
            }
        }

        buckets = newBuckets;
    }

    @Override
    public String toString() {
        return "CustomMap2{" +
                "buckets=" + Arrays.toString(buckets) +
                ", size=" + size +
                ", lock=" + lock +
                '}';
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            this.value = value;
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Entry<?, ?> entry = (Entry<?, ?>) obj;
            return Objects.equals(key, entry.key) && Objects.equals(value, entry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}