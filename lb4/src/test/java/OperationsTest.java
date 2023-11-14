import org.example.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.ListIterator;

public class OperationsTest {

    @Test
    public void addingNodeTest()  throws NullNodeException {
        Graph<Integer> graph = new Graph<>();
        graph.addNode(1);

        Assert.assertTrue(graph.isNodeInGraph(1));
    }

    @Test
    public void addingSimilarNodeTest()  throws NullNodeException {
        Graph<Integer> graph = new Graph<>();
        graph.addNode(1);
        graph.addNode(1);

        Assert.assertEquals(1, graph.getNodesCount());
    }

    @Test
    public void addingEdgeTest() throws NullNodeException, NullEdgeException {
        Graph<Integer> graph = new Graph<>();

        HeaderNode<Integer> node1 = new HeaderNode<>(1);
        HeaderNode<Integer> node2 = new HeaderNode<>(5);

        graph.addNode(1);
        graph.addNode(5);
        graph.addEdge(new Edge<>(node1, node2));

        Assert.assertTrue(graph.isEdgeInGraph(new Edge<>(node1, node2)));
    }

    @Test
    public void addingNullEdgeTest() throws NullNodeException, NullEdgeException{
        Graph<Integer> graph = new Graph<>();

        HeaderNode<Integer> node1 = new HeaderNode<>(1);
        HeaderNode<Integer> node2 = new HeaderNode<>(5);

        graph.addNode(1);
        graph.addNode(5);
        graph.addEdge(new Edge<>(null, null));

        Assert.assertEquals(0, graph.getEdgesCount());
    }

    @Test
    public void isEmptyTest(){
        Graph<Integer> graph = new Graph<>();
        Assert.assertTrue(graph.isEmpty());
    }

    @Test
    public void clearTest() throws NullNodeException{
        Graph<Integer> graph = new Graph<>();
        graph.addNode(1);
        graph.addNode(5);
        graph.clear();
        Assert.assertTrue(graph.isEmpty());
    }

    @Test
    public void gettingNodeTest() throws NullNodeException{
        Graph<Integer> graph = new Graph<>();
        HeaderNode<Integer> node = new HeaderNode<>(5);
        graph.addNode(5);

        Assert.assertEquals(node, graph.getNodesByInfo(5).get(0));
    }

    @Test
    public void isNodeInGraphTest() throws NullNodeException{
        Graph<Integer> graph = new Graph<>();
        graph.addNode(5);

        Assert.assertTrue(graph.isNodeInGraph(5));
    }

    @Test
    public void isEdgeInGraphTest() throws NullNodeException, NullEdgeException{
        Graph<Integer> graph = new Graph<>();
        graph.addNode(1);
        graph.addNode(5);
        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(5)));

        Assert.assertTrue(graph.isEdgeInGraph(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(5))));
    }

    @Test
    public void gettingNodesCountTest() throws NullNodeException{
        Graph<Integer> graph = new Graph<>();
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addNode(6);

        Assert.assertEquals(6, graph.getNodesCount());
    }

    @Test
    public void gettingEdgesCountTest() throws NullNodeException, NullEdgeException{
        Graph<Integer> graph = new Graph<>();
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addNode(6);

        graph.addEdge(new Edge<>(new HeaderNode<>(3), new HeaderNode<>(5)));
        graph.addEdge(new Edge<>(new HeaderNode<>(5), new HeaderNode<>(6)));
        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(6)));

        Assert.assertEquals(3, graph.getEdgesCount());
    }

    @Test
    public void gettingNodeDegreeTest() throws NullNodeException, NullEdgeException, NodeNotFoundException {
        Graph<Integer> graph = new Graph<>();
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addNode(6);


        graph.addEdge(new Edge<>(graph.getNodesByInfo(1).get(0), graph.getNodesByInfo(4).get(0)));
        graph.addEdge(new Edge<>(graph.getNodesByInfo(1).get(0), graph.getNodesByInfo(6).get(0)));
        graph.addEdge(new Edge<>(graph.getNodesByInfo(1).get(0), graph.getNodesByInfo(2).get(0)));
        graph.addEdge(new Edge<>(graph.getNodesByInfo(2).get(0), graph.getNodesByInfo(5).get(0)));
        graph.addEdge(new Edge<>(graph.getNodesByInfo(3).get(0), graph.getNodesByInfo(6).get(0)));

        Assert.assertEquals(3, graph.getNodeDegree(graph.getNodesByInfo(1).get(0)));
    }

    @Test
    public void deletingNodeTest() throws NullNodeException, NullEdgeException{
        Graph<Integer> graph = new Graph<>();
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addNode(6);

        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(4)));
        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(6)));
        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(2)));
        graph.addEdge(new Edge<>(new HeaderNode<>(2), new HeaderNode<>(5)));
        graph.addEdge(new Edge<>(new HeaderNode<>(3), new HeaderNode<>(6)));

        graph.deleteNode(graph.getNodesByInfo(1).get(0));

        Assert.assertFalse(graph.isNodeInGraph(1));
    }

    @Test
    public void deletingEdgeTest() throws NullNodeException, NullEdgeException{
        Graph<Integer> graph = new Graph<>();
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addNode(6);

        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(4)));
        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(6)));
        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(2)));
        graph.addEdge(new Edge<>(new HeaderNode<>(2), new HeaderNode<>(5)));
        graph.addEdge(new Edge<>(new HeaderNode<>(3), new HeaderNode<>(6)));

        graph.deleteEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(6)));

        Assert.assertFalse(graph.isEdgeInGraph(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(6))));
    }

    @Test
    public void gettingAllEdgesTest() throws NullNodeException, NullEdgeException{
        Graph<Integer> graph = new Graph<>();
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addNode(6);

        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(4)));
        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(6)));
        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(2)));
        graph.addEdge(new Edge<>(new HeaderNode<>(2), new HeaderNode<>(5)));
        graph.addEdge(new Edge<>(new HeaderNode<>(3), new HeaderNode<>(6)));

        Assert.assertEquals(5, graph.getEdgeList().size());
    }

    @Test
    public void gettingIncidentalEdges() throws NullNodeException, NullEdgeException{
        Graph<Integer> graph = new Graph<>();
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addNode(6);

        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(4)));
        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(6)));
        graph.addEdge(new Edge<>(new HeaderNode<>(1), new HeaderNode<>(2)));
        graph.addEdge(new Edge<>(new HeaderNode<>(2), new HeaderNode<>(5)));
        graph.addEdge(new Edge<>(new HeaderNode<>(3), new HeaderNode<>(6)));

        Assert.assertEquals(3, graph.getIncidentalEdges(graph.getNodesByInfo(1).get(0)).size());
    }

    @Test
    public void nodeIteratorTest() throws NullNodeException{
        Graph<Integer> graph = new Graph<>();

        HeaderNode<Integer> node1 = new HeaderNode<>(1);
        HeaderNode<Integer> node2 = new HeaderNode<>(2);
        HeaderNode<Integer> node3 = new HeaderNode<>(3);
        HeaderNode<Integer> node4 = new HeaderNode<>(4);

        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);

        ListIterator<HeaderNode<Integer>> iterator = graph.getNodeIterator();
        Assert.assertEquals(node1, iterator.next());
        Assert.assertEquals(node2, iterator.next());
        Assert.assertEquals(node3, iterator.next());
        Assert.assertEquals(node4, iterator.next());
    }

    @Test
    public void adjacentNodeIteratorTest() throws NullNodeException, NullEdgeException{
        Graph<Integer> graph = new Graph<>();

        HeaderNode<Integer> node1 = new HeaderNode<>(1);
        HeaderNode<Integer> node2 = new HeaderNode<>(2);
        HeaderNode<Integer> node3 = new HeaderNode<>(3);
        HeaderNode<Integer> node4 = new HeaderNode<>(4);

        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);

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
    public void edgeIteratorTest() throws NullNodeException, NullEdgeException{
        Graph<Integer> graph = new Graph<>();

        HeaderNode<Integer> node1 = new HeaderNode<>(1);
        HeaderNode<Integer> node2 = new HeaderNode<>(2);
        HeaderNode<Integer> node3 = new HeaderNode<>(3);
        HeaderNode<Integer> node4 = new HeaderNode<>(4);

        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);

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
    public void incidentalEdgeIteratorTest() throws NullNodeException, NullEdgeException{
        Graph<Integer> graph = new Graph<>();

        HeaderNode<Integer> node1 = new HeaderNode<>(1);
        HeaderNode<Integer> node2 = new HeaderNode<>(2);
        HeaderNode<Integer> node3 = new HeaderNode<>(3);
        HeaderNode<Integer> node4 = new HeaderNode<>(4);

        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);

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

    @Test
    public void nodeConstantIteratorTest() throws NullNodeException{
        Graph<Integer> graph = new Graph<>();

        HeaderNode<Integer> node1 = new HeaderNode<>(1);
        HeaderNode<Integer> node2 = new HeaderNode<>(2);
        HeaderNode<Integer> node3 = new HeaderNode<>(3);
        HeaderNode<Integer> node4 = new HeaderNode<>(4);

        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);

        ConstantIterator<HeaderNode<Integer>> iterator = graph.getConstantNodeIterator();
        Assert.assertEquals(node1, iterator.next());
        Assert.assertEquals(node2, iterator.next());
        Assert.assertEquals(node3, iterator.next());
        Assert.assertEquals(node4, iterator.next());
    }

    @Test
    public void adjacentNodeConstantIteratorTest() throws NullNodeException, NullEdgeException{
        Graph<Integer> graph = new Graph<>();

        HeaderNode<Integer> node1 = new HeaderNode<>(1);
        HeaderNode<Integer> node2 = new HeaderNode<>(2);
        HeaderNode<Integer> node3 = new HeaderNode<>(3);
        HeaderNode<Integer> node4 = new HeaderNode<>(4);

        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);

        graph.addEdge(new Edge<>(node1, node4));
        graph.addEdge(new Edge<>(node1, node3));
        graph.addEdge(new Edge<>(node1, node2));
        graph.addEdge(new Edge<>(node2, node1));
        graph.addEdge(new Edge<>(node3, node2));

        ConstantIterator<HeaderNode<Integer>> iterator = graph.getConstantAdjacentNodesIterator(node1);
        Assert.assertEquals(node4, iterator.next());
        Assert.assertEquals(node3, iterator.next());
        Assert.assertEquals(node2, iterator.next());
    }

    @Test
    public void edgeConstantIteratorTest() throws NullNodeException, NullEdgeException{
        Graph<Integer> graph = new Graph<>();

        HeaderNode<Integer> node1 = new HeaderNode<>(1);
        HeaderNode<Integer> node2 = new HeaderNode<>(2);
        HeaderNode<Integer> node3 = new HeaderNode<>(3);
        HeaderNode<Integer> node4 = new HeaderNode<>(4);

        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);

        graph.addEdge(new Edge<>(node1, node4));
        graph.addEdge(new Edge<>(node1, node3));
        graph.addEdge(new Edge<>(node1, node2));
        graph.addEdge(new Edge<>(node2, node1));
        graph.addEdge(new Edge<>(node3, node2));

        ConstantIterator<Edge<Integer>> iterator = graph.getConstantEdgeIterator();
        Assert.assertEquals(new Edge<>(node1, node4), iterator.next());
        Assert.assertEquals(new Edge<>(node1, node3), iterator.next());
        Assert.assertEquals(new Edge<>(node1, node2), iterator.next());
        Assert.assertEquals(new Edge<>(node2, node1), iterator.next());
        Assert.assertEquals(new Edge<>(node3, node2), iterator.next());
    }

    @Test
    public void incidentalEdgeConstantIteratorTest() throws NullNodeException, NullEdgeException{
        Graph<Integer> graph = new Graph<>();

        HeaderNode<Integer> node1 = new HeaderNode<>(1);
        HeaderNode<Integer> node2 = new HeaderNode<>(2);
        HeaderNode<Integer> node3 = new HeaderNode<>(3);
        HeaderNode<Integer> node4 = new HeaderNode<>(4);

        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);

        graph.addEdge(new Edge<>(node1, node4));
        graph.addEdge(new Edge<>(node1, node3));
        graph.addEdge(new Edge<>(node1, node2));
        graph.addEdge(new Edge<>(node2, node1));
        graph.addEdge(new Edge<>(node3, node2));

        ConstantIterator<Edge<Integer>> iterator = graph.getConstantIncidentalEdgesIterator(node1);
        Assert.assertEquals(new Edge<>(node1, node4), iterator.next());
        Assert.assertEquals(new Edge<>(node1, node3), iterator.next());
        Assert.assertEquals(new Edge<>(node1, node2), iterator.next());
    }

    @Test
    public void forEachTest() throws NullNodeException{
        Graph<Integer> graph = new Graph<>();

        HeaderNode<Integer> node1 = new HeaderNode<>(1);
        HeaderNode<Integer> node2 = new HeaderNode<>(2);
        HeaderNode<Integer> node3 = new HeaderNode<>(3);
        HeaderNode<Integer> node4 = new HeaderNode<>(4);

        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);

        for (HeaderNode<Integer> node : graph) {
            Assert.assertTrue(graph.isNodeInGraph(node.getInfo()));
        }
    }
}
