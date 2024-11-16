package Controllers;

import DatabaseItems.InventoryRequest;
import Databases.InventoryRequestDatabase;

/**
 * The {@code InventoryRequestManager} class manages inventory requests.
 * It allows adding new requests, approving existing requests, and displaying all requests.
 */
public class InventoryRequestManager {
    private InventoryRequestDatabase inventoryRequestDatabase;
    private InventoryManager inventoryManager;

    /**
     * Constructs an {@code InventoryRequestManager} for handling requests only.
     *
     * @param inventoryRequestDatabase the {@link InventoryRequestDatabase} used to manage inventory requests
     */
    public InventoryRequestManager(InventoryRequestDatabase inventoryRequestDatabase) {
        this.inventoryRequestDatabase = inventoryRequestDatabase;
    }

    /**
     * Constructs an {@code InventoryRequestManager} for handling both requests and approvals.
     *
     * @param inventoryRequestDatabase the {@link InventoryRequestDatabase} used to manage inventory requests
     * @param inventoryManager         the {@link InventoryManager} used to manage inventory stock
     */
    public InventoryRequestManager(InventoryRequestDatabase inventoryRequestDatabase,
                                   InventoryManager inventoryManager) {
        this.inventoryRequestDatabase = inventoryRequestDatabase;
        this.inventoryManager = inventoryManager;
    }

    /**
     * Adds a new inventory request.
     *
     * @param medicine the name of the medicine requested
     * @param value    the quantity of the medicine requested
     * @return true if the request was added successfully; false if the value is less than or equal to zero
     */
    public boolean addRequest(String medicine, int value) {
        if (value <= 0)
            return false; // Invalid request value
        InventoryRequest request = new InventoryRequest(medicine, value);
        inventoryRequestDatabase.addItem(request);
        return true; // Request added successfully
    }

    /**
     * Approves an existing inventory request and updates the stock accordingly.
     *
     * @param medicine the name of the medicine to approve
     * @param value    the quantity of the medicine to approve
     * @return true if the request was approved successfully; false if not found
     */
    public boolean approveRequest(String medicine, int value) {
        InventoryRequest request = (InventoryRequest) inventoryRequestDatabase.searchItem(medicine, value);
        if (request == null)
            return false; // Request not found

        if (!inventoryManager.addStock(medicine, value)) {
            // Add the new medicine if not found in inventory
            inventoryManager.addMedicine(medicine, value, 0);
        }
        
        inventoryRequestDatabase.removeItem(medicine, value); // Remove approved request from database
        return true; // Request approved successfully
    }

    /**
     * Displays all current inventory requests.
     */
    public void displayAllRequests() {
        inventoryRequestDatabase.printItems(); // Print all items in the request database
    }
}