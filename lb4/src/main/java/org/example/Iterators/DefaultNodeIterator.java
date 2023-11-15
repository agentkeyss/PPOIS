package org.example.Iterators;

import org.example.Models.HeaderNode;

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