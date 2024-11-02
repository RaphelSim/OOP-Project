package Common;

public class ClearOutput {
    public final static void clearOutput() {
        try {
            final String os = System.getProperty("os.name");

            ProcessBuilder processBuilder;
            if (os.contains("Windows")) {
                processBuilder = new ProcessBuilder("cmd", "/c", "cls");
            } else {
                processBuilder = new ProcessBuilder("clear");
            }

            // Attempt to clear the terminal using ProcessBuilder
            processBuilder.inheritIO().start().waitFor();
        } catch (final Exception e) {
            // Fallback to current method if ProcessBuilder fails
            fallbackClear();
        }
    }

    private static void fallbackClear() {
        try {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (final Exception e) {
            System.out.println("<<<<<Unable to clear screen>>>>>");
        }
    }
}
