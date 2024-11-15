package Controllers.AOManagers;

import java.util.List;
import java.util.stream.Collectors;

import Common.AppointmentOutcomeManager;
import Databases.AppointmentOutcomeDatabase;
import DatabaseItems.AppointmentOutcome;

public class PatientAOM extends AppointmentOutcomeManager {
    private String patientId;

    public PatientAOM(AppointmentOutcomeDatabase database, String id) {
        super(database);
        this.patientId = id;
    }

    // Method to view an appointment outcome for a patient by appointment ID
    public boolean displayOutcome(String appointmentId) {
        AppointmentOutcome record = getOutcome(appointmentId);
        if (record == null) {
            return false;
        }
        record.printItem();
        return true;
    }

    // Method to retrieve past outcomes for a specific patient by patient ID
    public boolean displayPastOutcomes() {
        List<AppointmentOutcome> outcomes = getAllOutcomes().stream()
                .filter(outcome -> outcome.getPatientId().equals(patientId))
                .collect(Collectors.toList());
        if (outcomes == null || outcomes.isEmpty())
            return false;
        for (AppointmentOutcome item : outcomes) {
            item.printItem();
        }
        return true;
    }
}
