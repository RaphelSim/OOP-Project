package Controllers;

import Common.AppManager;
import Common.ClearOutput;
import Common.CustomTimer;
import Controllers.AppManagers.AdminAppMgr;
import Controllers.AppManagers.DoctorAppMgr;
// import Common.Role;
import Controllers.AppManagers.PatientAppMgr;
import Controllers.AppManagers.PharmaAppMgr;
import DatabaseItems.Account;
import Databases.AccountDatabase;
import UI.LoginPage;

public class LoginManager {
    AccountDatabase accountDatabase;

    public LoginManager(){
       accountDatabase = new AccountDatabase();
    }
    
    public void logIn(){
        String userId = LoginPage.getUserId();
        Account account = (Account) accountDatabase.searchItem(userId);
        AppManager appManager;

        //check if account exist
        if(account == null){
            LoginPage.displayError("User not found!");
            CustomTimer.pause(2000);
        }
        //check if password is correct
        else{
            String password = LoginPage.getPassword();
            if(account.checkPassword(password)){
                System.out.println(account.getrole().toString());
                switch (account.getrole()) {
                    case PAT:
                        appManager = new PatientAppMgr();
                        break;
                    case DOC:   // Added parameter to pass in doctor account
                        appManager = new DoctorAppMgr(account);
                        break;
                    case PHA:
                        appManager = new PharmaAppMgr();
                        break;
                    case ADM:
                        appManager = new AdminAppMgr();
                        break;
                    default:
                        appManager = null; //should never reach here!
                        LoginPage.displayError("Something went wrong!!! Invalid Role!!!");
                        break;
                }
                ClearOutput.clearOutput();
                LoginPage.displaySuccess("Welcome to Hospital X System "+ account.getName());
                appManager.logIn(account);
            }
            else{
                LoginPage.displayError("Wrong Password!");
            }
        }
    }
}
