package Controllers;

import Databases.AppointmentOutcomeDatabase;
import DatabaseItems.AppointmentOutcome;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorAOM extends BaseAppointmentOutcomeManager {
    private final String doctorID;  // Doctor ID for filtering outcomes

    public DoctorAOM(AppointmentOutcomeDatabase database, String doctorID) {
        super(database);
        this.doctorID = doctorID;
    }

    @Override
    public void printAllOutcomes() {
        // Filter outcomes based on an assumed mapping or external logic
        List<AppointmentOutcome> doctorOutcomes = database.getRecords().stream()
            .map(record -> (AppointmentOutcome) record)
            .filter(outcome -> outcome.getAppointmentId().startsWith(doctorID))  // Example: Using appointment ID prefix
            .collect(Collectors.toList());

        // Print each outcome for the doctor
        doctorOutcomes.forEach(outcome -> System.out.println(outcome));
    }

    // Method to edit outcomes if required for doctors
    public void editOutcome(String appointmentID, AppointmentOutcome newOutcome) {
        removeOutcome(appointmentID);
        addOutcome(newOutcome);
    }
}


