package ru.promo.homework.funa4i;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Funa4iMap<K extends Comparable<K>, V> implements Map<K, V>{
    NodeOfTree<K, V> head;
    private final Lock _deleteWriteLock = new ReentrantLock();

    private final Lock _headChekLock = new ReentrantLock();
    private final AtomicInteger _size = new AtomicInteger(0);


    @Override
    public int size() {
        return _size.get();
    }

    @Override
    public boolean isEmpty() {
        return _size.get() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if (key == null){
            throw new IllegalArgumentException("Key can't be null");
        }

        NodeOfTree<K, V> node = getNodeForKey((K)key);
        try {
            return node != null;
        }
        finally {
            if (node != null){
                node.locker.unlock();
            }
        }
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        if (key == null){
            throw new IllegalArgumentException("Key can't be null");
        }

        NodeOfTree<K, V> node = getNodeForKey((K)key);
        try {
            if (node == null) {
                return null;
            }
            return node.data;
        } finally {
            if (node != null){
                node.locker.unlock();
            }
        }
    }


    @Override
    public V put(K key, V value) {
        _deleteWriteLock.lock();
        try {
            V returnobj = null;
            NodeOfTree<K, V> currentNode;
            if (head == null) {
                _headChekLock.lock();
                try {
                    head = new NodeOfTree<>(key, value, null);
                    synchronized (_size) {
                        _size.incrementAndGet();
                    }
                    return null;
                }finally {
                    _headChekLock.unlock();
                }
            } else {
                currentNode = head;
                currentNode.locker.lock();
                while (true) {
                    if (key.compareTo(currentNode.getKey()) > 0) {
                        if (currentNode.right == null) {

                            currentNode.right = new NodeOfTree<>(key, value, currentNode);
                            currentNode.locker.unlock();
                            break;

                        }

                        currentNode.right.locker.lock();
                        currentNode.locker.unlock();
                        currentNode = currentNode.right;

                    } else if (key.compareTo(currentNode.getKey()) < 0) {

                        if (currentNode.left == null) {
                            currentNode.left = new NodeOfTree<>(key, value, currentNode);
                            currentNode.locker.unlock();
                            break;

                        }


                        currentNode.left.locker.lock();
                        currentNode.locker.unlock();
                        currentNode = currentNode.left;

                    } else {

                        returnobj = currentNode.data;
                        currentNode.data = value;
                        currentNode.locker.unlock();
                        break;
                    }
                }
            }
            _size.incrementAndGet();
            balanceTree(currentNode);
            return returnobj;
        }
        finally {
            _deleteWriteLock.unlock();
        }
    }

    @Override
    public V remove(Object key) {
        _deleteWriteLock.lock();
        if (key == null){
            throw new IllegalArgumentException("Key can't be null");
        }
        NodeOfTree<K, V> nodeStart = getNodeForKey((K)key);
        if (nodeStart == null){
            return null;
        }
        NodeOfTree<K, V> nodeSwitch = nodeStart;
        if (nodeStart.right != null) {
            nodeSwitch.right.locker.lock();
            nodeSwitch = nodeStart.right;
            while (nodeSwitch.left != null) {
                nodeSwitch.left.locker.lock();
                nodeSwitch = nodeSwitch.left;
                nodeSwitch.ancestor.locker.unlock();
            }
        }
        if (nodeSwitch == head){
            _headChekLock.lock();
            try {
                head = null;
            }finally {
                _headChekLock.unlock();
            }
        }
        nodeStart.setKey(nodeSwitch.getKey());
        nodeStart.data = nodeSwitch.data;
        nodeStart.locker.unlock();
        if (nodeSwitch != nodeStart) {
            nodeSwitch.locker.unlock();
        }
        if (nodeSwitch.isRed()){
            deleteNode(nodeSwitch);
            _size.decrementAndGet();
            return nodeSwitch.data;
        }
        else {
            if (nodeSwitch.right != null) {
                nodeSwitch.locker.lock();
                nodeSwitch.right.locker.lock();

                nodeSwitch.swapNodes(nodeSwitch.right);

                nodeSwitch.locker.unlock();
                nodeSwitch.right.locker.unlock();

                nodeSwitch.right.setKey(nodeSwitch.getKey());
                deleteNode(nodeSwitch.right);

            } else if (nodeSwitch.left != null) {
                nodeSwitch.locker.lock();
                nodeSwitch.left.locker.lock();

                nodeSwitch.swapNodes(nodeSwitch.left);

                nodeSwitch.locker.unlock();
                nodeSwitch.left.locker.unlock();
                nodeSwitch.left.setKey(nodeSwitch.getKey());
                return nodeSwitch.data;
            } else {
                deleteNode(nodeSwitch);
            }
        }
        nodeSwitch = nodeSwitch.ancestor;
        try {
            _size.decrementAndGet();
            return nodeSwitch.data;
        }
        finally {
            balanceTreeFromNodeAfterDelete(nodeSwitch);
            _deleteWriteLock.unlock();
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

        m.forEach(this::put);

    }

    @Override
    public void clear() {
        _headChekLock.lock();
        head = null;
        _size.set(0);
        _headChekLock.unlock();
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

    private NodeOfTree<K, V> getNodeForKey(K key){
        NodeOfTree<K, V> currentNode;

        _headChekLock.lock();
        if (head == null){
            try {
                return null;
            }finally {
                _headChekLock.unlock();
            }
        }
        currentNode = head;
        _headChekLock.unlock();
        head.locker.lock();
        NodeOfTree<K, V> tempNode;
        while (true){
            if (key.compareTo(currentNode.getKey()) > 0){
                if (currentNode.right == null) {
                    currentNode.locker.unlock();
                    return null;
                }

                currentNode.right.locker.lock();
                currentNode.locker.unlock();
                currentNode = currentNode.right;


            } else if (key.compareTo(currentNode.getKey()) < 0) {

                if (currentNode.left == null) {
                    currentNode.locker.unlock();
                    return null;
                }

                currentNode.left.locker.lock();
                currentNode.locker.unlock();
                currentNode = currentNode.left;
            }
            else {
                return currentNode;
            }
        }
    }

    private void balanceTree(NodeOfTree<K, V> node){
        redBrotherSonParent(node);

        tryRotateLR(node);
        tryRotateLL(node);

        tryRotateRL(node);
        tryRotateRR(node);
        if (node.ancestor != null){
            balanceTree(node.ancestor);
        }
    }

    private boolean redBrotherSonParent(NodeOfTree<K, V> parentNode){
        if (!parentNode.isRed()){
            return false;
        }
        if (parentNode.left != null && parentNode.right != null){
            if (parentNode.left.isRed() && parentNode.right.isRed()) {
                return false;
            }
        }
        if (!isBrotherRed(parentNode)){
            return false;
        }

        int flag = 0;
        if (parentNode.left != null){
            if (parentNode.left.isRed()){
                flag++;
            }
        }
        if (parentNode.right != null){
            if (parentNode.right.isRed()){
                flag++;
            }
        }
        if (flag == 0){
            return false;
        }

        parentNode.ancestor.setColorRed();
        parentNode.ancestor.right.setColorBlack();
        parentNode.ancestor.left.setColorBlack();

        return true;
    }


    private boolean  tryLR(NodeOfTree<K, V> parentNode){
        if (parentNode.right == null){
            return false;
        }
        if (parentNode.getKey().compareTo(parentNode.ancestor.getKey()) < 0) {
            var anc = parentNode.ancestor;
            anc.locker.lock();

            parentNode.locker.lock();
            parentNode.right.locker.lock();

            anc.left = parentNode.right;
            anc.left.ancestor = anc;
            anc = anc.left;

            NodeOfTree<K, V> tempNode;
            tempNode = anc.left;
            anc.left = parentNode;

            parentNode.ancestor = anc;
            parentNode.right = tempNode;
            if (tempNode != null){
                tempNode.ancestor = parentNode.right;
            }

            anc.ancestor.locker.unlock();
            anc.locker.unlock();
            anc.left.locker.unlock();
            return true;
        }
        return false;
    }

    private void tryRotateLL(NodeOfTree<K, V> parentNode){
        if (parentNode.left == null) {
            return;
        }
        if (!parentNode.left.isRed()){
            return;
        }
        if (parentNode.left.left == null){
            return;
        }
        if (!parentNode.left.left.isRed()){
            return;
        }
        tryLL(parentNode);
        parentNode.setColorRed();
        parentNode.ancestor.setColorBlack();
    }
    private void tryLL(NodeOfTree<K, V> parentNode){
        var anc = parentNode.ancestor;
        if (anc != null) {
            anc.locker.lock();
        }
        parentNode.locker.lock();
        parentNode.left.locker.lock();

        if (anc == null){
            anc = parentNode.left;
            head = anc;
            anc.ancestor = null;
        }
        else if (parentNode.getKey().compareTo(parentNode.ancestor.getKey()) > 0){
            anc.right = parentNode.left;
            anc.right.ancestor = anc;
            anc = anc.right;
        }
        else {
            anc.left = parentNode.left;
            anc.left.ancestor = anc;
            anc = anc.left;
        }

        NodeOfTree<K, V> tempNode;
        tempNode = anc.right;
        anc.right = parentNode;
        parentNode.ancestor = anc;
        parentNode.left = tempNode;
        if (tempNode != null){
            tempNode.ancestor = parentNode;
        }
        if (anc.ancestor != null){
            anc.ancestor.locker.unlock();
        }
        anc.locker.unlock();
        parentNode.locker.unlock();
    }

    private void tryRotateRL(NodeOfTree<K, V> parentNode){
        if (!parentNode.isRed()){
            return;
        }
        if (parentNode.ancestor == null){
            return;
        }
        if (parentNode.left == null) {
            return;
        }
        if (!parentNode.left.isRed()){
            return;
        }
        if (parentNode.ancestor.left != null){
            if (parentNode.ancestor.left.isRed()){
                return;
            }
        }
        tryRL(parentNode);
    }
    private void tryRotateLR(NodeOfTree<K, V> parentNode){
        if (!parentNode.isRed()){
            return;
        }
        if (parentNode.ancestor == null){
            return;
        }
        if (parentNode.right == null) {
            return;
        }
        if (!parentNode.right.isRed()){
            return;
        }

        if (parentNode.ancestor.right != null){
            if (parentNode.ancestor.right.isRed()){
                return;
            }
        }
        tryLR(parentNode);
    }

    private void tryRotateRR(NodeOfTree<K, V> parentNode){
        if (parentNode.right == null) {
            return;
        }
        if (!parentNode.right.isRed()){
            return;
        }
        if (parentNode.right.right == null){
            return;
        }
        if (!parentNode.right.right.isRed()){
            return;
        }
        tryRR(parentNode);
        parentNode.setColorRed();
        parentNode.ancestor.setColorBlack();

    }

    private void deleteNode(NodeOfTree<K, V> node){
        node.ancestor.locker.lock();
        node.locker.lock();
        if (node.getKey().compareTo(node.ancestor.getKey()) >= 0){
            node.ancestor.right = null;
        }
        else {
            node.ancestor.left = null;
        }
        node.locker.unlock();
        node.ancestor.locker.unlock();
    }

    private boolean furtherCousinRed(NodeOfTree<K, V> parentNode){
        var anc = parentNode.ancestor;
        if (anc == null){
            return false;
        }
        if (parentNode.getKey().compareTo(parentNode.ancestor.getKey()) < 0) {
            if (anc.right.isRed()) {
                return false;
            }
            if (anc.right.right == null) {
                return false;
            }
            if (!anc.right.right.isRed()) {
                return false;
            }
            tryRR(anc);
            anc.swapColors(anc.ancestor);
            anc.ancestor.right.setColorBlack();
        }
        else if (parentNode.getKey().compareTo(parentNode.ancestor.getKey()) >= 0) {
            if (anc.left.isRed()) {
                return false;
            }
            if (anc.left.left == null) {
                return false;
            }
            if (!anc.left.left.isRed()) {
                return false;
            }
            tryLL(anc);
            anc.swapColors(anc.ancestor);
            anc.ancestor.left.setColorBlack();
        }
        return true;
    }

    private boolean nearCousinRed(NodeOfTree<K, V> parentNode) {
        var anc = parentNode.ancestor;
        if (parentNode.getKey().compareTo(parentNode.ancestor.getKey()) < 0) {
            if (anc.right.isRed()) {
                return false;
            }
            if (anc.right.left == null) {
                return false;
            }
            if (!anc.right.left.isRed()) {
                return false;
            }
            if (anc.right.right != null) {
                if (anc.right.right.isRed()) {
                    return false;
                }
            }
            tryRL(anc);
            anc.right.setColorBlack();
            anc.right.right.setColorRed();
        }
        else if (parentNode.getKey().compareTo(parentNode.ancestor.getKey()) >= 0){
            if (anc.left.isRed()) {
                return false;
            }
            if (anc.left.right == null) {
                return false;
            }
            if (!anc.left.right.isRed()) {
                return false;
            }
            if (anc.left.left != null) {
                if (anc.left.left.isRed()) {
                    return false;
                }
            }
            tryLR(anc);
            anc.left.setColorBlack();
            anc.left.left.setColorRed();
        }
        return furtherCousinRed(parentNode);
    }

    private boolean tryRL(NodeOfTree<K, V> parentNode){
        if (parentNode.left == null){
            return false;
        }
        if (parentNode.getKey().compareTo(parentNode.ancestor.getKey()) > 0) {
            var anc = parentNode.ancestor;
            anc.locker.lock();
            parentNode.locker.lock();
            parentNode.left.locker.lock();

            anc.right = parentNode.left;
            anc.right.ancestor = anc;
            anc = anc.right;

            NodeOfTree<K, V> tempNode;
            tempNode = anc.right;
            anc.right = parentNode;

            parentNode.ancestor = anc;
            parentNode.left = tempNode;

            anc.ancestor.locker.unlock();
            anc.locker.unlock();
            anc.right.locker.unlock();
            return true;
        }
        return false;
    }

    private void tryRR(NodeOfTree<K, V> parentNode){
        var anc = parentNode.ancestor;
        if (anc != null) {
            anc.locker.lock();
        }
        parentNode.locker.lock();
        parentNode.right.locker.lock();

        if (anc == null){
            anc = parentNode.right;
            head = anc;
            anc.ancestor = null;
        }
        else if (parentNode.getKey().compareTo(parentNode.ancestor.getKey()) > 0){
            anc.right = parentNode.right;
            anc.right.ancestor = anc;
            anc = anc.right;
        }
        else {
            anc.left = parentNode.right;
            anc.left.ancestor = anc;
            anc = anc.left;
        }

        NodeOfTree<K, V> tempNode;
        tempNode = anc.left;
        anc.left = parentNode;
        parentNode.ancestor = anc;
        parentNode.right = tempNode;
        if (tempNode != null){
            tempNode.ancestor = parentNode;
        }
        if (anc.ancestor != null){
            anc.ancestor.locker.unlock();
        }

        anc.locker.unlock();
        parentNode.locker.unlock();
    }

    private void balanceTreeFromNodeAfterDelete(NodeOfTree<K, V> nodeSwitch){
        if (nodeSwitch.ancestor == head) {
            if (nodeSwitch.ancestor.right != null) {
                nodeSwitch.ancestor.right.setColorRed();
            } else {
                nodeSwitch.ancestor.left.setColorRed();
            }
            return;
        }
        while (nodeSwitch.ancestor != null){
            if (!isBrotherRed(nodeSwitch)) {
                if (furtherCousinRed(nodeSwitch) || nearCousinRed(nodeSwitch)) {
                    return;
                } else if (nodeSwitch.ancestor.isRed()) {
                    nodeSwitch.ancestor.setColorBlack();
                    if (nodeSwitch.getKey().compareTo(nodeSwitch.ancestor.getKey()) < 0) {
                        nodeSwitch.ancestor.right.setColorRed();
                    }
                    else if (nodeSwitch.getKey().compareTo(nodeSwitch.ancestor.getKey()) >= 0){
                        nodeSwitch.ancestor.left.setColorRed();
                    }
                    return;
                }
                else {
                    if (nodeSwitch.getKey().compareTo(nodeSwitch.ancestor.getKey()) < 0){
                        nodeSwitch.ancestor.right.setColorRed();
                    }
                    else if (nodeSwitch.getKey().compareTo(nodeSwitch.ancestor.getKey()) >= 0){
                        nodeSwitch.ancestor.left.setColorRed();
                    }
                }
            }
            else {
                if (nodeSwitch.ancestor.right.isRed()){
                    tryRR(nodeSwitch.ancestor);
                }
                else {
                    tryLL(nodeSwitch.ancestor);
                }

                nodeSwitch.ancestor.ancestor.setColorBlack();
                nodeSwitch = nodeSwitch.ancestor;
            }
            nodeSwitch= nodeSwitch.ancestor;
        }

    }

    private boolean isBrotherRed(NodeOfTree<K, V> node) {
        if (node.ancestor == null) {
            return false;
        }

        if (node.getKey().compareTo(node.ancestor.getKey()) < 0) {
            if (node.ancestor.right != null) {
                return node.ancestor.right.isRed();
            }
        } else {
            if (node.ancestor.left != null) {
                return node.ancestor.left.isRed();
            }
        }
        return false;
    }
}