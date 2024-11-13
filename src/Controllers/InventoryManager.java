package Controllers;

import DatabaseItems.Medicines;
import Databases.InventoryDatabase;

public class InventoryManager {
    private InventoryDatabase inventoryDatabase;

    public InventoryManager(InventoryDatabase inventoryDatabase) {
        this.inventoryDatabase = inventoryDatabase;
    }

    public boolean addStock(String medicineName, int value) {
        Medicines stock = (Medicines) inventoryDatabase.searchItem(medicineName);
        if (stock == null)
            return false;
        stock.setStock(value + stock.getStock());
        return true;
    }

    public boolean removeStock(String medicineName, int value) {
        Medicines stock = (Medicines) inventoryDatabase.searchItem(medicineName);
        if (stock == null)
            return false;
        stock.setStock(
                stock.getStock() - value < 0 ? 0 : stock.getStock() - value);
        return true;
    }

    public boolean updateStock(String medicineName, int value) {
        Medicines stock = (Medicines) inventoryDatabase.searchItem(medicineName);
        if (stock == null)
            return false;
        stock.setStock(
                value < 0 ? 0 : value);
        return true;
    }

    public boolean updateAlertLine(String medicineName, int value) {
        Medicines stock = (Medicines) inventoryDatabase.searchItem(medicineName);
        if (stock == null)
            return false;
        stock.setAlertLevel(
                value < 0 ? 0 : value);
        return true;
    }

    public void displayInventory() {
        inventoryDatabase.printItems();
    }
}