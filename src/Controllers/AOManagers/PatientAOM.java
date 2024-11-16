package Controllers.AOManagers;

import java.util.List;
import java.util.stream.Collectors;

import Common.AppointmentOutcomeManager;
import Databases.AppointmentOutcomeDatabase;
import DatabaseItems.AppointmentOutcome;

/**
 * The {@code PatientAOM} class manages appointment outcomes specific to patients.
 * It extends {@link AppointmentOutcomeManager} to provide functionalities for 
 * viewing appointment outcomes related to a specific patient.
 */
public class PatientAOM extends AppointmentOutcomeManager {
    private String patientId;

    /**
     * Constructs a {@code PatientAOM} with the specified appointment outcome database
     * and patient ID.
     *
     * @param database the {@link AppointmentOutcomeDatabase} used to manage appointment outcomes
     * @param id       the unique identifier of the patient
     */
    public PatientAOM(AppointmentOutcomeDatabase database, String id) {
        super(database);
        this.patientId = id;
    }

    /**
     * Displays the outcome of a specific appointment for the patient.
     *
     * @param appointmentId the unique identifier of the appointment
     * @return true if the outcome was successfully displayed; false if not found
     */
    public boolean displayOutcome(String appointmentId) {
        AppointmentOutcome record = getOutcome(appointmentId);
        if (record == null) {
            return false; // Outcome not found
        }
        record.printItem(); // Print the outcome details
        return true; // Outcome displayed successfully
    }

    /**
     * Displays all past appointment outcomes for the specific patient.
     *
     * @return true if any past outcomes were successfully displayed; false if none found
     */
    public boolean displayPastOutcomes() {
        List<AppointmentOutcome> outcomes = getAllOutcomes().stream()
                .filter(outcome -> outcome.getPatientId().equals(patientId)) // Filter by patient ID
                .collect(Collectors.toList());
        if (outcomes == null || outcomes.isEmpty())
            return false; // No past outcomes found
        for (AppointmentOutcome item : outcomes) {
            item.printItem(); // Print each past outcome
        }
        return true; // Past outcomes displayed successfully
    }
}