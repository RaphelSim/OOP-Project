package AppointmentSystem;
import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

public class Appointment {
	/*	Appointment ID: 2024-10-20/1300
	 * 	isAssigned - to determine if appointment slot is open or taken
	 * 	if slot is taken, Appointment object store patientID, doctorID, slotTime
	 * 	if open, patientID and doctorID are empty
	 */
	
	private String appointmentID;
	private LocalDateTime slotTime;		// Format:  yyyy-mm-dd/hh-mm\
	private String doctorID;				// DOC12345 (DOC + 5 digits)
	private String patientID;				// PAT23456 (PAT + 5 digits)
	private boolean assigned;
	
	public Appointment(LocalDateTime slotTime, String doctorID, String patientID, boolean assigned) {
		//super();
		this.appointmentID = doctorID + "/" + slotTime;	//Format: DOC12345/PAT22345/yyyy-mm-dd/1200\
		//this.appointmentID = appointmentID;
		this.slotTime = slotTime;
		this.doctorID = doctorID;
		this.patientID = patientID;
		this.assigned = assigned;
	}
	
	public Appointment () {
		
	}
	
	public String getAppointmentID() {
		return appointmentID;
	}

	public LocalDateTime getSlotTime() {
		return slotTime;
	}

	public void setSlotTime(LocalDateTime slotTime) {
		this.slotTime = slotTime;
	}

	public String getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(String doctorID) {
		this.doctorID = doctorID;
	}

	public String getPatientID() {
		return patientID;
	}

	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	public boolean isAssigned() {
		return assigned;
	}
	
	
	public void setAssigned(LocalDateTime slotTime, String doctorID, String patientID) {
		this.assigned = true;
		this.slotTime = slotTime;
		this.doctorID = doctorID;
		this.patientID = patientID;
		
	}
	
	public void unAssigned() {
		this.assigned = false;
		this.slotTime = null;
		this.doctorID = "";
		this.patientID = "";
		
	}
	
	
	
}
