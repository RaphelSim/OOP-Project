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

public class AdminAM {
    private List<Account> doctors = new ArrayList<Account>();
    private AccountDatabase accountDatabase;

    public AdminAM(AccountDatabase accountDatabase) {
        this.accountDatabase = accountDatabase;
        retrieveDocList();
    }

    private void retrieveDocList() {
        for (DatabaseItems item : accountDatabase.getRecords()) {
            Account account = (Account) item;
            if (account.getrole() == Role.DOC) {
                doctors.add(account);
            }
        }
    }

    public List<AppointmentSlot> getAppointments() {
        List<AppointmentSlot> appointments = new ArrayList<AppointmentSlot>();

        // for each doctor, iterate their slots to get this patient's appointments
        for (Account doctor : doctors) {
            DoctorSchedule slots = new DoctorSchedule(doctor.getid());
            for (DatabaseItems item : slots.getRecords()) {
                AppointmentSlot slot = (AppointmentSlot) item;
                if (slot.getStatus() != AppointmentStatus.FREE)
                    appointments.add(slot);
            }
        }

        // use the static function provided by appointment slot to sort the appointments
        AppointmentSlot.sortAppointments(appointments);

        return appointments;
    }

}
