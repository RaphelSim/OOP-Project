package Databases;

import Common.Database;
import Common.DatabaseItems;
import DatabaseItems.AppointmentOutcome;

/**
 * The {@code AppointmentOutcomeDatabase} class manages a collection of 
 * {@link AppointmentOutcome} objects, providing functionalities to 
 * extract, search, remove, and create appointment outcomes from a CSV file.
 */
public class AppointmentOutcomeDatabase extends Database {

    /**
     * Constructs an {@code AppointmentOutcomeDatabase} with a default CSV path
     * for storing appointment outcome records.
     */
    public AppointmentOutcomeDatabase() {
        setHeaderFormat("appointment_id,patient_id,doctor_id,date,type_of_service,medication,consultation_notes,status");
        setcsvPath("Database/AppointmentOutcomeRecord.csv");
        extractFromCSV();
    }

    /**
     * Constructs an {@code AppointmentOutcomeDatabase} with a specified CSV path
     * for storing appointment outcome records.
     *
     * @param csvpath the path to the CSV file containing appointment outcomes
     */
    public AppointmentOutcomeDatabase(String csvpath) {
        setHeaderFormat("appointment_id,patient_id,doctor_id,date,type_of_service,medication,consultation_notes,status");
        setcsvPath(csvpath);
        extractFromCSV();
    }

    /**
     * Searches for an appointment outcome by its unique identifier.
     *
     * @param id the unique identifier of the appointment outcome to search for
     * @return the {@link AppointmentOutcome} object if found; otherwise, returns null
     */
    public DatabaseItems searchItem(String id) {
        for (DatabaseItems item : records) {
            AppointmentOutcome appointment = (AppointmentOutcome) item;
            if (appointment.getAppointmentId().equals(id)) {
                return appointment; // Return the found item
            }
        }
        return null; // Return null if no item is found
    }

    /**
     * Removes an appointment outcome from the database using its unique identifier.
     *
     * @param appointment_id the unique identifier of the appointment outcome to be removed
     * @return true if the item was successfully removed; false otherwise
     */
    public boolean removeItem(String appointment_id) {
        boolean itemRemoved = records.removeIf(record -> {
            AppointmentOutcome item = (AppointmentOutcome) record;
            return item.getAppointmentId().equals(appointment_id);
        });

        return itemRemoved; // Return true if removed, false otherwise
    }

    /**
     * Creates a new {@link AppointmentOutcome} object from an array of values.
     *
     * @param values an array of strings containing appointment details in order:
     *               appointment_id, patient_id, doctor_id, date,
     *               type_of_service, medication, consultation_notes, status
     * @return a new instance of {@code AppointmentOutcome}
     */
    public DatabaseItems createDatabaseItem(String[] values) {
        return new AppointmentOutcome(values);
    }

    /**
     * Prints all items in the appointment outcome database with a specified title.
     */
    public void printItems() {
        printItems("Appointment Outcome Records Database");
    }
}