package AppointmentSystem;

import Controllers.AMManagers.DoctorAM;

public class Doctor {
	private String doctorID;			// DOC12345 (DOC + 5 digits)
	private String name;
	private Patient[] patients;
	private DoctorAM AM_D;	// AM for Doc to allow them to set personal schedule, manage appointmnents
	
	public Doctor(String doctorID, String name, Patient[] patients) {
		//super();
		this.doctorID = doctorID;
		this.name = name;
		this.patients = patients;
		AM_D = new DoctorAM(this);
	}

	public Doctor() {
		
	}
	
	

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(String doctorID) {
		this.doctorID = doctorID;
	}

	public Patient[] getPatients() {
		return patients;
	}

	public void setPatients(Patient[] patients) {
		this.patients = patients;
	}



	public DoctorAM getAM_D() {
		return AM_D;
	}



	public void setAM_D(DoctorAM aM_D) {
		AM_D = aM_D;
	}

	
	
	

}
