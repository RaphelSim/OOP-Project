package Controllers.AppManagers;

import Common.AppManager;
import Common.ClearOutput;
import Controllers.AOManagers.PharmaAOM;
import Databases.AccountDatabase;
import Databases.AppointmentOutcomeDatabase;
import Databases.InventoryDatabase;
import Databases.InventoryRequestDatabase;
import UI.UserMenu;
import UI.AOMUI.PharmaOutcomeInterface;

public class PharmaAppMgr extends AppManager {
    // Declare managers
    private PharmaAOM pharmaOutcomeManager;
    private PharmaOutcomeInterface pharmaOutcomeUI;

    @Override
    public void displayMainPage() {
        boolean logout = false;
        while (!logout) {
            ClearOutput.clearOutput();
            int selection = UserMenu.displayPharmacistMenu();

            switch (selection) {
                case 1:
                    viewAppointmentOutcome();
                    break;
                case 2:
                    viewInventory();
                    break;
                case 3:
                    processReplenishmentRequests();
                    break;
                case 4:
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
        // Initialize manager instances here once the relevant classes are created
        pharmaOutcomeManager = new PharmaAOM(appointmentOutcomeDatabase);
    }

    @Override
    protected void createPages() {
        // Initialize UI pages here once they are available
        pharmaOutcomeUI = new PharmaOutcomeInterface(pharmaOutcomeManager);
    }

    // Methods to handle each menu option

    private void viewAppointmentOutcome(){
        pharmaOutcomeUI.displayOptions();
    }

    private void viewInventory() {
        // Implement functionality to view medication inventory
    }

    private void updateMedicationStock() {
        // Implement functionality to update stock levels of medications
    }

    private void processReplenishmentRequests() {
        // Implement functionality to handle replenishment requests
    }

    private void viewAppointmentPrescriptions() {
        // Implement functionality to view prescriptions related to appointments
    }
}
