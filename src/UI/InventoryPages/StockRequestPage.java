package UI.InventoryPages;

import Common.ClearOutput;
import Common.UserInterface;
import Controllers.InventoryRequestManager;

/**
 * The {@code StockRequestPage} class provides functionality for submitting
 * stock requests for medicines. It extends {@link UserInterface} and interacts
 * with the {@link InventoryRequestManager} to handle inventory requests.
 */
public class StockRequestPage extends UserInterface {
    private InventoryRequestManager inventoryRequestManager; // Manager for handling inventory requests

    /**
     * Constructs a {@code StockRequestPage} with the specified
     * {@link InventoryRequestManager} instance.
     *
     * @param inventoryRequestManager the manager used to handle inventory requests
     */
    public StockRequestPage(InventoryRequestManager inventoryRequestManager) {
        this.inventoryRequestManager = inventoryRequestManager; // Initialize the inventory request manager
    }

    /**
     * Displays options for submitting a stock request.
     * Prompts the user for medicine name and request value,
     * and submits the request through the inventory request manager.
     */
    public void displayOption() {
        ClearOutput.clearOutput(); // Clear previous output
        String name = getValidatedString("Please enter the name of medicine to request: "); // Get medicine name
        int value = getValidatedInt("Please enter the value to request for: "); // Get request value
        
        if (inventoryRequestManager.addRequest(name, value)) { // Attempt to add request
            displaySuccess("Request submitted successfully!"); // Success message
        } else {
            displayError("Invalid request!"); // Error message if request fails
        }
    }
}