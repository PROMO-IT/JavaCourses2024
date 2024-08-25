package ru.promo.homework.sofiaivv;

import java.util.*;

public class SofiaivvMap<K, V> implements java.util.Map<K, V> {
    private static class Node<K, V> {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final LinkedList<Node<K, V>>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public SofiaivvMap(int capacity) {
        table = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
        size = 0;
    }

    private int getHash(K key) {
        return key == null ? 0 : Math.abs(key.hashCode() % table.length);
    }

    @Override
    public V put(K key, V value) {
        int hash = getHash(key);
        LinkedList<Node<K, V>> bucket = table[hash];

        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                V oldValue = node.value;
                node.value = value; // обновляем значение
                return oldValue; // возвращаем старое значение
            }
        }

        bucket.add(new Node<>(key, value));
        size++;
        return null; // если добавлен новый элемент, возвращаем null
    }

    @Override
    public V get(Object key) {
        int hash = getHash((K) key);
        LinkedList<Node<K, V>> bucket = table[hash];

        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                return node.value; // возвращаем значение по ключу
            }
        }
        return null; // если ключ не найден
    }

    @Override
    public V remove(Object key) {
        int hash = getHash((K) key);
        LinkedList<Node<K, V>> bucket = table[hash];

        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                V value = node.value;
                bucket.remove(node); // удаляем узел
                size--;
                return value; // возвращаем удаленное значение
            }
        }
        return null; // если ключ не найден
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

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
        return get(key) != null; // проверяем наличие ключа
    }

    @Override
    public boolean containsValue(Object value) {
        for (LinkedList<Node<K, V>> bucket : table) {
            for (Node<K, V> node : bucket) {
                if (node.value.equals(value)) {
                    return true; // если нашли значение
                }
            }
        }
        return false; // если не нашли значение
    }

    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i].clear();
        }
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    @Override
    public Collection<V> values() {
        return List.of();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return Set.of();
    }
}
