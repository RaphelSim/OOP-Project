package UI.AppointmentPages;

import java.time.LocalDate;
import DatabaseItems.Account;
import DatabaseItems.AppointmentSlot;
import Databases.DoctorSchedule;
import Common.ClearOutput;
import Common.DatabaseItems;
import Common.UserInterface;
import Controllers.AMManagers.DoctorAM;

/**
 * The {@code SetAvailabilityPage} class provides functionality for
 * setting the availability schedule of a doctor. It extends {@link UserInterface}
 * and interacts with the {@link DoctorSchedule} and {@link DoctorAM} managers
 * to manage appointment slots.
 */
public class SetAvailabilityPage extends UserInterface {
    private Account doctor; // The account of the doctor whose availability is being set
    private DoctorSchedule doctorSchedule; // The schedule of the doctor
    private DoctorAM doctorAM; // Manager for handling doctor's appointment-related operations

    /**
     * Constructs a {@code SetAvailabilityPage} with the specified
     * {@link Account}, {@link DoctorSchedule}, and {@link DoctorAM} instances.
     *
     * @param doctor the account of the doctor
     * @param doctorSchedule the schedule of the doctor
     * @param doctorAM the manager used to access and manage doctor's appointments
     */
    public SetAvailabilityPage(Account doctor, DoctorSchedule doctorSchedule, DoctorAM doctorAM) {
        this.doctor = doctor; // Initialize the doctor's account
        this.doctorSchedule = doctorSchedule; // Initialize the doctor's schedule
        this.doctorAM = doctorAM; // Initialize the appointment manager
    }

    /**
     * Allows the doctor to set their availability by specifying a date and time range.
     * It prompts for input, validates it, and updates the doctor's schedule accordingly.
     */
    public void setAvailability() {
        ClearOutput.clearOutput(); // Clear previous output
        int day = 0, month = 0, year = 0; // Variables for date input
        int startHours, endHours; // Variables for time input

        System.out.println("Welcome Dr." + doctor.getName());
        System.out.println("This is your current schedule: ");
        System.out.println("------------------------------------");
        
        // Display current schedule
        for (DatabaseItems item : doctorSchedule.getRecords()) {
            AppointmentSlot slot = (AppointmentSlot) item; // Cast to AppointmentSlot
            System.out.println(
                    slot.getDate() + " " + slot.getTimestart() + " to " + slot.getTimeend() + " " + slot.getStatus());
        }

        // Option to go back
        String choice = getValidatedString(
                "\nEnter 'q' to go back to menu, enter any character to proceed to setup your personal schedule");
        if (choice != null && choice.equals("q")) // Check if user wants to quit
            return;

        // Year input loop
        while (true) {
            System.out.println("\nSetup your Personal Schedule Availability:");
            System.out.print("Enter Year (e.g.: 2024): ");
            try {
                year = getIntInput(-1); // Get year input
                if (year > 0)
                    break; // Valid year entered
                else
                    System.out.println("Invalid year! Please enter again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a numeric value.");
            }
        }

        // Month input loop
        while (true) {
            System.out.print("Enter Month (1-12): ");
            try {
                month = getIntInput(-1); // Get month input
                if (month >= 1 && month <= 12)
                    break; // Valid month entered
                else
                    System.out.println("Invalid month. Please enter a value between 1 and 12.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }

        // Day input loop
        while (true) {
            System.out.print("Enter Day (1-31): ");
            try {
                day = getIntInput(-1); // Get day input
                if (doctorAM.isValidDate(year, month, day))
                    break; // Valid date entered
                else
                    System.out.println("Invalid date! Please enter a valid date for your input month and year.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }

        // Start Time input loop
        while (true) {
            System.out.print("Enter Start Hour e.g. 9 (Must be within 8 to 18): ");
            startHours = getIntInput(-1); // Get start hour input
            if (doctorAM.isValidHour(startHours))
                break; // Valid start hour entered
            else
                System.out.println("Invalid start time! Please enter a start time between 8 and 18.");
        }

        // End Time input loop
        while (true) {
            System.out.print("Enter End Hour e.g. 18 (Must be within 8 to 18 and after Start Hour): ");
            endHours = getIntInput(-1); // Get end hour input
            if (doctorAM.isValidHour(endHours) && doctorAM.isEndTimeAfterStart(startHours, endHours))
                break; // Valid end hour entered after start hour check
            else
                System.out.println("Invalid end time! Ensure it is after start time and within 08 to 18.");
        }

        // Display Personal Schedule Date and Time confirmation
        String startTime = doctorAM.convertHours(startHours);
        String endTime = doctorAM.convertHours(endHours);
        
        System.out.printf("\nYou have entered:\nDate: %02d/%02d/%04d\nTime: %s - %s\n", day, month, year, startTime,
                endTime);
        
        LocalDate confirmDate = LocalDate.of(year, month, day); // Create LocalDate object

        // Format them and split into 60-minute interval time slots assigning each to Appointment Slot Database Item
        doctorAM.generateTimeSlot(doctor.getid(), confirmDate, startTime, endTime); 
        doctorSchedule.sortAppointments(); // Sort appointments after generation
        
        displaySuccess("Available slots updated"); // Display success message for updating availability
        
        pauseAndView(); // Pause before returning to allow user to view output
    }
}