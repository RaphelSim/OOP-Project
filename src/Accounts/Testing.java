package Accounts;

import Common.Role;

public class Testing {
    public static void main(String[] args) {
        AccountDatabase database = new AccountDatabase("Database/AccountCredentials.csv");
        Account account = new Account("test", "test", Role.ADM, "test", "test", "test");
        database.abstractFromCSV();
        database.addAccount(account);
        database.storeToCSV();
    }
}
