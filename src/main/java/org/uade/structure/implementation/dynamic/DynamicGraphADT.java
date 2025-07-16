package org.uade.structure.implementation.dynamic;

import org.uade.structure.exception.ElementNotFoundADTException;
import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.node.EdgeNode;
import org.uade.structure.implementation.node.GraphNode;

public class DynamicGraphADT implements GraphADT {
    private GraphNode firstNode;
    private DynamicSetADT vertxsSet;

    public DynamicGraphADT() {
        this.firstNode = null;
        this.vertxsSet = new DynamicSetADT();
    }
    /**
     * Descripcion: Retorna el conjunto de vertices. Precondición: No tiene.
     */
    @Override
    public SetADT getVertxs() {
        DynamicSetADT vertxsCopy = new DynamicSetADT(), aux = new DynamicSetADT();
        int element;
        while (!this.vertxsSet.isEmpty()) {
            element = vertxsSet.choose();
            vertxsCopy.add(element);
            aux.add(element);
            vertxsSet.remove(element);
        }

        while (!aux.isEmpty()) {
            element = aux.choose();
            vertxsSet.add(element);
            aux.remove(element);
        }

        return vertxsCopy;
    }

    /**
     * Descripcion: Agrega un nuevo vertice al grafo. Precondición: No tiene.
     *
     * @param vertex
     */
    @Override
    public void addVertx(int vertex) {
        if (vertxsSet.exist(vertex)) return;

        if (firstNode == null) {
            firstNode = new GraphNode(vertex);

        } else {
            GraphNode currentNode = firstNode;
            while (currentNode.getNextNode() != null) currentNode = currentNode.getNextNode();
            currentNode.setNextNode(new GraphNode(vertex));
        }
        vertxsSet.add(vertex);
    }

    /**
     * Descripcion: Eliminar un vertice del grafo. Precondición: No tiene.
     *
     * @param vertex
     */
    @Override
    public void removeVertx(int vertex) {
        GraphNode previousNode = null;
        GraphNode currentNode = firstNode;
        while (currentNode != null) {
            if (existsEdge(currentNode.getValue(), vertex)) removeEdge(currentNode.getValue(), vertex);
            currentNode = currentNode.getNextNode();
        }

        currentNode = firstNode;

        while (currentNode != null && currentNode.getValue() != vertex) {
            previousNode = currentNode;
            currentNode = currentNode.getNextNode();
        }

        if (currentNode == null) return;

        if (previousNode == null) firstNode = currentNode.getNextNode();
        else previousNode.setNextNode(currentNode.getNextNode());
        vertxsSet.remove(vertex);


    }

    /**
     * Descripcion: Agrega una nueva arista al grafo. Precondición: No tiene.
     *
     * @param vertxOne
     * @param vertxTwo
     * @param weight
     */
    @Override
    public void addEdge(int vertxOne, int vertxTwo, int weight) {
        if (!vertxsSet.exist(vertxOne) || !vertxsSet.exist(vertxTwo))
            throw new ElementNotFoundADTException("The given vertex does not exist");
        if (!existsEdge(vertxOne, vertxTwo)) {
            GraphNode origin = findVertex(vertxOne);
            GraphNode destination = findVertex(vertxTwo);

            EdgeNode newEdge = new EdgeNode(weight, destination);
            EdgeNode currentEdge = origin.getEdge();
            if (currentEdge == null) origin.setEdge(newEdge);
            else {
                while (currentEdge.getNextEdge() != null) currentEdge = currentEdge.getNextEdge();
                currentEdge.setNextEdge(newEdge);
            }
        }
    }

    /**
     * Descripcion: Eliminar una arista del grafo. Precondición: No tiene.
     *
     * @param vertxOne
     * @param vertxTwo
     */
    @Override
    public void removeEdge(int vertxOne, int vertxTwo) {
        EdgeNode edgeToRemove = findEdge(vertxOne, vertxTwo);
        if (!vertxsSet.exist(vertxOne) || !vertxsSet.exist(vertxTwo))
            throw new ElementNotFoundADTException("The given vertex does not exist");
        if (edgeToRemove == null) throw new ElementNotFoundADTException("The given edge does not exist");

        GraphNode origin = findVertex(vertxOne);
        EdgeNode currentEdge = origin.getEdge();
        if (currentEdge.equals(edgeToRemove)) {
            origin.setEdge(currentEdge.getNextEdge());
            return;
        }

        while (currentEdge.getNextEdge() != null && !currentEdge.getNextEdge().equals(edgeToRemove)) {
            currentEdge = currentEdge.getNextEdge();
        }
        if (currentEdge.getNextEdge() != null) {
            currentEdge.setNextEdge(currentEdge.getNextEdge().getNextEdge());
        }
    }

    /**
     * Descripcion: Comprueba si existe o no una arista en el grafo. Precondición: Debe tener elementos el grafo.
     *
     * @param vertxOne
     * @param vertxTwo
     */
    @Override
    public boolean existsEdge(int vertxOne, int vertxTwo) {
        return findEdge(vertxOne, vertxTwo) != null;
    }

    /**
     * Descripcion: Devuelve el peso de la arista entre dos vertices. Precondición: Debe tener elementos el grafo.
     *
     * @param vertxOne
     * @param vertxTwo
     */
    @Override
    public int edgeWeight(int vertxOne, int vertxTwo) {
        EdgeNode edgeToFind = findEdge(vertxOne, vertxTwo);
        if (edgeToFind == null) throw new ElementNotFoundADTException("The given vertex does not exist");
        return edgeToFind.getWeight();
    }

    /**
     * Descripcion: Debe comprobar si la estructura tiene o no valores. Precondición: No tiene.
     */
    @Override
    public boolean isEmpty() {
        return vertxsSet.isEmpty();
    }

    private GraphNode findVertex(int vertex) {
        GraphNode current = firstNode;
        while (current != null && current.getValue() != vertex) current = current.getNextNode();
        return current;
    }

    private EdgeNode findEdge(int vertexOne, int vertexTwo) {
        GraphNode origin = findVertex(vertexOne);

        if (origin == null) return null;
        EdgeNode currentEdge = origin.getEdge();

        while (currentEdge != null && currentEdge.getDestinyNode().getValue() != vertexTwo)
            currentEdge = currentEdge.getNextEdge();
        return currentEdge;
    }
}