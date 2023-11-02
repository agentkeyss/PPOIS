package org.example;

public class NullEdgeException extends Exception{
    public NullEdgeException() {
        super("Ребро не должно принимать значение null!");
    }

    public NullEdgeException(String message) {
        super(message);
    }
}
