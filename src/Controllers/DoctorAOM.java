package Controllers;
import java.util.List;
import java.util.stream.Collectors;
import UI.DoctorUI; // Assuming DoctorUI is in the UI package
import Databases.AppointmentOutcomeDatabase; // Replace with the actual package name
import DatabaseItems.AppointmentOutcome; // Replace with the actual package name

public class DoctorAOM extends BaseAppointmentOutcomeManager {
    private String doctorID;
    private DoctorUI ui;

    public DoctorAOM(AppointmentOutcomeDatabase database, String doctorID, DoctorUI ui) {
        super(database);
        this.doctorID = doctorID;
        this.ui = ui;  // Inject UI instance
    }

    public void handleOutcomes() {
        String action = ui.getActionChoice(); // Assume a method to ask user for choice
        switch (action.toLowerCase()) {
            case "view":
                displayDoctorOutcomes();
                break;
            case "edit":
                editOutcome();
                break;
            default:
                ui.displayMessage("Invalid choice.");
        }
    }

    private void displayDoctorOutcomes() {
        List<AppointmentOutcome> doctorOutcomes = database.getRecords().stream()
            .map(record -> (AppointmentOutcome) record) // Cast each item to AppointmentOutcome
            .filter(outcome -> outcome.getAppointmentId().startsWith(doctorID))
            .collect(Collectors.toList());
    
        if (doctorOutcomes.isEmpty()) {
            ui.displayMessage("No outcomes found.");
        } else {
            doctorOutcomes.forEach(ui::displayOutcomeDetails); // Delegate display to UI
        }
    }
    

    public void editOutcome() {
        String appointmentID = ui.getAppointmentIdInput();
        AppointmentOutcome existingOutcome = database.getRecord(appointmentID);
        
        if (existingOutcome != null) {
            ui.displayOutcomeDetails(existingOutcome); // Display current outcome
            AppointmentOutcome newOutcome = ui.getNewOutcomeDetails(); // Get new details from UI
            removeOutcome(appointmentID);
            addOutcome(newOutcome);
            ui.displayMessage("Outcome updated successfully.");
        } else {
            ui.displayMessage("Outcome not found.");
        }
    }
}
