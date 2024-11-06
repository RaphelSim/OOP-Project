package Controllers.AOManagers;

import Common.AppointmentOutcomeManager;
import Databases.AppointmentOutcomeDatabase;
import DatabaseItems.AppointmentOutcome;

public class AdminAOM extends AppointmentOutcomeManager {

    public AdminAOM(AppointmentOutcomeDatabase database) {
        super(database);
    }

    // Method to view an appointment outcome by ID
    public AppointmentOutcome viewOutcome(String appointmentId) {
        AppointmentOutcome record = getOutcome(appointmentId);
        if (record == null) {
            System.out.println("Record not found.");
        }
        return record; // Return the record for UI handling and display
    }

    // Method to delete an appointment outcome by ID
    public boolean deleteOutcome(String appointmentId) {
        boolean removed = removeOutcome(appointmentId); // Directly use inherited method to delete
        if (removed) {
            System.out.println("Appointment outcome deleted successfully.");
        } else {
            System.out.println("Record not found or failed to delete appointment outcome.");
        }
        return removed;
    }
}


