package DatabaseItems;

import Common.AppointmentOutcomeStatus;
import Common.DatabaseItems;

public class AppointmentOutcome implements DatabaseItems {

    private String appointment_id;
    private String date;
    private String type_of_service;
    private String medication;
    private String consultation_notes;
    private AppointmentOutcomeStatus status;

    // Constructor
    public AppointmentOutcome(String appointment_id, String date, String type_of_service, String medication,
            String consultation_notes, AppointmentOutcomeStatus status) {
        this.appointment_id = appointment_id;
        this.date = date;
        this.type_of_service = type_of_service;
        this.medication = medication;
        this.consultation_notes = consultation_notes;
        this.status = status;
    }

    // Getters
    public String getAppointmentId() {
        return appointment_id;
    }

    public String getDate() {
        return date;
    }

    public String getTypeOfService() {
        return type_of_service;
    }

    public String getMedication() {
        return medication;
    }

    public String getConsultationNotes() {
        return consultation_notes;
    }

    public AppointmentOutcomeStatus getStatus() {
        return status;
    }

    // Setters
    public void setDate(String date) {
        this.date = date;
    }

    public void setTypeOfService(String type_of_service) {
        this.type_of_service = type_of_service;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public void setConsultationNotes(String consultation_notes) {
        this.consultation_notes = consultation_notes;
    }

    public void setStatus(AppointmentOutcomeStatus status) {
        this.status = status;
    }
}
