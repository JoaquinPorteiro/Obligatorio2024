package uy.edu.um.adt.hash;

public class MyHashTableImpl<K, V> implements MyHashTable<K, V> {
    private static final int INITIAL_CAPACITY = 11; // Tamaño inicial de la tabla (un número primo)
    private Entry<K, V>[] table;
    private int size;
    private boolean useQuadratic;

    public MyHashTableImpl(boolean useQuadratic) {
        this.useQuadratic = useQuadratic;
        table = new Entry[INITIAL_CAPACITY];
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
        if (size >= table.length / 2) {
            resize();
        }

        int index = hash(key);
        int i = 0;
        while (table[index] != null && !table[index].key.equals(key)) {
            i++;
            index = getNextIndex(index, i);
        }

        if (table[index] == null) {
            size++;
        }
        table[index] = new Entry<>(key, value);
    }

    @Override
    public boolean contains(K key) {
        int index = hash(key);
        int i = 0;
        while (table[index] != null) {
            if (table[index].key.equals(key)) {
                return true;
            }
            i++;
            index = getNextIndex(index, i);
        }
        return false;
    }

    @Override
    public void remove(K key) {
        int index = hash(key);
        int i = 0;
        while (table[index] != null) {
            if (table[index].key.equals(key)) {
                table[index] = null;
                size--;
                rehash();
                return;
            }
            i++;
            index = getNextIndex(index, i);
        }
    }

    private int getNextIndex(int currentIndex, int i) {
        if (useQuadratic) {
            return (currentIndex + i * i) % table.length;
        } else {
            return (currentIndex + 1) % table.length;
        }
    }

    private void resize() {
        Entry<K, V>[] oldTable = table;
        table = new Entry[nextPrime(oldTable.length * 2)];
        size = 0;

        for (Entry<K, V> entry : oldTable) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
    }

    private void rehash() {
        Entry<K, V>[] oldTable = table;
        table = new Entry[oldTable.length];
        size = 0;

        for (Entry<K, V> entry : oldTable) {
            if (entry != null) {
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