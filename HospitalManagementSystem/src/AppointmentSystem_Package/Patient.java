package AppointmentSystem_Package;
import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

enum BloodTypes {
	A_POSITIVE,
    A_NEGATIVE,
    B_POSITIVE,
    B_NEGATIVE,
    AB_POSITIVE,
    AB_NEGATIVE,
    O_POSITIVE,
    O_NEGATIVE
}

public class Patient implements AppointmentManager{
	private int patientID;		// Patient ID 
	private String name;		// Name of Patient
	private LocalDate dob;		// Date of Birth format DD-MM-YYYY
	private char gender;		// M / F
	private int phone;			// Start with 6, 8 or 9 (8 digits)
	private String email;		// email address with @
	private BloodTypes blood;		// 8 blood types: A+, A-, B+, B-, AB+, AB-, O+, O-
	private String pastDiag;	// Placeholder for Past Diagnoses
	private String treatment;	// Placeholder for Treatment
	private int doctorID;		// Assigned Doctor?
	
	public Patient(int patientID, String name, LocalDate dob, char gender, int phone, String email, BloodTypes blood,
			String pastDiag, String treatment) {
		//super();
		this.patientID = patientID;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.blood = blood;
		this.pastDiag = pastDiag;
		this.treatment = treatment;
	}
	
	// Retrieve Personal Info - Phone
	public int getPhone() {
		return phone;
	}
	
	// Update Personal Info - Phone
	public void setPhone(int phone) {
		this.phone = phone;
	}

	// Retrieve Personal Info - Email
	public String getEmail() {
		return email;
	}
	
	// Update Personal Info - Email
	public void setEmail(String email) {
		this.email = email;
	}

	public int getPatientID() {
		return patientID;
	}

	public String getName() {
		return name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public char getGender() {
		return gender;
	}

	public BloodTypes getBlood() {
		return blood;
	}

	public String getPastDiag() {
		return pastDiag;
	}

	public String getTreatment() {
		return treatment;
	}
	
	// View Medical Record
	public void viewMedicalRecord() {
		
	}

	@Override
	public void viewSlots() {
		 
		
	}

	@Override
	public void schedule() {
		 
		
	}

	@Override
	public void reschedule() {
		 
		
	}

	@Override
	public void cancel() {
		 
		
	}

	// Not Applicable
	public void accept() {
		 
		
	}

	//Not Applicable
	public void reject() {
		 
		
	}
	
	
	
	

}

//// Format the date
//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//String formattedDob = dob.format(formatter);
//System.out.println("Formatted Date of Birth: " + formattedDob);



