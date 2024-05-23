package uy.edu.um.adt.hash.hashabierto;

import uy.edu.um.adt.hash.MyHashTable;
import uy.edu.um.adt.tree.binarysearchtree.BinarySearchTree;
import uy.edu.um.adt.tree.binarysearchtree.NodeBST;

public class MyHashTableOpenBST<K extends Comparable<K>, V> implements MyHashTable<K, V> {
    private static final int INITIAL_CAPACITY = 11;
    private BinarySearchTree<K, V>[] table;
    private int size;

    public MyHashTableOpenBST() {
        table = new BinarySearchTree[INITIAL_CAPACITY];
        for (int i = 0; i < table.length; i++) {
            table[i] = new BinarySearchTree<>();
        }
        size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    @Override
    public void put(K key, V value) {
        int index = hash(key);
        BinarySearchTree<K, V> bucket = table[index];
        bucket.insert(key, value);
        size++;

        if (size >= table.length * 2) {
            resize();
        }
    }

    @Override
    public boolean contains(K key) {
        int index = hash(key);
        BinarySearchTree<K, V> bucket = table[index];
        return bucket.find(key) != null;
    }

    @Override
    public void remove(K key) {
        int index = hash(key);
        BinarySearchTree<K, V> bucket = table[index];
        if (bucket.find(key) != null) {
            bucket.delete(key);
            size--;
        }
    }

    private void resize() {
        BinarySearchTree<K, V>[] oldTable = table;
        table = new BinarySearchTree[nextPrime(oldTable.length * 2)];
        for (int i = 0; i < table.length; i++) {
            table[i] = new BinarySearchTree<>();
        }
        size = 0;

        for (BinarySearchTree<K, V> bucket : oldTable) {
            rehash(bucket.getRoot());
        }
    }

    private void rehash(NodeBST<K, V> node) {
        if (node != null) {
            put(node.getKey(), node.getData());
            rehash(node.getLeftChild());
            rehash(node.getRightChild());
        }
    }

    private int nextPrime(int n) {
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
