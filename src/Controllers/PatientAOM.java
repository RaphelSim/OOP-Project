package Controllers;

import UI.PatientAOMUI;
import DatabaseItems.AppointmentOutcome;
import Databases.AppointmentOutcomeDatabase;
import java.util.List;
import java.util.stream.Collectors;

public class PatientAOM extends BaseAppointmentOutcomeManager {
    private String patientID;
    private PatientAOMUI ui;

    public PatientAOM(AppointmentOutcomeDatabase database, String patientID, PatientAOMUI ui) {
        super(database);
        this.patientID = patientID;
        this.ui = ui;
    }

    // Display outcomes for this patient
    public void displayPatientOutcomes() {
        List<AppointmentOutcome> patientOutcomes = getAllOutcomes().stream()
                .filter(outcome -> outcome.getPatientId().equals(patientID))
                .collect(Collectors.toList());

        if (patientOutcomes.isEmpty()) {
            ui.displayMessage("No outcomes found.");
        } else {
            patientOutcomes.forEach(ui::displayOutcomeDetails);
        }
    }
}



