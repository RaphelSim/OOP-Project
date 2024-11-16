package DatabaseItems;

import java.util.List;

import Common.BloodType;
import Common.Gender;
import Common.ListConverter;
import Common.DatabaseItems;

/**
 * The {@code MedicalRecord} class represents a patient's medical record.
 * It implements the {@link DatabaseItems} interface and provides methods
 * for serialization, deserialization, and displaying medical record details.
 */
public class MedicalRecord implements DatabaseItems {
    private String id; // Unique identifier for the medical record
    private String name; // Name of the patient
    private String dob; // Date of birth of the patient
    private Gender gender; // Gender of the patient
    private String email; // Email address of the patient
    private String phone; // Phone number of the patient
    private BloodType bloodType; // Blood type of the patient
    private List<String> diagnoses; // List of diagnoses for the patient
    private List<String> treatments; // List of treatments for the patient

    /**
     * Constructs a {@code MedicalRecord} instance with specified attributes.
     *
     * @param id            the unique identifier for the medical record
     * @param name          the name of the patient
     * @param dob           the date of birth of the patient
     * @param gender        the gender of the patient
     * @param email         the email address of the patient
     * @param phone         the phone number of the patient
     * @param bloodType     the blood type of the patient
     * @param diagnoses      a list of diagnoses for the patient
     * @param treatments    a list of treatments for the patient
     */
    public MedicalRecord(String id, String name, String dob, Gender gender,
                         String email, String phone, BloodType bloodType,
                         List<String> diagnoses, List<String> treatments) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.bloodType = bloodType;
        this.diagnoses = diagnoses;
        this.treatments = treatments;
    }

    /**
     * Constructs a {@code MedicalRecord} instance by deserializing from an array of parameters.
     *
     * @param params an array of strings containing serialized data for the medical record
     */
    public MedicalRecord(String... params) {
        deserialise(params); // Call the deserialization method
    }

    /**
     * Deserializes parameters into a {@code MedicalRecord} object.
     *
     * @param params an array of strings containing serialized data for the medical record
     */
    public void deserialise(String... params) {
        this.id = params[0]; // Set ID from params
        this.name = params[1]; // Set name from params
        this.dob = params[2]; // Set date of birth from params
        this.gender = Gender.fromString(params[3]); // Set gender from params
        this.email = params[4]; // Set email from params
        this.phone = params[5]; // Set phone from params
        this.bloodType = BloodType.fromString(params[6]); // Set blood type from params
        this.diagnoses = ListConverter.stringToList(params[7]); // Convert string to list for diagnoses
        this.treatments = ListConverter.stringToList(params[8]); // Convert string to list for treatments
    }

    /**
     * Serializes the {@code MedicalRecord} object into a CSV format string.
     *
     * @return a string representing the serialized data of the medical record
     */
    public String serialise() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
                this.id,
                this.name,
                this.dob,
                this.gender.toString(),
                this.email,
                this.phone,
                this.bloodType.toString(),
                ListConverter.listToString(this.diagnoses),
                ListConverter.listToString(this.treatments)); // Format as CSV
    }

    /**
     * Prints details of the medical record to the console.
     */
    public void printItem() {
        System.out.println(); // Print a new line for better readability
        System.out.println("ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Date of Birth: " + dob);
        System.out.println("Gender: " + this.gender);
        System.out.println("Email: " + this.email);
        System.out.println("Phone: " + this.phone);
        System.out.println("Blood Type: " + this.bloodType);
        printDiagnoses(); // Print diagnoses details
        printTreatments(); // Print treatments details
    }

    /**
     * Prints all diagnoses associated with this medical record.
     */
    public void printDiagnoses() {
        System.out.println("Diagnoses:");
        for (int i = 0; i < this.diagnoses.size(); i++) {
            System.out.println("   " + (i + 1) + ". " + this.diagnoses.get(i)); // Print each diagnosis with indexing
        }
    }

    /**
     * Prints all treatments associated with this medical record.
     */
    public void printTreatments() {
        System.out.println("Treatments:");
        for (int i = 0; i < this.treatments.size(); i++) {
            System.out.println("   " + (i + 1) + ". " + this.treatments.get(i)); // Print each treatment with indexing
        }
    }

    // Getters

    /**
     * Returns the unique identifier of the medical record.
     *
     * @return the ID of the medical record
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the patient.
     *
     * @return the name of the patient
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the date of birth of the patient.
     *
     * @return the date of birth as a string
     */
    public String getDob() {
        return dob;
    }

    /**
     * Returns the gender of the patient.
     *
     * @return the gender as a {@link Gender} object
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Returns the email address of the patient.
     *
     * @return the email address as a string
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the phone number of the patient.
     *
     * @return the phone number as a string
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Returns the blood type of the patient.
     *
     * @return the blood type as a {@link BloodType} object
     */
    public BloodType getBloodType() {
        return bloodType;
    }

    /**
     * Returns a list of diagnoses associated with this medical record.
     *
     * @return a list of diagnoses as strings
     */
    public List<String> getDiagnoses() {
        return diagnoses;
    }

    /**
     * Returns a list of treatments associated with this medical record.
     *
     * @return a list of treatments as strings
     */
    public List<String> getTreatments() {
        return treatments;
    }

   // Setters

   /**
   * Sets a new email address for the patient.
   *
   * @param email The new email address to set.
   */
   public void setEmail(String email) {
       this.email = email; 
   }

   /**
   * Sets a new phone number for the patient.
   *
   * @param phone The new phone number to set.
   */
   public void setPhone(String phone) {
       this.phone = phone; 
   }

   /**
   * Sets a new blood type for the patient.
   *
   * @param bloodType The new blood type to set.
   */
   public void setBloodType(BloodType bloodType) {
       this.bloodType = bloodType; 
   }

   /**
   * Sets new diagnoses for the patient's medical record.
   *
   * @param diagnoses The new list of diagnoses to set.
   */
   public void setDiagnoses(List<String> diagnoses) {
       this.diagnoses = diagnoses; 
   }

   /**
   * Sets new treatments for the patient's medical record.
   *
   * @param treatments The new list of treatments to set.
   */
   public void setTreatments(List<String> treatments) {
       this.treatments = treatments; 
   }
}