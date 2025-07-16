package org.uade.structure.implementation.dynamic.DynamicLlLinkedList;

import org.uade.structure.definition.LinkedListADT;

public class LlNode {
    private LinkedListADT value;
    private LlNode next;
    private LlNode prev;

    public LlNode(LinkedListADT value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }

    public LinkedListADT getValue() {
        return value;
    }

    public LlNode getNext() {
        return next;
    }

    public void setNext(LlNode next) {
        this.next = next;
    }

    public LlNode getPrev() {
        return prev;
    }

    public void setPrev(LlNode prev) {
        this.prev = prev;
    }
}