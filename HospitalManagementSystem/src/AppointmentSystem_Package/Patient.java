package AppointmentSystem_Package;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

enum Gender {
	M,
	F
}

public class Patient {
	private int patientID;					// Patient ID Format: PAT23456 (PAT + 5 digits)
	private String name;					// Name of Patient
	private LocalDate dob;					// Date of Birth format DD-MM-YYYY
	private Gender gender;					// M / F
	private String phone;					// Start with 6, 8 or 9 (8 digits)
	private String email;					// email address with @
	private BloodTypes blood;				// 8 blood types: A+, A-, B+, B-, AB+, AB-, O+, O-
	private String pastDiag;				// Placeholder for Past Diagnoses
	private String treatment;				// Placeholder for Treatment
	private Doctor doc;						// Assigned Doctor?
	private ArrayList<Appointment> app;		// Appointment for Patient using ArrayList
	
	public Patient(int patientID, String name, LocalDate dob, Gender gender, String phone, String email, BloodTypes blood,
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
	public String getPhone() {
		return phone;
	}
	
	// Update Personal Info - Phone
	public void setPhone(String phone) {
		char first = phone.charAt(0);
		if(phone.length() == 8 && (first == '6' || first == '8' || first == '9'))
			this.phone = phone;
		else
			System.out.println("Please enter a valid phone number. (8 digits starting with '6', '8' or '9'.");
	}

	// Retrieve Personal Info - Email
	public String getEmail() {
		return email;
	}
	
	// Update Personal Info - Email
	public void setEmail(String email) {
		if(email.contains("@") && email.contains(".com"))
			this.email = email;
		else
			System.out.println("Please provide a valid email address.");
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

	public Gender getGender() {
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
		System.out.println("Patient ID: " + this.patientID);
		System.out.println("Patient Name: " + this.name);
		System.out.println("Gender: " + this.gender);
		
		// Format the DOB
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formattedDob = this.dob.format(formatter);
		System.out.println("Date of Birth: " + formattedDob);
		
		System.out.println("Blood Type: " + this.blood);
		System.out.println("---------------------------------------------------");
		
		System.out.println("Assigned Doctor: " + this.doc.getName());
		System.out.println("Diagnoses: " + this.pastDiag);
		System.out.println("Recommended Treatment: " + this.treatment);
		
		
		
	}
	
	// Only able to get Doc Name
	public String getDoc() {
		return doc.getName();
	}
	
	// Assign Doctor
	public void setDoc(Doctor doc) {
		this.doc = doc;
	}


	
	
	
	

}





