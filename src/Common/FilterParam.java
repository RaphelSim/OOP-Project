package Common;

public enum FilterParam {
    GENDER,
    ROLE,
    AGE;

    public static FilterParam fromString(String filter) {
        try {
            return FilterParam.valueOf(filter.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid FilterParam: " + filter + "\nSet to ROLE by default");
            return FilterParam.ROLE;
        }
    }
}
