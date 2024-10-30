package Controllers;

import Databases.AppointmentOutcomeDatabase;
import DatabaseItems.AppointmentOutcome;

public class AppointmentOutcomeManager {
    private final AppointmentOutcomeDatabase database;

    // Constructor to inject the database dependency
    public AppointmentOutcomeManager(AppointmentOutcomeDatabase database) {
        this.database = database;
    }

    // Method to add a new AppointmentOutcome with a duplicate check
    public void addOutcome(AppointmentOutcome outcome) {
        boolean exists = database.getRecords().stream()
            .anyMatch(item -> ((AppointmentOutcome) item)
                .getAppointmentId().equals(outcome.getAppointmentId()));
    
        if (!exists) {
            database.addItem(outcome);
            database.storeToCSV();
            System.out.println("Outcome added.");
        } else {
            System.out.println("Outcome with this ID already exists.");
        }
    }
    // Method to print all outcomes
    public void printAllOutcomes() {
        database.printItems();
    }

    // Method to remove an outcome by appointment ID
    public void removeOutcome(String appointmentID) {
        boolean removed = database.removeItem(appointmentID);
        if (removed) {
            System.out.println("Outcome removed.");
        } else {
            System.out.println("Failed to remove outcome.");
        }
        database.storeToCSV();  // Ensure the CSV file reflects the change
    }
}

