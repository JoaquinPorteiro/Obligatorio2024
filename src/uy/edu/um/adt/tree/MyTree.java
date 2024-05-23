package uy.edu.um.adt.tree;

import uy.edu.um.adt.linkedlist.MyList;

public interface MyTree<K, T> {
    T find(K key);
    void insert(K key, T data, K parentKey);
    void delete(K key);
    int size();
    int countLeaf();
    int countCompleteElements();
    MyList<K> inOrder();
    MyList<K> preOrder();
    MyList<K> postOrder();
    MyList<K> levelOrder();
    void loadPostFijaExpression(String sPostFija);
}
