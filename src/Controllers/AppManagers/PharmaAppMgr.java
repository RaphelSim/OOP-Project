package Controllers.AppManagers;

import Common.AppManager;
import Common.ClearOutput;
import Controllers.AOManagers.PharmaAOM;
import Controllers.AccountManager;
import Controllers.InventoryRequestManager;
import Databases.AccountDatabase;
import Databases.AppointmentOutcomeDatabase;
import Databases.InventoryDatabase;
import Databases.InventoryRequestDatabase;
import UI.UserMenu;
import UI.AOMUI.PharmaOutcomeInterface;
import UI.AccountManagementPages.UpdateDetailsPage;
import UI.InventoryPages.StockRequestPage;
import UI.InventoryPages.ViewInventoryPage;

public class PharmaAppMgr extends AppManager {
    // Declare managers
    private PharmaAOM pharmaOutcomeManager;
    private InventoryRequestManager inventoryRequestManager;

    // Declare pages
    private PharmaOutcomeInterface pharmaOutcomeUI;
    private StockRequestPage stockRequestPage;
    private ViewInventoryPage viewInventoryPage;

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
                    requestReplenishment();
                    break;
                case 4:
                    settings();
                    break;
                case 5:
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
        accountManager = new AccountManager(account, accountDatabase, medicalRecordDatabase);
        inventoryRequestManager = new InventoryRequestManager(inventoryRequestDatabase);
    }

    @Override
    protected void createPages() {
        // Initialize UI pages here once they are available
        pharmaOutcomeUI = new PharmaOutcomeInterface(pharmaOutcomeManager);
        updateDetailsPage = new UpdateDetailsPage(accountManager);
        stockRequestPage = new StockRequestPage(inventoryRequestManager);
        viewInventoryPage = new ViewInventoryPage();
    }

    // Methods to handle each menu option

    private void viewAppointmentOutcome() {
        pharmaOutcomeUI.displayOptions();
    }

    private void viewInventory() {
        viewInventoryPage.display(inventoryDatabase);
    }

    private void requestReplenishment() {
        stockRequestPage.displayOption();
    }
}
