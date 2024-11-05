package UI.AOMUI;

import Controllers.AOManagers.DoctorAOM;
import Common.UserInterface;
import Common.AppointmentOutcomeStatus;
import DatabaseItems.AppointmentOutcome;

public class DoctorOutcomeInterface extends UserInterface {
    private DoctorAOM doctorManager;

    public DoctorOutcomeInterface(DoctorAOM doctorManager) {
        this.doctorManager = doctorManager;
    }
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void displayOptions() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Doctor Interface - Appointment Outcome Management");
            System.out.println("1. View Appointment Outcome");
            System.out.println("2. Edit Appointment Outcome");
            System.out.println("3. Exit");

            int choice = getIntInput(-1);
            switch (choice) {
                case 1:
                    viewOutcome();
                    break;
                case 2:
                    editOutcome();
                    break;
                case 3:
                    System.out.println("Exiting Doctor Interface...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void viewOutcome() {
        String appointmentId = getStringInput("Enter Appointment ID to view: ");
        AppointmentOutcome outcome = doctorManager.getOutcome(appointmentId);
        
        if (outcome != null) {
            outcome.printItem();
        } else {
            System.out.println("No outcome found for the given Appointment ID.");
        }
    }

    private void editOutcome() {
        String appointmentId = getStringInput("Enter Appointment ID to edit: ");
        AppointmentOutcome outcome = doctorManager.getOutcome(appointmentId);

        if (outcome == null) {
            System.out.println("No outcome found for the given Appointment ID.");
            return;
        }

        String newDate = getStringInput("Enter new Date: ");
        String newTypeOfService = getStringInput("Enter new Type of Service: ");
        String newMedication = getStringInput("Enter new Medication: ");
        String newConsultationNotes = getStringInput("Enter new Consultation Notes: ");
        AppointmentOutcomeStatus newStatus = AppointmentOutcomeStatus.fromString(
            getStringInput("Enter new Status (e.g., COMPLETED, PENDING): "));

        boolean success = doctorManager.editOutcome(appointmentId, newDate, newTypeOfService, newMedication, newConsultationNotes, newStatus);
        
        if (success) {
            System.out.println("Appointment outcome updated successfully.");
        } else {
            System.out.println("Failed to update appointment outcome.");
        }
    }
}





