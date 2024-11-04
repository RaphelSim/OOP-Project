package Common;

public class CustomTimer {
    public static void pause(long time) {
        try {
            Thread.sleep(time); // This will block the current thread for the specified time
        } catch (InterruptedException e) {
            System.out.println("Pause interrupted");
            Thread.currentThread().interrupt(); // Re-interrupt the thread if necessary
        }
    }
}
