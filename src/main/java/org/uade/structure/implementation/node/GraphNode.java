package org.uade.structure.implementation.node;

public class GraphNode {
    private int value;
    private EdgeNode edge;
    private GraphNode nextNode;

    public GraphNode(int value) {
        this.value = value;
        this.edge = null;
        this.nextNode = null;
    }

    public void setNextNode(GraphNode nextNode) {
        this.nextNode = nextNode;
    }

    public void setEdge(EdgeNode edge) {
        this.edge = edge;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public EdgeNode getEdge() {
        return edge;
    }

    public GraphNode getNextNode() {
        return nextNode;
    }
}