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
        account.setPassword(newPassword);
        accountDatabase.removeItem(account.getid()); // Remove old record
        accountDatabase.addItem(account); // Add updated record
        return true; // Indicate success
    }

    public boolean changeEmail(String newEmail) {
        if (newEmail == null || newEmail.isEmpty()) {
            return false; // New email invalid
        }
        MedicalRecord medicalRecord = (MedicalRecord) medicalRecordDatabase.searchItem(account.getid());
        if (medicalRecord == null) return false; // MedicalRecord not found

        medicalRecord.setEmail(newEmail);
        medicalRecordDatabase.removeItem(account.getid());
        medicalRecordDatabase.addItem(medicalRecord); // Update medical record
        return true;
    }

    public boolean changePhone(String newPhone) {
        if (newPhone == null || newPhone.isEmpty()) {
            return false; // New phone invalid
        }
        MedicalRecord medicalRecord = (MedicalRecord) medicalRecordDatabase.searchItem(account.getid());
        if (medicalRecord == null) return false; // MedicalRecord not found

        medicalRecord.setPhone(newPhone);
        medicalRecordDatabase.removeItem(account.getid());
        medicalRecordDatabase.addItem(medicalRecord); // Update medical record
        return true;
    }
}
