package UI.AppointmentPages;

import java.util.List;

import Common.ClearOutput;
import Common.UserInterface;
import Controllers.AMManagers.PatientAM;
import DatabaseItems.AppointmentSlot;

/**
 * The {@code CancelAppointmentPage} class provides functionality for
 * canceling scheduled appointments for a patient. It extends
 * {@link UserInterface}
 * and interacts with the {@link PatientAM} manager to manage appointment
 * cancellations.
 */
public class CancelAppointmentPage extends UserInterface {
    private PatientAM patientAM; // Manager for handling patient's appointment-related operations
    List<AppointmentSlot> slots; // List of scheduled appointment slots

    /**
     * Constructs a {@code CancelAppointmentPage} with the specified
     * {@link PatientAM} instance.
     *
     * @param patientAM the manager used to access and manage patient's appointments
     */
    public CancelAppointmentPage(PatientAM patientAM) {
        this.patientAM = patientAM;
    }

    /**
     * Displays the patient's scheduled appointments.
     * If there are no scheduled appointments, an error message is displayed.
     * It then calls the method to cancel a selected appointment.
     */
    public void displaySlots() {
        ClearOutput.clearOutput();
        slots = patientAM.getAppointments();
        System.out.println("Your Scheduled Appointments");
        System.out.println("------------------------------");

        // Display scheduled appointments
        for (AppointmentSlot slot : slots) {
            System.out.println(
                    slot.getAppointmentId() + " " + slot.getDate() + "  " + slot.getTimestart() + " to "
                            + slot.getTimeend() + " " + slot.getStatus());
        }

        if (slots.size() == 0) { // Check if there are no scheduled appointments
            displayError("No scheduled appointments"); // Display error message
            pauseAndView(); // Pause before returning
            return; // Exit method
        }

        cancelSlot(); // Proceed to cancel a selected appointment
    }

    /**
     * Prompts the user to enter the date and time of the appointment they wish to
     * cancel.
     * It validates the input and cancels the appointment if it exists.
     */
    private void cancelSlot() {
        System.out.println();

        String appointmentId = getValidatedString(
                "Enter the appointment id you wish to cancel");
        if (appointmentId == null || appointmentId.isEmpty()) {
            System.out.println("You have not entered anything.");
            pauseAndView();
            return;
        }

        if (patientAM.cancelSlot(appointmentId)) // Attempt to cancel the appointment
            displaySuccess("Your appointment has been cancelled."); // Success message
        else
            displayError("There is no such available slot"); // Error message if cancellation fails
    }
}