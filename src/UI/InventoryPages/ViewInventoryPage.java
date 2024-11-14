package UI.InventoryPages;

import Common.UserInterface;
import Databases.InventoryDatabase;

public class ViewInventoryPage extends UserInterface {

    public void display(InventoryDatabase inventoryDatabase) {
        inventoryDatabase.printItems();
        pauseAndView();
    }
}
