package Controllers.AOManagers;

import Common.AppointmentOutcomeManager;
import Databases.AppointmentOutcomeDatabase;
import DatabaseItems.AppointmentOutcome;

public class AdminAOM extends AppointmentOutcomeManager {

    public AdminAOM(AppointmentOutcomeDatabase database) {
        super(database);
    }

    // Method to view an appointment outcome
    public AppointmentOutcome viewOutcome(String appointmentId) {
        AppointmentOutcome record = getOutcome(appointmentId);
        if (record == null) {
            System.out.println("Record not found.");
        }
        return record; // Return record to allow UI to handle display
    }

    // Method to delete an appointment outcome
    public boolean deleteOutcome(String appointmentId) {
        AppointmentOutcome record = getOutcome(appointmentId);
        if (record == null) {
            System.out.println("Record not found.");
            return false;
        }

        boolean removed = database.removeItem(appointmentId); // Remove item from the database
        if (removed) {
            database.storeToCSV(); // Save changes to the database
            System.out.println("Appointment outcome deleted successfully.");
        } else {
            System.out.println("Failed to delete appointment outcome.");
        }
        return removed;
    }
}

