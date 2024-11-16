package UI;

import Common.UserInterface;

/**
 * The {@code LoginPage} class provides methods for user login functionality.
 * It extends {@link UserInterface} and includes methods to retrieve user ID
 * and password input from the console.
 */
public class LoginPage extends UserInterface {

    /**
     * Prompts the user to enter their user ID.
     *
     * @return the user ID entered by the user
     */
    public static String getUserId() {
        System.out.println("Please enter your user id");
        return scanner.nextLine(); // Read and return user ID input
    }

    /**
     * Prompts the user to enter their password.
     *
     * @return the password entered by the user
     */
    public static String getPassword() {
        System.out.println("Please enter your password");
        return scanner.nextLine(); // Read and return password input
    }
}