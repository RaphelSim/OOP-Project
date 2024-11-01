package Databases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import Common.AppointmentOutcomeStatus;
import Common.Database;
import Common.DatabaseItems;
import Common.ListConverter;
import DatabaseItems.AppointmentOutcome;

public class AppointmentOutcomeDatabase extends Database {
    public AppointmentOutcomeDatabase() {
        setcsvPath("Database/AppointmentOutcomeRecord.csv");
        extractFromCSV();
    }

    public AppointmentOutcomeDatabase(String csvpath) {
        setcsvPath(csvpath);
        extractFromCSV();
    }

    @Override
    public void extractFromCSV() {
        // System.out.println(System.getProperty("user.dir")); uncomment this line to
        // print your directory
        try (Scanner scanner = new Scanner(new File(csvPath))) {
            // Skip the header
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            // appointment_id,patient_id,doctor_id,date,type_of_service,medication,consultation_notes,status
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                String id = values[0];
                String patient_id = values[1];
                String doctor_id = values[2];
                String date = values[3];
                String type_of_service = ListConverter.replaceWithComma(values[4]);
                String medication = ListConverter.replaceWithComma(values[5]);
                String consultation = ListConverter.replaceWithComma(values[6]);
                AppointmentOutcomeStatus status = AppointmentOutcomeStatus.fromString(values[7]);

                records.add(new AppointmentOutcome(id,doctor_id,patient_id, date, type_of_service, medication, consultation, status));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void storeToCSV() {
        try (FileWriter writer = new FileWriter(csvPath)) {
            // Write header line
            writer.write("appointment_id,patient_id,doctor_id,date,type_of_service,medication,consultation_notes,status\n");

            // Write AppointmentOutcome details
            for (DatabaseItems record : records) {
                AppointmentOutcome AppointmentOutcome = (AppointmentOutcome) record;
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s\n",
                        AppointmentOutcome.getAppointmentId(),
                        AppointmentOutcome.getPatientId(),
                        AppointmentOutcome.getDoctorId(),
                        AppointmentOutcome.getDate(),
                        ListConverter.replaceWithCurly(AppointmentOutcome.getTypeOfService()),
                        ListConverter.replaceWithCurly(AppointmentOutcome.getMedication()),
                        ListConverter.replaceWithCurly(AppointmentOutcome.getConsultationNotes()),
                        AppointmentOutcome.getStatus().toString()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addItem(AppointmentOutcome record) {
        records.add(record);
    }

    public boolean removeItem(String appointment_id) {
        boolean itemRemoved = records.removeIf(record -> {
            AppointmentOutcome item = (AppointmentOutcome) record;
            return item.getAppointmentId().equals(appointment_id);
        });

        if (itemRemoved) {
            return true;
        } else {
            return false;
        }
    }

    public List<DatabaseItems> getRecords() {
    return records;
}

    @Override
    public void printItems() {
        // Print the AppointmentOutcomes
        System.out.println("AppointmentOutcome Database: ");
        System.out.println("--------------------------");
        for (DatabaseItems record : records) {
            if (record instanceof AppointmentOutcome) { // Ensure it's an AppointmentOutcome
                AppointmentOutcome AppointmentOutcome = (AppointmentOutcome) record;
                System.out.println(); // Print a new line for better readability
                System.out.println("Appointment ID: " + AppointmentOutcome.getAppointmentId());
                System.out.println("Doctor ID: " + AppointmentOutcome.getDoctorId());
                System.out.println("Patient ID: " + AppointmentOutcome.getPatientId());
                System.out.println("Date: " + AppointmentOutcome.getDate());
                System.out.println("Type of Service: " + AppointmentOutcome.getTypeOfService());
                System.out.println("Medication: " + AppointmentOutcome.getMedication());
                System.out.println("Consultation Notes: " + AppointmentOutcome.getConsultationNotes());
                System.out.println("Status: " + AppointmentOutcome.getStatus());
                System.out.println(); // Print a new line for better readability
            }
        }
    }
    

}
