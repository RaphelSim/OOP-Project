package UI.AOMUI;

import Controllers.AOManagers.PharmaAOM;
import Common.UserInterface;
import Common.ClearOutput;

/**
 * The {@code PharmaOutcomeInterface} class provides functionality for managing
 * appointment outcomes related to medicine dispensing. It extends {@link UserInterface}
 * and interacts with the {@link PharmaAOM} manager to handle appointment outcomes.
 */
public class PharmaOutcomeInterface extends UserInterface {

    private PharmaAOM pharmaManager; // Manager for handling pharmacy-related appointment outcomes

    /**
     * Constructs a {@code PharmaOutcomeInterface} with the specified
     * {@link PharmaAOM} instance.
     *
     * @param pharmaManager the manager used to access and manage pharmacy appointment outcomes
     */
    public PharmaOutcomeInterface(PharmaAOM pharmaManager) {
        this.pharmaManager = pharmaManager; // Initialize the pharmacy manager
    }

    /**
     * Prompts the user for a string input with the specified prompt message.
     *
     * @param prompt the message displayed to the user when requesting input
     * @return the trimmed input string provided by the user
     */
    private String getStringInput(String prompt) {
        System.out.print(prompt); // Display prompt message
        return scanner.nextLine().trim(); // Read and return user input
    }

    /**
     * Displays options for managing appointment outcomes.
     * Prompts the user to select an option and executes the corresponding functionality.
     */
    public void displayOptions() {
        boolean exit = false; // Flag to control the loop
        while (!exit) {
            ClearOutput.clearOutput(); // Clear previous output
            System.out.println("Please select an option:");
            System.out.println("1. View All Appointment Outcome");
            System.out.println("2. View Specific Appointment Outcome");
            System.out.println("3. Update Appointment Outcome Status");
            System.out.println("4. Exit");

            int choice = getIntInput(-1); // Get user choice
            switch (choice) {
                case 1:
                    viewAllOutcome(); // View all appointment outcomes
                    break;
                case 2:
                    viewOutcome(); // View a specific appointment outcome
                    break;
                case 3:
                    updateOutcomeStatus(); // Update the status of an appointment outcome
                    break;
                case 4:
                    exit = true; // Exit the loop
                    break;
                default:
                    System.out.println("Invalid option. Please try again."); // Handle invalid input
            }
        }
    }

    /**
     * Displays all appointment outcomes.
     * If no outcomes are pending, an error message is displayed.
     */
    private void viewAllOutcome() {
        ClearOutput.clearOutput(); // Clear previous output
        System.out.println("Appointment Outcomes");
        System.out.println("------------------------------");
        
        if (!pharmaManager.viewAllOutcome()) // Attempt to view all outcomes
            displayError("There are no appointments pending for medicine dispense"); // Display error if none exist
        
        pauseAndView(); // Pause before returning to allow user to view output
    }

    /**
     * Prompts the user to enter an appointment ID and displays its outcome.
     *
     * @param appointmentId the unique identifier of the appointment whose outcome is to be viewed
     */
    private void viewOutcome() {
        ClearOutput.clearOutput(); // Clear previous output
        String appointmentId = getStringInput("Enter Appointment ID to view: "); // Get appointment ID from user
        
        if (!pharmaManager.viewOutcome(appointmentId)) // Attempt to view outcome for specified ID
            displayError("This appointment does not exist / does not require medicine dispense yet"); // Display error if not found
        
        pauseAndView(); // Pause before returning to allow user to view output
    }

    /**
     * Prompts the user to enter an appointment ID and updates its outcome status.
     *
     * @param appointmentId the unique identifier of the appointment whose status is to be updated
     */
    private void updateOutcomeStatus() {
        ClearOutput.clearOutput(); // Clear previous output
        String appointmentId = getStringInput("Enter Appointment ID to view and update status: "); // Get appointment ID from user

        if (!pharmaManager.viewOutcome(appointmentId)) { // Check if outcome exists
            displayError("This appointment does not exist / does not require medicine dispense yet"); // Display error if not found
            return; // Exit method
        }

        System.out.println();
        System.out.println("Dispense medicine for this appointment?");
        System.out.println("1. Yes");
        System.out.println("2. Back");
        
        int choice = getIntInput(2); // Get user's choice on dispensing medicine
        
        if (choice == 1) { // If user chooses to dispense medicine
            if (pharmaManager.updateOutcomeStatus(appointmentId)) // Attempt to update status
                displaySuccess("Dispensed Successfully!"); // Success message on dispensing
            else
                displayError("Failed to update status"); // Error message on failure
        } else {
            return; // Exit method if back is chosen
        }
    }
}