package Controllers.AMManagers;

import java.util.ArrayList;
import java.util.List;

import Common.AppointmentStatus;
import Common.DatabaseItems;
import Common.Role;
import DatabaseItems.Account;
import DatabaseItems.AppointmentSlot;
import Databases.AccountDatabase;
import Databases.DoctorSchedule;

/**
 * The {@code PatientAM} class manages appointment-related functionalities for
 * patients.
 * It provides methods to retrieve doctor information, check appointment
 * availability,
 * and manage appointment slots.
 */
public class PatientAM {
    private String userId; // Unique identifier for the patient
    private List<Account> doctors = new ArrayList<Account>(); // List of doctors
    private AccountDatabase accountDatabase; // Database for managing accounts

    /**
     * Constructs a {@code PatientAM} with the specified patient ID and account
     * database.
     *
     * @param id              the unique identifier of the patient
     * @param accountDatabase the {@link AccountDatabase} used to manage accounts
     */
    public PatientAM(String id, AccountDatabase accountDatabase) {
        this.userId = id;
        this.accountDatabase = accountDatabase;
        retrieveDocList(); // Retrieve the list of doctors upon initialization
    }

    /**
     * Retrieves the list of doctors from the account database.
     */
    private void retrieveDocList() {
        for (DatabaseItems item : accountDatabase.getRecords()) {
            Account account = (Account) item;
            if (account.getrole() == Role.DOC) { // Check if the account role is DOC (Doctor)
                doctors.add(account); // Add doctor to the list
            }
        }
    }

    /**
     * Returns the list of doctors available to the patient.
     *
     * @return a list of {@link Account} objects representing doctors
     */
    public List<Account> getDocList() {
        return doctors; // Return the list of doctors
    }

    /**
     * Checks if a doctor exists in the database by their ID.
     *
     * @param id the unique identifier of the doctor
     * @return true if the doctor exists; false otherwise
     */
    public boolean checkDoctor(String id) {
        return accountDatabase.searchItem(id) != null; // Return true if found
    }

    /**
     * Retrieves the schedule for a specific doctor by their ID.
     *
     * @param id the unique identifier of the doctor
     * @return a {@link DoctorSchedule} object if found; null otherwise
     */
    public DoctorSchedule getDoctorSchedule(String id) {
        if (!checkDoctor(id)) {
            return null; // Doctor not found
        }
        return new DoctorSchedule(id); // Return new schedule instance for doctor
    }

    /**
     * Retrieves available appointment slots for a specific doctor by their ID.
     *
     * @param id the unique identifier of the doctor
     * @return a list of available {@link AppointmentSlot} objects; null if no
     *         schedule found
     */
    public List<AppointmentSlot> getAvailableSlots(String id) {
        DoctorSchedule schedule = getDoctorSchedule(id); // Retrieve doctor's schedule
        if (schedule == null)
            return null; // No schedule found

        List<AppointmentSlot> slots = new ArrayList<AppointmentSlot>();
        for (DatabaseItems item : schedule.getRecords()) {
            AppointmentSlot slot = (AppointmentSlot) item;

            if (slot.getStatus() == AppointmentStatus.FREE)
                slots.add(slot); // Add free slots to list
        }
        return slots; // Return available slots
    }

    /**
     * Retrieves all appointment slots for a specific doctor by their ID.
     *
     * @param id the unique identifier of the doctor
     * @return a list of all {@link AppointmentSlot} objects; null if no schedule
     *         found
     */
    public List<AppointmentSlot> getSlots(String id) {
        DoctorSchedule schedule = getDoctorSchedule(id); // Retrieve doctor's schedule
        if (schedule == null)
            return null; // No schedule found

        List<AppointmentSlot> slots = new ArrayList<AppointmentSlot>();
        for (DatabaseItems item : schedule.getRecords()) {
            AppointmentSlot slot = (AppointmentSlot) item;
            slots.add(slot); // Add all slots to list
        }
        return slots; // Return all slots
    }

    /**
     * Cancels an appointment slot by its ID.
     *
     * @param appointmentId the unique identifier of the appointment to cancel
     * @return true if the slot was successfully canceled; false otherwise
     */
    public boolean cancelSlot(String appointmentId) {
        // retrive the doctor's schedule
        if (appointmentId.length() < 25)
            return false;
        DoctorSchedule schedule = getDoctorSchedule(appointmentId.substring(0, 8));
        if (schedule == null)
            return false; // No schedule found

        AppointmentSlot slot = (AppointmentSlot) schedule.searchItem(appointmentId); // Find slot by ID
        if (slot == null || !userId.equals(slot.getPatientId()))
            return false; // Slot not found or does not belong to this patient

        slot.setPatientId(""); // Clear patient ID from slot
        slot.setStatus(AppointmentStatus.FREE); // Set status to FREE

        schedule.storeToCSV(); // Save changes to schedule
        return true; // Slot canceled successfully
    }

    /**
     * Requests an appointment slot by its ID.
     *
     * @param appointmentId the unique identifier of the appointment to request
     * @return true if the slot was successfully requested; false otherwise
     */
    public boolean requestSlot(String appointmentId) {
        // retrive the doctor's schedule
        if (appointmentId.length() < 25)
            return false;
        DoctorSchedule schedule = getDoctorSchedule(appointmentId.substring(0, 8));
        if (schedule == null)
            return false; // No schedule found

        AppointmentSlot slot = (AppointmentSlot) schedule.searchItem(appointmentId); // Find slot by ID
        if (slot == null)
            return false; // Slot not found

        slot.setPatientId(userId); // Assign patient ID to slot
        slot.setStatus(AppointmentStatus.REQUESTED); // Set status to REQUESTED

        schedule.storeToCSV(); // Save changes to schedule
        return true; // Slot requested successfully
    }

    /**
     * Retrieves all appointments associated with this patient across all doctors.
     *
     * @return a list of {@link AppointmentSlot} objects representing this patient's
     *         appointments
     */
    public List<AppointmentSlot> getAppointments() {
        List<AppointmentSlot> appointments = new ArrayList<AppointmentSlot>();

        for (Account doctor : doctors) {
            DoctorSchedule slots = new DoctorSchedule(doctor.getid());
            for (DatabaseItems item : slots.getRecords()) {
                AppointmentSlot slot = (AppointmentSlot) item;
                if (slot.getPatientId().equals(userId))
                    appointments.add(slot);
            }
        }

        AppointmentSlot.sortAppointments(appointments); // Sort appointments using static method

        return appointments;
    }

    /**
     * Checks whether a specific appointment slot is available.
     *
     * @param appointmentId the unique identifier of the appointment to check
     *                      availability for
     * @return true if the slot is available; false otherwise
     */
    public boolean checkSlotAvailable(String appointmentId) {
        if (appointmentId.length() < 25)
            return false;
        DoctorSchedule schedule = getDoctorSchedule(appointmentId.substring(0, 8));
        if (schedule == null)
            return false;

        AppointmentSlot slot = (AppointmentSlot) schedule.searchItem(appointmentId);
        if (slot == null || slot.getStatus() != AppointmentStatus.FREE)
            return false;

        return true;
    }

    /**
     * Checks whether a specific appointment slot can be canceled by this patient.
     *
     * @param appointmentId the unique identifier of the appointment to check
     *                      cancelability for
     * @return true if the slot can be canceled; false otherwise
     */
    public boolean checkSlotCancellable(String appointmentId) {
        if (appointmentId.length() < 25)
            return false;
        DoctorSchedule schedule = getDoctorSchedule(appointmentId.substring(0, 8));
        System.out.println(appointmentId);
        if (schedule == null)
            return false;

        AppointmentSlot slot = (AppointmentSlot) schedule.searchItem(appointmentId);

        if (slot == null || !userId.equals(slot.getPatientId()))
            return false;

        return true;
    }
}