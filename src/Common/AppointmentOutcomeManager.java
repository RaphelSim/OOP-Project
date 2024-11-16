package Common;

import Databases.AppointmentOutcomeDatabase;
import Databases.DoctorSchedule;
import DatabaseItems.AppointmentOutcome;
import DatabaseItems.AppointmentSlot;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code AppointmentOutcomeManager} class provides functionalities for managing
 * appointment outcomes. It allows adding, removing, and retrieving appointment outcomes
 * from the associated {@link AppointmentOutcomeDatabase}.
 */
public abstract class AppointmentOutcomeManager {
    protected final AppointmentOutcomeDatabase database;

    /**
     * Constructs an {@code AppointmentOutcomeManager} with the specified database.
     *
     * @param database the {@link AppointmentOutcomeDatabase} used to manage appointment outcomes
     */
    public AppointmentOutcomeManager(AppointmentOutcomeDatabase database) {
        this.database = database;
    }

    /**
     * Adds a new appointment outcome to the database if it does not already exist.
     *
     * @param outcome the {@link AppointmentOutcome} to be added
     * @param schedule the {@link DoctorSchedule} associated with the appointment
     * @return true if the outcome was added successfully; false if it already exists or the schedule is invalid
     */
    public boolean addOutcome(AppointmentOutcome outcome, DoctorSchedule schedule) {
        if (database.searchItem(outcome.getAppointmentId()) == null
                && schedule.searchItem(outcome.getAppointmentId()) != null) {
            database.addItem(outcome);
            AppointmentSlot slot = (AppointmentSlot) schedule.searchItem(outcome.getAppointmentId());
            slot.setStatus(AppointmentStatus.COMPLETED);
            return true; // Outcome added successfully
        } else {
            return false; // Outcome already exists
        }
    }

    /**
     * Removes an appointment outcome from the database by its unique identifier.
     *
     * @param appointmentID the unique identifier of the appointment outcome to be removed
     * @return true if the outcome was successfully removed; false otherwise
     */
    public boolean removeOutcome(String appointmentID) {
        return database.removeItem(appointmentID);
    }

    /**
     * Retrieves an appointment outcome by its unique identifier.
     *
     * @param appointmentID the unique identifier of the appointment outcome to retrieve
     * @return the {@link AppointmentOutcome} object if found; otherwise, returns null
     */
    public AppointmentOutcome getOutcome(String appointmentID) {
        return (AppointmentOutcome) database.searchItem(appointmentID);
    }

    /**
     * Retrieves all appointment outcomes from the database.
     *
     * @return a list of all {@link AppointmentOutcome} objects in the database
     */
    public List<AppointmentOutcome> getAllOutcomes() {
        return database.getRecords().stream() // Convert all DatabaseItems in this database to AppointmentOutcomes
                .filter(item -> item instanceof AppointmentOutcome)
                .map(item -> (AppointmentOutcome) item)
                .collect(Collectors.toList());
    }
}