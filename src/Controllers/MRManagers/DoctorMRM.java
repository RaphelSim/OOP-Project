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

/**
 * The {@code DoctorMRM} class extends {@link MedicalRecordsManager} to provide
 * functionalities specific to managing medical records for patients by doctors.
 * It allows doctors to view, edit, and update patient records.
 */
public class DoctorMRM extends MedicalRecordsManager {
    private List<MedicalRecord> allRecords = new ArrayList<MedicalRecord>();
    private AccountDatabase accountDatabase;

    /**
     * Constructs a {@code DoctorMRM} with the specified medical record database
     * and account database.
     *
     * @param medicalRecordDatabase the {@link MedicalRecordDatabase} used to manage medical records
     * @param accountDatabase        the {@link AccountDatabase} used to manage accounts
     */
    public DoctorMRM(MedicalRecordDatabase medicalRecordDatabase, AccountDatabase accountDatabase) {
        super(medicalRecordDatabase);
        this.accountDatabase = accountDatabase;
        retrieveAllRecords(); // Retrieve all patient records upon initialization
    }

    /**
     * Retrieves all patient medical records from the account database.
     */
    private void retrieveAllRecords() {
        for (DatabaseItems item : accountDatabase.getRecords()) {
            Account account = (Account) item;
            if (account.getrole() == Role.PAT) { // Check if the account role is PAT (Patient)
                MedicalRecord record = getRecord(account.getid());
                if (record != null)
                    allRecords.add(record); // Add the record to the list if found
            }
        }
    }

    /**
     * Views all patient medical records managed by the doctor.
     *
     * @return true if records were successfully viewed; false if no records exist
     */
    public boolean viewAllRecords() {
        if (allRecords == null || allRecords.isEmpty())
            return false; // No records to display
        for (MedicalRecord record : allRecords)
            record.printItem(); // Print each medical record
        return true; // Records viewed successfully
    }

    /**
     * Sets the blood type for a specific patient record.
     *
     * @param id       the unique identifier of the patient whose blood type is to be set
     * @param bloodType the new blood type to set for the patient
     * @return true if the blood type was successfully set; false if not found
     */
    public boolean setBloodType(String id, BloodType bloodType) {
        MedicalRecord record = getRecord(id);
        if (record == null)
            return false; // Record not found
        record.setBloodType(bloodType); // Set the blood type
        return true; // Blood type set successfully
    }

    /**
     * Adds a diagnosis to a specific patient record.
     *
     * @param id       the unique identifier of the patient whose diagnosis is to be added
     * @param diagnose the diagnosis to add
     * @return true if the diagnosis was successfully added; false if not found or invalid input
     */
    public boolean addDiagnoses(String id, String diagnose) {
        MedicalRecord record = getRecord(id);
        if (record == null || diagnose == null || diagnose.isEmpty())
            return false; // Record not found or invalid diagnosis input
        List<String> diagnoses = record.getDiagnoses();
        diagnoses.add(diagnose); // Add diagnosis to list
        record.setDiagnoses(diagnoses); // Update diagnoses in record
        return true; // Diagnosis added successfully
    }

    /**
     * Adds a treatment to a specific patient record.
     *
     * @param id       the unique identifier of the patient whose treatment is to be added
     * @param treatment the treatment to add
     * @return true if the treatment was successfully added; false if not found or invalid input
     */
    public boolean addTreatments(String id, String treatment) {
        MedicalRecord record = getRecord(id);
        if (record == null || treatment == null || treatment.isEmpty())
            return false; // Record not found or invalid treatment input
        List<String> treatments = record.getTreatments();
        treatments.add(treatment); // Add treatment to list
        record.setTreatments(treatments); // Update treatments in record
        return true; // Treatment added successfully
    }

    /**
     * Edits an existing diagnosis in a specific patient record.
     *
     * @param id       the unique identifier of the patient whose diagnosis is to be edited
     * @param index    the index of the diagnosis to edit (1-based index)
     * @param diagnose the new diagnosis value
     * @return true if the diagnosis was successfully edited; false if not found or invalid input
     */
    public boolean editDiagnoses(String id, int index, String diagnose) {
        MedicalRecord record = getRecord(id);
        List<String> diagnoses = record.getDiagnoses();
        if (index - 1 >= diagnoses.size() || diagnose == null || diagnose.isEmpty())
            return false; // Invalid index or diagnosis input

        diagnoses.set(index - 1, diagnose); // Update diagnosis at specified index
        return true; // Diagnosis edited successfully
    }

    /**
     * Edits an existing treatment in a specific patient record.
     *
     * @param id       the unique identifier of the patient whose treatment is to be edited
     * @param index    the index of the treatment to edit (1-based index)
     * @param treatment the new treatment value
     * @return true if the treatment was successfully edited; false if not found or invalid input
     */
    public boolean editTreatments(String id, int index, String treatment) {
        MedicalRecord record = getRecord(id);
        List<String> treatments = record.getTreatments();
        if (index - 1 >= treatments.size() || treatment == null || treatment.isEmpty())
            return false; // Invalid index or treatment input

        treatments.set(index - 1, treatment); // Update treatment at specified index
        return true; // Treatment edited successfully
    }
}