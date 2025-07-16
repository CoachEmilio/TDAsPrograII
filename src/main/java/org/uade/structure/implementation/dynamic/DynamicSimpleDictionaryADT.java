package org.uade.structure.implementation.dynamic;

import org.uade.structure.exception.ElementNotFoundADTException;
import org.uade.structure.exception.EmptyADTException;
import org.uade.structure.exception.UnsupportedOperationADTException;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;


public class DynamicSimpleDictionaryADT implements SimpleDictionaryADT {

    private static class Node {
        int key;
        int value;
        Node next;

        Node(int key, int value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node head;

    public DynamicSimpleDictionaryADT() {
        this.head = null;
    }

    @Override
    public void add(int key, int value) {
        Node current = head;
        while (current != null) {
            if (current.key == key) {
                current.value = value;
                return;
            }
            current = current.next;
        }
        head = new Node(key, value, head);
    }

    @Override
    public void remove(int key) {
        Node current = head;
        Node prev = null;
        while (current != null) {
            if (current.key == key) {
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    @Override
    public int get(int key) {
        Node current = head;
        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }
        throw new ElementNotFoundADTException ("Clave no encontrada");
    }

    @Override
    public SetADT getKeys() {
        return new SetADT() {
            @Override
            public boolean exist(int value) {
                Node current = head;
                while (current != null) {
                    if (current.key == value) return true;
                    current = current.next;
                }
                return false;
            }

            @Override
            public int choose() {
                if (head == null) throw new EmptyADTException("El conjunto está vacío");
                int size = 0;
                Node current = head;
                while (current != null) {
                    size++;
                    current = current.next;
                }
                int idx = (int) (Math.random() * size);
                current = head;
                for (int i = 0; i < idx; i++) {
                    current = current.next;
                }
                return current.key;
            }

            @Override
            public void add(int value) {
                // No soportado en este contexto
                throw new UnsupportedOperationADTException("Operación no soportada en este TDA diccionario");
            }

            @Override
            public void remove(int element) {
                // No soportado en este contexto
                throw new UnsupportedOperationADTException("Operación no soportada en este TDA diccionario");
            }

            @Override
            public boolean isEmpty() {
                return head == null;
            }
        };
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }
}