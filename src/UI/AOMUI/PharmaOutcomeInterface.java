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
        String appointmentId = getStringInput("Enter Appointment ID to view and update status: ");
        AppointmentOutcome outcome = pharmaManager.getOutcome(appointmentId);
    
        if (outcome == null) {
            System.out.println("No outcome found for the given Appointment ID.");
            System.out.println();
            return;
        }
    
        outcome.printItem(); 
        System.out.println();
        String userResponse = getStringInput("Do you want to proceed with updating the status? (yes/no): ");
        if (userResponse.equalsIgnoreCase("no")) {
            System.out.println();
            return;
        }
    
        AppointmentOutcomeStatus newStatus = null;
        while (true) {
            try {
                newStatus = AppointmentOutcomeStatus.fromString(
                    getStringInput("Enter new Status (PENDING, DISPENSED): "));
                if (newStatus == AppointmentOutcomeStatus.PENDING || newStatus == AppointmentOutcomeStatus.DISPENSED) {
                    break;
                } else {
                    System.out.println("Invalid status. Please enter either 'PENDING' or 'DISPENSED'.");
                    System.out.println();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid status entered. Please enter either 'PENDING' or 'DISPENSED'.");
                System.out.println();
            }
        }
        boolean success = pharmaManager.updateOutcomeStatus(appointmentId, newStatus); 
        if (success) {
            System.out.println("Appointment outcome status updated successfully.");
            System.out.println();
        } else {
            System.out.println("Failed to update appointment outcome status.");
            System.out.println();
        }
    }
    
}


