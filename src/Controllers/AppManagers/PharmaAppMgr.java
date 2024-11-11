package Controllers.AppManagers;

import Common.AppManager;
import Common.ClearOutput;
import Controllers.AccountManager;
import Databases.AccountDatabase;
import Databases.AppointmentOutcomeDatabase;
import Databases.InventoryDatabase;
import Databases.InventoryRequestDatabase;
import UI.UpdateDetailsPage;
import UI.UserMenu;

public class PharmaAppMgr extends AppManager {
    // Declare managers

    @Override
    public void displayMainPage() {
        boolean logout = false;
        while (!logout) {
            ClearOutput.clearOutput();
            int selection = UserMenu.displayPharmacistMenu();

            switch (selection) {
                case 1:
                    viewInventory();
                    break;
                case 2:
                    updateMedicationStock();
                    break;
                case 3:
                    processReplenishmentRequests();
                    break;
                case 4:
                    viewAppointmentPrescriptions();
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
        accountManager = new AccountManager(account, accountDatabase, medicalRecordDatabase);
    }

    @Override
    protected void createPages() {
        updateDetailsPage = new UpdateDetailsPage(accountManager);
    }

    // Methods to handle each menu option
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
