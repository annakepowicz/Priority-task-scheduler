package model;

import exceptions.InvalidTaskDataException;

public class FeatureRequest extends Task {
    public FeatureRequest(String title, int priority) throws InvalidTaskDataException {
        super(title, priority);
    }

    @Override
    public String getTypeLabel() {
        return "FEATURE REQUEST"; 
    }
}
