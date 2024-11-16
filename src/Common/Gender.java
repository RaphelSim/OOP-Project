package Common;

/**
 * The {@code Gender} enum represents the possible gender values for users in the system.
 * It includes values such as MALE, FEMALE, and NA (not applicable).
 */
public enum Gender {
    /** Represents male gender. */
    MALE,

    /** Represents female gender. */
    FEMALE,

    /** Represents a non-applicable gender. */
    NA;

    /**
     * Converts a string representation of gender to its corresponding
     * {@code Gender} enum value.
     *
     * @param gender the string representation of the gender
     * @return the corresponding {@code Gender} enum value
     *         or {@code NA} if the input is invalid
     */
    public static Gender fromString(String gender) {
        try {
            return Gender.valueOf(gender.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid gender: " + gender + "\nSet to NA by default");
            return Gender.NA; // Default to NA if invalid
        }
    }
}