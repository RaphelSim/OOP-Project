package Controllers.AOManagers;

import Common.AppointmentOutcomeManager;
import Databases.AppointmentOutcomeDatabase;
import DatabaseItems.AppointmentOutcome;
import Common.AppointmentOutcomeStatus;

public class PharmaAOM extends AppointmentOutcomeManager {

    public PharmaAOM(AppointmentOutcomeDatabase database) {
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

    // Method to update the status of an appointment outcome (e.g., prescription ready)
    public boolean updateOutcomeStatus(String appointmentId, AppointmentOutcomeStatus newStatus) {
        AppointmentOutcome record = getOutcome(appointmentId);
        if (record == null) {
            System.out.println("Record not found.");
            return false;
        }

        record.setStatus(newStatus); // Update the status
        database.storeToCSV(); // Save changes to the database
        System.out.println("Appointment outcome status updated successfully.");
        return true;
    }
}

