package Common;

import Databases.AppointmentOutcomeDatabase;
import DatabaseItems.AppointmentOutcome;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AppointmentOutcomeManager {
    protected final AppointmentOutcomeDatabase database;

    public AppointmentOutcomeManager(AppointmentOutcomeDatabase database) {
        this.database = database;
    }

    // Method to add a new outcome
    public boolean addOutcome(AppointmentOutcome outcome) {
        if (database.searchItem(outcome.getAppointmentId()) == null) {
            database.addItem(outcome);
            database.storeToCSV();
            return true;  // Outcome added successfully
        } else {
            System.out.println("Outcome with this ID already exists.");
            return false;  // Outcome already exists
        }
    }

    // Method to remove an outcome by appointment ID
    public boolean removeOutcome(String appointmentID) {
        boolean removed = database.removeItem(appointmentID);
        if (removed) {
            database.storeToCSV();
            System.out.println("Outcome removed successfully.");
        } else {
            System.out.println("Outcome not found.");
        }
        return removed;  // Return true if removed, false if not found
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

    // Save (update) an existing outcome
    public boolean saveOutcome(AppointmentOutcome outcome) {
        AppointmentOutcome existingOutcome = getOutcome(outcome.getAppointmentId());
        
        if (existingOutcome != null) {
            database.removeItem(outcome.getAppointmentId()); // Remove the existing record
            database.addItem(outcome); // Add the updated outcome to the database
            database.storeToCSV(); // Save changes to CSV
            return true;  // Outcome updated successfully
        } else {
            System.out.println("Outcome not found. Unable to update.");
            return false;  // Outcome not found
        }
    }
}


