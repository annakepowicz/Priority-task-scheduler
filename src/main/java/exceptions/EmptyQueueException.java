package exceptions;

public class EmptyQueueException extends RuntimeException {
    public EmptyQueueException() {
        super("QUEUE ERROR: The priority queue is empty.");
    }
}