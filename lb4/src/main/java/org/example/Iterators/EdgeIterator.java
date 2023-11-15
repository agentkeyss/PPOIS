package org.example.Iterators;

import org.example.Models.Edge;
import org.example.Models.Pair;

import java.util.Iterator;
import java.util.List;

public class EdgeIterator<T> implements Iterator<Pair<T, T>> {
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
    public Pair<T, T> next() {
        return new Pair<>(value.get(current).getStartNode().getInfo(), value.get(current++).getFinishNode().getInfo());
    }
}
