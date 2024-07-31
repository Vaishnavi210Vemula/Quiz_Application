package backend;
import java.util.*; 
class TimeLimitThread extends Thread { 
private int timeLimitSeconds; 
private String studentId; 
private String username; 
private int score; 
private List<QuizQuestion> quizQuestions; 
private volatile Thread mainThread; 
public TimeLimitThread(int timeLimitSeconds, String studentId, String username, int score, 
List<QuizQuestion> quizQuestions, Thread mainThread) { 
this.timeLimitSeconds = timeLimitSeconds; 
this.studentId = studentId; 
this.username = username; 
this.score = score; 
this.quizQuestions = quizQuestions; 
this.mainThread = mainThread; 
} 
@Override 
public void run() { 
try { 
Thread.sleep(timeLimitSeconds * 800); 
System.out.println("\nTime's up! Quiz completed."); 
mainThread.interrupt(); 
printScoreAndExit(); 
} catch (InterruptedException e) { 
} 
} 
 
    private void printScoreAndExit() { 
        Student student = new Student(); 
        student.createCertificate(studentId, username, score, quizQuestions.size()); 
        System.out.printf("Score:%d/%d",score,quizQuestions.size()); 
        QuizResultDAO quizResultDAO = new QuizResultDAO(); 
        QuizResult quizResult = new QuizResult(studentId, username, score); 
        quizResultDAO.saveQuizResult(quizResult); 
        System.exit(0); 
    } 
} 
