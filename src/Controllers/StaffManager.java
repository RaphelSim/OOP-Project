package Controllers;

import Common.DatabaseItems;
import Common.FilterParam;
import Common.Gender;
import Common.Role;
import Common.UserInterface;
import DatabaseItems.Account;
import Databases.AccountDatabase;
import Databases.DoctorSchedule;

import java.util.Random;

public class StaffManager {
    private AccountDatabase accountDatabase;

    public StaffManager(AccountDatabase accountDatabase){
        this.accountDatabase = accountDatabase;
    }

    public boolean addStaff(Account account) throws Exception{
        Random random = new Random();
        String tempId;
        int number; 
        String fiveDigitString; 

        if(account.getrole() == Role.PAT) throw new Exception("You are not allowed to add patients");
        if(account.getrole() == Role.ADM) throw new Exception("You are not allowed to add admins");

        do{
            number = random.nextInt(100000); // Generates a random number between 0 and 99999
            fiveDigitString = String.format("%05d", number); // Formats number with leading zeros
            tempId = account.getrole().toString()+ fiveDigitString;

            if(accountDatabase.searchItem(tempId) == null) //check if this id exists
            {
                account.setId(tempId);
                accountDatabase.addItem(account);

                if(account.getrole() == Role.DOC){
                    DoctorSchedule.newDoctor(account.getid()); // add doctor schedule file if adding a doctor
                }

                return true;
            }

        }while(true); //run until an id is found
    }

    public boolean removeStaff(String userId) throws Exception{
        if(accountDatabase.searchItem(userId)==null) return false;

        Account account = (Account) accountDatabase.searchItem(userId);

        if(account.getrole() == Role.PAT) throw new Exception("You are not allowed to remove patients");
        if(account.getrole() == Role.ADM) throw new Exception("You are not allowed to remove admins");
        
        if(account.getrole() == Role.DOC) DoctorSchedule.deleteDoctorFile(userId); //delete doctor schedule if account is doctor
        accountDatabase.removeItem(userId);

        return true;
    }

    public Account getUserInfo(String userId){
        return (Account) accountDatabase.searchItem(userId);
    }

    public boolean editName(String userId,String name){
        Account account = (Account) accountDatabase.searchItem(userId);
        if(account == null) return false;
        account.setName(name);
        return true;
    }

    public boolean editPassword(String userId, String password) {
        Account account = (Account) accountDatabase.searchItem(userId);
        if (account == null) return false;
        account.setPassword(password);
        return true;
    }

    public boolean editGender(String userId, Gender gender) {
        Account account = (Account) accountDatabase.searchItem(userId);
        if (account == null) return false;
        account.setGender(gender);
        return true;
    }

    public boolean editAge(String userId, int age) {
        Account account = (Account) accountDatabase.searchItem(userId);
        if (account == null) return false;
        account.setAge(age);
        return true;
    }

    public void displayStaffs(FilterParam filter, String param){
        if(filter == FilterParam.ROLE){
                for (DatabaseItems item : accountDatabase.getRecords()) {
                Account account = (Account) item;
                if (account.getrole().equals(Role.fromString(param))) {
                    account.printItem();
                }
            }
        }
        else if(filter == FilterParam.GENDER){
            for (DatabaseItems item : accountDatabase.getRecords()) {
                Account account = (Account) item;
                if (account.getGender().equals(Gender.fromString(param))) {
                    account.printItem();
                }
            }
        }
        else{
            UserInterface.displayError("Invalid filter, unable to display");
        }
    }

    public void displayStaffs(FilterParam filter, int lowerBound, int upperBound){
        if(filter == FilterParam.AGE){
            for (DatabaseItems item : accountDatabase.getRecords()) {
                Account account = (Account) item;
                if (account.getAge()>=lowerBound && account.getAge()<=upperBound) {
                    account.printItem();
                }
            }
        }
        else{
            UserInterface.displayError("Invalid filter, unable to display");
        }
    }

    public void displayAllStaff(){
        for (DatabaseItems item : accountDatabase.getRecords()) {
            Account account = (Account) item;
            if (account.getrole() == Role.DOC || account.getrole() == Role.PHA) {
                account.printItem();
            }
        }
    }

}
