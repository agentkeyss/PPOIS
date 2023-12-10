import org.example.BinaryTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class OperationsTests {
    @Test
    public void insertTest() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.insertNode(10);
        tree.insertNode(20);
        tree.insertNode(5);

        Assertions.assertEquals(List.of(5, 10, 20), tree.asList());
    }

    @Test
    public void searchTest() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.insertNode(10);
        tree.insertNode(20);
        tree.insertNode(5);
        tree.insertNode(7);
        tree.insertNode(1);
        tree.insertNode(15);
        Assertions.assertEquals(20, tree.searchNode(20).getValue());
    }

    @Test
    public void deleteTest() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.insertNode(10);
        tree.insertNode(20);
        tree.insertNode(5);
        tree.insertNode(7);
        tree.insertNode(1);
        tree.insertNode(15);
        tree.deleteNode(10);
        tree.deleteNode(7);

        Assertions.assertEquals(List.of(1, 5, 15, 20), tree.asList());
    }
}
