package AppointmentSystem;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class AppointmentManager {
	/*
	 *  Controls all the appointments exist in Hospital Management System (HMS)
	 *	
	 *	
	 *	
	 *
	 *	
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
	
 
	
	
	


