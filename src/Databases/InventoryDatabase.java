package Databases;

import Common.Database;
import Common.DatabaseItems;
import DatabaseItems.Medicines;

public class InventoryDatabase extends Database {

    //medicine,stock,alert_level
    public InventoryDatabase() {
        setHeaderFormat("medicine,stock,alert_level");
        setcsvPath("Database/InventoryStock.csv");
        extractFromCSV();
    }

    public InventoryDatabase(String csvPath) {
        setHeaderFormat("medicine,stock,alert_level");
        setcsvPath(csvPath);
        extractFromCSV();
    }

    public DatabaseItems createDatabaseItem(String[] values){
        return new Medicines(values);
    }

    public void printItems() {
        printItems("Inventory Database");
    }

    public DatabaseItems searchItem(String medicine_name) {
        for (DatabaseItems item : records) {
            Medicines medicine = (Medicines) item;
            if (medicine.getMedicine().equals(medicine_name)) {
                return medicine; // Return the found item
            }
        }
        return null; // Return null if no item is found
    }

    public boolean removeItem(String medicine) {
        boolean itemRemoved = records.removeIf(record -> {
            Medicines item = (Medicines) record;
            return item.getMedicine().equals(medicine);
        });

        if (itemRemoved) {
            return true;
        } else {
            return false;
        }
    }
}
