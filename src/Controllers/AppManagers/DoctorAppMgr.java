package Controllers.AppManagers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import Common.AppManager;
import Common.ClearOutput;
import Common.CustomTimer;
import Common.DatabaseItems;
import Controllers.AOManagers.DoctorAOM;
import Controllers.MRManagers.DoctorMRM;
import Controllers.AccountManager;
import Databases.AccountDatabase;
import Databases.AppointmentOutcomeDatabase;
import Databases.DoctorSchedule;
import Databases.MedicalRecordDatabase;
import UI.UserMenu;
import UI.setAvailabilityPage;
import UI.viewPersonalSchedulePage;
import UI.AOMUI.DoctorOutcomeInterface;
<<<<<<< HEAD
import DatabaseItems.Account;
import DatabaseItems.AppointmentSlot;
=======
import UI.AccountManagementPages.UpdateDetailsPage;
import UI.MedicalRecordPages.ManageMedicalRecordPage;
>>>>>>> main

public class DoctorAppMgr extends AppManager {
    // Declare managers
    private DoctorSchedule doctorSchedule;
    private DoctorAOM doctorOutcomeManager;
    private DoctorMRM doctorMRM;

    // Declare Pages
    private DoctorOutcomeInterface doctorOutcomeUI;
<<<<<<< HEAD
    
    // Get Login Doctor account object
    private Account doctor;

    // Store list of doctor Schedule
    private ArrayList<AppointmentSlot> personalSchedule;

    // Pages
    private setAvailabilityPage sAP;
    private viewPersonalSchedulePage vPSP;

    public DoctorAppMgr (Account doctor) {
        this.doctor = doctor;
        sAP = new setAvailabilityPage();
        vPSP = new viewPersonalSchedulePage();
        personalSchedule = new ArrayList<>();
    }
=======
    private ManageMedicalRecordPage manageMedicalRecordPage;
>>>>>>> main

    @Override
    public void displayMainPage() {
        boolean logout = false;
        while (!logout) {
            //ClearOutput.clearOutput();
            int selection = UserMenu.displayDoctorMenu();

            switch (selection) {
                case 1:
                    managePatientMedicalRecords();
                    break;
                case 2:
                    viewPersonalSchedule();
                    break;
                case 3:
                    setAvailability();
                    //CustomTimer.pause(3000);
                    // System.out.println("Press Enter to continue...");
                    // new Scanner(System.in).nextLine();
                    //ClearOutput.clearOutput();
                    break;
                case 4:
                    handleAppointmentRequests();
                    break;
                case 5:
                    viewUpcomingAppointments();
                    break;
                case 6:
                    recordAppointmentOutcome();
                    break;
                case 7:
                    settings();
                    break;
                case 8:
                    ClearOutput.clearOutput();
                    System.out.println("Thank you for using the Hospital X System. Goodbye!");
                    logout = true;
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        }
        logOut();
    }

    @Override
    protected void loadDatabases() {
        accountDatabase = new AccountDatabase();
        medicalRecordDatabase = new MedicalRecordDatabase();
        appointmentOutcomeDatabase = new AppointmentOutcomeDatabase();
        doctorSchedule = new DoctorSchedule(account.getid());
    }

    @Override
    protected void saveDatabases() {
        accountDatabase.storeToCSV();
        medicalRecordDatabase.storeToCSV();
        appointmentOutcomeDatabase.storeToCSV();
        doctorSchedule.storeToCSV();
    }

    @Override
    protected void createManagers() {
        doctorOutcomeManager = new DoctorAOM(appointmentOutcomeDatabase, account.getid());
        accountManager = new AccountManager(account, accountDatabase, medicalRecordDatabase);
        doctorMRM = new DoctorMRM(medicalRecordDatabase, accountDatabase);
    }

    @Override
    protected void createPages() {
        doctorOutcomeUI = new DoctorOutcomeInterface(doctorOutcomeManager, doctorSchedule);
        updateDetailsPage = new UpdateDetailsPage(accountManager);
        manageMedicalRecordPage = new ManageMedicalRecordPage(doctorMRM);
    }

    // Methods to handle each menu option
    private void managePatientMedicalRecords() {
        manageMedicalRecordPage.displayOptions();
    }

    private void viewPersonalSchedule() {
        // Implement functionality to view personal schedule

        // If setAvailability not called first, personal schedule will be empty
        // Else print out personal schedule = list of time slots (regardless of status)
        vPSP.displayDocTimeSlot(doctor, personalSchedule);
    }

    private void setAvailability() {
        // Implement functionality to set availability for appointments
        this.personalSchedule = sAP.setAvailability(doctor);
        doctorSchedule.newDoctor(doctor.getid());
    }

    private void handleAppointmentRequests() {
        // Implement functionality to accept or decline appointment requests
    }

    private void viewUpcomingAppointments() {
        // Implement functionality to view upcoming appointments
    }

    private void recordAppointmentOutcome() {
        // Implement functionality to record appointment outcomes
        doctorOutcomeUI.displayOptions();
    }
}
