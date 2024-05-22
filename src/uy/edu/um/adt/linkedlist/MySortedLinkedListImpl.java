package uy.edu.um.adt.linkedlist;

public class MySortedLinkedListImpl<T extends Comparable<T>> extends MyLinkedListImpl<T> {

    public void addSorted(T value) {
        if (value == null) {
            return;
        }

        Node<T> newNode = new Node<>(value);

        if (this.first == null) {
            this.first = newNode;
            this.last = newNode;
        } else if (this.first.getValue().compareTo(value) >= 0) {
            newNode.setNext(this.first);
            this.first = newNode;
        } else {
            Node<T> current = this.first;
            while (current.getNext() != null && current.getNext().getValue().compareTo(value) < 0) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
            if (newNode.getNext() == null) {
                this.last = newNode;
            }
        }
    }
}
