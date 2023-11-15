package org.example.Models;

import java.util.Objects;

public class Edge<T> {
    private final HeaderNode<T> startNode;
    private final HeaderNode<T> finishNode;

    public Edge(HeaderNode<T> startNode, HeaderNode<T> finishNode) {
        this.startNode = startNode;
        this.finishNode = finishNode;
    }

    public HeaderNode<T> getStartNode() {
        return startNode;
    }

    public HeaderNode<T> getFinishNode() {
        return finishNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return Objects.equals(startNode, edge.startNode) && Objects.equals(finishNode, edge.finishNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startNode, finishNode);
    }
}
