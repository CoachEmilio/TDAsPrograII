package org.uade.structure.implementation.node;

import org.uade.structure.exception.OutOfRangeADTException;
import org.uade.structure.definition.extras.PriorityNodeLinkedListADT;

public class PNodeDLinkedList implements PriorityNodeLinkedListADT {

    private class Node {
        private Node next;
        private PriorityNode value;

        Node(PriorityNode value) {
            this.value = value;
        }
        public Node getNext() {
            return next;
        }
        public void addNext(Node nextNode) {
            next = nextNode;
        }

        public PriorityNode getValue() {
            return value;
        }
    }

    protected Node head;
    protected int size;


    @Override
    public void add(PriorityNode value) {
        if (this.head == null) {
            this.head = new Node(value);
        }else {
            Node current = this.head;
            while (current.getNext()!=null){
                current = current.getNext();
            }
            current.addNext(new Node(value));
        }
        this.size++;
    }

    /**
     * Descripcion: Agrega el elemento a un indice.
     * Precondición: No tiene.
     *
     * @param index
     * @param value
     */
    @Override
    public void insert(int index, PriorityNode value) throws IndexOutOfBoundsException {

        if (index < 0 || index > size){
            throw new OutOfRangeADTException("Index out of range");
        }

        Node newNode = new Node(value);

        if(index == 0){
            newNode.addNext(this.head);
            this.head = newNode;
        }

        else {
            Node current = this.head;
            for (int i=0;i<index-1;i++){
                current = current.getNext();
            }
            newNode.addNext(current.getNext());
            current.addNext(newNode);
        }
        this.size++;
    }

    /**
     * Descripcion: Elimina el elemento de un indice.
     * Precondición: El indice debe existir.
     *
     * @param index
     */
    @Override
    public void remove(int index) {
        if (index < 0 || index > size){
            throw new OutOfRangeADTException("Index out of range");
        }

        if (index == 0){
            this.head = this.head.getNext();
        }
        else {
            Node current = this.head;
            for (int i=0;i<index-1;i++){
                current = current.getNext();
            }
            current.addNext(current.getNext().getNext());
        }
        this.size--;
    }

    /**
     * Descripcion: Retorna el elemento de un indice.
     * Precondición: El indice debe existir.
     *
     * @param index
     */
    @Override
    public PriorityNode get(int index) {
        if (index < 0 || index > size){
            throw new OutOfRangeADTException("Index out of range");
        }

        Node current = this.head;
        for (int i=0;i<index;i++){
            current = current.getNext();
        }
        return current.getValue();
    }

    /**
     * Descripcion: Retorna el tamaño de la lista.
     * Precondición: No tiene.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Descripcion: Debe comprobar si la estructura tiene o no valores.
     * Precondición: No tiene.
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}