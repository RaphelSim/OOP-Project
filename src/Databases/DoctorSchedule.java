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

/**
 * The {@code DoctorSchedule} class manages the schedule of a specific doctor.
 * It extends {@link Database} and provides methods for creating, searching,
 * removing, and sorting appointment slots.
 */
public class DoctorSchedule extends Database {
    private String doctor_id; // Unique identifier for the doctor

    /**
     * Constructs a {@code DoctorSchedule} for a specific doctor.
     *
     * @param doctor_id the unique identifier of the doctor
     */
    public DoctorSchedule(String doctor_id) {
        this.doctor_id = doctor_id;
        setcsvPath("Database/DoctorsSchedule/" + doctor_id + ".csv");
        setHeaderFormat("appointment_id,patient_id,date,timestart,timeend,status");
        extractFromCSV(); // Load records from the specified CSV file
    }

    /**
     * Creates a new CSV file for a new doctor with the specified ID.
     *
     * @param doctor_id the unique identifier of the doctor
     */
    public static void newDoctor(String doctor_id) {
        try (FileWriter writer = new FileWriter("Database/DoctorsSchedule/" + doctor_id + ".csv")) {
            // Write header line
            writer.write("appointment_id,patient_id,date,timestart,timeend,status\n");
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace on error
        }
    }

    /**
     * Deletes the CSV file associated with a specific doctor.
     *
     * @param doctor_id the unique identifier of the doctor
     */
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

    /**
     * Overrides the storing function to sort appointment slots before saving to CSV.
     */
    @Override
    public void storeToCSV() {
        sortAppointments(); // Sort appointments before storing
        super.storeToCSV(); // Call parent method to store records
    }

    /**
     * Creates a new {@link AppointmentSlot} instance from an array of values.
     *
     * @param values an array of strings containing appointment slot data
     * @return a new {@link AppointmentSlot} object
     */
    public DatabaseItems createDatabaseItem(String[] values) {
        return new AppointmentSlot(values); // Create and return a new appointment slot
    }

    /**
     * Searches for an appointment slot by its ID.
     *
     * @param id the unique identifier of the appointment slot to search for
     * @return the found {@link AppointmentSlot} object; null if not found
     */
    public DatabaseItems searchItem(String id) {
        for (DatabaseItems item : records) {
            AppointmentSlot appointment = (AppointmentSlot) item;
            if (appointment.getAppointmentId().equals(id)) {
                return appointment; // Return the found item
            }
        }
        return null; // Return null if no matching record is found
    }

    /**
     * Removes an appointment slot by its ID.
     *
     * @param appointment_id the unique identifier of the appointment slot to remove
     * @return true if any record was successfully removed; false otherwise
     */
    public boolean removeItem(String appointment_id) {
        boolean itemRemoved = records.removeIf(record -> {
            AppointmentSlot item = (AppointmentSlot) record;
            return item.getAppointmentId().equals(appointment_id); // Check if ID matches
        });

        return itemRemoved; // Return true if a record was removed
    }

    /**
     * Prints all items in the doctor's schedule.
     */
    @Override
    public void printItems() {
        super.printItems("Doctor " + doctor_id + "'s Schedule: "); // Print items with specified header
    }

    /**
     * Sorts appointment slots in ascending order based on date and start time.
     */
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
                        return dateComparison; // Return date comparison result if not equal
                    }
                    // If dates are equal, compare start times
                    return LocalTime.parse(a1.getTimestart()).compareTo(LocalTime.parse(a2.getTimestart()));
                }
                return 0; // Default comparison result if not applicable
            }
        });
    }
}