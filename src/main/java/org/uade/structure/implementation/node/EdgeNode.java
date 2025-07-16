package org.uade.structure.implementation.node;

import java.util.Objects;

public class EdgeNode {
    private int weight;
    private GraphNode destinyNode;
    private EdgeNode nextEdge;

    public EdgeNode(int weight, GraphNode destinyNode) {
        this.weight = weight;
        this.destinyNode = destinyNode;
        this.nextEdge = null;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setDestinyNode(GraphNode destinyNode) {
        this.destinyNode = destinyNode;
    }

    public void setNextEdge(EdgeNode nextEdge) {
        this.nextEdge = nextEdge;
    }

    public int getWeight() {
        return weight;
    }

    public GraphNode getDestinyNode() {
        return destinyNode;
    }

    public EdgeNode getNextEdge() {
        return nextEdge;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EdgeNode edgeNode = (EdgeNode) o;
        return weight == edgeNode.weight && Objects.equals(destinyNode, edgeNode.destinyNode) && Objects.equals(nextEdge, edgeNode.nextEdge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, destinyNode, nextEdge);
    }
}