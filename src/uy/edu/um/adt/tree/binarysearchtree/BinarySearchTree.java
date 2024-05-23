package uy.edu.um.adt.tree.binarysearchtree;

import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.linkedlist.MyList;

public class BinarySearchTree<K extends Comparable<K>, T> implements MyBinarySearchTree<K, T> {
    private NodeBST<K, T> root;

    public BinarySearchTree() {
        this.root = null;
    }

    private static class NodeBST<K, T> {
        K key;
        T data;
        NodeBST<K, T> leftChild;
        NodeBST<K, T> rightChild;

        NodeBST(K key, T data) {
            this.key = key;
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    @Override
    public T find(K key) {
        return findRecursive(root, key);
    }

    private T findRecursive(NodeBST<K, T> current, K key) {
        if (current == null) {
            return null;
        }
        if (key.compareTo(current.key) == 0) {
            return current.data;
        }
        return key.compareTo(current.key) < 0
                ? findRecursive(current.leftChild, key)
                : findRecursive(current.rightChild, key);
    }

    @Override
    public void insert(K key, T data) {
        root = insertRecursive(root, key, data);
    }

    private NodeBST<K, T> insertRecursive(NodeBST<K, T> current, K key, T data) {
        if (current == null) {
            return new NodeBST<>(key, data);
        }

        if (key.compareTo(current.key) < 0) {
            current.leftChild = insertRecursive(current.leftChild, key, data);
        } else if (key.compareTo(current.key) > 0) {
            current.rightChild = insertRecursive(current.rightChild, key, data);
        } else {
            // Si la clave ya existe, se actualiza el dato
            current.data = data;
        }

        return current;
    }

    @Override
    public void delete(K key) {
        root = deleteRecursive(root, key);
    }

    private NodeBST<K, T> deleteRecursive(NodeBST<K, T> current, K key) {
        if (current == null) {
            return null;
        }

        if (key.compareTo(current.key) == 0) {
            // Nodo a eliminar encontrado

            // Nodo sin hijos
            if (current.leftChild == null && current.rightChild == null) {
                return null;
            }

            // Nodo con un solo hijo
            if (current.leftChild == null) {
                return current.rightChild;
            }
            if (current.rightChild == null) {
                return current.leftChild;
            }

            // Nodo con dos hijos
            K smallestKey = findSmallestKey(current.rightChild);
            current.key = smallestKey;
            current.data = findRecursive(root, smallestKey);
            current.rightChild = deleteRecursive(current.rightChild, smallestKey);
            return current;

        }
        if (key.compareTo(current.key) < 0) {
            current.leftChild = deleteRecursive(current.leftChild, key);
            return current;
        }

        current.rightChild = deleteRecursive(current.rightChild, key);
        return current;
    }

    private K findSmallestKey(NodeBST<K, T> root) {
        return root.leftChild == null ? root.key : findSmallestKey(root.leftChild);
    }

    @Override
    public MyList<K> inOrder() {
        MyList<K> result = new MyLinkedListImpl<>();
        inOrderRecursive(root, result);
        return result;
    }

    private void inOrderRecursive(NodeBST<K, T> current, MyList<K> result) {
        if (current != null) {
            inOrderRecursive(current.leftChild, result);
            result.add(current.key);
            inOrderRecursive(current.rightChild, result);
        }
    }

    @Override
    public MyList<K> preOrder() {
        MyList<K> result = new MyLinkedListImpl<>();
        preOrderRecursive(root, result);
        return result;
    }

    private void preOrderRecursive(NodeBST<K, T> current, MyList<K> result) {
        if (current != null) {
            result.add(current.key);
            preOrderRecursive(current.leftChild, result);
            preOrderRecursive(current.rightChild, result);
        }
    }

    @Override
    public MyList<K> postOrder() {
        MyList<K> result = new MyLinkedListImpl<>();
        postOrderRecursive(root, result);
        return result;
    }

    private void postOrderRecursive(NodeBST<K, T> current, MyList<K> result) {
        if (current != null) {
            postOrderRecursive(current.leftChild, result);
            postOrderRecursive(current.rightChild, result);
            result.add(current.key);
        }
    }
}

