package Common;

import java.util.HashMap;
import java.util.Map;

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

    public static BloodType fromString(String bloodtype) {
        // Convert the input to uppercase and remove spaces for flexible matching
        String normalizedInput = bloodtype.toUpperCase().replace(" ", "");

        BloodType result = STRING_TO_BLOODTYPE_MAP.get(normalizedInput);

        if (result == null) {
            return NA;
        }

        return result;
    }
}
