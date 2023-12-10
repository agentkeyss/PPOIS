package org.example;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;

    public BinaryTree() {
        root = null;
    }

    public Node<T> getRoot() {
        return root;
    }

    /**
     * Функция для добавления узла с определенным значением в бинарное дерево поиска
     * @param value значение добавляемого узла
     */
    public void insertNode(T value) {
        if (root == null) root = new Node<>(value);
        else recursiveInsertNode(root, value);
    }

    /**
     * Функция для поиска узла с определенным значением в бинарном дереве поиска
     * @param value значение нужного узла
     * @return узел с данным значением
     */
    public Node<T> searchNode(T value) {
        return recursiveSearchNode(root, value);
    }

    /**
     * Функция для нахождения минимального значения в бинарном дереве поиска
     * @return узел с минимальным значением
     */
    public Node<T> getMin() {
        return recursiveGetMin(root);
    }

    /**
     * Функция для нахождения максимального значения в бинарном дереве поиска
     * @return узел с максимальным значением
     */
    public Node<T> getMax() {
        return recursiveGetMax(root);
    }

    /**
     * Функция для удаления узла с определенным значением из бинарного дерева поиска
     * @param value значение удаляемого узла
     * @return удаленный узел
     */
    public Node<T> deleteNode(T value) {
        return recursiveDeleteNode(root, value);
    }

    /**
     * Функция для представления бинарного дерева поиска как отсортированный список
     * @return отсортированный список
     */
    public List<T> asList() {
        List<T> result = new ArrayList<>();
        return recursiveAsList(root, result);
    }

    private List<T> recursiveAsList(Node<T> currentNode, List<T> result) {
        if (currentNode == null) return result;
        recursiveAsList(currentNode.left, result);
        result.add(currentNode.value);
        recursiveAsList(currentNode.right, result);

        return result;
    }

    private Node<T> recursiveDeleteNode(Node<T> currentNode, T value) {
        if (currentNode == null) return null;
        else if (value.compareTo(currentNode.getValue()) < 0) currentNode.left = recursiveDeleteNode(currentNode.left, value);
        else if (value.compareTo(currentNode.getValue()) > 0) currentNode.right = recursiveDeleteNode(currentNode.right, value);
        else {
            if (currentNode.left == null || currentNode.right == null)
                currentNode = (currentNode.left == null) ? currentNode.right : currentNode.left;
            else {
                Node<T> maxInLeft = recursiveGetMax(currentNode.left);
                currentNode.value = maxInLeft.value;
                currentNode.right = recursiveDeleteNode(currentNode.right, maxInLeft.getValue());
                currentNode.left = recursiveDeleteNode(currentNode.left, maxInLeft.getValue());
            }
        }

        return currentNode;
    }

    private Node<T> recursiveGetMin(Node<T> currentNode) {
        if (currentNode == null) return null;
        if (currentNode.left == null) return currentNode;
        return recursiveGetMin(currentNode.left);
    }

    private Node<T> recursiveGetMax(Node<T> currentNode) {
        if (currentNode == null) return null;
        if (currentNode.right == null) return currentNode;
        return recursiveGetMin(currentNode.right);
    }

    private Node<T> recursiveSearchNode(Node<T> currentNode, T value) {
        if (currentNode == null) return null;
        if (currentNode.getValue().compareTo(value) == 0) return currentNode;
        return (value.compareTo(currentNode.getValue()) < 0) ? recursiveSearchNode(currentNode.left, value) : recursiveSearchNode(currentNode.right, value);
    }

    private void recursiveInsertNode(Node<T> currentNode, T value) {
        if (value.compareTo(currentNode.getValue()) < 0) {
            if (currentNode.left == null) currentNode.left = new Node<>(value);
            else recursiveInsertNode(currentNode.left, value);
        }
        else if (value.compareTo(currentNode.getValue()) >= 0) {
            if (currentNode.right == null) currentNode.right = new Node<>(value);
            else recursiveInsertNode(currentNode.right, value);
        }
    }

    /**
     * Класс узла бинарного дерева поиска. Прописан как статический класс класса BinaryTree для возможности класса BinaryTree полноценно работать с
     * данным классом и невозможности любым другим классам изменить состояние узла нежелательным для разработчика образом
     * @param <T> класс, содержащийся в данном узле
     */
    public static class Node<T extends Comparable<T>> {
        private T value;
        private Node<T> left;
        private Node<T> right;

        private Node(T value) {
            this.value = value;
            left = null;
            right = null;
        }

        private Node(Node<T> node) {
            this.value = node.value;
            this.left = node.left;
            this.right = node.right;
        }

        public T getValue() {
            return value;
        }
    }

}
