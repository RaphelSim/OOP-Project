package UI;

import Common.ClearOutput;
import Common.UserInterface;
import Controllers.InventoryManager;

public class InventoryManagementPage extends UserInterface {
    private InventoryManager inventoryManager;

    public InventoryManagementPage(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public void displayOptions() {
        boolean quit = false;
        while (!quit) {
            ClearOutput.clearOutput();
            System.out.println("Please select an option: ");
            System.out.println("1. View Inventory");
            System.out.println("2. Update Inventory");
            System.out.println("3. Add Medicine");
            System.out.println("4. Remove Medicine");
            System.out.println("5. Back");
            int choice = getIntInput(6);
            switch (choice) {
                case 1 -> handleViewInventory();
                case 2 -> handleUpdateStock();
                case 3 -> handleAddMedicine();
                case 4 -> handleRemoveMedicine();
                case 5 -> {
                    quit = true;
                    continue;
                }
                default -> displayError("Invalid option selected.");
            }
        }
    }

    public void handleViewInventory() {
        ClearOutput.clearOutput();
        inventoryManager.displayInventory();
        System.out.println();
        System.out.println("Press ENTER to return to menu");
        scanner.nextLine();
    }

    public void handleAddMedicine() {
        String name = getValidatedString("Enter the name of new medicine");
        int value = getValidatedInt("Enter the stock level: ");
        int alert = getValidatedInt("Enter the alert level: ");
        if (inventoryManager.addMedicine(name, value, alert)) {
            displaySuccess("Medicine added");
        } else {
            displayError("Medicine exists already");
        }
    }

    public void handleRemoveMedicine() {
        String medicine = getValidatedString("Enter name of medicine to remove");
        if (inventoryManager.removeMedicine(medicine)) {
            displaySuccess("Medicine removed successfully");
        } else {
            displayError("Medicine not found");
        }
    }

    public void handleUpdateStock() {
        ClearOutput.clearOutput();
        inventoryManager.displayInventory();
        System.out.println();
        String medicine = getValidatedString("Enter name of medicine to update");
        System.out.println("Please select an option: ");
        System.out.println("1. Edit Alert Level");
        System.out.println("2. Add Stock");
        System.out.println("3. Remove Stock");
        System.out.println("4. Change Stock Value");
        System.out.println("5. Back");
        int choice = getIntInput(5);
        switch (choice) {
            case 1 -> handleUpdateAlertLevel(medicine);
            case 2 -> handleAddStock(medicine);
            case 3 -> handleRemoveStock(medicine);
            case 4 -> handleEditStock(medicine);
            case 5 -> {
                return;
            }
            default -> displayError("Invalid option selected.");
        }
    }

    public void handleAddStock(String medicine) {
        int value = getValidatedInt("Enter the value to add: ");
        if (inventoryManager.addStock(medicine, value)) {
            displaySuccess("Successfully added");
        } else {
            displayError("Failed to add");
        }
    }

    public void handleRemoveStock(String medicine) {
        int value = getValidatedInt("Enter the value to remove: ");
        if (inventoryManager.removeStock(medicine, value)) {
            displaySuccess("Successfully removed");
        } else {
            displayError("Failed to remove");
        }
    }

    public void handleEditStock(String medicine) {
        int value = getValidatedInt("Enter the new stock value: ");
        if (inventoryManager.updateStock(medicine, value)) {
            displaySuccess("Successfully updated");
        } else {
            displayError("Failed to update");
        }
    }

    public void handleUpdateAlertLevel(String medicine) {
        int value = getValidatedInt("Enter the new alert level: ");
        if (inventoryManager.updateAlertLine(medicine, value)) {
            displaySuccess("Successfully updated");
        } else {
            displayError("Failed to update");
        }
    }
}
