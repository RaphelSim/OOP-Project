package Controllers.AOManagers;

import Common.AppointmentOutcomeManager;
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

    public boolean editOutcome(String appointmentId, String newTypeOfService, String newMedication, String newConsultationNotes) {
        AppointmentOutcome record = getOutcome(appointmentId);
        if (record == null) {
            System.out.println("Record not found.");
            return false;
        }
        if (!record.getDoctorId().equals(this.doctorId)) {
            System.out.println("You are not authorized to edit this outcome.");
            return false;
        }

        record.setTypeOfService(newTypeOfService);
        record.setMedication(newMedication);
        record.setConsultationNotes(newConsultationNotes);
        return true;
    }

    public boolean addOutcome(AppointmentOutcome newOutcome) {
        return super.addOutcome(newOutcome);
    }
}



