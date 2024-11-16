package Controllers.AppManagers;

import Controllers.AMManagers.*;
import Common.AppManager;
import Common.ClearOutput;
import Controllers.AOManagers.DoctorAOM;
import Controllers.MRManagers.DoctorMRM;
import Controllers.AccountManager;
import Databases.AccountDatabase;
import Databases.AppointmentOutcomeDatabase;
import Databases.DoctorSchedule;
import Databases.MedicalRecordDatabase;
import UI.UserMenu;
import UI.AOMUI.DoctorOutcomeInterface;
import UI.AccountManagementPages.UpdateDetailsPage;
import UI.AppointmentPages.HandleAppointmentRequestsPage;
import UI.AppointmentPages.SetAvailabilityPage;
import UI.AppointmentPages.ViewPersonalSchedulePage;
import UI.AppointmentPages.ViewUpcomingAppointmentsPage;
import UI.MedicalRecordPages.ManageMedicalRecordPage;

/**
 * The {@code DoctorAppMgr} class manages the functionalities available to doctors
 * within the Hospital X System. It provides methods for managing patient medical records,
 * handling appointments, and accessing doctor-specific services.
 */
public class DoctorAppMgr extends AppManager {
    // Declare managers
    private DoctorAOM doctorOutcomeManager;
    private DoctorMRM doctorMRM; // Medical Records Manager for doctors
    private DoctorAM doctorAM; // Doctor Appointment Manager

    // Declare Pages
    private DoctorOutcomeInterface doctorOutcomeUI;
    private ManageMedicalRecordPage manageMedicalRecordPage;
    private SetAvailabilityPage setAvailabilityPage;
    private ViewPersonalSchedulePage viewPersonalSchedulePage;
    private HandleAppointmentRequestsPage handleAppointmentRequestsPage;
    private ViewUpcomingAppointmentsPage viewUpcomingAppointmentsPage;

    /**
     * Displays the main menu for doctors and handles user selections.
     */
    @Override
    public void displayMainPage() {
        boolean logout = false;
        while (!logout) {
            ClearOutput.clearOutput();
            int selection = UserMenu.displayDoctorMenu();

            switch (selection) {
                case 1:
                    managePatientMedicalRecords(); // Manage patient medical records
                    break;
                case 2:
                    viewPersonalSchedule(); // View personal schedule
                    break;
                case 3:
                    setAvailability(); // Set doctor's availability
                    break;
                case 4:
                    handleAppointmentRequests(); // Handle appointment requests
                    break;
                case 5:
                    viewUpcomingAppointments(); // View upcoming appointments
                    break;
                case 6:
                    recordAppointmentOutcome(); // Record appointment outcomes
                    break;
                case 7:
                    settings(); // Access settings
                    break;
                case 8:
                    ClearOutput.clearOutput();
                    System.out.println("Thank you for using the Hospital X System. Goodbye!");
                    logout = true; // Exit the menu loop
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        }
        logOut(); // Log out after exiting the menu
    }

    /**
     * Loads necessary databases for the application.
     */
    @Override
    protected void loadDatabases() {
        accountDatabase = new AccountDatabase();
        medicalRecordDatabase = new MedicalRecordDatabase();
        appointmentOutcomeDatabase = new AppointmentOutcomeDatabase();
        doctorSchedule = new DoctorSchedule(account.getid()); // Load doctor's schedule
    }

    /**
     * Saves all databases to their respective storage formats (e.g., CSV).
     */
    @Override
    protected void saveDatabases() {
        accountDatabase.storeToCSV();
        medicalRecordDatabase.storeToCSV();
        appointmentOutcomeDatabase.storeToCSV();
        doctorSchedule.storeToCSV(); // Save doctor's schedule
    }

    /**
     * Creates instances of managers used in the doctor application.
     */
    @Override
    protected void createManagers() {
        doctorOutcomeManager = new DoctorAOM(appointmentOutcomeDatabase, account.getid(), doctorSchedule);
        accountManager = new AccountManager(account, accountDatabase, medicalRecordDatabase);
        doctorMRM = new DoctorMRM(medicalRecordDatabase, accountDatabase);
        doctorAM = new DoctorAM(doctorSchedule); // Initialize Doctor Appointment Manager
    }

    /**
     * Creates instances of UI pages used in the doctor application.
     */
    @Override
    protected void createPages() {
        doctorOutcomeUI = new DoctorOutcomeInterface(doctorOutcomeManager, doctorSchedule);
        updateDetailsPage = new UpdateDetailsPage(accountManager);
        manageMedicalRecordPage = new ManageMedicalRecordPage(doctorMRM);
        setAvailabilityPage = new SetAvailabilityPage(account, doctorSchedule, doctorAM);
        viewPersonalSchedulePage = new ViewPersonalSchedulePage();
        handleAppointmentRequestsPage = new HandleAppointmentRequestsPage(doctorAM, account);
        viewUpcomingAppointmentsPage = new ViewUpcomingAppointmentsPage(doctorAM);
    }

    // Methods to handle each menu option

    /**
     * Displays options for managing patient medical records.
     */
    private void managePatientMedicalRecords() {
        manageMedicalRecordPage.displayOptions(); // Delegate to manage medical record page
    }

    /**
     * Displays the personal schedule of the doctor.
     */
    private void viewPersonalSchedule() {
        viewPersonalSchedulePage.displayDocTimeSlot(account, doctorSchedule.getRecords()); // Show personal time slots
    }

    /**
     * Displays options for setting the doctor's availability.
     */
    private void setAvailability() {
        setAvailabilityPage.setAvailability(); // Delegate to availability setting page
    }

    /**
     * Displays options for handling appointment requests.
     */
    private void handleAppointmentRequests() {
        handleAppointmentRequestsPage.handleAppointmentRequests(); // Delegate to handle appointment requests page
    }

    /**
     * Displays upcoming appointments for the doctor.
     */
    private void viewUpcomingAppointments() {
        viewUpcomingAppointmentsPage.viewUpcomingAppointments(); // Delegate to view upcoming appointments page
    }

    /**
     * Displays options for recording appointment outcomes.
     */
    private void recordAppointmentOutcome() {
        doctorOutcomeUI.displayOptions(); // Delegate to outcome interface for recording outcomes
    }
}