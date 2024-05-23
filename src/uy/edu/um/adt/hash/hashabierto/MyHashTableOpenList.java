package uy.edu.um.adt.hash.hashabierto;

import uy.edu.um.adt.hash.MyHashTable;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;

public class MyHashTableOpenList<K, V> implements MyHashTable<K, V> {
    private static final int INITIAL_CAPACITY = 11;
    private MyLinkedListImpl<Entry<K, V>>[] table;
    private int size;

    public MyHashTableOpenList() {
        table = new MyLinkedListImpl[INITIAL_CAPACITY];
        for (int i = 0; i < table.length; i++) {
            table[i] = new MyLinkedListImpl<>();
        }
        size = 0;
    }

    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    @Override
    public void put(K key, V value) {
        int index = hash(key);
        MyLinkedListImpl<Entry<K, V>> bucket = table[index];

        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        bucket.add(new Entry<>(key, value));
        size++;

        if (size >= table.length * 2) {
            resize();
        }
    }

    @Override
    public boolean contains(K key) {
        int index = hash(key);
        MyLinkedListImpl<Entry<K, V>> bucket = table[index];

        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if (entry.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(K key) {
        int index = hash(key);
        MyLinkedListImpl<Entry<K, V>> bucket = table[index];

        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if (entry.key.equals(key)) {
                bucket.remove(entry);
                size--;
                return;
            }
        }
    }

    private void resize() {
        MyLinkedListImpl<Entry<K, V>>[] oldTable = table;
        table = new MyLinkedListImpl[nextPrime(oldTable.length * 2)];
        for (int i = 0; i < table.length; i++) {
            table[i] = new MyLinkedListImpl<>();
        }
        size = 0;

        for (MyLinkedListImpl<Entry<K, V>> bucket : oldTable) {
            for (int i = 0; i < bucket.size(); i++) {
                Entry<K, V> entry = bucket.get(i);
                put(entry.key, entry.value);
            }
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
