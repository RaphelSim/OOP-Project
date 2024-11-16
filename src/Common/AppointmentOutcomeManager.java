package Common;

import Databases.AppointmentOutcomeDatabase;
import Databases.DoctorSchedule;
import DatabaseItems.AppointmentOutcome;
import DatabaseItems.AppointmentSlot;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AppointmentOutcomeManager {
    protected final AppointmentOutcomeDatabase database;

    public AppointmentOutcomeManager(AppointmentOutcomeDatabase database) {
        this.database = database;
    }

    // Method to add a new outcome
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

    // Method to remove an outcome by appointment ID
    public boolean removeOutcome(String appointmentID) {
        if (database.removeItem(appointmentID)) {
            return true;
        } else {
            return false;
        }
    }

    // Retrieve an outcome by appointment ID
    public AppointmentOutcome getOutcome(String appointmentID) {
        return (AppointmentOutcome) database.searchItem(appointmentID);
    }

    // Get all outcomes
    public List<AppointmentOutcome> getAllOutcomes() {
        return database.getRecords().stream() // convert all databaseitems in this database to appointment outcome
                .filter(item -> item instanceof AppointmentOutcome)
                .map(item -> (AppointmentOutcome) item)
                .collect(Collectors.toList());
    }
}
