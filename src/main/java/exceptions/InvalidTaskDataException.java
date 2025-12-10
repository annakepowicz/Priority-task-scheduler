package exceptions;

public class InvalidTaskDataException extends SchedulerException {
    public InvalidTaskDataException(String message) {
        super("DATA ERROR: " + message);
    }
}