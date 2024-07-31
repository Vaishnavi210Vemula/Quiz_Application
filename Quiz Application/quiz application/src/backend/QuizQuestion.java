package backend;
import java.util.*; 
class QuizQuestion { 
private int question_id; 
private String question_text; 
private List<String> options; 
private int correct_option; 
private volatile boolean attempted; 
public boolean isAttempted() { 
return attempted; 
} 
// Setter for attempted 
public void setAttempted(boolean attempted) { 
this.attempted = attempted; 
} 
// Constructor without questionId 
public QuizQuestion(String question_text, List<String> options, int correct_option) { 
this.question_text = question_text; 
this.options = options; 
this.correct_option = correct_option; 
} 
// Constructor with questionId 
public QuizQuestion(int question_id, String question_text, List<String> options, int correct_option) { 
this.question_id = question_id; 
this.question_text = question_text; 
this.options = options; 
this.correct_option = correct_option; 
this.attempted = false; 
} 
// Getter for questionId 
public int getQuestionId() { 
return question_id; 
} 
// Setter for questionId 
public void setQuestionId(int question_id) { 
this.question_id = question_id; 
} 
// Getter for questionText 
public String getQuestionText() { 
return question_text; 
} 
// Setter for questionText 
public void setQuestionText(String question_text) { 
this.question_text = question_text; 
} 
// Getter for options 
public List<String> getOptions() { 
return options; 
} 
// Setter for options 
public void setOptions(List<String> options) { 
this.options = options; 
} 
// Getter for correctOption 
public int getCorrectOption() { 
return correct_option; 
} 
// Setter for correctOption 
public void setCorrectOption(int correct_option) { 
this.correct_option = correct_option; 
} 
}