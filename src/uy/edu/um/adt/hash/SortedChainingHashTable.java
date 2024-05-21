package uy.edu.um.adt.hash;

import uy.edu.um.adt.linkedlist.MySortedLinkedListImpl;

public class SortedChainingHashTable<K extends Comparable<K>, V> implements MyHashTable<K, V> {
    private static final int INITIAL_CAPACITY = 11;

    private MySortedLinkedListImpl<Node<K, V>>[] table;
    private int size;
    private int capacity;

    public SortedChainingHashTable() {
        this.capacity = INITIAL_CAPACITY;
        this.table = new MySortedLinkedListImpl[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new MySortedLinkedListImpl<>();
        }
        this.size = 0;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    @Override
    public void put(K key, V value) {
        int i = hash(key);
        MySortedLinkedListImpl<Node<K, V>> bucket = table[i];

        for (int j = 0; j < bucket.size(); j++) {
            Node<K, V> node = bucket.get(j);
            if (node.getKey().equals(key)) {
                node.setValue(value);
                return;
            }
        }
        bucket.addSorted(new Node<>(key, value));
        size++;
    }

    @Override
    public boolean contains(K key) {
        int i = hash(key);
        MySortedLinkedListImpl<Node<K, V>> bucket = table[i];

        for (int j = 0; j < bucket.size(); j++) {
            Node<K, V> node = bucket.get(j);
            if (node.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(K key) {
        int i = hash(key);
        MySortedLinkedListImpl<Node<K, V>> bucket = table[i];

        for (int j = 0; j < bucket.size(); j++) {
            Node<K, V> node = bucket.get(j);
            if (node.getKey().equals(key)) {
                bucket.remove(node);
                size--;
                return;
            }
        }
    }

    private class Node<K, V> implements Comparable<Node<K, V>> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public int compareTo(Node<K, V> other) {
            return ((Comparable<K>) this.key).compareTo(other.getKey());
        }
    }
}

