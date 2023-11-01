package org.example;

import java.util.*;

public class Graph<T> {
    private final List<Edge<T>> edgeList;
    private final List<HeaderNode<T>> nodeList;

    public Graph() {
        nodeList = new LinkedList<>();
        edgeList = new LinkedList<>();
    }

    public Graph(Graph<T> graph) {
        nodeList = new LinkedList<>(graph.getNodeList());
        edgeList = new LinkedList<>(graph.getEdgeList());
    }

    public List<HeaderNode<T>> getNodeList() {
        return new LinkedList<>(nodeList);
    }

    public List<Edge<T>> getEdgeList() {
        return new LinkedList<>(edgeList);
    }

    public void addNode(HeaderNode<T> node) {
        if (node == null) return;
        nodeList.add(node);
    }

    public void addEdge(Edge<T> edge) {
        if (edge.getStartNode() == null || edge.getFinishNode() == null || !nodeList.contains(edge.getStartNode()) ||
                !nodeList.contains(edge.getFinishNode())) return;

        this.edgeList.add(edge);
        edge.getStartNode().addToTrail(edge.getFinishNode());
        edge.getFinishNode().setCount(edge.getFinishNode().getCount() + 1);
    }

    public boolean isEmpty() {
        return this.nodeList.isEmpty();
    }

    public void clear() {
        this.nodeList.clear();
    }

    public List<HeaderNode<T>> getNodesByInfo(T info) {
        List<HeaderNode<T>> result = new LinkedList<>();

        for (HeaderNode<T> node : nodeList) {
            if (node.getInfo().equals(info)) result.add(node);
        }

        return result;
    }

    public boolean isNodeInGraph(T info) {
        return !getNodesByInfo(info).isEmpty();
    }

    public boolean isEdgeInGraph(Edge<T> edge) {
        if (edge == null) return false;

        return edgeList.contains(edge);
    }

    public int getNodesCount() {
        return this.nodeList.size();
    }

    public int getEdgesCount() {
        return edgeList.size();
    }

    public int getNodeDegree(HeaderNode<T> node) {
        if (node == null) return -1;

        return node.getTrail().size() + node.getCount();
    }

    public void deleteNode(HeaderNode<T> node) {
        if (node == null) return;
        List<Edge<T>> newEdgeList = new LinkedList<>(edgeList);

        for (Edge<T> edge : newEdgeList) {
            if (edge.getFinishNode().equals(node)) {
                this.edgeList.remove(edge);
            } else if (edge.getStartNode().equals(node)) {
                this.edgeList.remove(edge);
                edge.getFinishNode().setCount(edge.getFinishNode().getCount() - 1);
            }
        }

        nodeList.remove(node);
    }

    public void deleteEdge(Edge<T> edge) {
        if (edge == null) return;

        this.edgeList.remove(edge);
        edge.getFinishNode().setCount(edge.getFinishNode().getCount() - 1);
        edge.getStartNode().getTrail().remove(edge.getFinishNode());
    }

    public List<Edge<T>> getIncidentalEdges(HeaderNode<T> node) {
        List<Edge<T>> edges = new LinkedList<>();
        if (node == null) return edges;

        for (Edge<T> edge : this.edgeList) {
            if (edge.getStartNode().equals(node)) {
                edges.add(edge);
            }
        }

        return edges;
    }

    public ListIterator<HeaderNode<T>> getNodeIterator() {
        return nodeList.listIterator();
    }

    public ListIterator<Edge<T>> getEdgeIterator() {
        return getEdgeList().listIterator();
    }

    public ListIterator<Edge<T>> getIncidentalEdgesIterator(HeaderNode<T> node) {
        if (node == null) return null;

        return getIncidentalEdges(node).listIterator();
    }

    public ListIterator<HeaderNode<T>> getAdjacentNodesIterator(HeaderNode<T> node) {
        if (node == null) return null;

        return node.getTrail().listIterator();
    }
}
