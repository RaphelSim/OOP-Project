package Accounts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Common.*;

public class AccountDatabase extends Database {
    AccountDatabase(String csvpath) {
        setcsvPath(csvpath);
    }

    @Override
    public void abstractFromCSV() {
        System.out.println(System.getProperty("user.dir"));
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
                String email = values[4].toString();
                String phone = values[5].toString();
                String dob = values[6];
                // Add a new Account to the records list
                records.add(new Account(name, id, role, dob, email, phone));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Print the users
        for (DatabaseItems user : records) {
            Account account = (Account) user;
            System.out.println(account.getName() + account.getrole().toString() + account.getEmail()
                    + account.getPhone() + account.getpassword() + account.getdob());
        }
    }

    @Override
    public void storeToCSV() {
        try (FileWriter writer = new FileWriter(csvPath)) {
            // Write header line
            writer.write("Name,ID,Password,Role,Email,Phone,DOB\n");

            // Write account details
            for (DatabaseItems user : records) {
                Account account = (Account) user;
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%s\n",
                        account.getName(),
                        account.getid(),
                        account.getpassword(), // You can handle password encryption or use the actual password
                        account.getrole().toString(),
                        account.getEmail(),
                        account.getPhone(),
                        account.getdob()));
            }
            System.out.println("Data successfully saved to CSV file.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAccount(Account account) {
        records.add(account);
    }

    public void removeAccount(String userid) {
        boolean accountRemoved = records.removeIf(user -> {
            Account account = (Account) user;
            return account.getid().equals(userid);
        });

        if (accountRemoved) {
            System.out.println("Account with ID " + userid + " removed successfully.");
        } else {
            System.out.println("Account with ID " + userid + " not found.");
        }
    }
}
