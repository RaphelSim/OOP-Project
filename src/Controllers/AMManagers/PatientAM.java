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

public class PatientAM {
    private String userId;
    private List<Account> doctors = new ArrayList<Account>();
    private AccountDatabase accountDatabase;

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

    public boolean checkDoctor(String id) {
        if (accountDatabase.searchItem(id) == null) {
            return false;
        } else
            return true;
    }

    public DoctorSchedule getDoctorSchedule(String id) {
        if (!checkDoctor(id)) {
            return null;
        }
        return new DoctorSchedule(id);
    }

    public List<AppointmentSlot> getAvailableSlots(String id) {
        // retrive the doctor's schedule
        DoctorSchedule schedule = getDoctorSchedule(id);
        if (schedule == null)
            return null;
        List<AppointmentSlot> slots = new ArrayList<AppointmentSlot>();
        for (DatabaseItems item : schedule.getRecords()) {
            AppointmentSlot slot = (AppointmentSlot) item;

            if (slot.getStatus() == AppointmentStatus.FREE)
                slots.add(slot);
        }
        return slots;
    }

    public List<AppointmentSlot> getSlots(String id) {
        // retrive the doctor's schedule
        DoctorSchedule schedule = getDoctorSchedule(id);
        if (schedule == null)
            return null;
        List<AppointmentSlot> slots = new ArrayList<AppointmentSlot>();
        for (DatabaseItems item : schedule.getRecords()) {
            AppointmentSlot slot = (AppointmentSlot) item;
            slots.add(slot);
        }
        return slots;
    }

    public boolean cancelSlot(String appointmentId) {
        // retrive the doctor's schedule
        DoctorSchedule schedule = getDoctorSchedule(appointmentId.substring(0, 8));
        if (schedule == null)
            return false;
        AppointmentSlot slot = (AppointmentSlot) schedule.searchItem(appointmentId);
        if (slot == null || !userId.equals(slot.getPatientId()))
            return false;

        slot.setPatientId("");
        slot.setStatus(AppointmentStatus.FREE);

        // save the changes
        schedule.storeToCSV();
        return true;
    }

    public boolean requestSlot(String appointmentId) {
        // retrive the doctor's schedule
        DoctorSchedule schedule = getDoctorSchedule(appointmentId.substring(0, 8));
        if (schedule == null)
            return false;
        AppointmentSlot slot = (AppointmentSlot) schedule.searchItem(appointmentId);
        if (slot == null)
            return false;

        slot.setPatientId(userId);
        slot.setStatus(AppointmentStatus.REQUESTED);

        // save the changes
        schedule.storeToCSV();
        return true;
    }

    public List<AppointmentSlot> getAppointments() {
        List<AppointmentSlot> appointments = new ArrayList<AppointmentSlot>();

        // for each doctor, iterate their slots to get this patient's appointments
        for (Account doctor : doctors) {
            DoctorSchedule slots = new DoctorSchedule(doctor.getid());
            for (DatabaseItems item : slots.getRecords()) {
                AppointmentSlot slot = (AppointmentSlot) item;
                if (slot.getPatientId().equals(userId))
                    appointments.add(slot);
            }
        }

        // use the static function provided by appointment slot to sort the appointments
        AppointmentSlot.sortAppointments(appointments);

        return appointments;
    }

    public boolean checkSlotAvailable(String appointmentId) {
        DoctorSchedule schedule = getDoctorSchedule(appointmentId.substring(0, 8));
        if (schedule == null)
            return false;
        AppointmentSlot slot = (AppointmentSlot) schedule.searchItem(appointmentId);
        if (slot == null || slot.getStatus() != AppointmentStatus.FREE)
            return false;

        return true;
    }

    public boolean checkSlotCancellable(String appointmentId) {
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
