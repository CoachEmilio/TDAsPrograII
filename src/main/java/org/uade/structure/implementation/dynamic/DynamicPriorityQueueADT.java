package org.uade.structure.implementation.dynamic;

import org.uade.exception.EmptyADTException;
import org.uade.structure.definition.PriorityQueueADT;

// Esta clase representa la implementacion dinamica del TDA Cola con Prioridad.
public class DynamicPriorityQueueADT implements PriorityQueueADT {

    private static class Node {
        int value;
        int priority;
        Node next;

        Node(int value, int priority) {
            this.value = value;
            this.priority = priority;
        }
    }

    private Node front = null;

    @Override
    public int getElement() {
        if (isEmpty()) {
            throw new EmptyADTException("La cola está vacía");
        }
        return front.value;
    }

    @Override
    public int getPriority() {
        if (isEmpty()) {
            throw new EmptyADTException("La cola está vacía");
        }
        return front.priority;
    }

    @Override
    public void add(int value, int priority) {
        if (priority <= 0) {
            throw new IllegalArgumentException("La prioridad debe ser un valor positivo");
        }
        Node newNode = new Node(value, priority);
        if (front == null || priority > front.priority) {
            newNode.next = front;
            front = newNode;
        } else {
            Node current = front;
            while (current.next != null && current.next.priority >= priority) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    @Override
    public void remove() {
        if (isEmpty()) {
            throw new EmptyADTException("La cola está vacía");
        }
        front = front.next;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }
}