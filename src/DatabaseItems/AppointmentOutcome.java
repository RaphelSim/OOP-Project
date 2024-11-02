package DatabaseItems;

import Common.AppointmentOutcomeStatus;
import Common.DatabaseItems;
import Common.ListConverter;

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

    public AppointmentOutcome(String ...params){
        deserialise(params);
    }

// appointment_id,patient_id,doctor_id,date,type_of_service,medication,consultation_notes,status

    //implement interface functions
    public void deserialise(String ...params){
        //Name,id,Password,Role
        this.appointment_id = params[0];
        this.patient_id = params[1];
        this.doctor_id = params[2];
        this.date = params[3];
        this.type_of_service = ListConverter.replaceWithComma(params[4]);
        this.medication = ListConverter.replaceWithComma(params[5]);
        this.consultation_notes = ListConverter.replaceWithComma(params[6]);
        this.status = AppointmentOutcomeStatus.fromString(params[7]);
    }

    public String serialise(){
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s\n",
        this.appointment_id,
        this.patient_id,
        this.doctor_id,
        this.date,
        ListConverter.replaceWithCurly(this.type_of_service),
        ListConverter.replaceWithCurly(this.medication),
        ListConverter.replaceWithCurly(this.consultation_notes),
        this.status.toString()
        );
    }

    public void printItem(){
        System.out.println(); // Print a new line for better readability
        System.out.println("Appointment ID: " + this.appointment_id);
        System.out.println("Doctor ID: " + this.doctor_id);
        System.out.println("Patient ID: " + this.patient_id);
        System.out.println("Date: " + this.date);
        System.out.println("Type of Service: " + this.type_of_service);
        System.out.println("Medication: " +this.medication);
        System.out.println("Consultation Notes: " + this.consultation_notes);
        System.out.println("Status: " + this.status);
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
