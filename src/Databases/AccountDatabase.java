package Databases;

import Common.*;
import DatabaseItems.Account;

/**
 * The {@code AccountDatabase} class manages a collection of {@link Account} 
 * objects, providing functionalities to create, search, print, and remove 
 * accounts from the database.
 */
public class AccountDatabase extends Database {

    /**
     * Constructs an {@code AccountDatabase} with a default CSV path for account credentials.
     * Initializes the header format and extracts data from the specified CSV file.
     */
    public AccountDatabase() {
        setHeaderFormat("name,id,password,role,gender,age");
        setcsvPath("Database/AccountCredentials.csv");
        extractFromCSV();
    }

    /**
     * Constructs an {@code AccountDatabase} with a specified CSV path for account credentials.
     *
     * @param csvpath the path to the CSV file containing account credentials
     */
    public AccountDatabase(String csvpath) {
        setHeaderFormat("name,id,password,role,gender,age");
        setcsvPath(csvpath);
        extractFromCSV();
    }

    /**
     * Creates a new {@link Account} object from the provided values.
     *
     * @param values an array of strings containing account details in the order:
     *               name, id, password, role, gender, age
     * @return a new {@code Account} object populated with the provided values
     */
    public DatabaseItems createDatabaseItem(String[] values) {
        // Name,id,Password,Role
        return new Account(values);
    }

    /**
     * Prints all items in the account database with a specified header.
     */
    public void printItems() {
        printItems("Account Database");
    }

    /**
     * Searches for an account in the database using the specified user ID.
     *
     * @param id the unique identifier of the account to search for
     * @return the {@link Account} object if found; otherwise, returns null
     */
    public DatabaseItems searchItem(String id) {
        for (DatabaseItems item : records) {
            Account account = (Account) item;
            if (account.getid().equals(id)) {
                return account; // Return the found item
            }
        }
        return null; // Return null if no item is found
    }

    /**
     * Removes an account from the database using the specified user ID.
     *
     * @param userid the unique identifier of the account to be removed
     * @return true if the account was successfully removed; false otherwise
     */
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