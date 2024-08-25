package ru.promo.homework.shamanStepapDed;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node<K, V> {
    private Node<K, V> leftNode;
    private Node<K, V> rightNode;
    private K key;
    private V value;
    private int hashCode;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.hashCode = key.hashCode();
        this.rightNode = null;
        this.leftNode = null;
    }
}
