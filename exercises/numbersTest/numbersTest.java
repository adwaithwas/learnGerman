package numbersTest;

import java.util.Random;
import java.util.Scanner;

// Main class to start the test
public class numbersTest {
    public static void main(String[] args) {
        // Create an instance of TestManager and start the test
        TestManager testManager = new TestManager();
        testManager.startTest();
    }
}

// Class to handle question generation
class QuestionGenerator {
    private final String[] germanNumbers = {"null", "eins", "zwei", "drei", "vier", "funf", "sechs", "sieben", "acht", "neun"};
    private final Random random = new Random();

    // Generate a random number
    public int generateRandomNumber() {
        return random.nextInt(10); // Random number between 0 and 9
    }

    // Get the German translation of the number
    public String getGermanTranslation(int number) {
        return germanNumbers[number];
    }
}

// Class to manage the test
class TestManager {
    private final QuestionGenerator questionGenerator = new QuestionGenerator();
    private final Scanner scanner = new Scanner(System.in);

    public void startTest() {
        System.out.println("Welcome to the Number to German Test!");
        System.out.println("You will be shown a number, and you must type its German translation.");
        System.out.println("Type 'quit' to exit at any time.\n");

        int score = 0; // Keep track of the score
        int totalQuestions = 5;

        for (int i = 0; i < totalQuestions; i++) {
            int randomNumber = questionGenerator.generateRandomNumber();
            boolean correct = false;

            while (!correct) {
                System.out.print("What is the German word for the number " + randomNumber + "? ");
                String userAnswer = scanner.nextLine().trim().toLowerCase();

                // Allow user to quit
                if (userAnswer.equals("quit")) {
                    System.out.println("You have chosen to quit the test. Goodbye!");
                    System.out.println("Your score: " + score + "/" + totalQuestions);
                    return;
                }

                // Check if the answer is correct
                if (userAnswer.equals(questionGenerator.getGermanTranslation(randomNumber))) {
                    System.out.println("Correct!");
                    score++;
                    correct = true; // Exit the loop
                } else {
                    System.out.println("Wrong! Try again.");
                }
            }
        }

        System.out.println("\nTest complete! Your score: " + score + "/" + totalQuestions);
    }
}

