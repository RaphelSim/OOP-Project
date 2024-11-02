package Databases;

import Common.Database;
import Common.DatabaseItems;
import DatabaseItems.AppointmentOutcome;


// appointment_id,patient_id,doctor_id,date,type_of_service,medication,consultation_notes,status
public class AppointmentOutcomeDatabase extends Database {
    public AppointmentOutcomeDatabase() {
        setHeaderFormat("appointment_id,patient_id,doctor_id,date,type_of_service,medication,consultation_notes,status");
        setcsvPath("Database/AppointmentOutcomeRecord.csv");
        extractFromCSV();
    }

    public AppointmentOutcomeDatabase(String csvpath ) {
        setHeaderFormat("appointment_id,patient_id,doctor_id,date,type_of_service,medication,consultation_notes,status");
        setcsvPath(csvpath);
        extractFromCSV();
    }

    public DatabaseItems searchItem(String id) {
        for (DatabaseItems item : records) {
            AppointmentOutcome appointment = (AppointmentOutcome) item;
            if (appointment.getAppointmentId().equals(id)) {
                return appointment; // Return the found item
            }
        }
        return null; // Return null if no item is found
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

 

    public DatabaseItems createDatabaseItem(String[] values){
        //Name,id,Password,Role
        return new AppointmentOutcome(values);
    }

    public void printItems() {
        printItems("Appointment Outcome Records Database");
    }

}
