package Common;

public enum Role {
    DOC,
    PAT,
    PHA,
    ADM;

    public static Role fromString(String role) {
        try {
            return Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role: " + role + "\nSet to Patient by default");
            return Role.PAT;
        }
    }
}
