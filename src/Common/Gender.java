package Common;

public enum Gender {
    MALE,
    FEMALE,
    NA;

    public static Gender fromString(String gender) {
        try {
            return Gender.valueOf(gender.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid gender: " + gender + "\nSet to NA by default");
            return Gender.NA;
        }
    }
}
