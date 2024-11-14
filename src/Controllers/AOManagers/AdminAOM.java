package Controllers.AOManagers;

import java.util.List;

import Common.AppointmentOutcomeManager;
import Databases.AppointmentOutcomeDatabase;
import DatabaseItems.AppointmentOutcome;

public class AdminAOM extends AppointmentOutcomeManager {

    public AdminAOM(AppointmentOutcomeDatabase database) {
        super(database);
    }

    // Method to view an appointment outcome by ID
    public boolean viewOutcome(String appointmentId) {
        AppointmentOutcome record = getOutcome(appointmentId);
        if (record == null) {
            return false;
        }
        record.printItem();
        return true;
    }

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
