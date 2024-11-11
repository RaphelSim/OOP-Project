import Common.ClearOutput;
import Common.CustomTimer;
import Common.UserInterface;
import Controllers.LoginManager;

public class HomePage extends UserInterface {
    public static void main(String[] args) {
        while(true)
        {
        ClearOutput.clearOutput();
        LoginManager loginManager = new LoginManager();
        if(displayWelcomeText()=='q'){
            displayFarewellText();
            return;
        }
        ClearOutput.clearOutput();
        loginManager.logIn();
        }
    }

    public static char displayWelcomeText(){
        String message = "Welcome to Hospital X System";
        
        // Typing effect for the initial message display
        for (char c : message.toCharArray()) {
            System.out.print(c);
            CustomTimer.pause(50); // delay for typing effect
        }
        
        CustomTimer.pause(500); // pause after initial display
        
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
            
            CustomTimer.pause(150); // delay for pulsing effect
        }
        
        // Final display
        ClearOutput.clearOutput();
        System.out.println();
        System.out.println("*************************************************");
        System.out.println("**           Welcome to Hospital X System      **");
        System.out.println("*************************************************");
        System.out.println("\n       Enter any character to proceed to login     ");
        System.out.println("\n            Enter 'q' to quit the app              ");
        String choice = scanner.nextLine();
        if(choice.isEmpty()) return 'a';
        return choice.charAt(0);
    }

    public static void displayFarewellText(){
        ClearOutput.clearOutput();
        String message = "Thank you for using Hospital X System, see you next time!";
        // Typing effect for the initial message display
        for (char c : message.toCharArray()) {
            System.out.print(c);
            CustomTimer.pause(30); // delay for typing effect
        }
        
        CustomTimer.pause(500);
    }
}
