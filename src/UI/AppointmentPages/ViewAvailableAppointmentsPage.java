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
        Boolean quit = false;
        char choice;
        ClearOutput.clearOutput();

        while(!quit) {
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
            //ClearOutput.clearOutput();
            List<AppointmentSlot> slots = patientAM.getAvailableSlots(docID);
            System.out.println("Available slots for " + docID);
            System.out.println("------------------------");
            for (AppointmentSlot slot : slots) {
                if(slot.getStatus() == AppointmentStatus.FREE)
                    System.out.println(slot.getDate() + "  " + slot.getTimestart() + " to " + slot.getTimeend());
            }

            if (slots.size() == 0) {
                displayError("No available slots appointments");
            }
        //pauseAndView();
            while(true) {
                choice = getValidatedString("Would you like to look at other doctor's list of available slots? (Y/N)")
                .charAt(0);
                if(choice == 'y' || choice == 'Y') {
                    System.out.println("Loading list of doctors..");
                    break;
                }
                else if(choice == 'n' || choice == 'N'){
                    quit = true;
                    break;
                }
                else
                    System.out.println("You have entered an invalid option.");
            }

        }
    }
}
