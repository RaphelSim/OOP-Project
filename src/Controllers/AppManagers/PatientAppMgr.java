package Controllers.AppManagers;

import Controllers.AOManagers.PatientAOM;
import Controllers.MRManagers.PatientMRM;
import Common.AppManager;
import Common.ClearOutput;
import Controllers.AccountManager;
import Controllers.AMManagers.PatientAM;
import Databases.AccountDatabase;
import Databases.AppointmentOutcomeDatabase;
import Databases.MedicalRecordDatabase;
import UI.UserMenu;
import UI.AOMUI.PatientOutcomeInterface;
import UI.AccountManagementPages.UpdateDetailsPage;
import UI.AppointmentPages.CancelAppointmentPage;
import UI.AppointmentPages.RescheduleAppointmentPage;
import UI.AppointmentPages.ScheduleAppointmentPage;
import UI.AppointmentPages.ViewAvailableAppointmentsPage;
import UI.AppointmentPages.ViewScheduledAppointmentsPage;
import UI.MedicalRecordPages.PatientViewMedicalRecordPage;

/**
 * The {@code PatientAppMgr} class manages the functionalities available to patients
 * within the Hospital X System. It provides methods for viewing medical records,
 * managing appointments, and accessing patient-specific services.
 */
public class PatientAppMgr extends AppManager {
    // Declare managers
    private AccountManager accountManager;

    // Attributes
    private PatientAOM patientOutcomeManager;
    private PatientMRM patientMRM;
    private PatientAM patientAM;

    // Declare pages
    private PatientOutcomeInterface patientOutcomeUI;
    private PatientViewMedicalRecordPage patientViewMedicalRecordPage;
    private ViewAvailableAppointmentsPage viewAvailableAppointmentsPage;
    private ScheduleAppointmentPage scheduleAppointmentPage;
    private RescheduleAppointmentPage rescheduleAppointmentPage;
    private CancelAppointmentPage cancelAppointmentPage;
    private ViewScheduledAppointmentsPage viewScheduledAppointmentsPage;

    /**
     * Displays the main menu for patients and handles user selections.
     */
    @Override
    public void displayMainPage() {
        boolean logout = false;
        while (!logout) {
            ClearOutput.clearOutput();
            int selection = UserMenu.displayPatientMenu();

            switch (selection) {
                case 1:
                    viewMedicalRecord(); // View medical record
                    ClearOutput.clearOutput();
                    break;
                case 2:
                    settings(); // Access settings
                    break;
                case 3:
                    viewAvailableAppointments(); // View available appointment slots
                    break;
                case 4:
                    scheduleAppointment(); // Schedule a new appointment
                    ClearOutput.clearOutput();
                    break;
                case 5:
                    rescheduleAppointment(); // Reschedule an existing appointment
                    ClearOutput.clearOutput();
                    break;
                case 6:
                    cancelAppointment(); // Cancel an existing appointment
                    ClearOutput.clearOutput();
                    break;
                case 7:
                    viewScheduledAppointments(); // View all scheduled appointments
                    ClearOutput.clearOutput();
                    break;
                case 8:
                    viewPastAppointmentOutcomes(); // View outcomes of past appointments
                    ClearOutput.clearOutput();
                    break;
                case 9:
                    System.out.println("Thank you for using the Hospital X System. See you again soon!");
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
    }

    /**
     * Saves all databases to their respective storage formats (e.g., CSV).
     */
    @Override
    protected void saveDatabases() {
        accountDatabase.storeToCSV();
        medicalRecordDatabase.storeToCSV();
        appointmentOutcomeDatabase.storeToCSV();
    }

    /**
     * Creates instances of managers used in the patient application.
     */
    @Override
    protected void createManagers() {
        accountManager = new AccountManager(account, accountDatabase, medicalRecordDatabase);
        patientOutcomeManager = new PatientAOM(appointmentOutcomeDatabase, account.getid());
        patientMRM = new PatientMRM(medicalRecordDatabase, account.getid());
        patientAM = new PatientAM(account.getid(), accountDatabase);
    }

    /**
     * Creates instances of UI pages used in the patient application.
     */
    @Override
    protected void createPages() {
        updateDetailsPage = new UpdateDetailsPage(accountManager);
        patientOutcomeUI = new PatientOutcomeInterface(patientOutcomeManager);
        patientViewMedicalRecordPage = new PatientViewMedicalRecordPage(patientMRM);
        viewAvailableAppointmentsPage = new ViewAvailableAppointmentsPage(patientAM);
        scheduleAppointmentPage = new ScheduleAppointmentPage(patientAM);
        rescheduleAppointmentPage = new RescheduleAppointmentPage(patientAM, account);
        cancelAppointmentPage = new CancelAppointmentPage(patientAM);
        viewScheduledAppointmentsPage = new ViewScheduledAppointmentsPage(patientAM);
    }

    // Methods to handle each menu option

    /**
     * Displays the patient's medical record.
     */
    private void viewMedicalRecord() {
        patientViewMedicalRecordPage.displayMedicalRecord(); // Delegate to the medical record page
    }

    /**
     * Displays available appointment slots for scheduling.
     */
    private void viewAvailableAppointments() {
        viewAvailableAppointmentsPage.viewAvailableAppointments(); // Delegate to available appointments page
    }

    /**
     * Displays options for scheduling a new appointment.
     */
    private void scheduleAppointment() {
        scheduleAppointmentPage.displayOptions(); // Delegate to schedule appointment page
    }

    /**
     * Displays options for rescheduling an existing appointment.
     */
    private void rescheduleAppointment() {
        rescheduleAppointmentPage.displayOptions(); // Delegate to reschedule appointment page
    }

    /**
     * Displays options for canceling an existing appointment.
     */
    private void cancelAppointment() {
        cancelAppointmentPage.displaySlots(); // Delegate to cancel appointment page
    }

    /**
     * Displays scheduled appointments for the patient.
     */
    private void viewScheduledAppointments() {
        viewScheduledAppointmentsPage.displaySlots(); // Delegate to view scheduled appointments page
    }

    /**
     * Displays past appointment outcomes for the patient.
     */
    private void viewPastAppointmentOutcomes() {
        patientOutcomeUI.displayOptions(account.getid()); // Delegate to outcome interface for past appointments
    }
}