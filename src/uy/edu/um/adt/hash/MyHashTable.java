package uy.edu.um.adt.hash;

public interface MyHashTable<K, V> {
    public void put(K key, V value);
    public boolean contains(K key);
    public void remove(K clave);
}