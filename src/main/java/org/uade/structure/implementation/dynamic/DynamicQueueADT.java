package org.uade.structure.implementation.dynamic;

import org.uade.exception.EmptyADTException;
import org.uade.structure.definition.QueueADT;

// Esta clase representa la implementacion dinamica del TDA Cola.
public class DynamicQueueADT implements QueueADT {

    private static class Node {
        int value;
        Node next;
        Node(int value) {
            this.value = value;
        }
    }

    private Node front = null;
    private Node rear = null;

    @Override
    public int getElement() {
        if (isEmpty()) {
            throw new EmptyADTException("La cola está vacía");
        }
        return front.value;
    }

    @Override
    public void add(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    @Override
    public void remove() {
        if (isEmpty()) {
            throw new EmptyADTException("La cola está vacía");
        }
        front = front.next;
        if (front == null) {
            rear = null;
        }
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }
}