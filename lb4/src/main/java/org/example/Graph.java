package org.example;

import java.util.*;

public class Graph<T> implements Iterable<HeaderNode<T>>{
    private final List<Edge<T>> edgeList;
    private final List<HeaderNode<T>> nodeList;

    public Graph() {
        this(new LinkedList<>(), new LinkedList<>());
    }

    public Graph(Graph<T> graph) {
        this(graph.nodeList, graph.edgeList);
    }

    private Graph(List<HeaderNode<T>> nodeList, List<Edge<T>> edgeList) {
        this.nodeList = nodeList;
        this.edgeList = edgeList;
    }

    public List<Edge<T>> getEdgeList() {
        return new LinkedList<>(edgeList);
    }

    public void addNode(HeaderNode<T> node) throws NullNodeException {
        if (node == null) throw new NullNodeException();
        if (this.nodeList.contains(node)) return;
        nodeList.add(node);
    }

    public void addEdge(Edge<T> edge) throws NullEdgeException {
        if (edge == null) throw new NullEdgeException();

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
        this.edgeList.clear();
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

    public boolean isEdgeInGraph(Edge<T> edge) throws NullEdgeException {
        if (edge == null) throw new NullEdgeException();

        return edgeList.contains(edge);
    }

    public int getNodesCount() {
        return this.nodeList.size();
    }

    public int getEdgesCount() {
        return edgeList.size();
    }

    public int getNodeDegree(HeaderNode<T> node) throws NullNodeException {
        if (node == null) throw new NullNodeException();
        if (!this.nodeList.contains(node)) return -1;

        return node.getTrail().size() + node.getCount();
    }

    public void deleteNode(HeaderNode<T> node) throws NullNodeException {
        if (node == null) throw new NullNodeException();
        List<Edge<T>> newEdgeList = new LinkedList<>(edgeList);

        for (Edge<T> edge : newEdgeList) {
            if (edge.getFinishNode().equals(node)) {
                this.edgeList.remove(edge);
            } else if (edge.getStartNode().equals(node)) {
                this.edgeList.remove(edge);
                edge.getFinishNode().setCount(edge.getFinishNode().getCount() - 1);
            }
        }
        node.setCount(0);
        node.clearTrail();

        nodeList.remove(node);
    }

    public void deleteEdge(Edge<T> edge) throws NullEdgeException {
        if (edge == null) throw new NullEdgeException();
        if (!this.edgeList.contains(edge)) return;

        this.edgeList.remove(edge);
        edge.getFinishNode().setCount(edge.getFinishNode().getCount() - 1);
        edge.getStartNode().getTrail().remove(edge.getFinishNode());
    }

    public List<Edge<T>> getIncidentalEdges(HeaderNode<T> node) throws NullNodeException {
        if (node == null) throw new NullNodeException();
        List<Edge<T>> edges = new LinkedList<>();

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
        return edgeList.listIterator();
    }

    public ListIterator<Edge<T>> getIncidentalEdgesIterator(HeaderNode<T> node) throws NullNodeException {
        if (node == null) throw new NullNodeException();

        return getIncidentalEdges(node).listIterator();
    }

    public ListIterator<HeaderNode<T>> getAdjacentNodesIterator(HeaderNode<T> node) throws NullNodeException {
        if (node == null) throw new NullNodeException();

        return node.getTrail().listIterator();
    }

    public ConstantIterator<HeaderNode<T>> getConstantNodeIterator() {
        return new ConstantIterator<>(nodeList);
    }

    public ConstantIterator<Edge<T>> getConstantEdgeIterator() {
        return new ConstantIterator<>(edgeList);
    }

    public ConstantIterator<Edge<T>> getConstantIncidentalEdgesIterator(HeaderNode<T> node) throws NullNodeException {
        if (node == null) throw new NullNodeException();

        return new ConstantIterator<>(getIncidentalEdges(node));
    }

    public ConstantIterator<HeaderNode<T>> getConstantAdjacentNodesIterator(HeaderNode<T> node) throws NullNodeException {
        if (node == null) throw new NullNodeException();

        return new ConstantIterator<>(node.getTrail());
    }

    @Override
    public Iterator<HeaderNode<T>> iterator() {
        return nodeList.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graph<?> graph = (Graph<?>) o;
        return Objects.equals(new HashSet<>(edgeList), new HashSet<>(graph.edgeList)) && Objects.equals(new HashSet<>(nodeList), new HashSet<>(graph.nodeList));
    }

    @Override
    public int hashCode() {
        return Objects.hash(new HashSet<>(edgeList), new HashSet<>(nodeList));
    }
}
