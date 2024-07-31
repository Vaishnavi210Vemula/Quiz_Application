package backend;
import java.util.*; 
public class QuizApplication { 
    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in); 
        while(true){ 
            System.out.println(""); 
            System.out.println(""); 
            System.out.println("***************************"); 
            System.out.println("WELCOME TO QUIZ APPLICATION"); 
            System.out.println("***************************"); 
            System.out.println(""); 
            System.out.println("================================"); 
            System.out.println("Are you a Teacher or a Student?"); 
            System.out.println("================================"); 
            System.out.println("---------------------------------"); 
            System.out.println("1. Teacher"); 
            System.out.println("2. Student"); 
            System.out.println("3. Exit Quiz Application"); 
            System.out.println("---------------------------------"); 
            System.out.print("Enter your choice (1/2/3): "); 
            int choice = scanner.nextInt(); 
            if (choice == 1) { 
                System.out.println("Enter the password:"); 
                String password=scanner.next(); 
                Teacher teacher = new Teacher(); 
                if (password.equals("root"))  
                { 
                    while(true) 
                    { 
                        System.out.println(""); 
                        System.out.println("Choose an option:"); 
                        System.out.println("1. Add a quiz question"); 
                        System.out.println("2. Update a quiz question"); 
                        System.out.println("3. Delete a quiz question"); 
                        System.out.println("4. See student marks"); 
                        System.out.println("5. All Students Progress"); 
                        System.out.println("6.Exit Teacher Field"); 
                        System.out.print("Enter your choice (1/2/3/4/5/6): "); 
                        int option = scanner.nextInt(); 
                        switch (option) 
                        { 
                            case 1: 
                                scanner.nextLine(); // Consume the newline left by nextInt() 
                                System.out.print("Enter the question text: "); 
                                String questionText = scanner.nextLine(); 
                                List<String> options = new ArrayList<>(); 
                                for (int i = 1; i <= 4; i++) { 
                                    System.out.print("Enter option " + i + ": "); 
                                    String optionText = scanner.nextLine(); 
                                    options.add(optionText); 
                                } 
                                System.out.print("Enter the correct option index (1/2/3/4): "); 
                                int correctOption = scanner.nextInt(); 
                                QuizQuestion newQuestion = new QuizQuestion(questionText, options, 
correctOption); 
                                teacher.addQuizQuestion(newQuestion); 
                                break; 
 
                            case 2: 
                                System.out.print("Enter the question ID to update: "); 
                                int questionIdToUpdate = scanner.nextInt(); 
                                scanner.nextLine(); // Consume the newline left by nextInt() 
                                System.out.print("Enter the updated question text: "); 
                                String updatedQuestionText = scanner.nextLine(); 
                                List<String> updatedOptions = new ArrayList<>(); 
                                for (int i = 1; i <= 4; i++)  
                                { 
                                    System.out.print("Enter updated option " + i + ": "); 
                                    String updatedOptionText = scanner.nextLine(); 
                                    updatedOptions.add(updatedOptionText); 
                                } 
                                System.out.print("Enter the updated correct option index (1/2/3/4): "); 
                                int updatedCorrectOption = scanner.nextInt(); 
                                QuizQuestion updatedQuestion = new QuizQuestion(questionIdToUpdate, 
updatedQuestionText, updatedOptions, updatedCorrectOption); 
                                teacher.updateQuizQuestion(questionIdToUpdate,updatedQuestion); 
                                break; 
 
                            case 3: 
                                System.out.print("Enter the question ID to delete: "); 
                                int questionIdToDelete = scanner.nextInt(); 
                                teacher.deleteQuizQuestion(questionIdToDelete); 
                                break; 
 
                            case 4: 
                                System.out.print("Enter the student's ID to see marks: "); 
                                String studentId = scanner.next(); 
                                teacher.getStudentMarksById(studentId); 
                                break; 
                            case 5: 
                                System.out.println("Displaying all Students marks who attempted"); 
                                teacher.printStudentsOrderByMarks(); 
                                break; 
                            case 6: 
                                System.out.println("Exiting... Goodbye!"); 
                                break; 
 
                            default: 
                                System.out.println("Invalid option. Please enter a valid choice (1/2/3/4)."); 
                                break; 
                        } 
                        if (option == 6) { 
                            break; // Exit the teacher menu loop and go back to the main loop 
                        } 
                    } 
                }  
                else { 
                    System.out.println("Invalid password. Access denied."); 
                } 
            }  
            else if (choice == 2)  
            { 
                Student student = new Student(); 
                student.startQuiz(scanner); 
                
            } 
            else if(choice ==3){ 
                System.out.println("Exiting"); 
                System.exit(0); 
            }  
            else{ 
                System.out.println("Invalid choice!!"); 
            } 
             
        } 
    } 
} 
