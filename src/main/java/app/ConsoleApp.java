package app;

import logic.Scheduler;
import model.*;
import exceptions.SchedulerException;
import exceptions.EmptyQueueException;
import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler(15);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== PRIORITY JOB SCHEDULER (CUSTOM HEAP) ==="); 

        while (running) {
            System.out.println("\n-----------------------------------------");
            System.out.println("1. Add BugFix");
            System.out.println("2. Add Feature Request");
            System.out.println("3. Show Next Task");
            System.out.println("4. Complete Task");
            System.out.println("5. Exit");
            System.out.println("-----------------------------------------");
            System.out.print("Select option > "); 

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1": // Add BugFix
                        handleAddBugFix(scanner, scheduler);
                        break;

                    case "2": // Add Feature Request
                        handleAddFeature(scanner, scheduler);
                        break;

                    case "3": // Show Next Task (Peek)
                        Task previewTask = scheduler.peekNextTask();
                        if (previewTask != null) {
                            System.out.println("\nNEXT TASK IN QUEUE:"); 
                            System.out.println(previewTask);
                            System.out.println("(Task is still awaiting completion)"); 
                        } else {
                            System.out.println("\nINFO: Queue is empty. Time for a break!");
                        }
                        break;

                    case "4": // Complete Task (Pop)
                        Task completedTask = scheduler.completeNextTask();
                        System.out.println("\nSUCCESS: Task completed and archived!"); 
                        System.out.println("Completed: " + completedTask);
                        break;

                    case "5":
                        running = false;
                        System.out.println("Shutting down the scheduler..."); 
                        break;

                    default:
                        System.out.println("Unknown option. Select 1-5.");
                }
            } catch (EmptyQueueException e) {
                System.out.println("\nERROR: " + e.getMessage()); 
            } catch (Exception e) {
                System.out.println("\nCRITICAL ERROR: " + e.getMessage());
            }
        }
    }

    private static void handleAddBugFix(Scanner scanner, Scheduler scheduler) {
        try {
            System.out.print("Bug description: "); 
            String title = scanner.nextLine();
            System.out.print("Base Priority (0-100): ");
            int prio = Integer.parseInt(scanner.nextLine());
            
            scheduler.addTask(new BugFix(title, prio));
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Priority must be a number!"); 
        } catch (SchedulerException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }

    private static void handleAddFeature(Scanner scanner, Scheduler scheduler) {
        try {
            System.out.print("Feature name: "); 
            String title = scanner.nextLine();
            System.out.print("Base Priority (0-100): "); 
            int prio = Integer.parseInt(scanner.nextLine());

            scheduler.addTask(new FeatureRequest(title, prio));

        } catch (NumberFormatException e) {
            System.out.println("Error: Priority must be a number!");
        } catch (SchedulerException e) {
            System.out.println("Validation Error: " + e.getMessage()); 
        }
    }
}