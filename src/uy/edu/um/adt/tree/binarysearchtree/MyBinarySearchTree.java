package uy.edu.um.adt.tree.binarysearchtree;

import uy.edu.um.adt.linkedlist.MyList;

public interface MyBinarySearchTree<K extends Comparable<K>, T> {
    T find(K key);
    void insert(K key, T data);
    void delete(K key);
    MyList<K> inOrder();
    MyList<K> preOrder();
    MyList<K> postOrder();
}

