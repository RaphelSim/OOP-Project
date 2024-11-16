package Controllers.AOManagers;

import java.util.List;

import Common.AppointmentOutcomeManager;
import Databases.AppointmentOutcomeDatabase;
import DatabaseItems.AppointmentOutcome;

/**
 * The {@code AdminAOM} class serves as the Appointment Outcome Manager for administrators.
 * It provides functionalities to view appointment outcomes from the appointment outcome database.
 */
public class AdminAOM extends AppointmentOutcomeManager {

    /**
     * Constructs an {@code AdminAOM} instance with the specified appointment outcome database.
     *
     * @param database the {@link AppointmentOutcomeDatabase} used to manage appointment outcomes
     */
    public AdminAOM(AppointmentOutcomeDatabase database) {
        super(database);
    }

    /**
     * Views an appointment outcome by its unique identifier.
     *
     * @param appointmentId the unique identifier of the appointment whose outcome is to be viewed
     * @return true if the outcome was found and printed; false if no outcome exists for the given ID
     */
    public boolean viewOutcome(String appointmentId) {
        AppointmentOutcome record = getOutcome(appointmentId);
        if (record == null) {
            return false;
        }
        record.printItem();
        return true;
    }

    /**
     * Views all appointment outcomes in the database.
     *
     * @return true if outcomes were found and printed; false if no outcomes exist
     */
    public boolean viewAllOutcome() {
        List<AppointmentOutcome> records = getAllOutcomes();
        if (records == null || records.isEmpty())
            return false;
        for (AppointmentOutcome item : records) {
            item.printItem();
        }
        return true;
    }
}