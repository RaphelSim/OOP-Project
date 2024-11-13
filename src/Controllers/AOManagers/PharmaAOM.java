package Controllers.AOManagers;

import java.util.List;

import Common.AppointmentOutcomeManager;
import Databases.AppointmentOutcomeDatabase;
import DatabaseItems.AppointmentOutcome;
import Common.AppointmentOutcomeStatus;

public class PharmaAOM extends AppointmentOutcomeManager {

    public PharmaAOM(AppointmentOutcomeDatabase database) {
        super(database);
    }

    public boolean viewOutcome(String appointmentId) {
        AppointmentOutcome record = getOutcome(appointmentId);
        if (record == null || record.getStatus() != AppointmentOutcomeStatus.PENDING) {
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
            if (item.getStatus() == AppointmentOutcomeStatus.PENDING)
                item.printItem();
        }
        return true;
    }

    public boolean updateOutcomeStatus(String appointmentId) {
        AppointmentOutcome record = getOutcome(appointmentId);
        if (record == null || record.getStatus() != AppointmentOutcomeStatus.PENDING) {
            return false;
        }
        record.setStatus(AppointmentOutcomeStatus.DISPENSED);
        return true;
    }
}
