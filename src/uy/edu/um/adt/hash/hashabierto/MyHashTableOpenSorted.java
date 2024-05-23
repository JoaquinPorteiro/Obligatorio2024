package uy.edu.um.adt.hash.hashabierto;

import uy.edu.um.adt.hash.MyHashTable;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;

public class MyHashTableOpenSorted<K extends Comparable<K>, V> implements MyHashTable<K, V> {
    private static final int INITIAL_CAPACITY = 11;
    private MyLinkedListImpl<Entry<K, V>>[] table;
    private int size;

    public MyHashTableOpenSorted() {
        table = new MyLinkedListImpl[INITIAL_CAPACITY];
        for (int i = 0; i < table.length; i++) {
            table[i] = new MyLinkedListImpl<>();
        }
        size = 0;
    }

    private static class Entry<K, V> implements Comparable<Entry<K, V>> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Entry<K, V> other) {
            return ((Comparable<K>) this.key).compareTo(other.key);
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    @Override
    public void put(K key, V value) {
        int index = hash(key);
        MyLinkedListImpl<Entry<K, V>> bucket = table[index];

        // Verificar si la clave ya existe y actualizar el valor
        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        // Crear una nueva entrada
        Entry<K, V> newEntry = new Entry<>(key, value);

        // Insertar la nueva entrada en la posición ordenada
        int insertIndex = 0;
        while (insertIndex < bucket.size() && bucket.get(insertIndex).compareTo(newEntry) < 0) {
            insertIndex++;
        }
        if (insertIndex == bucket.size()) {
            bucket.add(newEntry);
        } else {
            MyLinkedListImpl<Entry<K, V>> newBucket = new MyLinkedListImpl<>();
            for (int i = 0; i < insertIndex; i++) {
                newBucket.add(bucket.get(i));
            }
            newBucket.add(newEntry);
            for (int i = insertIndex; i < bucket.size(); i++) {
                newBucket.add(bucket.get(i));
            }
            table[index] = newBucket;
        }

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
