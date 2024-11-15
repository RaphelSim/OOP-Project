package UI;

import java.util.ArrayList;

import Common.UserInterface;
import DatabaseItems.Account;
import Databases.AccountDatabase;
import Databases.DoctorSchedule;

public class scheduleAppointmentPage extends UserInterface{
    private AccountDatabase accountDatabase;
    private DoctorSchedule doctorSchedule;

    public scheduleAppointmentPage() {
        accountDatabase = new AccountDatabase("Database/AccountCredentials.csv");
        doctorSchedule = new DoctorSchedule("");
    }

    public void scheduleAppointment() {
        String choiceDoc = "";
        int choiceSlot;
        boolean exists = false;
        ArrayList<Account> docList = accountDatabase.getDocList();

        choiceDoc = getValidatedString("Enter ID of Doctor you wish to schedule with:");

            for(Account a:docList) {
                if(a.getid().equalsIgnoreCase(choiceDoc)) {
                    exists = true;
                    break;
                }
            }
            if(!exists)
                System.out.println("Invalid Doctor ID entered!");
            else {
                // User input to choose timeslot then add to Patient's Appointment
                
                //if(choice)


                //System.out.println("Appointment slot is taken. Please choose another available slot.");

            }

        }

        

        

    
}
