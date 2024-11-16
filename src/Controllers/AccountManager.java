package Controllers;

import DatabaseItems.Account;
import DatabaseItems.MedicalRecord;
import Databases.AccountDatabase;
import Databases.MedicalRecordDatabase;

/**
 * The {@code AccountManager} class provides functionalities for managing
 * user account details, including changing passwords, emails, and phone numbers.
 */
public class AccountManager {
    private Account account;
    private AccountDatabase accountDatabase;
    private MedicalRecordDatabase medicalRecordDatabase;

    /**
     * Constructs an {@code AccountManager} with the specified account and databases.
     *
     * @param account the {@link Account} associated with this manager
     * @param accountDatabase the {@link AccountDatabase} for managing accounts
     * @param medicalRecordDatabase the {@link MedicalRecordDatabase} for managing medical records
     */
    public AccountManager(Account account, AccountDatabase accountDatabase, MedicalRecordDatabase medicalRecordDatabase) {
        this.account = account;
        this.accountDatabase = accountDatabase;
        this.medicalRecordDatabase = medicalRecordDatabase;
    }

    /**
     * Changes the password of the associated account.
     *
     * @param newPassword the new password to set for the account
     * @return true if the password was successfully changed; false if the new password is invalid
     */
    public boolean changePassword(String newPassword) {
        if (newPassword == null || newPassword.isEmpty()) {
            return false; // New password invalid
        }

        Account newAccount = (Account) accountDatabase.searchItem(account.getid());
        newAccount.setPassword(newPassword);
        return true; // Indicate success
    }

    /**
     * Changes the email address associated with the user's medical record.
     *
     * @param newEmail the new email address to set
     * @return true if the email was successfully changed; false if the new email is invalid or medical record not found
     */
    public boolean changeEmail(String newEmail) {
        if (newEmail == null || newEmail.isEmpty()) {
            return false; // New email invalid
        }
        MedicalRecord medicalRecord = (MedicalRecord) medicalRecordDatabase.searchItem(account.getid());
        if (medicalRecord == null) return false; // MedicalRecord not found

        medicalRecord.setEmail(newEmail); // Update medical record
        return true;
    }

    /**
     * Changes the phone number associated with the user's medical record.
     *
     * @param newPhone the new phone number to set
     * @return true if the phone number was successfully changed; false if the new phone number is invalid or medical record not found
     */
    public boolean changePhone(String newPhone) {
        if (newPhone == null || newPhone.isEmpty()) {
            return false; // New phone invalid
        }
        MedicalRecord medicalRecord = (MedicalRecord) medicalRecordDatabase.searchItem(account.getid());
        if (medicalRecord == null) return false; // MedicalRecord not found

        medicalRecord.setPhone(newPhone); // Update medical record
        return true;
    }
}