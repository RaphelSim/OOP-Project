package UI.AppointmentPages;

import java.util.List;

import Common.ClearOutput;
import Common.UserInterface;
import Controllers.AMManagers.PatientAM;
import DatabaseItems.AppointmentSlot;

public class ViewScheduledAppointmentsPage extends UserInterface {
    private PatientAM patientAM;

    public ViewScheduledAppointmentsPage(PatientAM patientAM) {
        this.patientAM = patientAM;
    }

    public void displaySlots() {
        ClearOutput.clearOutput();
        List<AppointmentSlot> slots = patientAM.getAppointments();
        System.out.println("Your Scheduled Appointments");
        System.out.println("------------------------------");
        for (AppointmentSlot slot : slots) {
            System.out.println(
                    slot.getAppointmentId().substring(0, 8) + " " + slot.getDate() + "  " + slot.getTimestart() + " to "
                            + slot.getTimeend() + " " + slot.getStatus());
        }
        pauseAndView();
    }

}
