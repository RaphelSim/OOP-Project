package Controllers.AppManagers;

import Common.AppManager;
import Databases.AccountDatabase;
import Databases.AppointmentOutcomeDatabase;
import Databases.InventoryDatabase;
import Databases.InventoryRequestDatabase;
import Databases.MedicalRecordDatabase;

public class AdminAppMgr extends AppManager {
    //initialise your managers and pages here


    @Override
    public void displayMainPage() {
        //call the main page here

        //must call after the user selects logout
        logOut();
    }

    @Override
    protected void loadDatabases() {
        accountDatabase = new AccountDatabase();
        inventoryDatabase = new InventoryDatabase();
        inventoryRequestDatabase = new InventoryRequestDatabase();
        medicalRecordDatabase = new MedicalRecordDatabase();
        appointmentOutcomeDatabase = new AppointmentOutcomeDatabase();
    }

    @Override
    protected void saveDatabases() {
        accountDatabase.storeToCSV();
        inventoryDatabase.storeToCSV();
        inventoryRequestDatabase.storeToCSV();
        medicalRecordDatabase.storeToCSV();
        appointmentOutcomeDatabase.storeToCSV();
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
