package AppointmentSystem_Package;
import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

public class Appointment {
	private LocalDateTime slotTime;
	private int doctorID;
	private int patientID;
	private boolean assigned;
	
	public Appointment(LocalDateTime slotTime, int doctorID, int patientID, boolean assigned) {
		//super();
		this.slotTime = slotTime;
		this.doctorID = doctorID;
		this.patientID = patientID;
		this.assigned = assigned;
	}
	
	public Appointment () {
		
	}

	public LocalDateTime getSlot() {
		return slotTime;
	}

	public void setSlot(LocalDateTime slotTime) {
		this.slotTime = slotTime;
	}

	public int getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public boolean isAssigned() {
		return assigned;
	}
	
	
	public void setAssigned(LocalDateTime slotTime, int doctorID, int patientID) {
		this.assigned = true;
		this.slotTime = slotTime;
		this.doctorID = doctorID;
		this.patientID = patientID;
		
	}
	
	public void unAssigned() {
		this.assigned = false;
		this.slotTime = null;
		this.doctorID = 0;
		this.patientID = 0;
		
	}
	
	
	
}
