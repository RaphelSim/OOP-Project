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
 * The {@code AdminAM} class serves as the Appointment Manager for administrators.
 * It manages appointment-related operations for doctors, retrieving doctor accounts
 * and their scheduled appointments from the account database.
 */
public class AdminAM {
    private List<Account> doctors = new ArrayList<Account>();
    private AccountDatabase accountDatabase;

    /**
     * Constructs an {@code AdminAM} instance with the specified account database.
     *
     * @param accountDatabase the {@link AccountDatabase} used to retrieve doctor accounts
     */
    public AdminAM(AccountDatabase accountDatabase) {
        this.accountDatabase = accountDatabase;
        retrieveDocList();
    }

    /**
     * Retrieves the list of doctor accounts from the account database.
     * This method filters accounts based on their role and adds them to the
     * internal list of doctors.
     */
    private void retrieveDocList() {
        for (DatabaseItems item : accountDatabase.getRecords()) {
            Account account = (Account) item;
            if (account.getrole() == Role.DOC) {
                doctors.add(account);
            }
        }
    }

    /**
     * Gets a list of appointments for all doctors managed by this appointment manager.
     *
     * @return a list of {@link AppointmentSlot} objects representing scheduled appointments
     */
    public List<AppointmentSlot> getAppointments() {
        List<AppointmentSlot> appointments = new ArrayList<AppointmentSlot>();

        // For each doctor, iterate their slots to get this patient's appointments
        for (Account doctor : doctors) {
            DoctorSchedule slots = new DoctorSchedule(doctor.getid());
            for (DatabaseItems item : slots.getRecords()) {
                AppointmentSlot slot = (AppointmentSlot) item;
                if (slot.getStatus() != AppointmentStatus.FREE)
                    appointments.add(slot);
            }
        }

        // Use the static function provided by appointment slot to sort the appointments
        AppointmentSlot.sortAppointments(appointments);

        return appointments;
    }

}