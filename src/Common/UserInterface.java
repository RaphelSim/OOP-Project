package Common;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    protected static Scanner scanner = new Scanner(System.in);

    protected static int getIntInput(int defaultReturn){
        try{
            int choice = scanner.nextInt();
            scanner.nextLine(); //remove new line character left in the input buffer
            return choice;
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid integer.");
            scanner.nextLine();
            return defaultReturn;
        }
    }

    protected static String getNumericString(){
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.matches("\\d+")) return input;
            System.out.println("Invalid input. Please enter only numeric values.");
        }
    }

    protected static void displaySuccess(String message) {
        System.out.println(message);
    }

    protected static void displayError(String message) {
        System.err.println(message);
    }
} 
