import java.util.*;
class User {
    String username;
    String password;
    String profile;
    User(String username, String password) {
        this.username = username;
        this.password = password;
        this.profile = "Default Profile";
    }
    
    boolean authenticate(String user, String pass) {
        return username.equals(user) && password.equals(pass);
    }
    
    void updateProfile(String newProfile) {
        this.profile = newProfile;
        System.out.println("Profile updated successfully!");
    }
    
    void updatePassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Password updated successfully!");
    }
}

class MCQExam {
    private Map<String, String> questions;
    private Map<String, String> userAnswers;
    private int timer;
    
    MCQExam() {
        questions = new LinkedHashMap<>();
        userAnswers = new HashMap<>();
        timer = 30; // 30 seconds timer
        
        questions.put("What is the capital of France?", "A. Paris  B. London  C. Berlin  D. Madrid");
        questions.put("Who invented Java?", "A. Dennis Ritchie  B. Bjarne Stroustrup  C. James Gosling  D. Guido van Rossum");
        questions.put("Which keyword is used to define a class in Java?", "A. class  B. struct  C. def  D. function");
    }
    
    void startExam(Scanner scanner) {
        System.out.println("Your exam has started! You have " + timer + " seconds.");
        TimerTask autoSubmit = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up! Auto-submitting...");
                submitExam();
            }
        };
        Timer t = new Timer();
        t.schedule(autoSubmit, timer * 1000);
        
        for (String question : questions.keySet()) {
            System.out.println(question);
            System.out.println(questions.get(question));
            System.out.print("Your Answer (A/B/C/D): ");
            String answer = scanner.nextLine().toUpperCase();
            userAnswers.put(question, answer);
        }
        t.cancel();
        submitExam();
    }
    
    void submitExam() {
        System.out.println("Exam submitted! Here are your answers:");
        for (Map.Entry<String, String> entry : userAnswers.entrySet()) {
            System.out.println(entry.getKey() + " -> Your Answer: " + entry.getValue());
        }
    }
}

public class OnlineMCQSystem {
    static Scanner scanner = new Scanner(System.in);
    static User user = new User("admin", "password");
    static MCQExam exam = new MCQExam();
    
    public static void main(String[] args) {
        System.out.println("Welcome to the Online MCQ Exam System");
        if (!login()) {
            System.out.println("Authentication failed! Exiting...");
            scanner.close();
            return;
        }
        
        int choice;
        do {
            System.out.println("\n1. Update Profile\n2. Change Password\n3. Start MCQ Exam\n4. Logout");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter new profile info: ");
                    user.updateProfile(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter new password: ");
                    user.updatePassword(scanner.nextLine());
                    break;
                case 3:
                    exam.startExam(scanner);
                    break;
                case 4:
                    System.out.println("Logging out... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 4);
        
        scanner.close();
    }
    
    static boolean login() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        if (user.authenticate(username, password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid credentials. Exiting...");
            return false;
        }
    }
}