package org.example.Models;

import org.example.Iterators.ConstantEdgeIterator;
import org.example.Iterators.ConstantNodeIterator;
import org.example.Iterators.DefaultEdgeIterator;
import org.example.Iterators.DefaultNodeIterator;
import org.example.Exceptions.NodeNotFoundException;
import org.example.Exceptions.NullEdgeException;
import org.example.Exceptions.NullNodeException;

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

    public void addNode(T info){
        if (this.nodeList.contains(new HeaderNode<>(info))) return;
        nodeList.add(new HeaderNode<>(info));
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

    public int getNodeDegree(HeaderNode<T> node) throws NullNodeException, NodeNotFoundException {
        if (node == null) throw new NullNodeException();
        if (!this.nodeList.contains(node)) throw new NodeNotFoundException();

        return node.getTrail().size() + node.getCount();
    }

    public void deleteNode(HeaderNode<T> node) throws NullNodeException {
        if (node == null) throw new NullNodeException();
        ListIterator<Edge<T>> edgeListIterator = edgeList.listIterator();

        while(edgeListIterator.hasNext()) {
            Edge<T> currentEdge = edgeListIterator.next();

            if (currentEdge.getFinishNode().equals(node)) {
                edgeListIterator.remove();
            } else if (currentEdge.getStartNode().equals(node)) {
                edgeListIterator.remove();
                currentEdge.getFinishNode().setCount(currentEdge.getFinishNode().getCount() - 1);
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

    public DefaultNodeIterator<T> getNodeIterator() {
        return new DefaultNodeIterator<>(nodeList);
    }

    public DefaultEdgeIterator<T> getEdgeIterator() {
        return new DefaultEdgeIterator<>(edgeList);
    }

    public DefaultEdgeIterator<T> getIncidentalEdgesIterator(HeaderNode<T> node) throws NullNodeException {
        if (node == null) throw new NullNodeException();

        return new DefaultEdgeIterator<>(getIncidentalEdges(node));
    }

    public DefaultNodeIterator<T> getAdjacentNodesIterator(HeaderNode<T> node) throws NullNodeException {
        if (node == null) throw new NullNodeException();

        return new DefaultNodeIterator<>(node.getTrail());
    }

    public ConstantNodeIterator<T> getConstantNodeIterator() {
        return new ConstantNodeIterator<>(nodeList);
    }

    public ConstantEdgeIterator<T> getConstantEdgeIterator() {
        return new ConstantEdgeIterator<>(edgeList);
    }

    public ConstantEdgeIterator<T> getConstantIncidentalEdgesIterator(HeaderNode<T> node) throws NullNodeException {
        if (node == null) throw new NullNodeException();

        return new ConstantEdgeIterator<>(getIncidentalEdges(node));
    }

    public ConstantNodeIterator<T> getConstantAdjacentNodesIterator(HeaderNode<T> node) throws NullNodeException {
        if (node == null) throw new NullNodeException();

        return new ConstantNodeIterator<>(node.getTrail());
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
