package UI.AOMUI;

import Controllers.AOManagers.DoctorAOM;
import Common.UserInterface;
import Common.AppointmentStatus;
import DatabaseItems.AppointmentSlot;
import Databases.DoctorSchedule;
import Common.ClearOutput;
import Common.DatabaseItems;

/**
 * The {@code DoctorOutcomeInterface} class provides functionality for managing
 * appointment outcomes for doctors. It extends {@link UserInterface}
 * and interacts with the {@link DoctorAOM} manager to handle appointment outcomes.
 */
public class DoctorOutcomeInterface extends UserInterface {
    private DoctorAOM doctorManager; // Manager for handling doctor's appointment-related operations
    private DoctorSchedule schedule; // Schedule of appointments for the doctor

    /**
     * Constructs a {@code DoctorOutcomeInterface} with the specified
     * {@link DoctorAOM} and {@link DoctorSchedule} instances.
     *
     * @param doctorManager   the manager used to access and manage doctor's appointment outcomes
     * @param doctorSchedule  the schedule of appointments for the doctor
     */
    public DoctorOutcomeInterface(DoctorAOM doctorManager, DoctorSchedule doctorSchedule) {
        this.doctorManager = doctorManager; // Initialize the doctor manager
        this.schedule = doctorSchedule; // Initialize the doctor's schedule
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Prompts the user to select an option for managing appointment outcomes.
     * Displays a menu of options and executes the corresponding functionality based on user input.
     */
    public void displayOptions() {
        boolean exit = false; // Flag to control the loop
        while (!exit) {
            ClearOutput.clearOutput(); // Clear previous output
            System.out.println("Doctor Interface - Appointment Outcome Management");
            System.out.println("1. View Confirmed Appointments");
            System.out.println("2. Record Appointment Outcome");
            System.out.println("3. Exit");

            int choice = getIntInput(-1); // Get user choice
            switch (choice) {
                case 1:
                    viewConfirmedAppointments(); // View confirmed appointments
                    break;
                case 2:
                    selectAppointment(); // Select an appointment to record an outcome
                    break;
                case 3:
                    exit = true; // Exit the loop
                    break;
                default:
                    displayError("Invalid option. Please try again."); // Handle invalid input
            }
        }
    }

    /**
     * Displays all confirmed appointments from the doctor's schedule.
     */
    public void viewConfirmedAppointments() {
        ClearOutput.clearOutput(); // Clear previous output
        System.out.println("All confirmed appointments: ");
        System.out.println("------------------------------");
        
        for (DatabaseItems item : schedule.getRecords()) { // Iterate through scheduled records
            AppointmentSlot slot = (AppointmentSlot) item; // Cast to AppointmentSlot
            if (slot.getStatus() == AppointmentStatus.CONFIRMED) { // Check if slot is confirmed
                slot.printItem(); // Print appointment details
            }
        }
        
        pauseAndView(); // Pause before returning to allow user to view output
    }

    /**
     * Prompts the user to select a confirmed appointment and record its outcome.
     */
    public void selectAppointment() {
        ClearOutput.clearOutput(); // Clear previous output
        System.out.println("All confirmed appointments");
        System.out.println("--------------------------------");
        
        for (DatabaseItems item : schedule.getRecords()) { // Iterate through scheduled records
            AppointmentSlot slot = (AppointmentSlot) item; // Cast to AppointmentSlot
            
            if (slot.getStatus() == AppointmentStatus.CONFIRMED) { // Check if slot is confirmed
                System.out.println(slot.getPatientId() + "  " + slot.getDate() + "  " + slot.getTimestart() + " to "
                        + slot.getTimeend()); // Print appointment details
            }
        }
        
        System.out.println("--------------------------------");
        System.out.println();
        
        String aptID = getValidatedString(
                "Enter 'q' to go back\n\nOR\n\nEnter the date and time in the format YYYY-MM-DD/HH:mm \n e.g 2024-12-31/13:00");
        
        if (aptID != null && aptID.equals("q")) // Check if user wants to go back
            return;
        
        aptID = doctorManager.getDoctorId() + "/" + aptID; // Combine doctor's ID with appointment details
        
        if (schedule.searchItem(aptID) == null) { // Check if appointment exists in schedule
            displayError("Appointment does not exist"); // Display error if not found
            return;
        } else {
            AppointmentSlot slot = (AppointmentSlot) schedule.searchItem(aptID); // Retrieve selected slot
            
            if (slot == null || slot.getStatus() != AppointmentStatus.CONFIRMED) { // Check if slot is valid
                displayError("Invalid slot"); // Display error if invalid slot is found
                return;
            } else {
                recordOutcome(slot.getAppointmentId(), slot.getDoctorId(), slot.getPatientId(), slot.getDate()); // Record outcome for selected appointment
            }
        }
    }

    /**
     * Records the outcome of an appointment based on provided details.
     *
     * @param appointmentId   the unique identifier of the appointment being recorded
     * @param doctorId       the unique identifier of the doctor associated with the appointment
     * @param patientId      the unique identifier of the patient associated with the appointment
     * @param date           the date of the appointment being recorded
     */
    public void recordOutcome(String appointmentId, String doctorId, String patientId, String date) {
        ClearOutput.clearOutput(); // Clear previous output
        
        String typeOfService = getStringInput("Enter Type of Service: "); // Get type of service from user
        String medication = getStringInput("Enter Medication: "); // Get medication from user
        String consultationNotes = getStringInput("Enter Consultation Notes: "); // Get consultation notes from user

        if (doctorManager.writeOutcome(appointmentId, patientId, date, typeOfService, medication, consultationNotes)) {
            displaySuccess("Appointment outcome recorded successfully."); // Success message on recording outcome
        } else {
            displayError("Failed to record appointment outcome. An outcome with this ID may already exist."); // Error message on failure 
        }
    }
}