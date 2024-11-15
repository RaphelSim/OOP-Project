package Controllers.AppManagers;

import Controllers.AOManagers.PatientAOM;
import Controllers.MRManagers.PatientMRM;
import Common.AppManager;
import Common.ClearOutput;
import Controllers.AccountManager;
import Databases.AccountDatabase;
import Databases.AppointmentOutcomeDatabase;
import Databases.MedicalRecordDatabase;
import UI.UserMenu;
import UI.ViewAvailableAppointmentsPage;
import UI.AOMUI.PatientOutcomeInterface;
import UI.AccountManagementPages.UpdateDetailsPage;
import UI.MedicalRecordPages.PatientViewMedicalRecordPage;

public class PatientAppMgr extends AppManager {
    // Declare managers
    private AccountManager accountManager;
    private ViewAvailableAppointmentsPage vAAP;

    // Attributes
    // private ArrayList<AppointmentSlot> patientApps;
    private PatientAOM patientOutcomeManager;
    private PatientMRM patientMRM;

    // Declare pages
    private PatientOutcomeInterface patientOutcomeUI;
    private PatientViewMedicalRecordPage patientViewMedicalRecordPage;

    @Override
    public void displayMainPage() {
        boolean logout = false;
        while (!logout) {
            ClearOutput.clearOutput();
            int selection = UserMenu.displayPatientMenu();

            switch (selection) {
                case 1:
                    viewMedicalRecord();
                    break;
                case 2:
                    settings();
                    break;
                case 3:
                    viewAvailableAppointments();
                    break;
                case 4:
                    scheduleAppointment();
                    break;
                case 5:
                    rescheduleAppointment();
                    break;
                case 6:
                    cancelAppointment();
                    break;
                case 7:
                    viewScheduledAppointments();
                    break;
                case 8:
                    viewPastAppointmentOutcomes();
                    break;
                case 9:
                    System.out.println("Thank you for using the Hospital X System. See you again soon!");
                    logout = true;
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        }
        logOut();
    }

    @Override
    protected void loadDatabases() {
        accountDatabase = new AccountDatabase();
        medicalRecordDatabase = new MedicalRecordDatabase();
        appointmentOutcomeDatabase = new AppointmentOutcomeDatabase();
    }

    @Override
    protected void saveDatabases() {
        accountDatabase.storeToCSV();
        medicalRecordDatabase.storeToCSV();
        appointmentOutcomeDatabase.storeToCSV();
    }

    @Override
    protected void createManagers() {
        accountManager = new AccountManager(account, accountDatabase, medicalRecordDatabase);
        patientOutcomeManager = new PatientAOM(appointmentOutcomeDatabase, account.getid());
        patientMRM = new PatientMRM(medicalRecordDatabase, account.getid());
    }

    @Override
    protected void createPages() {
        updateDetailsPage = new UpdateDetailsPage(accountManager);
        patientOutcomeUI = new PatientOutcomeInterface(patientOutcomeManager);
        patientViewMedicalRecordPage = new PatientViewMedicalRecordPage(patientMRM);
        vAAP = new ViewAvailableAppointmentsPage(accountDatabase);
    }

    // // Get List of Doctors
    // public ArrayList<Account> getDocList() {
    // return accountDatabase.getDocList();
    // }

    // Methods to handle each menu option
    private void viewMedicalRecord() {
        patientViewMedicalRecordPage.displayMedicalRecord();
    }

    private void viewAvailableAppointments() {
        // Implement PatientAptMgr interaction for viewing available slots
        // List Doctors (Name, ID) to choose
        // Get the chosen doctor's list of timeslots and display
        // Dont clear output to allow scheduleAppointment method
        vAAP.viewAvailableAppointments();

    }

    private void scheduleAppointment() {
        // Implement PatientAptMgr interaction for scheduling an appointment

        // String choiceDoc = "";
        // Doctor chosenDoc = new Doctor();
        // Scanner sc = new Scanner(System.in);
        // boolean exists = true;
        // while (exists) {
        // System.out.println("Here are the list of Doctors you can choose from (Enter
        // Doctor ID):");
        // System.out.println("Doctor ID | Doctor Name");
        // for(Doctor d:doctorList) {
        // System.out.println(d.getDoctorID() + " " + d.getName());
        // }

        // choiceDoc = sc.nextLine();

        // for(Doctor d:doctorList) {
        // if(d.getDoctorID().equalsIgnoreCase(choiceDoc)) {
        // chosenDoc = d;
        // exists = false;
        // break;
        // }
        // }
        // if(exists)
        // System.out.println("Invalid Doctor ID entered! Please enter again.");

        // }

        // chosenDoc.getAM_D().displayDocTimeSlot();

        // // User input to choose timeslot then add to Patient's Appointment
        // int choice;
        // choice = sc.nextInt();
        // //if(choice)

        // //System.out.println("Appointment slot is taken. Please choose another
        // available slot.");
        // sc.close();
    }

    private void rescheduleAppointment() {
        // Implement PatientAptMgr interaction for rescheduling an appointment
    }

    private void cancelAppointment() {
        // Implement PatientAptMgr interaction for canceling an appointment
    }

    private void viewScheduledAppointments() {
        // Implement PatientAptMgr interaction for viewing scheduled appointments
    }

    private void viewPastAppointmentOutcomes() {
        // Implement PatientAOM interaction for viewing past outcomes
        patientOutcomeUI.displayOptions(account.getid());
    }
}
