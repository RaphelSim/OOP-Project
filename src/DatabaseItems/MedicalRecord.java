package DatabaseItems;

import java.util.List;

import Common.BloodType;
import Common.Gender;
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
