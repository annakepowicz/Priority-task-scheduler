package logic;

import structures.Array3Heap;
import structures.MaxHeap;
import model.Task;
import exceptions.EmptyQueueException;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Scheduler {
    private MaxHeap<Task> taskQueue;

    public Scheduler(int capacity) {
        Comparator<Task> priorityComparator = (t1, t2) -> 
            Integer.compare(t1.getEffectivePriority(), t2.getEffectivePriority());
            
        this.taskQueue = new Array3Heap<>(capacity, priorityComparator);
    }

    public void addTask(Task task) {
        taskQueue.add(task);
        System.out.println(">> Successfully added: " + task.getTypeLabel());
    }

    public Task completeNextTask() {
        try {
            return taskQueue.maximum();
        } catch (NoSuchElementException e) {
            throw new EmptyQueueException();
        }
    }


    public Task peekNextTask() {
        try {
            if (taskQueue instanceof Array3Heap) {
                return ((Array3Heap<Task>) taskQueue).nthMaximum(1);
            }
            throw new UnsupportedOperationException("Peek operation not supported for this heap type.");
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}