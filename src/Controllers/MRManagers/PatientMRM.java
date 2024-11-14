package Controllers.MRManagers;

import Common.MedicalRecordsManager;
import Databases.MedicalRecordDatabase;

public class PatientMRM extends MedicalRecordsManager {
    protected String userId;

    public PatientMRM(MedicalRecordDatabase medicalRecordDatabase, String userId) {
        super(medicalRecordDatabase);
        this.userId = userId;
    }

    public boolean viewRecord() {
        return super.viewRecord(userId);
    }

}
