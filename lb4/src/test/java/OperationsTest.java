import org.example.Graph;
import org.example.HeaderNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class OperationsTest {
    @Test
    public void addingNodeTest() {
        Graph<Integer> graph = new Graph<>();
        graph.addNode(new HeaderNode<>(1));

        Assert.assertTrue(graph.isNodeInGraph(1));
    }

    @Test
    public void addingEdgeTest() {
        Graph<Integer> graph = new Graph<>();
        graph.addNode(new HeaderNode<>(1));
        graph.addNode(new HeaderNode<>(5));
        graph.addEdge(graph.getNodeByInfo(1), graph.getNodeByInfo(5));

        Assert.assertTrue(graph.isEdgeInGraph(1, 5));
    }

    @Test
    public void isEmptyTest(){
        Graph<Integer> graph = new Graph<>();
        Assert.assertTrue(graph.isEmpty());
    }

    @Test
    public void clearTest(){
        Graph<Integer> graph = new Graph<>();
        graph.addNode(new HeaderNode<>(1));
        graph.addNode(new HeaderNode<>(5));
        graph.clear();
        Assert.assertTrue(graph.isEmpty());
    }

    @Test
    public void gettingNodeTest() {
        Graph<Integer> graph = new Graph<>();
        HeaderNode<Integer> node = new HeaderNode<>(5);
        graph.addNode(node);

        Assert.assertEquals(node, graph.getNodeByInfo(5));
    }

    @Test
    public void isNodeInGraphTest() {
        Graph<Integer> graph = new Graph<>();
        graph.addNode(new HeaderNode<>(5));

        Assert.assertTrue(graph.isNodeInGraph(5));
    }

    @Test
    public void isEdgeInGraphTest() {
        Graph<Integer> graph = new Graph<>();
        graph.addNode(new HeaderNode<>(1));
        graph.addNode(new HeaderNode<>(5));
        graph.addEdge(graph.getNodeByInfo(1), graph.getNodeByInfo(5));

        Assert.assertTrue(graph.isEdgeInGraph(1, 5));
    }

    @Test
    public void gettingNodesCountTest() {
        Graph<Integer> graph = new Graph<>();
        graph.addNode(new HeaderNode<>(1));
        graph.addNode(new HeaderNode<>(2));
        graph.addNode(new HeaderNode<>(3));
        graph.addNode(new HeaderNode<>(4));
        graph.addNode(new HeaderNode<>(5));
        graph.addNode(new HeaderNode<>(6));

        Assert.assertEquals(6, graph.getNodesCount());
    }

    @Test
    public void gettingEdgesCountTest() {
        Graph<Integer> graph = new Graph<>();
        graph.addNode(new HeaderNode<>(1));
        graph.addNode(new HeaderNode<>(2));
        graph.addNode(new HeaderNode<>(3));
        graph.addNode(new HeaderNode<>(4));
        graph.addNode(new HeaderNode<>(5));
        graph.addNode(new HeaderNode<>(6));

        graph.addEdge(graph.getNodeByInfo(3), graph.getNodeByInfo(4));
        graph.addEdge(graph.getNodeByInfo(5), graph.getNodeByInfo(6));
        graph.addEdge(graph.getNodeByInfo(1), graph.getNodeByInfo(6));

        Assert.assertEquals(3, graph.getEdgesCount());
    }

    @Test
    public void gettingNodeDegreeTest() {
        Graph<Integer> graph = new Graph<>();
        graph.addNode(new HeaderNode<>(1));
        graph.addNode(new HeaderNode<>(2));
        graph.addNode(new HeaderNode<>(3));
        graph.addNode(new HeaderNode<>(4));
        graph.addNode(new HeaderNode<>(5));
        graph.addNode(new HeaderNode<>(6));

        graph.addEdge(graph.getNodeByInfo(1), graph.getNodeByInfo(4));
        graph.addEdge(graph.getNodeByInfo(1), graph.getNodeByInfo(6));
        graph.addEdge(graph.getNodeByInfo(1), graph.getNodeByInfo(2));
        graph.addEdge(graph.getNodeByInfo(2), graph.getNodeByInfo(5));
        graph.addEdge(graph.getNodeByInfo(3), graph.getNodeByInfo(6));

        Assert.assertEquals(3, graph.getNodeDegree(graph.getNodeByInfo(1)));
    }

    @Test
    public void deletingNodeTest() {
        Graph<Integer> graph = new Graph<>();
        graph.addNode(new HeaderNode<>(1));
        graph.addNode(new HeaderNode<>(2));
        graph.addNode(new HeaderNode<>(3));
        graph.addNode(new HeaderNode<>(4));
        graph.addNode(new HeaderNode<>(5));
        graph.addNode(new HeaderNode<>(6));

        graph.addEdge(graph.getNodeByInfo(1), graph.getNodeByInfo(4));
        graph.addEdge(graph.getNodeByInfo(1), graph.getNodeByInfo(6));
        graph.addEdge(graph.getNodeByInfo(1), graph.getNodeByInfo(2));
        graph.addEdge(graph.getNodeByInfo(2), graph.getNodeByInfo(5));
        graph.addEdge(graph.getNodeByInfo(3), graph.getNodeByInfo(6));

        graph.deleteNodeByInfo(1);

        Assert.assertFalse(graph.isNodeInGraph(1));
    }

    @Test
    public void deletingEdgeTest() {
        Graph<Integer> graph = new Graph<>();
        graph.addNode(new HeaderNode<>(1));
        graph.addNode(new HeaderNode<>(2));
        graph.addNode(new HeaderNode<>(3));
        graph.addNode(new HeaderNode<>(4));
        graph.addNode(new HeaderNode<>(5));
        graph.addNode(new HeaderNode<>(6));

        graph.addEdge(graph.getNodeByInfo(1), graph.getNodeByInfo(4));
        graph.addEdge(graph.getNodeByInfo(1), graph.getNodeByInfo(6));
        graph.addEdge(graph.getNodeByInfo(1), graph.getNodeByInfo(2));
        graph.addEdge(graph.getNodeByInfo(2), graph.getNodeByInfo(5));
        graph.addEdge(graph.getNodeByInfo(3), graph.getNodeByInfo(6));

        graph.deleteEdge(1, 6);

        Assert.assertFalse(graph.isEdgeInGraph(1, 6));
    }

    @Test
    public void gettingAllEdgesTest() {
        Graph<Integer> graph = new Graph<>();
        graph.addNode(new HeaderNode<>(1));
        graph.addNode(new HeaderNode<>(2));
        graph.addNode(new HeaderNode<>(3));
        graph.addNode(new HeaderNode<>(4));
        graph.addNode(new HeaderNode<>(5));
        graph.addNode(new HeaderNode<>(6));

        graph.addEdge(graph.getNodeByInfo(1), graph.getNodeByInfo(4));
        graph.addEdge(graph.getNodeByInfo(1), graph.getNodeByInfo(6));
        graph.addEdge(graph.getNodeByInfo(1), graph.getNodeByInfo(2));
        graph.addEdge(graph.getNodeByInfo(2), graph.getNodeByInfo(5));
        graph.addEdge(graph.getNodeByInfo(3), graph.getNodeByInfo(6));

        Assert.assertEquals(5, graph.getAllEdges().size());
    }

    @Test
    public void gettingIncidentalEdges() {
        Graph<Integer> graph = new Graph<>();
        graph.addNode(new HeaderNode<>(1));
        graph.addNode(new HeaderNode<>(2));
        graph.addNode(new HeaderNode<>(3));
        graph.addNode(new HeaderNode<>(4));
        graph.addNode(new HeaderNode<>(5));
        graph.addNode(new HeaderNode<>(6));

        graph.addEdge(graph.getNodeByInfo(1), graph.getNodeByInfo(4));
        graph.addEdge(graph.getNodeByInfo(1), graph.getNodeByInfo(6));
        graph.addEdge(graph.getNodeByInfo(1), graph.getNodeByInfo(2));
        graph.addEdge(graph.getNodeByInfo(2), graph.getNodeByInfo(5));
        graph.addEdge(graph.getNodeByInfo(3), graph.getNodeByInfo(6));

        Assert.assertEquals(3, graph.getIncidentalEdges(graph.getNodeByInfo(1)).size());
    }

    @Test
    public void nodeIteratorTest() {
        Graph<Integer> graph = new Graph<>();

        HeaderNode<Integer> node1 = new HeaderNode<>(1);
        HeaderNode<Integer> node2 = new HeaderNode<>(2);
        HeaderNode<Integer> node3 = new HeaderNode<>(3);
        HeaderNode<Integer> node4 = new HeaderNode<>(4);

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);

        ListIterator<HeaderNode<Integer>> iterator = graph.getNodeIterator();
        Assert.assertEquals(node1, iterator.next());
        Assert.assertEquals(node2, iterator.next());
        Assert.assertEquals(node3, iterator.next());
        Assert.assertEquals(node4, iterator.next());
    }

    @Test
    public void adjacentNodeIteratorTest() {
        Graph<Integer> graph = new Graph<>();

        HeaderNode<Integer> node1 = new HeaderNode<>(1);
        HeaderNode<Integer> node2 = new HeaderNode<>(2);
        HeaderNode<Integer> node3 = new HeaderNode<>(3);
        HeaderNode<Integer> node4 = new HeaderNode<>(4);

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);

        graph.addEdge(node1, node4);
        graph.addEdge(node1, node3);
        graph.addEdge(node1, node2);
        graph.addEdge(node2, node1);
        graph.addEdge(node3, node2);

        ListIterator<HeaderNode<Integer>> iterator = graph.getAdjacentNodesIterator(node1);
        Assert.assertEquals(node4, iterator.next());
        Assert.assertEquals(node3, iterator.next());
        Assert.assertEquals(node2, iterator.next());
    }

    @Test
    public void edgeIteratorTest() {
        Graph<Integer> graph = new Graph<>();

        HeaderNode<Integer> node1 = new HeaderNode<>(1);
        HeaderNode<Integer> node2 = new HeaderNode<>(2);
        HeaderNode<Integer> node3 = new HeaderNode<>(3);
        HeaderNode<Integer> node4 = new HeaderNode<>(4);

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);

        graph.addEdge(node1, node4);
        graph.addEdge(node1, node3);
        graph.addEdge(node1, node2);
        graph.addEdge(node2, node1);
        graph.addEdge(node3, node2);

        ListIterator<List<HeaderNode<Integer>>> iterator = graph.getEdgeIterator();
        Assert.assertEquals(List.of(node1, node4), iterator.next());
        Assert.assertEquals(List.of(node1, node3), iterator.next());
        Assert.assertEquals(List.of(node1, node2), iterator.next());
        Assert.assertEquals(List.of(node2, node1), iterator.next());
        Assert.assertEquals(List.of(node3, node2), iterator.next());
    }

    @Test
    public void incidentalEdgeIteratorTest() {
        Graph<Integer> graph = new Graph<>();

        HeaderNode<Integer> node1 = new HeaderNode<>(1);
        HeaderNode<Integer> node2 = new HeaderNode<>(2);
        HeaderNode<Integer> node3 = new HeaderNode<>(3);
        HeaderNode<Integer> node4 = new HeaderNode<>(4);

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);

        graph.addEdge(node1, node4);
        graph.addEdge(node1, node3);
        graph.addEdge(node1, node2);
        graph.addEdge(node2, node1);
        graph.addEdge(node3, node2);

        ListIterator<List<HeaderNode<Integer>>> iterator = graph.getIncidentalEdgesIterator(node1);
        Assert.assertEquals(List.of(node1, node4), iterator.next());
        Assert.assertEquals(List.of(node1, node3), iterator.next());
        Assert.assertEquals(List.of(node1, node2), iterator.next());
    }
}
