package Common;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The {@code UserInterface} class provides methods for user input handling,
 * including validation of integer and string inputs. It facilitates interaction
 * with the user through the console.
 */
public class UserInterface {
    protected static Scanner scanner = new Scanner(System.in);

    /**
     * Gets an integer input from the user and checks for input errors.
     * If an error occurs, it returns a default value.
     *
     * @param defaultReturn the value to return in case of an input error
     * @return the integer input from the user or the default value if an error occurs
     */
    protected static int getIntInput(int defaultReturn) {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Remove new line character left in the input buffer
            return choice;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid integer.");
            scanner.nextLine(); // Clear the invalid input
            return defaultReturn;
        }
    }

    /**
     * Gets a numeric string input from the user.
     * This method continues to prompt until a valid numeric string is entered.
     *
     * @return a string containing only numeric characters
     */
    protected static String getNumericString() {
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.matches("\\d+")) {
                return input; // Return valid numeric string
            }
            System.out.println("Invalid input. Please enter only numeric values.");
        }
    }

    /**
     * Prompts the user for a positive integer value.
     *
     * @param prompt the message to display to the user
     * @return the positive integer entered by the user, or -1 if invalid
     */
    protected int getValidatedInt(String prompt) {
        System.out.println(prompt);
        int value = getIntInput(0);
        return value > 0 ? value : -1; // Return -1 if not positive
    }

    /**
     * Prompts the user for a string input and checks for null or empty values.
     *
     * @param prompt the message to display to the user
     * @return the trimmed string entered by the user, or null if empty
     */
    protected String getValidatedString(String prompt) {
        System.out.println(prompt);
        String value = scanner.nextLine().trim();
        return value.isEmpty() ? null : value; // Return null if empty
    }

    /**
     * Displays a success message to the console.
     *
     * @param message the success message to display
     */
    public static void displaySuccess(String message) {
        System.out.println("SUCCESS: " + message);
        CustomTimer.pause(1500); // Pause to view before clearing console
    }

    /**
     * Displays an error message to the console.
     *
     * @param message the error message to display
     */
    public static void displayError(String message) {
        System.err.println("ERROR: " + message + "<<<<<<<<<");
        CustomTimer.pause(2000); // Pause to view before clearing console
    }

    /**
     * Pauses execution and waits for the user to press ENTER before returning to menu.
     */
    protected void pauseAndView() {
        System.out.println();
        System.out.println("Press ENTER to return to menu");
        scanner.nextLine();
    }
}