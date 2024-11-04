package UI;

import Common.UserInterface;
import DatabaseItems.AppointmentOutcome;
import Common.AppointmentOutcomeStatus;

public class PharmaAOMUI extends UserInterface {

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayErrorMessage(String message) {
        System.err.println("Error: " + message);
    }

    public void displayOutcomeDetails(AppointmentOutcome outcome) {
        System.out.println("Appointment ID: " + outcome.getAppointmentId());
        System.out.println("Patient ID: " + outcome.getPatientId());
        System.out.println("Status: " + outcome.getStatus());
    }

    public AppointmentOutcomeStatus getUpdatedStatus(AppointmentOutcomeStatus currentStatus) {
        System.out.println("Current Status: " + currentStatus);
        System.out.print("Enter new status (e.g., DISPENSED, PENDING) or leave blank to cancel: ");
        String newStatusInput = scanner.nextLine().toUpperCase();

        if (newStatusInput.isEmpty()) {
            return null; // Return null if the pharmacist cancels the update
        }

        try {
            return AppointmentOutcomeStatus.valueOf(newStatusInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status entered.");
            return null;
        }
    }
}

