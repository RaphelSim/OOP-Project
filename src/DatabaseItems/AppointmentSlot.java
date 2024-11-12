package DatabaseItems;

import Common.AppointmentStatus;
import Common.DatabaseItems;

public class AppointmentSlot implements DatabaseItems {
    private String appointment_id;
    private String patient_id;
    private String doctor_id;
    private String date;
    private String timestart;
    private String timeend;
    private AppointmentStatus status;

    // If appointment id provided
    public AppointmentSlot(String appointment_id, String patient_id, String doctor_id, String date, String timestart,
            String timeend,
            AppointmentStatus status) {
        this.doctor_id = doctor_id;
        this.appointment_id = appointment_id;
        this.patient_id = patient_id;
        this.date = date;
        this.timestart = timestart;
        this.timeend = timeend;
        this.status = status;
    }

    // If appointment id not provided
    public AppointmentSlot(String patient_id, String doctor_id, String date, String timestart, String timeend,
            AppointmentStatus status) {
        this(doctor_id + "/" + date + "/" + timestart,patient_id,doctor_id,date,timestart,timeend,status);
    }

    //call the deserialisation method
    public AppointmentSlot(String ...params){
        deserialise(params);
    }

    //appointment_id,patient_id,date,timestart,timeend,status
    //interface functions
    public void deserialise(String ...params){
        this.appointment_id = params[0];
        this.patient_id = params[1];
        this.date = params[2];
        this.timestart = params[3];
        this.timeend = params[4];
        this.status = AppointmentStatus.fromString(params[5]);
    }

    public String serialise(){
        return String.format("%s,%s,%s,%s,%s,%s\n",
        this.appointment_id,
        this.patient_id,
        this.date,
        this.timestart,
        this.timeend,
        this.status.toString());
    }

    public void printItem(){
        System.out.println(); // add a line break to improve readability
        System.out.println("Appointment ID: " + this.appointment_id);
        System.out.println("Patient ID: " + this.patient_id);
        System.out.println("Date: " +this.date);
        System.out.println("Time Start: " + this.timestart);
        System.out.println("Time End: " + this.timeend);
        System.out.println("Status: " + this.status);
    }

    // Getters
    public String getAppointmentId() {
        return appointment_id;
    }

    public String getPatientId() {
        return patient_id;
    }

    public String getDate() {
        return date;
    }

    public String getTimestart() {
        return timestart;
    }

    public String getTimeend() {
        return timeend;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public String getDoctorId() {
        return doctor_id;
    }

    // Setters (all except appointment_id)
    public void setPatientId(String patient_id) {
        this.patient_id = patient_id;
    }

    public void setDoctorId(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTimestart(String timestart) {
        this.timestart = timestart;
    }

    public void setTimeend(String timeend) {
        this.timeend = timeend;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
