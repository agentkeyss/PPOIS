package org.example;

import java.util.Iterator;
import java.util.List;

public class EdgeIterator<T> implements Iterator<List<T>> {
    protected final List<Edge<T>> value;
    protected int current = 0;

    public EdgeIterator(List<Edge<T>> value) {
        this.value = value;
    }

    @Override
    public boolean hasNext() {
        return current < value.size();
    }

    @Override
    public List<T> next() {
        return List.of(value.get(current).getStartNode().getInfo(), value.get(current++).getFinishNode().getInfo());
    }
}
