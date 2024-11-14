package UI.MedicalRecordPages;

import Common.BloodType;
import Common.ClearOutput;
import Common.UserInterface;
import Controllers.MRManagers.DoctorMRM;

public class ManageMedicalRecordPage extends UserInterface {
    private DoctorMRM doctorMRM;

    public ManageMedicalRecordPage(DoctorMRM doctorMRM) {
        this.doctorMRM = doctorMRM;
    }

    public void displayOptions() {
        boolean quit = false;
        while (!quit) {
            ClearOutput.clearOutput();
            System.out.println("Please select an option: ");
            System.out.println("1. View all medical records");
            System.out.println("2. View and Edit a medical record");
            System.out.println("3. Back");
            int choice = getIntInput(4);
            switch (choice) {
                case 1 -> viewAllRecords();
                case 2 -> editRecord();
                case 3 -> {
                    quit = true;
                    continue;
                }
                default -> displayError("Invalid option selected.");
            }
        }
    }

    private void viewAllRecords() {
        ClearOutput.clearOutput();
        System.out.println("List of Medical Records");
        System.out.println("--------------------------");
        if (!doctorMRM.viewAllRecords())
            displayError("no Record found in database");
        viewAndGoBack();
    }

    private void editRecord() {
        ClearOutput.clearOutput();
        String id = getValidatedString("Enter the Patient's id to view and edit: ");

        ClearOutput.clearOutput();
        if (!doctorMRM.viewRecord(id)) {
            displayError("Patient does not exist");
            return;
        }

        boolean quit = false;
        while (!quit) {
            ClearOutput.clearOutput();
            doctorMRM.viewRecord(id);
            System.out.println();
            System.out.println("Perform an action: ");
            System.out.println("1. Update BloodType");
            System.out.println("2. Update Diagnoses");
            System.out.println("3. Update Treatments");
            System.out.println("4. Back");
            int choice = getIntInput(5);
            switch (choice) {
                case 1 -> updateBloodType(id);
                case 2 -> updateDiagnoses(id);
                case 3 -> updateTreatments(id);
                case 4 -> {
                    quit = true;
                    return;
                }
                default -> displayError("Invalid option selected.");
            }
        }
    }

    private void updateBloodType(String id) {
        ClearOutput.clearOutput();
        BloodType type = BloodType.fromString(getValidatedString(
                "Enter the updated Bloodtype [HINT: Key in A+ for A positive blood type]"));
        if (type == BloodType.NA) {
            displayError("Invalid BloodType entered");
            return;
        }
        doctorMRM.setBloodType(id, type);
        displaySuccess("Bloodtype updated");
    }

    private void updateDiagnoses(String id) {
        ClearOutput.clearOutput();
        System.out.println("Perform an action: ");
        System.out.println("1. Edit a diagnose");
        System.out.println("2. Add a diagnose");
        System.out.println("3. Back");
        int choice = getIntInput(3);
        switch (choice) {
            case 1:
                editDiagnose(id);
                break;
            case 2:
                if (!doctorMRM.addDiagnoses(id, getValidatedString("Enter new diagnosis")))
                    displayError("Failed to add diagnosis");
                else
                    displaySuccess("New diagnosis added");
                break;
            default:
                break;
        }
    }

    private void editDiagnose(String id) {
        ClearOutput.clearOutput();
        doctorMRM.viewDiagnoses(id);
        System.out.println();
        System.out.println("Enter the index/number of the diagnosis to edit: ");
        int index = getIntInput(-1);
        System.out.println();
        String input = getValidatedString("Enter the updated diagnosis: ");
        if (!doctorMRM.editDiagnoses(id, index, input))
            displayError("Failed to edit diagnosis");
        else
            displaySuccess("Diagnosis updated");
    }

    private void updateTreatments(String id) {
        ClearOutput.clearOutput();
        System.out.println("Perform an action: ");
        System.out.println("1. Edit a treatment");
        System.out.println("2. Add a treatment");
        System.out.println("3. Back");
        int choice = getIntInput(3);
        switch (choice) {
            case 1:
                editTreatment(id);
                break;
            case 2:
                if (!doctorMRM.addTreatments(id, getValidatedString("Enter new treatment")))
                    displayError("Failed to add treatment");
                else
                    displaySuccess("New treatment added");
                break;
            default:
                break;
        }
    }

    private void editTreatment(String id) {
        ClearOutput.clearOutput();
        doctorMRM.viewTreatments(id);
        System.out.println();
        System.out.println("Enter the index/number of the treatment to edit: ");
        int index = getIntInput(-1);
        System.out.println();
        String input = getValidatedString("Enter the updated treatment: ");
        if (!doctorMRM.editTreatments(id, index, input))
            displayError("Failed to edit treatment");
        else
            displaySuccess("Treatment updated");
    }
}
