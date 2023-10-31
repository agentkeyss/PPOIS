package org.example;

import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        graph.addNode(new HeaderNode<Integer>(1));
        graph.addNode(new HeaderNode<Integer>(2));
        graph.addNode(new HeaderNode<Integer>(3));
        graph.addNode(new HeaderNode<Integer>(4));
        graph.addNode(new HeaderNode<Integer>(5));

        graph.addEdge(graph.getNodeByInfo(3), graph.getNodeByInfo(5));
        graph.addEdge(graph.getNodeByInfo(1), graph.getNodeByInfo(2));

        System.out.println(graph.getAllEdges());

    }
}