package org.example;

public class NodeNotFoundException extends Exception{
    public NodeNotFoundException() {
        super("Данный узел не найден!");
    }

    public NodeNotFoundException(String message) {
        super(message);
    }
}