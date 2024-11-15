package Controllers.AppManagers;

import Common.AppManager;
import Common.ClearOutput;
//import Controllers.AOManagers.AdminAOM;
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
// import UI.AOMUI.AdminOutcomeInterface;
import UI.InventoryPages.InventoryManagementPage;
import Controllers.AMManagers.AdminAM;
import Controllers.AOManagers.AdminAOM;

public class AdminAppMgr extends AppManager {
    // Declare managers
    // private AdminAOM adminOutcomeManager;
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
        accountManager = new AccountManager(account, accountDatabase, medicalRecordDatabase);
        staffManager = new StaffManager(accountDatabase);
        inventoryManager = new InventoryManager(inventoryDatabase);
        inventoryRequestManager = new InventoryRequestManager(inventoryRequestDatabase, inventoryManager);
        adminAM = new AdminAM(accountDatabase);
        adminAOM = new AdminAOM(appointmentOutcomeDatabase);
    }

    @Override
    protected void createPages() {
        updateDetailsPage = new UpdateDetailsPage(accountManager);
        manageStaffPage = new ManageStaffPage(staffManager);
        inventoryManagementPage = new InventoryManagementPage(inventoryManager);
        approveReplenishRequestPage = new ApproveReplenishRequestPage(inventoryRequestManager);
        viewAppointmentsDetailsPage = new ViewAppointmentsDetailsPage(adminAM, adminAOM);
    }

    // Methods to handle each menu option
    private void manageHospitalStaff() {
        manageStaffPage.displayOptions();
    }

    private void viewAppointmentsDetails() {
        // Implement functionality to view appointment details
        viewAppointmentsDetailsPage.displaySlots();

    }

    private void manageMedicationInventory() {
        inventoryManagementPage.displayOptions();
    }

    private void approveReplenishmentRequests() {
        approveReplenishRequestPage.displayOptions();
    }
}
