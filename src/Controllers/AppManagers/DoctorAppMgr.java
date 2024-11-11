package Controllers.AppManagers;

import Common.AppManager;
import Common.ClearOutput;
import Controllers.AOManagers.DoctorAOM;
import Controllers.AccountManager;
import Databases.AccountDatabase;
import Databases.AppointmentOutcomeDatabase;
import Databases.DoctorSchedule;
import Databases.MedicalRecordDatabase;
import UI.UpdateDetailsPage;
import UI.UserMenu;
import UI.AOMUI.DoctorOutcomeInterface;

public class DoctorAppMgr extends AppManager {
    // Declare managers
    private DoctorSchedule doctorSchedule;
    private DoctorAOM doctorOutcomeManager;
    private DoctorOutcomeInterface doctorOutcomeUI;

    @Override
    public void displayMainPage() {
        boolean logout = false;
        while (!logout) {
            ClearOutput.clearOutput();
            int selection = UserMenu.displayDoctorMenu();

            switch (selection) {
                case 1:
                    viewPatientMedicalRecords();
                    break;
                case 2:
                    updatePatientMedicalRecords();
                    break;
                case 3:
                    viewPersonalSchedule();
                    break;
                case 4:
                    setAvailability();
                    break;
                case 5:
                    handleAppointmentRequests();
                    break;
                case 6:
                    viewUpcomingAppointments();
                    break;
                case 7:
                    recordAppointmentOutcome();
                    break;
                case 8:
                    settings();
                    break;
                case 9:
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
    }

    @Override
    protected void createPages() {
        doctorOutcomeUI = new DoctorOutcomeInterface(doctorOutcomeManager);
        updateDetailsPage = new UpdateDetailsPage(accountManager);
    }

    // Methods to handle each menu option
    private void viewPatientMedicalRecords() {
        // Implement functionality to view patient medical records
    }

    private void updatePatientMedicalRecords() {
        // Implement functionality to update patient medical records
    }

    private void viewPersonalSchedule() {
        // Implement functionality to view personal schedule
    }

    private void setAvailability() {
        // Implement functionality to set availability for appointments
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
