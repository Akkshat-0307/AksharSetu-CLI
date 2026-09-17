package db;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    // Path to the SQLite database file
    private static final String URL = "jdbc:sqlite:data/aksharsetu.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
        return conn;
    }

    public static void initializeDatabase() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS users ("
                + "id TEXT PRIMARY KEY, "
                + "name TEXT NOT NULL, "
                + "role TEXT NOT NULL);";

        String createAssessmentsTable = "CREATE TABLE IF NOT EXISTS assessments ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "student_id TEXT, "
                + "score INTEGER, "
                + "weak_topic TEXT, "
                + "FOREIGN KEY(student_id) REFERENCES users(id));";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            
            if (conn != null) {
                stmt.execute(createUsersTable);
                stmt.execute(createAssessmentsTable);
                System.out.println("Database and tables initialized successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error initializing tables: " + e.getMessage());
        }
    }
    public static void saveAssessmentResult(String studentId, int score, String weakTopic) {
        // First, ensure the user exists in the DB (Dummy user for now)
        String insertUser = "INSERT OR IGNORE INTO users(id, name, role) VALUES(?, 'StudentName', 'Student')";
        String insertScore = "INSERT INTO assessments(student_id, score, weak_topic) VALUES(?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmtUser = conn.prepareStatement(insertUser);
             PreparedStatement pstmtScore = conn.prepareStatement(insertScore)) {
            
            // Insert user
            pstmtUser.setString(1, studentId);
            pstmtUser.executeUpdate();

            // Insert score
            pstmtScore.setString(1, studentId);
            pstmtScore.setInt(2, score);
            pstmtScore.setString(3, weakTopic);
            pstmtScore.executeUpdate();
            
            System.out.println("--> Result successfully saved to database.");
            
        } catch (SQLException e) {
            System.out.println("Database error while saving result: " + e.getMessage());
        }
    }
}