package Controllers.AppManagers;

import Controllers.AOManagers.PharmaAOM;
import Common.AppManager;
import Databases.AccountDatabase;
import Databases.AppointmentOutcomeDatabase;
import Databases.InventoryDatabase;
import Databases.InventoryRequestDatabase;
import UI.AOMUI.PharmaOutcomeInterface;

public class PharmaAppMgr extends AppManager {
    //initiate managers and pages(UI here)
    private PharmaAOM pharmaOutcomeManager;
    private PharmaOutcomeInterface pharmaOutcomeUI;

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
        appointmentOutcomeDatabase = new AppointmentOutcomeDatabase();
    }

    @Override
    protected void saveDatabases() {
        accountDatabase.storeToCSV();
        inventoryDatabase.storeToCSV();
        inventoryRequestDatabase.storeToCSV();
        appointmentOutcomeDatabase.storeToCSV();
    }

    @Override
    protected void createManagers() {
        pharmaOutcomeManager = new PharmaAOM(appointmentOutcomeDatabase);
        // create instance of manager here
    }

    @Override
    protected void createPages() {
        pharmaOutcomeUI = new PharmaOutcomeInterface(pharmaOutcomeManager);
        // create instance of pages here
    }

    private void viewAppointmentOutcomes() {
        pharmaOutcomeUI.displayOptions(); // Display appointment outcome options for the pharmacist
    }
    //declare functions to call your managers accordingly
}
