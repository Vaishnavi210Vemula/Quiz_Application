package backend;
import java.util.*;
import java.io.BufferedWriter; 
import java.io.FileWriter; 
import java.io.IOException; 
 
class Student  
{ 
    public void startQuiz(Scanner scanner) { 
        String password = "student"; 
        System.out.print("Enter Password: "); 
        String inputPassword = scanner.next(); 
 
        if (password.equals(inputPassword)) { 
            QuizDAO quizDAO = new QuizDAO(); 
            List<QuizQuestion> quizQuestions = quizDAO.getQuizQuestions(); 
            int timeLimitSeconds = 60; // 2 minutes 
 
            System.out.print("Enter your ID: "); 
            String id = scanner.next(); 
 
            System.out.print("Enter your username: "); 
            String username = scanner.next(); 
 
            QuizResultDAO quizResultDAO = new QuizResultDAO(); // Update the variable name here 
            QuizResult previousResult = quizResultDAO.getQuizResultById(id); // Update the variable name here 
 
            if (previousResult != null) { 
                System.out.println("You have already attempted the quiz."); 
                System.out.println("Your previous score: " + previousResult.getScore()); 
         // Exit the application since the user has already attempted the quiz. 
            } 
 
            int score = 0; 
            for (QuizQuestion question : quizQuestions) { 
                System.out.println(question.getQuestionText()); 
                List<String> options = question.getOptions(); 
                for (int i = 0; i < options.size(); i++) { 
                    System.out.println((i + 1) + ". " + options.get(i)); 
                } 
                System.out.print("Enter your answer (1/2/3/4): "); 
                int userAnswerIndex = scanner.nextInt() - 1; 
                int correctAnswer = question.getCorrectOption(); 
                // Get the user's answer using the user's answer index 
                if (userAnswerIndex >= 0 && userAnswerIndex < options.size()) { 
                    if (userAnswerIndex + 1 == correctAnswer) { 
                        System.out.println("Correct!"); 
                        score++; 
                        TimeLimitThread timeLimitThread = new TimeLimitThread(timeLimitSeconds, id, username, score, quizQuestions, Thread.currentThread()); 
                        timeLimitThread.start(); 
                    } else { 
                        System.out.println("Wrong!"); 
                    } 
                    question.setAttempted(true); 
                    System.out.println(""); 
                } else { 
                    System.out.println("Invalid answer choice. Please choose a valid option (1/2/3/4)."); 
                } 
            } 
 
            TimeLimitThread timeLimitThread = new TimeLimitThread(timeLimitSeconds, id, username, 
score, quizQuestions, Thread.currentThread()); 
            timeLimitThread.start(); 
            QuizResult quizResult = new QuizResult(id, username, score); 
            quizResultDAO.saveQuizResult(quizResult); 
            System.out.printf("Your score is: %d/%d", score, quizQuestions.size()); 
            System.out.println(""); 
            System.out.println("Quiz Summary:"); 
            System.out.println("------------"); 
            for (QuizQuestion question : quizQuestions) { 
                System.out.println(question.getQuestionText()); 
                System.out.println("Correct Option: " + question.getCorrectOption() + "\n"); 
            } 
            createCertificate(id, username, score, quizQuestions.size()); 
            timeLimitThread.interrupt(); 
        } else { 
            System.out.println("Invalid password. Access denied."); 
        } 
    } 
 
    public void createCertificate(String studentId, String username, int score, int totalQuestions)  
    { 
        QuizDAO quizDAO = new QuizDAO(); 
        List<QuizQuestion> quizQuestions = quizDAO.getQuizQuestions(); 
        double percentile = (double) score / totalQuestions * 100; 
 
        // Calculate grade based on percentile 
        char grade; 
        if (percentile >= 90) { 
            grade = 'A'; 
        } else if (percentile >= 80) { 
            grade = 'B'; 
        } else if (percentile >= 70) { 
            grade = 'C'; 
        } else if (percentile >= 60) { 
            grade = 'D'; 
        } else { 
            grade = 'F'; 
        } 
        StringBuilder certificateContent = new StringBuilder(); 
        
certificateContent.append("===================================================\n"); 
certificateContent.append("\t\t\t  Congratulations!\n"); 
certificateContent.append("===================================================\n"); 
certificateContent.append("Student ID: ").append(studentId).append("\n"); 
certificateContent.append("Name: ").append(username).append("\n"); 
certificateContent.append("Marks Scored: ").append(score).append("/").append(totalQuestions).append("\n"); 
certificateContent.append(String.format("Percentile: %.2f%%\n", percentile)); 
certificateContent.append("Grade: ").append(grade).append("\n"); 
certificateContent.append("===================================================\n\n"); 
// Write the content to a text file 
String fileName = "QuizCertificate_" + studentId + ".txt"; 
try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) { 
writer.write(certificateContent.toString()); 
System.out.println("Certificate generated and saved as: " + fileName); 
}  
catch (IOException e) { 
e.printStackTrace(); 
} 
} 
} 
