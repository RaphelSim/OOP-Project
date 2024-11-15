package UI.AppointmentPages;

import java.util.List;

import Common.AppointmentStatus;
import Common.ClearOutput;
import Common.UserInterface;
import Controllers.AMManagers.DoctorAM;
import DatabaseItems.AppointmentSlot;
import DatabaseItems.Account;

public class HandleAppointmentRequestsPage extends UserInterface {
    private DoctorAM doctorAM;
    private Account doctor;

    public HandleAppointmentRequestsPage(DoctorAM doctorAM, Account doctor) {
        this.doctorAM = doctorAM;
        this.doctor = doctor;
    }

    public void handleAppointmentRequests() {

        List<AppointmentSlot> slots = doctorAM.getAvailableSlots();
        String appID;
        int choice;

        ClearOutput.clearOutput();
        System.out.println("Requested Appointment slot(s)");
        System.out.println("----------------------------");
        System.out.println("|Date|         |Time|");

        for (AppointmentSlot slot : slots) {
            if (slot.getStatus() == AppointmentStatus.REQUESTED) {
                System.out.println("   " + slot.getDate() + "    " + slot.getTimestart()
                        + " to " + slot.getTimeend());
                System.out.println();
            }
        }

        System.out.println();
        while (true) {
            appID = getValidatedString(
                    "Enter 'q' to quit or\nEnter date and time in the format YYYY-MM-DD/HH:mm \n(e.g./2024-10-29/12:00):");
            if (appID.equals("q"))
                return;
            appID = doctor.getid() + "/" + appID;
            // System.out.println("You have entered: " + appID);
            if (doctorAM.isAppIDExist(appID)
                    && doctorAM.getSlotWithAppID(appID).getStatus().equals(AppointmentStatus.REQUESTED)) {
                ClearOutput.clearOutput();
                while (true) {
                    AppointmentSlot selected = doctorAM.getSlotWithAppID(appID);
                    System.out.println("You have selected this appointment slot: " + selected.getDate() + "  "
                            + selected.getTimestart() + " to " + selected.getTimeend());
                    System.out.println("Enter 1. Accept or 2. Reject - this appointment slot:");
                    choice = getIntInput(-1);

                    if (choice == 1) {
                        doctorAM.acceptRequest(selected);
                        break;
                    } else if (choice == 2) {
                        doctorAM.rejectRequest(selected);
                        break;
                    } else
                        System.out.println("Invalid option! Enter option 1 or 2 only");

                }
                break;
            } else {
                System.out.println("Error! Please enter a valid Appointment ID!");
            }

        }

        pauseAndView();
    }

}
