package org.example;

import java.util.*;

/**
 * Класс предназначен для представления графа в структуре Вирта и его обработки
 * @param <T> Тип данных для информации узлов
 */
public class Graph<T> {
    private List<HeaderNode<T>> nodeList;

    public Graph() {
        nodeList = new LinkedList<>();
    }

    public Graph(Graph<T> graph) {
        nodeList = new LinkedList<>(graph.getNodeList());
    }

    public List<HeaderNode<T>> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<HeaderNode<T>> nodeList) {
        this.nodeList = nodeList;
    }

    /**
     * Метод для добавления узла в граф
     * @param node добавляемый узел
     */
    public void addNode(HeaderNode<T> node) {
        nodeList.add(node);
    }

    /**
     * Метод для добавления ребра в граф
     * @param startNode узел, от которого идет ребро
     * @param finishNode узел, к которому идет ребро
     */
    public void addEdge(HeaderNode<T> startNode, HeaderNode<T> finishNode) {
        startNode.addToTrail(finishNode);
        finishNode.setCount(finishNode.getCount() + 1);
    }

    /**
     * Метод для проверки графа на пустоту
     * @return истинность утверждения
     */
    public boolean isEmpty() {
        return this.nodeList.isEmpty();
    }

    /**
     * Метод для очищения графа
     */
    public void clear() {
        this.nodeList.clear();
    }

    /**
     * Метод для получения узла по его информации
     * @param info заданная информация
     * @return узел, с данной информацией(если такого узла нет - null)
     */
    public HeaderNode<T> getNodeByInfo(T info) {
        for (HeaderNode<T> node : nodeList) {
            if (node.getInfo().equals(info)) return node;
        }

        return null;
    }

    /**
     * Метод для проверки наличия узла в графе
     * @param info заданная информация
     * @return истинность утверждения
     */
    public boolean isNodeInGraph(T info) {
        return getNodeByInfo(info) != null;
    }

    /**
     * Метод для проверки наличия ребра в графе
     * @param firstNodeInfo информационная часть стартовой вершины
     * @param secondNodeInfo информационная часть финишной вершины
     * @return истинность утверждения
     */
    public boolean isEdgeInGraph(T firstNodeInfo, T secondNodeInfo) {
        for (HeaderNode<T> node : getNodeByInfo(firstNodeInfo).getTrail()) {
            if (node.getInfo().equals(secondNodeInfo)) return true;
        }

        return false;
    }

    /**
     * Метод для получения количества узлов
     * @return количество узлов графа
     */
    public int getNodesCount() {
        return this.nodeList.size();
    }

    /**
     * Метод для получения количества ребер
     * @return количество ребер графа
     */
    public int getEdgesCount() {
        int result = 0;

        for (HeaderNode<T> node : this.nodeList) {
            result += node.getTrail().size();
        }

        return result;
    }

    /**
     * Метод для получения степени вершины
     * @param node заданная вершина
     * @return степень заданной вершины
     */
    public int getNodeDegree(HeaderNode<T> node) {
        return node.getTrail().size() + node.getCount();
    }

    /**
     * Метод для удаления вершины по ее информативной части
     * @param info заданная информация
     */
    public void deleteNodeByInfo(T info) {
        HeaderNode<T> deleteNode = getNodeByInfo(info);
        nodeList.remove(deleteNode);
    }

    /**
     * Метод для удаления ребра по начальному и конечному узлу
     * @param firstNodeInfo информационная часть стартовой вершины
     * @param secondNodeInfo информационная часть финишной вершины
     */
    public void deleteEdge(T firstNodeInfo, T secondNodeInfo) {
        getNodeByInfo(firstNodeInfo).getTrail().remove(getNodeByInfo(secondNodeInfo));
    }

    /**
     * Метод для получения всех ребер графа
     * @return список ребер графа
     */
    public List<List<HeaderNode<T>>> getAllEdges() {
        List<List<HeaderNode<T>>> edges = new ArrayList<>();

        for (HeaderNode<T> startNode : nodeList) {
            for (HeaderNode<T> finishNode : startNode.getTrail()) {
                edges.add(List.of(startNode, finishNode));
            }
        }

        return edges;
    }

    /**
     * Метод для получения инцидентных ребер к определенной вершине
     * @param node заданная вершина
     * @return список инцидентных ребер
     */
    public List<List<HeaderNode<T>>> getIncidentalEdges(HeaderNode<T> node) {
        List<List<HeaderNode<T>>> edges = new ArrayList<>();

        for (HeaderNode<T> finishNode : node.getTrail()) {
            edges.add(List.of(node, finishNode));
        }

        return edges;
    }

    /**
     * Метод для получения итератора для вершин
     * @return итератор вершин
     */
    public ListIterator<HeaderNode<T>> getNodeIterator() {
        return nodeList.listIterator();
    }

    /**
     * Метод для получения итератора для ребер
     * @return итератор ребер
     */
    public ListIterator<List<HeaderNode<T>>> getEdgeIterator() {
        return getAllEdges().listIterator();
    }

    /**
     * Метод для получения итератора для инцидентных определенной вершине ребер
     * @return итератор инцидентных ребер
     */
    public ListIterator<List<HeaderNode<T>>> getIncidentalEdgesIterator(HeaderNode<T> node) {
        return getIncidentalEdges(node).listIterator();
    }

    /**
     * Метод для получения итератора для смежных определенной вершине вершин
     * @return итератор смежных вершин
     */
    public ListIterator<HeaderNode<T>> getAdjacentNodesIterator(HeaderNode<T> node) {
        return node.getTrail().listIterator();
    }
}
