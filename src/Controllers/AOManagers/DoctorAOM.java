package Controllers.AOManagers;

import Common.AppointmentOutcomeManager;
import Common.AppointmentOutcomeStatus;
import Databases.AppointmentOutcomeDatabase;
import DatabaseItems.AppointmentOutcome;

public class DoctorAOM extends AppointmentOutcomeManager {
    private String doctorId;

    public DoctorAOM(AppointmentOutcomeDatabase database, String doctorId) {
        super(database);
        this.doctorId = doctorId;
    }
    public String getDoctorId() {
        return doctorId; 
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

        // Use the inherited saveOutcome method to update and save the changes
        return saveOutcome(record);
    }

    public boolean addOutcome(AppointmentOutcome newOutcome) {
        return super.addOutcome(newOutcome);
    }
}



