package UI.AOMUI;

import Controllers.AOManagers.PharmaAOM;
import Common.UserInterface;
import Common.AppointmentOutcomeStatus;
import Common.ClearOutput;
import DatabaseItems.AppointmentOutcome;

public class PharmaOutcomeInterface extends UserInterface {
    
    private PharmaAOM pharmaManager;

    public PharmaOutcomeInterface(PharmaAOM pharmaManager) {
        this.pharmaManager = pharmaManager;
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public void displayOptions() {
        ClearOutput.clearOutput();
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
                
        ClearOutput.clearOutput();
        String appointmentId = getStringInput("Enter Appointment ID to view: ");
        AppointmentOutcome outcome = pharmaManager.getOutcome(appointmentId);

        if (outcome != null) {
            outcome.printItem();
            System.out.println();
        } else {
            System.out.println("No outcome found for the given Appointment ID.");
            System.out.println();
        }
    }

    private void updateOutcomeStatus() {
        ClearOutput.clearOutput();
        String appointmentId = getStringInput("Enter Appointment ID to update status: ");
        AppointmentOutcome outcome = pharmaManager.getOutcome(appointmentId);

        if (outcome == null) {
            System.out.println("No outcome found for the given Appointment ID.");
            return;
        }

        String dispenseChoice = getStringInput("Do you want to mark this as DISPENSED? (yes/no): ").toLowerCase();

        AppointmentOutcomeStatus newStatus;
        if (dispenseChoice.equals("yes")) {
            newStatus = AppointmentOutcomeStatus.DISPENSED;
        } else {
            newStatus = AppointmentOutcomeStatus.PENDING;
        }

        boolean success = pharmaManager.updateOutcomeStatus(appointmentId, newStatus);

        if (success) {
            System.out.println("Appointment outcome status updated successfully.");
        } else {
            System.out.println("Failed to update appointment outcome status.");
        }
    }

}


