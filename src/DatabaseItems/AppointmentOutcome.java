package DatabaseItems;

import Common.AppointmentOutcomeStatus;
import Common.DatabaseItems;
import Common.ListConverter;

/**
 * The {@code AppointmentOutcome} class represents the outcome of a medical appointment.
 * It includes details such as appointment ID, doctor ID, patient ID, date, type of service,
 * medication prescribed, consultation notes, and the status of the appointment outcome.
 */
public class AppointmentOutcome implements DatabaseItems {

    private String appointment_id;
    private String doctor_id;
    private String patient_id;
    private String date;
    private String type_of_service;
    private String medication;
    private String consultation_notes;
    private AppointmentOutcomeStatus status;

    /**
     * Constructs an {@code AppointmentOutcome} with specified details.
     *
     * @param appointment_id      the unique identifier for the appointment
     * @param doctor_id           the unique identifier for the doctor
     * @param patient_id          the unique identifier for the patient
     * @param date                the date of the appointment
     * @param type_of_service     the type of service provided during the appointment
     * @param medication          prescribed medication
     * @param consultation_notes   notes from the consultation
     * @param status              the status of the appointment outcome
     */
    public AppointmentOutcome(String appointment_id, String doctor_id, String patient_id, 
                              String date, String type_of_service, String medication,
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

    /**
     * Constructs an {@code AppointmentOutcome} by deserializing from a set of parameters.
     *
     * @param params an array of strings containing appointment details in order:
     *               appointment_id, patient_id, doctor_id, date, type_of_service,
     *               medication, consultation_notes, status
     */
    public AppointmentOutcome(String ...params){
        deserialise(params);
    }

    /**
     * Deserializes appointment details from a string array.
     *
     * @param params an array of strings containing appointment details in order:
     *               appointment_id, patient_id, doctor_id, date, type_of_service,
     *               medication, consultation_notes, status
     */
    public void deserialise(String ...params){
        this.appointment_id = params[0];
        this.patient_id = params[1];
        this.doctor_id = params[2];
        this.date = params[3];
        this.type_of_service = ListConverter.replaceWithComma(params[4]);
        this.medication = ListConverter.replaceWithComma(params[5]);
        this.consultation_notes = ListConverter.replaceWithComma(params[6]);
        this.status = AppointmentOutcomeStatus.fromString(params[7]);
    }

    /**
     * Serializes the appointment outcome into a comma-separated string.
     *
     * @return a string representation of the appointment outcome
     */
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

    /**
     * Prints the details of the appointment outcome to standard output.
     */
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

    /**
     * Returns the unique identifier for the appointment.
     *
     * @return the appointment ID
     */
    public String getAppointmentId() {
        return appointment_id;
    }

    /**
     * Returns the date of the appointment.
     *
     * @return the date of the appointment
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the type of service provided during the appointment.
     *
     * @return the type of service
     */
    public String getTypeOfService() {
        return type_of_service;
    }

    /**
     * Returns the prescribed medication.
     *
     * @return the medication prescribed
     */
    public String getMedication() {
        return medication;
    }

    /**
     * Returns notes from the consultation.
     *
     * @return consultation notes
     */
    public String getConsultationNotes() {
        return consultation_notes;
    }

    /**
     * Returns the status of the appointment outcome.
     *
     * @return the status of the outcome
     */
    public AppointmentOutcomeStatus getStatus() {
        return status;
    }

    /**
     * Returns the unique identifier for the doctor associated with this outcome.
     *
     * @return the doctor ID
     */
    public String getDoctorId(){
        return doctor_id;
    }

    /**
     * Returns the unique identifier for the patient associated with this outcome.
     *
     * @return the patient ID
     */
    public String getPatientId(){
        return patient_id;
    }

    // Setters

    /**
     * Sets a new date for the appointment outcome.
     
      *
      *@param date new date for the appointment 
      */ 
      public void setDate(String date) { 
          this.date = date; 
      } 

      /** 
       * Sets a new type of service for the appointment outcome. 
       *
       *@param type_of_service new type of service 
       */ 
       public void setTypeOfService(String type_of_service) { 
           this.type_of_service = type_of_service; 
       } 

       /** 
       * Sets a new medication for the appointment outcome. 
       *
       *@param medication new medication 
       */ 
       public void setMedication(String medication) { 
           this.medication = medication; 
       } 

       /** 
       * Sets new consultation notes for the appointment outcome. 
       *
       *@param consultation_notes new consultation notes 
       */ 
       public void setConsultationNotes(String consultation_notes) { 
           this.consultation_notes = consultation_notes; 
       } 

       /** 
       * Sets a new status for the appointment outcome. 
       *
       *@param status new status for the outcome 
       */ 
       public void setStatus(AppointmentOutcomeStatus status) { 
           this.status = status; 
       } 

       /** 
       * Sets a new patient ID for this outcome. 
       *
       *@param patient_id new patient ID 
       */ 
       public void setPatientId(String patient_id){ 
           this.patient_id = patient_id; 
       } 

       /** 
       * Sets a new doctor ID for this outcome. 
       *
       *@param doctor_id new doctor ID 
       */ 
      public void setDoctorId(String doctor_id){ 
          this.doctor_id = doctor_id; 
      } 
    
}