package Databases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Common.Database;
import Common.DatabaseItems;
import DatabaseItems.AppointmentSlot;

public class DoctorSchedule extends Database {
    private String doctor_id;

    public DoctorSchedule(String doctor_id) {
        this.doctor_id = doctor_id;
        setcsvPath("Database/DoctorsSchedule/" + doctor_id + ".csv");
        setHeaderFormat("appointment_id,patient_id,date,timestart,timeend,status");
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

    public DatabaseItems createDatabaseItem(String[] values){
        return new AppointmentSlot(values);
    }

    public DatabaseItems searchItem(String id) {
        for (DatabaseItems item : records) {
            AppointmentSlot appointment = (AppointmentSlot) item;
            if (appointment.getAppointmentId().equals(id)) {
                return appointment; // Return the found item
            }
        }
        return null; // Return null if no item is found
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
        printItems("Doctor " + doctor_id + "'s Schedule: ");
    }

    // Print Doctor Personal Schedule from CSV
    public void printDoctorSchedule(String doctor_id) {
        String filePath = "Database/DoctorsSchedule/" + doctor_id + ".csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            if ((line = br.readLine()) != null) {
                System.out.println("Schedule for Dr. : " + doctor_id);
                System.out.println(line);
            }
           
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading schedule for Doctor ID: " + doctor_id);
            e.printStackTrace();
        }
    }

    // Save Doctor Personal Schedule to CSV
    public void saveScheduleToCSV(ArrayList<AppointmentSlot> personalSchedule) {

        try (FileWriter writer = new FileWriter(csvPath, false)) {
            writer.write(headerFormat + "\n");

            for (AppointmentSlot appointment : personalSchedule) {
                writer.write(appointment.serialise());
            }
            System.out.println("Personal schedule saved successfully for Doctor ID: " + doctor_id);
        } catch (IOException e) {
            System.out.println("Error saving personal schedule for Doctor ID: " + doctor_id);
            e.printStackTrace();
        }
    }


}
