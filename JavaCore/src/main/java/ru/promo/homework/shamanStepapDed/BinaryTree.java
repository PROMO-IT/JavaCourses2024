package ru.promo.homework.shamanStepapDed;

public class BinaryTree<K, V> {
    private Node<K, V> first = null;

    public Node<K,V> findElement(K key) {
        if (first == null) {
//            System.out.println("Пустое бинарное дерево");
            return null;
        }
//        System.out.println("Мы ищем элемент с ключом " + key.toString());
        var result = findElement(first, key.hashCode());
        if (result != null) {
            return result;
        }
        return null;
    }

    private Node<K, V> findElement(Node<K, V> node, int hashCode) {
        if (node.getHashCode() == hashCode) {
//            System.out.println("Мы нашли ваш элемент!УРАААААААААААААААААа");
//            System.out.println();
//            System.out.println();
            return node;
        }
        if (node.getHashCode() > hashCode) {
            if (node.getLeftNode() == null) {
//                System.out.println("Элемент не был найден");
                return null;
            } else {
//                System.out.println("Спускаемся левее");
                return findElement(node.getLeftNode(), hashCode);
            }
        } else {
            if (node.getRightNode() == null) {
//                System.out.println("Элемент не был найден");
                return null;
            } else {
//                System.out.println("Спускаемся правее");
                return findElement(node.getRightNode(), hashCode);
            }
        }
    }

    public void addValue(K key, V value) {
        if (first == null) {
            first = new Node<K, V>(key, value);
        }
        addValue(key.hashCode(), first, key, value);
    }

    private Node<K, V> addValue(int hashCode, Node<K, V> node, K key, V value) {
        if (node.getHashCode() > hashCode) {
            if (node.getLeftNode() == null) {
                node.setLeftNode(new Node<K, V>(key, value));
                return null;
            } else {
                return addValue(hashCode, node.getLeftNode(), key, value);
            }
        } else if (node.getHashCode() < hashCode) {
            if (node.getRightNode() == null) {
                node.setRightNode(new Node<K, V>(key, value));
                return null;
            } else {
                return addValue(hashCode, node.getRightNode(), key, value);
            }
        }
        return null;
    }

    public boolean deleteValue(K key) {
        Node<K, V> currentNode = first;
        Node<K, V> parentNode = first;

        boolean isLeftChild = true;
        while (currentNode.getHashCode() != key.hashCode()) {
            parentNode = currentNode;
            if (key.hashCode() < currentNode.getHashCode()) {
                currentNode = currentNode.getLeftNode();
            } else {
                isLeftChild = false;
                currentNode = currentNode.getRightNode();
            }
            if (currentNode == null) {
                return false;
            }
        }
        if (currentNode.getLeftNode() == null && currentNode.getRightNode() == null) {
            if (currentNode == first) {
                first = null;
            } else if (isLeftChild) {
                parentNode.setLeftNode(null);
            } else {
                parentNode.setRightNode(null);
            }
        } else if (currentNode.getRightNode() == null) {
            if (currentNode == first) {
                first = currentNode.getLeftNode();
            } else if (isLeftChild) {
                parentNode.setLeftNode(currentNode.getLeftNode());
            } else {
                parentNode.setRightNode(currentNode.getLeftNode());
            }
        } else if (currentNode.getLeftNode() == null) {
            if (currentNode == first) {
                first = currentNode.getRightNode();
            } else if (isLeftChild) {
                parentNode.setLeftNode(currentNode.getRightNode());
            } else {
                parentNode.setRightNode(currentNode.getRightNode());
            }
        } else {
            Node<K, V> heir = receiveHeir(currentNode);
            if (currentNode == first) {
                first = heir;
            } else if (isLeftChild) {
                parentNode.setLeftNode(heir);
            } else {
                parentNode.setRightNode(heir);
            }
        }
        return true;
    }

    private Node<K, V> receiveHeir(Node<K, V> node) {
        Node<K, V> parentNode = node;
        Node<K, V> heirNode = node;
        Node<K, V> currentNode = node.getRightNode();
        while (currentNode != null) {
            parentNode = heirNode;
            heirNode = currentNode;
            currentNode = currentNode.getLeftNode();
        }
        if (heirNode != node.getRightNode()) {
            parentNode.setLeftNode(heirNode.getRightNode());
            heirNode.setRightNode(node.getRightNode());
        }
        return heirNode;
    }
}
