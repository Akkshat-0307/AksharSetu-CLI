import db.DatabaseManager;
import services.AssessmentEngine;
import services.BatchProcessor;
import utils.InputValidator;
import models.Student;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initializing Database...");
        DatabaseManager.initializeDatabase();
        
        boolean running = true;
        AssessmentEngine engine = new AssessmentEngine();

        while (running) {
            System.out.println("\n=== AksharSetu Terminal ===");
            System.out.println("1. Login as Student");
            System.out.println("2. Mentor Login (Run Concurrent Batch Processing)");
            System.out.println("3. Exit");
            
            int choice = InputValidator.getValidInteger("Select an option: ");

            if (choice == 1) {
                String name = InputValidator.getValidString("Enter your name: ");
                Student currentStudent = new Student("STU" + System.currentTimeMillis(), name);
                
                currentStudent.displayDashboard();
                int studentChoice = InputValidator.getValidInteger("Select: ");
                
                if (studentChoice == 1) {
                    engine.startAssessment(currentStudent.getUserId());
                }
            } else if (choice == 2) {
                System.out.println("\n--- Initiating Multi-threaded Load Test ---");
                // Spawn 5 separate threads to simulate concurrent processing
                for (int i = 1; i <= 5; i++) {
                    Thread t = new Thread(new BatchProcessor("SIM_STU_" + i));
                    t.start();
                }
                // Small pause to let threads output before printing the menu again
                try { Thread.sleep(2500); } catch (InterruptedException ignored) {}
            } else if (choice == 3) {
                System.out.println("Exiting system. Goodbye!");
                running = false;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }
}