import org.example.Edge;
import org.example.Graph;
import org.example.HeaderNode;
import org.junit.Assert;
import org.junit.Test;

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
    public void addingNullNodeTest() {
        Graph<Integer> graph = new Graph<>();
        graph.addNode(null);

        Assert.assertTrue(graph.isEmpty());
    }

    @Test
    public void addingEdgeTest() {
        Graph<Integer> graph = new Graph<>();

        HeaderNode<Integer> node1 = new HeaderNode<>(1);
        HeaderNode<Integer> node2 = new HeaderNode<>(5);

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addEdge(new Edge<>(node1, node2));

        Assert.assertTrue(graph.isEdgeInGraph(new Edge<>(node1, node2)));
    }

    @Test
    public void addingNullEdgeTest() {
        Graph<Integer> graph = new Graph<>();

        HeaderNode<Integer> node1 = new HeaderNode<>(1);
        HeaderNode<Integer> node2 = new HeaderNode<>(5);

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addEdge(new Edge<>(null, null));

        Assert.assertEquals(0, graph.getEdgesCount());
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

        Assert.assertEquals(node, graph.getNodesByInfo(5).get(0));
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
        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(5)));

        Assert.assertTrue(graph.isEdgeInGraph(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(5))));
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

        graph.addEdge(new Edge<>(new HeaderNode<>(3), new HeaderNode<>(5)));
        graph.addEdge(new Edge<>(new HeaderNode<>(5), new HeaderNode<>(6)));
        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(6)));

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


        graph.addEdge(new Edge<>(graph.getNodesByInfo(1).get(0), graph.getNodesByInfo(4).get(0)));
        graph.addEdge(new Edge<>(graph.getNodesByInfo(1).get(0), graph.getNodesByInfo(6).get(0)));
        graph.addEdge(new Edge<>(graph.getNodesByInfo(1).get(0), graph.getNodesByInfo(2).get(0)));
        graph.addEdge(new Edge<>(graph.getNodesByInfo(2).get(0), graph.getNodesByInfo(5).get(0)));
        graph.addEdge(new Edge<>(graph.getNodesByInfo(3).get(0), graph.getNodesByInfo(6).get(0)));

        Assert.assertEquals(3, graph.getNodeDegree(graph.getNodesByInfo(1).get(0)));
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

        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(4)));
        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(6)));
        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(2)));
        graph.addEdge(new Edge<>(new HeaderNode<>(2), new HeaderNode<>(5)));
        graph.addEdge(new Edge<>(new HeaderNode<>(3), new HeaderNode<>(6)));

        graph.deleteNode(graph.getNodesByInfo(1).get(0));

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

        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(4)));
        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(6)));
        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(2)));
        graph.addEdge(new Edge<>(new HeaderNode<>(2), new HeaderNode<>(5)));
        graph.addEdge(new Edge<>(new HeaderNode<>(3), new HeaderNode<>(6)));

        graph.deleteEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(6)));

        Assert.assertFalse(graph.isEdgeInGraph(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(6))));
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

        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(4)));
        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(6)));
        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(2)));
        graph.addEdge(new Edge<>(new HeaderNode<>(2), new HeaderNode<>(5)));
        graph.addEdge(new Edge<>(new HeaderNode<>(3), new HeaderNode<>(6)));

        Assert.assertEquals(5, graph.getEdgeList().size());
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

        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(4)));
        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(6)));
        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(2)));
        graph.addEdge(new Edge<>(new HeaderNode<>(2), new HeaderNode<>(5)));
        graph.addEdge(new Edge<>(new HeaderNode<>(3), new HeaderNode<>(6)));

        Assert.assertEquals(3, graph.getIncidentalEdges(graph.getNodesByInfo(1).get(0)).size());
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

        graph.addEdge(new Edge<>(node1, node4));
        graph.addEdge(new Edge<>(node1, node3));
        graph.addEdge(new Edge<>(node1, node2));
        graph.addEdge(new Edge<>(node2, node1));
        graph.addEdge(new Edge<>(node3, node2));

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

        graph.addEdge(new Edge<>(node1, node4));
        graph.addEdge(new Edge<>(node1, node3));
        graph.addEdge(new Edge<>(node1, node2));
        graph.addEdge(new Edge<>(node2, node1));
        graph.addEdge(new Edge<>(node3, node2));

        ListIterator<Edge<Integer>> iterator = graph.getEdgeIterator();
        Assert.assertEquals(new Edge<>(node1, node4), iterator.next());
        Assert.assertEquals(new Edge<>(node1, node3), iterator.next());
        Assert.assertEquals(new Edge<>(node1, node2), iterator.next());
        Assert.assertEquals(new Edge<>(node2, node1), iterator.next());
        Assert.assertEquals(new Edge<>(node3, node2), iterator.next());
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

        graph.addEdge(new Edge<>(node1, node4));
        graph.addEdge(new Edge<>(node1, node3));
        graph.addEdge(new Edge<>(node1, node2));
        graph.addEdge(new Edge<>(node2, node1));
        graph.addEdge(new Edge<>(node3, node2));

        ListIterator<Edge<Integer>> iterator = graph.getIncidentalEdgesIterator(node1);
        Assert.assertEquals(new Edge<>(node1, node4), iterator.next());
        Assert.assertEquals(new Edge<>(node1, node3), iterator.next());
        Assert.assertEquals(new Edge<>(node1, node2), iterator.next());
    }
}
