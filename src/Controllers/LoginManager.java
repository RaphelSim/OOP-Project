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
    AccountDatabase accountDatabase = new AccountDatabase();
    
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
                    case DOC:
                        appManager = new DoctorAppMgr();
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
                CustomTimer.pause(1500);
                appManager.logIn(account);
            }
            else{
                System.out.println("Wrong Password!");
                CustomTimer.pause(1500);
            }
        }
    }
}
