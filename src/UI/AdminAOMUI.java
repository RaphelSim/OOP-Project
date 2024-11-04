package UI;

import Common.UserInterface;
import DatabaseItems.AppointmentOutcome;
import Common.ClearOutput;

public class AdminAOMUI extends UserInterface {

    // Display details of a specific outcome for an admin
    public void displayOutcomeDetails(AppointmentOutcome outcome) {
        System.out.println("Appointment ID: " + outcome.getAppointmentId());
        System.out.println("Patient ID: " + outcome.getPatientId());
        System.out.println("Doctor ID: " + outcome.getDoctorId());
        System.out.println("Date: " + outcome.getDate());
        System.out.println("Type of Service: " + outcome.getTypeOfService());
        System.out.println("Medication: " + outcome.getMedication());
        System.out.println("Consultation Notes: " + outcome.getConsultationNotes());
        System.out.println("Status: " + outcome.getStatus());
    }

    // Method for admin to display success or error messages
    public void displaySuccessMessage(String message) {
        displaySuccess(message);
    }

    public void displayErrorMessage(String message) {
        displayError(message);
    }
}

