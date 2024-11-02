package UI;

import DatabaseItems.AppointmentOutcome;

public class AdminAOMUI {

    // Display details of a specific outcome
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

    // Display a message to the administrator
    public void displayMessage(String message) {
        System.out.println(message);
    }
}

