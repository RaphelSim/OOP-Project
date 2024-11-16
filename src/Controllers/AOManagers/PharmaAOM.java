package Controllers.AOManagers;

import java.util.List;

import Common.AppointmentOutcomeManager;
import Databases.AppointmentOutcomeDatabase;
import DatabaseItems.AppointmentOutcome;
import Common.AppointmentOutcomeStatus;

/**
 * The {@code PharmaAOM} class manages appointment outcomes specific to pharmacists.
 * It extends {@link AppointmentOutcomeManager} to provide functionalities for viewing
 * and updating appointment outcomes.
 */
public class PharmaAOM extends AppointmentOutcomeManager {

    /**
     * Constructs a {@code PharmaAOM} with the specified appointment outcome database.
     *
     * @param database the {@link AppointmentOutcomeDatabase} used to manage appointment outcomes
     */
    public PharmaAOM(AppointmentOutcomeDatabase database) {
        super(database);
    }

    /**
     * Views the outcome of a specific appointment by its ID.
     *
     * @param appointmentId the unique identifier of the appointment
     * @return true if the outcome was successfully viewed; false if not found or not pending
     */
    public boolean viewOutcome(String appointmentId) {
        AppointmentOutcome record = getOutcome(appointmentId);
        if (record == null || record.getStatus() != AppointmentOutcomeStatus.PENDING) {
            return false; // Outcome not found or not pending
        }
        record.printItem(); // Print the outcome details
        return true; // Outcome viewed successfully
    }

    /**
     * Views all pending appointment outcomes.
     *
     * @return true if any pending outcomes were successfully viewed; false if none found
     */
    public boolean viewAllOutcome() {
        List<AppointmentOutcome> records = getAllOutcomes();
        if (records == null || records.isEmpty())
            return false; // No records found
        for (AppointmentOutcome item : records) {
            if (item.getStatus() == AppointmentOutcomeStatus.PENDING)
                item.printItem(); // Print each pending outcome
        }
        return true; // Outcomes viewed successfully
    }

    /**
     * Updates the status of a specific appointment outcome to DISPERSED.
     *
     * @param appointmentId the unique identifier of the appointment
     * @return true if the status was successfully updated; false if not found or not pending
     */
    public boolean updateOutcomeStatus(String appointmentId) {
        AppointmentOutcome record = getOutcome(appointmentId);
        if (record == null || record.getStatus() != AppointmentOutcomeStatus.PENDING) {
            return false; // Outcome not found or not pending
        }
        record.setStatus(AppointmentOutcomeStatus.DISPENSED); // Update status to DISPENSED
        return true; // Status updated successfully
    }
}