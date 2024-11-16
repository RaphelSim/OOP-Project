import Common.ClearOutput;
import Common.CustomTimer;
import Common.UserInterface;
import Controllers.LoginManager;

/**
 * The {@code HomePage} class serves as the entry point for the Hospital X System application.
 * It handles the display of welcome messages, user login, and application termination.
 */
public class HomePage extends UserInterface {
    /**
     * The main method that runs the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        while (true) {
            ClearOutput.clearOutput();
            LoginManager loginManager = new LoginManager();
            if (displayWelcomeText() == 'q') {
                displayFarewellText();
                return; // Exit the application
            }
            ClearOutput.clearOutput();
            loginManager.logIn(); // Proceed to login
        }
    }

    /**
     * Displays a welcome message with a typing effect and a pulsing animation.
     *
     * @return a character input by the user; 'q' to quit, or any other character to proceed
     */
    public static char displayWelcomeText() {
        String message = "Welcome to Hospital X System";
        
        // Typing effect for the initial message display
        for (char c : message.toCharArray()) {
            System.out.print(c);
            CustomTimer.pause(50); // Delay for typing effect
        }
        
        CustomTimer.pause(500); // Pause after initial display
        
        // Loop to create a pulsing effect
        for (int i = 0; i < 7; i++) {
            ClearOutput.clearOutput();
            System.out.println(); // Move the text a bit down the console
            
            // Display message with pulsing effect
            if (i % 2 == 0) {
                System.out.println("*************************************************");
                System.out.println("**           Welcome to Hospital X System      **");
                System.out.println("*************************************************");
            } else {
                System.out.println("                                                 ");
                System.out.println("           Welcome to Hospital X System          ");
                System.out.println("                                                 ");
            }
            
            CustomTimer.pause(150); // Delay for pulsing effect
        }
        
        // Final display
        ClearOutput.clearOutput();
        System.out.println();
        System.out.println("*************************************************");
        System.out.println("**           Welcome to Hospital X System      **");
        System.out.println("*************************************************");
        System.out.println("\n       Enter any character to proceed to login     ");
        System.out.println("\n            Enter 'q' to quit the app              ");
        
        String choice = scanner.nextLine(); // Read user input
        if (choice.isEmpty()) return 'a'; // Default return if no input
        return choice.charAt(0); // Return first character of input
    }

    /**
     * Displays a farewell message when the application is closing.
     */
    public static void displayFarewellText() {
        ClearOutput.clearOutput();
        String message = "Thank you for using Hospital X System, see you next time!";
        
        // Typing effect for the farewell message display
        for (char c : message.toCharArray()) {
            System.out.print(c);
            CustomTimer.pause(30); // Delay for typing effect
        }
        
        CustomTimer.pause(500); // Pause after displaying farewell message
    }
}