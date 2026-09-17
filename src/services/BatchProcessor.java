package services;

import db.DatabaseManager;

public class BatchProcessor implements Runnable {
    private String studentId;

    public BatchProcessor(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public void run() {
        System.out.println("Starting background processing for: " + studentId);
        try {
            // Simulate processing time
            Thread.sleep((long) (Math.random() * 2000));
            
            // Generate a random score and topic for the simulation
            int simulatedScore = (int) (Math.random() * 4);
            String[] topics = {"Data Structures", "OOP Concepts", "Java Collections"};
            String simulatedTopic = topics[(int) (Math.random() * topics.length)];
            
            // Save to database concurrently
            DatabaseManager.saveAssessmentResult(studentId, simulatedScore, simulatedTopic);
            
            System.out.println("Finished processing for: " + studentId);
        } catch (InterruptedException e) {
            System.out.println("Processing interrupted for " + studentId + ": " + e.getMessage());
        }
    }
}