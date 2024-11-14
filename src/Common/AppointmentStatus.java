package Common;

public enum AppointmentStatus {
    REQUESTED,
    CONFIRMED,
    COMPLETED,
    FREE,
    DECLINED;

    public static AppointmentStatus fromString(String status) {
        try {
            return AppointmentStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status: " + status + "\nSet to REQUESTED by default");
            return AppointmentStatus.REQUESTED;
        }
    }
}
