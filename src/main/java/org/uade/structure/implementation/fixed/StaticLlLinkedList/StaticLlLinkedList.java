package org.uade.structure.implementation.fixed.StaticLlLinkedList;


import org.uade.exception.FullADTException;
import org.uade.exception.OutOfRangeADTException;
import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.definition.extras.LlLinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicLlLinkedList.LlNode;

public class StaticLlLinkedList implements LlLinkedListADT {

    private LlNode head=null;
    private int size=0;
    private int maxSize;


    public StaticLlLinkedList(){
        this.maxSize= 10;
    }
    public StaticLlLinkedList(int maxSize){
        this.maxSize = maxSize;
    }

    @Override
    public void add(LinkedListADT value) {
        if(size==maxSize) throw new FullADTException("this structure is full");

        if (this.head == null) {
            this.head = new LlNode(value);
        }else {
            LlNode current = this.head;
            while (current.getNext()!=null){
                current = current.getNext();
            }
            current.setNext(new LlNode(value));
        }
        this.size++;
    }

    @Override
    public void insert(int index, LinkedListADT value) {

        if (index < 0 || index > size){
            throw new OutOfRangeADTException("Index out of range");
        }

        LlNode newNode = new LlNode(value);

        if(index == 0){
            newNode.setNext(this.head);
            this.head = newNode;
        }

        else {
            LlNode current = this.head;
            for (int i=0;i<index-1;i++){
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
        this.size++;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index > size){
            throw new OutOfRangeADTException("Index out of range");
        }

        if (index == 0){
            this.head = this.head.getNext();
        }
        else {
            LlNode current = this.head;
            for (int i=0;i<index-1;i++){
                current = current.getNext();
            }
            current.setNext(current.getNext().getNext());
        }
        this.size--;
    }

    @Override
    public LinkedListADT get(int index) {
        if (index < 0 || index > size){
            throw new OutOfRangeADTException("Index out of range");
        }

        LlNode current = this.head;
        for (int i=0;i<index;i++){
            current = current.getNext();
        }
        return current.getValue();

    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}