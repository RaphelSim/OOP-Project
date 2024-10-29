package Common;

public enum AppointmentOutcomeStatus {

    PENDING,
    DISPENSED;

    public static AppointmentOutcomeStatus fromString(String status) {
        try {
            return AppointmentOutcomeStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status: " + status + "\nSet to pending by default");
            return AppointmentOutcomeStatus.PENDING;
        }
    }

}
