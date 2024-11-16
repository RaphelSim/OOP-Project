package UI.AppointmentPages;

import DatabaseItems.Account;
import DatabaseItems.AppointmentSlot;

import java.util.List;

import Common.AppointmentStatus;
import Common.ClearOutput;
import Common.UserInterface;
import Controllers.AMManagers.PatientAM;

/**
 * The {@code ViewAvailableAppointmentsPage} class provides functionality for
 * viewing available appointments for doctors. It extends {@link UserInterface}
 * and interacts with the {@link PatientAM} manager to retrieve doctor and appointment details.
 */
public class ViewAvailableAppointmentsPage extends UserInterface {
    private PatientAM patientAM; // Manager for handling patient's appointment-related operations

    /**
     * Constructs a {@code ViewAvailableAppointmentsPage} with the specified
     * {@link PatientAM} instance.
     *
     * @param patientAM the manager used to access and manage patient's appointments
     */
    public ViewAvailableAppointmentsPage(PatientAM patientAM) {
        this.patientAM = patientAM; // Initialize the appointment manager
    }

    /**
     * Displays a list of available appointments for doctors.
     * It shows a list of doctors, allows the user to select a doctor,
     * and displays available time slots for that doctor. The user can
     * choose to view another doctor's slots or return to the menu.
     */
    public void viewAvailableAppointments() {
        // View List of Doctors
        List<Account> docList = patientAM.getDocList(); // Retrieve list of doctors
        Boolean quit = false; // Flag to control loop
        char choice; // Variable to store user choice

        while (!quit) {
            ClearOutput.clearOutput(); // Clear previous output
            System.out.println("Doctor ID | Doctor Name");
            System.out.println("----------------------------");
            for (Account item : docList) {
                System.out.println(item.getid() + "   " + item.getName()); // Print doctor details
            }
            System.out.println();

            // Viewing specific doctor's schedule
            String docID = getValidatedString("Enter the doctor's id to view or enter 'q' to go back to menu: ");
            if (docID == null || docID.equals("q"))
                return; // Exit if input is null or 'q'

            // Check if doctor exists
            if (!patientAM.checkDoctor(docID)) {
                displayError("Doctor not found!"); // Display error if doctor not found
                return; // Exit method
            }

            // Show list of available time slots for chosen Doctor
            List<AppointmentSlot> slots = patientAM.getAvailableSlots(docID); // Retrieve available slots
            System.out.println();
            System.out.println("Available slots for " + docID);
            System.out.println("------------------------");
            for (AppointmentSlot slot : slots) {
                if (slot.getStatus() == AppointmentStatus.FREE) // Check if slot is free
                    System.out.println(slot.getDate() + "  " + slot.getTimestart() + " to " + slot.getTimeend()); // Print slot details
            }

            if (slots.size() == 0) {
                displayError("No available slots appointments"); // Display error if no slots are available
            }

            while (true) {
                String c = getValidatedString(
                        "Would you like to look at other doctor's list of available slots? (Y/N)");

                // Check for null input
                if (c == null) {
                    displayError("Invalid input"); // Handle null input case
                    break;
                }
                choice = c.charAt(0); // Get user's choice

                if (choice == 'y' || choice == 'Y') {
                    System.out.println("Loading list of doctors.."); // Inform user about loading process
                    break; // Break loop to show doctors again
                } else if (choice == 'n' || choice == 'N') {
                    quit = true; // Set quit flag to exit loop
                    break; // Break loop to exit method
                } else {
                    System.out.println("You have entered an invalid option."); // Handle invalid option case
                }
            }
        }
    }
}