package ru.promo.homework.NikitaMorozka;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class NikitaMorozkaMap<K, V> extends MyMap <K, V> implements Map<K, V> {

    private final AtomicInteger count = new AtomicInteger(0);
    private final Node<K, V>[] buckets;
    private final Object[] locks;

    public NikitaMorozkaMap(int capacity) {
        locks = new Object[capacity];
        for (int i = 0; i < locks.length; i++) {
            locks[i] = new Object();
        }
        buckets = (Node<K, V>[]) new Node[capacity];
    }

    @Override
    public int size() {
        return count.get();
    }

    @Override
    public V get(Object key) {
        if (key == null) throw new IllegalArgumentException();
        int hash = hash(key);
        synchronized (getLockFor(hash)) {
            Node<K, V> node = buckets[getBucketIndex(hash)];

            while (node != null) {
                if (isKeyEquals(key, hash, node)) {
                    return node.value;
                }

                node = node.next;
            }

            return null;
        }
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) throw new IllegalArgumentException();
        int hash = hash(key);
        synchronized (getLockFor(hash)) {
            int bucketIndex = getBucketIndex(hash);
            Node<K, V> node = buckets[bucketIndex];
            if (node == null) {
                buckets[bucketIndex] = new Node<>(hash, key, value, null);
                count.incrementAndGet();
                return null;
            } else {
                Node<K, V> prevNode = node;
                while (node != null) {
                    if (isKeyEquals(key, hash, node)) {
                        V prevValue = node.value;
                        node.value = value;

                        return prevValue;
                    }

                    prevNode = node;
                    node = node.next;
                }

                prevNode.next = new Node<>(hash, key, value, null);
                count.incrementAndGet();
                return null;
            }
        }
    }

    @Override
    public V remove(Object key) {
        if (key == null) throw new IllegalArgumentException();
        int hash = hash(key);
        synchronized (getLockFor(hash(key))) {
            int bucketIndex = getBucketIndex(hash);
            Node<K, V> node = buckets[bucketIndex];

            if (node != null) {
                if (isKeyEquals(key, hash, node)) {
                    if (node.next != null) {
                        buckets[bucketIndex] = node.next;
                    } else {
                        buckets[bucketIndex] = null;
                    }

                    count.decrementAndGet();
                    return node.value;
                }

                Node<K, V> prevNode = node;
                while (node != null) {
                    if (isKeyEquals(key, hash, node)) {
                        V prevValue = node.value;
                        prevNode.next = node.next;

                        count.decrementAndGet();

                        return prevValue;
                    }

                    prevNode = node;
                    node = node.next;
                }
            }

            return null;
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i]) {
                buckets[i] = null;
            }
        }
        count.set(0);
    }

    @Override
    public boolean containsKey(Object key) {
        if (key == null) throw new IllegalArgumentException();
        int hash = hash(key);
        synchronized (getLockFor(hash)) {
            Node<K, V> node = buckets[getBucketIndex(hash)];
            while (node != null) {
                if (isKeyEquals(key, hash, node)) {
                    return true;
                }
                node = node.next;
            }
            return false;
        }
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null) throw new IllegalArgumentException();
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i]) {
                Node<K, V> node = buckets[i];
                while (node != null) {
                    if (isValueEquals(value, node)) { // Проверка соответствия значения
                        return true;
                    }
                    node = node.next;
                }
            }
        }
        return false;
    }


    @Override
    public V replace(K key, V newValue) {
        if (key == null || newValue == null) throw new IllegalArgumentException();
        int hash = hash(key);
        synchronized (getLockFor(hash)) {
            Node<K, V> node = buckets[getBucketIndex(hash)];
            while (node != null) {
                if (isKeyEquals(key, hash, node)) {
                    V oldValue = node.value;
                    node.value = newValue;
                    return oldValue;
                }
                node = node.next;
            }
            return null;
        }
    }


    private boolean isKeyEquals(Object key, int hash, Node<K, V> node) {
        return node.hash == hash &&
                (node.key == key || (node.key != null && node.key.equals(key)));

    }

    private boolean isValueEquals(Object value, Node<K, V> node) {
        return node.value == value || (node.value != null && node.value.equals(value));
    }


    private int hash(Object key) {
        return Math.abs(key.hashCode());
    }

    private int getBucketIndex(int hash) {
        return hash % buckets.length;
    }

    private Object getLockFor(int hash) {
        return locks[hash % locks.length];
    }

    private static class Node<K, V> {
        final int hash;
        K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}