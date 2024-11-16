package UI.AppointmentPages;

import java.util.List;

import Common.AppointmentStatus;
import Common.ClearOutput;
import Common.UserInterface;
import Controllers.AMManagers.DoctorAM;
import DatabaseItems.AppointmentSlot;

/**
 * The {@code ViewUpcomingAppointmentsPage} class provides functionality for
 * viewing upcoming appointments for a doctor. It extends {@link UserInterface}
 * and interacts with the {@link DoctorAM} manager to retrieve appointment details.
 */
public class ViewUpcomingAppointmentsPage extends UserInterface {
    private DoctorAM doctorAM; // Manager for handling doctor's appointments

    /**
     * Constructs a {@code ViewUpcomingAppointmentsPage} with the specified
     * {@link DoctorAM} instance.
     *
     * @param doctorAM the manager used to access and manage doctor's appointments
     */
    public ViewUpcomingAppointmentsPage(DoctorAM doctorAM) {
        this.doctorAM = doctorAM; // Initialize the appointment manager
    }

    /**
     * Displays upcoming appointments for the doctor.
     * It retrieves confirmed appointments and displays their details.
     * If no upcoming appointments are found, an error message is displayed.
     */
    public void viewUpcomingAppointments() {
        List<AppointmentSlot> slots = doctorAM.getAvailableSlots(); // Retrieve available appointment slots
        ClearOutput.clearOutput(); // Clear previous output
        System.out.println("Upcoming Appointments");
        System.out.println("---------------------");
        System.out.println("    |Appointment ID|         |Patient ID|     |Date|         |Time|");
        
        for (AppointmentSlot slot : slots) {
            if (slot.getStatus() == AppointmentStatus.CONFIRMED) { // Check if appointment is confirmed
                System.out.println(slot.getAppointmentId() + "      " + slot.getPatientId() + "     " +
                        slot.getDate() + "    " + slot.getTimestart() + " to " + slot.getTimeend());
                System.out.println();
            }
        }
        pauseAndView(); // Pause to allow user to view output
        
        if (slots.size() == 0) { // Check if no confirmed appointments were found
            displayError("No upcoming appointments"); // Display error message
            pauseAndView(); // Pause before returning
            return; // Exit method
        }
    }
}