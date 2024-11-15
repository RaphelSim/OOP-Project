package UI.AppointmentPages;

import java.util.List;

import Common.AppointmentStatus;
import Common.ClearOutput;
import Common.UserInterface;
import Controllers.AMManagers.PatientAM;
import DatabaseItems.AppointmentSlot;

public class ScheduleAppointmentPage extends UserInterface {
    private PatientAM patientAM;

    public ScheduleAppointmentPage(PatientAM patientAM) {
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

        bookSlot(docId);
    }

    private void displaySlots(String id) {
        ClearOutput.clearOutput();
        List<AppointmentSlot> slots = patientAM.getAvailableSlots(id);
        System.out.println("Available slots for " + id);
        System.out.println("------------------------");
        for (AppointmentSlot slot : slots) {
            if (slot.getStatus() == AppointmentStatus.FREE) {
                System.out.println(slot.getDate() + "  " + slot.getTimestart() + " to " + slot.getTimeend());
            }
        }
    }

    private void bookSlot(String id) {
        System.out.println();
        String appointmentId = getValidatedString(
                "Enter the date and time you wish to book in the format YYYY-MM-DD/HH:MM \n e.g 2024-12-31/12:00 where 12:00 is the start time");
        appointmentId = id + "/" + appointmentId;
        if (patientAM.requestSlot(appointmentId))
            displaySuccess("Your appointment request has been sent to the doctor");
        else
            displayError("There is no such available slot");
    }

}
