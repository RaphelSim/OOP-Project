package UI;

import DatabaseItems.Account;
import DatabaseItems.AppointmentSlot;
import java.util.ArrayList;

public class viewPersonalSchedulePage {

    // Display Doctor's Personal Schedule (TimeSlots - 60min interval)
    public void displayDocTimeSlot(Account doctor, ArrayList<AppointmentSlot> personalSchedule) {
        if(personalSchedule.size() == 0)
            System.out.println("You have yet to set your Personal Schedule! Use Set Availability option.");
        else {
            int counter = 1;
            System.out.println("");
            System.out.println("Dr. " + doctor.getName() + "'s Personal Schedule Timeslots:");
            System.out.println("No. |Appointment ID|            |Date|      |Start Time|  |End Time|  |Status|");
            for (AppointmentSlot tS : personalSchedule) {
                System.out.print(counter + ".  ");
                System.out.print(tS.getAppointmentId()+ "   ");
                System.out.print(tS.getDate()+ "  ");
                System.out.print(tS.getTimestart()+ "         ");
                System.out.print(tS.getTimeend()+ "         ");
                System.out.print(tS.getStatus()+"\n");
                counter ++;
            }
        }
    }

}
