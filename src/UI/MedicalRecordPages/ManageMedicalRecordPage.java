package UI.MedicalRecordPages;

import Common.BloodType;
import Common.ClearOutput;
import Common.UserInterface;
import Controllers.MRManagers.DoctorMRM;

/**
 * The {@code ManageMedicalRecordPage} class provides functionality for managing
 * a patient's medical records. It extends {@link UserInterface} and interacts
 * with the {@link DoctorMRM} manager to perform operations such as viewing,
 * editing, and updating medical records.
 */
public class ManageMedicalRecordPage extends UserInterface {
    private DoctorMRM doctorMRM; // Manager for handling doctor-specific medical records

    /**
     * Constructs a {@code ManageMedicalRecordPage} with the specified
     * {@link DoctorMRM} instance.
     *
     * @param doctorMRM the {@link DoctorMRM} manager used to access and manage medical records
     */
    public ManageMedicalRecordPage(DoctorMRM doctorMRM) {
        this.doctorMRM = doctorMRM; // Initialize the doctor record manager
    }

    /**
     * Displays options for managing medical records and handles user input.
     */
    public void displayOptions() {
        boolean quit = false;
        while (!quit) {
            ClearOutput.clearOutput();
            System.out.println("Please select an option: ");
            System.out.println("1. View all medical records");
            System.out.println("2. View and Edit a medical record");
            System.out.println("3. Back");
            int choice = getIntInput(4); // Get user input with a maximum option of 4
            switch (choice) {
                case 1 -> viewAllRecords(); // View all medical records
                case 2 -> editRecord(); // Edit a specific medical record
                case 3 -> {
                    quit = true; // Exit the loop
                    continue; // Continue to next iteration
                }
                default -> displayError("Invalid option selected."); // Handle invalid input
            }
        }
    }

    /**
     * Displays all medical records and handles errors if no records exist.
     */
    private void viewAllRecords() {
        ClearOutput.clearOutput();
        System.out.println("List of Medical Records");
        System.out.println("--------------------------");
        if (!doctorMRM.viewAllRecords())
            displayError("No Record found in database"); // Display error if no records are found
        pauseAndView(); // Pause to allow user to view output
    }

    /**
     * Allows the user to edit a specific medical record by patient ID.
     */
    private void editRecord() {
        ClearOutput.clearOutput();
        String id = getValidatedString("Enter the Patient's id to view and edit: "); // Get patient ID

        ClearOutput.clearOutput();
        if (!doctorMRM.viewRecord(id)) { // Attempt to view the record
            displayError("Patient does not exist"); // Handle case where patient does not exist
            return;
        }

        boolean quit = false;
        while (!quit) {
            ClearOutput.clearOutput();
            doctorMRM.viewRecord(id); // Display current record details
            System.out.println();
            System.out.println("Perform an action: ");
            System.out.println("1. Update BloodType");
            System.out.println("2. Update Diagnoses");
            System.out.println("3. Update Treatments");
            System.out.println("4. Back");
            int choice = getIntInput(5); // Get user input with a maximum option of 5
            switch (choice) {
                case 1 -> updateBloodType(id); // Update blood type for the patient
                case 2 -> updateDiagnoses(id); // Update diagnoses for the patient
                case 3 -> updateTreatments(id); // Update treatments for the patient
                case 4 -> {
                    quit = true; // Exit the loop
                    return; 
                }
                default -> displayError("Invalid option selected."); // Handle invalid input
            }
        }
    }

    /**
     * Updates the blood type of a patient by ID.
     *
     * @param id the unique identifier of the patient whose blood type is to be updated
     */
    private void updateBloodType(String id) {
        ClearOutput.clearOutput();
        BloodType type = BloodType.fromString(getValidatedString(
                "Enter the updated Bloodtype [HINT: Key in A+ for A positive blood type]")); // Get new blood type input
        
        if (type == BloodType.NA) { 
            displayError("Invalid BloodType entered"); // Handle invalid blood type input
            return;
        }
        
        doctorMRM.setBloodType(id, type); // Set new blood type in manager
        displaySuccess("Bloodtype updated"); // Display success message
    }

    /**
     * Allows updating of diagnoses associated with a patient's record.
     *
     * @param id the unique identifier of the patient whose diagnoses are to be updated
     */
    private void updateDiagnoses(String id) {
        ClearOutput.clearOutput();
        System.out.println("Perform an action: ");
        System.out.println("1. Edit a diagnosis");
        System.out.println("2. Add a diagnosis");
        System.out.println("3. Back");
        
        int choice = getIntInput(3); // Get user input with a maximum option of 3
        
        switch (choice) {
            case 1:
                editDiagnose(id); // Edit an existing diagnosis
                break;
            case 2:
                if (!doctorMRM.addDiagnoses(id, getValidatedString("Enter new diagnosis"))) 
                    displayError("Failed to add diagnosis"); // Handle failure to add diagnosis
                else 
                    displaySuccess("New diagnosis added"); // Display success message for adding diagnosis
                break;
            default:
                break; 
        }
    }

    /**
     * Allows editing of a specific diagnosis associated with a patient's record.
     *
     * @param id the unique identifier of the patient whose diagnosis is to be edited
     */
    private void editDiagnose(String id) {
        ClearOutput.clearOutput();
        doctorMRM.viewDiagnoses(id); // Display current diagnoses for the patient
        
        System.out.println();
        System.out.println("Enter the index/number of the diagnosis to edit: ");
        
        int index = getIntInput(-1); // Get index input from user
        
        System.out.println();
        
        String input = getValidatedString("Enter the updated diagnosis: "); 
        if (!doctorMRM.editDiagnoses(id, index, input)) 
            displayError("Failed to edit diagnosis"); // Handle failure to edit diagnosis
        else 
            displaySuccess("Diagnosis updated"); // Display success message for editing diagnosis
    }

    /**
     * Allows updating of treatments associated with a patient's record.
     *
     * @param id the unique identifier of the patient whose treatments are to be updated
     */
    private void updateTreatments(String id) {
        ClearOutput.clearOutput();
        
        System.out.println("Perform an action: ");
        System.out.println("1. Edit a treatment");
        System.out.println("2. Add a treatment");
        System.out.println("3. Back");
        
        int choice = getIntInput(3); // Get user input with a maximum option of 3
        
        switch (choice) {
            case 1:
                editTreatment(id); // Edit an existing treatment
                break;
            case 2:
                if (!doctorMRM.addTreatments(id, getValidatedString("Enter new treatment"))) 
                    displayError("Failed to add treatment"); // Handle failure to add treatment
                else 
                    displaySuccess("New treatment added"); // Display success message for adding treatment
                break;
            default:
                break; 
        }
    }

    /**
     * Allows editing of a specific treatment associated with a patient's record.
     *
     * @param id the unique identifier of the patient whose treatment is to be edited
     */
    private void editTreatment(String id) {
        ClearOutput.clearOutput();
        
        doctorMRM.viewTreatments(id); // Display current treatments for the patient
        
        System.out.println();
        
        System.out.println("Enter the index/number of the treatment to edit: ");
        
        int index = getIntInput(-1); // Get index input from user
        
        System.out.println();
        
        String input = getValidatedString("Enter the updated treatment: "); 
       
       if (!doctorMRM.editTreatments(id, index, input)) 
           displayError("Failed to edit treatment"); // Handle failure to edit treatment
       else 
           displaySuccess("Treatment updated"); // Display success message for editing treatment
   }
}