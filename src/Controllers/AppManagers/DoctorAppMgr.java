package Controllers.AppManagers;

import Common.AppManager;
import Databases.AccountDatabase;
import Databases.AppointmentOutcomeDatabase;
import Databases.DoctorSchedule;
import Databases.MedicalRecordDatabase;

public class DoctorAppMgr extends AppManager {
    //initiate managers and pages(UI here)

    @Override
    public void displayMainPage() {
        //call the main page here

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
        // create instance of manager here
    }

    @Override
    protected void createPages() {
        // create instance of pages here
    }

    //declare functions to call your managers accordingly
}
