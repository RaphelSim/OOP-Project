package UI;

import Common.UserInterface;
import Databases.InventoryDatabase;

public class ViewInventoryPage extends UserInterface {

    public void display(InventoryDatabase inventoryDatabase) {
        inventoryDatabase.printItems();
        System.out.println(); // line break for readability
        System.out.println("Press ENTER to return to menu");
        scanner.nextLine();
    }
}
