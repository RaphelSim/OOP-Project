
import Common.AppointmentOutcomeStatus;
import Common.AppointmentStatus;
import Common.BloodType;
import Common.ClearOutput;
import Common.Gender;
import Common.Role;
import DatabaseItems.Account;
import DatabaseItems.AppointmentOutcome;
import DatabaseItems.AppointmentSlot;
import DatabaseItems.InventoryRequest;
import DatabaseItems.MedicalRecord;
import DatabaseItems.Medicines;
import Databases.AccountDatabase;
import Databases.AppointmentOutcomeDatabase;
import Databases.DoctorSchedule;
import Databases.InventoryDatabase;
import Databases.InventoryRequestDatabase;
import Databases.MedicalRecordDatabase;
import java.util.List;

public class Testing {

    public static void main(String[] args) {

        // you can implement your own testings in this file, or uncomment parts of the
        // below to try

        // TEST FOR ACCOUNTS/USERS
        // ClearOutput.clearOutput();
        // AccountDatabase database = new
        // AccountDatabase("Database/AccountCredentials.csv");
        // Account account = new Account("test2", "test2", Role.ADM);
        // database.addItem(account);
        // if(database.removeItem("3"))System.out.println("Remove success");
        // database.storeToCSV();
        // database.printItems();

        // TEST FOR MEDICAL RECORDS
        // ClearOutput.clearOutput();
        // MedicalRecordDatabase medidata = new MedicalRecordDatabase();
        // List<String> diagnose = List.of("Big eye balls", "Back,shoulder pain");
        // List<String> treatment = List.of("Small back", "Chest,back pain");
        // MedicalRecord record = new MedicalRecord("88", "Test", "2004-08-22",
        // Gender.MALE, "test", "0123131",
        // BloodType.AB_NEGATIVE, diagnose, treatment);
        // medidata.addItem(record);
        // if(medidata.removeItem("1234")) System.out.println("Remove success");
        // medidata.printItems();
        // medidata.storeToCSV();

        // TEST FOR APPOINTMENT OUTCOME
        ClearOutput.clearOutput();
        AppointmentOutcomeDatabase database = new AppointmentOutcomeDatabase();
        AppointmentOutcome record = new AppointmentOutcome("testesttest","DOC12345","PAT12345",
        "2004-01-07", "MRI,XRay", "drugs,weed",
        "All good", AppointmentOutcomeStatus.PENDING);
        database.addItem(record);
        //if(database.removeItem("testesttest")) System.out.println("Remove success");
        database.printItems();
        database.storeToCSV();

        // TEST FOR DOCTOR SCHEDULE
        // ClearOutput.clearOutput();
        // DoctorSchedule.newDoctor("DOC88888"); // if doctor is newly added
        // DoctorSchedule schedule = new DoctorSchedule("DOC12345"); // retrieveschedule
        // AppointmentSlot slot = new AppointmentSlot("PAT12345", "DOC12345",
        // "2024-10-29", "12:00", "13:00",
        // AppointmentStatus.REQUESTED);
        // schedule.addItem(slot);
        // schedule.printItems();
        // schedule.storeToCSV();
        // DoctorSchedule.deleteDoctorFile("DOC88888"); // to delete a Doctor's file
        // schedule.storeToCSV();

        // TEST FOR INVENTORY
        // ClearOutput.clearOutput();
        // InventoryDatabase database = new InventoryDatabase();
        // Medicines medicine = new Medicines("Viagra", 20, 5);
        // database.addItem(medicine);
        // database.printItems();
        // database.storeToCSV();
        // if(database.removeItem("Viagra")) System.out.println("Remove success");
        // database.storeToCSV();

        // TEST FOR INVERNTORY REQUESTS
        // ClearOutput.clearOutput();
        // InventoryRequestDatabase database = new InventoryRequestDatabase();
        // InventoryRequest request = new InventoryRequest("Weed", 10);
        // database.addItem(request);
        // database.printItems();
        // database.storeToCSV();
        // if (database.removeItem("Weed", 10))
        // System.out.println("Remove success");
        // else
        // System.out.println("Remove failed");
        // database.storeToCSV();
    }
}

// PatientAppointmentManager(AppointmentDatabase database, String doctor_id , String patient_id)
