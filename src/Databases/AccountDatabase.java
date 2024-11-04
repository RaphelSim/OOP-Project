package Databases;


import Common.*;
import DatabaseItems.Account;

public class AccountDatabase extends Database {

    public AccountDatabase() {
        setHeaderFormat("name,id,password,role,gender,age");
        setcsvPath("Database/AccountCredentials.csv");
        extractFromCSV();
    }

    public AccountDatabase(String csvpath) {
        setHeaderFormat("name,id,password,role,gender,age");
        setcsvPath(csvpath);
        extractFromCSV();
    }

    public DatabaseItems createDatabaseItem(String[] values){
        //Name,id,Password,Role
        return new Account(values);
    }

    public void printItems() {
        printItems("Account Database");
    }

    public DatabaseItems searchItem(String id) {
        for (DatabaseItems item : records) {
            Account account = (Account) item;
            if (account.getid().equals(id)) {
                return account; // Return the found item
            }
        }
        return null; // Return null if no item is found
    }

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
