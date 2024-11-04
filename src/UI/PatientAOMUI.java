package UI;

import Common.ClearOutput;
import Common.UserInterface;
import DatabaseItems.AppointmentOutcome;

public class PatientAOMUI extends UserInterface {

    // Method to display a general message
    public void displayMessage(String message) {
        System.out.println(message);
    }

    // Method to display an error message
    public void displayErrorMessage(String message) {
        System.err.println("Error: " + message);
    }

    // Method to display outcome details for a patient
    public void displayOutcomeDetails(AppointmentOutcome outcome) {
        System.out.println("Appointment ID: " + outcome.getAppointmentId());
        System.out.println("Doctor ID: " + outcome.getDoctorId());
        System.out.println("Date: " + outcome.getDate());
        System.out.println("Type of Service: " + outcome.getTypeOfService());
        System.out.println("Medication: " + outcome.getMedication());
        System.out.println("Consultation Notes: " + outcome.getConsultationNotes());
        System.out.println("Status: " + outcome.getStatus());
    }
}
