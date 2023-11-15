package org.example;

import java.util.Iterator;
import java.util.List;

public class DefaultNodeIterator<T> extends NodeIterator<T> {
    public DefaultNodeIterator(List<HeaderNode<T>> value) {
        super(value);
    }

    @Override
    public void remove() {
        super.value.remove(current);
    }
}