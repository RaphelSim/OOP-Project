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

    // Method to view an appointment outcome for a patient by appointment ID
    public AppointmentOutcome viewOutcome(String appointmentId) {
        AppointmentOutcome record = getOutcome(appointmentId);
        if (record == null) {
            System.out.println("Record not found.");
        } else {
            System.out.println("Appointment outcome found for Appointment ID: " + appointmentId);
        }
        return record; 
    }

    // Method to retrieve past outcomes for a specific patient by patient ID
    public List<AppointmentOutcome> getPastOutcomes(String patientId) {
        return getAllOutcomes().stream() // Use inherited helper method to get only AppointmentOutcome records
            .filter(outcome -> outcome.getPatientId().equals(patientId)) // Filter by patient ID
            .collect(Collectors.toList()); // Collect and return as a list
    }
}





