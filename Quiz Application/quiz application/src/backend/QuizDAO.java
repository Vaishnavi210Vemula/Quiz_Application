package backend;
import java.sql.*; 
import java.util.*; 
class QuizDAO { 
    public List<QuizQuestion> getQuizQuestions() { 
        List<QuizQuestion> quizQuestions = new ArrayList<>(); 
 
        try{ 
            Connection conn = DatabaseConnection.getConnection(); 
            Statement stmt = conn.createStatement(); 
            String query = "SELECT id, question,option1, option2, option3, option4, correct_option " + 
                           "FROM quiz_questions"; 
 
            ResultSet rs = stmt.executeQuery(query); 
            while (rs.next()) { 
                int question_id = rs.getInt("id"); 
                String question_text = rs.getString("question"); 
                List<String> options = new ArrayList<>(); 
                options.add(rs.getString("option1")); 
                options.add(rs.getString("option2")); 
                options.add(rs.getString("option3")); 
                options.add(rs.getString("option4")); 
                int correct_option = rs.getInt("correct_option"); 
 
                QuizQuestion quizQuestion = new QuizQuestion(question_id, question_text, options, 
correct_option); 
                quizQuestions.add(quizQuestion); 
            } 
            Collections.shuffle(quizQuestions); 
        }  
        catch (SQLException e) { 
            e.printStackTrace(); 
        } 
 
        return quizQuestions; 
    } 
} 
