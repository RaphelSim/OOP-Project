package Databases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Common.Database;
import Common.DatabaseItems;
import DatabaseItems.InventoryRequest;
import DatabaseItems.InventoryRequest;
import DatabaseItems.InventoryRequest;

public class InventoryRequestDatabase extends Database {

    public InventoryRequestDatabase() {
        setcsvPath("Database/InventoryRequests.csv");
        extractFromCSV();
    }

    public InventoryRequestDatabase(String csvPath) {
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
            // medicine,request_value
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                String medicine = values[0];
                int request_value = Integer.parseInt(values[1]);

                records.add(new InventoryRequest(medicine, request_value));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void storeToCSV() {
        try (FileWriter writer = new FileWriter(csvPath)) {
            // Write header line
            writer.write("medicine,request_value\n");

            // Write InventoryRequest details
            for (DatabaseItems record : records) {
                InventoryRequest InventoryRequest = (InventoryRequest) record;
                writer.write(String.format("%s,%s\n",
                        InventoryRequest.getMedicine(),
                        InventoryRequest.getRequestValue()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printItems() {
        // Print the InventoryRequests
        System.out.println("Inventory Requests Database: ");
        System.out.println("--------------------------");
        for (DatabaseItems record : records) {
            if (record instanceof InventoryRequest) { // Ensure it's an InventoryRequest
                InventoryRequest InventoryRequest = (InventoryRequest) record;
                System.out.println(); // Print a new line for better readability
                System.out.println("Medicine: " + InventoryRequest.getMedicine());
                System.out.println("Request Value: " + InventoryRequest.getRequestValue());
                System.out.println(); // Print a new line for better readability
            }
        }
    }

    public void addItem(InventoryRequest request) {
        records.add(request);
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
