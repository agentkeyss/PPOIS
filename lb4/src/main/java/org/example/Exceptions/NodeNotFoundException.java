package org.example.Exceptions;

public class NodeNotFoundException extends Exception{
    public NodeNotFoundException() {
        super("Данный узел не найден!");
    }

    public NodeNotFoundException(String message) {
        super(message);
    }
}