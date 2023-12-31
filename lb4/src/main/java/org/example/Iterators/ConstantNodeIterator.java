package org.example.Iterators;

import org.example.Models.HeaderNode;

import java.util.Iterator;
import java.util.List;

public class ConstantNodeIterator<T> extends NodeIterator<T> implements Iterator<T> {
    public ConstantNodeIterator(List<HeaderNode<T>> value) {
        super(value);
    }
}
