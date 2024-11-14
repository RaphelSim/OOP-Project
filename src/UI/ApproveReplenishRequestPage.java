package UI;

import Common.UserInterface;
import Controllers.InventoryRequestManager;

public class ApproveReplenishRequestPage extends UserInterface {
    private InventoryRequestManager inventoryRequestManager;

    public ApproveReplenishRequestPage(InventoryRequestManager inventoryRequestManager) {
        this.inventoryRequestManager = inventoryRequestManager;
    }

    public void displayOptions() {
        inventoryRequestManager.displayAllRequests();
        System.out.println();
        String name = getValidatedString("Please enter the name of medicine to approve: ");
        int value = getValidatedInt("Enter the value to approve");
        if (inventoryRequestManager.approveRequest(name, value)) {
            displaySuccess("Request approved successfully!");
        } else {
            displayError("Request not found!");
        }
    }
}
