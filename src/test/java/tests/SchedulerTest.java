package tests;

import logic.Scheduler;
import model.Task;
import model.BugFix;
import model.FeatureRequest;
import exceptions.EmptyQueueException;
import exceptions.InvalidTaskDataException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SchedulerTest {

    @Test
    void shouldCompleteHighPriorityTaskFirst() throws InvalidTaskDataException {
        // Given
        Scheduler scheduler = new Scheduler(10);
        
        scheduler.addTask(new FeatureRequest("Low Priority Feature", 1));
        scheduler.addTask(new FeatureRequest("CRITICAL Feature", 99)); 
        scheduler.addTask(new FeatureRequest("Medium Priority Feature", 50));

        // When
        Task first = scheduler.completeNextTask();
        Task second = scheduler.completeNextTask();
        Task third = scheduler.completeNextTask();

        // Then
        assertEquals(99, first.getEffectivePriority(), "First task should have the highest priority (99).");
        assertEquals(50, second.getEffectivePriority(), "Second task should have priority 50.");
        assertEquals(1, third.getEffectivePriority(), "Third task should have the lowest priority (1).");
        
        assertThrows(EmptyQueueException.class, () -> scheduler.completeNextTask(), "Queue should be empty and throw EmptyQueueException.");
    }

    @Test
    void bugFixShouldBePrioritizedOverFeatureRequest() throws InvalidTaskDataException {
        // Given
        Scheduler scheduler = new Scheduler(10);
        
        scheduler.addTask(new FeatureRequest("High Feature Prio 80", 80)); 
        scheduler.addTask(new BugFix("Medium Bug Prio 70", 70)); 
        
        // Effective priorities: Feature=80, BugFix=70+20=90

        // When
        Task first = scheduler.completeNextTask();
        Task second = scheduler.completeNextTask();

        // Then
        assertEquals("BUG FIX", first.getTypeLabel(), "BugFix should come first due to priority boost.");
        assertEquals(90, first.getEffectivePriority(), "BugFix should have effective priority 90.");
        
        assertEquals("FEATURE REQUEST", second.getTypeLabel());
        assertEquals(80, second.getEffectivePriority());
    }

    @Test
    void peekShouldNotRemoveTaskAndReturnHighestPriority() throws InvalidTaskDataException {
        // Given
        Scheduler scheduler = new Scheduler(10);
        scheduler.addTask(new FeatureRequest("Medium Prio", 50));
        scheduler.addTask(new FeatureRequest("Highest Prio", 100));

        // When
        Task peekedTask = scheduler.peekNextTask();

        // Then
        assertNotNull(peekedTask);
        assertEquals(100, peekedTask.getEffectivePriority(), "Peek should return the highest priority (100).");
        
        // When
        Task secondPeek = scheduler.peekNextTask();
        
        // Then
        assertEquals(peekedTask.getTitle(), secondPeek.getTitle(), "Peeking twice should return the same task.");
        
        // When
        Task completedTask = scheduler.completeNextTask();
        
        // Then
        assertEquals(peekedTask.getTitle(), completedTask.getTitle(), "Completed task should match the peeked task.");
        
        // When
        scheduler.completeNextTask();
        
        // Then
        assertNull(scheduler.peekNextTask(), "After removing all tasks, peek should return null.");
    }
}