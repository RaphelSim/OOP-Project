package Common;

/**
 * The {@code AppointmentOutcomeStatus} enum represents the possible statuses
 * for an appointment outcome. It includes statuses such as PENDING and DISPENSED.
 */
public enum AppointmentOutcomeStatus {

    /** Indicates that the appointment outcome is pending. */
    PENDING,

    /** Indicates that the medication has been dispensed. */
    DISPENSED;

    /**
     * Converts a string representation of a status to its corresponding
     * {@code AppointmentOutcomeStatus} enum value.
     *
     * @param status the string representation of the status
     * @return the corresponding {@code AppointmentOutcomeStatus} enum value
     *         or {@code PENDING} if the input is invalid
     */
    public static AppointmentOutcomeStatus fromString(String status) {
        try {
            return AppointmentOutcomeStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status: " + status + "\nSet to pending by default");
            return AppointmentOutcomeStatus.PENDING;
        }
    }

}