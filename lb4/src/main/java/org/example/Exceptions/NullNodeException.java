package org.example.Exceptions;

public class NullNodeException extends Exception{
    public NullNodeException() {
        super("Вершина не должна принимать значение null!");
    }

    public NullNodeException(String message) {
        super(message);
    }
}
