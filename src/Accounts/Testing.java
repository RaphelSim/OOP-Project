package Accounts;

import Common.Role;

public class Testing {
    public static void main(String[] args) {
        AccountDatabase database = new AccountDatabase("Database/AccountCredentials.csv");
        Account account = new Account("test2", "test2", Role.ADM, "test", "test", "test");
        database.abstractFromCSV();
        database.addAccount(account);
        database.removeAccount("test");
        database.storeToCSV();
    }
}
