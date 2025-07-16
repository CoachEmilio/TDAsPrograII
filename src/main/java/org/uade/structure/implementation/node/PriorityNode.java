package org.uade.structure.implementation.node;

public class PriorityNode {
    protected int value;
    protected PriorityNode next;
    protected int priority;

    public PriorityNode(int x, int priority){
        this.value = x;
        this.next = null;
        this.priority = priority;
    }

    public int getPriority(){
        return this.priority;
    }

    public int getValue(){
        return this.value;
    }

    public PriorityNode getNext(){
        return this.next;
    }

    public void setNext(PriorityNode next){
        this.next = next;
    }

    public void setValue(int value){
        this.value = value;
    }

    public void setPriority(int priority){
        this.priority = priority;
    }
}
