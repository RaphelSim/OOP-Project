package UI.AppointmentPages;

import java.util.List;

import Common.ClearOutput;
import Common.UserInterface;
import Controllers.AMManagers.PatientAM;
import DatabaseItems.AppointmentSlot;
import DatabaseItems.Account;

/**
 * The {@code RescheduleAppointmentPage} class provides functionality for
 * rescheduling appointments for a patient. It extends {@link UserInterface}
 * and interacts with the {@link PatientAM} manager to manage appointment details.
 */
public class RescheduleAppointmentPage extends UserInterface {
    private PatientAM patientAM; // Manager for handling patient's appointment-related operations
    private Account patient; // The account of the patient

    /**
     * Constructs a {@code RescheduleAppointmentPage} with the specified
     * {@link PatientAM} and {@link Account} instances.
     *
     * @param patientAM the manager used to access and manage patient's appointments
     * @param patient   the account of the patient whose appointments are being rescheduled
     */
    public RescheduleAppointmentPage(PatientAM patientAM, Account patient) {
        this.patientAM = patientAM; // Initialize the appointment manager
        this.patient = patient; // Initialize the patient's account
    }

    /**
     * Displays the patient's scheduled appointments and allows the user to select
     * an appointment to reschedule. It retrieves available slots for the selected doctor.
     */
    public void displayOptions() {
        ClearOutput.clearOutput(); // Clear previous output
        System.out.println("Your Scheduled Appointments");
        System.out.println("------------------------------");
        
        // Display scheduled appointments
        for (AppointmentSlot slot : patientAM.getAppointments()) {
            System.out.println(
                    slot.getAppointmentId().substring(0, 8) + " " + slot.getDate() + "  " + slot.getTimestart() + " to "
                            + slot.getTimeend() + " " + slot.getStatus());
        }

        if (patientAM.getAppointments().size() == 0) { // Check if there are no scheduled appointments
            displayError("\nNo scheduled appointments"); // Display error message
            pauseAndView(); // Pause before returning
            return; // Exit method
        }
        
        String docId = getValidatedString("\nEnter the doctor's id: "); // Get doctor's ID from user

        // Check if doctor can be found
        if (!patientAM.checkDoctor(docId)) {
            displayError("Doctor not found"); // Display error if doctor is not found
            return; // Exit method
        }
        
        displaySlots(docId); // Display available slots for selected doctor

        rescheduleSlot(docId); // Proceed to reschedule a slot for the selected doctor
    }

    /**
     * Displays available appointment slots for the specified doctor.
     *
     * @param id the unique identifier of the doctor whose slots are being displayed
     */
    private void displaySlots(String id) {
        ClearOutput.clearOutput(); // Clear previous output
        Boolean check = false; // Flag to check if any slots are found
        
        List<AppointmentSlot> slots = patientAM.getSlots(id); // Retrieve available slots for the doctor
        
        System.out.println("Current appointments with " + id);
        System.out.println("-----------------------------");
        
        for (AppointmentSlot slot : slots) {
            if (slot.getPatientId().equals(patient.getid())) { // Check if slot belongs to the current patient
                check = true; // Set flag if at least one matching slot is found
                System.out.println(slot.getDate() + "  " + slot.getTimestart() + " to " + slot.getTimeend() + "  "
                        + slot.getStatus()); // Print appointment details
            }
        }

        if (!check) { // Check if no matching slots were found
            displayError("No Available slots"); // Display error message
            pauseAndView(); // Pause before returning
            return; // Exit method
        }
    }

    /**
     * Prompts the user to reschedule an appointment by entering new date and time.
     *
     * @param id the unique identifier of the doctor whose appointment is being rescheduled
     */
    private void rescheduleSlot(String id) {
        System.out.println();
        
        String cancelappointmentId = getValidatedString(
                "Enter the date and time you wish to reschedule in the format YYYY-MM-DD/HH:MM \n e.g 2024-12-31/12:00 where 12:00 is the start time");
        
        cancelappointmentId = id + "/" + cancelappointmentId; // Combine doctor's ID with appointment details

        System.out.println();
        System.out.println("Available slots for " + id);
        System.out.println("------------------------------------------");
        
        List<AppointmentSlot> slots = patientAM.getAvailableSlots(id); // Retrieve available slots

        if (slots.size() == 0) { // Check if no available slots are found
            displayError("No Available slots"); // Display error message
            pauseAndView(); // Pause before returning
            return; // Exit method
        }

        for (AppointmentSlot slot : slots) { // Display available time slots 
            System.out.println(slot.getDate() + "  " + slot.getTimestart() + " to " + slot.getTimeend() + "  "
                    + slot.getStatus());
        }
        
        System.out.println();

        String appointmentId;
        
        appointmentId = getValidatedString(
                "Enter the date and time of the new appointment slot you wish to schedule:");
        
        appointmentId = id + "/" + appointmentId; // Combine doctor's ID with new appointment details

        if (!patientAM.checkSlotAvailable(appointmentId)) { 
            displayError("Invalid appointmentslot to reschedule"); // Display error if slot is not available
            pauseAndView(); 
            return; 
        } else if (!patientAM.checkSlotCancellable(cancelappointmentId)) { 
            displayError("Invalid cancelappointmentslot to reschedule"); // Display error if cancellation fails 
            pauseAndView(); 
            return; 
        }

        if (patientAM.requestSlot(appointmentId) && patientAM.cancelSlot(cancelappointmentId)) { 
            displaySuccess("Your appointment request has been sent to the doctor"); // Success message on booking 
            pauseAndView(); 
        } else {
            displayError("There is no such available slot, please enter a valid slot"); // Error message on failure 
        }
    }
}