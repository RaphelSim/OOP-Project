package Common;

public enum BloodType {
    NA,
    A_POSITIVE,
    A_NEGATIVE,
    B_POSITIVE,
    B_NEGATIVE,
    AB_POSITIVE,
    AB_NEGATIVE,
    O_POSITIVE,
    O_NEGATIVE;

    public static BloodType fromString(String bloodtype) {
        try {
            return BloodType.valueOf(bloodtype.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role: " + bloodtype + "\nSet to Patient by default");
            return BloodType.NA;
        }
    }
}
