package Common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public abstract class Database {
    protected String csvPath;
    protected String headerFormat;
    protected ArrayList<DatabaseItems> records = new ArrayList<DatabaseItems>();

    public void setcsvPath(String path) {
        this.csvPath = path;
    }

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

    public void storeToCSV() {
        try (FileWriter writer = new FileWriter(csvPath)) {
            // Write header line
            writer.write(headerFormat+"\n");
            // Write account details
            for (DatabaseItems items : records) {
                writer.write(items.serialise());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void setHeaderFormat(String headerFormat){
        this.headerFormat = headerFormat;
    }

    protected void printItems(String title) {
        System.out.println(title);
        System.out.println("-------------------");
        for (DatabaseItems item : records) {
            item.printItem();
        }
    }

    public void addItem(DatabaseItems item) {
        records.add(item);
    };

    public List<DatabaseItems> getRecords(){
        return records;
    }

    // Abstract method for subclasses to implement
    protected abstract DatabaseItems createDatabaseItem(String[] values);
    public abstract void printItems();
    public abstract boolean removeItem(String id);
    public abstract DatabaseItems searchItem(String id);
}
