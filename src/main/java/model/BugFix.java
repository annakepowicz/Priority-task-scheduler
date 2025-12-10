package model;

import exceptions.InvalidTaskDataException;

public class BugFix extends Task {
    public BugFix(String title, int priority) throws InvalidTaskDataException {
        super(title, priority);
    }

    @Override
    public String getTypeLabel() {
        return "BUG FIX";
    }

    @Override
    public int getEffectivePriority() {
        return Math.min(super.basePriority + 20, Task.MAX_PRIORITY);
    }
}
