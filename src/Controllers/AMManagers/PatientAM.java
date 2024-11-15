package Controllers.AMManagers;

import java.util.ArrayList;
import java.util.List;

import Common.AppointmentManager;
import Common.DatabaseItems;
import Common.Role;
import DatabaseItems.Account;
import DatabaseItems.AppointmentSlot;
import Databases.AccountDatabase;
import Databases.DoctorSchedule;

public class PatientAM extends AppointmentManager {
    private String userId;
    private List<Account> doctors = new ArrayList<Account>();
    private AccountDatabase accountDatabase;
    private DoctorSchedule schedule;

    public PatientAM(String id, AccountDatabase accountDatabase) {
        this.userId = id;
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

    public List<Account> getDocList() {
        return doctors;
    }

    public boolean setDoctor(String id) {
        if (accountDatabase.searchItem(id) == null) {
            return false;
        }
        schedule = new DoctorSchedule(id);
        return true;
    }

    public List<AppointmentSlot> getAvailableSlots() {
        if (schedule == null)
            return null;
        List<AppointmentSlot> slots = new ArrayList<AppointmentSlot>();
        for (DatabaseItems item : schedule.getRecords()) {
            AppointmentSlot slot = (AppointmentSlot) item;
            slots.add(slot);
        }
        return slots;
    }

}
