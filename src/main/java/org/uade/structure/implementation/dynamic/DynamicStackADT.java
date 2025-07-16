package org.uade.structure.implementation.dynamic;

import org.uade.exception.EmptyADTException;
import org.uade.structure.definition.StackADT;

public class DynamicStackADT implements StackADT {

    private static class Node {
        int value;
        Node next;
        Node(int value) {
            this.value = value;
        }
    }

    private Node top = null;

    @Override
    public int getElement() {
        if (isEmpty()) {
            throw new EmptyADTException("La pila está vacía");
        }
        return top.value;
    }

    @Override
    public void add(int value) {
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
    }

    @Override
    public void remove() {
        if (isEmpty()) {
            throw new EmptyADTException("La pila está vacía");
        }
        top = top.next;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }
}