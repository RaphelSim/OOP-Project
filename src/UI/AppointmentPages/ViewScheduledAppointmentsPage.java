package UI.AppointmentPages;

import java.util.List;

import Common.ClearOutput;
import Common.UserInterface;
import Controllers.AMManagers.PatientAM;
import DatabaseItems.AppointmentSlot;

/**
 * The {@code ViewScheduledAppointmentsPage} class provides functionality for
 * viewing scheduled appointments for a patient. It extends {@link UserInterface}
 * and interacts with the {@link PatientAM} manager to retrieve appointment details.
 */
public class ViewScheduledAppointmentsPage extends UserInterface {
    private PatientAM patientAM; // Manager for handling patient's appointments

    /**
     * Constructs a {@code ViewScheduledAppointmentsPage} with the specified
     * {@link PatientAM} instance.
     *
     * @param patientAM the manager used to access and manage patient's appointments
     */
    public ViewScheduledAppointmentsPage(PatientAM patientAM) {
        this.patientAM = patientAM; // Initialize the appointment manager
    }

    /**
     * Displays the scheduled appointments for the patient.
     * It retrieves and prints appointment details, including ID, date, time,
     * and status. If no scheduled appointments are found, an error message is displayed.
     */
    public void displaySlots() {
        ClearOutput.clearOutput(); // Clear previous output
        List<AppointmentSlot> slots = patientAM.getAppointments(); // Retrieve scheduled appointments
        System.out.println("Your Scheduled Appointments");
        System.out.println("------------------------------");
        
        for (AppointmentSlot slot : slots) {
            System.out.println(
                    slot.getAppointmentId().substring(0, 8) + " " + slot.getDate() + "  " + slot.getTimestart() + " to "
                            + slot.getTimeend() + " " + slot.getStatus()); // Print appointment details
        }

        if (slots.size() == 0) { // Check if no scheduled appointments were found
            displayError("No scheduled appointments"); // Display error message
            pauseAndView(); // Pause before returning
            return; // Exit method
        }
        pauseAndView(); // Pause to allow user to view output
    }
}