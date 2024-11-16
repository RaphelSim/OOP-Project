package Controllers.AOManagers;

import Common.AppointmentOutcomeManager;
import Common.AppointmentOutcomeStatus;
import Databases.AppointmentOutcomeDatabase;
import Databases.DoctorSchedule;
import DatabaseItems.AppointmentOutcome;

/**
 * The {@code DoctorAOM} class manages appointment outcomes specific to doctors.
 * It extends {@link AppointmentOutcomeManager} to provide functionalities for 
 * writing and managing appointment outcomes for a specific doctor.
 */
public class DoctorAOM extends AppointmentOutcomeManager {
    private String doctorId; // Unique identifier for the doctor
    private DoctorSchedule schedule; // Schedule associated with the doctor

    /**
     * Constructs a {@code DoctorAOM} with the specified appointment outcome database,
     * doctor ID, and doctor's schedule.
     *
     * @param database the {@link AppointmentOutcomeDatabase} used to manage appointment outcomes
     * @param doctorId the unique identifier of the doctor
     * @param schedule  the {@link DoctorSchedule} associated with the doctor
     */
    public DoctorAOM(AppointmentOutcomeDatabase database, String doctorId, DoctorSchedule schedule) {
        super(database);
        this.doctorId = doctorId;
        this.schedule = schedule;
    }

    /**
     * Returns the unique identifier of the doctor.
     *
     * @return the doctor's ID
     */
    public String getDoctorId() {
        return doctorId;
    }

    /**
     * Writes a new outcome for an appointment.
     *
     * @param appointmentId        the unique identifier of the appointment
     * @param patientId            the unique identifier of the patient
     * @param newDate              the new date for the appointment outcome
     * @param newTypeOfService     the type of service provided during the appointment
     * @param newMedication        any medication prescribed during the appointment
     * @param newConsultationNotes notes from the consultation
     * @return true if the outcome was successfully added; false otherwise
     */
    public boolean writeOutcome(String appointmentId, String patientId, String newDate, String newTypeOfService,
                                 String newMedication, String newConsultationNotes) {

        AppointmentOutcome record = new AppointmentOutcome(
                appointmentId, doctorId, patientId, newDate, newTypeOfService, newMedication, newConsultationNotes,
                AppointmentOutcomeStatus.PENDING);

        return addOutcome(record, schedule); // Add outcome to database with associated schedule
    }
}