package org.uade.structure.implementation.fixed;

import org.uade.exception.EmptyADTException;
import org.uade.exception.FullADTException;
import org.uade.structure.definition.StackADT;


// Esta clase representa la implementacion estatica del TDA Pila.
public class StaticStackADT implements StackADT {

    private static final int CAPACITY = 100;
    private int[] data = new int[CAPACITY];
    private int top = -1;

    @Override
    public int getElement() {
        if (isEmpty()) {
            throw new EmptyADTException("La pila está vacía");
        }
        return data[top];
    }

    @Override
    public void add(int value) {
        if (top + 1 >= CAPACITY) {
            throw new FullADTException("La pila está llena");
        }
        data[++top] = value;
    }

    @Override
    public void remove() {
        if (isEmpty()) {
            throw new EmptyADTException("La pila está vacía");
        }
        top--;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }
}