package Controllers;

import Databases.AppointmentOutcomeDatabase;
import DatabaseItems.AppointmentOutcome;

public abstract class BaseAppointmentOutcomeManager {
    protected final AppointmentOutcomeDatabase database;

    public BaseAppointmentOutcomeManager(AppointmentOutcomeDatabase database) {
        this.database = database;
    }

    // Common functionalities
    public void addOutcome(AppointmentOutcome outcome) {
        boolean exists = database.getRecords().stream()
            .anyMatch(item -> ((AppointmentOutcome) item).getAppointmentId().equals(outcome.getAppointmentId()));

        if (!exists) {
            database.addItem(outcome);
            database.storeToCSV();
            System.out.println("Outcome added.");
        } else {
            System.out.println("Outcome with this ID already exists.");
        }
    }

    public void removeOutcome(String appointmentID) {
        boolean removed = database.removeItem(appointmentID);
        if (removed) {
            System.out.println("Outcome removed.");
            database.storeToCSV();
        } else {
            System.out.println("Failed to remove outcome.");
        }
    }

    public abstract void printAllOutcomes(); // Abstract for specialized print logic
}
