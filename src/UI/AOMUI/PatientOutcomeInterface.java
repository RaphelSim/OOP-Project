package UI.AOMUI;

import Controllers.AOManagers.PatientAOM;
import Common.UserInterface;
import Common.ClearOutput;

/**
 * The {@code PatientOutcomeInterface} class provides functionality for managing
 * appointment outcomes for patients. It extends {@link UserInterface}
 * and interacts with the {@link PatientAOM} manager to handle patient-related outcomes.
 */
public class PatientOutcomeInterface extends UserInterface {
    private PatientAOM patientManager; // Manager for handling patient-related appointment outcomes

    /**
     * Constructs a {@code PatientOutcomeInterface} with the specified
     * {@link PatientAOM} instance.
     *
     * @param patientManager the manager used to access and manage patient appointment outcomes
     */
    public PatientOutcomeInterface(PatientAOM patientManager) {
        this.patientManager = patientManager; // Initialize the patient manager
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Prompts the user to select an option for managing appointment outcomes.
     * Displays a menu of options and executes the corresponding functionality based on user input.
     *
     * @param accountId the unique identifier of the patient's account
     */
    public void displayOptions(String accountId) {
        boolean exit = false; // Flag to control the loop
        while (!exit) {
            ClearOutput.clearOutput(); // Clear previous output
            System.out.println("Patient Interface - Appointment Outcome");
            System.out.println("1. View Specific Appointment Outcome");
            System.out.println("2. View All Past Appointment Outcomes");
            System.out.println("3. Exit");

            int choice = getIntInput(-1); // Get user choice
            switch (choice) {
                case 1:
                    viewSpecificOutcome(); // View a specific appointment outcome
                    break;
                case 2:
                    viewPastOutcomes(); // View all past appointment outcomes
                    break;
                case 3:
                    System.out.println("Exiting Patient Interface..."); // Exit message
                    exit = true; // Set exit flag to true
                    break;
                default:
                    System.out.println("Invalid option. Please try again."); // Handle invalid input
            }
        }
    }

    /**
     * Prompts the user to enter an appointment ID and displays its outcome.
     * If the ID is empty or does not exist, an error message is displayed.
     */
    public void viewSpecificOutcome() {
        ClearOutput.clearOutput(); // Clear previous output
        String appointmentId = getStringInput("Enter Appointment ID to view: "); // Get appointment ID from user

        if (appointmentId.isEmpty()) { // Check if appointment ID is empty
            displayError("Appointment ID cannot be empty"); // Display error message
            return; // Exit method
        }

        if (!patientManager.displayOutcome(appointmentId)) // Attempt to display outcome for specified ID
            displayError("There is no existing outcome for this appointment id"); // Display error if not found

        pauseAndView(); // Pause before returning to allow user to view output
    }

    /**
     * Displays all past outcomes for appointments associated with the patient.
     * If no past outcomes are found, an error message is displayed.
     */
    public void viewPastOutcomes() {
        ClearOutput.clearOutput(); // Clear previous output
        System.out.println("Appointment Outcome History");
        System.out.println("------------------------------");
        
        if (!patientManager.displayPastOutcomes()) // Attempt to display past outcomes
            displayError("No appointment outcome found"); // Display error if none exist

        pauseAndView(); // Pause before returning to allow user to view output
    }
}