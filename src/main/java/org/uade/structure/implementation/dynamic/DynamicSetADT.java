package org.uade.structure.implementation.dynamic;

import org.uade.exception.NoSuchElementADTException;
import org.uade.structure.definition.SetADT;

// Esta clase representa la implementación dinámica del TDA Conjunto.
public class DynamicSetADT implements SetADT {

    private static class Node {
        private int value;
        private Node next;

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node head;
    private int count;

    public DynamicSetADT() {
        head = null;
        count = 0;
    }

    @Override
    public boolean exist(int value) {
        Node current = head;
        while (current != null) {
            if (current.value == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int choose() {
        if (isEmpty()) {
            throw new NoSuchElementADTException("El conjunto está vacío");
        }
        return head.value;
    }

    @Override
    public void add(int value) {
        if (!exist(value)) {
            head = new Node(value, head);
            count++;
        }
    }

    @Override
    public void remove(int element) {
        Node current = head;
        Node prev = null;
        while (current != null) {
            if (current.value == element) {
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                count--;
                break;
            }
            prev = current;
            current = current.next;
        }
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }
}
