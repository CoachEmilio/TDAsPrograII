package org.uade.structure.implementation.fixed;

import org.uade.exception.ElementNotFoundADTException;
import org.uade.exception.EmptyADTException;
import org.uade.exception.UnsupportedOperationADTException;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;

public class StaticSimpleDictionaryADT implements SimpleDictionaryADT {

    private static final int CAPACITY = 100;
    private final int[] keys;
    private final int[] values;
    private int size;

    public StaticSimpleDictionaryADT() {
        this.keys = new int[CAPACITY];
        this.values = new int[CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(int key, int value) {
        for (int i = 0; i < size; i++) {
            if (keys[i] == key) {
                values[i] = value;
                return;
            }
        }
        if (size >= CAPACITY) {
            throw new UnsupportedOperationADTException("Capacidad máxima alcanzada");
        }
        keys[size] = key;
        values[size] = value;
        size++;
    }

    @Override
    public void remove(int key) {
        for (int i = 0; i < size; i++) {
            if (keys[i] == key) {
                for (int j = i; j < size - 1; j++) {
                    keys[j] = keys[j + 1];
                    values[j] = values[j + 1];
                }
                size--;
                return;
            }
        }
        throw new ElementNotFoundADTException("Clave no encontrada");
    }

    @Override
    public int get(int key) {
        for (int i = 0; i < size; i++) {
            if (keys[i] == key) {
                return values[i];
            }
        }
        throw new ElementNotFoundADTException("Clave no encontrada");
    }

    @Override
    public SetADT getKeys() {
        return new SetADT() {
            @Override
            public boolean exist(int value) {
                for (int i = 0; i < size; i++) {
                    if (keys[i] == value) return true;
                }
                return false;
            }

            @Override
            public int choose() {
                if (size == 0) throw new EmptyADTException("El conjunto está vacío");
                int idx = (int) (Math.random() * size);
                return keys[idx];
            }

            @Override
            public void add(int value) {
                throw new UnsupportedOperationADTException("Operación no soportada en este TDA diccionario");
            }

            @Override
            public void remove(int element) {
                throw new UnsupportedOperationADTException("Operación no soportada en este TDA diccionario");
            }

            @Override
            public boolean isEmpty() {
                return size == 0;
            }
        };
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}