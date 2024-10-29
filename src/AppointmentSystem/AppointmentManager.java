package AppointmentSystem;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class AppointmentManager {
	/*
	 *  Controls all the appointments exist in Hospital Management System (HMS)
	 *	For a start, probably work with 5 appointment slots
	 *	
	 *	[Patient] View available appointment slots with doctors
	 *	 [ 5 ]Methods: Schedule, Reschedule, Cancel, View Status of Scheduled, View Past Appointment Outcomes
	 *	
	 *	Status (based on Doctor) - Confirmed, Cancelled, Completed)
	 *  
	 *  Should doctors be already assigned to patients? Should we allow patients to choose doctors?
	 *
	 *	[Doctor] View personal schedule & set appointment availability
	 *	[  ] Methods: Accept, Decline, View Upcoming list, Record Appointment Outcome
	 *
	 *	Set current date time?
	 *	Appointment personal schedule doctor set a day 9am - 1300
	 *	Doctor each appointment manager
	 *	Time slot display in interval (30mins?) for patients when they choose doctor 
	 *
	 * Save to file after logout
	 *  Files read at start of runtime
	 *  
	 *  Patient choose a appointment - pending
	 */
	
	protected ArrayList<Appointment> slots = new ArrayList<Appointment>();
	
	// Display All Appointment Slots
	public void getAppointmentSlots() {
		for(int i = 0; i < slots.size(); i++) {
			System.out.println(slots.get(i).getAppointmentID());
		}
	}
	
 
	/*	[Patient] Schedule an Appointment
	 * Ability to choose doctor, available time slot
	 */
	public void scheduleAppointment(Appointment app, int patientID, int doctorID) {
		
		if(app.isAssigned()) {
			System.out.println("Appointment slot is taken. Please choose another available slot.");
		}
		else {
			app.setAssigned(app.getSlotTime(), patientID, doctorID);
		}
	}
	
	/* [Patient] Reschedule an Existing Appointment
	 * Change existing appointment to a new slot with no conflict
	 * Slot availability updated automatically
	 */ 
	public void rescheduleAppointment(Appointment oldApp, Appointment newApp, int patientID, int doctorID) {
		oldApp.unAssigned();
		newApp.setAssigned(newApp.getSlotTime(), patientID, doctorID);
	}
	
	/*  [Patient] Cancel an Existing Appointment
	 * 	Upon success cancellation, slot availability updated automatically
	 * 
	 */
	public void cancelAppointment(Appointment app) {
		app.unAssigned();
	}
	
	/*	[Doctor] Set Personal Schedule and availability
	 * 	Set day and time period of availability
	 * 	Each 30mins interval time slot will be created for patients to choose (if they choose this doc)
	 * 	All these will be updated into file after logout
	 */	

	 public void setPersonalSchedule(Doctor d) {

		Scanner sc = new Scanner(System.in);
		int day = 0, month = 0, year = 0;
		String date = "", startTime = "", endTime = "";
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
            startTime = sc.nextLine();
            if (isValidTime(startTime))
                break;
            else
                System.out.println("Invalid time! Please enter a start time between 08:00 and 18:00.");
        }

        // End Time
        while (true) {
            System.out.print("Enter End Time (HH:MM, must be after Start Time): ");
            endTime = sc.nextLine();
            if (isValidTime(endTime) && isEndTimeAfterStart(startTime, endTime))
                break;
            else
                System.out.println("Invalid end time! Ensure it is after start time and within 08:00 to 18:00.");
        }

        // Display Personal Schedule Date and Time
        System.out.printf("You have entered:\nDate: %02d/%02d/%04d\nTime: %s - %s\n", day, month, year, startTime, endTime);

		// Should we ask to confirm date and time?

		// Code to format them and split to 30mins interval time slots assigning each to appointment
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
}
	
	//  [Doctor] Accept Appointment Request
	//public void acceptAppointment();
	
	//  [Doctor] Reject Appointment Request
	//public void rejectAppointment();

}
