package model;

import exceptions.InvalidTaskDataException;
import java.time.LocalDateTime;

public abstract class Task {

    public static final int MIN_PRIORITY = 0;
    public static final int MAX_PRIORITY = 100;

    protected String title;
    protected int basePriority;
    protected LocalDateTime createdAt;

    public Task(String title, int priority) throws InvalidTaskDataException {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidTaskDataException("Tittle cannot be null or empty.");
        }
        if (priority < MIN_PRIORITY || priority > MAX_PRIORITY) {
            throw new InvalidTaskDataException("Priority must be between 0 and 100.");
        }
        this.title = title;
        this.basePriority = priority;
        this.createdAt = LocalDateTime.now();
    }

    public abstract String getTypeLabel();
    
    public int getEffectivePriority() {
        return basePriority;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format("[%s] (Priority: %d) %s", getTypeLabel(), getEffectivePriority(), title);
    }
}
