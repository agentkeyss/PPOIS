import org.example.Exceptions.NodeNotFoundException;
import org.example.Exceptions.NullEdgeException;
import org.example.Exceptions.NullNodeException;
import org.example.Iterators.*;
import org.example.Models.Edge;
import org.example.Models.Graph;
import org.example.Models.HeaderNode;
import org.example.Models.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

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
        graph.addEdge(node1, node2);

        Assert.assertTrue(graph.isEdgeInGraph(new Edge<>(node1, node2)));
    }

    @Test
    public void addingNullEdgeTest() throws NullNodeException, NullEdgeException{
        Graph<Integer> graph = new Graph<>();

        HeaderNode<Integer> node1 = new HeaderNode<>(1);
        HeaderNode<Integer> node2 = new HeaderNode<>(5);

        graph.addNode(1);
        graph.addNode(5);
        graph.addEdge(null, null);

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
        graph.addEdge(new HeaderNode<>(1), new HeaderNode<>(5));

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

        graph.addEdge(new HeaderNode<>(3), new HeaderNode<>(5));
        graph.addEdge(new HeaderNode<>(5), new HeaderNode<>(6));
        graph.addEdge(new HeaderNode<>(1), new HeaderNode<>(6));

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


        graph.addEdge(graph.getNodesByInfo(1).get(0), graph.getNodesByInfo(4).get(0));
        graph.addEdge(graph.getNodesByInfo(1).get(0), graph.getNodesByInfo(6).get(0));
        graph.addEdge(graph.getNodesByInfo(1).get(0), graph.getNodesByInfo(2).get(0));
        graph.addEdge(graph.getNodesByInfo(2).get(0), graph.getNodesByInfo(5).get(0));
        graph.addEdge(graph.getNodesByInfo(3).get(0), graph.getNodesByInfo(6).get(0));

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

        graph.addEdge(graph.getNodesByInfo(1).get(0), graph.getNodesByInfo(4).get(0));
        graph.addEdge(graph.getNodesByInfo(1).get(0), graph.getNodesByInfo(6).get(0));
        graph.addEdge(graph.getNodesByInfo(1).get(0), graph.getNodesByInfo(2).get(0));
        graph.addEdge(graph.getNodesByInfo(2).get(0), graph.getNodesByInfo(5).get(0));
        graph.addEdge(graph.getNodesByInfo(3).get(0), graph.getNodesByInfo(6).get(0));

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

        graph.addEdge(graph.getNodesByInfo(1).get(0), graph.getNodesByInfo(4).get(0));
        graph.addEdge(graph.getNodesByInfo(1).get(0), graph.getNodesByInfo(6).get(0));
        graph.addEdge(graph.getNodesByInfo(1).get(0), graph.getNodesByInfo(2).get(0));
        graph.addEdge(graph.getNodesByInfo(2).get(0), graph.getNodesByInfo(5).get(0));
        graph.addEdge(graph.getNodesByInfo(3).get(0), graph.getNodesByInfo(6).get(0));

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

        graph.addEdge(graph.getNodesByInfo(1).get(0), graph.getNodesByInfo(4).get(0));
        graph.addEdge(graph.getNodesByInfo(1).get(0), graph.getNodesByInfo(6).get(0));
        graph.addEdge(graph.getNodesByInfo(1).get(0), graph.getNodesByInfo(2).get(0));
        graph.addEdge(graph.getNodesByInfo(2).get(0), graph.getNodesByInfo(5).get(0));
        graph.addEdge(graph.getNodesByInfo(3).get(0), graph.getNodesByInfo(6).get(0));

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

        graph.addEdge(graph.getNodesByInfo(1).get(0), graph.getNodesByInfo(4).get(0));
        graph.addEdge(graph.getNodesByInfo(1).get(0), graph.getNodesByInfo(6).get(0));
        graph.addEdge(graph.getNodesByInfo(1).get(0), graph.getNodesByInfo(2).get(0));
        graph.addEdge(graph.getNodesByInfo(2).get(0), graph.getNodesByInfo(5).get(0));
        graph.addEdge(graph.getNodesByInfo(3).get(0), graph.getNodesByInfo(6).get(0));

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

        DefaultNodeIterator<Integer> iterator = graph.getNodeIterator();
        Assert.assertEquals(1, 0 + iterator.next());
        Assert.assertEquals(2, 0 + iterator.next());
        Assert.assertEquals(3, 0 + iterator.next());
        Assert.assertEquals(4, 0 + iterator.next());
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

        graph.addEdge(node1, node4);
        graph.addEdge(node1, node3);
        graph.addEdge(node1, node2);
        graph.addEdge(node2, node1);
        graph.addEdge(node3, node2);

        DefaultNodeIterator<Integer> iterator = graph.getAdjacentNodesIterator(node1);
        Assert.assertEquals(4, 0 + iterator.next());
        Assert.assertEquals(3, 0 + iterator.next());
        Assert.assertEquals(2, 0 + iterator.next());
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

        graph.addEdge(node1, node4);
        graph.addEdge(node1, node3);
        graph.addEdge(node1, node2);
        graph.addEdge(node2, node1);
        graph.addEdge(node3, node2);

        DefaultEdgeIterator<Integer> iterator = graph.getEdgeIterator();
        Assert.assertEquals(new Pair<>(1, 4), iterator.next());
        Assert.assertEquals(new Pair<>(1, 3), iterator.next());
        Assert.assertEquals(new Pair<>(1, 2), iterator.next());
        Assert.assertEquals(new Pair<>(2, 1), iterator.next());
        Assert.assertEquals(new Pair<>(3, 2), iterator.next());
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

        graph.addEdge(node1, node4);
        graph.addEdge(node1, node3);
        graph.addEdge(node1, node2);
        graph.addEdge(node2, node1);
        graph.addEdge(node3, node2);

        Iterator<Pair<Integer, Integer>> iterator = graph.getIncidentalEdgesIterator(node1);
        Assert.assertEquals(new Pair<>(1, 4), iterator.next());
        Assert.assertEquals(new Pair<>(1, 3), iterator.next());
        Assert.assertEquals(new Pair<>(1, 2), iterator.next());
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

        ConstantNodeIterator<Integer> iterator = graph.getConstantNodeIterator();
        Assert.assertEquals(1, 0 + iterator.next());
        Assert.assertEquals(2, 0 + iterator.next());
        Assert.assertEquals(3, 0 + iterator.next());
        Assert.assertEquals(4, 0 + iterator.next());
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

        graph.addEdge(node1, node4);
        graph.addEdge(node1, node3);
        graph.addEdge(node1, node2);
        graph.addEdge(node2, node1);
        graph.addEdge(node3, node2);

        ConstantNodeIterator<Integer> iterator = graph.getConstantAdjacentNodesIterator(node1);
        Assert.assertEquals(4, 0 + iterator.next());
        Assert.assertEquals(3, 0 + iterator.next());
        Assert.assertEquals(2, 0 + iterator.next());
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

        graph.addEdge(node1, node4);
        graph.addEdge(node1, node3);
        graph.addEdge(node1, node2);
        graph.addEdge(node2, node1);
        graph.addEdge(node3, node2);

        ConstantEdgeIterator<Integer> iterator = graph.getConstantEdgeIterator();
        Assert.assertEquals(new Pair<>(1, 4), iterator.next());
        Assert.assertEquals(new Pair<>(1, 3), iterator.next());
        Assert.assertEquals(new Pair<>(1, 2), iterator.next());
        Assert.assertEquals(new Pair<>(2, 1), iterator.next());
        Assert.assertEquals(new Pair<>(3, 2), iterator.next());
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

        graph.addEdge(node1, node4);
        graph.addEdge(node1, node3);
        graph.addEdge(node1, node2);
        graph.addEdge(node2, node1);
        graph.addEdge(node3, node2);

        ConstantIterator<Pair<Integer, Integer>> iterator = graph.getConstantIncidentalEdgesIterator(node1);
        Assert.assertEquals(new Pair<>(1, 4), iterator.next());
        Assert.assertEquals(new Pair<>(1, 3), iterator.next());
        Assert.assertEquals(new Pair<>(1, 2), iterator.next());
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

        for (Integer node : graph) {
            Assert.assertTrue(graph.isNodeInGraph(node));
        }
    }
}
