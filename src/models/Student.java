package models;

public class Student extends User {
    
    public Student(String userId, String name) {
        super(userId, name);
    }

    @Override
    public void displayDashboard() {
        System.out.println("\n=== Student Dashboard ===");
        System.out.println("Welcome, " + name + " (ID: " + userId + ")");
        System.out.println("1. Take Diagnostic Assessment");
        System.out.println("2. View Gap Analysis Report");
        System.out.println("3. Logout");
    }
}