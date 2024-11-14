package UI;

import AppointmentSystem.Doctor;
import Common.AppointmentManager;
import Databases.AccountDatabase;
import Databases.DoctorSchedule;
import DatabaseItems.Account;
import java.util.ArrayList;
import Common.UserInterface;

public class viewAvailableAppointmentsPage extends UserInterface{
    private AppointmentManager am;
    private AccountDatabase accountDatabase;
    private DoctorSchedule doctorSchedule;

    public viewAvailableAppointmentsPage() {
        accountDatabase = new AccountDatabase("Database/AccountCredentials.csv");
        doctorSchedule = new DoctorSchedule("");
    }

    public void viewAvailableAppointments() {
        // View List of Doctors
        ArrayList<Account> docList = accountDatabase.getDocList();
        String docID = "";

        System.out.println("Doctor ID   |   Doctor Name");
        for(Account item:docList) {
            System.out.println(item.getid() + "   " + item.getName());
        }

        //System.out.println("List of Doctors you can choose from (Enter Doctor ID):");
        docID = getValidatedString("List of Doctors you can choose from (Enter Doctor ID):");

        // Show list of timeslots for chosen Doctor
        doctorSchedule.printDoctorSchedule(docID);

        
    }
}
