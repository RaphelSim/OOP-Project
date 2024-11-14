package Controllers;

import DatabaseItems.Medicines;
import Databases.InventoryDatabase;

public class InventoryManager {
    private InventoryDatabase inventoryDatabase;

    public InventoryManager(InventoryDatabase inventoryDatabase) {
        this.inventoryDatabase = inventoryDatabase;
    }

    public boolean addMedicine(String medicineName, int value, int alert) {
        Medicines stock = (Medicines) inventoryDatabase.searchItem(medicineName);
        if (stock != null)
            return false;
        stock = new Medicines(medicineName, value, alert);
        inventoryDatabase.addItem(stock);
        return true;
    }

    public boolean removeMedicine(String medicineName) {
        Medicines stock = (Medicines) inventoryDatabase.searchItem(medicineName);
        if (stock == null)
            return false;
        inventoryDatabase.removeItem(medicineName);
        return true;
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