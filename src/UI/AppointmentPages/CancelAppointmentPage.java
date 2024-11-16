package UI.AppointmentPages;

import java.util.List;

import Common.ClearOutput;
import Common.UserInterface;
import Controllers.AMManagers.PatientAM;
import DatabaseItems.AppointmentSlot;

public class CancelAppointmentPage extends UserInterface {
    private PatientAM patientAM;
    List<AppointmentSlot> slots;

    public CancelAppointmentPage(PatientAM patientAM) {
        this.patientAM = patientAM;
    }

    public void displaySlots() {
        ClearOutput.clearOutput();
        slots = patientAM.getAppointments();
        System.out.println("Your Scheduled Appointments");
        System.out.println("------------------------------");
        for (AppointmentSlot slot : slots) {
            System.out.println(
                    slot.getAppointmentId() + " " + slot.getDate() + "  " + slot.getTimestart() + " to "
                            + slot.getTimeend() + " " + slot.getStatus());
        }

        if (slots.size() == 0) {
            displayError("No scheduled appointments");
            pauseAndView();
            return;
        }
        cancelSlot();
    }

    private void cancelSlot() {
        System.out.println();
        String appointmentId = getValidatedString(
                "Enter the appointment id you wish to cancel");
        if (appointmentId == null || appointmentId.isEmpty()) {
            System.out.println("You have not entered anything.");
            pauseAndView();
            return;
        }

        if (patientAM.cancelSlot(appointmentId))
            displaySuccess("Your appointment has been cancelled.");
        else
            displayError("There is no such available slot");
    }

}
