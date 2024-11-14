package Common;

import DatabaseItems.MedicalRecord;
import Databases.MedicalRecordDatabase;

public class MedicalRecordsManager {
    protected MedicalRecordDatabase medicalRecordDatabase;

    public MedicalRecordsManager(MedicalRecordDatabase medicalRecordDatabase) {
        this.medicalRecordDatabase = medicalRecordDatabase;
    }

    public MedicalRecord getRecord(String userId) {
        MedicalRecord record = (MedicalRecord) medicalRecordDatabase.searchItem(userId);
        return record;
    }

    public boolean viewRecord(String userId) {
        MedicalRecord record = (MedicalRecord) medicalRecordDatabase.searchItem(userId);
        if (record == null)
            return false;
        record.printItem();
        return true;
    }

    public boolean viewTreatments(String userId) {
        MedicalRecord record = (MedicalRecord) medicalRecordDatabase.searchItem(userId);
        if (record == null)
            return false;
        record.printTreatments();
        return true;
    }

    public boolean viewDiagnoses(String userId) {
        MedicalRecord record = (MedicalRecord) medicalRecordDatabase.searchItem(userId);
        if (record == null)
            return false;
        record.printDiagnoses();
        return true;
    }
}
