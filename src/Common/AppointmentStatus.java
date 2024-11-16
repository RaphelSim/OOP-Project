package Common;

/**
 * The {@code AppointmentStatus} enum represents the various statuses
 * that an appointment can have in the system. It includes statuses such as
 * REQUESTED, CONFIRMED, COMPLETED, FREE, and DECLINED.
 */
public enum AppointmentStatus {
    /** Indicates that the appointment has been requested but not yet confirmed. */
    REQUESTED,

    /** Indicates that the appointment has been confirmed. */
    CONFIRMED,

    /** Indicates that the appointment has been completed. */
    COMPLETED,

    /** Indicates that the appointment slot is currently free. */
    FREE,

    /** Indicates that the appointment has been declined. */
    DECLINED;

    /**
     * Converts a string representation of an appointment status to its corresponding
     * {@code AppointmentStatus} enum value.
     *
     * @param status the string representation of the appointment status
     * @return the corresponding {@code AppointmentStatus} enum value
     *         or {@code REQUESTED} if the input is invalid
     */
    public static AppointmentStatus fromString(String status) {
        try {
            return AppointmentStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status: " + status + "\nSet to REQUESTED by default");
            return AppointmentStatus.REQUESTED; // Default to REQUESTED if invalid
        }
    }
}