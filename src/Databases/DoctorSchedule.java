package Databases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Common.AppointmentStatus;
import Common.Database;
import Common.DatabaseItems;
import DatabaseItems.AppointmentSlot;

public class DoctorSchedule extends Database {
    private String doctor_id;

    public DoctorSchedule(String doctor_id) {
        this.doctor_id = doctor_id;
        setcsvPath("Database/DoctorsSchedule/" + doctor_id + ".csv");
        extractFromCSV();
    }

    // function to create a new file for a new doctor
    public static void newDoctor(String doctor_id) {
        try (FileWriter writer = new FileWriter("Database/DoctorsSchedule/" + doctor_id + ".csv")) {
            // Write header line
            writer.write("appointment_id,patient_id,date,timestart,timeend,status\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // function to remove file for doctor
    public static void deleteDoctorFile(String doctor_id) {
        String filePath = "Database/DoctorsSchedule/" + doctor_id + ".csv";
        File file = new File(filePath);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted for Doctor ID: " + doctor_id);
            } else {
                System.out.println("Failed to delete the file for Doctor ID: " + doctor_id);
            }
        } else {
            System.out.println("File does not exist for Doctor ID: " + doctor_id);
        }
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
            // appointment_id,patient_id,date,timestart,timeend,status
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                String appointment_id = values[0];
                String patiend_id = values[1];
                String date = values[2];
                String timestart = values[3];
                String timeend = values[4];
                AppointmentStatus status = AppointmentStatus.fromString(values[5]);

                records.add(
                        new AppointmentSlot(appointment_id, patiend_id, doctor_id, date, timestart, timeend, status));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void storeToCSV() {
        try (FileWriter writer = new FileWriter(csvPath)) {
            // Write header line
            writer.write("appointment_id,patient_id,date,timestart,timeend,status\n");

            // Write AppointmentSlot details
            for (DatabaseItems record : records) {
                AppointmentSlot appointment_slot = (AppointmentSlot) record;
                writer.write(String.format("%s,%s,%s,%s,%s,%s\n",
                        appointment_slot.getAppointmentId(),
                        appointment_slot.getPatientId(),
                        appointment_slot.getDate(),
                        appointment_slot.getTimestart(),
                        appointment_slot.getTimeend(),
                        appointment_slot.getStatus().toString()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addItem(AppointmentSlot record) {
        records.add(record);
    }

    public boolean removeItem(String appointment_id) {
        boolean itemRemoved = records.removeIf(record -> {
            AppointmentSlot item = (AppointmentSlot) record;
            return item.getAppointmentId().equals(appointment_id);
        });

        if (itemRemoved) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void printItems() {
        // Print the AppointmentSlots
        // appointment_id,patient_id,date,timestart,timeend,status
        System.out.println("Doctor " + doctor_id + "'s Schedule: ");
        System.out.println("--------------------------");
        for (DatabaseItems record : records) {
            if (record instanceof AppointmentSlot) { // Ensure it's an AppointmentSlot
                AppointmentSlot AppointmentSlot = (AppointmentSlot) record;
                System.out.println(); // Print a new line for better readability
                System.out.println("Appointment ID: " + AppointmentSlot.getAppointmentId());
                System.out.println("Patient ID: " + AppointmentSlot.getPatientId());
                System.out.println("Date: " + AppointmentSlot.getDate());
                System.out.println("Time Start: " + AppointmentSlot.getTimestart());
                System.out.println("Time End: " + AppointmentSlot.getTimeend());
                System.out.println("Status: " + AppointmentSlot.getStatus());
                System.out.println(); // Print a new line for better readability
            }
        }
    }
}
