package DatabaseItems;

import java.util.List;

import Common.BloodType;
import Common.Gender;
import Common.ListConverter;
import Common.DatabaseItems;

public class MedicalRecord implements DatabaseItems {
    private String id;
    private String name;
    private String dob;
    private Gender gender;
    private String email;
    private String phone;
    private BloodType bloodType;
    private List<String> diagnoses;
    private List<String> treatments;

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

    //call the deserialisation method
    public MedicalRecord(String ...params){
        deserialise(params);
    }

    // id,name,dob,gender,email,phone,bloodtype,diagnoses,treatments
    //interface functions
    public void deserialise(String ...params){
        this.id = params[0];
        this.name = params[1];
        this.dob = params[2];
        this.gender = Gender.fromString(params[3]);
        this.email = params[4];
        this.phone = params[5];
        this.bloodType = BloodType.fromString(params[6]);
        this.diagnoses = ListConverter.stringToList(params[7]);
        this.treatments = ListConverter.stringToList(params[8]);
    }

    public String serialise(){
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
        this.id,
        this.name,
        this.dob,
        this.gender.toString(),
        this.email,
        this.phone,
        this.bloodType.toString(),
        ListConverter.listToString(this.diagnoses),
        ListConverter.listToString(this.treatments)
        );
    }

    public void printItem(){
        System.out.println(); // Print a new line for better readability
        System.out.println("ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Date of Birth: " + dob);
        System.out.println("Gender: " + this.gender);
        System.out.println("Email: " + this.email);
        System.out.println("Phone: " + this.phone);
        System.out.println("Blood Type: " + this.bloodType);
        System.out.println("Diagnoses: " + String.join(", ", this.diagnoses));
        System.out.println("Treatments: " + String.join(", ", this.treatments));
    }

    // getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public Gender getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public List<String> getDiagnoses() {
        return diagnoses;
    }

    public List<String> getTreatments() {
        return treatments;
    }

    // setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public void setDiagnoses(List<String> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public void setTreatments(List<String> treatments) {
        this.treatments = treatments;
    }
}
