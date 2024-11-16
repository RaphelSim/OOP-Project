package DatabaseItems;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Common.AppointmentStatus;
import Common.DatabaseItems;

/**
 * The {@code AppointmentSlot} class represents a time slot for an appointment.
 * It includes details such as appointment ID, patient ID, doctor ID, date,
 * start time, end time, and the status of the appointment.
 */
public class AppointmentSlot implements DatabaseItems {
    private String appointment_id;
    private String patient_id;
    private String doctor_id;
    private String date;
    private String timestart;
    private String timeend;
    private AppointmentStatus status;

    /**
     * Constructs an {@code AppointmentSlot} with specified details when both
     * patient ID and appointment ID are not provided.
     *
     * @param doctor_id       the unique identifier for the doctor
     * @param date            the date of the appointment
     * @param timestart       the start time of the appointment
     * @param timeend         the end time of the appointment
     * @param status          the status of the appointment
     */
    public AppointmentSlot(String doctor_id, String date, String timestart,
                           String timeend, AppointmentStatus status) {
        this.doctor_id = doctor_id;
        this.appointment_id = doctor_id + "/" + date + "/" + timestart;
        this.patient_id = "";
        this.date = date;
        this.timestart = timestart;
        this.timeend = timeend;
        this.status = status;
    }

    /**
     * Constructs an {@code AppointmentSlot} with specified details when both
     * patient ID and appointment ID are provided.
     *
     * @param appointment_id  the unique identifier for the appointment
     * @param patient_id      the unique identifier for the patient
     * @param doctor_id       the unique identifier for the doctor
     * @param date            the date of the appointment
     * @param timestart       the start time of the appointment
     * @param timeend         the end time of the appointment
     * @param status          the status of the appointment
     */
    public AppointmentSlot(String appointment_id, String patient_id, 
                           String doctor_id, String date, String timestart,
                           String timeend, AppointmentStatus status) {
        this.doctor_id = doctor_id;
        this.appointment_id = appointment_id;
        this.patient_id = patient_id;
        this.date = date;
        this.timestart = timestart;
        this.timeend = timeend;
        this.status = status;
    }

    /**
     * Constructs an {@code AppointmentSlot} with specified details when only 
     * patient ID is provided.
     *
     * @param patient_id      the unique identifier for the patient
     * @param doctor_id       the unique identifier for the doctor
     * @param date            the date of the appointment
     * @param timestart       the start time of the appointment
     * @param timeend         the end time of the appointment
     * @param status          the status of the appointment
     */
    public AppointmentSlot(String patient_id, String doctor_id, 
                           String date, String timestart, 
                           String timeend, AppointmentStatus status) {
        this(doctor_id + "/" + date + "/" + timestart, patient_id, 
             doctor_id, date, timestart, timeend, status);
    }

    /**
     * Constructs an {@code AppointmentSlot} by deserializing from a set of parameters.
     *
     * @param params an array of strings containing appointment details in order:
     *               appointment_id, patient_id, date, timestart,
     *               timeend, status
     */
    public AppointmentSlot(String... params) {
        deserialise(params);
    }

    /**
     * Deserializes appointment details from a string array.
     *
     * @param params an array of strings containing appointment details in order:
     *               appointment_id, patient_id, date,
     *               timestart, timeend, status
     */
    public void deserialise(String... params) {
        this.appointment_id = params[0];
        this.patient_id = params[1];
        this.date = params[2];
        this.timestart = params[3];
        this.timeend = params[4];
        this.status = AppointmentStatus.fromString(params[5]);
    }

    /**
     * Serializes the appointment slot into a comma-separated string.
     *
     * @return a string representation of the appointment slot
     */
    public String serialise() {
        return String.format("%s,%s,%s,%s,%s,%s\n",
                this.appointment_id,
                this.patient_id,
                this.date,
                this.timestart,
                this.timeend,
                this.status.toString());
    }

    /**
     * Prints the details of the appointment slot to standard output.
     */
    public void printItem() {
        System.out.println(); // Add a line break to improve readability
        System.out.println("Appointment ID: " + this.appointment_id);
        System.out.println("Patient ID: " + this.patient_id);
        System.out.println("Date: " + this.date);
        System.out.println("Time Start: " + this.timestart);
        System.out.println("Time End: " + this.timeend);
        System.out.println("Status: " + this.status);
    }

    /**
     * Sorts a list of appointment slots by date and start time.
     *
     * @param appointments a list of {@link AppointmentSlot} objects to be sorted
     */
    public static void sortAppointments(List<AppointmentSlot> appointments) {
        Collections.sort(appointments, new Comparator<AppointmentSlot>() {
            @Override
            public int compare(AppointmentSlot a1, AppointmentSlot a2) {
                // Compare dates first
                int dateComparison = LocalDate.parse(a1.getDate()).compareTo(LocalDate.parse(a2.getDate()));
                if (dateComparison != 0) {
                    return dateComparison;
                }
                // If dates are equal, compare start times
                return LocalTime.parse(a1.getTimestart()).compareTo(LocalTime.parse(a2.getTimestart()));
            }
        });
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
     * Returns the unique identifier for the patient associated with this slot.
     *
     * @return the patient ID
     */
    public String getPatientId() {
        return patient_id;
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
     * Returns the start time of the appointment.
     
      *
      *@return start time of appointment 
      */ 
      public String getTimestart() { 
          return timestart; 
      } 

      /** 
       * Returns end time of appointment. 
       *
       *@return end time of appointment 
       */ 
       public String getTimeend() { 
           return timeend; 
       } 

       /** 
       * Returns current status of  appointment. 
       *
       *@return current status 
       */ 
       public AppointmentStatus getStatus() { 
           return status; 
       } 

       /** 
       * Returns unique identifier for associated doctor. 
       *
       *@return doctor ID 
       */ 
      public String getDoctorId() { 
          return doctor_id; 
      } 

      // Setters (all except appointment id)

      /** 
       * Sets a new patient ID for this slot. 
       *
       *@param patient_id new patient ID 
       */ 
      public void setPatientId(String patient_id) { 
          this.patient_id = patient_id; 
      } 

      /** 
       * Sets a new doctor ID for this slot. 
       *
       *@param doctor_id new doctor ID  
       */ 
      public void setDoctorId(String doctor_id) { 
          this.doctor_id = doctor_id; 
      } 

      /** 
       * Sets a new date for this slot.  
       *
       *@param date new date  
       */  
      public void setDate(String date) {  
          this.date = date;  
      }  

      /**  
       * Sets a new start time for this slot.  
       *
       *@param timestart new start time  
       */  
      public void setTimestart(String timestart) {  
          this.timestart = timestart;  
      }  

      /**  
       * Sets a new end time for this slot.  
       *
       *@param timeend new end time  
       */  
      public void setTimeend(String timeend) {  
          this.timeend = timeend;  
      }  

      /**  
       * Sets a new status for this slot.   
       *
       *@param status new status   
       */  
      public void setStatus(AppointmentStatus status) {  
          this.status = status;  
      }  

}