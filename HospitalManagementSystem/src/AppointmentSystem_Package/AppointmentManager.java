package AppointmentSystem_Package;
import java.util.ArrayList;

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
	
	
	//  [Doctor] Accept Appointment Request
	//public void acceptAppointment();
	
	//  [Doctor] Reject Appointment Request
	//public void rejectAppointment();

}
