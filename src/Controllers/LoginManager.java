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
            System.out.println("User not found!");
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
                        System.out.println("Something went wrong!!! Invalid Role!!!");
                        break;
                }
                ClearOutput.clearOutput();
                System.out.println("Welcome to Hospital X System "+ account.getName());
                CustomTimer.pause(1000);
                appManager.logIn(account);
            }
            else{
                System.out.println("Wrong Password!");
            }
        }
    }
}
