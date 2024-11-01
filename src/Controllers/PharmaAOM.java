package Controllers;

import Common.AppointmentOutcomeStatus;
import DatabaseItems.AppointmentOutcome;
import Databases.AppointmentOutcomeDatabase;
public class PharmaAOM extends BaseAppointmentOutcomeManager {

    public PharmaAOM(AppointmentOutcomeDatabase database) {
        super(database);
    }

    @Override
    public void printAllOutcomes() {
        database.printItems(); // Pharmacists can view all outcomes
    }

    public void updatePrescriptionStatus(String appointmentID, AppointmentOutcomeStatus newStatus) {
        database.getRecords().stream()
        .filter(record -> record instanceof AppointmentOutcome)  // Filter only AppointmentOutcome objects
        .map(record -> (AppointmentOutcome) record)  // Safely cast to AppointmentOutcome
        .filter(outcome -> outcome.getAppointmentId().equals(appointmentID))  // Match by appointment ID
        .findFirst()  // Get the first matching outcome
        .ifPresent(outcome -> {
            outcome.setStatus(newStatus);  // Update the status
            database.storeToCSV();  // Save changes back to CSV
            System.out.println("Prescription status updated.");
        });
    }
}
