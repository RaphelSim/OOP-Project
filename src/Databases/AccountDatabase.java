package Databases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Common.*;
import DatabaseItems.Account;

public class AccountDatabase extends Database {
    public AccountDatabase() {
        setcsvPath("Database/AccountCredentials.csv");
        extractFromCSV();
    }

    public AccountDatabase(String csvpath) {
        setcsvPath(csvpath);
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

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                String name = values[0];
                String id = values[1];
                Role role = Role.fromString(values[3]);
                // Add a new Account to the records list
                records.add(new Account(name, id, role));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void storeToCSV() {
        try (FileWriter writer = new FileWriter(csvPath)) {
            // Write header line
            writer.write("Name,id,Password,Role\n");

            // Write account details
            for (DatabaseItems user : records) {
                Account account = (Account) user;
                writer.write(String.format("%s,%s,%s,%s\n",
                        account.getName(),
                        account.getid(),
                        account.getpassword(), // You can handle password encryption or use the actual password
                        account.getrole().toString()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printItems() {
        // Print the users
        System.out.println("Account Database:");
        System.out.println("-------------------");
        for (DatabaseItems user : records) {
            Account account = (Account) user;
            System.out.println(); // Print a new line for better readability
            System.out.println("ID: " + account.getid());
            System.out.println("Name: " + account.getName());
            System.out.println("Role: " + account.getrole().toString());
            System.out.println("Password: " + account.getpassword());
            System.out.println(); // Print a new line for better readability
        }
    }

    // method overloading, add item to the database
    public void addItem(Account account) {
        records.add(account);
    }

    // method overloading, remove item from database, return true id successful else
    // return false
    public boolean removeItem(String userid) {
        boolean accountRemoved = records.removeIf(user -> {
            Account account = (Account) user;
            return account.getid().equals(userid);
        });

        if (accountRemoved) {
            return true;
        } else {
            return false;
        }
    }
}
