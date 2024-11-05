package Controllers.AppManagers;
import Controllers.AOManagers.DoctorAOM;
import Common.AppManager;
import Databases.AccountDatabase;
import Databases.AppointmentOutcomeDatabase;
import Databases.DoctorSchedule;
import Databases.MedicalRecordDatabase;
import UI.AOMUI.DoctorOutcomeInterface;

public class DoctorAppMgr extends AppManager {
    //initiate managers and pages(UI here)

    private DoctorAOM doctorOutcomeManager;
    private DoctorOutcomeInterface doctorOutcomeUI;

    @Override
    public void displayMainPage() {
        //call the main page here
        doctorOutcomeUI.displayOptions();
        //must call after the user selects logout
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
        doctorOutcomeManager = new DoctorAOM(appointmentOutcomeDatabase);
        // create instance of manager here
    }

    @Override
    protected void createPages() {
        doctorOutcomeUI = new DoctorOutcomeInterface(doctorOutcomeManager);
        // create instance of pages here
    }
    private void viewAppointmentOutcomes() {
        doctorOutcomeUI.displayOptions();
    }
    //declare functions to call your managers accordingly
}
