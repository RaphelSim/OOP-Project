package UI.AppointmentPages;

//import Common.AppointmentManager;
import DatabaseItems.Account;
import DatabaseItems.AppointmentSlot;

import java.util.List;

import Common.AppointmentStatus;
import Common.ClearOutput;
import Common.UserInterface;
import Controllers.AMManagers.PatientAM;

public class ViewAvailableAppointmentsPage extends UserInterface {
    private PatientAM patientAM;

    public ViewAvailableAppointmentsPage(PatientAM patientAM) {
        this.patientAM = patientAM;
    }

    public void viewAvailableAppointments() {
        // View List of Doctors
        List<Account> docList = patientAM.getDocList();

        ClearOutput.clearOutput();
        System.out.println("Doctor ID | Doctor Name");
        System.out.println("----------------------------");
        for (Account item : docList) {
            System.out.println(item.getid() + "   " + item.getName());
        }
        System.out.println();

        /// viewing specific doctor's schedule
        String docID = getValidatedString("Enter the doctor's id to view: ");

        // check if doctor exists
        if (!patientAM.checkDoctor(docID)) {
            displayError("Doctor not found!");
            return;
        }

        // Show list of available timeslots for chosen Doctor
        ClearOutput.clearOutput();
        List<AppointmentSlot> slots = patientAM.getAvailableSlots(docID);
        System.out.println("Available slots for " + docID);
        System.out.println("------------------------");
        for (AppointmentSlot slot : slots) {
            System.out.println(slot.getDate() + "  " + slot.getTimestart() + " to " + slot.getTimeend());
        }
        pauseAndView();
    }
}
