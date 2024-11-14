package Controllers.MRManagers;

import java.util.ArrayList;
import java.util.List;

import Common.BloodType;
import Common.DatabaseItems;
import Common.MedicalRecordsManager;
import Common.Role;
import DatabaseItems.Account;
import DatabaseItems.MedicalRecord;
import Databases.AccountDatabase;
import Databases.MedicalRecordDatabase;

public class DoctorMRM extends MedicalRecordsManager {
    private List<MedicalRecord> allRecords = new ArrayList<MedicalRecord>();
    private AccountDatabase accountDatabase;

    public DoctorMRM(MedicalRecordDatabase medicalRecordDatabase, AccountDatabase accountDatabase) {
        super(medicalRecordDatabase);
        this.accountDatabase = accountDatabase;
        retrieveAllRecords();
    }

    private void retrieveAllRecords() {
        for (DatabaseItems item : accountDatabase.getRecords()) {
            Account account = (Account) item;
            if (account.getrole() == Role.PAT) {
                MedicalRecord record = getRecord(account.getid());
                if (record != null)
                    allRecords.add(record);
            }
        }
    }

    public boolean viewAllRecords() {
        if (allRecords == null || allRecords.isEmpty())
            return false;
        for (MedicalRecord record : allRecords)
            record.printItem();
        return true;
    }

    public boolean setBloodType(String id, BloodType bloodType) {
        MedicalRecord record = getRecord(id);
        if (record == null)
            return false;
        record.setBloodType(bloodType);
        return true;
    }

    public boolean addDiagnoses(String id, String diagnose) {
        MedicalRecord record = getRecord(id);
        if (record == null || diagnose == null || diagnose.isEmpty())
            return false;
        List<String> diagnoses = record.getDiagnoses();
        diagnoses.add(diagnose);
        record.setDiagnoses(diagnoses);
        return true;
    }

    public boolean addTreatments(String id, String treatment) {
        MedicalRecord record = getRecord(id);
        if (record == null || treatment == null || treatment.isEmpty())
            return false;
        List<String> treatments = record.getTreatments();
        treatments.add(treatment);
        record.setTreatments(treatments);
        return true;
    }

    public boolean editDiagnoses(String id, int index, String diagnose) {
        MedicalRecord record = getRecord(id);
        List<String> diagnoses = record.getDiagnoses();
        if (index - 1 > diagnose.length() || diagnose == null || diagnose.isEmpty())
            return false;
        diagnoses.set(index - 1, diagnose);
        return true;
    }

    public boolean editTreatments(String id, int index, String treatment) {
        MedicalRecord record = getRecord(id);
        List<String> treatments = record.getTreatments();
        if (index - 1 > treatment.length() || treatment == null || treatment.isEmpty())
            return false;
        treatments.set(index - 1, treatment);
        return true;
    }
}
