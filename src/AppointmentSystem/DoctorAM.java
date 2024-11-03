package AppointmentSystem;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

// [Doctor] View personal schedule & set appointment availability
// 	 [  ] Methods: Accept, Decline, View Upcoming list, Record Appointment Outcome
// 	 
// 	 Set current date time?
// 	 Appointment personal schedule doctor set a day 9am - 1300
// 	 Doctor each appointment manager
// 	 Time slot display in interval (30mins?) for patients when they choose doctor 

public class DoctorAM extends AppointmentManager {

    private ArrayList<Appointment> doctorSchedule = new ArrayList<>();





    /*	[Doctor] Set Personal Schedule and availability
	 * 	Set day and time period of availability
	 * 	Each 30mins interval time slot will be created for patients to choose (if they choose this doc)
	 * 	All these will be updated into file after logout
	 */	

	 public void setPersonalSchedule(Doctor d) {

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
        sc.close();

		Scanner sc2 = new Scanner(System.in);
        // Start Time
        while (true) {
            System.out.print("Enter Start Time (HH:MM, from 08:00 to 18:00): ");
            startTime = sc2.nextLine();
            if (isValidTime(startTime))
                break;
            else
                System.out.println("Invalid time! Please enter a start time between 08:00 and 18:00.");
        }

        // End Time
        while (true) {
            System.out.print("Enter End Time (HH:MM, must be after Start Time): ");
            endTime = sc2.nextLine();
            if (isValidTime(endTime) && isEndTimeAfterStart(startTime, endTime))
                break;
            else
                System.out.println("Invalid end time! Ensure it is after start time and within 08:00 to 18:00.");
        }

        // Display Personal Schedule Date and Time
        System.out.printf("You have entered:\nDate: %02d/%02d/%04d\nTime: %s - %s\n", day, month, year, startTime, endTime);
		LocalDate confirmDate = LocalDate.of(year, month, day);
		// Should we ask to confirm date and time?


		// Code to format them and split to 30mins interval time slots assigning each to appointment
		this.doctorSchedule = generateTimeSlot(confirmDate, startTime, endTime);

		// Display Timeslots
        displayDocTimeSlot();



		sc2.close();
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
	private ArrayList<Appointment> generateTimeSlot(LocalDate date, String startTime, String endTime) {
		LocalTime sT = LocalTime.parse(startTime);
		LocalTime eT = LocalTime.parse(endTime);
		ArrayList<Appointment> ap = new ArrayList<>();

		while(sT.plusMinutes(30).isBefore(eT)) {
			//LocalTime end = sT.plusMinutes(30);
			LocalDateTime slotTime = LocalDateTime.of(date, sT);
			ap.add(new Appointment(slotTime, 0, 0,false));
			sT = sT.plusMinutes(30);
		}

		return ap;
	}

    // Display Doctor's Personal Schedule (TimeSlots - 30min interval)
    public void displayDocTimeSlot() {
        System.out.println("Generated Timeslots:");
        for (Appointment tS : this.doctorSchedule) {
            System.out.println(tS.getAppointmentID());
        }
    }
}
	
	//  [Doctor] Accept Appointment Request
	//public void acceptAppointment();
	
	//  [Doctor] Reject Appointment Request
	//public void rejectAppointment();


