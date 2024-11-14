package UI;

import Common.UserInterface;

public class UserMenu extends UserInterface {
    public static int displayPatientMenu() {
        System.out.println("Please select an option: ");
        System.out.println("1. View Medical Record");
        System.out.println("2. Update Personal Information");
        System.out.println("3. View Available Appointment Slots");
        System.out.println("4. Schedule an Appointment");
        System.out.println("5. Reschedule an Appointment");
        System.out.println("6. Cancel an Appointment");
        System.out.println("7. View Scheduled Appointments");
        System.out.println("8. View Past Appointment Outcome Records");
        System.out.println("9. Logout");
        return getIntInput(10);
    }

    public static int displayDoctorMenu() {
        System.out.println("Please select an option: ");
        System.out.println("1. Manage Patient Medical Records");
        System.out.println("2. View Personal Schedule");
        System.out.println("3. Set Availability for Appointments");
        System.out.println("4. Accept or Decline Appointment Requests");
        System.out.println("5. View Upcoming Appointments");
        System.out.println("6. Record Appointment Outcome");
        System.out.println("7. Settings");
        System.out.println("8. Logout");
        return getIntInput(9);
    }

    public static int displayPharmacistMenu() {
        System.out.println("Please select an option: ");
        System.out.println("1. View and update Appointment Outcome Record");
        System.out.println("2. View Medication Inventory");
        System.out.println("3. Submit Replenishment Request");
        System.out.println("4. Settings");
        System.out.println("5. Logout");
        return getIntInput(6);
    }

    public static int displayAdminMenu() {
        System.out.println("Please select an option: ");
        System.out.println("1. View and Manage Hospital Staff");
        System.out.println("2. View Appointments details");
        System.out.println("3. View and Manage Medication Inventory");
        System.out.println("4. Approve Replenishment Requests");
        System.out.println("5. Settings");
        System.out.println("6. Logout");
        return getIntInput(7);
    }

}