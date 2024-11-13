package Controllers;

import DatabaseItems.InventoryRequest;
import Databases.InventoryRequestDatabase;

public class InventoryRequestManager {
    InventoryRequestDatabase inventoryRequestDatabase;
    InventoryManager inventoryManager;

    // for request only
    public InventoryRequestManager(InventoryRequestDatabase inventoryRequestDatabase) {
        this.inventoryRequestDatabase = inventoryRequestDatabase;
    }

    // for approval
    public InventoryRequestManager(InventoryRequestDatabase inventoryRequestDatabase,
            InventoryManager inventoryManager) {
        this.inventoryRequestDatabase = inventoryRequestDatabase;
        this.inventoryManager = inventoryManager;
    }

    public boolean addRequest(String medicine, int value) {
        if (value <= 0)
            return false;
        InventoryRequest request = new InventoryRequest(medicine, value);
        inventoryRequestDatabase.addItem(request);
        return true;
    }

    public boolean approveRequest(String medicine, int value) {
        InventoryRequest request = (InventoryRequest) inventoryRequestDatabase.searchItem(medicine, value);
        if (request == null)
            return false;
        if (!inventoryManager.addStock(medicine, value)) {
            // add the new medicine if not found in inventory
            inventoryManager.addMedicine(medicine, value, 0);
        }
        inventoryRequestDatabase.removeItem(medicine, value);
        return true;
    }

    public void displayAllRequests() {
        inventoryRequestDatabase.printItems();
    }

}
