package DatabaseItems;

import Common.AppointmentOutcomeStatus;
import Common.DatabaseItems;

public class AppointmentOutcome implements DatabaseItems {

    private String appointment_id;
    private String doctor_id;
    private String patient_id;
    private String date;
    private String type_of_service;
    private String medication;
    private String consultation_notes;
    private AppointmentOutcomeStatus status;

    // Constructor
    public AppointmentOutcome(String appointment_id,String doctor_id,String patient_id, String date, String type_of_service, String medication,
            String consultation_notes, AppointmentOutcomeStatus status) {
        this.appointment_id = appointment_id;
        this.date = date;
        this.type_of_service = type_of_service;
        this.medication = medication;
        this.consultation_notes = consultation_notes;
        this.status = status;
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
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

    public String getDoctorId(){
        return doctor_id;
    }

    public String getPatientId(){
        return patient_id;
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

    public void setPatientId(String patient_id){
        this.patient_id = patient_id;
    }

    public void setDoctorId(String doctor_id){
        this.doctor_id = doctor_id;
    }
}
