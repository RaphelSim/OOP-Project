package Common;

/**
 * The {@code Role} enum represents the different roles that users can have
 * in the system. It includes roles such as DOC (Doctor), PAT (Patient),
 * PHA (Pharmacist), and ADM (Administrator).
 */
public enum Role {
    /** Represents a Doctor role. */
    DOC,

    /** Represents a Patient role. */
    PAT,

    /** Represents a Pharmacist role. */
    PHA,

    /** Represents an Administrator role. */
    ADM;

    /**
     * Converts a string representation of a role to its corresponding
     * {@code Role} enum value.
     *
     * @param role the string representation of the role
     * @return the corresponding {@code Role} enum value
     *         or {@code PAT} if the input is invalid
     */
    public static Role fromString(String role) {
        try {
            return Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role: " + role + "\nSet to Patient by default");
            return Role.PAT; // Default to Patient if invalid
        }
    }
}