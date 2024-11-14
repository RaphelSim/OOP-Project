package UI.InventoryPages;

import Common.ClearOutput;
import Common.UserInterface;
import Controllers.InventoryRequestManager;

public class StockRequestPage extends UserInterface {
    private InventoryRequestManager inventoryRequestManager;

    public StockRequestPage(InventoryRequestManager inventoryRequestManager) {
        this.inventoryRequestManager = inventoryRequestManager;
    }

    public void displayOption() {
        ClearOutput.clearOutput();
        String name = getValidatedString("Please enter the name of medicine to request: ");
        int value = getValidatedInt("Please enter the value to request for: ");
        if (inventoryRequestManager.addRequest(name, value)) {
            displaySuccess("Request submitted successfully!");
        } else {
            displayError("Invalid request!");
        }
    }
}
