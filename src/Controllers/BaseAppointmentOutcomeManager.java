package Controllers;

import Databases.AppointmentOutcomeDatabase;
import DatabaseItems.AppointmentOutcome;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseAppointmentOutcomeManager {
    protected final AppointmentOutcomeDatabase database;

    public BaseAppointmentOutcomeManager(AppointmentOutcomeDatabase database) {
        this.database = database;
    }

    // Method to add a new outcome
    public void addOutcome(AppointmentOutcome outcome) {
        if (database.searchItem(outcome.getAppointmentId()) == null) {
            database.addItem(outcome);
            database.storeToCSV();
            System.out.println("Outcome added successfully.");
        } else {
            System.out.println("Outcome with this ID already exists.");
        }
    }

    // Method to remove an outcome by appointment ID
    public void removeOutcome(String appointmentID) {
        boolean removed = database.removeItem(appointmentID);
        if (removed) {
            database.storeToCSV();
            System.out.println("Outcome removed successfully.");
        } else {
            System.out.println("Outcome not found.");
        }
    }

    // Retrieve an outcome by appointment ID
    public AppointmentOutcome getOutcome(String appointmentID) {
        return (AppointmentOutcome) database.searchItem(appointmentID);
    }

    // Get all outcomes
    public List<AppointmentOutcome> getAllOutcomes() {
        return database.getRecords().stream()
                .filter(item -> item instanceof AppointmentOutcome)
                .map(item -> (AppointmentOutcome) item)
                .collect(Collectors.toList());
    }

    //edit existing record
    public void saveOutcome(AppointmentOutcome outcome) {
        // Remove the old record (if it exists) and add the updated outcome back to the database
        database.removeItem(outcome.getAppointmentId()); // Remove the existing record by appointment ID
        database.addItem(outcome); // Add the updated record to the database
        database.storeToCSV(); // Save all records to the CSV file or database
    }
}


