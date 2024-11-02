package Databases;

import Common.Database;
import Common.DatabaseItems;
import DatabaseItems.MedicalRecord;

public class MedicalRecordDatabase extends Database {

    public MedicalRecordDatabase() {
        setHeaderFormat("id,name,dob,gender,email,phone,bloodtype,diagnoses,treatments");
        setcsvPath("Database/MedicalRecords.csv");
        extractFromCSV();
    }

    public MedicalRecordDatabase(String csvpath) {
        setHeaderFormat("id,name,dob,gender,email,phone,bloodtype,diagnoses,treatments");
        setcsvPath(csvpath);
        extractFromCSV();
    }

    // id,name,dob,gender,email,phone,bloodtype,diagnoses,treatments
    public DatabaseItems createDatabaseItem(String[] values){
        return new MedicalRecord(values);
    }

    public DatabaseItems searchItem(String id){
        for (DatabaseItems item : records) {
            MedicalRecord medicalRecord = (MedicalRecord) item;
            if (medicalRecord.getId().equals(id)) {
                return medicalRecord; // Return the found item
            }
        }
        return null;
    }

    public boolean removeItem(String userid) {
        boolean accountRemoved = records.removeIf(user -> {
            MedicalRecord account = (MedicalRecord) user;
            return account.getId().equals(userid);
        });

        if (accountRemoved) {
            return true;
        } else {
            return false;
        }
    }

    public void printItems() {
        printItems("Medical Records Database");
    }
}
