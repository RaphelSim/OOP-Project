package Common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The {@code Database} class serves as an abstract base class for managing
 * collections of {@link DatabaseItems}. It provides methods for extracting
 * items from a CSV file, storing items to a CSV file, and managing
 * database records.
 */
public abstract class Database {
    protected String csvPath;
    protected String headerFormat;
    protected ArrayList<DatabaseItems> records = new ArrayList<DatabaseItems>();

    /**
     * Sets the path to the CSV file used for storing and retrieving data.
     *
     * @param path the path to the CSV file
     */
    public void setcsvPath(String path) {
        this.csvPath = path;
    }

    /**
     * Extracts database items from the specified CSV file.
     * This method reads from the CSV file, skipping the header,
     * and populates the {@code records} list with items created
     * using the {@link #createDatabaseItem(String[])} method.
     */
    public void extractFromCSV() {
        try (Scanner scanner = new Scanner(new File(csvPath))) {
            // Skip the header
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");

                // Use the abstract method to create a specific DatabaseItem
                records.add(createDatabaseItem(values));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stores all database items to the specified CSV file.
     * This method writes the header format followed by each item's
     * serialized representation to the CSV file.
     */
    public void storeToCSV() {
        try (FileWriter writer = new FileWriter(csvPath)) {
            // Write header line
            writer.write(headerFormat + "\n");
            // Write account details
            for (DatabaseItems items : records) {
                writer.write(items.serialise());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the header format for the CSV file.
     *
     * @param headerFormat a string representing the header format
     */
    protected void setHeaderFormat(String headerFormat) {
        this.headerFormat = headerFormat;
    }

    /**
     * Prints all items in the database with a specified title.
     *
     * @param title the title to be printed before listing items
     */
    protected void printItems(String title) {
        System.out.println(title);
        System.out.println("-------------------");
        for (DatabaseItems item : records) {
            item.printItem();
        }
    }

    /**
     * Adds a new item to the database records.
     *
     * @param item the {@link DatabaseItems} object to be added
     */
    public void addItem(DatabaseItems item) {
        records.add(item);
    };

    /**
     * Returns a list of all database items.
     *
     * @return a list of {@link DatabaseItems} objects in this database
     */
    public List<DatabaseItems> getRecords() {
        return records;
    }

    /**
     * Creates a specific {@link DatabaseItems} object from an array of values.
     * This method must be implemented by subclasses to define how 
     * database items are created from CSV data.
     *
     * @param values an array of strings containing data for creating an item
     * @return a new instance of {@code DatabaseItems}
     */
    protected abstract DatabaseItems createDatabaseItem(String[] values);

    /**
     * Prints all items in this database. This method must be implemented 
     * by subclasses to provide specific printing functionality.
     */
    public abstract void printItems();

    /**
     * Removes an item from the database using its unique identifier.
     *
     * @param id the unique identifier of the item to be removed
     * @return true if the item was successfully removed; false otherwise
     */
    public abstract boolean removeItem(String id);

    /**
     * Searches for an item in the database using its unique identifier.
     *
     * @param id the unique identifier of the item to search for
     * @return the found {@link DatabaseItems} object, or null if not found
     */
    public abstract DatabaseItems searchItem(String id);
}