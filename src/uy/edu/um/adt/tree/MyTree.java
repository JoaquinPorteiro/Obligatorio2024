package uy.edu.um.adt.tree;

public interface MyTree<K, T> {
    T find(K key);
    void insert(K key, T data, K parentKey);
    void delete(K key);
    int size();
    int countLeaf();
    int countCompleteElements();
    // List<K> inOrder();
    // List<K> preOrder();
    // List<K> postOrder();
}
