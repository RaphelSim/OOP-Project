package Controllers;

import UI.DoctorAOMUI;
import DatabaseItems.AppointmentOutcome;
import Databases.AppointmentOutcomeDatabase;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorAOM extends BaseAppointmentOutcomeManager {
    private String doctorID;
    private DoctorAOMUI ui;

    public DoctorAOM(AppointmentOutcomeDatabase database, String doctorID, DoctorAOMUI ui) {
        super(database);
        this.doctorID = doctorID;
        this.ui = ui;
    }

    // Display outcomes assigned to this doctor
    public void displayDoctorOutcomes() {
        List<AppointmentOutcome> doctorOutcomes = getAllOutcomes().stream()
                .filter(outcome -> outcome.getDoctorId().equals(doctorID))
                .collect(Collectors.toList());

        if (doctorOutcomes.isEmpty()) {
            ui.displayMessage("No outcomes found.");
        } else {
            doctorOutcomes.forEach(ui::displayOutcomeDetails);
        }
    }

    // Edit an outcome for a specific appointment
    public void editOutcome(String appointmentID) {
        AppointmentOutcome existingOutcome = getOutcome(appointmentID);
        if (existingOutcome != null && existingOutcome.getDoctorId().equals(doctorID)) {
            ui.displayOutcomeDetails(existingOutcome);
            AppointmentOutcome newOutcome = ui.getNewOutcomeDetails();
            removeOutcome(appointmentID);
            addOutcome(newOutcome);
            ui.displayMessage("Outcome updated successfully.");
        } else {
            ui.displayMessage("Outcome not found or you are not authorized to edit this outcome.");
        }
    }
}
