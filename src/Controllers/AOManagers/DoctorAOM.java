package Controllers.AOManagers;

import Common.AppointmentOutcomeManager;
import Common.AppointmentOutcomeStatus;
import Databases.AppointmentOutcomeDatabase;
import DatabaseItems.AppointmentOutcome;

public class DoctorAOM extends AppointmentOutcomeManager {

    public DoctorAOM(AppointmentOutcomeDatabase database) {
        super(database);
    }

    // Doctor-specific method to edit an outcome
    public boolean editOutcome(String appointmentId, String newDate, String newTypeOfService, String newMedication, String newConsultationNotes, AppointmentOutcomeStatus newStatus) {
    AppointmentOutcome record = getOutcome(appointmentId);
    if (record == null) {
        System.out.println("Record not found.");
        return false;
    }

    // Set each field individually using existing setters
    record.setDate(newDate);
    record.setTypeOfService(newTypeOfService);
    record.setMedication(newMedication);
    record.setConsultationNotes(newConsultationNotes);
    record.setStatus(newStatus);

    database.storeToCSV(); // Save changes to the database
    return true;
    }

}


