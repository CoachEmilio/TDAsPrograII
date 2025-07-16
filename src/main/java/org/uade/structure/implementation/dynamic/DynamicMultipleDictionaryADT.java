package org.uade.structure.implementation.dynamic;

import org.uade.structure.definition.MultipleDictionaryADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.fixed.StaticSetADT;

public class DynamicMultipleDictionaryADT implements MultipleDictionaryADT {

        private static class Node {
            int key;
            ListNode valuesHead;
            Node next;

            Node(int key) {
                this.key = key;
            }
        }

        private static class ListNode {
            int value;
            ListNode next;

            ListNode(int value) {
                this.value = value;
            }
        }

        private Node head;

    public DynamicMultipleDictionaryADT() {
        head = null;
    }

        @Override
        public void add ( int key, int value){
        Node node = findNode(key);
        if (node == null) {
            node = new Node(key);
            node.next = head;
            head = node;
        }
        ListNode newValue = new ListNode(value);
        newValue.next = node.valuesHead;
        node.valuesHead = newValue;
    }

        @Override
        public void remove ( int key){
        Node prev = null, curr = head;
        while (curr != null) {
            if (curr.key == key) {
                if (prev == null) {
                    head = curr.next;
                } else {
                    prev.next = curr.next;
                }
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }

        @Override
        public int[] get ( int key){
        Node node = findNode(key);
        if (node == null) {
            return new int[0];
        }
        int count = 0;
        ListNode temp = node.valuesHead;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        int[] result = new int[count];
        temp = node.valuesHead;
        for (int i = 0; i < count; i++) {
            result[i] = temp.value;
            temp = temp.next;
        }
        return result;
    }

        @Override
        public SetADT getKeys () {
        // Contar claves
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        int[] keys = new int[count];
        temp = head;
        for (int i = 0; i < count; i++) {
            keys[i] = temp.key;
            temp = temp.next;
        }
        return new StaticSetADT(keys, count);
    }

        @Override
        public boolean isEmpty () {
        return head == null;
    }

        @Override
        public void remove ( int key, int value){
        Node node = findNode(key);
        if (node == null) return;
        ListNode prev = null, curr = node.valuesHead;
        while (curr != null) {
            if (curr.value == value) {
                if (prev == null) {
                    node.valuesHead = curr.next;
                } else {
                    prev.next = curr.next;
                }
                break;
            }
            prev = curr;
            curr = curr.next;
        }
        // Si ya no quedan valores, eliminar la clave
        if (node.valuesHead == null) {
            remove(key);
        }
    }

        private Node findNode ( int key){
        Node curr = head;
        while (curr != null) {
            if (curr.key == key) return curr;
            curr = curr.next;
        }
        return null;
    }
}