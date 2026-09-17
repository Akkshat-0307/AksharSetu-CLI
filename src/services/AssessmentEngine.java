package services;

import models.Question;
import utils.InputValidator;
import db.DatabaseManager;
import utils.ReportGenerator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssessmentEngine {
    private List<Question> questionBank;

    public AssessmentEngine() {
        questionBank = new ArrayList<>();
        // Seed some dummy questions (Change these for your final submission)
        questionBank.add(new Question("What is the time complexity of binary search?", 
                new String[]{"O(n)", "O(log n)", "O(n^2)"}, 2, "Data Structures"));
        questionBank.add(new Question("Which OOP principle hides internal details?", 
                new String[]{"Inheritance", "Polymorphism", "Encapsulation"}, 3, "OOP Concepts"));
        questionBank.add(new Question("Which collection stores unique elements?", 
                new String[]{"List", "Set", "Map"}, 2, "Java Collections"));
    }

    public void startAssessment(String studentId) {
        System.out.println("\n--- Starting Diagnostic Assessment ---");
        int score = 0;
        Map<String, Integer> topicMistakes = new HashMap<>();

        for (Question q : questionBank) {
            q.displayQuestion();
            int answer = InputValidator.getValidInteger("Your answer (1-" + q.getOptionsLength() + "): ");
            
            if (q.isCorrect(answer)) {
                score++;
            } else {
                // Track which topic the user got wrong
                topicMistakes.put(q.getTopic(), topicMistakes.getOrDefault(q.getTopic(), 0) + 1);
            }
        }

        System.out.println("\nAssessment Complete! Score: " + score + "/" + questionBank.size());
        
        String weakTopic = analyzeWeakness(topicMistakes);
        System.out.println("Identified Learning Gap: " + weakTopic);
        DatabaseManager.saveAssessmentResult(studentId, score, weakTopic);
        ReportGenerator.generateReport(studentId, score, questionBank.size(), weakTopic);
        // Next step: Save this result to the JDBC database
    }

    private String analyzeWeakness(Map<String, Integer> mistakes) {
        if (mistakes.isEmpty()) return "None - Excellent Work!";
        
        String weakest = "";
        int maxMistakes = 0;
        for (Map.Entry<String, Integer> entry : mistakes.entrySet()) {
            if (entry.getValue() > maxMistakes) {
                maxMistakes = entry.getValue();
                weakest = entry.getKey();
            }
        }
        return weakest;
    }
}