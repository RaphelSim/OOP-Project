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

    protected static String getStringInput(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine().trim(); // Ensure no extra spaces in input
    }


    public void displayOptions(String accountId) { // Passing accountId if needed
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
                    displayPastOutcomes(accountId); // Use the passed accountId
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
    
    private void viewSpecificOutcome() {
        String appointmentId = getStringInput("Enter Appointment ID to view: "); // Prompt user to enter ID
        AppointmentOutcome outcome = patientManager.getOutcome(appointmentId);
    
        if (outcome != null) {
            outcome.printItem();
        } else {
            System.out.println("No outcome found for the given Appointment ID.");
        }
    }
    

    // New method to display all past outcomes for a specific patient
    public void displayPastOutcomes(String patientId) {
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
