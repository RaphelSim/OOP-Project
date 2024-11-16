package Databases;

import Common.Database;
import Common.DatabaseItems;
import DatabaseItems.Medicines;

/**
 * The {@code InventoryDatabase} class manages a collection of medicines in inventory.
 * It extends {@link Database} and provides methods for creating, searching, 
 * removing, and printing inventory items.
 */
public class InventoryDatabase extends Database {

    /**
     * Constructs an {@code InventoryDatabase} with a default CSV path.
     */
    public InventoryDatabase() {
        setHeaderFormat("medicine,stock,alert_level");
        setcsvPath("Database/InventoryStock.csv");
        extractFromCSV(); // Load records from the specified CSV file
    }

    /**
     * Constructs an {@code InventoryDatabase} with a specified CSV path.
     *
     * @param csvPath the path to the CSV file containing inventory data
     */
    public InventoryDatabase(String csvPath) {
        setHeaderFormat("medicine,stock,alert_level");
        setcsvPath(csvPath);
        extractFromCSV(); // Load records from the specified CSV file
    }

    /**
     * Creates a new {@link Medicines} instance from an array of values.
     *
     * @param values an array of strings containing medicine data
     * @return a new {@link Medicines} object
     */
    public DatabaseItems createDatabaseItem(String[] values) {
        return new Medicines(values); // Create and return a new medicine instance
    }

    /**
     * Prints all items in the inventory database.
     */
    public void printItems() {
        printItems("Inventory Database"); // Print items with specified header
    }

    /**
     * Searches for a medicine by its name.
     *
     * @param medicine_name the name of the medicine to search for
     * @return the found {@link Medicines} item; null if not found
     */
    public DatabaseItems searchItem(String medicine_name) {
        for (DatabaseItems item : records) {
            Medicines medicine = (Medicines) item;
            if (medicine.getMedicine().equals(medicine_name)) {
                return medicine; // Return the found item
            }
        }
        return null; // Return null if no matching record is found
    }

    /**
     * Removes a medicine from the inventory by its name.
     *
     * @param medicine the name of the medicine to remove from inventory
     * @return true if any record was successfully removed; false otherwise
     */
    public boolean removeItem(String medicine) {
        boolean itemRemoved = records.removeIf(record -> {
            Medicines item = (Medicines) record;
            return item.getMedicine().equals(medicine); // Check if medicine matches
        });

        return itemRemoved; // Return true if a record was removed
    }
}