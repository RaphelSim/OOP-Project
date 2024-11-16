package Controllers.AppManagers;

import Common.AppManager;
import Common.ClearOutput;
import Controllers.AccountManager;
import Controllers.InventoryManager;
import Controllers.InventoryRequestManager;
import Controllers.StaffManager;
import Databases.AccountDatabase;
import Databases.AppointmentOutcomeDatabase;
import Databases.InventoryDatabase;
import Databases.InventoryRequestDatabase;
import Databases.MedicalRecordDatabase;
import UI.UserMenu;
import UI.AccountManagementPages.ManageStaffPage;
import UI.AccountManagementPages.UpdateDetailsPage;
import UI.AppointmentPages.ViewAppointmentsDetailsPage;
import UI.InventoryPages.ApproveReplenishRequestPage;
import UI.InventoryPages.InventoryManagementPage;
import Controllers.AMManagers.AdminAM;
import Controllers.AOManagers.AdminAOM;

/**
 * The {@code AdminAppMgr} class is responsible for managing the administrative
 * operations of the hospital system. It provides functionalities for managing
 * hospital staff, viewing appointment details, and handling inventory operations.
 */
public class AdminAppMgr extends AppManager {
    // Declare managers
    private StaffManager staffManager;
    private ManageStaffPage manageStaffPage;
    private InventoryRequestManager inventoryRequestManager;
    private InventoryManager inventoryManager;
    private AdminAM adminAM;
    private AdminAOM adminAOM;

    // Declare pages
    private ApproveReplenishRequestPage approveReplenishRequestPage;
    private InventoryManagementPage inventoryManagementPage;
    private ViewAppointmentsDetailsPage viewAppointmentsDetailsPage;

    /**
     * Displays the main administrative menu and handles user selections
     * until the user chooses to log out.
     */
    @Override
    public void displayMainPage() {
        boolean logout = false;
        while (!logout) {
            ClearOutput.clearOutput();
            int selection = UserMenu.displayAdminMenu();

            switch (selection) {
                case 1:
                    manageHospitalStaff();
                    break;
                case 2:
                    viewAppointmentsDetails();
                    break;
                case 3:
                    manageMedicationInventory();
                    break;
                case 4:
                    approveReplenishmentRequests();
                    break;
                case 5:
                    settings();
                    break;
                case 6:
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

    /**
     * Loads the necessary databases for managing accounts, inventory,
     * medical records, and appointment outcomes.
     */
    @Override
    protected void loadDatabases() {
        accountDatabase = new AccountDatabase();
        inventoryDatabase = new InventoryDatabase();
        inventoryRequestDatabase = new InventoryRequestDatabase();
        medicalRecordDatabase = new MedicalRecordDatabase();
        appointmentOutcomeDatabase = new AppointmentOutcomeDatabase();
    }

    /**
     * Saves all changes made to the databases back to their respective CSV files.
     */
    @Override
    protected void saveDatabases() {
        accountDatabase.storeToCSV();
        inventoryDatabase.storeToCSV();
        inventoryRequestDatabase.storeToCSV();
        medicalRecordDatabase.storeToCSV();
        appointmentOutcomeDatabase.storeToCSV();
    }

    /**
     * Creates instances of various managers used in the application,
     * including account management, staff management, inventory management,
     * and appointment outcome management.
     */
    @Override
    protected void createManagers() {
        accountManager = new AccountManager(account, accountDatabase, medicalRecordDatabase);
        staffManager = new StaffManager(accountDatabase);
        inventoryManager = new InventoryManager(inventoryDatabase);
        inventoryRequestManager = new InventoryRequestManager(inventoryRequestDatabase, inventoryManager);
        adminAM = new AdminAM(accountDatabase);
        adminAOM = new AdminAOM(appointmentOutcomeDatabase);
    }

    /**
     * Creates instances of various pages used in the application,
     * including pages for managing staff, updating details, viewing appointments,
     * and managing inventory.
     */
    @Override
    protected void createPages() {
        updateDetailsPage = new UpdateDetailsPage(accountManager);
        manageStaffPage = new ManageStaffPage(staffManager);
        inventoryManagementPage = new InventoryManagementPage(inventoryManager);
        approveReplenishRequestPage = new ApproveReplenishRequestPage(inventoryRequestManager);
        viewAppointmentsDetailsPage = new ViewAppointmentsDetailsPage(adminAM, adminAOM);
    }

    // Methods to handle each menu option

    /**
     * Handles the functionality for managing hospital staff by displaying
     * options available on the manage staff page.
     */
    private void manageHospitalStaff() {
        manageStaffPage.displayOptions();
    }

    /**
     * Handles viewing appointment details by displaying available slots
     * on the appointments details page.
     */
    private void viewAppointmentsDetails() {
        viewAppointmentsDetailsPage.displaySlots();
    }

    /**
     * Handles managing medication inventory by displaying options available
     * on the inventory management page.
     */
    private void manageMedicationInventory() {
        inventoryManagementPage.displayOptions();
    }

    /**
     * Handles approving replenishment requests by displaying options available
     * on the replenish request approval page.
     */
    private void approveReplenishmentRequests() {
        approveReplenishRequestPage.displayOptions();
    }
}