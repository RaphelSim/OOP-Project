package Controllers.AppManagers;

import Common.AppManager;
import Controllers.AOManagers.AdminAOM;
import Databases.AccountDatabase;
import Databases.AppointmentOutcomeDatabase;
import Databases.InventoryDatabase;
import Databases.InventoryRequestDatabase;
import Databases.MedicalRecordDatabase;
import UI.AOMUI.AdminOutcomeInterface;

public class AdminAppMgr extends AppManager {
    //initialise your managers and pages here
    private AdminAOM adminOutcomeManager;
    private AdminOutcomeInterface adminOutcomeUI;

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
        adminOutcomeManager = new AdminAOM(appointmentOutcomeDatabase);
        // create instance of manager here
    }

    @Override
    protected void createPages() {
        // create instance of pages here
        adminOutcomeUI = new AdminOutcomeInterface(adminOutcomeManager);
    }

    private void viewOrEditAppointmentOutcomes() {
        adminOutcomeUI.displayOptions(); // Display options to view or edit appointment outcomes
    }
    //declare functions to call your managers accordingly
}
