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
    public boolean writeOutcome(String appointmentId, String patientId, String newDate, String newTypeOfService,
            String newMedication,
            String newConsultationNotes) {

        AppointmentOutcome record = new AppointmentOutcome(
                appointmentId, doctorId, patientId, newDate, newTypeOfService, newMedication, newConsultationNotes,
                AppointmentOutcomeStatus.PENDING);

        return addOutcome(record);
    }
}
