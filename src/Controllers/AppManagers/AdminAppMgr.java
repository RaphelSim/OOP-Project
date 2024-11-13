package Controllers.AppManagers;

import Common.AppManager;
import Common.ClearOutput;
//import Controllers.AOManagers.AdminAOM;
import Controllers.AccountManager;
import Controllers.StaffManager;
import Databases.AccountDatabase;
import Databases.AppointmentOutcomeDatabase;
import Databases.InventoryDatabase;
import Databases.InventoryRequestDatabase;
import Databases.MedicalRecordDatabase;
import UI.ManageStaffPage;
import UI.UpdateDetailsPage;
import UI.UserMenu;
// import UI.AOMUI.AdminOutcomeInterface;

public class AdminAppMgr extends AppManager {
    // Declare managers
    //private AdminAOM adminOutcomeManager;
    private StaffManager staffManager;
    private ManageStaffPage manageStaffPage;

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
    }

    @Override
    protected void createPages() {
        updateDetailsPage = new UpdateDetailsPage(accountManager);
        manageStaffPage = new ManageStaffPage(staffManager);
    }

    // Methods to handle each menu option
    private void manageHospitalStaff() {
        manageStaffPage.displayOptions();
    }

    private void viewAppointmentsDetails() {
        // Implement functionality to view appointment details
    }

    private void manageMedicationInventory() {
        // Implement functionality to manage medication inventory
    }

    private void approveReplenishmentRequests() {
        // Implement functionality to approve replenishment requests
    }
}
