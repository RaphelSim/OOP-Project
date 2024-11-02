package Databases;

import Common.Database;
import Common.DatabaseItems;
import DatabaseItems.InventoryRequest;

public class InventoryRequestDatabase extends Database {

    public InventoryRequestDatabase() {
        setHeaderFormat("medicine,request_value");
        setcsvPath("Database/InventoryRequests.csv");
        extractFromCSV();
    }

    public InventoryRequestDatabase(String csvPath) {
        setHeaderFormat("medicine,request_value");
        setcsvPath(csvPath);
        extractFromCSV();
    }

    public DatabaseItems createDatabaseItem(String[] values){
        return new InventoryRequest(values);
    }

    public void printItems() {
        printItems("Inventory Request Database");
    }

    public boolean removeItem(String medicine){
        boolean itemRemoved = records.removeIf(record -> {
            InventoryRequest item = (InventoryRequest) record;
            return item.getMedicine().equals(medicine);
        });

        if (itemRemoved) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean removeItem(String medicine, int request_value) {
        boolean itemRemoved = records.removeIf(record -> {
            InventoryRequest item = (InventoryRequest) record;
            return item.getMedicine().equals(medicine) && item.getRequestValue() == request_value;
        });

        if (itemRemoved) {
            return true;
        } else {
            return false;
        }
    }
}
