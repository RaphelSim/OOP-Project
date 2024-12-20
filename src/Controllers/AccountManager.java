package Controllers;

import DatabaseItems.Account;
import DatabaseItems.MedicalRecord;
import Databases.AccountDatabase;
import Databases.MedicalRecordDatabase;

public class AccountManager {
    private Account account;
    private AccountDatabase accountDatabase;
    private MedicalRecordDatabase medicalRecordDatabase;

    public AccountManager(Account account, AccountDatabase accountDatabase, MedicalRecordDatabase medicalRecordDatabase) {
        this.account = account;
        this.accountDatabase = accountDatabase;
        this.medicalRecordDatabase = medicalRecordDatabase;
    }

    public boolean changePassword(String newPassword) {
        if (newPassword == null || newPassword.isEmpty()) {
            return false; // New password invalid
        }

        Account newAccount = (Account) accountDatabase.searchItem(account.getid());
        newAccount.setPassword(newPassword);
        return true; // Indicate success
    }

    public boolean changeEmail(String newEmail) {
        if (newEmail == null || newEmail.isEmpty()) {
            return false; // New email invalid
        }
        MedicalRecord medicalRecord = (MedicalRecord) medicalRecordDatabase.searchItem(account.getid());
        if (medicalRecord == null) return false; // MedicalRecord not found

        medicalRecord.setEmail(newEmail); // Update medical record
        return true;
    }

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
