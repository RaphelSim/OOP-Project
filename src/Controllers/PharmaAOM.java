package Controllers;

import DatabaseItems.AppointmentOutcome;
import UI.PharmaAOMUI;
import Databases.AppointmentOutcomeDatabase;

import java.util.List;

public class PharmaAOM extends BaseAppointmentOutcomeManager {
    private PharmaAOMUI ui;

    public PharmaAOM(AppointmentOutcomeDatabase database, PharmaAOMUI ui) {
        super(database);
        this.ui = ui;
    }

    // Display medication details for all outcomes
    public void displayMedicationDetails() {
        List<AppointmentOutcome> outcomes = getAllOutcomes();
        outcomes.forEach(ui::displayMedicationDetails);
    }
}


