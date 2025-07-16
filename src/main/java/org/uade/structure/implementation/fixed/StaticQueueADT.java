package org.uade.structure.implementation.fixed;

import org.uade.exception.EmptyADTException;
import org.uade.exception.FullADTException;
import org.uade.structure.definition.QueueADT;

public class StaticQueueADT implements QueueADT {

    private static final int CAPACITY = 100;
    private int[] data = new int[CAPACITY];
    private int front = 0;
    private int rear = -1;
    private int size = 0;

    @Override
    public int getElement() {
        if (isEmpty()) {
            throw new EmptyADTException("La cola está vacía");
        }
        return data[front];
    }

    @Override
    public void add(int value) {
        if (size == CAPACITY) {
            throw new FullADTException("La cola está llena");
        }
        rear = (rear + 1) % CAPACITY;
        data[rear] = value;
        size++;
    }

    @Override
    public void remove() {
        if (isEmpty()) {
            throw new EmptyADTException("La cola está vacía");
        }
        front = (front + 1) % CAPACITY;
        size--;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
