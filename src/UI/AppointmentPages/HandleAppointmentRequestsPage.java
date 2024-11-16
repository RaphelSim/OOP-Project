package UI.AppointmentPages;

import java.util.List;

import Common.AppointmentStatus;
import Common.ClearOutput;
import Common.UserInterface;
import Controllers.AMManagers.DoctorAM;
import DatabaseItems.AppointmentSlot;
import DatabaseItems.Account;

/**
 * The {@code HandleAppointmentRequestsPage} class provides functionality for
 * handling appointment requests for a doctor. It extends {@link UserInterface}
 * and interacts with the {@link DoctorAM} manager to manage appointment requests.
 */
public class HandleAppointmentRequestsPage extends UserInterface {
    private DoctorAM doctorAM; // Manager for handling doctor's appointment-related operations
    private Account doctor; // The account of the doctor handling the requests

    /**
     * Constructs a {@code HandleAppointmentRequestsPage} with the specified
     * {@link DoctorAM} and {@link Account} instances.
     *
     * @param doctorAM the manager used to access and manage appointment requests
     * @param doctor   the account of the doctor handling the requests
     */
    public HandleAppointmentRequestsPage(DoctorAM doctorAM, Account doctor) {
        this.doctorAM = doctorAM; // Initialize the appointment manager
        this.doctor = doctor; // Initialize the doctor's account
    }

    /**
     * Displays requested appointment slots and allows the doctor to accept or reject
     * appointments. It retrieves available slots, displays them, and handles user input
     * for managing appointment requests.
     */
    public void handleAppointmentRequests() {
        List<AppointmentSlot> slots = doctorAM.getAvailableSlots(); // Retrieve available appointment slots
        String appID; // Variable to store appointment ID
        int choice; // Variable to store user choice

        ClearOutput.clearOutput(); // Clear previous output
        System.out.println("Requested Appointment slot(s)");
        System.out.println("----------------------------");
        System.out.println("|Date|         |Time|");

        // Display requested appointment slots
        for (AppointmentSlot slot : slots) {
            if (slot.getStatus() == AppointmentStatus.REQUESTED) { // Check if slot is requested
                System.out.println(slot.getDate() + "    " + slot.getTimestart()
                        + " to " + slot.getTimeend());
                System.out.println();
            }
        }

        if (slots.size() == 0) { // Check if no requested appointments are found
            displayError("No requested appointments."); // Display error message
            pauseAndView(); // Pause before returning
            return; // Exit method
        }

        System.out.println();
        
        while (true) {
            appID = getValidatedString(
                    "Enter 'q' to quit or\nEnter date and time in the format YYYY-MM-DD/HH:mm \n(e.g./2024-10-29/12:00):");
            if (appID.equals("q")) // Check if user wants to quit
                return;

            appID = doctor.getid() + "/" + appID; // Combine doctor's ID with appointment details
            
            if (doctorAM.isAppIDExist(appID)
                    && doctorAM.getSlotWithAppID(appID).getStatus().equals(AppointmentStatus.REQUESTED)) {
                ClearOutput.clearOutput(); // Clear previous output
                
                while (true) {
                    AppointmentSlot selected = doctorAM.getSlotWithAppID(appID); // Retrieve selected slot
                    
                    System.out.println("You have selected this appointment slot: " + selected.getDate() + "  "
                            + selected.getTimestart() + " to " + selected.getTimeend());
                    
                    System.out.println("Enter 1. Accept or 2. Reject - this appointment slot:");
                    choice = getIntInput(-1); // Get user choice

                    if (choice == 1) { // Accept request case
                        doctorAM.acceptRequest(selected); // Accept the appointment request
                        break; // Exit inner loop
                    } else if (choice == 2) { // Reject request case
                        doctorAM.rejectRequest(selected); // Reject the appointment request
                        break; // Exit inner loop
                    } else {
                        System.out.println("Invalid option! Enter option 1 or 2 only"); // Handle invalid input
                    }
                }
                break; // Exit outer loop after processing request
            } else {
                System.out.println("Error! Please enter a valid Appointment ID!"); // Handle invalid appointment ID input
            }
        }

        pauseAndView(); // Pause before returning to allow user to view output
    }
}