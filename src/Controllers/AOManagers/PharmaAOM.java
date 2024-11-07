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
        } else {
            System.out.println("Appointment outcome found for Appointment ID: " + appointmentId);
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

        // Optional validation check for pharmacist-allowed statuses
        if (newStatus != AppointmentOutcomeStatus.PENDING && newStatus != AppointmentOutcomeStatus.DISPENSED) {
            System.out.println("Error: Invalid status update. Pharmacists can only set statuses to PENDING or DISPENSED.");
            return false;
        }

        record.setStatus(newStatus); // Update the status
        
        try {
            database.storeToCSV(); // Save changes to the database
        } catch (Exception e) {
            System.out.println("Error: Failed to save the updated status to the database.");
            return false;
        }
        
        return true;
    }
}

