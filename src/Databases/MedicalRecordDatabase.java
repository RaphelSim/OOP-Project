package Databases;

import Common.Database;
import Common.DatabaseItems;
import DatabaseItems.MedicalRecord;

/**
 * The {@code MedicalRecordDatabase} class manages a collection of medical records.
 * It extends {@link Database} and provides methods for creating, searching, 
 * removing, and printing medical records.
 */
public class MedicalRecordDatabase extends Database {

    /**
     * Constructs a {@code MedicalRecordDatabase} with a default CSV path.
     */
    public MedicalRecordDatabase() {
        setHeaderFormat("id,name,dob,gender,email,phone,bloodtype,diagnoses,treatments");
        setcsvPath("Database/MedicalRecords.csv");
        extractFromCSV(); // Load records from the specified CSV file
    }

    /**
     * Constructs a {@code MedicalRecordDatabase} with a specified CSV path.
     *
     * @param csvpath the path to the CSV file containing medical records
     */
    public MedicalRecordDatabase(String csvpath) {
        setHeaderFormat("id,name,dob,gender,email,phone,bloodtype,diagnoses,treatments");
        setcsvPath(csvpath);
        extractFromCSV(); // Load records from the specified CSV file
    }

    /**
     * Creates a new {@link MedicalRecord} instance from an array of values.
     *
     * @param values an array of strings containing medical record data
     * @return a new {@link MedicalRecord} object
     */
    public DatabaseItems createDatabaseItem(String[] values) {
        return new MedicalRecord(values); // Create and return a new medical record
    }

    /**
     * Searches for a medical record by its unique identifier (ID).
     *
     * @param id the unique identifier of the medical record to search for
     * @return the found {@link MedicalRecord} object; null if not found
     */
    public DatabaseItems searchItem(String id) {
        for (DatabaseItems item : records) {
            MedicalRecord medicalRecord = (MedicalRecord) item;
            if (medicalRecord.getId().equals(id)) {
                return medicalRecord; // Return the found item
            }
        }
        return null; // Return null if no matching record is found
    }

    /**
     * Removes a medical record by its unique identifier (ID).
     *
     * @param userid the unique identifier of the medical record to remove
     * @return true if the record was successfully removed; false otherwise
     */
    public boolean removeItem(String userid) {
        boolean accountRemoved = records.removeIf(user -> {
            MedicalRecord account = (MedicalRecord) user;
            return account.getId().equals(userid); // Check if ID matches
        });

        return accountRemoved; // Return true if a record was removed
    }

    /**
     * Prints all items in the medical records database.
     */
    public void printItems() {
        printItems("Medical Records Database"); // Print items with specified header
    }
}