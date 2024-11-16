package Controllers.MRManagers;

import Common.MedicalRecordsManager;
import Databases.MedicalRecordDatabase;

/**
 * The {@code PatientMRM} class extends {@link MedicalRecordsManager} to provide
 * functionalities specific to managing a patient's medical records.
 * It allows patients to view their own medical records.
 */
public class PatientMRM extends MedicalRecordsManager {
    protected String userId;

    /**
     * Constructs a {@code PatientMRM} with the specified medical record database
     * and user ID.
     *
     * @param medicalRecordDatabase the {@link MedicalRecordDatabase} used to manage medical records
     * @param userId                the unique identifier of the patient whose records are managed
     */
    public PatientMRM(MedicalRecordDatabase medicalRecordDatabase, String userId) {
        super(medicalRecordDatabase);
        this.userId = userId;
    }

    /**
     * Views the medical record of the patient associated with this instance.
     *
     * @return true if the record was successfully viewed; false otherwise
     */
    public boolean viewRecord() {
        return super.viewRecord(userId); // Delegate to the superclass method
    }
}