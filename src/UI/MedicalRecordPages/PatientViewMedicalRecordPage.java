package UI.MedicalRecordPages;

import Common.ClearOutput;
import Common.UserInterface;
import Controllers.MRManagers.PatientMRM;

public class PatientViewMedicalRecordPage extends UserInterface {
    private PatientMRM patientMRM;

    public PatientViewMedicalRecordPage(PatientMRM patientMRM) {
        this.patientMRM = patientMRM;
    }

    public void displayMedicalRecord() {
        ClearOutput.clearOutput();
        System.out.println("Current Medical Record");
        System.out.println("-------------------------");
        if (!patientMRM.viewRecord())
            displayError("Medical Record does not exist");
        pauseAndView();
    }
}
