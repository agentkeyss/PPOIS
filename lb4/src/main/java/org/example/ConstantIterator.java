package org.example;

import javax.swing.*;
import java.util.Iterator;
import java.util.List;

public class ConstantIterator<T> implements Iterator<T> {
    private final List<T> value;
    private int current = 0;

    public ConstantIterator(List<T> value) {
        this.value = value;
    }

    @Override
    public boolean hasNext() {
        return current < value.size();
    }

    @Override
    public T next() {
        return value.get(current++);
    }
}
