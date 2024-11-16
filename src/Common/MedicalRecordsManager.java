package Common;

import DatabaseItems.MedicalRecord;
import Databases.MedicalRecordDatabase;

/**
 * The {@code MedicalRecordsManager} class provides functionalities for managing
 * medical records. It allows retrieval and viewing of medical records, treatments,
 * and diagnoses associated with a specific user.
 */
public class MedicalRecordsManager {
    protected MedicalRecordDatabase medicalRecordDatabase;

    /**
     * Constructs a {@code MedicalRecordsManager} with the specified medical record database.
     *
     * @param medicalRecordDatabase the {@link MedicalRecordDatabase} used to manage medical records
     */
    public MedicalRecordsManager(MedicalRecordDatabase medicalRecordDatabase) {
        this.medicalRecordDatabase = medicalRecordDatabase;
    }

    /**
     * Retrieves a medical record by user ID.
     *
     * @param userId the unique identifier of the user whose medical record is to be retrieved
     * @return the {@link MedicalRecord} associated with the user ID, or null if not found
     */
    public MedicalRecord getRecord(String userId) {
        MedicalRecord record = (MedicalRecord) medicalRecordDatabase.searchItem(userId);
        return record;
    }

    /**
     * Views a medical record by user ID and prints its details.
     *
     * @param userId the unique identifier of the user whose medical record is to be viewed
     * @return true if the record was found and printed; false if not found
     */
    public boolean viewRecord(String userId) {
        MedicalRecord record = (MedicalRecord) medicalRecordDatabase.searchItem(userId);
        if (record == null)
            return false;
        record.printItem();
        return true;
    }

    /**
     * Views treatments associated with a user's medical record.
     *
     * @param userId the unique identifier of the user whose treatments are to be viewed
     * @return true if treatments were found and printed; false if not found
     */
    public boolean viewTreatments(String userId) {
        MedicalRecord record = (MedicalRecord) medicalRecordDatabase.searchItem(userId);
        if (record == null)
            return false;
        record.printTreatments();
        return true;
    }

    /**
     * Views diagnoses associated with a user's medical record.
     *
     * @param userId the unique identifier of the user whose diagnoses are to be viewed
     * @return true if diagnoses were found and printed; false if not found
     */
    public boolean viewDiagnoses(String userId) {
        MedicalRecord record = (MedicalRecord) medicalRecordDatabase.searchItem(userId);
        if (record == null)
            return false;
        record.printDiagnoses();
        return true;
    }
}