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
        this.doctor_id = doctor_id;
        this.appointment_id = doctor_id + "/" + patient_id + "/" + date + "/" + timestart; // create the appointment id
        this.patient_id = patient_id;
        this.date = date;
        this.timestart = timestart;
        this.timeend = timeend;
        this.status = status;
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
