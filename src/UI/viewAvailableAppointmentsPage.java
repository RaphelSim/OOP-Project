package UI;

//import Common.AppointmentManager;
import Databases.AccountDatabase;
import Databases.DoctorSchedule;
import DatabaseItems.Account;
import DatabaseItems.AppointmentSlot;

import java.util.ArrayList;
import java.util.List;

import Common.AppointmentStatus;
import Common.ClearOutput;
import Common.DatabaseItems;
import Common.UserInterface;

public class ViewAvailableAppointmentsPage extends UserInterface {
    // private AppointmentManager am;
    private AccountDatabase accountDatabase;

    public ViewAvailableAppointmentsPage(AccountDatabase accountDatabase) {
        this.accountDatabase = accountDatabase;
    }

    public void viewAvailableAppointments() {
        // View List of Doctors
        ArrayList<Account> docList = accountDatabase.getDocList();

        ClearOutput.clearOutput();
        System.out.println("Doctor ID | Doctor Name");
        System.out.println("----------------------------");
        for (Account item : docList) {
            System.out.println(item.getid() + "   " + item.getName());
        }
        System.out.println();
        String docID = getValidatedString("Enter the doctor's id to view: ");

        // check if doctor can be found in database
        if (docID == null || accountDatabase.searchItem(docID) == null) {
            displayError("Invalid id");
            return;
        }

        DoctorSchedule doctorSchedule = new DoctorSchedule(docID);
        // Show list of available timeslots for chosen Doctor
        ClearOutput.clearOutput();
        List<DatabaseItems> slots = doctorSchedule.getRecords();
        System.out.println("Available slots");
        System.out.println("------------------");
        for (DatabaseItems item : slots) {
            AppointmentSlot slot = (AppointmentSlot) item;
            if (slot.getStatus() == AppointmentStatus.FREE) {
                System.out.println(slot.getDate() + "  " + slot.getTimestart() + " to " + slot.getTimeend());
            }
        }
        pauseAndView();
    }
}
