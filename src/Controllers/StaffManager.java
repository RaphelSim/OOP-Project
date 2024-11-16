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

/**
 * The {@code StaffManager} class provides functionalities for managing staff accounts.
 * It allows adding, removing, editing, and displaying staff information based on various filters.
 */
public class StaffManager {
    private AccountDatabase accountDatabase;

    /**
     * Constructs a {@code StaffManager} with the specified account database.
     *
     * @param accountDatabase the {@link AccountDatabase} used to manage staff accounts
     */
    public StaffManager(AccountDatabase accountDatabase) {
        this.accountDatabase = accountDatabase;
    }

    /**
     * Adds a new staff member to the database.
     *
     * @param account the {@link Account} object representing the staff member to be added
     * @return true if the staff member was added successfully; false otherwise
     * @throws Exception if the role of the account is PAT or ADM, as they cannot be added
     */
    public boolean addStaff(Account account) throws Exception {
        Random random = new Random();
        String tempId;
        int number;
        String fiveDigitString;

        if (account.getrole() == Role.PAT)
            throw new Exception("You are not allowed to add patients");
        if (account.getrole() == Role.ADM)
            throw new Exception("You are not allowed to add admins");

        do {
            number = random.nextInt(100000); // Generates a random number between 0 and 99999
            fiveDigitString = String.format("%05d", number); // Formats number with leading zeros
            tempId = account.getrole().toString() + fiveDigitString;

            if (accountDatabase.searchItem(tempId) == null) { // Check if this ID exists
                account.setId(tempId);
                accountDatabase.addItem(account);

                if (account.getrole() == Role.DOC) {
                    DoctorSchedule.newDoctor(account.getid()); // Add doctor schedule file if adding a doctor
                }

                return true; // Staff member added successfully
            }

        } while (true); // Run until an ID is found
    }

    /**
     * Removes a staff member from the database by user ID.
     *
     * @param userId the unique identifier of the user to be removed
     * @return true if the staff member was removed successfully; false if not found
     * @throws Exception if trying to remove patients or admins
     */
    public boolean removeStaff(String userId) throws Exception {
        if (accountDatabase.searchItem(userId) == null)
            return false;

        Account account = (Account) accountDatabase.searchItem(userId);

        if (account.getrole() == Role.PAT)
            throw new Exception("You are not allowed to remove patients");
        if (account.getrole() == Role.ADM)
            throw new Exception("You are not allowed to remove admins");

        if (account.getrole() == Role.DOC)
            DoctorSchedule.deleteDoctorFile(userId); // Delete doctor schedule if account is doctor

        accountDatabase.removeItem(userId);
        return true; // Staff member removed successfully
    }

    /**
     * Retrieves user information by user ID.
     *
     * @param userId the unique identifier of the user whose information is to be retrieved
     * @return the {@link Account} object representing the user, or null if not found
     */
    public Account getUserInfo(String userId) {
        return (Account) accountDatabase.searchItem(userId);
    }

    /**
     * Edits the name of a staff member by user ID.
     *
     * @param userId the unique identifier of the user whose name is to be edited
     * @param name   the new name for the user
     * @return true if the name was updated successfully; false if not found
     */
    public boolean editName(String userId, String name) {
        Account account = (Account) accountDatabase.searchItem(userId);
        if (account == null)
            return false;
        account.setName(name);
        return true; // Name updated successfully
    }

    /**
     * Edits the password of a staff member by user ID.
     *
     * @param userId   the unique identifier of the user whose password is to be edited
     * @param password the new password for the user
     * @return true if the password was updated successfully; false if not found
     */
    public boolean editPassword(String userId, String password) {
        Account account = (Account) accountDatabase.searchItem(userId);
        if (account == null)
            return false;
        account.setPassword(password);
        return true; // Password updated successfully
    }

    /**
     * Edits the gender of a staff member by user ID.
     *
     * @param userId the unique identifier of the user whose gender is to be edited
     * @param gender the new gender for the user
     * @return true if the gender was updated successfully; false if not found
     */
    public boolean editGender(String userId, Gender gender) {
        Account account = (Account) accountDatabase.searchItem(userId);
        if (account == null)
            return false;
        account.setGender(gender);
        return true; // Gender updated successfully
    }

    /**
     * Edits the age of a staff member by user ID.
     *
     * @param userId the unique identifier of the user whose age is to be edited
     * @param age    the new age for the user
     * @return true if the age was updated successfully; false if not found
     */
    public boolean editAge(String userId, int age) {
        Account account = (Account) accountDatabase.searchItem(userId);
        if (account == null)
            return false;
        account.setAge(age);
        return true; // Age updated successfully
    }

    /**
     * Displays all staff members filtered by specified parameter and value.
     *
     * @param filter parameter by which to filter staff members (e.g., ROLE or GENDER)
     * @param param value used for filtering based on specified parameter
     */
    public void displayStaffs(FilterParam filter, String param) {
        if (filter == FilterParam.ROLE) {
            for (DatabaseItems item : accountDatabase.getRecords()) {
                Account account = (Account) item;
                if (account.getrole().equals(Role.fromString(param))) {
                    account.printItem(); // Print staff info matching role filter
                }
            }
        } else if (filter == FilterParam.GENDER) {
            for (DatabaseItems item : accountDatabase.getRecords()) {
                Account account = (Account) item;
                if (account.getGender().equals(Gender.fromString(param)) && 
                    account.getrole() != Role.PAT) {
                    account.printItem(); // Print staff info matching gender filter, excluding patients
                }
            }
        } else {
            UserInterface.displayError("Invalid filter, unable to display");
        }
    }

    /**
     * Displays all staff members filtered by age range.
     *
     * @param filter      parameter by which to filter staff members, must be AGE
     * @param lowerBound  lower bound of age range for filtering
     * @param upperBound  upper bound of age range for filtering
     */
    public void displayStaffs(FilterParam filter, int lowerBound, int upperBound) {
        if (filter == FilterParam.AGE) {
            for (DatabaseItems item : accountDatabase.getRecords()) {
                Account account = (Account) item;
                if (account.getAge() >= lowerBound && 
                    account.getAge() <= upperBound) {
                    account.printItem(); // Print staff info matching age range filter
                }
            }
        } else {
            UserInterface.displayError("Invalid filter, unable to display");
        }
    }

    /**
     * Displays all staff members who are doctors or pharmacists.
     */
    public void displayAllStaff() {
        for (DatabaseItems item : accountDatabase.getRecords()) {
            Account account = (Account) item;
            if (account.getrole() == Role.DOC || 
                account.getrole() == Role.PHA) {
                account.printItem(); // Print all doctors and pharmacists' info
            }
        }
    }
}