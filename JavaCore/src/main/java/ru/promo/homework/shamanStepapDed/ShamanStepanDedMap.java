package ru.promo.homework.shamanStepapDed;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


public class ShamanStepanDedMap<K, V> implements Map<K, V> {

    private int DEFAULT_INITIAL_CAPACITY = 1_000_000_000;
    private final double HASH_TABLE_EXTENSION = 0.7D;
    private int countElement = 0;
    private int size = 16;
    private BinaryTree<K, V>[] hashTable;

    public ShamanStepanDedMap() {
        this.hashTable = new BinaryTree[DEFAULT_INITIAL_CAPACITY];
    }

    public ShamanStepanDedMap(int capacity) {
        if (capacity > 0) {
            DEFAULT_INITIAL_CAPACITY = capacity;
        }
        this.hashTable = new BinaryTree[DEFAULT_INITIAL_CAPACITY];
    }

    private int getHashCode(K key) {
        return Objects.hashCode(key);
    }

    private int getIndexByHashCode(K key) {
        int hashCode = getHashCode(key);
        int index = hashCode % size;
        index = index < 0 ? index * -1 : index;
        return index;
    }

    @Override
    public int size() {
        return countElement;
    }

    @Override
    public boolean isEmpty() {
        return countElement == 0;
    }

    @Override
    public boolean containsKey(Object o) {
        return false;
    }

    @Override
    public boolean containsValue(Object o) {
        return false;
    }

    @Override
    public V get(Object object) {
        K key = (K) object;
        int bucketIndex = getIndexByHashCode(key);
        if (hashTable[bucketIndex] == null) {
            return null;
        }
        Node<K, V> node = hashTable[bucketIndex].findElement(key);
        if (node != null) {

            return node.getValue();
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int bucketIndex = getIndexByHashCode(key);

        if (hashTable[bucketIndex] == null) {
            hashTable[bucketIndex] = new BinaryTree<>();
            hashTable[bucketIndex].addValue(key, value);
        } else {
            if (hashTable[bucketIndex].findElement(key) == null) {
                hashTable[bucketIndex].addValue(key, value);
            }
        }
        countElement++;
        return null;
    }

    @Override
    public V remove(Object o) {
        K key = (K) o;
        int bucketIndex = getIndexByHashCode(key);
        Node<K, V> node = hashTable[bucketIndex].findElement(key);
        if (node != null) {
            V answer = node.getValue();
            hashTable[bucketIndex].deleteValue(node.getKey());
            return answer;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {

    }

    @Override
    public void clear() {
        hashTable = new BinaryTree[DEFAULT_INITIAL_CAPACITY];
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}