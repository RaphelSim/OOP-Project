package UI.AppointmentPages;

import DatabaseItems.Account;
import DatabaseItems.AppointmentSlot;
import java.util.List;

import Common.ClearOutput;
import Common.DatabaseItems;
import Common.UserInterface;

public class ViewPersonalSchedulePage extends UserInterface {

    // Display Doctor's Personal Schedule (TimeSlots - 60min interval)
    public void displayDocTimeSlot(Account doctor, List<DatabaseItems> personalSchedule) {
        ClearOutput.clearOutput();
        if (personalSchedule.size() == 0)
            System.out.println("You have yet to set your Personal Schedule! Use Set Availability option.");
        else {
            int counter = 1;
            System.out.println("");
            System.out.println("Dr. " + doctor.getName() + "'s Personal Schedule Timeslots:");
            System.out.println("No. |Appointment ID|            |Date|      |Start Time|  |End Time|  |Status|");
            System.out.println("-------------------------------------------------------------------------------------");
            for (DatabaseItems tS : personalSchedule) {
                AppointmentSlot slot = (AppointmentSlot) tS;
                System.out.print(counter + ".  ");
                System.out.print(slot.getAppointmentId() + "   ");
                System.out.print(slot.getDate() + "  ");
                System.out.print(slot.getTimestart() + "         ");
                System.out.print(slot.getTimeend() + "       ");
                System.out.print(slot.getStatus() + "\n");
                counter++;
            }
        }
        pauseAndView();
    }

}
