package Common;

/**
 * The {@code CustomTimer} class provides utility methods for managing time-related
 * operations, such as pausing execution of the current thread for a specified duration.
 */
public class CustomTimer {
    
    /**
     * Pauses the execution of the current thread for a specified amount of time.
     *
     * @param time the duration in milliseconds for which to pause the thread
     */
    public static void pause(long time) {
        try {
            Thread.sleep(time); // This will block the current thread for the specified time
        } catch (InterruptedException e) {
            System.out.println("Pause interrupted");
            Thread.currentThread().interrupt(); // Re-interrupt the thread if necessary
        }
    }
}