package uy.edu.um.adt.linkedlist;

public class MySortedLinkedListImpl<T extends Comparable<T>> extends MyLinkedListImpl<T> {

    public void addSorted(T value) {
        if (value == null) {
            return; // Ignorar valores nulos
        }

        Node<T> newNode = new Node<>(value);

        if (this.first == null) { // Lista vacía
            this.first = newNode;
            this.last = newNode;
        } else if (this.first.getValue().compareTo(value) >= 0) { // Insertar al inicio
            newNode.setNext(this.first);
            this.first = newNode;
        } else { // Insertar en el medio o al final
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
