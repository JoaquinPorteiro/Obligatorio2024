package uy.edu.um.adt.hash;

public interface MyHashTable<K, V> {
    void put(K key, V value);
    boolean contains(K key);
    void remove(K key);
}
