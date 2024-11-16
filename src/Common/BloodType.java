package Common;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code BloodType} enum represents different blood types.
 * It includes types such as A, B, AB, and O, along with their positive and negative variants.
 */
public enum BloodType {
    /** Represents a non-applicable blood type. */
    NA,
    
    /** Represents A positive blood type. */
    A_POSITIVE,
    
    /** Represents A negative blood type. */
    A_NEGATIVE,
    
    /** Represents B positive blood type. */
    B_POSITIVE,
    
    /** Represents B negative blood type. */
    B_NEGATIVE,
    
    /** Represents AB positive blood type. */
    AB_POSITIVE,
    
    /** Represents AB negative blood type. */
    AB_NEGATIVE,
    
    /** Represents O positive blood type. */
    O_POSITIVE,
    
    /** Represents O negative blood type. */
    O_NEGATIVE;

    // Map to hold alternative representations for each blood type
    private static final Map<String, BloodType> STRING_TO_BLOODTYPE_MAP = new HashMap<>();

    // Static block to initialize the map
    static {
        STRING_TO_BLOODTYPE_MAP.put("A+", A_POSITIVE);
        STRING_TO_BLOODTYPE_MAP.put("A-", A_NEGATIVE);
        STRING_TO_BLOODTYPE_MAP.put("B+", B_POSITIVE);
        STRING_TO_BLOODTYPE_MAP.put("B-", B_NEGATIVE);
        STRING_TO_BLOODTYPE_MAP.put("AB+", AB_POSITIVE);
        STRING_TO_BLOODTYPE_MAP.put("AB-", AB_NEGATIVE);
        STRING_TO_BLOODTYPE_MAP.put("O+", O_POSITIVE);
        STRING_TO_BLOODTYPE_MAP.put("O-", O_NEGATIVE);

        // Adding enum name mappings as well
        for (BloodType type : BloodType.values()) {
            STRING_TO_BLOODTYPE_MAP.put(type.name(), type);
        }
    }

    /**
     * Converts a string representation of a blood type to its corresponding
     * {@code BloodType} enum value.
     *
     * @param bloodtype the string representation of the blood type
     * @return the corresponding {@code BloodType} enum value or {@code NA}
     *         if the input does not match any known blood types
     */
    public static BloodType fromString(String bloodtype) {
        // Convert the input to uppercase and remove spaces for flexible matching
        String normalizedInput = bloodtype.toUpperCase().replace(" ", "");

        BloodType result = STRING_TO_BLOODTYPE_MAP.get(normalizedInput);

        if (result == null) {
            return NA; // Return NA if no match found
        }

        return result; // Return the matched BloodType
    }
}