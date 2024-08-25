package ru.promo.homework.funa4i;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NodeOfTree<K extends Comparable<K>, V> {

    // 0 - black
    // 1 - red
    private boolean _isRed = true;

    final public Lock locker;

    private K _key;

    public K getKey() {
        return _key;
    }

    V data;
    NodeOfTree<K, V> left, right, ancestor;

    public NodeOfTree(K key, V value, NodeOfTree<K, V> ancestor){
        this._key = key;
        this.data = value;
        this.ancestor = ancestor;
        if (ancestor == null){
            _isRed = false;
        }
        locker = new ReentrantLock();
    }

    public boolean isRed(){
        return _isRed;
    }

    public void setColorBlack()
    {
        _isRed = false;
    }

    public void setColorRed(){
        if (ancestor == null){
            _isRed = false;
            return;
        }
        _isRed = true;
    }

    void swapColors(NodeOfTree<K, V> node){
        boolean tempColor = node.isRed();
        if (this._isRed){
            node.setColorRed();
        }
        else {
            node.setColorBlack();
        }
        if (tempColor){
            this.setColorRed();
        }
        else{
            this.setColorBlack();
        }
    }

    void swapNodes(NodeOfTree<K, V> node){
        V tempV = node.data;
        node.data = data;
        data = tempV;
        K tempK = node._key;
        node._key = _key;
        _key = tempK;
    }

    public void setKey(K key){
        this._key = key;
    }
}