package Controllers;

import Databases.AppointmentOutcomeDatabase;
import DatabaseItems.AppointmentOutcome;
import UI.PharmaAOMUI;
import Common.AppointmentOutcomeStatus;

public class PharmaAOM extends BaseAppointmentOutcomeManager {
    private PharmaAOMUI ui;

    public PharmaAOM(AppointmentOutcomeDatabase database, PharmaAOMUI ui) {
        super(database);
        this.ui = ui;
    }

    // Method for pharmacist to update prescription status
    public void updatePrescriptionStatus(String appointmentID) {
        AppointmentOutcome outcome = getOutcome(appointmentID);
        if (outcome == null) {
            ui.displayErrorMessage("Appointment Outcome not found.");
            return;
        }
    
        ui.displayOutcomeDetails(outcome);
        AppointmentOutcomeStatus newStatus = ui.getUpdatedStatus(outcome.getStatus());
    
        if (newStatus != null) {
            outcome.setStatus(newStatus); // Update the status
            saveOutcome(outcome);  // Save the entire AppointmentOutcome record
            ui.displayMessage("Prescription status updated successfully.");
        } else {
            ui.displayMessage("Update canceled.");
        }
    }
}

