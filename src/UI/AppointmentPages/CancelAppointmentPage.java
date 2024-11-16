package UI.AppointmentPages;

import java.util.List;

import Common.AppointmentStatus;
import Common.ClearOutput;
import Common.UserInterface;
import Controllers.AMManagers.PatientAM;
import DatabaseItems.AppointmentSlot;

public class CancelAppointmentPage extends UserInterface {
    private PatientAM patientAM;
    List<AppointmentSlot> slots;

    public CancelAppointmentPage(PatientAM patientAM) {
        this.patientAM = patientAM;
        slots = patientAM.getAppointments();
    }

    public void displaySlots() {
        ClearOutput.clearOutput();
        
        System.out.println("Your Scheduled Appointments");
        System.out.println("------------------------------");
        for (AppointmentSlot slot : slots) {
            System.out.println(
                    slot.getAppointmentId() + " " + slot.getDate() + "  " + slot.getTimestart() + " to "
                            + slot.getTimeend() + " " + slot.getStatus());
        }

        if (slots == null) {
            displayError("No scheduled appointments");
            pauseAndView();
            return;
        }
        cancelSlot();
    }

    private void cancelSlot() {
        System.out.println();
        String appointmentId = getValidatedString(
                "Enter the date and time you wish to cancel in the format YYYY-MM-DD/HH:MM \n e.g 2024-12-31/12:00 where 12:00 is the start time");
        if(appointmentId != null) {
            for(AppointmentSlot slot : slots) {
                if(slot.getAppointmentId().substring(9).equals(appointmentId)) {
                    appointmentId = slot.getAppointmentId();
                }
            } 
        }
        else {
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
