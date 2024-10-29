package Databases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Common.Database;
import Common.DatabaseItems;
import DatabaseItems.Medicines;

public class InventoryDatabase extends Database {

    public InventoryDatabase() {
        setcsvPath("Database/InventoryStock.csv");
        extractFromCSV();
    }

    public InventoryDatabase(String csvPath) {
        setcsvPath(csvPath);
        extractFromCSV();
    }

    @Override
    public void extractFromCSV() {
        // System.out.println(System.getProperty("user.dir")); uncomment this line to
        // print your directory
        try (Scanner scanner = new Scanner(new File(csvPath))) {
            // Skip the header
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            // medicine,stock,alert_level
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                String medicine = values[0];
                int stock = Integer.parseInt(values[1]);
                int alert_level = Integer.parseInt(values[2]);

                records.add(new Medicines(medicine, stock, alert_level));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void storeToCSV() {
        try (FileWriter writer = new FileWriter(csvPath)) {
            // Write header line
            writer.write("medicine,stock,alert_level\n");

            // Write Medicines details
            for (DatabaseItems record : records) {
                Medicines Medicines = (Medicines) record;
                writer.write(String.format("%s,%s,%s\n",
                        Medicines.getMedicine(),
                        Medicines.getStock(),
                        Medicines.getAlertLevel()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printItems() {
        // Print the Inventory
        System.out.println("Inventory Database: ");
        System.out.println("--------------------------");
        for (DatabaseItems record : records) {
            if (record instanceof Medicines) { // Ensure it's an Medicines
                Medicines Medicines = (Medicines) record;
                System.out.println(); // Print a new line for better readability
                System.out.println("Medicine: " + Medicines.getMedicine());
                System.out.println("Stock: " + Medicines.getStock());
                System.out.println("Alert Level: " + Medicines.getAlertLevel());
                System.out.println(); // Print a new line for better readability
            }
        }
    }

    public void addItem(Medicines medicine) {
        records.add(medicine);
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
