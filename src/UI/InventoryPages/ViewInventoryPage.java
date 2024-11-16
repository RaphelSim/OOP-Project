package UI.InventoryPages;

import Common.UserInterface;
import Databases.InventoryDatabase;

/**
 * The {@code ViewInventoryPage} class provides functionality for displaying
 * the inventory of medicines. It extends {@link UserInterface} and interacts
 * with the {@link InventoryDatabase} to retrieve and display inventory details.
 */
public class ViewInventoryPage extends UserInterface {

    /**
     * Displays the contents of the specified inventory database.
     *
     * @param inventoryDatabase the {@link InventoryDatabase} containing inventory items to display
     */
    public void display(InventoryDatabase inventoryDatabase) {
        inventoryDatabase.printItems(); // Print all items in the inventory database
        pauseAndView(); // Pause to allow user to view output before proceeding
    }
}