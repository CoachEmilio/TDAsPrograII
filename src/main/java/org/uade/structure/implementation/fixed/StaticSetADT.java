package org.uade.structure.implementation.fixed;

import org.uade.structure.exception.EmptyADTException;
import org.uade.structure.definition.SetADT;

public class StaticSetADT implements SetADT {

    private static final int MAX_SIZE = 100;
    private int[] elements;
    private int count;

    public StaticSetADT() {
        elements = new int[MAX_SIZE];
        count = 0;
    }

    // Constructor usado por StaticMultipleDictionaryADT para crear un set a partir de un array y un tamaño
    public StaticSetADT(int[] source, int size) {
        elements = new int[MAX_SIZE];
        count = 0;
        for (int i = 0; i < size && count < MAX_SIZE; i++) {
            add(source[i]);
        }
    }

    @Override
    public boolean exist(int value) {
        for (int i = 0; i < count; i++) {
            if (elements[i] == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int choose() {
        if (isEmpty()) {
            throw new EmptyADTException("El conjunto está vacío");
        }
        return elements[0];
    }

    @Override
    public void add(int value) {
        if (!exist(value) && count < MAX_SIZE) {
            elements[count++] = value;
        }
    }

    @Override
    public void remove(int element) {
        for (int i = 0; i < count; i++) {
            if (elements[i] == element) {
                for (int j = i; j < count - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                count--;
                break;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }
}