package UI;

import java.util.List;

import Common.AppointmentStatus;
import Common.ClearOutput;
import Common.UserInterface;
import Controllers.AMManagers.PatientAM;
import DatabaseItems.AppointmentSlot;

public class ViewScheduledAppointmentsPage extends UserInterface{
    private PatientAM patientAM;

    public ViewScheduledAppointmentsPage(PatientAM patientAM) {
        this.patientAM = patientAM;
    }

    public void displayOptions() {
        ClearOutput.clearOutput();
        String docId = getValidatedString("Enter the doctor's id: ");

        // check if doctor can be found
        if (!patientAM.checkDoctor(docId)) {
            displayError("Doctor not found");
            return;
        }
        displaySlots(docId);
    }

    private void displaySlots(String id) {
        ClearOutput.clearOutput();
        List<AppointmentSlot> slots = patientAM.getAvailableSlots(id);
        System.out.println("Available slots for " + id);
        System.out.println("------------------------");
        for (AppointmentSlot slot : slots) {
            if (slot.getStatus() == AppointmentStatus.REQUESTED || slot.getStatus() == AppointmentStatus.CONFIRMED 
            || slot.getStatus() == AppointmentStatus.DECLINED) {
                System.out.println(slot.getDate() + "  " + slot.getTimestart() + " to " + slot.getTimeend());
            }
        }
    }
}
