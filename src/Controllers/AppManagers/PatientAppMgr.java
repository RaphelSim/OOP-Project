package Controllers.AppManagers;

import java.util.ArrayList;
import java.util.Scanner;

import AppointmentSystem.Doctor;
import Common.AppManager;
import Common.ClearOutput;
import Controllers.AccountManager;
import Databases.AccountDatabase;
import Databases.AppointmentOutcomeDatabase;
import Databases.MedicalRecordDatabase;
import UI.UserMenu;
import UI.UpdateDetailsPage;

public class PatientAppMgr extends AppManager {
    // Declare managers
    private AccountManager accountManager;
    private UpdateDetailsPage updateDetailsPage;

    // Attributes
    private ArrayList<Doctor> doctorList;

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
                    updatePersonalInformation();
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
    }

    @Override
    protected void createPages() {
        updateDetailsPage = new UpdateDetailsPage(accountManager);
    }

    // Methods to handle each menu option
    private void viewMedicalRecord() {
        //  Implement PatientMRM interaction here
    }

    private void updatePersonalInformation() {
        updateDetailsPage.displayOptions(account);
    }

    private void viewAvailableAppointments() {
        //  Implement PatientAptMgr interaction for viewing available slots
    }

    private void scheduleAppointment() {
        //  Implement PatientAptMgr interaction for scheduling an appointment

        String choiceDoc = "";
        Doctor chosenDoc = new Doctor();
        Scanner sc = new Scanner(System.in);
        boolean exists = true;
        while (exists) {
            System.out.println("Here are the list of Doctors you can choose from (Enter Doctor ID):");
            System.out.println("Doctor ID   |   Doctor Name");
            for(Doctor d:doctorList) {
                System.out.println(d.getDoctorID() + "      " + d.getName());
            }
            
            choiceDoc = sc.nextLine();

            for(Doctor d:doctorList) {
                if(d.getDoctorID().equalsIgnoreCase(choiceDoc)) {
                    chosenDoc = d;
                    exists = false;
                    break;
                }
            }
            if(exists)
                System.out.println("Invalid Doctor ID entered! Please enter again.");

        }

        chosenDoc.getAM_D().displayDocTimeSlot();

        // User input to choose timeslot then add to Patient's Appointment
        int choice;
        choice = sc.nextInt();
        //if(choice)


		//System.out.println("Appointment slot is taken. Please choose another available slot.");
        sc.close();
    }

    private void rescheduleAppointment() {
        //  Implement PatientAptMgr interaction for rescheduling an appointment
    }

    private void cancelAppointment() {
        //  Implement PatientAptMgr interaction for canceling an appointment
    }

    private void viewScheduledAppointments() {
        //  Implement PatientAptMgr interaction for viewing scheduled appointments
    }

    private void viewPastAppointmentOutcomes() {
        //  Implement PatientAOM interaction for viewing past outcomes
    }
}
