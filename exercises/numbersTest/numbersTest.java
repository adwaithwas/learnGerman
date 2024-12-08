import java.util.Random;
import java.util.Scanner;

// Main class to start the test
public class numbersTest {
    public static void main(String[] args) {
        TestManager testManager = new TestManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose the test mode:");
        System.out.println("1. English to German");
        System.out.println("2. German to English");
        System.out.print("Enter your choice (1 or 2): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (choice == 1) {
            testManager.startEnglishToGermanTest();
        } else if (choice == 2) {
            testManager.startGermanToEnglishTest();
        } else {
            System.out.println("Invalid choice. Exiting program.");
        }
    }
}

// Class to handle question generation
class QuestionGenerator {
    private final String[] germanNumbers = {"null", "eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben", "acht", "neun"};
    private final String[] englishNumbers = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private final Random random = new Random();

    // Generate a random number
    public int generateRandomNumber() {
        return random.nextInt(10); // Random number between 0 and 9
    }

    // Get the German translation of a number
    public String getGermanTranslation(int number) {
        return germanNumbers[number];
    }

    // Get the English translation of a number
    public String getEnglishTranslation(int number) {
        return englishNumbers[number];
    }

    // Check if input matches German translation or valid alternative
    public boolean isCorrectGermanAnswer(int number, String userInput) {
        String correctGerman = germanNumbers[number];
        // Allow alternatives for special characters
        switch (number) {
            case 5: // fünf -> funf
                return userInput.equals(correctGerman) || userInput.equals("funf");
            case 6: // sechs -> sechz
                return userInput.equals(correctGerman) || userInput.equals("sechz");
            case 7: // sieben -> sieban
                return userInput.equals(correctGerman);
            default:
                return userInput.equals(correctGerman);
        }
    }
}

// Class to manage the test
class TestManager {
    private final QuestionGenerator questionGenerator = new QuestionGenerator();
    private final Scanner scanner = new Scanner(System.in);

    // Utility function to normalize user input
    private String normalizeInput(String input) {
        return input.replace("ü", "u")
                .replace("ö", "o")
                .replace("ä", "a")
                .replace("ß", "ss")
                .toLowerCase();
    }

    // Start the English-to-German test
    public void startEnglishToGermanTest() {
        System.out.println("English to German Test");
        System.out.println("Type 'quit' to exit at any time.\n");

        int score = 0;

        while (score < 5) {
            int randomNumber = questionGenerator.generateRandomNumber();
            boolean correct = false;

            while (!correct) {
                System.out.print("What is the German word for the number " + randomNumber + "? ");
                String userAnswer = normalizeInput(scanner.nextLine().trim());

                if (userAnswer.equals("quit")) {
                    System.out.println("You quit the test. Goodbye!");
                    System.out.println("Your score: " + score + "/5");
                    return;
                }

                if (questionGenerator.isCorrectGermanAnswer(randomNumber, userAnswer)) {
                    System.out.println("Correct!");
                    score++;
                    correct = true;
                } else {
                    System.out.println("Wrong! Try again.");
                }
            }
        }

        System.out.println("Test complete! Your score: 5/5");
    }

    // Start the German-to-English test
    public void startGermanToEnglishTest() {
        System.out.println("German to English Test");
        System.out.println("Type 'quit' to exit at any time.\n");

        int score = 0;

        while (score < 5) {
            int randomNumber = questionGenerator.generateRandomNumber();
            boolean correct = false;

            while (!correct) {
                System.out.print("What is the English word for the German word '" + questionGenerator.getGermanTranslation(randomNumber) + "'? ");
                String userAnswer = normalizeInput(scanner.nextLine().trim());

                if (userAnswer.equals("quit")) {
                    System.out.println("You quit the test. Goodbye!");
                    System.out.println("Your score: " + score + "/5");
                    return;
                }

                if (userAnswer.equals(questionGenerator.getEnglishTranslation(randomNumber))) {
                    System.out.println("Correct!");
                    score++;
                    correct = true;
                } else {
                    System.out.println("Wrong! Try again.");
                }
            }
        }

        System.out.println("Test complete! Your score: 5/5");
    }
}
