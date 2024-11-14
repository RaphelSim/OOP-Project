package Common;

import Controllers.AccountManager;

// import java.io.File;
// import java.io.FilenameFilter;
// import java.util.ArrayList;
// import java.util.List;

import DatabaseItems.Account;
import Databases.*;
import UI.AccountManagementPages.UpdateDetailsPage;

public abstract class AppManager {
    protected AccountDatabase accountDatabase;
    protected AppointmentOutcomeDatabase appointmentOutcomeDatabase;
    protected InventoryDatabase inventoryDatabase;
    protected InventoryRequestDatabase inventoryRequestDatabase;
    protected MedicalRecordDatabase medicalRecordDatabase;
    // protected List<DoctorSchedule> doctorsSchedules = new
    // ArrayList<DoctorSchedule>();
    protected DoctorSchedule doctorSchedule;
    protected Account account;
    protected Role role;

    // common managers for all users to be able to change password
    protected AccountManager accountManager;
    protected UpdateDetailsPage updateDetailsPage;

    public void logIn(Account account) {
        this.account = account;
        this.role = account.getrole();
        loadDatabases();
        createManagers();
        createPages();
        displayMainPage();
    }

    public void logOut() {
        saveDatabases();
    }

    public Role getRole() {
        return role;
    }

    protected void settings() {
        updateDetailsPage.displayOptions(account);
    }

    protected abstract void createManagers();

    protected abstract void createPages();

    protected abstract void loadDatabases(); // implement by subclasses to create instances of the databases

    protected abstract void saveDatabases(); // implement by subclasses to store back to csv files

    public abstract void displayMainPage();
}

// NOT SURE IF I WANT TO ADD THIS FUNCTION, BUT I'LL LEAVE IT HERE FOR NOW
// public void loadAllDoctorSchedule(){
// File folder = new File("Database/DoctorsSchedule");

// // Filter for .csv files
// File[] files = folder.listFiles(new FilenameFilter() {
// @Override
// public boolean accept(File dir, String name) {
// return name.toLowerCase().endsWith(".csv");
// }
// });

// // Add each CSV file name to the list
// String doctorId;
// if (files != null) {
// for (File file : files) {
// doctorId = file.getName().substring(0,(int) file.length()-4); //load the
// doctor id by removing .csv from the end of the filename
// System.out.println(doctorId);
// doctorsSchedules.add(new DoctorSchedule(doctorId));
// }
// }
// }