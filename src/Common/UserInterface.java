package Common;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    protected static Scanner scanner = new Scanner(System.in);

    // get an integer input and check for any error, if yes then return the
    // defaultreturn parameter
    protected static int getIntInput(int defaultReturn) {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // remove new line character left in the input buffer
            return choice;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid integer.");
            scanner.nextLine();
            return defaultReturn;
        }
    }

    // getting a numeric string for id purposes
    protected static String getNumericString() {
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.matches("\\d+"))
                return input;
            System.out.println("Invalid input. Please enter only numeric values.");
        }
    }

    // getting a positive integer
    protected int getValidatedInt(String prompt) {
        System.out.println(prompt);
        int value = getIntInput(0);
        return value > 0 ? value : -1;
    }

    // getting a string which checks for null values
    protected String getValidatedString(String prompt) {
        System.out.println(prompt);
        String value = scanner.nextLine().trim();
        return value.isEmpty() ? null : value;
    }

    public static void displaySuccess(String message) {
        System.out.println("SUCCESS: " + message);
        CustomTimer.pause(1500); // pause to view before clearing console
    }

    public static void displayError(String message) {
        System.err.println("ERROR: " + message + "<<<<<<<<<");
        CustomTimer.pause(2000); // pause to view before clearing console
    }

    protected void pauseAndView() {
        System.out.println();
        System.out.println("Press ENTER to return to menu");
        scanner.nextLine();
    }
}
