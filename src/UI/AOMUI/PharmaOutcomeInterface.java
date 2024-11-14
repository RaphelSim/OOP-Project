package UI.AOMUI;

import Controllers.AOManagers.PharmaAOM;
import Common.UserInterface;
import Common.ClearOutput;

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
        boolean exit = false;
        while (!exit) {
            ClearOutput.clearOutput();
            System.out.println("Please select an option:");
            System.out.println("1. View All Appointment Outcome");
            System.out.println("2. View Specific Appointment Outcome");
            System.out.println("3. Update Appointment Outcome Status");
            System.out.println("4. Exit");

            int choice = getIntInput(-1);
            switch (choice) {
                case 1:
                    viewAllOutcome();
                    break;
                case 2:
                    viewOutcome();
                    break;
                case 3:
                    updateOutcomeStatus();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void viewAllOutcome() {
        ClearOutput.clearOutput();
        System.out.println("Appointment Outcomes");
        System.out.println("------------------------------");
        if (!pharmaManager.viewAllOutcome())
            displayError("There are no appointments pending for medicine dispense");
        System.out.println();
        System.out.println("Press ENTER to return to menu");
        scanner.nextLine();
    }

    private void viewOutcome() {
        ClearOutput.clearOutput();
        String appointmentId = getStringInput("Enter Appointment ID to view: ");
        if (!pharmaManager.viewOutcome(appointmentId))
            displayError("This appointment does not exist / does not require medicine dispense yet");
        System.out.println();
        System.out.println("Press ENTER to return to menu");
        scanner.nextLine();
    }

    private void updateOutcomeStatus() {
        ClearOutput.clearOutput();
        String appointmentId = getStringInput("Enter Appointment ID to view and update status: ");

        if (!pharmaManager.viewOutcome(appointmentId)) {
            displayError("This appointment does not exist / does not require medicine dispense yet");
            return;
        }

        System.out.println();
        System.out.println("Dispense medicine for this appointment?");
        System.out.println("1. Yes");
        System.out.println("2. Back");
        int choice = getIntInput(2);
        if (choice == 1) {
            if (pharmaManager.updateOutcomeStatus(appointmentId))
                displaySuccess("Dispensed Successfully!");
            else
                displayError("Failed to update status");
        } else
            return;
    }

}
