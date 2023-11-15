package org.example;

import java.util.List;

public class ConstantEdgeIterator<T> extends EdgeIterator<T> {
    public ConstantEdgeIterator(List<Edge<T>> value) {
        super(value);
    }
}
