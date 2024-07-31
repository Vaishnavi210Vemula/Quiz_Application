package backend;
import java.sql.*;

class QuizResultDAO { 
    public void saveQuizResult(QuizResult quizResult) { 
    try{ 
    Connection conn = DatabaseConnection.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO quiz_results (id,Name,Score) VALUES (?,?,?)"); 
                stmt.setString(1, quizResult.getId()); 
                stmt.setString(2, quizResult.getUsername()); 
                stmt.setInt(3, quizResult.getScore()); 
                stmt.executeUpdate(); 
            } catch (SQLException e) { 
                e.printStackTrace(); 
            } 
        } 
        public QuizResult getQuizResultById(String id)  
        { 
            try { 
                Connection conn = DatabaseConnection.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM quiz_results WHERE id = ?"); 
                stmt.setString(1, id); 
                ResultSet rs = stmt.executeQuery(); 
                if (rs.next()) { 
                    String username = rs.getString("Name"); 
                    int score = rs.getInt("Score"); 
                    return new QuizResult(id, username, score); 
                } 
            }  
            catch (SQLException e) { 
                e.printStackTrace(); 
            } 
     
            return null; // Return null if no previous result is found 
        } 
    }