package uy.edu.um.adt.tree.binarysearchtree;

import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.linkedlist.MyList;

public class BinarySearchTree<K extends Comparable<K>, T> implements MyBinarySearchTree<K, T> {
    private NodeBST<K, T> root;
    public NodeBST<K, T> getRoot() {
        return root;
    }

    public BinarySearchTree() {
        this.root = null;
    }

    @Override
    public T find(K key) {
        return findRecursive(root, key);
    }

    private T findRecursive(NodeBST<K, T> current, K key) {
        if (current == null) {
            return null;
        }
        if (key.compareTo(current.getKey()) == 0) {
            return current.getData();
        }
        return key.compareTo(current.getKey()) < 0
                ? findRecursive(current.getLeftChild(), key)
                : findRecursive(current.getRightChild(), key);
    }

    @Override
    public void insert(K key, T data) {
        root = insertRecursive(root, key, data);
    }

    private NodeBST<K, T> insertRecursive(NodeBST<K, T> current, K key, T data) {
        if (current == null) {
            return new NodeBST<>(key, data);
        }

        if (key.compareTo(current.getKey()) < 0) {
            current.setLeftChild(insertRecursive(current.getLeftChild(), key, data));
        } else if (key.compareTo(current.getKey()) > 0) {
            current.setRightChild(insertRecursive(current.getRightChild(), key, data));
        } else {
            // Si la clave ya existe, se actualiza el dato
            current.setData(data);
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

        if (key.compareTo(current.getKey()) == 0) {
            // Nodo a eliminar encontrado

            // Nodo sin hijos
            if (current.getLeftChild() == null && current.getRightChild() == null) {
                return null;
            }

            // Nodo con un solo hijo
            if (current.getLeftChild() == null) {
                return current.getRightChild();
            }
            if (current.getRightChild() == null) {
                return current.getLeftChild();
            }

            // Nodo con dos hijos
            K smallestKey = findSmallestKey(current.getRightChild());
            current.setKey(smallestKey);
            current.setData(findRecursive(root, smallestKey));
            current.setRightChild(deleteRecursive(current.getRightChild(), smallestKey));
            return current;

        }
        if (key.compareTo(current.getKey()) < 0) {
            current.setLeftChild(deleteRecursive(current.getLeftChild(), key));
            return current;
        }

        current.setRightChild(deleteRecursive(current.getRightChild(), key));
        return current;
    }

    private K findSmallestKey(NodeBST<K, T> root) {
        return root.getLeftChild() == null ? root.getKey() : findSmallestKey(root.getLeftChild());
    }

    @Override
    public MyList<K> inOrder() {
        MyList<K> result = new MyLinkedListImpl<>();
        inOrderRecursive(root, result);
        return result;
    }

    private void inOrderRecursive(NodeBST<K, T> current, MyList<K> result) {
        if (current != null) {
            inOrderRecursive(current.getLeftChild(), result);
            result.add(current.getKey());
            inOrderRecursive(current.getRightChild(), result);
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
            result.add(current.getKey());
            preOrderRecursive(current.getLeftChild(), result);
            preOrderRecursive(current.getRightChild(), result);
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
            postOrderRecursive(current.getLeftChild(), result);
            postOrderRecursive(current.getRightChild(), result);
            result.add(current.getKey());
        }
    }
}
