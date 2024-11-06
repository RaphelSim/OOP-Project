package UI.AOMUI;

import Controllers.AOManagers.PatientAOM;
import Common.UserInterface;
import DatabaseItems.AppointmentOutcome;
import java.util.List;

public class PatientOutcomeInterface extends UserInterface {
    private PatientAOM patientManager;

    public PatientOutcomeInterface(PatientAOM patientManager) {
        this.patientManager = patientManager;
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim(); 
    }

    public void displayOptions(String accountId) { 
        boolean exit = false;
        while (!exit) {
            System.out.println("Patient Interface - Appointment Outcome");
            System.out.println("1. View Specific Appointment Outcome");
            System.out.println("2. View All Past Appointment Outcomes");
            System.out.println("3. Exit");

            int choice = getIntInput(-1); 
            switch (choice) {
                case 1:
                    viewSpecificOutcome();
                    break;
                case 2:
                    viewPastOutcomes(accountId); 
                    break;
                case 3:
                    System.out.println("Exiting Patient Interface...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void viewSpecificOutcome() {
        String appointmentId = getStringInput("Enter Appointment ID to view: "); 
        
        if (appointmentId.isEmpty()) {
            System.out.println("Appointment ID cannot be empty. Please try again.");
            return;
        }
        
        AppointmentOutcome outcome = patientManager.getOutcome(appointmentId);
        if (outcome != null) {
            outcome.printItem();
        } else {
            System.out.println("No outcome found for the given Appointment ID.");
        }
    }

    // Method to display all past outcomes for a specific patient
    public void viewPastOutcomes(String patientId) {
        List<AppointmentOutcome> outcomes = patientManager.getPastOutcomes(patientId); // Assuming this method exists in PatientAOM
        
        System.out.println("Past Appointment Outcomes for Patient ID: " + patientId);
        if (outcomes.isEmpty()) {
            System.out.println("No past outcomes found.");
        } else {
            for (AppointmentOutcome outcome : outcomes) {
                outcome.printItem(); // Display each outcome
            }
        }
    }
}

