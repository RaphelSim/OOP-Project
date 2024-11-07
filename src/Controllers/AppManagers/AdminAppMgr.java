package Controllers.AppManagers;

import Common.AppManager;
import Common.ClearOutput;
import Databases.AccountDatabase;
import Databases.AppointmentOutcomeDatabase;
import Databases.InventoryDatabase;
import Databases.InventoryRequestDatabase;
import Databases.MedicalRecordDatabase;
import UI.UserMenu;

public class AdminAppMgr extends AppManager {
    // Declare managers

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
;
    }

    @Override
    protected void createPages() {
        // Create instances of pages for the admin view
    }

    // Methods to handle each menu option
    private void manageHospitalStaff() {
        // Implement functionality for managing hospital staff
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
