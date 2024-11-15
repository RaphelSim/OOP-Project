package UI.AppointmentPages;

import java.util.List;

import Common.ClearOutput;
import Common.UserInterface;
import Controllers.AMManagers.AdminAM;
import DatabaseItems.AppointmentSlot;

public class ViewAppointmentsDetailsPage extends UserInterface{
    private AdminAM adminAM;

    public ViewAppointmentsDetailsPage(AdminAM adminAM) {
        this.adminAM = adminAM;
    }

    public void displaySlots() {
        ClearOutput.clearOutput();
        List<AppointmentSlot> slots = adminAM.getAppointments();
        System.out.println("All Appointments Details");
        System.out.println("-----------------------------");
        System.out.println("\n     |Appointment ID|         |Patient ID|    |Date|     |Time|         |Status|");
        for (AppointmentSlot slot : slots) {
                System.out.println(slot.getAppointmentId() + "      " + slot.getPatientId() + "     " + slot.getDate() 
                + "  " + slot.getTimestart() + " to " + slot.getTimeend() + "  " + slot.getStatus());
        }
        pauseAndView();
    }
    
}
