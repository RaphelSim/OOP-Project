package Controllers.AppManagers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import AppointmentSystem.Appointment;
import AppointmentSystem.Doctor;
import Common.AppManager;
import Common.ClearOutput;
import Controllers.AOManagers.DoctorAOM;
import Controllers.AccountManager;
import Databases.AccountDatabase;
import Databases.AppointmentOutcomeDatabase;
import Databases.DoctorSchedule;
import Databases.MedicalRecordDatabase;
import UI.UpdateDetailsPage;
import UI.UserMenu;
import UI.AOMUI.DoctorOutcomeInterface;

public class DoctorAppMgr extends AppManager {
    // Declare managers
    private DoctorSchedule doctorSchedule;
    private DoctorAOM doctorOutcomeManager;
    private DoctorOutcomeInterface doctorOutcomeUI;

    // Attributes
    private Doctor d;
    private ArrayList<Appointment> doctorSchedulelist = new ArrayList<>();

    public DoctorAppMgr (Doctor d) {
        this.d = d;
    }

    public DoctorAppMgr () {
    }

    @Override
    public void displayMainPage() {
        boolean logout = false;
        while (!logout) {
            ClearOutput.clearOutput();
            int selection = UserMenu.displayDoctorMenu();

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
                    handleAppointmentRequests();
                    break;
                case 6:
                    viewUpcomingAppointments();
                    break;
                case 7:
                    recordAppointmentOutcome();
                    break;
                case 8:
                    settings();
                    break;
                case 9:
                    ClearOutput.clearOutput();
                    System.out.println("Thank you for using the Hospital X System. Goodbye!");
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
        accountManager = new AccountManager(account, accountDatabase, medicalRecordDatabase);
    }

    @Override
    protected void createPages() {
        doctorOutcomeUI = new DoctorOutcomeInterface(doctorOutcomeManager, doctorSchedule);
        updateDetailsPage = new UpdateDetailsPage(accountManager);
    }

    // Methods to handle each menu option
    private void viewPatientMedicalRecords() {
        // Implement functionality to view patient medical records
    }

    private void updatePatientMedicalRecords() {
        // Implement functionality to update patient medical records
    }

    private void viewPersonalSchedule() {
        // Implement functionality to view personal schedule
    }

    private void setAvailability() {
        // Implement functionality to set availability for appointments
        Scanner sc = new Scanner(System.in);
		int day = 0, month = 0, year = 0;
		String startTime = "", endTime = "";
		System.out.println("Welcome Dr." + d.getName());
		System.out.println("Please setup your Personal Schedule Availability:");
		
		// Year
        while (true) {
            System.out.print("Enter Year (e.g.: 2024): ");
            try {
                year = sc.nextInt();
                if (year > 0)
                    break;
                else
                    System.out.println("Invalid year! Please enter again.");
            } 
			catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a numeric value.");
			}
        }

        // Month
        while (true) {
            System.out.print("Enter Month (1-12): ");
            try {
                month = sc.nextInt();
                if (month >= 1 && month <= 12) 
                    break;
                else
                    System.out.println("Invalid month. Please enter a value between 1 and 12.");
                
            } 
			catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
			}
        }

        // Day
        while (true) {
            System.out.print("Enter Day (1-31): ");
            try {
                day = sc.nextInt();
                if (isValidDate(year, month, day))
                    break;
                else
                    System.out.println("Invalid date! Please enter a valid date for your input month and year.");
            } 
			catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
			}
        }
        

        // Start Time
        while (true) {
            System.out.print("Enter Start Time (HH:MM, from 08:00 to 18:00): ");
            startTime = sc.next();
            if (isValidTime(startTime))
                break;
            else
                System.out.println("Invalid start time! Please enter a start time between 08:00 and 18:00.");
        }

        // End Time
        while (true) {
            System.out.print("Enter End Time (HH:MM, must be after Start Time): ");
            endTime = sc.next();
            if (isValidTime(endTime) && isEndTimeAfterStart(startTime, endTime))
                break;
            else
                System.out.println("Invalid end time! Ensure it is after start time and within 08:00 to 18:00.");
        }

        // Display Personal Schedule Date and Time
        System.out.printf("You have entered:\nDate: %02d/%02d/%04d\nTime: %s - %s\n", day, month, year, startTime, endTime);
		LocalDate confirmDate = LocalDate.of(year, month, day);
		// Should we ask to confirm date and time?


		// Code to format them and split to 60mins interval time slots assigning each to appointment
		this.doctorSchedulelist = generateTimeSlot(d.getDoctorID(),confirmDate, startTime, endTime);

		// Display Timeslots
        displayDocTimeSlot();



		sc.close();
    }

    // Just a method to check valid date
	 private static boolean isValidDate(int year, int month, int day) {
		try {
            LocalDate date = LocalDate.of(year, month, day);
            return true;
		}
        catch (DateTimeParseException e) {
            return false;
		}
	}

	// Just a method to check valid time
	private static boolean isValidTime(String time) {
        if (!time.matches("\\d{2}:\\d{2}")) 
			return false;
        
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
		int mins = Integer.parseInt(parts[1]);
        
        return (hours >= 8 && hours <= 18 && mins >= 0 && mins <= 59);
    }

	// Just a method to check if end time is after start time
    private static boolean isEndTimeAfterStart(String startTime, String endTime) {
        String[] startParts = startTime.split(":");
        String[] endParts = endTime.split(":");
        
        int startHours = Integer.parseInt(startParts[0]);
        int startMinutes = Integer.parseInt(startParts[1]);
        
        int endHours = Integer.parseInt(endParts[0]);
        int endMinutes = Integer.parseInt(endParts[1]);

        return (endHours > startHours || (endHours == startHours && endMinutes > startMinutes));
    }

	// Just a method to split start time and end time to individual 30mins time slots
	private ArrayList<Appointment> generateTimeSlot(String doctorID, LocalDate date, String startTime, String endTime) {
		LocalTime sT = LocalTime.parse(startTime);
		LocalTime eT = LocalTime.parse(endTime);
		ArrayList<Appointment> ap = new ArrayList<>();

		while(sT.plusMinutes(60).isBefore(eT)) {
			//LocalTime end = sT.plusMinutes(60);
			LocalDateTime slotTime = LocalDateTime.of(date, sT);
			ap.add(new Appointment(slotTime, doctorID, "",false));
			sT = sT.plusMinutes(60);
		}

		return ap;
	}

    // Display Doctor's Personal Schedule (TimeSlots - 60min interval)
    public void displayDocTimeSlot() {
        int counter = 1;
        System.out.println("Generated Timeslots:");
        for (Appointment tS : this.doctorSchedulelist) {
            System.out.print(counter + ". ");
            System.out.println(tS.getAppointmentID());
            counter ++;
        }
    }

    private void handleAppointmentRequests() {
        // Implement functionality to accept or decline appointment requests
    }

    private void viewUpcomingAppointments() {
        // Implement functionality to view upcoming appointments
    }

    private void recordAppointmentOutcome() {
        // Implement functionality to record appointment outcomes
        doctorOutcomeUI.displayOptions();
    }
}
