package Controllers;

import Common.AppManager;
import Common.ClearOutput;
import Controllers.AppManagers.AdminAppMgr;
import Controllers.AppManagers.DoctorAppMgr;
import Controllers.AppManagers.PatientAppMgr;
import Controllers.AppManagers.PharmaAppMgr;
import DatabaseItems.Account;
import Databases.AccountDatabase;
import UI.LoginPage;

/**
 * The {@code LoginManager} class handles user login functionality.
 * It manages the authentication process for users and directs them
 * to the appropriate application manager based on their role.
 */
public class LoginManager {
    private AccountDatabase accountDatabase;

    /**
     * Constructs a {@code LoginManager} and initializes the account database.
     */
    public LoginManager() {
        accountDatabase = new AccountDatabase();
    }

    /**
     * Manages the login process for users. It retrieves user credentials,
     * checks if the account exists, validates the password, and directs
     * users to their respective application managers based on their roles.
     */
    public void logIn() {
        String userId = LoginPage.getUserId(); // Get user ID from login page
        Account account = (Account) accountDatabase.searchItem(userId);
        AppManager appManager;

        // Check if account exists
        if (account == null) {
            LoginPage.displayError("User not found!");
        } else {
            // Check if password is correct
            String password = LoginPage.getPassword();
            if (account.checkPassword(password)) {
                System.out.println(account.getrole().toString());
                switch (account.getrole()) {
                    case PAT:
                        appManager = new PatientAppMgr(); // Patient application manager
                        break;
                    case DOC:
                        appManager = new DoctorAppMgr(); // Doctor application manager
                        break;
                    case PHA:
                        appManager = new PharmaAppMgr(); // Pharmacist application manager
                        break;
                    case ADM:
                        appManager = new AdminAppMgr(); // Administrator application manager
                        break;
                    default:
                        appManager = null; // Should never reach here!
                        LoginPage.displayError("Something went wrong!!! Invalid Role!!!");
                        break;
                }
                ClearOutput.clearOutput();
                LoginPage.displaySuccess("Welcome to Hospital X System " + account.getName());
                appManager.logIn(account); // Direct to the respective application manager
            } else {
                LoginPage.displayError("Wrong Password!"); // Incorrect password handling
            }
        }
    }
}