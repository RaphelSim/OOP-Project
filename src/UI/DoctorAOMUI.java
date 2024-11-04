package UI;

import Common.UserInterface;
import DatabaseItems.AppointmentOutcome;
import Common.AppointmentOutcomeStatus;
import Common.ClearOutput;

public class DoctorAOMUI extends UserInterface {

    // Method to display a general message
    public void displayMessage(String message) {
        System.out.println(message);
    }

    // Method to display an error message
    public void displayErrorMessage(String message) {
        System.err.println("Error: " + message);
    }

    // Method to display outcome details for a doctor
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

    // Method to collect new outcome details for editing or adding a new outcome
    public AppointmentOutcome getNewOutcomeDetails(String appointmentId, String doctorId, String patientId) {
        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Enter Type of Service: ");
        String typeOfService = scanner.nextLine();

        System.out.print("Enter Medication: ");
        String medication = scanner.nextLine();

        System.out.print("Enter Consultation Notes: ");
        String consultationNotes = scanner.nextLine();

        AppointmentOutcomeStatus status = AppointmentOutcomeStatus.PENDING;

        return new AppointmentOutcome(appointmentId, doctorId, patientId, date, typeOfService, medication, consultationNotes, status);
    }
}


