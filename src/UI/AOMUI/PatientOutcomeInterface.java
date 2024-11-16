package UI.AOMUI;

import Controllers.AOManagers.PatientAOM;
import Common.UserInterface;
import Common.ClearOutput;

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
            ClearOutput.clearOutput();
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
                    viewPastOutcomes();
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
        ClearOutput.clearOutput();
        String appointmentId = getStringInput("Enter Appointment ID to view: ");

        if (appointmentId.isEmpty()) {
            displayError("Appointment ID cannot be empty");
            return;
        }

        if (!patientManager.displayOutcome(appointmentId))
            displayError("There is no existing outcome for this appointment id");

        pauseAndView();
    }

    // Method to display all past outcomes for a specific patient
    public void viewPastOutcomes() {
        ClearOutput.clearOutput();
        System.out.println("Appointment Outcome History");
        System.out.println("------------------------------");
        if (!patientManager.displayPastOutcomes())
            displayError("No apppointment outcome found");

        pauseAndView();
    }
}
