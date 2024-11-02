package Databases;

import java.util.ArrayList;
import java.util.List;

import Common.Database;
import Common.DatabaseItems;
import DatabaseItems.InventoryRequest;
import DatabaseItems.Medicines;

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

    public DatabaseItems searchItem(String medicine){
        for (DatabaseItems item : records) {
            Medicines request = (Medicines) item;
            if (request.getMedicine().equals(medicine)) {
                return request; // Return the found item
            }
        }
        return null;
    }
    
    public DatabaseItems searchItem(String medicine, int request_value){
        for (DatabaseItems item : records) {
            InventoryRequest request = (InventoryRequest) item;
            if (request.getMedicine().equals(medicine) && request.getRequestValue() == request_value) {
                return request; // Return the found item
            }
        }
        return null;
    }

    public List<InventoryRequest> searchItems(String medicine) {
    List<InventoryRequest> matchingItems = new ArrayList<>(); // List to store found items
    for (DatabaseItems item : records) {
        InventoryRequest account = (InventoryRequest) item;
        if (account.getMedicine().equals(medicine)) {
            matchingItems.add(account); // Add matching item to the list
        }
    }
    return matchingItems; // Return the list of matching items
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
