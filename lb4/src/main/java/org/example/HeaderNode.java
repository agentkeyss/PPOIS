package org.example;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class HeaderNode<T> {
    private final T info;
    private int count;
    private final List<HeaderNode<T>> trail;

    public HeaderNode(T info) {
        this.info = info;
        this.count = 0;
        this.trail = new LinkedList<>();
    }

    public T getInfo() {
        return info;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<HeaderNode<T>> getTrail() {
        return new LinkedList<>(trail);
    }

    public void addToTrail(HeaderNode<T> node) {
        this.trail.add(node);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeaderNode<?> that = (HeaderNode<?>) o;
        return Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(info);
    }
}