package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class ReportGenerator {
    
    public static void generateReport(String studentId, int score, int total, String weakTopic) {
        String filename = "data/" + studentId + "_DiagnosticReport.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("=======================================\n");
            writer.write("      AKSHARSETU DIAGNOSTIC REPORT     \n");
            writer.write("=======================================\n");
            writer.write("Student ID: " + studentId + "\n");
            writer.write("Date: " + LocalDateTime.now() + "\n");
            writer.write("---------------------------------------\n");
            writer.write("Total Score: " + score + " / " + total + "\n");
            writer.write("Identified Learning Gap: " + weakTopic + "\n");
            writer.write("=======================================\n");
            writer.write("Recommendation: Please review the modules related to [" + weakTopic + "].\n");
            
            System.out.println("--> Local report generated: " + filename);
        } catch (IOException e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
    }
}