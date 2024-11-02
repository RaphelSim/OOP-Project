package UI;

import DatabaseItems.AppointmentOutcome;

public class PharmaAOMUI {

    // Display medication details from an outcome
    public void displayMedicationDetails(AppointmentOutcome outcome) {
        System.out.println("Appointment ID: " + outcome.getAppointmentId());
        System.out.println("Patient ID: " + outcome.getPatientId());
        System.out.println("Medication: " + outcome.getMedication());
        System.out.println("Status: " + outcome.getStatus());
    }

    // Display a message to the pharmacist
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
