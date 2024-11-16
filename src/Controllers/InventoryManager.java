package Controllers;

import DatabaseItems.Medicines;
import Databases.InventoryDatabase;

/**
 * The {@code InventoryManager} class manages the inventory of medicines.
 * It provides functionalities to add, remove, and update stock levels,
 * as well as to display the current inventory.
 */
public class InventoryManager {
    private InventoryDatabase inventoryDatabase;

    /**
     * Constructs an {@code InventoryManager} with the specified inventory database.
     *
     * @param inventoryDatabase the {@link InventoryDatabase} used to manage medicine inventory
     */
    public InventoryManager(InventoryDatabase inventoryDatabase) {
        this.inventoryDatabase = inventoryDatabase;
    }

    /**
     * Adds a new medicine to the inventory.
     *
     * @param medicineName the name of the medicine to be added
     * @param value        the initial stock quantity of the medicine
     * @param alert        the alert level for low stock
     * @return true if the medicine was added successfully; false if it already exists
     */
    public boolean addMedicine(String medicineName, int value, int alert) {
        Medicines stock = (Medicines) inventoryDatabase.searchItem(medicineName);
        if (stock != null)
            return false; // Medicine already exists
        stock = new Medicines(medicineName, value, alert);
        inventoryDatabase.addItem(stock);
        return true; // Medicine added successfully
    }

    /**
     * Removes a medicine from the inventory.
     *
     * @param medicineName the name of the medicine to be removed
     * @return true if the medicine was removed successfully; false if not found
     */
    public boolean removeMedicine(String medicineName) {
        Medicines stock = (Medicines) inventoryDatabase.searchItem(medicineName);
        if (stock == null)
            return false; // Medicine not found
        inventoryDatabase.removeItem(medicineName);
        return true; // Medicine removed successfully
    }

    /**
     * Adds stock to an existing medicine in the inventory.
     *
     * @param medicineName the name of the medicine for which to add stock
     * @param value        the amount of stock to add
     * @return true if stock was added successfully; false if the medicine is not found
     */
    public boolean addStock(String medicineName, int value) {
        Medicines stock = (Medicines) inventoryDatabase.searchItem(medicineName);
        if (stock == null)
            return false; // Medicine not found
        stock.setStock(value + stock.getStock()); // Update stock quantity
        return true; // Stock added successfully
    }

    /**
     * Removes a specified amount of stock from an existing medicine.
     *
     * @param medicineName the name of the medicine from which to remove stock
     * @param value        the amount of stock to remove
     * @return true if stock was removed successfully; false if the medicine is not found
     */
    public boolean removeStock(String medicineName, int value) {
        Medicines stock = (Medicines) inventoryDatabase.searchItem(medicineName);
        if (stock == null)
            return false; // Medicine not found
        stock.setStock(stock.getStock() - value < 0 ? 0 : stock.getStock() - value); // Prevent negative stock
        return true; // Stock removed successfully
    }

    /**
     * Updates the stock level of an existing medicine.
     *
     * @param medicineName the name of the medicine whose stock is to be updated
     * @param value        the new stock quantity for the medicine
     * @return true if the stock was updated successfully; false if not found
     */
    public boolean updateStock(String medicineName, int value) {
        Medicines stock = (Medicines) inventoryDatabase.searchItem(medicineName);
        if (stock == null)
            return false; // Medicine not found
        stock.setStock(value < 0 ? 0 : value); // Prevent negative stock
        return true; // Stock updated successfully
    }

    /**
     * Updates the alert level for low stock of an existing medicine.
     *
     * @param medicineName the name of the medicine whose alert level is to be updated
     * @param value        the new alert level for low stock
     * @return true if the alert level was updated successfully; false if not found
     */
    public boolean updateAlertLine(String medicineName, int value) {
        Medicines stock = (Medicines) inventoryDatabase.searchItem(medicineName);
        if (stock == null)
            return false; // Medicine not found
        stock.setAlertLevel(value < 0 ? 0 : value); // Prevent negative alert level
        return true; // Alert level updated successfully
    }

    /**
     * Displays all items in the current inventory.
     */
    public void displayInventory() {
        inventoryDatabase.printItems(); // Print all items in inventory database
    }
}