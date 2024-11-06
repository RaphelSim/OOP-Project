package UI.AOMUI;

import Controllers.AOManagers.AdminAOM;
import Common.UserInterface;
import DatabaseItems.AppointmentOutcome;

public class AdminOutcomeInterface extends UserInterface {
    private AdminAOM adminManager;

    public AdminOutcomeInterface(AdminAOM adminManager) {
        this.adminManager = adminManager;
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim(); // Trim input to avoid leading/trailing spaces
    }    

    public void displayOptions() {
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
        }
    }

    private void deleteOutcome() {
        String appointmentId = getStringInput("Enter Appointment ID to delete: ");
        
        if (appointmentId.isEmpty()) {
            System.out.println("Appointment ID cannot be empty. Please try again.");
            return;
        }

        boolean success = adminManager.deleteOutcome(appointmentId);
        if (success) {
            System.out.println("Appointment outcome deleted successfully.");
        } else {
            System.out.println("Failed to delete appointment outcome. The record may not exist.");
        }
    }
}

