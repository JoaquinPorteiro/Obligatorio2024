package uy.edu.um.adt.tree;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.linkedlist.MyList;
import uy.edu.um.adt.queue.EmptyQueueException;
import uy.edu.um.adt.queue.MyQueue;
import uy.edu.um.adt.stack.EmptyStackException;
import uy.edu.um.adt.stack.MyStack;

public class Tree<K extends Comparable<K>, T> implements MyTree<K, T> {
    private Node<K, T> root;

    public Tree() {
        this.root = null;
    }

    @Override
    public T find(K key) {
        return findRecursive(root, key);
    }

    private T findRecursive(Node<K, T> current, K key) {
        if (current == null) {
            return null;
        }
        if (key.compareTo(current.key) == 0) {
            return current.data;
        }
        return key.compareTo(current.key) < 0
                ? findRecursive(current.leftChild, key)
                : findRecursive(current.rightChild, key);
    }

    @Override
    public void insert(K key, T data, K parentKey) {
        if (root == null) {
            root = new Node<>(key, data);
        } else {
            insertRecursive(root, key, data);
        }
    }

    private void insertRecursive(Node<K, T> current, K key, T data) {
        if (key.compareTo(current.key) < 0) {
            if (current.leftChild == null) {
                current.leftChild = new Node<>(key, data);
            } else {
                insertRecursive(current.leftChild, key, data);
            }
        } else if (key.compareTo(current.key) > 0) {
            if (current.rightChild == null) {
                current.rightChild = new Node<>(key, data);
            } else {
                insertRecursive(current.rightChild, key, data);
            }
        } else {
            current.data = data; // actualiza el valor si la clave ya existe
        }
    }

    @Override
    public void delete(K key) {
        root = deleteRecursive(root, key);
    }

    private Node<K, T> deleteRecursive(Node<K, T> current, K key) {
        if (current == null) {
            return null;
        }

        if (key.compareTo(current.key) == 0) {
            // Nodo a eliminar encontrado

            // Nodo sin hijos
            if (current.leftChild == null && current.rightChild == null) {
                return null;
            }

            // Nodo con un solo hijo
            if (current.leftChild == null) {
                return current.rightChild;
            }
            if (current.rightChild == null) {
                return current.leftChild;
            }

            // Nodo con dos hijos
            K smallestKey = findSmallestKey(current.rightChild);
            current.key = smallestKey;
            current.data = findRecursive(root, smallestKey);
            current.rightChild = deleteRecursive(current.rightChild, smallestKey);
            return current;

        }
        if (key.compareTo(current.key) < 0) {
            current.leftChild = deleteRecursive(current.leftChild, key);
            return current;
        }

        current.rightChild = deleteRecursive(current.rightChild, key);
        return current;
    }

    private K findSmallestKey(Node<K, T> root) {
        return root.leftChild == null ? root.key : findSmallestKey(root.leftChild);
    }

    @Override
    public int size() {
        return sizeRecursive(root);
    }

    private int sizeRecursive(Node<K, T> current) {
        if (current == null) {
            return 0;
        }
        return 1 + sizeRecursive(current.leftChild) + sizeRecursive(current.rightChild);
    }

    @Override
    public int countLeaf() {
        return countLeafRecursive(root);
    }

    private int countLeafRecursive(Node<K, T> current) {
        if (current == null) {
            return 0;
        }
        if (current.leftChild == null && current.rightChild == null) {
            return 1;
        }
        return countLeafRecursive(current.leftChild) + countLeafRecursive(current.rightChild);
    }

    @Override
    public int countCompleteElements() {
        return countCompleteElementsRecursive(root);
    }

    private int countCompleteElementsRecursive(Node<K, T> current) {
        if (current == null) {
            return 0;
        }
        int count = (current.leftChild != null && current.rightChild != null) ? 1 : 0;
        return count + countCompleteElementsRecursive(current.leftChild) + countCompleteElementsRecursive(current.rightChild);
    }

    @Override
    public MyList<K> inOrder() {
        MyList<K> result = new MyLinkedListImpl<>();
        inOrderRecursive(root, result);
        return result;
    }

    private void inOrderRecursive(Node<K, T> current, MyList<K> result) {
        if (current != null) {
            inOrderRecursive(current.leftChild, result);
            result.add(current.key);
            inOrderRecursive(current.rightChild, result);
        }
    }

    @Override
    public MyList<K> preOrder() {
        MyList<K> result = new MyLinkedListImpl<>();
        preOrderRecursive(root, result);
        return result;
    }

    private void preOrderRecursive(Node<K, T> current, MyList<K> result) {
        if (current != null) {
            result.add(current.key);
            preOrderRecursive(current.leftChild, result);
            preOrderRecursive(current.rightChild, result);
        }
    }

    @Override
    public MyList<K> postOrder() {
        MyList<K> result = new MyLinkedListImpl<>();
        postOrderRecursive(root, result);
        return result;
    }

    private void postOrderRecursive(Node<K, T> current, MyList<K> result) {
        if (current != null) {
            postOrderRecursive(current.leftChild, result);
            postOrderRecursive(current.rightChild, result);
            result.add(current.key);
        }
    }

    @Override
    public MyList<K> levelOrder() {
        MyList<K> result = new MyLinkedListImpl<>();
        if (root == null) {
            return result;
        }

        MyQueue<Node<K, T>> queue = new MyLinkedListImpl<>();
        queue.enqueue(root);

        while (queue.size() > 0) {
            Node<K, T> current = null;
            try {
                current = queue.dequeue();
            } catch (EmptyQueueException e) {
                e.printStackTrace();
            }
            if (current != null) {
                result.add(current.key);
                if (current.leftChild != null) {
                    queue.enqueue(current.leftChild);
                }
                if (current.rightChild != null) {
                    queue.enqueue(current.rightChild);
                }
            }
        }
        return result;
    }
    @Override
    public void loadPostFijaExpression(String sPostFija) {
        MyStack<Node<String, String>> stack = new MyLinkedListImpl<>();

        for (String token : sPostFija.split(" ")) {
            if (isOperator(token)) {
                Node<String, String> right = null;
                Node<String, String> left = null;
                try {
                    right = stack.pop();
                    left = stack.pop();
                } catch (EmptyStackException e) {
                    e.printStackTrace();
                }
                Node<String, String> node = new Node<>(token, token);
                node.leftChild = left;
                node.rightChild = right;
                stack.push(node);
            } else {
                stack.push(new Node<>(token, token));
            }
        }

        try {
            root = stack.pop();
        } catch (EmptyStackException e) {
            e.printStackTrace();
        }
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }
}