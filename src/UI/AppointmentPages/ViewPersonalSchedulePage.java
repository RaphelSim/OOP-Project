package UI.AppointmentPages;

import DatabaseItems.Account;
import DatabaseItems.AppointmentSlot;
import java.util.List;

import Common.ClearOutput;
import Common.DatabaseItems;
import Common.UserInterface;

/**
 * The {@code ViewPersonalSchedulePage} class provides functionality for
 * viewing a doctor's personal schedule. It extends {@link UserInterface}
 * and displays available appointment slots for a specific doctor.
 */
public class ViewPersonalSchedulePage extends UserInterface {

    /**
     * Displays the doctor's personal schedule, including time slots
     * and their statuses. If no schedule is set, an appropriate message is displayed.
     *
     * @param doctor         the {@link Account} representing the doctor
     * @param personalSchedule a list of {@link DatabaseItems} representing the doctor's scheduled appointments
     */
    public void displayDocTimeSlot(Account doctor, List<DatabaseItems> personalSchedule) {
        ClearOutput.clearOutput(); // Clear previous output
        
        if (personalSchedule.size() == 0) { // Check if there are no scheduled appointments
            System.out.println("You have yet to set your Personal Schedule! Use Set Availability option.");
        } else {
            int counter = 1; // Counter for appointment listing
            System.out.println("");
            System.out.println("Dr. " + doctor.getName() + "'s Personal Schedule Timeslots:");
            System.out.println("No. |Appointment ID|            |Date|      |Start Time|  |End Time|  |Status|");
            System.out.println("-------------------------------------------------------------------------------------");
            
            for (DatabaseItems tS : personalSchedule) { // Iterate through scheduled appointments
                AppointmentSlot slot = (AppointmentSlot) tS; // Cast to AppointmentSlot
                System.out.print(counter + ".  ");
                System.out.print(slot.getAppointmentId() + "   ");
                System.out.print(slot.getDate() + "  ");
                System.out.print(slot.getTimestart() + "         ");
                System.out.print(slot.getTimeend() + "       ");
                System.out.print(slot.getStatus() + "\n"); // Print appointment details
                counter++; // Increment counter
            }
        }
        pauseAndView(); // Pause to allow user to view output
    }
}