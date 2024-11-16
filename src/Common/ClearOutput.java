package Common;

/**
 * The {@code ClearOutput} class provides a utility method for clearing the console output.
 * It attempts to clear the terminal screen based on the operating system.
 */
public class ClearOutput {
    
    /**
     * Clears the console output by executing the appropriate command for the current operating system.
     * If the command fails, a fallback method is used to clear the output using ANSI escape codes.
     */
    public final static void clearOutput() {
        try {
            final String os = System.getProperty("os.name");

            ProcessBuilder processBuilder;
            if (os.contains("Windows")) {
                processBuilder = new ProcessBuilder("cmd", "/c", "cls"); // Command for Windows
            } else {
                processBuilder = new ProcessBuilder("clear"); // Command for Unix/Linux
            }

            // Attempt to clear the terminal using ProcessBuilder
            processBuilder.inheritIO().start().waitFor();
        } catch (final Exception e) {
            // Fallback to current method if ProcessBuilder fails
            fallbackClear();
        }
    }

    /**
     * Fallback method to clear the console output using ANSI escape codes.
     * This method prints escape sequences to reset the cursor position and clear the screen.
     */
    private static void fallbackClear() {
        try {
            System.out.print("\033[H\033[2J"); // ANSI escape codes to clear screen
            System.out.flush();
        } catch (final Exception e) {
            System.out.println("<<<<<Unable to clear screen>>>>>");
        }
    }
}