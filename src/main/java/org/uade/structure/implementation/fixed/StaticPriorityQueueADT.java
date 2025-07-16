package org.uade.structure.implementation.fixed;

import org.uade.exception.EmptyADTException;
import org.uade.exception.FullADTException;
import org.uade.structure.definition.PriorityQueueADT;

public class StaticPriorityQueueADT implements PriorityQueueADT {

    private static class Element {
        int value;
        int priority;

        Element(int value, int priority) {
            this.value = value;
            this.priority = priority;
        }
    }

    private static final int CAPACITY = 100;
    private final Element[] queue = new Element[CAPACITY];
    private int size = 0;

    @Override
    public int getElement() {
        if (isEmpty()) {
            throw new EmptyADTException("La cola está vacía");
        }
        int idx = getHighestPriorityIndex();
        return queue[idx].value;
    }

    @Override
    public int getPriority() {
        if (isEmpty()) {
            throw new EmptyADTException("La cola está vacía");
        }
        int idx = getHighestPriorityIndex();
        return queue[idx].priority;
    }

    @Override
    public void add(int value, int priority) {
        if (priority <= 0) {
            throw new IllegalArgumentException("La prioridad debe ser un valor positivo");
        }
        if (size >= CAPACITY) {
            throw new FullADTException("La cola está llena");
        }
        queue[size++] = new Element(value, priority);
    }

    @Override
    public void remove() {
        if (isEmpty()) {
            throw new EmptyADTException("La cola está vacía");
        }
        int idx = getHighestPriorityIndex();
        // Mover el último elemento a la posición eliminada para mantener compacto
        queue[idx] = queue[size - 1];
        queue[size - 1] = null;
        size--;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getHighestPriorityIndex() {
        int idx = 0;
        for (int i = 1; i < size; i++) {
            if (queue[i].priority > queue[idx].priority) {
                idx = i;
            }
        }
        return idx;
    }
}