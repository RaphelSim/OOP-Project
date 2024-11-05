package UI.AOMUI;

import Controllers.AOManagers.PharmaAOM;
import Common.UserInterface;
import Common.AppointmentOutcomeStatus;
import DatabaseItems.AppointmentOutcome;

public class PharmaOutcomeInterface extends UserInterface {
    private PharmaAOM pharmaManager;

    public PharmaOutcomeInterface(PharmaAOM pharmaManager) {
        this.pharmaManager = pharmaManager;
    }
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void displayOptions() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Pharmacist Interface - Appointment Outcome");
            System.out.println("1. View Appointment Outcome");
            System.out.println("2. Update Appointment Outcome Status");
            System.out.println("3. Exit");

            int choice = getIntInput(-1);
            switch (choice) {
                case 1:
                    viewOutcome();
                    break;
                case 2:
                    updateOutcomeStatus();
                    break;
                case 3:
                    System.out.println("Exiting Pharmacist Interface...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void viewOutcome() {
        String appointmentId = getStringInput("Enter Appointment ID to view: ");
        AppointmentOutcome outcome = pharmaManager.getOutcome(appointmentId);
        
        if (outcome != null) {
            outcome.printItem();
        } else {
            System.out.println("No outcome found for the given Appointment ID.");
        }
    }

    private void updateOutcomeStatus() {
        String appointmentId = getStringInput("Enter Appointment ID to update status: ");
        AppointmentOutcome outcome = pharmaManager.getOutcome(appointmentId);

        if (outcome == null) {
            System.out.println("No outcome found for the given Appointment ID.");
            return;
        }

        AppointmentOutcomeStatus newStatus = AppointmentOutcomeStatus.fromString(
            getStringInput("Enter new Status (e.g., READY, COMPLETED): "));

        boolean success = pharmaManager.updateOutcomeStatus(appointmentId, newStatus);
        if (success) {
            System.out.println("Appointment outcome status updated successfully.");
        } else {
            System.out.println("Failed to update appointment outcome status.");
        }
    }
}


