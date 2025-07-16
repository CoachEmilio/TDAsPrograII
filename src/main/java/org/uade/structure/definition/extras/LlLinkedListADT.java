package org.uade.structure.definition.extras;

import org.uade.structure.definition.LinkedListADT;

// Esta interfaz representa el TDA Lista enlazada.
public interface LlLinkedListADT {

    /**
     * Descripcion: Agrega el elemento a un indice.
     * Precondición: No tiene
     */
    void add(LinkedListADT value);

    /**
     * Descripcion: Agrega el elemento a un indice.
     * Precondición: No tiene.
     */
    void insert(int index, LinkedListADT value);

    /**
     * Descripcion: Elimina el elemento de un indice.
     * Precondición: El indice debe existir.
     */
    void remove(int index);

    /**
     * Descripcion: Retorna el elemento de un indice.
     * Precondición: El indice debe existir.
     */
    LinkedListADT get(int index);

    /**
     * Descripcion: Retorna el tamaño de la lista.
     * Precondición: No tiene.
     */
    int size();

    /**
     * Descripcion: Debe comprobar si la estructura tiene o no valores.
     * Precondición: No tiene.
     */
    boolean isEmpty();
}
