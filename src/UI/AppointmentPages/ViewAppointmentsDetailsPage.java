package UI.AppointmentPages;

import java.util.List;

import Common.ClearOutput;
import Common.UserInterface;
import Controllers.AMManagers.AdminAM;
import Controllers.AOManagers.AdminAOM;
import DatabaseItems.AppointmentOutcome;
import DatabaseItems.AppointmentSlot;

/**
 * The {@code ViewAppointmentsDetailsPage} class provides functionality for
 * viewing details of appointments. It extends {@link UserInterface}
 * and interacts with the {@link AdminAM} and {@link AdminAOM} managers
 * to retrieve appointment and outcome details.
 */
public class ViewAppointmentsDetailsPage extends UserInterface {
    private AdminAM adminAM; // Manager for handling appointment-related operations
    private AdminAOM adminAOM; // Manager for handling appointment outcomes

    /**
     * Constructs a {@code ViewAppointmentsDetailsPage} with the specified
     * {@link AdminAM} and {@link AdminAOM} instances.
     *
     * @param adminAM the manager used to access and manage appointments
     * @param adminAOM the manager used to access and manage appointment outcomes
     */
    public ViewAppointmentsDetailsPage(AdminAM adminAM, AdminAOM adminAOM) {
        this.adminAM = adminAM; // Initialize the appointment manager
        this.adminAOM = adminAOM; // Initialize the appointment outcome manager
    }

    /**
     * Displays all scheduled appointments and their details.
     * It retrieves appointment slots, prints their details, and allows
     * the user to view specific appointment outcomes based on appointment ID.
     */
    public void displaySlots() {
        ClearOutput.clearOutput(); // Clear previous output
        List<AppointmentSlot> slots = adminAM.getAppointments(); // Retrieve all appointments
        System.out.println("All Appointments Details");
        System.out.println("-----------------------------");
        System.out.println("\n     |Appointment ID|         |Patient ID|    |Date|     |Time|         |Status|");
        
        String tempId = ""; // Temporary variable to track appointment ID
        
        for (AppointmentSlot slot : slots) {
            if (!tempId.equals(slot.getAppointmentId().substring(0, 8))) // Print a new line for each new appointment group
                System.out.println();
            tempId = slot.getAppointmentId().substring(0, 8);
            System.out.println(slot.getAppointmentId() + "      " + slot.getPatientId() + "     " + slot.getDate()
                    + "  " + slot.getTimestart() + " to " + slot.getTimeend() + "  " + slot.getStatus()); // Print appointment details
        }

        if (slots.size() == 0) { // Check if no appointments are available
            displayError("No appointments to show at this moment."); // Display error message
            pauseAndView(); // Pause before returning
            return; // Exit method
        }

        System.out.println();
        String aptId = getValidatedString("Enter the appointment id to view outcome. [Enter 'q' to go back]"); // Get user input for appointment ID
        
        if (aptId.equals("q")) // Check if user wants to go back
            return; 
        else 
            displayAppointmentOutcome(aptId); // Display outcome for the specified appointment ID
    }

    /**
     * Displays the outcome of a specific appointment based on its ID.
     *
     * @param id the unique identifier of the appointment whose outcome is to be displayed
     */
    public void displayAppointmentOutcome(String id) {
        ClearOutput.clearOutput(); // Clear previous output
        AppointmentOutcome outcome = adminAOM.getOutcome(id); // Retrieve outcome for the specified appointment ID

        if (outcome == null) { // Check if outcome exists
            displayError("Outcome not found"); // Display error message if not found
            return; // Exit method
        }

        outcome.printItem(); // Print the details of the outcome
        pauseAndView(); // Pause before returning to allow user to view output
    }
}