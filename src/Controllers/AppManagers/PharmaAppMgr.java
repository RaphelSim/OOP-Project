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

/**
 * The {@code PharmaAppMgr} class manages the pharmacist's application functionalities.
 * It provides methods for viewing appointment outcomes, managing inventory,
 * requesting replenishments, and handling user settings.
 */
public class PharmaAppMgr extends AppManager {
    // Declare managers
    private PharmaAOM pharmaOutcomeManager;
    private InventoryRequestManager inventoryRequestManager;

    // Declare pages
    private PharmaOutcomeInterface pharmaOutcomeUI;
    private StockRequestPage stockRequestPage;
    private ViewInventoryPage viewInventoryPage;

    /**
     * Displays the main menu for the pharmacist and handles user selections.
     */
    @Override
    public void displayMainPage() {
        boolean logout = false;
        while (!logout) {
            ClearOutput.clearOutput();
            int selection = UserMenu.displayPharmacistMenu();

            switch (selection) {
                case 1:
                    viewAppointmentOutcome(); // View appointment outcomes
                    break;
                case 2:
                    viewInventory(); // View current inventory
                    break;
                case 3:
                    requestReplenishment(); // Request stock replenishment
                    break;
                case 4:
                    settings(); // Access settings
                    break;
                case 5:
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
        inventoryDatabase = new InventoryDatabase();
        inventoryRequestDatabase = new InventoryRequestDatabase();
        appointmentOutcomeDatabase = new AppointmentOutcomeDatabase();
    }

    /**
     * Saves all databases to their respective storage formats (e.g., CSV).
     */
    @Override
    protected void saveDatabases() {
        accountDatabase.storeToCSV();
        inventoryDatabase.storeToCSV();
        inventoryRequestDatabase.storeToCSV();
        appointmentOutcomeDatabase.storeToCSV();
    }

    /**
     * Creates instances of managers used in the pharmacist application.
     */
    @Override
    protected void createManagers() {
        // Initialize manager instances here once the relevant classes are created
        pharmaOutcomeManager = new PharmaAOM(appointmentOutcomeDatabase);
        accountManager = new AccountManager(account, accountDatabase, medicalRecordDatabase);
        inventoryRequestManager = new InventoryRequestManager(inventoryRequestDatabase);
    }

    /**
     * Creates instances of UI pages used in the pharmacist application.
     */
    @Override
    protected void createPages() {
        // Initialize UI pages here once they are available
        pharmaOutcomeUI = new PharmaOutcomeInterface(pharmaOutcomeManager);
        updateDetailsPage = new UpdateDetailsPage(accountManager);
        stockRequestPage = new StockRequestPage(inventoryRequestManager);
        viewInventoryPage = new ViewInventoryPage();
    }

    // Methods to handle each menu option

    /**
     * Displays options for viewing appointment outcomes.
     */
    private void viewAppointmentOutcome() {
        pharmaOutcomeUI.displayOptions(); // Delegate to the outcome interface
    }

    /**
     * Displays the current inventory.
     */
    private void viewInventory() {
        viewInventoryPage.display(inventoryDatabase); // Delegate to the inventory page
    }

    /**
     * Displays options for requesting stock replenishment.
     */
    private void requestReplenishment() {
        stockRequestPage.displayOption(); // Delegate to the stock request page
    }
}