package UI.AppointmentPages;

import java.util.List;

import Common.AppointmentStatus;
import Common.ClearOutput;
import Common.UserInterface;
import Controllers.AMManagers.DoctorAM;
import DatabaseItems.AppointmentSlot;

public class ViewUpcomingAppointmentsPage extends UserInterface {
    private DoctorAM doctorAM;

    public ViewUpcomingAppointmentsPage(DoctorAM doctorAM) {
        this.doctorAM = doctorAM;
    }

    public void viewUpcomingAppointments() {
        List<AppointmentSlot> slots = doctorAM.getAvailableSlots();
        ClearOutput.clearOutput();
        System.out.println("Upcoming Appointments");
        System.out.println("---------------------");
        System.out.println("    |Appointment ID|         |Patient ID|     |Date|         |Time|");
        for (AppointmentSlot slot : slots) {
            if (slot.getStatus() == AppointmentStatus.CONFIRMED) {
                System.out.println(slot.getAppointmentId() + "      " + slot.getPatientId() + "     " +
                        slot.getDate() + "    " + slot.getTimestart() + " to " + slot.getTimeend());
                System.out.println();
            }
        }
        pauseAndView();
        if (slots.size() == 0) {
            displayError("No upcoming appointments");
            pauseAndView();
            return;
        }
    }

}
