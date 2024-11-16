package UI.InventoryPages;

import Common.UserInterface;
import Controllers.InventoryRequestManager;

/**
 * The {@code ApproveReplenishRequestPage} class provides functionality for
 * approving inventory replenishment requests. It extends {@link UserInterface}
 * and interacts with the {@link InventoryRequestManager} to manage requests.
 */
public class ApproveReplenishRequestPage extends UserInterface {
    private InventoryRequestManager inventoryRequestManager; // Manager for handling inventory requests

    /**
     * Constructs an {@code ApproveReplenishRequestPage} with the specified
     * {@link InventoryRequestManager} instance.
     *
     * @param inventoryRequestManager the manager used to handle inventory requests
     */
    public ApproveReplenishRequestPage(InventoryRequestManager inventoryRequestManager) {
        this.inventoryRequestManager = inventoryRequestManager; // Initialize the inventory request manager
    }

    /**
     * Displays options for approving replenishment requests.
     * It shows all current requests and prompts for user input to approve a specific request.
     */
    public void displayOptions() {
        inventoryRequestManager.displayAllRequests(); // Display all replenishment requests
        System.out.println();
        
        String name = getValidatedString("Please enter the name of medicine to approve: "); // Get medicine name
        int value = getValidatedInt("Enter the value to approve"); // Get quantity to approve
        
        if (inventoryRequestManager.approveRequest(name, value)) { // Attempt to approve request
            displaySuccess("Request approved successfully!"); // Success message
        } else {
            displayError("Request not found!"); // Error message if request is not found
        }
    }
}