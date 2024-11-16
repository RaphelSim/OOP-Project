package Databases;

import java.util.ArrayList;
import java.util.List;

import Common.Database;
import Common.DatabaseItems;
import DatabaseItems.InventoryRequest;
import DatabaseItems.Medicines;

/**
 * The {@code InventoryRequestDatabase} class manages a collection of inventory requests.
 * It extends {@link Database} and provides methods for creating, searching, 
 * removing, and printing inventory requests.
 */
public class InventoryRequestDatabase extends Database {

    /**
     * Constructs an {@code InventoryRequestDatabase} with a default CSV path.
     */
    public InventoryRequestDatabase() {
        setHeaderFormat("medicine,request_value");
        setcsvPath("Database/InventoryRequests.csv");
        extractFromCSV(); // Load records from the specified CSV file
    }

    /**
     * Constructs an {@code InventoryRequestDatabase} with a specified CSV path.
     *
     * @param csvPath the path to the CSV file containing inventory requests
     */
    public InventoryRequestDatabase(String csvPath) {
        setHeaderFormat("medicine,request_value");
        setcsvPath(csvPath);
        extractFromCSV(); // Load records from the specified CSV file
    }

    /**
     * Creates a new {@link InventoryRequest} instance from an array of values.
     *
     * @param values an array of strings containing inventory request data
     * @return a new {@link InventoryRequest} object
     */
    public DatabaseItems createDatabaseItem(String[] values) {
        return new InventoryRequest(values); // Create and return a new inventory request
    }

    /**
     * Prints all items in the inventory request database.
     */
    public void printItems() {
        printItems("Inventory Request Database"); // Print items with specified header
    }

    /**
     * Searches for an inventory request by medicine name.
     *
     * @param medicine the name of the medicine to search for
     * @return the found {@link Medicines} item; null if not found
     */
    public DatabaseItems searchItem(String medicine) {
        for (DatabaseItems item : records) {
            Medicines request = (Medicines) item;
            if (request.getMedicine().equals(medicine)) {
                return request; // Return the found item
            }
        }
        return null; // Return null if no matching record is found
    }

    /**
     * Searches for an inventory request by medicine name and request value.
     *
     * @param medicine      the name of the medicine to search for
     * @param request_value the requested quantity of the medicine
     * @return the found {@link InventoryRequest} item; null if not found
     */
    public DatabaseItems searchItem(String medicine, int request_value) {
        for (DatabaseItems item : records) {
            InventoryRequest request = (InventoryRequest) item;
            if (request.getMedicine().equals(medicine) && request.getRequestValue() == request_value) {
                return request; // Return the found item
            }
        }
        return null; // Return null if no matching record is found
    }

    /**
     * Searches for all inventory requests associated with a specific medicine.
     *
     * @param medicine the name of the medicine to search for
     * @return a list of matching {@link InventoryRequest} objects; empty list if none found
     */
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

    /**
     * Removes an inventory request by medicine name.
     *
     * @param medicine the name of the medicine to remove from requests
     * @return true if any record was successfully removed; false otherwise
     */
    public boolean removeItem(String medicine) {
        boolean itemRemoved = records.removeIf(record -> {
            InventoryRequest item = (InventoryRequest) record;
            return item.getMedicine().equals(medicine); // Check if medicine matches
        });

        return itemRemoved; // Return true if a record was removed
    }

    /**
     * Removes an inventory request by medicine name and request value.
     *
     * @param medicine      the name of the medicine to remove from requests
     * @param request_value the requested quantity to match for removal
     * @return true if any record was successfully removed; false otherwise
     */
    public boolean removeItem(String medicine, int request_value) {
        boolean itemRemoved = records.removeIf(record -> {
            InventoryRequest item = (InventoryRequest) record;
            return item.getMedicine().equals(medicine) && item.getRequestValue() == request_value; // Check both criteria
        });

        return itemRemoved; // Return true if a record was removed
    }
}