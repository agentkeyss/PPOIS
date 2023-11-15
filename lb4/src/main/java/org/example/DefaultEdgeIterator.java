package org.example;

import java.util.List;

public class DefaultEdgeIterator<T> extends EdgeIterator<T> {

    public DefaultEdgeIterator(List<Edge<T>> value) {
        super(value);
    }

    @Override
    public void remove() {
        super.value.remove(current);
    }
}
