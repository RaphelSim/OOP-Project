package Controllers;

import DatabaseItems.AppointmentOutcome;
import UI.AdminAOMUI;
import Databases.AppointmentOutcomeDatabase;

public class AdminAOM extends BaseAppointmentOutcomeManager {
    private AdminAOMUI ui;

    public AdminAOM(AppointmentOutcomeDatabase database, AdminAOMUI ui) {
        super(database);
        this.ui = ui;
    }

    // Display all outcomes
    public void displayAllOutcomes() {
        getAllOutcomes().forEach(ui::displayOutcomeDetails);
    }

    // Add a new outcome
    public void addNewOutcome(AppointmentOutcome outcome) {
        addOutcome(outcome);
        ui.displayMessage("Outcome added successfully.");
    }

    // Remove an outcome by ID
    public void removeOutcomeById(String appointmentID) {
        removeOutcome(appointmentID);
        ui.displayMessage("Outcome removed successfully.");
    }
}

