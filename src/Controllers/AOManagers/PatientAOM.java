package Controllers.AOManagers;

import java.util.List;
import java.util.stream.Collectors;

import Common.AppointmentOutcomeManager;
import Databases.AppointmentOutcomeDatabase;
import DatabaseItems.AppointmentOutcome;

public class PatientAOM extends AppointmentOutcomeManager {

    public PatientAOM(AppointmentOutcomeDatabase database) {
        super(database);
    }

    // Method to view an appointment outcome for a patient
    public AppointmentOutcome viewOutcome(String appointmentId) {
        AppointmentOutcome record = getOutcome(appointmentId);
        if (record == null) {
            System.out.println("Record not found.");
        }
        return record; // Return record to allow UI to handle display
    }
     public List<AppointmentOutcome> getPastOutcomes(String patientId) {
        return database.getRecords().stream()
            .filter(record -> record instanceof AppointmentOutcome) // Ensure it's an AppointmentOutcome record
            .map(record -> (AppointmentOutcome) record) // Cast to AppointmentOutcome
            .filter(outcome -> outcome.getPatientId().equals(patientId)) // Filter by patient ID
            .collect(Collectors.toList()); // Collect and return as a list
    }
}





