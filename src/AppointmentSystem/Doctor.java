package AppointmentSystem;

public class Doctor {
	private int doctorID;			// DOC12345 (DOC + 5 digits)
	private String name;
	private Patient[] patients;
	private AppointmentManager AM_D;	// AM for Doc to allow them to set personal schedule, manage appointmnents
	
	public Doctor(int doctorID, String name, Patient[] patients) {
		//super();
		this.doctorID = doctorID;
		this.name = name;
		this.patients = patients;
	}
	
	

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

	public Patient[] getPatients() {
		return patients;
	}

	public void setPatients(Patient[] patients) {
		this.patients = patients;
	}
	
	

}
