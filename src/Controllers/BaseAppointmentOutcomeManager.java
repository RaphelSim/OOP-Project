// BaseAppointmentOutcomeManager.java
package Controllers;

import Databases.AppointmentOutcomeDatabase;
import DatabaseItems.AppointmentOutcome;

import java.util.List;

public abstract class BaseAppointmentOutcomeManager {
    protected AppointmentOutcomeDatabase database;

    public BaseAppointmentOutcomeManager(AppointmentOutcomeDatabase database) {
        this.database = database;
    }

    // Shared data management logic (without printing)
    public void addOutcome(AppointmentOutcome outcome) {
        database.addRecord(outcome);
    }

    public void removeOutcome(String appointmentID) {
        database.removeRecord(appointmentID);
    }

    public AppointmentOutcome getOutcome(String appointmentID) {
        return database.getRecord(appointmentID);
    }

    public List<AppointmentOutcome> getAllOutcomes() {
        return database.getRecords();
    }

    // Optionally, define additional methods without enforcing display-related actions.
}

