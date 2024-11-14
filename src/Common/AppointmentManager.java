package Common;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import AppointmentSystem.Appointment;
import AppointmentSystem.Doctor;

public class AppointmentManager {
	/*
	 *  Controls all the appointments exist in Hospital Management System (HMS)
	 *	
	 *	
	 *	
	 */
	protected ArrayList<Appointment> slots = new ArrayList<Appointment>();
	private ArrayList<Doctor> doctorList;

	public AppointmentManager() {
		doctorList = new ArrayList<>();
	}
	
	// Display All Appointment Slots
	public void getAppointmentSlots() {
		for(int i = 0; i < slots.size(); i++) {
			System.out.println(slots.get(i).getAppointmentID());
		}
	}
}
	
 
	
	
	


