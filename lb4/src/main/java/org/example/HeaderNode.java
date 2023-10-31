package org.example;

import java.util.LinkedList;
import java.util.List;

public class HeaderNode<T> {
    private T info;
    private int count;
    private List<HeaderNode<T>> trail;

    public HeaderNode(T info) {
        this.info = info;
        this.count = 0;
        this.trail = new LinkedList<>();
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<HeaderNode<T>> getTrail() {
        return trail;
    }

    public void setTrail(List<HeaderNode<T>> trail) {
        this.trail = trail;
    }

    public void addToTrail(HeaderNode<T> node) {
        this.trail.add(node);
    }
}
