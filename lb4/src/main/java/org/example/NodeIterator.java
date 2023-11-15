package org.example;

import java.util.Iterator;
import java.util.List;

public class NodeIterator<T> implements Iterator<T> {
    protected final List<HeaderNode<T>> value;
    protected int current = 0;

    public NodeIterator(List<HeaderNode<T>> value) {
        this.value = value;
    }

    @Override
    public boolean hasNext() {
        return current < value.size();
    }

    @Override
    public T next() {
        return value.get(current++).getInfo();
    }
}