package UI.AOMUI;

import Controllers.AOManagers.AdminAOM;
import Common.ClearOutput;
import Common.UserInterface;
import DatabaseItems.AppointmentOutcome;

public class AdminOutcomeInterface extends UserInterface {
    private AdminAOM adminManager;

    public AdminOutcomeInterface(AdminAOM adminManager) {
        this.adminManager = adminManager;
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim(); 
    }    

    public void displayOptions() {
        ClearOutput.clearOutput();
        boolean exit = false;
        while (!exit) {
            System.out.println("Admin Interface - Appointment Outcome Management");
            System.out.println("1. View Appointment Outcome");
            System.out.println("2. Delete Appointment Outcome");
            System.out.println("3. Exit");

            int choice = getIntInput(-1);
            switch (choice) {
                case 1:
                    viewOutcome();
                    break;
                case 2:
                    deleteOutcome();
                    break;
                case 3:
                    System.out.println("Exiting Admin Interface...");
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
        
        if (appointmentId.isEmpty()) {
            System.out.println("Appointment ID cannot be empty. Please try again.");
            return;
        }

        AppointmentOutcome outcome = adminManager.getOutcome(appointmentId);
        if (outcome != null) {
            outcome.printItem();
        } else {
            System.out.println("No outcome found for the given Appointment ID.");
            System.out.println();
        }
    }

    private void deleteOutcome() {
        ClearOutput.clearOutput();
        String appointmentId = getStringInput("Enter Appointment ID to delete: ");
        
        if (appointmentId.isEmpty()) {
            System.out.println("Appointment ID cannot be empty. Please try again.");
            System.out.println();
            return;
        }

        boolean success = adminManager.deleteOutcome(appointmentId);
        if (success) {
            System.out.println("Appointment outcome deleted successfully.");
            System.out.println();
        } else {
            System.out.println("Failed to delete appointment outcome. The record may not exist.");
        }
    }
}

