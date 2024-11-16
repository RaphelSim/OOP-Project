package Common;

import Controllers.AccountManager;
import DatabaseItems.Account;
import Databases.*;
import UI.AccountManagementPages.UpdateDetailsPage;

/**
 * The {@code AppManager} class serves as an abstract base class for managing
 * application-level functionalities. It provides methods for user login/logout,
 * database management, and common settings across different roles.
 */
public abstract class AppManager {
    protected AccountDatabase accountDatabase;
    protected AppointmentOutcomeDatabase appointmentOutcomeDatabase;
    protected InventoryDatabase inventoryDatabase;
    protected InventoryRequestDatabase inventoryRequestDatabase;
    protected MedicalRecordDatabase medicalRecordDatabase;
    protected DoctorSchedule doctorSchedule;
    protected Account account;
    protected Role role;

    // Common managers for all users to be able to change password
    protected AccountManager accountManager;
    protected UpdateDetailsPage updateDetailsPage;

    /**
     * Logs in the specified account and initializes the application state.
     *
     * @param account the {@link Account} object representing the user logging in
     */
    public void logIn(Account account) {
        this.account = account;
        this.role = account.getrole();
        loadDatabases();
        createManagers();
        createPages();
        displayMainPage();
    }

    /**
     * Logs out the current user and saves any changes made to the databases.
     */
    public void logOut() {
        saveDatabases();
    }

    /**
     * Returns the role of the currently logged-in user.
     *
     * @return the {@link Role} of the current user
     */
    public Role getRole() {
        return role;
    }

    /**
     * Displays settings options for updating user details.
     */
    protected void settings() {
        updateDetailsPage.displayOptions(account);
    }

    /**
     * Creates instances of managers specific to the application context.
     * This method must be implemented by subclasses to define manager creation.
     */
    protected abstract void createManagers();

    /**
     * Creates instances of pages specific to the application context.
     * This method must be implemented by subclasses to define page creation.
     */
    protected abstract void createPages();

    /**
     * Loads necessary databases for managing application data.
     * This method must be implemented by subclasses to define database loading.
     */
    protected abstract void loadDatabases();

    /**
     * Saves changes made to databases back to their respective CSV files.
     * This method must be implemented by subclasses to define database saving.
     */
    protected abstract void saveDatabases();

    /**
     * Displays the main page of the application, providing access to various functionalities.
     */
    public abstract void displayMainPage();

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
}