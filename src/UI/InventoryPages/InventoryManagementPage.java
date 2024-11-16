package UI.InventoryPages;

import Common.ClearOutput;
import Common.UserInterface;
import Controllers.InventoryManager;

/**
 * The {@code InventoryManagementPage} class provides functionality for managing
 * the inventory of medicines. It extends {@link UserInterface} and interacts
 * with the {@link InventoryManager} to perform operations such as viewing,
 * adding, updating, and removing medicines.
 */
public class InventoryManagementPage extends UserInterface {
    private InventoryManager inventoryManager; // Manager for handling inventory operations

    /**
     * Constructs an {@code InventoryManagementPage} with the specified
     * {@link InventoryManager} instance.
     *
     * @param inventoryManager the manager used to handle inventory operations
     */
    public InventoryManagementPage(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager; // Initialize the inventory manager
    }

    /**
     * Displays options for managing the inventory and handles user input.
     */
    public void displayOptions() {
        boolean quit = false;
        while (!quit) {
            ClearOutput.clearOutput(); // Clear previous output
            System.out.println("Please select an option: ");
            System.out.println("1. View Inventory");
            System.out.println("2. Update Inventory");
            System.out.println("3. Add Medicine");
            System.out.println("4. Remove Medicine");
            System.out.println("5. Back");
            int choice = getIntInput(6); // Get user input with a maximum option of 6
            
            switch (choice) {
                case 1 -> handleViewInventory(); // View all inventory items
                case 2 -> handleUpdateStock(); // Update stock levels of medicines
                case 3 -> handleAddMedicine(); // Add a new medicine to the inventory
                case 4 -> handleRemoveMedicine(); // Remove a medicine from the inventory
                case 5 -> {
                    quit = true; // Exit the loop
                    continue; // Continue to next iteration
                }
                default -> displayError("Invalid option selected."); // Handle invalid input
            }
        }
    }

    /**
     * Displays all items in the inventory.
     */
    public void handleViewInventory() {
        ClearOutput.clearOutput();
        inventoryManager.displayInventory(); // Display current inventory items
        pauseAndView(); // Pause to allow user to view output before proceeding
    }

    /**
     * Prompts the user to add a new medicine to the inventory.
     */
    public void handleAddMedicine() {
        String name = getValidatedString("Enter the name of new medicine"); // Get medicine name
        int value = getValidatedInt("Enter the stock level: "); // Get initial stock level
        int alert = getValidatedInt("Enter the alert level: "); // Get alert level
        
        if (inventoryManager.addMedicine(name, value, alert)) { // Attempt to add medicine
            displaySuccess("Medicine added"); // Success message
        } else {
            displayError("Medicine exists already"); // Error message if medicine already exists
        }
    }

    /**
     * Prompts the user to remove a medicine from the inventory.
     */
    public void handleRemoveMedicine() {
        String medicine = getValidatedString("Enter name of medicine to remove"); // Get medicine name
        
        if (inventoryManager.removeMedicine(medicine)) { // Attempt to remove medicine
            displaySuccess("Medicine removed successfully"); // Success message
        } else {
            displayError("Medicine not found"); // Error message if medicine not found
        }
    }

    /**
     * Allows updating of stock levels for medicines in the inventory.
     */
    public void handleUpdateStock() {
        ClearOutput.clearOutput();
        inventoryManager.displayInventory(); // Display current inventory items
        
        System.out.println();
        String medicine = getValidatedString("Enter name of medicine to update"); // Get medicine name
        
        System.out.println("Please select an option: ");
        System.out.println("1. Edit Alert Level");
        System.out.println("2. Add Stock");
        System.out.println("3. Remove Stock");
        System.out.println("4. Change Stock Value");
        System.out.println("5. Back");
        
        int choice = getIntInput(5); // Get user input with a maximum option of 5
        
        switch (choice) {
            case 1 -> handleUpdateAlertLevel(medicine); // Update alert level for the specified medicine
            case 2 -> handleAddStock(medicine); // Add stock for the specified medicine
            case 3 -> handleRemoveStock(medicine); // Remove stock for the specified medicine
            case 4 -> handleEditStock(medicine); // Change stock value for the specified medicine
            case 5 -> {
                return; // Exit method if back is selected
            }
            default -> displayError("Invalid option selected."); // Handle invalid input
        }
    }

    /**
     * Prompts the user to add stock for a specified medicine.
     *
     * @param medicine the name of the medicine for which stock is being added
     */
    public void handleAddStock(String medicine) {
        int value = getValidatedInt("Enter the value to add: "); // Get quantity to add
        
        if (inventoryManager.addStock(medicine, value)) { 
            displaySuccess("Successfully added"); // Success message if addition is successful
        } else {
            displayError("Failed to add"); // Error message if addition fails
        }
    }

    /**
     * Prompts the user to remove stock from a specified medicine.
     *
     * @param medicine the name of the medicine from which stock is being removed
     */
    public void handleRemoveStock(String medicine) {
        int value = getValidatedInt("Enter the value to remove: "); // Get quantity to remove
        
        if (inventoryManager.removeStock(medicine, value)) { 
            displaySuccess("Successfully removed"); // Success message if removal is successful
        } else {
            displayError("Failed to remove"); // Error message if removal fails
        }
    }

    /**
     * Prompts the user to change the stock value of a specified medicine.
     *
     * @param medicine the name of the medicine whose stock value is being changed
     */
    public void handleEditStock(String medicine) {
        int value = getValidatedInt("Enter the new stock value: "); // Get new stock value
        
        if (inventoryManager.updateStock(medicine, value)) { 
            displaySuccess("Successfully updated"); // Success message if update is successful
        } else {
            displayError("Failed to update"); // Error message if update fails
        }
    }

    /**
     * Prompts the user to update the alert level for a specified medicine.
     *
     * @param medicine the name of the medicine whose alert level is being updated
     */
    public void handleUpdateAlertLevel(String medicine) {
        int value = getValidatedInt("Enter the new alert level: "); // Get new alert level
        
        if (inventoryManager.updateAlertLine(medicine, value)) { 
            displaySuccess("Successfully updated"); // Success message if update is successful
        } else {
            displayError("Failed to update"); // Error message if update fails
        }
    }
}