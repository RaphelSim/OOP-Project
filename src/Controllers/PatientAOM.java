package Controllers;

import DatabaseItems.AppointmentOutcome;
import Databases.AppointmentOutcomeDatabase;
import java.util.*;
import java.util.stream.Collectors;

public class PatientAOM extends BaseAppointmentOutcomeManager {
    private final String patientID;  // Patient ID for filtering

    public PatientAOM(AppointmentOutcomeDatabase database, String patientID) {
        super(database);
        this.patientID = patientID;
    }

    @Override
    public void printAllOutcomes() {
        List<AppointmentOutcome> patientOutcomes = database.getRecords().stream()
            .map(record -> (AppointmentOutcome) record)
            .filter(outcome -> patientID.equals(lookupPatientId(outcome.getAppointmentId())))
            .collect(Collectors.toList());

        patientOutcomes.forEach(System.out::println);
    }

    // Mock method to look up patient ID
    private String lookupPatientId(String appointmentId) {
        Map<String, String> appointmentToPatientMap = Map.of(
            "appt001", "pat123",
            "appt002", "pat456"
        );
        return appointmentToPatientMap.getOrDefault(appointmentId, "unknown");
    }
}


