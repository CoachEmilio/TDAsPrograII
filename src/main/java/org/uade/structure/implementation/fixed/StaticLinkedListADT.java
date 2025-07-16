package org.uade.structure.implementation.fixed;
import org.uade.exception.FullADTException;
import org.uade.exception.IndexOutOfBoundsADTException;
import org.uade.structure.definition.LinkedListADT;

public class StaticLinkedListADT implements LinkedListADT {
    private static final int CAPACITY = 100;
    private int[] values = new int[CAPACITY];
    private int[] next = new int[CAPACITY];
    private int head = -1;
    private int free = 0;
    private int size = 0;

    public StaticLinkedListADT() {
        for (int i = 0; i < CAPACITY - 1; i++) {
            next[i] = i + 1;
        }
        next[CAPACITY - 1] = -1;
    }

    @Override
    public void add(int value) {
        if (free == -1) {
            throw new FullADTException ("La Lista estática está llena");
        }
        int newNode = free;
        free = next[free];
        values[newNode] = value;
        next[newNode] = -1;
        if (head == -1) {
            head = newNode;
        } else {
            int current = head;
            while (next[current] != -1) {
                current = next[current];
            }
            next[current] = newNode;
        }
        size++;
    }

    @Override
    public void insert(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsADTException("Índice esta fuera del rango solicitado de esta lista estática");
        }
        if (free == -1) {
            throw new FullADTException("La lista estática está completa" );
        }
        int newNode = free;
        free = next[free];
        values[newNode] = value;

        if (index == 0) {
            next[newNode] = head;
            head = newNode;
        } else {
            int prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = next[prev];
            }
            next[newNode] = next[prev];
            next[prev] = newNode;
        }
        size++;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsADTException("Índice fuera de rango de la lista estática");
        }
        int toRemove;
        if (index == 0) {
            toRemove = head;
            head = next[head];
        } else {
            int prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = next[prev];
            }
            toRemove = next[prev];
            next[prev] = next[toRemove];
        }
        // Liberar el nodo eliminado
        next[toRemove] = free;
        free = toRemove;
        size--;
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsADTException("Índice fuera de rango de la lista estática");
        }
        int current = head;
        for (int i = 0; i < index; i++) {
            current = next[current];
        }
        return values[current];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
