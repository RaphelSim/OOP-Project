package AppointmentSystem_Package;

public class Doctor {
	private int doctorID;
	private Patient[] patients;
	
	public Doctor(int doctorID, Patient[] patients) {
		//super();
		this.doctorID = doctorID;
		this.patients = patients;
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
