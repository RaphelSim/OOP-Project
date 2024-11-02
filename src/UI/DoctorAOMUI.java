package UI;

import Common.AppointmentOutcomeStatus;
import DatabaseItems.AppointmentOutcome;

import java.util.Scanner;

public class DoctorAOMUI {
    private Scanner scanner;

    public DoctorAOMUI() {
        this.scanner = new Scanner(System.in);
    }

    // Display a single appointment outcome's details
    public void displayOutcomeDetails(AppointmentOutcome outcome) {
        System.out.println("Appointment ID: " + outcome.getAppointmentId());
        System.out.println("Doctor ID: " + outcome.getDoctorId());
        System.out.println("Patient ID: " + outcome.getPatientId());
        System.out.println("Date: " + outcome.getDate());
        System.out.println("Type of Service: " + outcome.getTypeOfService());
        System.out.println("Medication: " + outcome.getMedication());
        System.out.println("Consultation Notes: " + outcome.getConsultationNotes());
        System.out.println("Status: " + outcome.getStatus());
    }

    // Display a message to the user
    public void displayMessage(String message) {
        System.out.println(message);
    }

    // Get the user's choice of action (view, edit, etc.)
    public String getActionChoice() {
        System.out.println("Choose an action: [view, edit, exit]");
        return scanner.nextLine();
    }

    // Prompt the user to input an appointment ID
    public String getAppointmentIdInput() {
        System.out.print("Enter Appointment ID: ");
        return scanner.nextLine();
    }

    // Collect new outcome details from the user
    public AppointmentOutcome getNewOutcomeDetails() {
        System.out.print("Enter Appointment ID: ");
        String appointmentId = scanner.nextLine();

        System.out.print("Enter Doctor ID: ");
        String doctorId = scanner.nextLine();

        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();

        System.out.print("Enter Date (e.g., YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Enter Type of Service: ");
        String typeOfService = scanner.nextLine();

        System.out.print("Enter Medication: ");
        String medication = scanner.nextLine();

        System.out.print("Enter Consultation Notes: ");
        String consultationNotes = scanner.nextLine();

        System.out.print("Enter Status (e.g., COMPLETED, PENDING, etc.): ");
        String statusInput = scanner.nextLine();
        AppointmentOutcomeStatus status;
        try {
            status = AppointmentOutcomeStatus.valueOf(statusInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status entered. Defaulting to PENDING.");
            status = AppointmentOutcomeStatus.PENDING;
        }

        // Create a new AppointmentOutcome instance with collected data
        return new AppointmentOutcome(appointmentId, doctorId, patientId, date, typeOfService, medication, consultationNotes, status);
    }

    // Close the scanner when done
    public void close() {
        scanner.close();
    }
}


