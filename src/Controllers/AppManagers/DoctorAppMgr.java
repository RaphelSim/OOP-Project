package Controllers.AppManagers;
import Controllers.AOManagers.DoctorAOM;
import Common.AppManager;
import Common.ClearOutput;
import Databases.AccountDatabase;
import Databases.AppointmentOutcomeDatabase;
import Databases.DoctorSchedule;
import Databases.MedicalRecordDatabase;
import UI.DoctorMenu;
import UI.AOMUI.DoctorOutcomeInterface;

public class DoctorAppMgr extends AppManager {
    //initiate managers and pages(UI here)
    private DoctorAOM doctorOutcomeManager;
    private DoctorOutcomeInterface doctorOutcomeUI;
    //call the main page here
    @Override
    public void displayMainPage() {
        boolean logout = false;
        while (!logout) {
            ClearOutput.clearOutput();  
            int selection = DoctorMenu.displayMenu();

            switch (selection) {
                case 1:
                    viewPatientMedicalRecords();
                    break;
                case 2:
                    updatePatientMedicalRecords();
                    break;
                case 3:
                    viewPersonalSchedule();
                    break;
                case 4:
                    setAvailability();
                    break;
                case 5:
                    acceptOrDeclineAppointmentRequests();
                    break;
                case 6:
                    viewUpcomingAppointments();
                    break;
                case 7:
                    ClearOutput.clearOutput();
                    recordAppointmentOutcome();
                    break;
                case 8:
                    ClearOutput.clearOutput();
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
        doctorSchedule = new DoctorSchedule(account.getid());
    }

    @Override
    protected void saveDatabases() {
        accountDatabase.storeToCSV();
        medicalRecordDatabase.storeToCSV();
        appointmentOutcomeDatabase.storeToCSV();
        doctorSchedule.storeToCSV();
    }

    @Override
    protected void createManagers() {
        doctorOutcomeManager = new DoctorAOM(appointmentOutcomeDatabase, account.getid());
        // create instance of manager here
    }

    @Override
    protected void createPages() {
        doctorOutcomeUI = new DoctorOutcomeInterface(doctorOutcomeManager);
        // create instance of pages here
    }

    private void viewPatientMedicalRecords() {
        doctorOutcomeUI.displayOptions();  
    }

    private void updatePatientMedicalRecords() {
        doctorOutcomeUI.editOutcome(); 
    }

    //declare functions to call your managers accordingly
    private void viewPersonalSchedule(){}
    private void setAvailability(){}
    private void acceptOrDeclineAppointmentRequests(){}
    private void viewUpcomingAppointments(){}

    private void recordAppointmentOutcome() {
        doctorOutcomeUI.recordOutcome();
    }
}
