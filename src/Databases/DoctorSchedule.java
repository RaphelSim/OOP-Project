package Databases;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;

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

    // override the storing function to sort first before storing back
    @Override
    public void storeToCSV() {
        sortAppointments();
        super.storeToCSV();
    }

    public DatabaseItems createDatabaseItem(String[] values) {
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
        super.printItems("Doctor " + doctor_id + "'s Schedule: ");
    }

    // Method to sort the appointment slots in ascending order
    public void sortAppointments() {
        Collections.sort(this.records, new Comparator<DatabaseItems>() {
            @Override
            public int compare(DatabaseItems d1, DatabaseItems d2) {
                // Downcast to AppointmentSlot
                if (d1 instanceof AppointmentSlot && d2 instanceof AppointmentSlot) {
                    AppointmentSlot a1 = (AppointmentSlot) d1;
                    AppointmentSlot a2 = (AppointmentSlot) d2;

                    // Compare dates first
                    int dateComparison = LocalDate.parse(a1.getDate()).compareTo(LocalDate.parse(a2.getDate()));
                    if (dateComparison != 0) {
                        return dateComparison;
                    }
                    // If dates are equal, compare start times
                    return LocalTime.parse(a1.getTimestart()).compareTo(LocalTime.parse(a2.getTimestart()));
                }
                return 0;
            }
        });
    }

}
