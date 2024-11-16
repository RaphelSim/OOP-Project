package UI.MedicalRecordPages;

import Common.ClearOutput;
import Common.UserInterface;
import Controllers.MRManagers.PatientMRM;

/**
 * The {@code PatientViewMedicalRecordPage} class provides functionality for
 * displaying a patient's medical record. It extends {@link UserInterface}
 * and interacts with the {@link PatientMRM} manager to retrieve and display
 * medical records.
 */
public class PatientViewMedicalRecordPage extends UserInterface {
    private PatientMRM patientMRM; // Manager for handling patient medical records

    /**
     * Constructs a {@code PatientViewMedicalRecordPage} with the specified
     * {@link PatientMRM} instance.
     *
     * @param patientMRM the {@link PatientMRM} manager used to access medical records
     */
    public PatientViewMedicalRecordPage(PatientMRM patientMRM) {
        this.patientMRM = patientMRM; // Initialize the patient record manager
    }

    /**
     * Displays the current medical record of the patient.
     * If the record does not exist, an error message is displayed.
     */
    public void displayMedicalRecord() {
        ClearOutput.clearOutput(); // Clear previous output
        System.out.println("Current Medical Record");
        System.out.println("-------------------------");
        
        // Attempt to view the medical record
        if (!patientMRM.viewRecord())
            displayError("Medical Record does not exist"); // Display error if record is not found
        
        pauseAndView(); // Pause before returning to allow user to view the output
    }
}