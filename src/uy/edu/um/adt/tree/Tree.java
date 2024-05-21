package uy.edu.um.adt.tree;
import java.util.ArrayList;
import java.util.List;

public class Tree<K, T> implements MyTree<K, T> {
    private Node<K, T> root;

    public Tree() {
        this.root = null;
    }

    @Override
    public T find(K key) {
        return findRecursive(root, key);
    }

    private T findRecursive(Node<K, T> current, K key) {
        if (current == null) {
            return null;
        }
        if (current.key.equals(key)) {
            return current.data;
        }
        T leftResult = findRecursive(current.leftChild, key);
        if (leftResult != null) {
            return leftResult;
        }
        return findRecursive(current.rightChild, key);
    }

    @Override
    public void insert(K key, T data, K parentKey) {
        Node<K, T> newNode = new Node<>(key, data);
        if (root == null) {
            root = newNode;
        } else {
            insertRecursive(root, newNode, parentKey);
        }
    }

    private void insertRecursive(Node<K, T> current, Node<K, T> newNode, K parentKey) {
        if (current == null) {
            return;
        }
        if (current.key.equals(parentKey)) {
            if (current.leftChild == null) {
                current.leftChild = newNode;
            } else if (current.rightChild == null) {
                current.rightChild = newNode;
            }
            return;
        }
        insertRecursive(current.leftChild, newNode, parentKey);
        insertRecursive(current.rightChild, newNode, parentKey);
    }

    @Override
    public void delete(K key) {
        root = deleteRecursive(root, key);
    }

    private Node<K, T> deleteRecursive(Node<K, T> current, K key) {
        if (current == null) {
            return null;
        }
        if (current.key.equals(key)) {
            if (current.leftChild == null && current.rightChild == null) {
                return null;
            }
            if (current.rightChild == null) {
                return current.leftChild;
            }
            if (current.leftChild == null) {
                return current.rightChild;
            }
            K smallestKey = findSmallestKey(current.rightChild);
            current.key = smallestKey;
            current.rightChild = deleteRecursive(current.rightChild, smallestKey);
            return current;
        }
        current.leftChild = deleteRecursive(current.leftChild, key);
        current.rightChild = deleteRecursive(current.rightChild, key);
        return current;
    }

    private K findSmallestKey(Node<K, T> root) {
        return root.leftChild == null ? root.key : findSmallestKey(root.leftChild);
    }

    @Override
    public int size() {
        return sizeRecursive(root);
    }

    private int sizeRecursive(Node<K, T> current) {
        if (current == null) {
            return 0;
        }
        return 1 + sizeRecursive(current.leftChild) + sizeRecursive(current.rightChild);
    }

    @Override
    public int countLeaf() {
        return countLeafRecursive(root);
    }

    private int countLeafRecursive(Node<K, T> current) {
        if (current == null) {
            return 0;
        }
        if (current.leftChild == null && current.rightChild == null) {
            return 1;
        }
        return countLeafRecursive(current.leftChild) + countLeafRecursive(current.rightChild);
    }

    @Override
    public int countCompleteElements() {
        return countCompleteElementsRecursive(root);
    }

    private int countCompleteElementsRecursive(Node<K, T> current) {
        if (current == null) {
            return 0;
        }
        int count = 0;
        if (current.leftChild != null && current.rightChild != null) {
            count = 1;
        }
        return count + countCompleteElementsRecursive(current.leftChild) + countCompleteElementsRecursive(current.rightChild);
    }

    @Override
    public List<K> inOrder() {
        List<K> result = new ArrayList<>();
        inOrderRecursive(root, result);
        return result;
    }

    private void inOrderRecursive(Node<K, T> node, List<K> result) {
        if (node != null) {
            inOrderRecursive(node.leftChild, result);
            result.add(node.key);
            inOrderRecursive(node.rightChild, result);
        }
    }

    @Override
    public List<K> preOrder() {
        List<K> result = new ArrayList<>();
        preOrderRecursive(root, result);
        return result;
    }

    private void preOrderRecursive(Node<K, T> node, List<K> result) {
        if (node != null) {
            result.add(node.key);
            preOrderRecursive(node.leftChild, result);
            preOrderRecursive(node.rightChild, result);
        }
    }

    @Override
    public List<K> postOrder() {
        List<K> result = new ArrayList<>();
        postOrderRecursive(root, result);
        return result;
    }

    private void postOrderRecursive(Node<K, T> node, List<K> result) {
        if (node != null) {
            postOrderRecursive(node.leftChild, result);
            postOrderRecursive(node.rightChild, result);
            result.add(node.key);
        }
    }
}
