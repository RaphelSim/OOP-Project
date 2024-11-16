package UI.AppointmentPages;

import java.util.List;

import Common.AppointmentStatus;
import Common.ClearOutput;
import Common.UserInterface;
import Controllers.AMManagers.PatientAM;
import DatabaseItems.Account;
import DatabaseItems.AppointmentSlot;

/**
 * The {@code ScheduleAppointmentPage} class provides functionality for
 * scheduling appointments with doctors. It extends {@link UserInterface}
 * and interacts with the {@link PatientAM} manager to retrieve doctor
 * and appointment details.
 */
public class ScheduleAppointmentPage extends UserInterface {
    private PatientAM patientAM; // Manager for handling patient's appointment-related operations

    /**
     * Constructs a {@code ScheduleAppointmentPage} with the specified
     * {@link PatientAM} instance.
     *
     * @param patientAM the manager used to access and manage patient's appointments
     */
    public ScheduleAppointmentPage(PatientAM patientAM) {
        this.patientAM = patientAM; // Initialize the appointment manager
    }

    /**
     * Displays a list of doctors and allows the user to select a doctor
     * to schedule an appointment. It retrieves available slots for the
     * selected doctor and prompts the user to book a slot.
     */
    public void displayOptions() {
        ClearOutput.clearOutput(); // Clear previous output
        System.out.println("List of Doctors");
        System.out.println("--------------------");
        
        // Display list of doctors
        for (Account doctor : patientAM.getDocList()) {
            System.out.println(doctor.getid() + "  " + doctor.getName());
        }

        String docId = getValidatedString("\nEnter the Doctor's id: "); // Get doctor's ID from user

        // Check if doctor can be found
        if (!patientAM.checkDoctor(docId)) {
            displayError("Doctor not found"); // Display error if doctor is not found
            return; // Exit method
        }
        
        displaySlots(docId); // Display available slots for selected doctor

        bookSlot(docId); // Proceed to book a slot for the selected doctor
    }

    /**
     * Displays available appointment slots for the specified doctor.
     *
     * @param id the unique identifier of the doctor whose slots are being displayed
     */
    private void displaySlots(String id) {
        ClearOutput.clearOutput(); // Clear previous output
        List<AppointmentSlot> slots = patientAM.getAvailableSlots(id); // Retrieve available slots
        
        System.out.println("Available slots for " + id);
        System.out.println("------------------------");
        
        // Display available time slots
        for (AppointmentSlot slot : slots) {
            if (slot.getStatus() == AppointmentStatus.FREE) { // Check if slot is free
                System.out.println(slot.getDate() + "  " + slot.getTimestart() + " to " + slot.getTimeend());
            }
        }

        if (slots.size() == 0) { // Check if no available slots are found
            displayError("No available appointments"); // Display error message
            pauseAndView(); // Pause before returning
            return; // Exit method
        }
    }

    /**
     * Prompts the user to book an appointment slot with the specified doctor.
     *
     * @param id the unique identifier of the doctor with whom the appointment is being booked
     */
    private void bookSlot(String id) {
        System.out.println();
        
        String appointmentId = getValidatedString(
                "Enter the date and time you wish to book in the format YYYY-MM-DD/HH:MM \n e.g 2024-12-31/12:00 where 12:00 is the start time");
        
        appointmentId = id + "/" + appointmentId; // Combine doctor's ID with appointment details
        
        if (patientAM.requestSlot(appointmentId)) // Attempt to request the slot
            displaySuccess("Your appointment request has been sent to the doctor"); // Success message
        else
            displayError("There is no such available slot"); // Error message if slot is not available
    }
}