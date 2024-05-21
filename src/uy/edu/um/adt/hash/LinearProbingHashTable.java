package uy.edu.um.adt.hash;

public class LinearProbingHashTable<K, V> implements MyHashTable<K, V> {
    private static final int INITIAL_CAPACITY = 11;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    private K[] keys;
    private V[] values;
    private int size;
    private int capacity;

    public LinearProbingHashTable() {
        this.capacity = INITIAL_CAPACITY;
        this.keys = (K[]) new Object[capacity];
        this.values = (V[]) new Object[capacity];
        this.size = 0;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private void resize(int newCapacity) {
        LinearProbingHashTable<K, V> temp = new LinearProbingHashTable<>();
        temp.capacity = newCapacity;
        temp.keys = (K[]) new Object[newCapacity];
        temp.values = (V[]) new Object[newCapacity];

        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], values[i]);
            }
        }

        this.keys = temp.keys;
        this.values = temp.values;
        this.capacity = newCapacity;
    }

    private int findNextPrime(int n) {
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    @Override
    public void put(K key, V value) {
        if (size >= capacity * LOAD_FACTOR_THRESHOLD) {
            resize(findNextPrime(2 * capacity));
        }

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % capacity) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }

        keys[i] = key;
        values[i] = value;
        size++;
    }

    @Override
    public boolean contains(K key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % capacity) {
            if (keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(K key) {
        if (!contains(key)) return;

        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % capacity;
        }

        keys[i] = null;
        values[i] = null;
        size--;

        i = (i + 1) % capacity;
        while (keys[i] != null) {
            K keyToRehash = keys[i];
            V valueToRehash = values[i];
            keys[i] = null;
            values[i] = null;
            size--;
            put(keyToRehash, valueToRehash);
            i = (i + 1) % capacity;
        }

        if (size > 0 && size <= capacity / 8) {
            resize(findNextPrime(capacity / 2));
        }
    }
}
