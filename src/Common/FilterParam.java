package Common;

/**
 * The {@code FilterParam} enum represents the different parameters that can be used
 * for filtering data in the system. It includes parameters such as GENDER, ROLE,
 * and AGE.
 */
public enum FilterParam {
    /** Represents the gender filter parameter. */
    GENDER,

    /** Represents the role filter parameter. */
    ROLE,

    /** Represents the age filter parameter. */
    AGE;

    /**
     * Converts a string representation of a filter parameter to its corresponding
     * {@code FilterParam} enum value.
     *
     * @param filter the string representation of the filter parameter
     * @return the corresponding {@code FilterParam} enum value
     *         or {@code ROLE} if the input is invalid
     */
    public static FilterParam fromString(String filter) {
        try {
            return FilterParam.valueOf(filter.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid FilterParam: " + filter + "\nSet to ROLE by default");
            return FilterParam.ROLE; // Default to ROLE if invalid
        }
    }
}