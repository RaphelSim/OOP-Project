package UI;

import java.util.List;

import Common.AppointmentStatus;
import Common.ClearOutput;
import Common.UserInterface;
import Databases.DoctorSchedule;
import Controllers.AMManagers.DoctorAM;
import DatabaseItems.AppointmentSlot;
import DatabaseItems.Account;

public class HandleAppointmentRequestsPage extends UserInterface{
    private DoctorSchedule doctorSchedule;
    private DoctorAM doctorAM;
    private Account doctor;

    public HandleAppointmentRequestsPage(DoctorSchedule doctorSchedule, DoctorAM doctorAM, Account doctor) {
        this.doctorSchedule = doctorSchedule;
        this.doctorAM = doctorAM;
        this.doctor = doctor;
    }

    public void handleAppointmentRequests() {

        List<AppointmentSlot> slots = doctorAM.getAvailableSlots();
        String appID;
        int choice;

        System.out.println("Requested Appointment slot(s)");
        System.out.println("----------------------------");
        System.out.println("    |Appointment ID|          |Date|         |Time|");
        for (AppointmentSlot slot : slots) {
            if (slot.getStatus() == AppointmentStatus.REQUESTED) {
                System.out.println(slot.getAppointmentId()+ "   " + slot.getDate() + "    " + slot.getTimestart() 
                + " to " + slot.getTimeend());
                System.out.println();
            }
        }
        while(true) {
            appID = getValidatedString("Enter AppointmentID (e.g./2024-10-29/12:00):");
            appID = doctor.getid() + appID;
            //System.out.println("You have entered: " + appID);
            if(doctorAM.isAppIDExist(appID) && doctorAM.getSlotWithAppID(appID).getStatus().equals(AppointmentStatus.REQUESTED)) {
                ClearOutput.clearOutput();
                while(true) {
                    AppointmentSlot selected = doctorAM.getSlotWithAppID(appID);
                    System.out.println("You have selected this appointment slot: " + selected.getDate() + "  " 
                    + selected.getTimestart() + " to " + selected.getTimeend());
                    System.out.println("Enter 1. Accept or 2. Reject - this appointment slot:");
                    choice = getIntInput(-1);

                    if(choice == 1) {
                        doctorAM.acceptRequest(selected);
                        break;
                    }
                    else if (choice == 2) {
                        doctorAM.rejectRequest(selected);
                        break;
                    }
                    else
                        System.out.println("Invalid option! Enter option 1 or 2 only");

                }
                break;
            }
            else {
                System.out.println("Error! Please enter a valid Appointment ID!");
            }

        }

        pauseAndView();
    }
    
    
    



    
    

}
