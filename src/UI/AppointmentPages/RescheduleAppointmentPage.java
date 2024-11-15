package UI.AppointmentPages;

import java.util.List;

import Common.ClearOutput;
import Common.UserInterface;
import Controllers.AMManagers.PatientAM;
import DatabaseItems.AppointmentSlot;
import DatabaseItems.Account;

public class RescheduleAppointmentPage extends UserInterface {
    private PatientAM patientAM;
    private Account patient;

    public RescheduleAppointmentPage(PatientAM patientAM, Account patient) {
        this.patientAM = patientAM;
        this.patient = patient;
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

        rescheduleSlot(docId);
    }

    private void displaySlots(String id) {
        ClearOutput.clearOutput();
        List<AppointmentSlot> slots = patientAM.getSlots(id);
        System.out.println("Current appointments with " + id);
        System.out.println("-----------------------------");
        for (AppointmentSlot slot : slots) {
            if (slot.getPatientId().equals(patient.getid()))
                System.out.println(slot.getDate() + "  " + slot.getTimestart() + " to " + slot.getTimeend() + "  "
                        + slot.getStatus());
        }
    }

    private void rescheduleSlot(String id) {
        System.out.println();
        String cancelappointmentId = getValidatedString(
                "Enter the date and time you wish to reschedule in the format YYYY-MM-DD/HH:MM \n e.g 2024-12-31/12:00 where 12:00 is the start time");
        cancelappointmentId = id + "/" + cancelappointmentId;

        System.out.println();
        System.out.println("Available slots: ");
        System.out.println("---------------------");
        List<AppointmentSlot> slots = patientAM.getAvailableSlots(id);

        if (slots == null) {
            displayError("No Available slots");
            pauseAndView();
            return;
        }

        for (AppointmentSlot slot : slots) {
            System.out.println(slot.getDate() + "  " + slot.getTimestart() + " to " + slot.getTimeend() + "  "
                    + slot.getStatus());
        }
        System.out.println();

        String appointmentId;
        appointmentId = getValidatedString(
                "Enter the date and time of the new appointment slot you wish to schedule:");
        appointmentId = id + "/" + appointmentId;

        if (!patientAM.checkSlotAvailable(appointmentId)) {
            displayError("Invalid appointmentslot to reschedule");
            pauseAndView();
            return;
        } else if (!patientAM.checkSlotCancellable(cancelappointmentId)) {
            displayError("Invalid cancelappointmentslot to reschedule");
            pauseAndView();
            return;
        }

        if (patientAM.requestSlot(appointmentId) && patientAM.cancelSlot(cancelappointmentId)) {
            displaySuccess("Your appointment request has been sent to the doctor");
        } else
            displayError("There is no such available slot, please enter a valid slot");

    }

}
