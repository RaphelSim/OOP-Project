package UI.AppointmentPages;

import java.util.List;

import Common.ClearOutput;
import Common.UserInterface;
import Controllers.AMManagers.AdminAM;
import Controllers.AOManagers.AdminAOM;
import DatabaseItems.AppointmentOutcome;
import DatabaseItems.AppointmentSlot;

public class ViewAppointmentsDetailsPage extends UserInterface {
    private AdminAM adminAM;
    private AdminAOM adminAOM;

    public ViewAppointmentsDetailsPage(AdminAM adminAM, AdminAOM adminAOM) {
        this.adminAM = adminAM;
        this.adminAOM = adminAOM;
    }

    public void displaySlots() {
        ClearOutput.clearOutput();
        List<AppointmentSlot> slots = adminAM.getAppointments();
        System.out.println("All Appointments Details");
        System.out.println("-----------------------------");
        System.out.println("\n     |Appointment ID|         |Patient ID|    |Date|     |Time|         |Status|");
        String tempId = "";
        for (AppointmentSlot slot : slots) {
            if (!tempId.equals(slot.getAppointmentId().substring(0, 8)))
                System.out.println();
            tempId = slot.getAppointmentId().substring(0, 8);
            System.out.println(slot.getAppointmentId() + "      " + slot.getPatientId() + "     " + slot.getDate()
                    + "  " + slot.getTimestart() + " to " + slot.getTimeend() + "  " + slot.getStatus());
        }

        if (slots.size() == 0) {
            displayError("No appointments to show at this moment.");
            pauseAndView();
            return;
        }

        System.out.println();
        String aptId = getValidatedString("Enter the appointment id to view outcome. [Enter 'q' to go back]");
        if (aptId.equals("q"))
            return;
        else
            displayAppointmentOutcome(aptId);
    }

    public void displayAppointmentOutcome(String id) {
        ClearOutput.clearOutput();
        AppointmentOutcome outcome = adminAOM.getOutcome(id);

        if (outcome == null) {
            displayError("Outcome not found");
            return;
        }

        outcome.printItem();
        pauseAndView();
    }

}
